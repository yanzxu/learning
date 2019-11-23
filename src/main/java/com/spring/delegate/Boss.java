package com.spring.delegate;

public class Boss {
    public void doThings(String thing){
        new Leader().doSomething("print PPT");
    }
}
