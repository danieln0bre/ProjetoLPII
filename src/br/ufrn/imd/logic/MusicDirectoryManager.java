package br.ufrn.imd.logic;

import br.ufrn.imd.filehandling.DirectoriesFileHandler;
import br.ufrn.imd.authentication.CommonUser;

import java.util.ArrayList;

public class MusicDirectoryManager {

    public void addMusicDirectory(CommonUser user, String directoryPath) {
        // Get the existing directories for the user
        ArrayList<String> directoriesData = getDirectoriesData(user);

        // Add the new directory to the data
        directoriesData.add(user.getUsername() + ";" + directoryPath);

        // Write the updated data back to the file
        updateDirectoriesFile(directoriesData, user);
    }

    public ArrayList<String> getMusicDirectories(CommonUser user) {
        // Get the existing directories for the user
        return getDirectoriesData(user);
    }

    private ArrayList<String> getDirectoriesData(CommonUser user) {
        DirectoriesFileHandler directoriesFileHandler = new DirectoriesFileHandler(user.getUsername());
        return directoriesFileHandler.readData();
    }

    private void updateDirectoriesFile(ArrayList<String> directoriesData, CommonUser user) {
        DirectoriesFileHandler directoriesFileHandler = new DirectoriesFileHandler(user.getUsername());
        directoriesFileHandler.writeData(directoriesData);
    }
}
