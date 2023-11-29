package br.ufrn.imd.authentication;
import java.io.*;
import java.util.ArrayList;

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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE_PATH, true))) {
            // Escreve no arquivo no formato: tipo;nome;email;senha
            writer.write(tipo + ";" + nome + ";" + email + ";" + senha);
            writer.newLine();

            if ("vip".equals(tipo)) {
                createDirectoryForUser(nome);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        ArrayList<User> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }

        return users;
    }
}
