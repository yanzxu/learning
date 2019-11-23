package com.spring.delegate;

public class Leader implements Employee {

    @Override
    public void doSomething(String thing) {
        new EmployeeA().doSomething(thing);
    }
}
