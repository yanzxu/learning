package com.demo.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 对配置文件进行查找、读取、解析
 */
public class MyBeanDefinitionReader {
    private List<String> registryBeanClasses = new ArrayList<>();

    private Properties config = new Properties();

    // 固定我配置文件的key
    private final String SCAN_PACKAGE = "scanPackage";

    public MyBeanDefinitionReader(String... locations) {
        final InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(locations[0].replace("classpath:", ""));
        try {
            config.load(inputStream);
        } catch (IOException e) {
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

        doScanner(config.getProperty(SCAN_PACKAGE));

    }

    public Properties getConfig() {
        return this.config;
    }

    // 把配置文件扫描到的所有配置信息转换为MyBeanDefinition对象，以便以后的IOC操作
    public List<MyBeanDefinition> loadBeanDefinitions() {
        List<MyBeanDefinition> result = new ArrayList<>();

        try {
            for (String className : registryBeanClasses) {
                final Class<?> beanClass = Class.forName(className);
                if (beanClass.isInterface()) {
                    continue;
                }

                result.add(doCreateBeanDefinition(toLowerFirstCase(beanClass.getSimpleName()), beanClass.getName()));

                Class<?>[] interfaces = beanClass.getInterfaces();
                for (Class<?> i : interfaces) {
                    result.add(doCreateBeanDefinition(toLowerFirstCase(i.getSimpleName()), beanClass.getName()));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    //把每一个配置信息解析成一个BeanDefinition
    private MyBeanDefinition doCreateBeanDefinition(String factoryBeanName, String beanClassName) {
        final MyBeanDefinition beanDefinition = new MyBeanDefinition();
        beanDefinition.setBeanClassName(beanClassName);
        beanDefinition.setFactoryBeanName(factoryBeanName);
        return beanDefinition;
    }

    private String toLowerFirstCase(String simpleName) {
        final char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    private void doScanner(String scanPackage) {
        final URL url = this.getClass().getClassLoader().getResource("/" + scanPackage.replaceAll("\\.", "/"));
        final File classPath = new File(url.getFile());
        for (File file : classPath.listFiles()) {
            if (file.isDirectory()) {
                doScanner(scanPackage + "." + file.getName());
            } else {
                if (file.getName().endsWith(".class")) {
                    String className = scanPackage.concat(".").concat(file.getName().replace(".class", ""));
                    registryBeanClasses.add(className);
                }
            }
        }
    }

}
