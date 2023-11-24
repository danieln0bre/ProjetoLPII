package br.ufrn.imd.authentication;

import javax.swing.JFileChooser;
// import javax.swing.JFrame;
import javax.swing.filechooser.FileSystemView;

public class CommonUser extends User {
	
	CommonUser(String username, String email, String password, int id, boolean auth) {
		super(username, email, password, id, auth);
	}
	
	public CommonUser(){
		
	}
        
}
