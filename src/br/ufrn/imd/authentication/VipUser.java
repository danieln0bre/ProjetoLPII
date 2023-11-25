package br.ufrn.imd.authentication;

public class VipUser extends CommonUser {
	
	VipUser(String username, String email, String password, int id, boolean auth) {
		super(username, email, password, id, auth);
	}

}
