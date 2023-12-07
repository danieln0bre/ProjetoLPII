package br.ufrn.imd.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import br.ufrn.imd.authentication.*;

public class PlaylistGUI extends JFrame implements ActionListener {
    private User user;

    public PlaylistGUI(User user) {
        this.user = user;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Playlist Screen");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton backButton = new JButton("Voltar para MainGUI");
        backButton.setBounds(120, 250, 160, 25);
        backButton.addActionListener(this::backToMainGUI);
        add(backButton);

        setVisible(true);
    }

    private void backToMainGUI(ActionEvent e) {
        MainGUI mainGUI = new MainGUI(user);
        this.dispose(); // Fecha a janela atual
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Implemente a lógica para lidar com outros eventos, se necessário
    }
}
