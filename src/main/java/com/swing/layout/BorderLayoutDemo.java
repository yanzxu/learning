package com.swing.layout;

import javax.swing.*;
import java.awt.*;

public class BorderLayoutDemo {
    public static void main(String[] agrs)
    {
        JFrame frame=new JFrame("Java GUI 003");
        frame.setSize(400,200);
        frame.setLayout(new BorderLayout());
        JButton button1=new JButton ("上");
        JButton button2=new JButton("左");
        JButton button3=new JButton("中");
        JButton button4=new JButton("右");
        JButton button5=new JButton("下");
        frame.add(button1,BorderLayout.NORTH);
        frame.add(button2,BorderLayout.WEST);
        frame.add(button3,BorderLayout.CENTER);
        frame.add(button4,BorderLayout.EAST);
        frame.add(button5,BorderLayout.SOUTH);
        frame.setBounds(300,200,600,300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
