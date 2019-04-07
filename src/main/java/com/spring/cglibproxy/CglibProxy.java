package com.spring.cglibproxy;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class CglibProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {

        System.out.println("++++ 目标对象执行前逻辑 ++++");
        Object result = methodProxy.invokeSuper(obj, args);
        System.out.println("++++ 目标对象执行后逻辑 ++++");

        return result;
    }

    public Object getProxyInstance(Object target) {
        // 创建工具类
        Enhancer enhancer = new Enhancer();
        // 设置父类
        enhancer.setSuperclass(target.getClass());
        // 设置要织入的类
        enhancer.setCallback(this);

        // 返回代理对象
        return enhancer.create();
    }
}
