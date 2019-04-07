package com.spring.JDKproxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class JDKDogProxy {
    @Autowired
    private Dog dog; // 创建目标对象

    // 获取代理对象
    public Animal getJDKProxy() {
        // 参数一：ClassLoader loader，指定使用哪个类装载器生成代理对象，一般用代理类的类加载器；
        // 参数二：Class<?>[] interfaces，通过指定接口来指定哪个对象的代理对象，即指定代理类实现的接口；
        // 参数三：InvocationHandler h，通过实现handler的invoke方法来指定要在代理对象的方法里做什么事；
        return (Animal) Proxy.newProxyInstance(Dog.class.getClassLoader(), Dog.class.getInterfaces(), (Object proxy, Method method, Object[] args) -> {
            if (method.getName().equals("printName")) {
                System.out.println("++++ 调用目标方法前处理 ++++");

                method.invoke(dog, args);

                System.out.println("++++ 调用目标方法后处理 ++++");
            }

            return null;
        });
    }
}
