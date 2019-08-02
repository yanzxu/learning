package com.java8.enumeration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

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

    @Test
    public void testHashMap(){
        HashMap<String, String> map = new HashMap<>();
        map.put("aaa","AAA");
        map.put("aaa","bb");
        for (Map.Entry entry: map.entrySet()){
            System.err.println("key= "+ entry.getKey()+", value= "+ entry.getValue() );
        }
    }

    @Test
    public void testLinkedHashMap(){
//        HashMap<String, String> map = new HashMap<>();
//        map.put("aaa","AAA");
//        map.put("aaa","bb");
//        for (Map.Entry entry: map.entrySet()){
//            System.err.println("key= "+ entry.getKey()+", value= "+ entry.getValue() );
//        }

        List objects = Collections.emptyList();
        System.err.println(Collections.emptyList().toString());
        List emptyList = Collections.EMPTY_LIST;
        System.err.println(emptyList.toString());
    }
}
