package com.swing.layout;

import javax.swing.*;
import java.awt.*;

public class CardLayoutDemo {
    public static void main(String[] agrs) {
        JFrame frame = new JFrame("Java第五个程序");
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel cards = new JPanel(new CardLayout());
        p1.add(new JButton("登录按钮"));
        p1.add(new JButton("注册按钮"));
        p1.add(new JButton("找回密码按钮"));

        p2.add(new JTextField("用户名文本框", 20));
        p2.add(new JTextField("密码文本框", 20));
        p2.add(new JTextField("验证码文本框", 20));

        cards.add(p1, "card1");
        cards.add(p2, "card2");

        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, "card1");

        frame.add(cards);
        frame.setBounds(300, 200, 400, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
