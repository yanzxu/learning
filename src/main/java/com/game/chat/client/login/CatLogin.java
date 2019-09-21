package com.game.chat.client.login;

import com.game.chat.client.client.CatChatroom;
import com.game.chat.client.function.ClientBean;
import com.game.chat.client.util.CatUtil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Properties;

public class CatLogin extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JPasswordField passwordField;
    public static HashMap<String, ClientBean> onlines;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CatLogin frame = new CatLogin();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public CatLogin() {
        setTitle("Landing cat chat room\n");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(350, 250, 450, 300);
        contentPane = new JPanel() {
            private static final long serialVersionUID = 1L;

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(new ImageIcon(
                                "images\\\u767B\u9646\u754C\u9762.jpg").getImage(), 0,
                        0, getWidth(), getHeight(), null);
            }
        };
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        textField = new JTextField();
        textField.setBounds(128, 153, 104, 21);
        textField.setOpaque(false);
        contentPane.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setForeground(Color.BLACK);
        passwordField.setEchoChar('*');
        passwordField.setOpaque(false);
        passwordField.setBounds(128, 189, 104, 21);
        contentPane.add(passwordField);

        final JButton btnNewButton = new JButton();
        btnNewButton.setIcon(new ImageIcon("images\\\u767B\u9646.jpg"));
        btnNewButton.setBounds(246, 227, 50, 25);
        getRootPane().setDefaultButton(btnNewButton);
        contentPane.add(btnNewButton);

        final JButton btnNewButton_1 = new JButton();
        btnNewButton_1.setIcon(new ImageIcon("images\\\u6CE8\u518C.jpg"));
        btnNewButton_1.setBounds(317, 227, 50, 25);
        contentPane.add(btnNewButton_1);

        final JLabel lblNewLabel = new JLabel();
        lblNewLabel.setBounds(60, 220, 151, 21);
        lblNewLabel.setForeground(Color.red);
        getContentPane().add(lblNewLabel);

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Properties userPro = new Properties();
                File file = new File("Users.properties");
                CatUtil.loadPro(userPro, file);
                String u_name = textField.getText();
                if (file.length() != 0) {

                    if (userPro.containsKey(u_name)) {
                        String u_pwd = new String(passwordField.getPassword());
                        if (u_pwd.equals(userPro.getProperty(u_name))) {

                            try {
                                Socket client = new Socket("localhost", 8520);

                                btnNewButton.setEnabled(false);
                                CatChatroom frame = new CatChatroom(u_name,
                                        client);
                                frame.setVisible(true);
                                setVisible(false);

                            } catch (UnknownHostException e1) {
                                // TODO Auto-generated catch block
                                errorTip("The connection with the server is interrupted, please login again");
                            } catch (IOException e1) {
                                // TODO Auto-generated catch block
                                errorTip("The connection with the server is interrupted, please login again");
                            }

                        } else {
                            lblNewLabel.setText("���������������");
                            textField.setText("");
                            passwordField.setText("");
                            textField.requestFocus();
                        }
                    } else {
                        lblNewLabel.setText("�������ǳƲ����ڣ�");
                        textField.setText("");
                        passwordField.setText("");
                        textField.requestFocus();
                    }
                } else {
                    lblNewLabel.setText("�������ǳƲ����ڣ�");
                    textField.setText("");
                    passwordField.setText("");
                    textField.requestFocus();
                }
            }
        });

        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnNewButton_1.setEnabled(false);
                CatResign frame = new CatResign();
                frame.setVisible(true);
                setVisible(false);
            }
        });
    }

    protected void errorTip(String str) {
        // TODO Auto-generated method stub
        JOptionPane.showMessageDialog(contentPane, str, "Error Message",
                JOptionPane.ERROR_MESSAGE);
        textField.setText("");
        passwordField.setText("");
        textField.requestFocus();
    }
}