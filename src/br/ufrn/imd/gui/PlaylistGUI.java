package br.ufrn.imd.gui;

import br.ufrn.imd.authentication.CommonUser;
import br.ufrn.imd.authentication.User;
import br.ufrn.imd.logic.PlaylistManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class PlaylistGUI extends JFrame implements ActionListener {
    private User user;
    private PlaylistManager playlistManager;
    private DefaultListModel<String> playlistListModel;
    private JList<String> playlistList;

    public PlaylistGUI(User user) {
        this.user = user;
        this.playlistManager = new PlaylistManager();
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Playlist Screen");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Botão para carregar playlists
        JButton loadPlaylistsButton = new JButton("Carregar Playlists");
        loadPlaylistsButton.addActionListener(this::loadPlaylists);
        add(loadPlaylistsButton, BorderLayout.NORTH);

        // Lista de playlists
        playlistListModel = new DefaultListModel<>();
        playlistList = new JList<>(playlistListModel);
        JScrollPane scrollPane = new JScrollPane(playlistList);
        add(scrollPane, BorderLayout.CENTER);

        // Botões para interações com a playlist
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));

        JButton createPlaylistButton = new JButton("Criar Playlist");
        createPlaylistButton.addActionListener(this::createPlaylist);
        buttonPanel.add(createPlaylistButton);

        JButton addSongButton = new JButton("Adicionar Música");
        addSongButton.addActionListener(this::addSongToPlaylist);
        buttonPanel.add(addSongButton);

        JButton removeSongButton = new JButton("Remover Música");
        removeSongButton.addActionListener(this::removeSongFromPlaylist);
        buttonPanel.add(removeSongButton);

        JButton deletePlaylistButton = new JButton("Excluir Playlist");
        deletePlaylistButton.addActionListener(this::deletePlaylist);
        buttonPanel.add(deletePlaylistButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Botão para voltar para MainGUI
        JButton backButton = new JButton("Voltar para MainGUI");
        backButton.addActionListener(this::backToMainGUI);
        add(backButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void loadPlaylists(ActionEvent e) {
        ArrayList<String> userPlaylists = playlistManager.loadUserPlaylists(user);

        if (userPlaylists != null) {
            // Limpa a lista antes de adicionar as novas playlists
            playlistListModel.clear();

            // Adiciona as playlists à lista
            for (String playlist : userPlaylists) {
                playlistListModel.addElement(playlist);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Erro ao carregar playlists.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void createPlaylist(ActionEvent e) {
        String playlistName = JOptionPane.showInputDialog(this, "Digite o nome da nova playlist:");

        if (playlistName != null && !playlistName.isEmpty()) {
            playlistManager.createPlaylist(user, playlistName);
            loadPlaylists(null); // Atualiza a lista após criar uma nova playlist
        }
    }

    private void addSongToPlaylist(ActionEvent e) {
        String selectedPlaylist = playlistList.getSelectedValue();

        if (selectedPlaylist != null) {
            String newSong = JOptionPane.showInputDialog(this, "Digite o nome da nova música:");

            if (newSong != null && !newSong.isEmpty()) {
                playlistManager.addSongToPlaylist(user, selectedPlaylist, newSong);
                loadPlaylists(null); // Atualiza a lista após adicionar uma nova música
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma playlist para adicionar uma música.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeSongFromPlaylist(ActionEvent e) {
        String selectedPlaylist = playlistList.getSelectedValue();

        if (selectedPlaylist != null) {
            String songToRemove = JOptionPane.showInputDialog(this, "Digite o nome da música a ser removida:");

            if (songToRemove != null && !songToRemove.isEmpty()) {
                playlistManager.removeSongFromPlaylist(user, selectedPlaylist, songToRemove);
                loadPlaylists(null); // Atualiza a lista após remover uma música
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma playlist para remover uma música.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletePlaylist(ActionEvent e) {
        String selectedPlaylist = playlistList.getSelectedValue();

        if (selectedPlaylist != null) {
            int option = JOptionPane.showConfirmDialog(this, "Tem certeza de que deseja excluir a playlist selecionada?",
                    "Confirmação", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                playlistManager.deletePlaylist(user, selectedPlaylist);
                loadPlaylists(null); // Atualiza a lista após excluir uma playlist
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma playlist para excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void backToMainGUI(ActionEvent e) {
        MainGUI mainGUI = new MainGUI(user);
        this.dispose(); // Fecha a janela atual
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Implemente a lógica para lidar com outros eventos, se necessário
    }

    public static void main(String[] args) {
        User loggedUser = new CommonUser("username", "email", "password");
        SwingUtilities.invokeLater(() -> new PlaylistGUI(loggedUser));
    }
}
