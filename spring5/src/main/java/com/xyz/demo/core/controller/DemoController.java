package com.xyz.demo.core.controller;

import com.xyz.demo.core.annotaion.MyAutowired;
import com.xyz.demo.core.annotaion.MyController;
import com.xyz.demo.core.annotaion.MyRequestMapping;
import com.xyz.demo.core.annotaion.MyRequestParam;
import com.xyz.demo.core.service.DemoServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MyController
@MyRequestMapping("/demo")
public class DemoController {
    @MyAutowired
    private DemoServiceImpl demoService;

    @MyRequestMapping("/query")
    public void query(HttpServletRequest request, HttpServletResponse response, @MyRequestParam("name") String name){
        String result = demoService.getName(name);

        try {
            response.getWriter().write(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
