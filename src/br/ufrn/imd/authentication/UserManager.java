package br.ufrn.imd.authentication;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import br.ufrn.imd.filehandling.UserFileHandler;

public class UserManager {
    
    private static final String USER_FILE_PATH = "./files/usuarios.txt";
    private static final String USER_DIRECTORY_PATH = "./files/";

    public UserManager() {
        // Verifica se o arquivo de usuários existe e o cria se não existir
        try {
            FileWriter fileWriter = new FileWriter(USER_FILE_PATH, true);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void registerUser(String tipo, String nome, String email, String senha) {
        // Carrega os usuários existentes
        ArrayList<User> users = loadUsers();

        // Verifica se o nome de usuário ou e-mail já está em uso
        if (isUsernameTaken(users, nome) || isEmailTaken(users, email)) {
            System.err.println("Erro: Nome de usuário ou e-mail já em uso.");
            return;
        }

        ArrayList<String> userData = new ArrayList<>();
        userData.add(tipo + ";" + nome + ";" + email + ";" + senha);

        UserFileHandler userFileHandler = new UserFileHandler();
        userFileHandler.writeData(userData);

        createDirectoryForUser(nome);
    }


    // Métodos auxiliares para verificar se um nome de usuário ou e-mail já está em uso
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
    
    public void loginUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite o nome de usuário:");
        String username = scanner.nextLine();

        System.out.println("Digite a senha:");
        String password = scanner.nextLine();

        ArrayList<User> users = loadUsers();

        for (User user : users) {
        	user.setAuth(false);
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                user.setAuth(true);
                System.out.println("Login bem-sucedido para o usuário: " + username);

                return;
            }
        }

        System.out.println("Login falhou. Verifique o nome de usuário e a senha.");
    }

    private void createDirectoryForUser(String username) {
        String userDirectoryPath = USER_DIRECTORY_PATH + username;

        File userDirectory = new File(userDirectoryPath);
        if (!userDirectory.exists()) {
            if (userDirectory.mkdirs()) {
                System.out.println("Diretório do usuário criado: " + userDirectoryPath);
            } else {
                System.err.println("Falha ao criar diretório do usuário: " + userDirectoryPath);
            }
        }
    }

    public ArrayList<User> loadUsers() {
        UserFileHandler userFileHandler = new UserFileHandler();
        ArrayList<String> userLines = userFileHandler.readData();

        ArrayList<User> users = new ArrayList<>();

        for (String line : userLines) {
            String[] dados = line.split(";");
            
            // Verifica se há pelo menos 4 elementos no array antes de acessar os índices
            if (dados.length >= 4) {
                String type = dados[0];
                String username = dados[1];
                String email = dados[2];
                String password = dados[3];

                if ("vip".equals(type)) {
                    users.add(new VipUser(username, email, password));
                } else if ("common".equals(type)) {
                    users.add(new CommonUser(username, email, password));
                }
            } else {
                System.err.println("Erro: Linha do arquivo não contém informações suficientes: " + line);
            }
        }

        return users;
    }
}
