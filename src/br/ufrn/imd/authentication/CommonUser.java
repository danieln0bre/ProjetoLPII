package br.ufrn.imd.authentication;

import br.ufrn.imd.filehandling.DirectoriesFileHandler;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class CommonUser extends User {

    // Assuming this path is only for initialization and will be updated during runtime
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

            // Set the selected directory as the user's directory
            this.userDirectoryPath = selectedDirectory;

            // Call the method to update the diretorios.txt file
            updateDirectoriesFile();
        } else {
            System.out.println("No directory selected.");
        }
    }

    private void updateDirectoriesFile() {
        DirectoriesFileHandler directoriesFileHandler = new DirectoriesFileHandler(this.username);
        ArrayList<String> directoriesData = directoriesFileHandler.readData();

        // Add the user's directory path to the data
        directoriesData.add(this.userDirectoryPath);

        // Write the updated data back to the file
        directoriesFileHandler.writeData(directoriesData);
    }

    public CommonUser() {

    }
}
