package br.ufrn.imd.authentication;
import br.ufrn.imd.filehandling.DirectoriesFileHandler;
import java.util.ArrayList;

import javax.swing.JFileChooser;
// import javax.swing.JFrame;
import javax.swing.filechooser.FileSystemView;

public class CommonUser extends User {
	
	String userDirectoryPath = "./files/" + username; 
	
	CommonUser(String username, String email, String password){
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
            String diretorioSelecionado = fileChooser.getSelectedFile().getAbsolutePath();
            System.out.println("Diretório selecionado: " + diretorioSelecionado);

            // Set the selected directory as the user's directory
            userDirectoryPath = diretorioSelecionado;

            // Call the method to update the diretorios.txt file
            updateDirectoriesFile();
        } else {
            System.out.println("Nenhum diretório selecionado.");
        }
    }

    private void updateDirectoriesFile() {
        DirectoriesFileHandler directoriesFileHandler = new DirectoriesFileHandler();
        ArrayList<String> directoriesData = directoriesFileHandler.readData();

        // Add the user's directory path to the data
        directoriesData.add(this.username + ";" + this.userDirectoryPath);

        // Write the updated data back to the file
        directoriesFileHandler.writeData(directoriesData);
    }
	
	public CommonUser(){
		
	}
        
}
