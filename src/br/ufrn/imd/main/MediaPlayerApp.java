package br.ufrn.imd.main;
//import br.ufrn.imd.authentication.UserManager;
import br.ufrn.imd.gui.*;
//import br.ufrn.imd.gui.RegisterGUI;
import br.ufrn.imd.logic.AudioPlayer;

public class MediaPlayerApp {
	public static void main (String[] args) {

		new LoginGUI();
		//new RegisterGUI();
		/*UserManager manager = new UserManager();
		manager.loadUsers();
		manager.registerUser("common", "daniel", "daniel@teste.com", "teste");
		manager.registerUser("vip", "gabriel", "gabriel@teste.com", "teste");
		System.out.println(manager.loadUsers());
		manager.loginUser();*/
		
        String filePath = "/home/daniel/eclipse-workspace/ProjetoLPII/teste/HINO.mp3";

        AudioPlayer audioPlayer = new AudioPlayer(filePath);
        audioPlayer.play();

        // Sleep for a while to allow the player to start playing
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	
	}
}
