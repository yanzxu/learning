package com.spring.JDKproxy;

import org.springframework.stereotype.Component;

@Component
public class Dog implements Animal {
    @Override
    public void printName() {
        System.out.println("===== I am a dog ====");
    }
}
