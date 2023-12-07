package br.ufrn.imd.filehandling;

import java.io.*;
import java.util.ArrayList;

public class DirectoriesFileHandler implements IFileHandler {

    private String username;
    private static final String USER_DIRECTORY_PATH = "./files/";

    public DirectoriesFileHandler(String username) {
        this.username = username;
    }

    private String getDirectoriesFilePath() {
        return USER_DIRECTORY_PATH + username + "/diretorios.txt";
    }

    @Override
    public ArrayList<String> readData() {
        ArrayList<String> directoriesData = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(getDirectoriesFilePath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                directoriesData.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading directories file: " + e.getMessage());
        }

        return directoriesData;
    }

    @Override
    public void writeData(ArrayList<String> data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(getDirectoriesFilePath(), true))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to directories file: " + e.getMessage());
        }
    }
}
