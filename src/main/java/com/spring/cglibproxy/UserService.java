package com.spring.cglibproxy;

import org.springframework.stereotype.Component;

@Component
public class UserService {
    public void print() {
        System.out.println("==== 真正的业务逻辑 ====");
    }

    public void printClassName() {
        System.out.println("==== class name: UserService ====");
    }
}
