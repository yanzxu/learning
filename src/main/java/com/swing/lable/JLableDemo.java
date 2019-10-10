package com.swing.lable;

import javax.swing.*;

public class JLableDemo {
    public static void main(String[] agrs) {
        JFrame frame = new JFrame("Java标签组件示例");
        JPanel jp = new JPanel();
        JLabel label1 = new JLabel("普通标签");
        JLabel label2 = new JLabel();
        label2.setText("调用setText()方法");
//        ImageIcon img = new ImageIcon("D:\\money.png");    //创建一个图标
        //创建既含有文本又含有图标的JLabel对象
//        JLabel label3 = new JLabel("开始理财", img, JLabel.CENTER);
        jp.add(label1);    //添加标签到面板
        jp.add(label2);
//        jp.add(label3);
        frame.add(jp);
        frame.setBounds(300, 200, 400, 100);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
