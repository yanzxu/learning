package com.swing.box;

import javax.swing.*;
import java.awt.*;

public class JCheckBoxDemo {
    public static void main(String[] agrs) {
        JFrame frame = new JFrame("Java复选组件示例");
        JPanel jp = new JPanel();

        JLabel label = new JLabel("流行编程语言有：");
        label.setFont(new Font("楷体", Font.BOLD, 16));

        JCheckBox chkbox1 = new JCheckBox("C#", true);
        JCheckBox chkbox2 = new JCheckBox("C++");
        JCheckBox chkbox3 = new JCheckBox("Java");
        JCheckBox chkbox4 = new JCheckBox("Python");
        JCheckBox chkbox5 = new JCheckBox("PHP");
        JCheckBox chkbox6 = new JCheckBox("Perl");
        jp.add(label);
        jp.add(chkbox1);
        jp.add(chkbox2);
        jp.add(chkbox3);
        jp.add(chkbox4);
        jp.add(chkbox5);
        jp.add(chkbox6);
        frame.add(jp);
        frame.setBounds(300, 200, 400, 100);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
