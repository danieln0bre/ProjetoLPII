package br.ufrn.imd.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayerGUI extends JFrame implements ActionListener {
    public StringBuffer directories = new StringBuffer();

    PlayerGUI() {
        JPanel panel = new JPanel();
        setTitle("MediaPlayerApp");
        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        add(panel);

        // TEMPORÁRIO
        /*adicionarDiretorio("ROCK ");
        adicionarDiretorio("POP ");
        adicionarDiretorio("FUNK ");
        adicionarDiretorio("SERTANEJO ");
        adicionarDiretorio("TRAP ");
        adicionarDiretorio("RAP ");*/
        for(int i = 0; i < 50; i++)
        {
            adicionarDiretorio("TEGELA ");
        }
        // -------

        JList<String> listDirectories = new JList<>(directories.toString().split(" "));
        listDirectories.setFont(new Font("Arial", Font.BOLD, 18));
        listDirectories.setVisibleRowCount(3);

        JScrollPane listDirectoriesScroll = new JScrollPane(listDirectories);
        // Ajuste a posição e o tamanho do JScrollPane
        listDirectoriesScroll.setBounds(100, 100, 200, 200);
        add(listDirectoriesScroll);

        setVisible(true);
    }

    public boolean adicionarDiretorio(String path) {
        this.directories.append(path);
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Hello World");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PlayerGUI());
    }
}
