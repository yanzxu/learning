package com.spring.ioc;

import lombok.Data;

@Data
public class BeanDefinition {
    private String className;
    private String alias;
    private String superNames;

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getSuperNames() {
        return superNames;
    }

    public void setSuperNames(String superNames) {
        this.superNames = superNames;
    }
}
