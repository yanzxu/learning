package com.demo.ioc;

import com.xyz.demo.core.annotaion.MyAutowired;
import com.xyz.demo.core.annotaion.MyController;
import com.xyz.demo.core.annotaion.MyService;

import java.lang.reflect.Field;
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
        // 生成通知事件
        final MyBeanPostProcessor postProcessor = new MyBeanPostProcessor();
        final MyBeanDefinition beanDefinition = super.beanDefinitionMap.get(beanName);
        final Object instance = instantiateBean(beanDefinition);
        if (instance == null) {
            return null;
        }

        // 在实例初始化之前调用一次
        postProcessor.postProcessBeforeInitialization(instance, beanName);

        final MyBeanWrapper beanWrapper = new MyBeanWrapper(instance);
        factoryBeanInstanceCache.put(beanName, beanWrapper);
        // 在实例初始化之后再调用一次
        postProcessor.postProcessAfterInitialization(instance, beanName);
        populateBean(beanName, instance);

        return this.factoryBeanInstanceCache.get(beanName).getWrappedInstance();
    }

    @Override
    public Object getBean(Class<?> beanClass) throws Exception {
        return getBean(beanClass.getName());
    }

    private void populateBean(String beanName, Object instance) {
        final Class<?> clazz = instance.getClass();
        if (!(clazz.isAnnotationPresent(MyController.class) || clazz.isAnnotationPresent(MyService.class))) {
            return;
        }
        final Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if (!field.isAnnotationPresent(MyAutowired.class)) {
                continue;
            }
            String autowiredBeanName = field.getAnnotation(MyAutowired.class).value().trim();
            if ("".equals(autowiredBeanName)) {
                autowiredBeanName = field.getType().getName();
            }

            field.setAccessible(true);
            try {
                field.set(instance,this.factoryBeanInstanceCache.get(autowiredBeanName));
            }catch (IllegalAccessException e){
                e.printStackTrace();
            }
        }

    }

    private Object instantiateBean(MyBeanDefinition beanDefinition) {
        Object instance = null;
        try {
            final String className = beanDefinition.getBeanClassName();
            if (this.factoryBeanInstanceCache.containsKey(className)) {
                instance = this.factoryBeanInstanceCache.get(className);
            } else {
                final Class<?> clazz = Class.forName(className);
                instance = clazz.newInstance();
                this.factorySingleBeanCache.put(beanDefinition.getFactoryBeanName(), instance);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
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
