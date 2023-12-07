package br.ufrn.imd.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import br.ufrn.imd.authentication.*;
import br.ufrn.imd.gui.*;

public class MainGUI extends JFrame {
    private static User user;

    public MainGUI(User user) {
        this.user = user;

        setTitle("Main Screen");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JButton playerButton = new JButton("Player");
        playerButton.setBounds(80, 50, 100, 25);
        playerButton.addActionListener(this::openPlayerGUI);
        add(playerButton);

        JButton playlistButton = new JButton("Playlist");
        playlistButton.setBounds(220, 50, 100, 25);
        playlistButton.addActionListener(this::openPlaylistGUI);
        add(playlistButton);

        setVisible(true);
    }

    private void openPlayerGUI(ActionEvent e) {
        // Abre a tela de Player passando o objeto do usuário
        PlayerGUI playerGUI = new PlayerGUI(user);
        this.dispose(); // Fecha a janela atual
    }

    private void openPlaylistGUI(ActionEvent e) {
        // Verifica se o usuário é VIP antes de abrir a PlaylistGUI
        if (user.getUserType()=="VipUser") {
            // Cria uma lista de reprodução fictícia para enviar para a PlaylistGUI

            // Abre a tela de Playlist passando o modelo da lista de reprodução e o objeto do usuário
            PlaylistGUI playlistGUI = new PlaylistGUI(user);
            this.dispose(); // Fecha a janela atual
        } else {
            JOptionPane.showMessageDialog(this, "Apenas usuários VIP podem acessar a Playlist.", "Acesso Restrito", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        // Exemplo de uso
        SwingUtilities.invokeLater(() -> {
            // Supondo que você tenha um objeto de usuário (substitua isso com seu próprio código de obtenção de usuário)
            User loggedUser = new CommonUser(user.getUsername(),user.getEmail(), user.getPassword());
            new MainGUI(loggedUser);
        });
    }
}
