package com.spring.delegate;

public class EmployeeA implements Employee {
    @Override
    public void doSomething(String thing) {
        System.out.println("I am A " + thing);
    }
}
