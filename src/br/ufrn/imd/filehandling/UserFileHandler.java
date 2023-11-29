package br.ufrn.imd.filehandling;

import java.io.*;
import java.util.ArrayList;

public class UserFileHandler implements IFileHandler {

    private static final String USER_FILE_PATH = "./files/usuarios.txt";

    @Override
    public ArrayList<String> readData() {
    	ArrayList<String> userLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                userLines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Trate exceções específicas se necessário
        }

        return userLines;
    }

    @Override
    public void writeData(ArrayList<String> userData) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE_PATH, true))) {
            for (String data : userData) {
                writer.write(data);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Trate exceções específicas se necessário
        }
    }
}
