package com.xyz.demo.core;

import com.xyz.demo.core.annotaion.MyAutowired;
import com.xyz.demo.core.annotaion.MyController;
import com.xyz.demo.core.annotaion.MyRequestMapping;
import com.xyz.demo.core.annotaion.MyService;
import org.springframework.util.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MyDispatcherServlet extends HttpServlet {
    private Map<String, Object> resourceHolder = new HashMap<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500 Exception " + Arrays.toString(e.getStackTrace()));
        }
    }

    private void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String url = request.getRequestURI().replace(request.getContextPath(), "").replaceAll("/+", "/");

        if (!resourceHolder.containsKey(url)) {
            response.getWriter().write("404 not found: " + url);
            return;
        }

        Method method = (Method) resourceHolder.get(url);

        Map<String, String[]> params = request.getParameterMap();
        method.invoke(resourceHolder.get(method.getDeclaringClass().getName()),
                request, response, params.get("name")[0]);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        InputStream inputStream = null;

        try {
            Properties properties = new Properties();
            inputStream = this.getClass().getClassLoader().getResourceAsStream(config.getInitParameter("contextConfigLocation"));
            properties.load(inputStream);
            String scanPackage = properties.getProperty("scanPackage");

            scanPackages(scanPackage);

            for (String className : resourceHolder.keySet()) {
                if (!className.contains(".")) {
                    continue;
                }

                Class<?> clazz = Class.forName(className);
                if (clazz.isAnnotationPresent(MyController.class)) {
                    resourceHolder.put(className, clazz.newInstance());
                    String baseUrl = "";
                    if (clazz.isAnnotationPresent(MyRequestMapping.class)) {
                        baseUrl = clazz.getAnnotation(MyRequestMapping.class).value();
                    }

                    Method[] methods = clazz.getMethods();
                    for (Method method : methods) {
                        if (!method.isAnnotationPresent(MyRequestMapping.class)) {
                            continue;
                        }

                        String url = (baseUrl + "/" + method.getAnnotation(MyRequestMapping.class).value()).replaceAll("/+", "/");
                        resourceHolder.put(url, method);
                        System.out.println("==== add resource to holder, key: " + url + "  value: " + method);
                    }
                } else if (clazz.isAnnotationPresent(MyService.class)) {
                    String beanName = clazz.getAnnotation(MyService.class).value();
                    if (StringUtils.isEmpty(beanName)) {
                        beanName = clazz.getName();
                    }

                    Object instance = clazz.newInstance();
                    resourceHolder.put(beanName, inputStream);

                    for (Class<?> i : clazz.getInterfaces()) {
                        resourceHolder.put(i.getName(), instance);
                    }
                }
            }

            for (Object object : resourceHolder.values()) {
                if (object == null) {
                    continue;
                }

                Class<?> clazz = object.getClass();
                if (clazz.isAnnotationPresent(MyController.class)) {
                    Field[] fields = clazz.getDeclaredFields();
                    for (Field field : fields) {
                        if (!field.isAnnotationPresent(MyAutowired.class)) {
                            continue;
                        }

                        String beanName = field.getAnnotation(MyAutowired.class).value();
                        if (StringUtils.isEmpty(beanName)) {
                            beanName = field.getType().getName();
                        }

                        field.setAccessible(true);
                        try {
                            field.set(resourceHolder.get(clazz.getName()), resourceHolder.get(beanName));
                        } catch (IllegalAccessException e) {
                            System.out.println("Exception: 自动注入bean失败，beanName: " + beanName);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error: init dispatcher失败");
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void scanPackages(String packagePath) {
        URL resource = this.getClass().getClassLoader().getResource("/" + packagePath.replaceAll("\\.", "/"));
        File[] files = new File(resource.getFile()).listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                scanPackages(packagePath + "." + file.getName());
            } else {
                if (file.getName().endsWith(".class")) {
                    String className = packagePath + "." + file.getName().replace(".class", "");
                    resourceHolder.put(className, null);
                }
            }
        }
    }
}
