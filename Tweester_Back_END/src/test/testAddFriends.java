package test;

import org.junit.Test;

import services.Friends;
import tools.AuthentificationTools;

public class testAddFriends {
	String nom1 = "mohamed";
	String prenom1 = "kichou";
	String login1 = "mohamed";
	String password1 = "mohamed";
	String mail1 = "@gmail.com";
	String tel1 = "123456789";
	
	String nom = "yanis";
	String prenom = "kichou";
	String login = "yanis";
	String password = "yanis";
	String mail = "@gmail.com";
	String tel = "123456789";
	char sexe='H';
	@Test
	public void ajouterMultipleFriendTest() {
		
		int myId=AuthentificationTools.getIdUser(TestCreateUser.us1.getLogin());
		
		for (int i = 0; i < 10; i++) {
			System.out.println(Friends.addFriendship(AuthentificationTools.getIdUser(login1+i),AuthentificationTools.getKey(AuthentificationTools.getIdUser(TestCreateUser.us1.getLogin()))));
		}
		
		System.out.println(Friends.getFriendOfMine(AuthentificationTools.getKey(myId)));
	}
}
