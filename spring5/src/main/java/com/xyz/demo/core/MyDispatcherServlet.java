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
import java.util.*;
import java.util.stream.Stream;

public class MyDispatcherServlet extends HttpServlet {
    // 用于保存类的实例
    private Map<String, Object> iocContainer = new HashMap<>();
    // 用于保存url和method的映射关系
    private Map<String, Object> handlerMapping = new HashMap<>();
    private Properties properties = new Properties();
    private List<String> classNames = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // 委派模式
            dispatchRequest(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500 Exception " + Arrays.toString(e.getStackTrace()));
        }
    }

    private void dispatchRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String url = request.getRequestURI().replace(request.getContextPath(), "").replaceAll("/+", "/");

        if (!iocContainer.containsKey(url)) {
            response.getWriter().write("404 not found: " + url);
            return;
        }

        Method method = (Method) handlerMapping.get(url);

        Map<String, String[]> params = request.getParameterMap();
        final String beanName = toLowerFistCase(method.getDeclaringClass().getSimpleName());

        System.out.println(String.format("===== invoke method: %s =====", method.getName()));
        // 利用反射进行动态委派
        method.invoke(iocContainer.get(beanName), request, response, params.get("name")[0]);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        loadConfigure(config.getInitParameter("contextConfigLocation"));

        scanPackages(properties.getProperty("scanPackage"));

        initInstances();

        autowireInstances();

        initHandlerMapping();

        System.out.println("===== Init spring framework successfully =====");
    }

    private void initHandlerMapping() {
        // 策略模式
        iocContainer.values().stream().map(Object::getClass).filter(clazz -> clazz.isAnnotationPresent(MyController.class))
                .forEach(clazz -> {
                    String baseUrl = clazz.isAnnotationPresent(MyRequestMapping.class) ? clazz.getAnnotation(MyRequestMapping.class).value() : "";

                    Stream.of(clazz.getMethods()).filter(method -> method.isAnnotationPresent(MyRequestMapping.class))
                            .forEach(method -> {
                                String url = ("/" + baseUrl + "/" + method.getAnnotation(MyRequestMapping.class).value()).replaceAll("/+", "/");
                                handlerMapping.put(url, method);
                                System.out.println(String.format("===== put url: %s and method : %s into handlerMapping", url, method));
                            });
                });
    }

    private void autowireInstances() {
        iocContainer.entrySet().forEach(entry -> {
            final Field[] declaredFields = entry.getValue().getClass().getDeclaredFields();
            Stream.of(declaredFields).filter(field -> field.isAnnotationPresent(MyAutowired.class)).forEach(field -> {
                String beanName = field.getAnnotation(MyAutowired.class).value();
                if (StringUtils.isEmpty(beanName)) {
                    beanName = field.getType().getName();
                }

                field.setAccessible(true);
                try {
                    field.set(entry.getValue(), iocContainer.get(beanName));
                    System.out.println(String.format("===== autowired beanName %s to filed: %s =====", beanName, field.getName()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            });
        });
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
                    classNames.add(className);
                    System.out.println(String.format("===== add className : %s to classNames", className));
                }
            }
        }
    }

    private void loadConfigure(String contextConfigLocation) {
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initInstances() {
        // 工厂模式
        classNames.stream().forEach(className -> {
            try {
                Class<?> clazz = Class.forName(className);
                final Object instance = clazz.newInstance();
                iocContainer.put(getBeanName(clazz), instance);
                Stream.of(clazz.getInterfaces()).forEach(i -> iocContainer.put(i.getName(), instance));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private String toLowerFistCase(String name) {
        final char[] chars = name.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    private String getBeanName(Class<?> clazz) {
        if (clazz.isAnnotationPresent(MyController.class)) {
            return StringUtils.isEmpty(clazz.getAnnotation(MyController.class).value()) ? toLowerFistCase(clazz.getSimpleName())
                    : clazz.getAnnotation(MyController.class).value();
        }

        if (clazz.isAnnotationPresent(MyService.class)) {
            return StringUtils.isEmpty(clazz.getAnnotation(MyService.class).value()) ? toLowerFistCase(clazz.getSimpleName())
                    : clazz.getAnnotation(MyService.class).value();
        }

        return "";
    }
}
