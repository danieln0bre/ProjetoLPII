package br.ufrn.imd.authentication;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import br.ufrn.imd.filehandling.UserFileHandler;
import br.ufrn.imd.filehandling.DirectoriesFileHandler;
import br.ufrn.imd.exceptions.AuthenticationException;

public class UserManager {

    private static final String USER_FILE_PATH = "./files/usuarios.txt";
    private static final String USER_DIRECTORY_PATH = "./files/";

    public UserManager() {
        // Check if the user file exists and create it if it doesn't
        try {
            FileWriter fileWriter = new FileWriter(USER_FILE_PATH, true);
            fileWriter.close();
        } catch (IOException e) {
            // Handle the IOException appropriately, log it, or take corrective action
            e.printStackTrace();
        }
    }

    public void registerUser(String type, String username, String email, String password) {
        // Load existing users
        ArrayList<User> users = loadUsers();

        // Check if the username or email is already in use
        if (isUsernameTaken(users, username) || isEmailTaken(users, email)) {
            System.err.println("Error: Username or email already in use.");
            return;
        }

        ArrayList<String> userData = new ArrayList<>();
        userData.add(type + ";" + username + ";" + email + ";" + password);

        UserFileHandler userFileHandler = new UserFileHandler();
        userFileHandler.writeData(userData);

        createDirectoryForUser(username);

        // Pass the username to DirectoriesFileHandler
        DirectoriesFileHandler directoriesFileHandler = new DirectoriesFileHandler(username);
        directoriesFileHandler.writeData(new ArrayList<>()); // You can pass actual data if needed
    }

    public User loginUser(String username, String password) throws AuthenticationException {
        ArrayList<User> users = loadUsers();

        for (User user : users) {
            user.setAuth(false);
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                user.setAuth(true);
                System.out.println("Successful login for user: " + username);
                return user;  // Return the authenticated user object
            }
        }

        throw new AuthenticationException("Login failed. Check the username and password.");
    }

    private boolean isUsernameTaken(ArrayList<User> users, String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    private boolean isEmailTaken(ArrayList<User> users, String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    private void createDirectoryForUser(String username) {
        String userDirectoryPath = USER_DIRECTORY_PATH + username;

        File userDirectory = new File(userDirectoryPath);
        if (!userDirectory.exists()) {
            if (userDirectory.mkdirs()) {
                System.out.println("User directory created: " + userDirectoryPath);

                String diretoriosFilePath = userDirectoryPath + "/diretorios.txt";
                File diretoriosFile = new File(diretoriosFilePath);

                try {
                    if (diretoriosFile.createNewFile()) {
                        System.out.println("Arquivo de diretórios criado: " + diretoriosFilePath);
                    } else {
                        System.err.println("Falha ao criar arquivo de diretorios: " + diretoriosFilePath);
                    }
                } catch (IOException e) {
                    System.err.println("Erro ao criar arquivo de diretorios: " + e.getMessage());
                }

            } else {
                System.err.println("Falha ao criar diretorio de usuario: " + userDirectoryPath);
            }
        }
    }

    public ArrayList<User> loadUsers() {
        UserFileHandler userFileHandler = new UserFileHandler();
        ArrayList<String> userLines = userFileHandler.readData();

        ArrayList<User> users = new ArrayList<>();

        for (String line : userLines) {
            String[] data = line.split(";");
            
            // Check if there are at least 4 elements in the array before accessing the indices
            if (data.length >= 4) {
                String type = data[0];
                String username = data[1];
                String email = data[2];
                String userPassword = data[3];

                if ("vip".equals(type)) {
                    users.add(new VipUser(username, email, userPassword));
                } else if ("common".equals(type)) {
                    users.add(new CommonUser(username, email, userPassword));
                }
            } else {
                System.err.println("Error: Line in the file does not contain enough information: " + line);
            }
        }

        return users;
    }

    public boolean isUserVip(User user) {
        ArrayList<User> users = loadUsers();

        for (User currentUser : users) {
            if (currentUser.getUsername().equals(user.getUsername()) && currentUser.getUserType().equals("VipUser")) {
                return true;
            }
        }

        return false;
    }
}
