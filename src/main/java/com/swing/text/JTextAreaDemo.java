package com.swing.text;

import javax.swing.*;
import java.awt.*;

public class JTextAreaDemo {
    public static void main(String[] agrs) {
        JFrame frame = new JFrame("Java文本域组件示例");
        JPanel jp = new JPanel();

        JTextArea jta = new JTextArea("请输入内容", 7, 30);
        jta.setLineWrap(true);    //设置文本域中的文本为自动换行
        jta.setForeground(Color.BLACK);
        jta.setFont(new Font("楷体", Font.BOLD, 16));
        jta.setBackground(Color.YELLOW);    //设置按钮背景色

        JScrollPane jsp = new JScrollPane(jta);    //将文本域放入滚动窗口
        Dimension size = jta.getPreferredSize();    //获得文本域的首选大小
        jsp.setBounds(110, 90, size.width, size.height);

        jp.add(jsp);
        frame.add(jp);
        frame.setBackground(Color.LIGHT_GRAY);
        frame.setSize(500, 200);
        frame.setVisible(true);
    }
}
