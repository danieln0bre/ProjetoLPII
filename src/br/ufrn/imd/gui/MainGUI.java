package br.ufrn.imd.gui;

import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import br.ufrn.imd.authentication.*;

public class MainGUI extends JFrame {
    private static User user;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public MainGUI(User user) {
        this.user = user;

        setTitle("Main Screen");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        // Adicione PlayerGUI ao CardLayout
        PlayerGUI playerGUI = new PlayerGUI(user);
        cardPanel.add(playerGUI, "Player");

        // Adicione PlaylistGUI ao CardLayout
        PlaylistGUI playlistGUI = new PlaylistGUI(user);
        cardPanel.add(playlistGUI, "Playlist");

        // Adicione o CardLayout ao contentPane
        getContentPane().add(cardPanel);

        // Adicione botões para alternar entre as "cartas"
        JButton playerButton = new JButton("Player");
        playerButton.addActionListener(e -> cardLayout.show(cardPanel, "Player"));

        JButton playlistButton = new JButton("Playlist");
        playlistButton.addActionListener(e -> cardLayout.show(cardPanel, "Playlist"));

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(playerButton);
        buttonPanel.add(playlistButton);

        getContentPane().add(buttonPanel, BorderLayout.NORTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            User loggedUser = new CommonUser(user.getUsername(), user.getEmail(), user.getPassword());
            new MainGUI(loggedUser);
        });
    }
}
