package br.ufrn.imd.authentication;

import javax.swing.JFileChooser;
// import javax.swing.JFrame;
import javax.swing.filechooser.FileSystemView;

public class CommonUser extends User {
	
	CommonUser(String username, String email, String password, int id, boolean auth){
		this.username = username;
		this.email = email;
		this.password = password;
		this.id = id;
		this.auth = auth;
		
	}
	
    @Override
    public String getUserType() {
        return "CommonUser";
    }
	
	public void addDirectory() {
        // Cria uma instância do JFileChooser
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

        // Configura o modo para a escolha de diretórios
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

        // Exibe a janela de seleção de diretório
        int result = fileChooser.showOpenDialog(null);

        // Verifica se o usuário selecionou um diretório
        if (result == JFileChooser.APPROVE_OPTION) {
            // Obtém o diretório selecionado
            String diretorioSelecionado = fileChooser.getSelectedFile().getAbsolutePath();

            // Faça algo com o diretório selecionado (por exemplo, imprimir na console)
            System.out.println("Diretório selecionado: " + diretorioSelecionado);
        } else {
            System.out.println("Nenhum diretório selecionado.");
        
        }
	}
	
	public CommonUser(){
		
	}
        
}
