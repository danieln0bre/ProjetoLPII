package br.ufrn.imd.main;
import br.ufrn.imd.authentication.UserManager;
import br.ufrn.imd.gui.LoginGUI;

public class MediaPlayerApp {
	public static void main (String[] args) {

		new LoginGUI();
		UserManager manager = new UserManager();
		manager.registerUser("common", "daniel", "daniel@teste.com", "teste");
		manager.registerUser("vip", "gabriel", "gabriel@teste.com", "teste");
		manager.loadUsers();
		System.out.println(manager.loadUsers());
	
	}
}
