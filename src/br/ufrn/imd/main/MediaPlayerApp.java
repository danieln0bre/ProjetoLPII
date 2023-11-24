package br.ufrn.imd.main;
import br.ufrn.imd.authentication.User;
import br.ufrn.imd.authentication.CommonUser;

public class MediaPlayerApp {
	public static void main (String[] args) {
		CommonUser user = new CommonUser();
		
		user.addDirectory();
	}
}
