package com.swing.listener;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class FocusListenerDemo extends JFrame {
    public FocusListenerDemo() {
        setTitle("焦点事件监听器示例");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 200);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JLabel label = new JLabel(" ");
        label.setFont(new Font("楷体", Font.BOLD, 16));
        contentPane.add(label, BorderLayout.SOUTH);

        JTextArea textArea = new JTextArea();
        textArea.setFont(new Font("黑体", Font.BOLD, 16));

        textArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                label.setText("mouseClicked");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                label.setText("mousePressed");
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                label.setText("mouseReleased");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                label.setText("mouseEntered");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                label.setText("mouseExited");
            }
        });

        contentPane.add(textArea);
    }

    public static void main(String[] args) {
        FocusListenerDemo frame = new FocusListenerDemo();
        frame.setVisible(true);
    }
}
