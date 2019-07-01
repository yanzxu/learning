package com.java8.enumeration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class TestSize {

    @Test
    public void test01(){
        Size small = Size.SMALL;


        System.out.println("toString:  " + small.toString());
        System.out.println("name: " + small.name());
        System.out.println("small == Size.SMALL:  " + (small == Size.SMALL));
        System.out.println("small.equals(Size.SMALL):  " + (small.equals(Size.SMALL)));
        System.out.println("small.ordinal():  " + small.ordinal());
        System.out.println("Size.valueOf(\"SMALL\"):  " + small.valueOf("SMALL"));
    }
}
