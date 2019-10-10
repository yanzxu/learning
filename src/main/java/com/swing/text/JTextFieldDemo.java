package com.swing.text;

import javax.swing.*;
import java.awt.*;

public class JTextFieldDemo {
    public static void main(String[] agrs) {
        JFrame frame = new JFrame("Java文本框组件示例");
        JPanel jp = new JPanel();

        JTextField txtfield1 = new JTextField();
        txtfield1.setText("普通文本框");

        JTextField txtfield2 = new JTextField(28);
        txtfield2.setFont(new Font("楷体", Font.BOLD, 16));
        txtfield2.setText("指定长度和字体的文本框");

        JTextField txtfield3 = new JTextField(30);
        txtfield3.setText("居中对齐");
        txtfield3.setHorizontalAlignment(JTextField.CENTER);

        jp.add(txtfield1);
        jp.add(txtfield2);
        jp.add(txtfield3);

        frame.add(jp);
        frame.setBounds(300, 200, 500, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
