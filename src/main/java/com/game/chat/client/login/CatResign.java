package com.game.chat.client.login;

import com.game.chat.client.util.CatUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class CatResign extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;
    private JPasswordField passwordField_1;
    private JLabel lblNewLabel;

    public CatResign() {
        setTitle("Registered cat chat room\n");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(350, 250, 450, 300);
        contentPane = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(new ImageIcon("images\\\u6CE8\u518C\u754C\u9762.jpg").getImage(), 0, 0, getWidth(), getHeight(), null);
            }
        };
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setBounds(150, 42, 104, 21);
        textField.setOpaque(false);
        contentPane.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setEchoChar('*');
        passwordField.setOpaque(false);
        passwordField.setBounds(190, 98, 104, 21);
        contentPane.add(passwordField);

        passwordField_1 = new JPasswordField();
        passwordField_1.setBounds(192, 152, 104, 21);
        passwordField_1.setOpaque(false);
        contentPane.add(passwordField_1);

        final JButton btnNewButton_1 = new JButton();
        btnNewButton_1.setIcon(new ImageIcon("images\\ע��1.jpg"));
        btnNewButton_1.setBounds(320, 198, 80, 40);
        getRootPane().setDefaultButton(btnNewButton_1);
        contentPane.add(btnNewButton_1);

        final JButton btnNewButton = new JButton("");
        btnNewButton.setIcon(new ImageIcon("images\\����.jpg"));
        btnNewButton.setBounds(230, 198, 80, 40);
        contentPane.add(btnNewButton);

        lblNewLabel = new JLabel();
        lblNewLabel.setBounds(55, 218, 185, 20);
        lblNewLabel.setForeground(Color.red);
        contentPane.add(lblNewLabel);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnNewButton.setEnabled(false);
                CatLogin frame = new CatLogin();
                frame.setVisible(true);
                setVisible(false);
            }
        });

        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Properties userPro = new Properties();
                File file = new File("Users.properties");
                CatUtil.loadPro(userPro, file);

                String u_name = textField.getText();
                String u_pwd = new String(passwordField.getPassword());
                String u_pwd_ag = new String(passwordField_1.getPassword());

                if (u_name.length() != 0) {

                    if (userPro.containsKey(u_name)) {
                        lblNewLabel.setText("�û����Ѵ���!");
                    } else {
                        isPassword(userPro, file, u_name, u_pwd, u_pwd_ag);
                    }
                } else {
                    lblNewLabel.setText("�û�������Ϊ�գ�");
                }
            }

            private void isPassword(Properties userPro,
                                    File file, String u_name, String u_pwd, String u_pwd_ag) {
                if (u_pwd.equals(u_pwd_ag)) {
                    if (u_pwd.length() != 0) {
                        userPro.setProperty(u_name, u_pwd_ag);
                        try {
                            userPro.store(new FileOutputStream(file),
                                    "Copyright (c) Boxcode Studio");
                        } catch (FileNotFoundException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                        btnNewButton_1.setEnabled(false);
                        CatLogin frame = new CatLogin();
                        frame.setVisible(true);
                        setVisible(false);
                    } else {
                        lblNewLabel.setText("����Ϊ�գ�");
                    }
                } else {
                    lblNewLabel.setText("���벻һ�£�");
                }
            }
        });
    }
}
