package com.swing.layout;

import javax.swing.*;
import java.awt.*;

public class FlowLayoutDemo {
    public static void main(String[] agrs) {
        JFrame jFrame = new JFrame("Java GUI 004");
        JPanel jPanel = new JPanel();
        JButton btn1 = new JButton("1");
        JButton btn2 = new JButton("2");
        JButton btn3 = new JButton("3");
        JButton btn4 = new JButton("4");
        JButton btn5 = new JButton("5");
        JButton btn6 = new JButton("6");
        JButton btn7 = new JButton("7");
        JButton btn8 = new JButton("8");
        JButton btn9 = new JButton("9");
        jPanel.add(btn1);
        jPanel.add(btn2);
        jPanel.add(btn3);
        jPanel.add(btn4);
        jPanel.add(btn5);
        jPanel.add(btn6);
        jPanel.add(btn7);
        jPanel.add(btn8);
        jPanel.add(btn9);

        jPanel.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 20));
        jPanel.setBackground(Color.gray);
        jFrame.add(jPanel);
        jFrame.setBounds(300, 200, 300, 150);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
