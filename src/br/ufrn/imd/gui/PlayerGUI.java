package br.ufrn.imd.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import br.ufrn.imd.authentication.*;

public class PlayerGUI extends JFrame implements ActionListener {
    private static User user;
    public StringBuffer directories = new StringBuffer();

    public PlayerGUI(User user) {
        this.user = user;

        setTitle("MediaPlayerApp");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        add(panel);

        for (int i = 0; i < 50; i++) {
            adicionarDiretorio("TEGELA ");
        }

        JList<String> listDirectories = new JList<>(directories.toString().split(" "));
        listDirectories.setFont(new Font("Arial", Font.BOLD, 18));
        listDirectories.setVisibleRowCount(3);

        JScrollPane listDirectoriesScroll = new JScrollPane(listDirectories);
        listDirectoriesScroll.setBounds(100, 100, 200, 200);
        add(listDirectoriesScroll);

        JButton backButton = new JButton("Voltar para MainGUI");
        backButton.setBounds(120, 350, 160, 25);
        backButton.addActionListener(this::backToMainGUI);
        add(backButton);

        setVisible(true);
    }

    public boolean adicionarDiretorio(String path) {
        this.directories.append(path);
        return true;
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
        SwingUtilities.invokeLater(() -> {
            // Supondo que você tenha um objeto de usuário (substitua isso com seu próprio código de obtenção de usuário)
            User loggedUser = new CommonUser(user.getUsername(), user.getEmail(), user.getPassword());
            new PlayerGUI(user);
        });
    }
}
