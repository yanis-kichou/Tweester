package test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;


import services.Friends;
import tools.AuthentificationTools;

public class testRemoveFriends {

	@Test
	public void testRemoveFriends() {
		TestCreateUser.us1.setKey(AuthentificationTools.getKey(AuthentificationTools.getIdUser(TestCreateUser.us1.getLogin())));
		TestCreateUser.us2.setKey(AuthentificationTools.getKey(AuthentificationTools.getIdUser(TestCreateUser.us2.getLogin())));
		
		int myId=AuthentificationTools.getIdUser(TestCreateUser.us1.getLogin());
		int id=AuthentificationTools.getIdUser(TestCreateUser.us2.getLogin());
		
		Friends.RemoveFriend(TestCreateUser.us1.getKey(),4);
		assertFalse(AuthentificationTools.checkFriendship(myId, 4));
	
		
	}
}
