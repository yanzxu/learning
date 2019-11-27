package com.xyz.demo.core;

import com.xyz.demo.core.annotaion.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class MyDispatcherServlet extends HttpServlet {

    private static final String LOCATION = "contextConfigLocation";
    public static final String SCAN_PACKAGE = "scanPackage";

    private Properties properties = new Properties();

    private List<String> classNames = new ArrayList<>();

    private Map<String, Object> iocContainer = new HashMap<>();

    private List<Handler> handlerMapping = new ArrayList<>();

    public MyDispatcherServlet() {
        super();
    }


    public void init(ServletConfig config) {

        //1、加载配置文件
        loadConfigurations(config.getInitParameter(LOCATION));

        //2、扫描所有相关的类
        scanPackages(properties.getProperty(SCAN_PACKAGE));

        //3、初始化所有相关类的实例，并保存到IOC容器中
        initInstances();

        //4、依赖注入
        autowireInstance();

        //5、构造HandlerMapping
        initHandlerMapping();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req, resp);

        } catch (Exception e) {
            resp.getWriter().write("500 Exception,Details:\r\n" + Arrays.toString(e.getStackTrace()).replaceAll("\\[|\\]", "").replaceAll(",\\s", "\r\n"));
        }
    }

    private void scanPackages(String packageName) {
        URL url = this.getClass().getClassLoader().getResource("/" + packageName.replaceAll("\\.", "/"));

        Stream.of(new File(url.getFile()).listFiles()).forEach(file -> {
            if (file.isDirectory()) {
                scanPackages(packageName + "." + file.getName());
            } else {
                classNames.add(packageName + "." + file.getName().replace(".class", "").trim());
            }

        });
    }

    private void loadConfigurations(String location) {
        try (InputStream input = this.getClass().getClassLoader().getResourceAsStream(location)) {
            properties.load(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initInstances() {
        if (classNames.size() == 0) {
            return;
        }

        try {
            for (String className : classNames) {
                Class<?> clazz = Class.forName(className);
                String beanName;

                if (clazz.isAnnotationPresent(MyController.class)) {
                    beanName = getBeanName(clazz, clazz.getAnnotation(MyController.class).value());
                } else if (clazz.isAnnotationPresent(MyService.class)) {
                    beanName = getBeanName(clazz, clazz.getAnnotation(MyService.class).value());
                } else {
                    continue;
                }

                final Object instance = clazz.newInstance();
                iocContainer.put(beanName, instance);
                Stream.of(clazz.getInterfaces()).forEach(i -> iocContainer.put(i.getName(), instance));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void autowireInstance() {
        if (iocContainer.isEmpty()) {
            return;
        }

        for (Map.Entry<String, Object> entry : iocContainer.entrySet()) {
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if (!field.isAnnotationPresent(MyAutowired.class)) {
                    continue;
                }

                String beanName = field.getAnnotation(MyAutowired.class).value().trim();
                if ("".equals(beanName)) {
                    beanName = field.getType().getName();
                }

                field.setAccessible(true);
                try {
                    field.set(entry.getValue(), iocContainer.get(beanName));
                } catch (Exception e) {
                    e.printStackTrace();
                    continue;
                }
            }
        }
    }

    private void initHandlerMapping() {
        if (iocContainer.isEmpty()) {
            return;
        }

        for (Map.Entry<String, Object> entry : iocContainer.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if (!clazz.isAnnotationPresent(MyController.class)) {
                continue;
            }

            String baseUrl = "";
            if (clazz.isAnnotationPresent(MyRequestMapping.class)) {
                baseUrl = clazz.getAnnotation(MyRequestMapping.class).value();
            }

            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                if (!method.isAnnotationPresent(MyRequestMapping.class)) {
                    continue;
                }

                String regex = baseUrl + method.getAnnotation(MyRequestMapping.class).value();
                Pattern pattern = Pattern.compile(regex);
                handlerMapping.add(new Handler(pattern, entry.getValue(), method));
            }
        }

    }

    private String getBeanName(Class<?> clazz, String name) {
        if (!"".equals(name.trim())) {
            return name;
        }

        char[] chars = clazz.getSimpleName().toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        try {
            Handler handler = getHandler(req);

            if (handler == null) {
                resp.getWriter().write("404 Not Found");
                return;
            }

            Class<?>[] paramTypes = handler.method.getParameterTypes();
            //保存所有需要自动赋值的参数值
            Object[] paramValues = new Object[paramTypes.length];


            Map<String, String[]> params = req.getParameterMap();
            for (Map.Entry<String, String[]> param : params.entrySet()) {
                String value = Arrays.toString(param.getValue()).replaceAll("[\\[\\]]", "").replaceAll(",\\s", ",");

                if (!handler.paramIndexMapping.containsKey(param.getKey())) {
                    continue;
                }

                int index = handler.paramIndexMapping.get(param.getKey());
                paramValues[index] = convert(paramTypes[index], value);
            }


            //设置方法中的request和response对象
            int reqIndex = handler.paramIndexMapping.get(HttpServletRequest.class.getName());
            paramValues[reqIndex] = req;

            int respIndex = handler.paramIndexMapping.get(HttpServletResponse.class.getName());
            paramValues[respIndex] = resp;

            handler.method.invoke(handler.controller, paramValues);

        } catch (Exception e) {
            throw e;
        }
    }

    private Handler getHandler(HttpServletRequest req) {
        if (handlerMapping.isEmpty()) {
            return null;
        }

        String url = req.getRequestURI().replace(req.getContextPath(), "");

        for (Handler handler : handlerMapping) {
            try {
                Matcher matcher = handler.pattern.matcher(url);
                if (!matcher.matches()) {
                    continue;
                }

                return handler;
            } catch (Exception e) {
                throw e;
            }
        }
        return null;
    }

    //url传过来的参数都是String类型的，HTTP是基于字符串协议
    //只需要把String转换为任意类型就好
    private Object convert(Class<?> type, String value) {
        if (Integer.class == type) {
            return Integer.valueOf(value);
        }
        //如果还有double或者其他类型，继续加if
        //这时候，我们应该想到策略模式了
        //在这里暂时不实现
        return value;
    }

    /**
     * Handler记录Controller中的RequestMapping和Method的对应关系
     */
    private class Handler {
        protected Object controller;
        protected Method method;
        protected Pattern pattern;
        protected Map<String, Integer> paramIndexMapping;    //参数顺序

        protected Handler(Pattern pattern, Object controller, Method method) {
            this.controller = controller;
            this.method = method;
            this.pattern = pattern;

            paramIndexMapping = new HashMap<>();
            putParamIndexMapping(method);
        }

        private void putParamIndexMapping(Method method) {
            //提取方法中加了注解的参数
            Annotation[][] pa = method.getParameterAnnotations();
            for (int i = 0; i < pa.length; i++) {
                for (Annotation a : pa[i]) {
                    if (a instanceof MyRequestParam) {
                        String paramName = ((MyRequestParam) a).value();
                        if (!"".equals(paramName.trim())) {
                            paramIndexMapping.put(paramName, i);
                        }
                    }
                }
            }

            //提取方法中的request和response参数
            Class<?>[] paramsTypes = method.getParameterTypes();
            for (int i = 0; i < paramsTypes.length; i++) {
                Class<?> type = paramsTypes[i];
                if (type == HttpServletRequest.class ||
                        type == HttpServletResponse.class) {
                    paramIndexMapping.put(type.getName(), i);
                }
            }
        }
    }
}
