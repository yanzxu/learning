package com.demo.core;

public class MyBeanPostProcessor {
    // 为bean的初始化之前提供回调入口
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }

    // 为bean的初始化之后提供回调入口
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }
}
