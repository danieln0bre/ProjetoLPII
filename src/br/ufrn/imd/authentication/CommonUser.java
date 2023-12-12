package br.ufrn.imd.authentication;

import br.ufrn.imd.filehandling.DirectoriesFileHandler;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;
import java.io.File;
import java.util.ArrayList;

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
    
    public void removeDirectory() {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            String selectedDirectory = fileChooser.getSelectedFile().getAbsolutePath();
            System.out.println("Selected directory to remove: " + selectedDirectory);

            if (removeDirectoryFromList(selectedDirectory)) {
                System.out.println("Directory removed successfully.");
            } else {
                System.out.println("Directory not found in the list.");
            }

            updateDirectoriesFile();
        } else {
            System.out.println("No directory selected.");
        }
    }

    private boolean removeDirectoryFromList(String directoryToRemove) {
        ArrayList<String> directories = getDirectories();
        if (directories.contains(directoryToRemove)) {
            directories.remove(directoryToRemove);
            return true;
        }
        return false;
    }

    private void updateDirectoriesFile() {
        DirectoriesFileHandler directoriesFileHandler = new DirectoriesFileHandler(this.username);
        ArrayList<String> directoriesData = new ArrayList<>(getDirectories());
        directoriesData.add(this.userDirectoryPath);

        directoriesFileHandler.writeData(directoriesData);
    }
    
    public ArrayList<String> getDirectories() {
        // Read directories from the file using DirectoriesFileHandler
        DirectoriesFileHandler directoriesFileHandler = new DirectoriesFileHandler(this.username);
        return directoriesFileHandler.readData();
    }

    public CommonUser() {

    }
}
