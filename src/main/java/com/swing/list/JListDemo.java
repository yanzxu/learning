package com.swing.list;

import javax.swing.*;

public class JListDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Java列表框组件示例");
        JPanel jp = new JPanel();
        JLabel label1 = new JLabel("证件类型：");

        String[] items = new String[]{"身份证", "驾驶证", "军官证"};
        JList list = new JList(items);

        jp.add(label1);
        jp.add(list);

        frame.add(jp);
        frame.setBounds(300, 200, 400, 100);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
