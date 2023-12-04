package br.ufrn.imd.filehandling;

import br.ufrn.imd.exceptions.PlaylistException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class PlaylistFileHandler {

    private String filePath;

    public PlaylistFileHandler(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<String> readData() throws PlaylistException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            ArrayList<String> data = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
            return data;
        } catch (IOException e) {
            throw new PlaylistException("Error reading playlist data.", e);
        }
    }

    public void writeData(ArrayList<String> data) throws PlaylistException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : data) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            throw new PlaylistException("Error writing playlist data.", e);
        }
    }
}