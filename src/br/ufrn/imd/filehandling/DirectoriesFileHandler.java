package br.ufrn.imd.filehandling;

import java.io.*;
import java.util.ArrayList;

public class DirectoriesFileHandler implements IFileHandler {

    private static final String DIRECTORIES_FILE_PATH = "./files/diretorios.txt";

    @Override
    public ArrayList<String> readData() {
        ArrayList<String> directoriesData = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(DIRECTORIES_FILE_PATH))) {
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DIRECTORIES_FILE_PATH, true))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to directories file: " + e.getMessage());
        }
    }
}
