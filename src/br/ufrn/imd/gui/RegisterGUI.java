package br.ufrn.imd.gui;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import br.ufrn.imd.authentication.*;

public class RegisterGUI implements ActionListener {
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

    public RegisterGUI(){
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setTitle("MediaPlayerApp");
        frame.setSize(350,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.setLayout(null);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setBounds(10,20,80,25);
        frame.add(emailLabel);

        JTextField emailText = new JTextField();
        emailText.setBounds(100,20,200,25);
        frame.add(emailText);

        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10,50,80,25);
        frame.add(userLabel);

        userText = new JTextField();
        userText.setBounds(100,50,200,25);
        userText.setText("username");
        frame.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10,80,80,25);
        frame.add(passwordLabel);

        JPasswordField passwordText = new JPasswordField();
        passwordText.setBounds(100,80,200,25);
        frame.add(passwordText);

        JButton button = new JButton("Register");
        button.setBounds(100,110,130,25);
        button.addActionListener(this);
        frame.add(button);

        JToggleButton userType = new JToggleButton("VIP");
        userType.setBounds(240, 110, 60,25);
        ItemListener typeListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                int state = itemEvent.getStateChange();
                if(state == ItemEvent.SELECTED){
                    setUserType("vip");
                }else{
                    setUserType("Common");
                }
            }
        };
        userType.addItemListener(typeListener);
        frame.add(userType);

        JLabel sucess = new JLabel("");
        sucess.setBounds(10,110,300,25);
        frame.add(sucess);
        //sucess.setText("-");

        frame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e){
        UserManager manager = new UserManager();
        manager.loadUsers();
        manager.registerUser(getUserType(), userText.getText(), emailText.getText(), passwordText.getText());

        System.out.println("Registrando...");
    }
}
