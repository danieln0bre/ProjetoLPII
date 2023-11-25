package br.ufrn.imd.authentication;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class UserManager {
	
    private ArrayList<User> userList;
    private static final String USER_FILE_PATH = "usuarios.txt";
    
    public UserManager() {
        this.userList = new ArrayList<>();
        loadUsersFromFile();
    }
	
	public void Register(String userType, String username, String password, String email) {
		if (userType.equals("VipUser")) {
			
		}
	}

}
