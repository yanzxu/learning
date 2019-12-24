package com.demo.core;

/**
 * 所有IOC容器的顶层设计
 */
public interface MyBeanFactory {
    Object getBean(String beanName) throws Exception;

    public Object getBean(Class<?> beanClass) throws Exception;
}
