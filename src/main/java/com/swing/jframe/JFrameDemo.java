package com.swing.jframe;

import javax.swing.*;
import java.awt.*;

public class JFrameDemo extends JFrame {
    public JFrameDemo(){
        setTitle("Java GUI 001");
        setSize(400,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel jLabel = new JLabel(" This is a window created by JFrame.");
        getContentPane().add(jLabel);

        setVisible(true);
    }

    public static void main(String[] args){
//        new JFrameDemo();

        JFrame jFrame = new JFrame(" Java GUI 002");
        jFrame.setBounds(300,100,400,200);
        jFrame.setBackground(Color.WHITE);
        jFrame.add(new JPanel().add(new JLabel(" This is a tag on the panel")));
        jFrame.setVisible(true);


    }
}
