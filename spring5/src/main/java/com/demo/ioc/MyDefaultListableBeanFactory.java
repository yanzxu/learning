package com.demo.ioc;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * IOC容器众多子类的典型代表，该mini版本只定义了顶层的IOC缓存
 */
public class MyDefaultListableBeanFactory extends MyAbstractApplicationContext{
    protected final Map<String, MyBeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

}
