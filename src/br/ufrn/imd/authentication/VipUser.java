package br.ufrn.imd.authentication;

public class VipUser extends CommonUser {
	
	VipUser(String username, String email, String password) {
		super(username, email, password);
	}
	
	VipUser() {
		
	}
	
    @Override
    public String getUserType() {
        return "VipUser";
    }

}
