package com.spring;

import com.spring.JDKproxy.Animal;
import com.spring.JDKproxy.JDKDogProxy;
import com.spring.cglibproxy.CglibProxy;
import com.spring.cglibproxy.UserService;
import com.spring.staticproxy.StaticProxyImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProxyTest {

    @Autowired
    private StaticProxyImpl staticProxy;
    @Autowired
    private JDKDogProxy jdkDogProxy;
    @Autowired
    private CglibProxy cglibProxy;
    @Autowired
    private UserService userService;

    @Test
    public void tetStaticProxy() {
        staticProxy.delete();
    }

    @Test
    public void testJDKProxy() {
        Animal dog = jdkDogProxy.getJDKProxy();
        dog.printName();
    }

    @Test
    public void testCglibProxy(){
        UserService proxyInstance = (UserService) cglibProxy.getProxyInstance(userService);
        proxyInstance.print();
        System.out.println("-------------------------------");
        proxyInstance.printClassName();
    }
}
