package com.demo.core;

/**
 * IOC容器实现类的顶层抽象类，实现IOC容器相关的公共逻辑。
 */
public abstract class MyAbstractApplicationContext {
    // 只提供给子类重写
    public void refresh() throws Exception{

    }
}
