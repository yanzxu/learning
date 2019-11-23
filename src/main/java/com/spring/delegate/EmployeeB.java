package com.spring.delegate;

public class EmployeeB implements Employee {
    @Override
    public void doSomething(String thing) {
        System.out.println("I am B " + thing);
    }
}
