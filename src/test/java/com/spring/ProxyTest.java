package com.spring;

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


    @Test
    public void tetStaticProxy() {
        staticProxy.delete();
    }
}
