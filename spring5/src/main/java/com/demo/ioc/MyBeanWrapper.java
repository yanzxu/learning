package com.demo.ioc;

/**
 * 用于封装创建后的对象实例，代理对象或者原生对象都由该BeanWrapper保存
 */
public class MyBeanWrapper {
    private Object wrappedInstance;

    private Class<?> wrapperClass;

    public MyBeanWrapper(Object wrappedInstance){
        this.wrappedInstance = wrappedInstance;
    }

    public Object getWrappedInstance(){
        return this.wrappedInstance;
    }

    public Class<?> getWrapperClass(){
        return this.wrappedInstance.getClass();
    }
}
