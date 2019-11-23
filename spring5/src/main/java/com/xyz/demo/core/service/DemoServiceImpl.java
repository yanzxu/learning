package com.xyz.demo.core.service;

import com.xyz.demo.core.annotaion.MyService;

@MyService
public class DemoServiceImpl implements DemoService{
    @Override
    public String getName(String name){
        return "My name is: " + name;
    }
}
