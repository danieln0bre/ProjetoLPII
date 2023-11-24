package br.ufrn.imd.authentication;

import javax.swing.JFileChooser;
// import javax.swing.JFrame;
import javax.swing.filechooser.FileSystemView;

public abstract class User {
	protected String username;
	protected String email;
	protected String password;
	protected int id;
	protected boolean auth;
	
	public User(String username, String email, String password, int id, boolean auth){
		this.username = username;
		this.email = email;
		this.password = password;
		this.id = id;
		this.auth = auth;
		
	}
	
	public User() {
		
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
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword () {
		return password;
	}
	
	public void serId(int id) {
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
	
	public void setAuth(boolean auth) {
		this.auth = auth;
	}
	
	public boolean getAuth() {
		return auth;
	}
	
}
