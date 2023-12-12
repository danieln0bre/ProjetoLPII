package br.ufrn.imd.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import br.ufrn.imd.authentication.*;
import br.ufrn.imd.exceptions.AuthenticationException;
import br.ufrn.imd.gui.*;

public class LoginGUI extends JFrame implements ActionListener {
    JTextField userText;
    JTextField passwordText;
    private User user; // Adicionando o campo User

    public LoginGUI() {
        JPanel panel = new JPanel();
        setTitle("Login screen");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panel);
        setLayout(null);

        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 20, 80, 25);
        add(userLabel);

        userText = new JTextField();
        userText.setBounds(100, 20, 165, 25);
        add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(100, 50, 165, 25);
        add(passwordText);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(80, 80, 100, 25);
        loginButton.addActionListener(this::actionPerformed);
        add(loginButton);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(190, 80, 100, 25);
        registerButton.addActionListener(this::registrar);
        add(registerButton);

        setVisible(true);
    }

    public void registrar(ActionEvent e) {
        RegisterGUI registerGUI = new RegisterGUI();
        this.dispose();
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        UserManager manager = UserManager.getInstance(); // Obtenha a instância compartilhada
        try {
            User loggedUser = manager.loginUser(userText.getText(), passwordText.getText());
            manager.setLoggedUser(loggedUser); // Atualize o usuário logado no UserManager

            // Crie uma instância da MainGUI e passe o objeto do usuário logado
            MainGUI mainGUI = new MainGUI();
            this.dispose(); // Feche a janela de login
        } catch (AuthenticationException ex) {
            JOptionPane.showMessageDialog(this, "Login failed. Check the username and password.", "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginGUI());
    }
}
