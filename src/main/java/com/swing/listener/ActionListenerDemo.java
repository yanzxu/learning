package com.swing.listener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionListenerDemo extends JFrame {
    JLabel label;
    JButton button1 = new JButton("我是个按钮");
    int clicks = 1;

    public ActionListenerDemo() {
        setTitle("动作事件监听器示例");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 200);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        label = new JLabel(" ");
        label.setFont(new Font("楷体", Font.BOLD, 16));
        contentPane.add(label, BorderLayout.SOUTH);

//        button1.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                label.setText("按钮被点击了 " + (clicks++) + "次");
//            }
//        });

        button1.addActionListener(new ButtonActionListener());

        contentPane.add(button1);
    }

    public static void main(String[] args) {
        ActionListenerDemo frame = new ActionListenerDemo();
        frame.setVisible(true);
    }

    public class ButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            label.setText("按钮被单击了 " + (clicks++) + " 次");
        }
    }


}
