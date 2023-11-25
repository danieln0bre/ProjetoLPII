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
