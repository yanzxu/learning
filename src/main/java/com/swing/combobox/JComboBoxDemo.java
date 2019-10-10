package com.swing.combobox;

import javax.swing.*;

public class JComboBoxDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Java下拉列表组件示例");
        JPanel jp = new JPanel();
        JLabel label1 = new JLabel("证件类型：");

        JComboBox cmb = new JComboBox();
        cmb.addItem("--请选择--");
        cmb.addItem("身份证");
        cmb.addItem("驾驶证");
        cmb.addItem("军官证");

        jp.add(label1);
        jp.add(cmb);

        frame.add(jp);
        frame.setBounds(300, 200, 400, 100);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
