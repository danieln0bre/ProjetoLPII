package br.ufrn.imd.main;
import br.ufrn.imd.authentication.UserManager;

public class MediaPlayerApp {
	public static void main (String[] args) {
		
		UserManager manager = new UserManager();
		manager.registerUser("common", "daniel", "daniel@teste.com", "teste");
		manager.registerUser("vip", "gabriel", "gabriel@teste.com", "teste");
		manager.loadUsers();
		System.out.println(manager.loadUsers());
		// CommonUser user = new CommonUser();		
		
		// user.addDirectory();
	}
}
