package com.swing.button;

import javax.swing.*;
import java.awt.*;

public class JButtonDemo {
    public static void main(String[] args)
    {
        JFrame frame=new JFrame("Java按钮组件示例");
        frame.setSize(400, 200);
        JPanel jp=new JPanel();
        JButton btn1=new JButton("我是普通按钮");
        JButton btn2=new JButton("我是带背景颜色按钮");
        JButton btn3=new JButton("我是不可用按钮");
        JButton btn4=new JButton("我是底部对齐按钮");

        jp.add(btn1);

        btn2.setBackground(Color.YELLOW);
        jp.add(btn2);

        btn3.setEnabled(false);    //设置按钮不可用
        jp.add(btn3);

        Dimension preferredSize=new Dimension(160, 60);    //设置尺寸
        btn4.setPreferredSize(preferredSize);    //设置按钮大小
        btn4.setVerticalAlignment(SwingConstants.BOTTOM);
        jp.add(btn4);

        frame.add(jp);
        frame.setBounds(300, 200, 600, 300);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
