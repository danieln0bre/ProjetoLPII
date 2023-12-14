package br.ufrn.imd.gui;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import br.ufrn.imd.authentication.CommonUser;
import br.ufrn.imd.authentication.User;
import br.ufrn.imd.authentication.UserManager;
import br.ufrn.imd.authentication.VipUser;
import br.ufrn.imd.logic.AudioPlayer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class PlayerGUI extends JPanel {
    private static final long serialVersionUID = 1L;
    private UserManager userManager;
    private JList<String> listDirectories;
    private DefaultListModel<String> listModel;
    private JList<String> listDirectoryFiles;
    private DefaultListModel<String> filesModel;

    public PlayerGUI(User user) {
        userManager = UserManager.getInstance();
        initializeUI();
        updateDirectoriesList(); 
    }

    private void initializeUI() {
        setLayout(new FlowLayout());

        this.listModel = new DefaultListModel<>();
        this.listDirectories = new JList<>(listModel);
        JScrollPane listDirectoriesScroll = new JScrollPane(listDirectories);
        listDirectoriesScroll.setPreferredSize(new Dimension(300, 500));
        add(listDirectoriesScroll);

        this.filesModel = new DefaultListModel<>();
        this.listDirectoryFiles = new JList<>(filesModel);
        JScrollPane listDirectorySongs = new JScrollPane(listDirectoryFiles);
        listDirectorySongs.setPreferredSize(new Dimension(300, 500));
        add(listDirectorySongs);

        JButton addDirectoryButton = new JButton("+");
        addDirectoryButton.addActionListener(this::addDirectory);
        add(addDirectoryButton);

        JButton removeDirectoryButton = new JButton("-");
        removeDirectoryButton.addActionListener(this::removeDirectory);
        add(removeDirectoryButton);

        JButton playButton = new JButton("Tocar Música");
        playButton.addActionListener(this::playMusic);
        add(playButton);

        // Adiciona um ouvinte para a seleção de diretórios
        listDirectories.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                PlayerGUI.this.valueChanged(e);
            }
        });
    }
    
    private void removeDirectory(ActionEvent e) {
        // Obtém o usuário atualmente logado
        User loggedUser = userManager.getLoggedUser();

        // Verifica o tipo de usuário e chama o método específico
        if (loggedUser instanceof CommonUser) {
            CommonUser commonUser = (CommonUser) loggedUser;
            commonUser.removeDirectory();
        } else if (loggedUser instanceof VipUser) {
            VipUser vipUser = (VipUser) loggedUser;
            vipUser.removeDirectory();
        } else {
            // Lida com outros tipos de usuário, se necessário
            System.out.println("Unsupported user type");
        }

        // Atualiza a lista de diretórios após remover um diretório
        updateDirectoriesList();
    }


    private void addDirectory(ActionEvent e) {
        // Obtém o usuário atualmente logado
        User loggedUser = userManager.getLoggedUser();

        // Verifica o tipo de usuário e chama o método específico
        if (loggedUser instanceof CommonUser) {
            CommonUser commonUser = (CommonUser) loggedUser;
            commonUser.addDirectory();
        } else if (loggedUser instanceof VipUser) {
            VipUser vipUser = (VipUser) loggedUser;
            vipUser.addDirectory();
        } else {
            // Lida com outros tipos de usuário, se necessário
            System.out.println("Unsupported user type");
        }
        
        // Atualiza a lista de diretórios após adicionar um novo
        updateDirectoriesList();
    }

    private void playMusic(ActionEvent e) {
        String selectedDirectory = listDirectories.getSelectedValue();
        String selectedFile = listDirectoryFiles.getSelectedValue();

        if (selectedDirectory != null && selectedFile != null) {
            String filePath = selectedDirectory + File.separator + selectedFile;
            System.out.println("Tocando Música:" + selectedFile);
            System.out.println("SongPath: " + filePath);
            AudioPlayer audioPlayer = new AudioPlayer(filePath, false);
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

    private void updateDirectoriesList() {
        // Obtém o usuário atualmente logado
        User loggedUser = userManager.getLoggedUser();

        // Limpa a lista antes de adicionar os novos diretórios
        listModel.clear();

        // Adiciona os diretórios do usuário à lista
        if (loggedUser != null && loggedUser instanceof CommonUser) {
            CommonUser commonUser = (CommonUser) loggedUser;
            listModel.addAll(commonUser.getDirectories());
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            User loggedUser = new CommonUser("username", "email", "password");
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new PlayerGUI(loggedUser));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
