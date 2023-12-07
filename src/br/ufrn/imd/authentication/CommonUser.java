package br.ufrn.imd.authentication;

import br.ufrn.imd.filehandling.DirectoriesFileHandler;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class CommonUser extends User {

    private String userDirectoryPath = "./files/";

    public CommonUser(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    @Override
    public String getUserType() {
        return "CommonUser";
    }

    public void addDirectory() {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            String selectedDirectory = fileChooser.getSelectedFile().getAbsolutePath();
            System.out.println("Selected directory: " + selectedDirectory);

            this.userDirectoryPath = selectedDirectory;

            updateDirectoriesFile();
        } else {
            System.out.println("No directory selected.");
        }
    }

    private void updateDirectoriesFile() {
        DirectoriesFileHandler directoriesFileHandler = new DirectoriesFileHandler(this.username);
        ArrayList<String> directoriesData = directoriesFileHandler.readData();

        directoriesData.add(this.userDirectoryPath);

        directoriesFileHandler.writeData(directoriesData);
    }

    public CommonUser() {

    }
}
