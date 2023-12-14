package br.ufrn.imd.gui;

import br.ufrn.imd.authentication.CommonUser;
import br.ufrn.imd.authentication.User;
import br.ufrn.imd.exceptions.PlaylistException;
import br.ufrn.imd.filehandling.PlaylistFileHandler;
import br.ufrn.imd.logic.AudioPlayer;
import br.ufrn.imd.logic.PlaylistManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class PlaylistGUI extends JPanel implements ActionListener {
    private User user;
    private PlaylistManager playlistManager;
    private AudioPlayer audioPlayer;
    private DefaultListModel<String> playlistListModel;
    private JList<String> playlistList;

    public PlaylistGUI(User user) {
        this.user = user;
        this.playlistManager = new PlaylistManager();
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new BorderLayout());

        JButton playPlaylistButton = new JButton("Play Playlist");
        playPlaylistButton.addActionListener(this::playPlaylist);
        add(playPlaylistButton, BorderLayout.LINE_END);

        JButton loadPlaylistsButton = new JButton("Carregar Playlists");
        loadPlaylistsButton.addActionListener(this::loadPlaylists);
        add(loadPlaylistsButton, BorderLayout.NORTH);

        playlistListModel = new DefaultListModel<>();
        playlistList = new JList<>(playlistListModel);
        JScrollPane scrollPane = new JScrollPane(playlistList);
        add(scrollPane, BorderLayout.CENTER);

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
    }

    private void loadPlaylists(ActionEvent e) {
        ArrayList<String> userPlaylists = playlistManager.loadUserPlaylists(user);

        if (userPlaylists != null) {
            playlistListModel.clear();

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
            loadPlaylists(null);
        }
    }

    private void addSongToPlaylist(ActionEvent e) {
        String selectedPlaylist = playlistList.getSelectedValue();

        if (selectedPlaylist != null) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Escolha uma música");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

            int result = fileChooser.showOpenDialog(this);

            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();

                if (selectedFile != null) {
                    String filePath = selectedFile.getAbsolutePath();

                    // Adiciona o endereço do arquivo ao PlaylistFileHandler
                    try {
                        PlaylistFileHandler playlistFileHandler = new PlaylistFileHandler("./files/"+user.getUsername()+"/playlists/"+selectedPlaylist);
                        ArrayList<String> playlistData = playlistFileHandler.readData();
                        playlistData.add(filePath);
                        playlistFileHandler.writeData(playlistData);
                        loadPlaylists(null);
                    } catch (PlaylistException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(this, "Erro ao adicionar música à playlist.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma playlist para adicionar uma música.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void removeSongFromPlaylist(ActionEvent e) {
        String selectedPlaylist = playlistList.getSelectedValue();
        System.out.println(selectedPlaylist);
        if (selectedPlaylist != null) {
            String songToRemove = JOptionPane.showInputDialog(this, "Digite o nome da música a ser removida:");

            if (songToRemove != null && !songToRemove.isEmpty()) {
                playlistManager.removeSongFromPlaylist(user, selectedPlaylist, songToRemove);
                loadPlaylists(null);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma playlist para remover uma música.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletePlaylist(ActionEvent e) {
        String selectedPlaylist = playlistList.getSelectedValue();
        System.out.println(selectedPlaylist);
        if (selectedPlaylist != null) {
            int option = JOptionPane.showConfirmDialog(this, "Tem certeza de que deseja excluir a playlist selecionada?",
                    "Confirmação", JOptionPane.YES_NO_OPTION);

            if (option == JOptionPane.YES_OPTION) {
                playlistManager.deletePlaylist(user, selectedPlaylist);
                loadPlaylists(null);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma playlist para excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void playPlaylist(ActionEvent e) {
    	String selectedPlaylist = playlistList.getSelectedValue();
        this.audioPlayer = new AudioPlayer("./files/"+user.getUsername()+"/playlists/"+selectedPlaylist, true);
        Thread playThread = new Thread(() -> {
            audioPlayer.playPlaylist();
        });
        playThread.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Implemente a lógica para lidar com outros eventos, se necessário
    }

    public static void main(String[] args) {
        User loggedUser = new CommonUser("username", "email", "password");
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new PlaylistGUI(loggedUser));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
