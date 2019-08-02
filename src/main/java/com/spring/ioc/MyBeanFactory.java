package com.spring.ioc;

public interface MyBeanFactory {
    Object getBeanByName(String name) throws Exception;
}
