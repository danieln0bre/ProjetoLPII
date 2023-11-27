package br.ufrn.imd.gui;
import javax.swing.*;
public class LoginGUI extends JFrame {
    public LoginGUI(){
        setTitle("MediaPlayerApp");
        setVisible(true);
        setSize(800,500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton loginButton = new JButton("login");
        JButton registerButton = new JButton("register");
        JTextField loginField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        //loginButtonConfig
        loginButton.setBounds(300,300,100,20);

        //registerButtonConfig
        registerButton.setBounds(400,300,100,20);

        //loginFieldConfig
        loginField.setBounds(300,350,400,300);
        //passwordFieldConfig

        add(loginButton);
        add(registerButton);
        add(loginField);
    }
}
