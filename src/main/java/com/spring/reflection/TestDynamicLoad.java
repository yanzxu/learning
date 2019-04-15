package com.spring.reflection;

public class TestDynamicLoad {

    public static void main(String[] args) {
        new A();
        System.out.println("=======================");
        new B();

        new C();
        new C();

        new D();
        new D();
    }

}

class A{};

class B{};

class C{
    static {
        System.out.println("CCCCCCCCCCCCCCCCCCCC");
    }
};

class D{

    D() {
        System.out.println("D1D1D1D1D1D1D1D1D1D1D1D1D1D1D1D1D1D1D1D");
    }

    {
        System.out.println("D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2D2DD2D2D");
    }
};