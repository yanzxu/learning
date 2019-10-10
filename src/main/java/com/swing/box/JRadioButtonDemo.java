package com.swing.box;

import javax.swing.*;
import java.awt.*;

public class JRadioButtonDemo {
    public static void main(String[] agrs) {
        JFrame frame = new JFrame("Java单选组件示例");
        JPanel panel = new JPanel();
        JLabel label1 = new JLabel("现在是哪个季节：");
        label1.setFont(new Font("楷体", Font.BOLD, 16));

        JRadioButton rb1 = new JRadioButton("春天");
        JRadioButton rb2 = new JRadioButton("夏天");
        JRadioButton rb3 = new JRadioButton("秋天", true);
        JRadioButton rb4 = new JRadioButton("冬天");

        ButtonGroup group = new ButtonGroup();
        group.add(rb1);
        group.add(rb2);

        panel.add(label1);
        panel.add(rb1);
        panel.add(rb2);
        panel.add(rb3);
        panel.add(rb4);
        frame.add(panel);
        frame.setBounds(300, 200, 400, 100);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
