package com.demo.core;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 用户接触的直接入口，主要实现DefaultListableBeanFactory的refresh()方法和BeanFactory的getBean()方法，完成IOC、DI、AOP的衔接
 */
public class MyApplicationContext extends MyDefaultListableBeanFactory implements MyBeanFactory {
    private String[] configLocations;
    private MyBeanDefinitionReader reader;
    // 单例的IOC容器缓存
    private Map<String, Object> factorySingleBeanCache = new ConcurrentHashMap<>();
    // 通用的IOC容器缓存
    private Map<String, MyBeanWrapper> factoryBeanInstanceCache = new ConcurrentHashMap<>();

    public MyApplicationContext(String... configLocations) {
        this.configLocations = configLocations;
        try {
            refresh();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void refresh() throws Exception {
        reader = new MyBeanDefinitionReader(this.configLocations);
        final List<MyBeanDefinition> beanDefinitions = reader.loadBeanDefinitions();
        doRegisterBeanDefinition(beanDefinitions);
        // 将不是延时加载类提前初始化
        doAutowired();
    }

    /**
     * 装饰器模式：1. 保留原有的oop关系；2.需要对他进行扩展、增强，为以后的Aop打基础；
     */
    @Override
    public Object getBean(String beanName) throws Exception {
        return null;//todo
    }

    @Override
    public Object getBean(Class<?> beanClass) throws Exception {
        return getBean(beanClass.getName());
    }

    private void doAutowired() {
        for (Map.Entry<String, MyBeanDefinition> entry : super.beanDefinitionMap.entrySet()) {
            final String beanName = entry.getKey();
            if (!entry.getValue().isLazyInit()) {
                try {
                    getBean(beanName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    private void doRegisterBeanDefinition(List<MyBeanDefinition> beanDefinitions) throws Exception {
        for (MyBeanDefinition bean : beanDefinitions) {
            if (super.beanDefinitionMap.containsKey(bean.getFactoryBeanName())) {
                throw new Exception("The \"" + bean.getFactoryBeanName() + "\" is already exists.");
            }

            super.beanDefinitionMap.put(bean.getFactoryBeanName(), bean);
        }
    }
}
