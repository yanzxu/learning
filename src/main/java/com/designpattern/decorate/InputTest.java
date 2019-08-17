package com.designpattern.decorate;

import java.io.*;

public class InputTest {
    public static void main(String[] args) {
        int c;
        try {
            InputStream inputStream = new LowerCaseInputStream(
                    new BufferedInputStream(new FileInputStream(InputTest.class.getClassLoader().getResource("test.txt").getFile())));

            while ((c = inputStream.read()) >= 0){
                System.out.print((char)c);
            }

        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
