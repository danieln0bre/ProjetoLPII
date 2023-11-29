package br.ufrn.imd.gui;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI implements ActionListener {
    public LoginGUI(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setTitle("MediaPlayerApp");
        frame.setSize(350,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLayout(null);

        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10,20,80,25);
        frame.add(userLabel);

        JTextField userText = new JTextField();
        userText.setBounds(100,20,165,25);
        frame.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,50,80,25);
        frame.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(100,50,165,25);
        frame.add(passwordText);

        JButton button = new JButton("Login");
        button.setBounds(10,80,80,25);
        //button.addActionListener(new LoginGUI());
        frame.add(button);

        JLabel sucess = new JLabel("");
        sucess.setBounds(10,110,300,25);
        frame.add(sucess);
        //sucess.setText();

        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e){

    }
}
