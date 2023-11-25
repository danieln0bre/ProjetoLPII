package br.ufrn.imd.authentication;
import java.io.*;
import java.util.ArrayList;

public class UserManager {
	
    private ArrayList<User> users;
    private static final String USER_FILE_PATH = "../../../../files/usuarios.txt";
    
    public UserManager() {
        this.users = new ArrayList<>();
        loadUsersFromFile(USER_FILE_PATH );
    }
	
    public void registerUser(User newUser) {
        users.add(newUser);
        saveUsersToFile(USER_FILE_PATH);
    }
	
    // Método para salvar os usuários no arquivo usuarios.txt
    private void saveUsersToFile(String USER_FILE_PATH) {
        try (FileWriter writer = new FileWriter(USER_FILE_PATH)) {
            for (User user : users) {
                writer.write(user.getUserType() + "," + user.username + "," + user.password + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadUsersFromFile(String USER_FILE_PATH ) {
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE_PATH ))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] userData = line.split(",");
                if (userData.length == 3) {
                    String userType = userData[0];
                    String username = userData[1];
                    String password = userData[2];

                    User user;
                    if ("CommonUser".equals(userType)) {
                        user = new CommonUser();
                    } else if ("VipUser".equals(userType)) {
                        user = new VipUser();
                    }

                    user.username = username;
                    user.password = password;

                    users.add(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
