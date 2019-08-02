package com.spring.rpc;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Map;
import java.util.Set;

public class RpcInitConfig implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        ClassPathScanningCandidateComponentProvider provider = getScanner();
        //设置扫描器
        provider.addIncludeFilter(new AnnotationTypeFilter(RpcClient.class));
        //扫描此包下的所有带有@RpcClient的注解的类
        Set<BeanDefinition> beanDefinitionSet = provider.findCandidateComponents("com.spring.rpc");
        for (BeanDefinition beanDefinition : beanDefinitionSet){
            if (beanDefinition instanceof AnnotatedBeanDefinition){
                //获得注解上的参数信息
                AnnotatedBeanDefinition annotatedBeanDefinition = (AnnotatedBeanDefinition) beanDefinition;
                String beanClassAllName = beanDefinition.getBeanClassName();
                Map<String, Object> paraMap = annotatedBeanDefinition.getMetadata()
                        .getAnnotationAttributes(RpcClient.class.getCanonicalName());
                //将RpcClient的工厂类注册进去
                BeanDefinitionBuilder builder = BeanDefinitionBuilder
                        .genericBeanDefinition(RpcClientFactoryBean.class);
                //设置RpcClientFactoryBean工厂类中的构造函数的值
                builder.addConstructorArgValue(beanClassAllName);
                builder.getBeanDefinition().setAutowireMode(AbstractBeanDefinition.AUTOWIRE_BY_TYPE);
                //将其注册进容器中
                registry.registerBeanDefinition(
                        beanClassAllName ,
                        builder.getBeanDefinition());
            }
        }
    }

    protected ClassPathScanningCandidateComponentProvider getScanner() {
        return new ClassPathScanningCandidateComponentProvider(false) {
            @Override
            protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
                return beanDefinition.getMetadata().isInterface() && beanDefinition.getMetadata().isIndependent();
            }
        };
    }
}
