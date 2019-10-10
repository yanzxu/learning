package com.swing.layout;

import javax.swing.*;
import java.awt.*;

public class BoxLayoutDemo {
    public static void main(String[] agrs) {
        JFrame frame = new JFrame("Java GUI 005");
        Box b1 = Box.createHorizontalBox();    //创建横向Box容器
        Box b2 = Box.createVerticalBox();    //创建纵向Box容器
        frame.add(b1);    //将外层横向Box添加进窗体
        b1.add(Box.createVerticalStrut(200));    //添加高度为200的垂直框架
        b1.add(new JButton("西"));    //添加按钮1
        b1.add(Box.createHorizontalStrut(140));    //添加长度为140的水平框架
        b1.add(new JButton("东"));    //添加按钮2
        b1.add(Box.createHorizontalGlue());    //添加水平胶水
        b1.add(b2);    //添加嵌套的纵向Box容器

        //添加宽度为100，高度为20的固定区域
        b2.add(Box.createRigidArea(new Dimension(100, 20)));
        b2.add(new JButton("北"));    //添加按钮3
        b2.add(Box.createVerticalGlue());    //添加垂直组件
        b2.add(new JButton("南"));    //添加按钮4
        b2.add(Box.createVerticalStrut(40));    //添加长度为40的垂直框架
        //设置窗口的关闭动作、标题、大小位置以及可见性等

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(100, 100, 400, 200);
        frame.setVisible(true);
    }
}
