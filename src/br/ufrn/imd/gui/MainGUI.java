package br.ufrn.imd.gui;

import javax.swing.*;

import br.ufrn.imd.authentication.User;
import br.ufrn.imd.authentication.UserManager;
import br.ufrn.imd.authentication.VipUser;

import java.awt.*;
import java.awt.event.ActionEvent;

public class MainGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private UserManager userManager;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public MainGUI() {
        userManager = UserManager.getInstance();

        setTitle("Main Screen");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        cardPanel = new JPanel();
        cardLayout = new CardLayout();
        cardPanel.setLayout(cardLayout);

        JButton playerButton = new JButton("Player");
        playerButton.addActionListener(this::showPlayer);

        JButton playlistButton = new JButton("Playlist");
        playlistButton.addActionListener(this::showPlaylist);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(playerButton);

        // Adiciona o botão de playlist apenas se o usuário logado for um VipUser
        if (userManager.getLoggedUser() instanceof VipUser) {
            buttonPanel.add(playlistButton);
        }

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(cardPanel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.NORTH);

        // Mostra o PlayerGUI por padrão ao inicializar
        showPlayer(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, "default"));

        setVisible(true);
    }

    private void showPlayer(ActionEvent e) {
        PlayerGUI playerGUI = new PlayerGUI(userManager.getLoggedUser());
        cardPanel.add(playerGUI, "Player");
        cardLayout.show(cardPanel, "Player");
    }

    private void showPlaylist(ActionEvent e) {
        // Obtém o usuário atualmente logado
        User loggedUser = userManager.getLoggedUser();

        // Verifica se o usuário logado é um VipUser
        if (loggedUser instanceof VipUser) {
            // Se for um VipUser, mostra a PlaylistGUI
            PlaylistGUI playlistGUI = new PlaylistGUI(loggedUser);
            cardPanel.add(playlistGUI, "Playlist");
            cardLayout.show(cardPanel, "Playlist");
        } else {
            // Se não for um VipUser, exibe uma mensagem ou lida de acordo com os requisitos
            JOptionPane.showMessageDialog(this, "Acesso restrito. Somente usuários VIP podem acessar a Playlist.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainGUI::new);
    }
}
