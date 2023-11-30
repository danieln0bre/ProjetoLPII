package br.ufrn.imd.gui;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import br.ufrn.imd.authentication.*;
import br.ufrn.imd.gui.*;

public class RegisterGUI extends JFrame implements ActionListener {
    private String userType = "common";
    JTextField emailText;
    JTextField userText;
    JTextField passwordText;
    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserType() {
        return userType;
    }

    public RegisterGUI() {

        JPanel panel = new JPanel();
        setTitle("Register screen");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(panel);
        setLayout(null);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(10, 20, 80, 25);
        add(emailLabel);

        emailText = new JTextField();
        emailText.setBounds(100, 20, 200, 25);
        add(emailText);

        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 50, 80, 25);
        add(userLabel);

        userText = new JTextField();
        userText.setBounds(100, 50, 200, 25);
        add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 80, 80, 25);
        add(passwordLabel);

        passwordText = new JPasswordField();
        passwordText.setBounds(100, 80, 200, 25);
        add(passwordText);

        JButton button = new JButton("Register");
        button.setBounds(100, 110, 130, 25);
        button.addActionListener(this::actionPerformed);
        add(button);

        JToggleButton userType = new JToggleButton("VIP");
        userType.setBounds(240, 110, 60, 25);
        ItemListener typeListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                int state = itemEvent.getStateChange();
                if (state == ItemEvent.SELECTED) {
                    setUserType("vip");
                } else {
                    setUserType("Common");
                }
            }
        };
        userType.addItemListener(typeListener);
        add(userType);

        JButton goToLogin = new JButton("<");
        goToLogin.setBounds(40, 110, 50, 25);
        goToLogin.addActionListener(this::retornar);
        add(goToLogin);

        //JLabel sucess = new JLabel(" ");
        //sucess.setBounds(10, 110, 300, 25);
        //add(sucess);
        //sucess.setText("-");

        setVisible(true);
    }


    public void retornar(ActionEvent e) {
        LoginGUI loginGUI = new LoginGUI();
        this.dispose();
        System.out.println("Voltando Para a tela de login...");
    }
    @Override
    public void actionPerformed(ActionEvent e){
        UserManager manager = new UserManager();
        manager.registerUser(getUserType(), userText.getText(), emailText.getText(), passwordText.getText());

        System.out.println("Registrando...");
    }
}
