package com.spring.ioc;

import lombok.Data;

@Data
public class BeanDefinition {
    private String className;
    private String alias;
    private String superNames;
}
