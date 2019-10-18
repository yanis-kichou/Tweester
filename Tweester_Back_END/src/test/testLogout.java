package test;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.Test;

import services.User;
import tools.AuthentificationTools;

public class testLogout {

	@Test
	public void testlogout() {
		
		User.Logout(TestCreateUser.us1.getLogin());
		assertFalse(AuthentificationTools.checkSaissionExist(AuthentificationTools.getIdUser(TestCreateUser.us1.getLogin())));
		System.out.println("Logout Ok");
	}
}
