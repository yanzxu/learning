package com.spring.JDKproxy;

public class TestPersonProxy {
    public static void main(String[] args) {
        Person person = new PersonProxy().getProxyInstance(new Student());
        person.speak();
    }
}
