package com.spring.JDKproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class PersonProxy implements InvocationHandler {
    private Person person;

    public Person getProxyInstance(Person target) {
        this.person = target;
        return (Person) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object invoke = method.invoke(this.person, args);
        after();

        return invoke;
    }


    private void before() {
        System.out.println("----- before invoke --------");
    }

    private void after() {
        System.out.println("------ after invoke -------");
    }
}
