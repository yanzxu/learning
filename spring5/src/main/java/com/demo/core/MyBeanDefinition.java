package com.demo.core;

/**
 * 用于保存bean相关的配置信息
 */
public class MyBeanDefinition {
    private String beanClassName;// 原生bean的全类名

    private boolean lazyInit = false;// 是否延迟加载

    private String factoryBeanName;// 保存beanName, IOC容器存储的key

    public String getBeanClassName() {
        return beanClassName;
    }

    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    public boolean isLazyInit() {
        return lazyInit;
    }

    public void setLazyInit(boolean lazyInit) {
        this.lazyInit = lazyInit;
    }

    public String getFactoryBeanName() {
        return factoryBeanName;
    }

    public void setFactoryBeanName(String factoryBeanName) {
        this.factoryBeanName = factoryBeanName;
    }
}
