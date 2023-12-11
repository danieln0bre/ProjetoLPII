package br.ufrn.imd.gui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import br.ufrn.imd.authentication.*;
import br.ufrn.imd.logic.*;

public class PlayerGUI extends JPanel implements ActionListener {
    private static User user;
    private JList<String> listDirectories;
    private DefaultListModel<String> listModel;
    private JList<String> listDirectoryFiles;
    private DefaultListModel<String> filesModel;

    public PlayerGUI(User user) {
        this.user = user;
        initializeUI();
    }

    private void initializeUI() {
        setLayout(null);

        this.listModel = new DefaultListModel<String>();

        this.listDirectories = new JList<>(listModel);
        listDirectories.setFont(new Font("Arial", Font.BOLD, 18));
        listDirectories.setVisibleRowCount(3);

        JScrollPane listDirectoriesScroll = new JScrollPane(listDirectories);
        listDirectoriesScroll.setBounds(150, 125, 300, 500);
        add(listDirectoriesScroll);

        this.filesModel = new DefaultListModel<String>();

        this.listDirectoryFiles = new JList<>(filesModel);
        listDirectoryFiles.setFont(new Font("Arial", Font.BOLD, 18));
        listDirectoryFiles.setVisibleRowCount(3);
        listDirectories.addListSelectionListener(this::valueChanged);

        JScrollPane listDirectorySongs = new JScrollPane(listDirectoryFiles);
        listDirectorySongs.setBounds(500, 125, 300, 500);
        add(listDirectorySongs);

        JLabel directoriesLabel = new JLabel("Directories");
        directoriesLabel.setBounds(230, 100, 140, 30);
        directoriesLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(directoriesLabel);

        JLabel songsLabel = new JLabel("Songs");
        songsLabel.setBounds(600, 100, 100, 30);
        songsLabel.setFont(new Font("Arial", Font.BOLD, 20));
        add(songsLabel);

        JButton addDirectoryButton = new JButton("+");
        addDirectoryButton.setBounds(100, 100, 50, 50);
        addDirectoryButton.addActionListener(this::addDirectory);
        add(addDirectoryButton);


        JButton playButton = new JButton("Tocar Música");
        playButton.setBounds(410, 650, 140, 30);
        playButton.addActionListener(this::playMusic);
        add(playButton);
    }

    private void addDirectory(ActionEvent e) {
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int escolha = chooser.showOpenDialog(null);

        if (escolha == JFileChooser.APPROVE_OPTION) {
            // O usuário selecionou um diretório
            String directoryPath = chooser.getSelectedFile().getAbsolutePath();
            //TODO: Visualizar o nome do diretório e não o AbsolutePath
            //String directoryName = chooser.getSelectedFile().getName();
            System.out.println("Diretório selecionado: " + directoryPath);
            listModel.addElement(directoryPath);
        } else {
            System.out.println("Operação cancelada pelo usuário");
        }
    }
    private void playMusic(ActionEvent e) {
        // Obtenha o diretório selecionado na lista de diretórios
        String selectedDirectory = listDirectories.getSelectedValue();

        // Obtenha o nome do arquivo selecionado na lista de arquivos
        String selectedFile = listDirectoryFiles.getSelectedValue();

        // Verifique se ambos são diferentes de null
        if (selectedDirectory != null && selectedFile != null) {
            // Concatene o caminho absoluto do diretório com o nome do arquivo
            String filePath = selectedDirectory + File.separator + selectedFile;
            System.out.println("Tocando Música:" + selectedFile);
            System.out.println("SongPath: " + filePath);
            // Crie e inicie o reprodutor de áudio com o caminho absoluto
            AudioPlayer audioPlayer = new AudioPlayer(filePath);
            audioPlayer.play();
        } else {
            System.out.println("Nenhum diretório ou arquivo selecionado.");
        }
    }


    private void valueChanged(ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            int selectedIndex = listDirectories.getSelectedIndex();
            if (selectedIndex != -1) {
                String selectedDirectory = listModel.getElementAt(selectedIndex);
                updateFilesList(selectedDirectory);
            }
        }
    }
    private void updateFilesList(String directoryPath) {
        filesModel.clear();
        File directory = new File(directoryPath);
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                filesModel.addElement(file.getName());
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Implemente a lógica para lidar com outros eventos, se necessário
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Supondo que você tenha um objeto de usuário (substitua isso com seu próprio código de obtenção de usuário)
            User loggedUser = new CommonUser(user.getUsername(), user.getEmail(), user.getPassword());
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new PlayerGUI(loggedUser));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
