package com.spring.cglibproxy;

public enum TestEnum {
    USER_SERVICE(UserService.class,"userService");

     Class class1;
     String className;

    TestEnum(Class class1, String className) {
        this.class1 = class1;
        this.className = className;
    }
}
