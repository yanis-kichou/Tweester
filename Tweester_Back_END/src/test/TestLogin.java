package test;

import org.json.JSONException;

import bd.UserBD;
import services.Comment;
import services.User;
import tools.AuthentificationTools;

public class TestLogin {
public static void main(String[] args) {
	

 UserTest e=new UserTest("anonymous", "anonymous", "annonymous", "anonymous", "annonymous","annonymous", 'a');


	
		System.out.println(TestCreateUser.us1.getPassword());
		System.out.println(User.Login(TestCreateUser.us1.getLogin(),TestCreateUser.us1.getPassword())+" Essaye de se connectée ....");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(AuthentificationTools.checkSaissionExist(AuthentificationTools.getIdUser(TestCreateUser.us1.getLogin())));
			System.out.println("L'utilisateur est bien connecté");
		
		
		System.out.println(e.getLogin()+": "+" Essaye de se connectée ....");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		if(!AuthentificationTools.checkSaissionExist(AuthentificationTools.getIdUser(e.getLogin())));
			System.out.println("L'utilisateur n'existe pas ");
	
		try {
			System.out.println(User.Profile(AuthentificationTools.getKey(AuthentificationTools.getIdUser(TestCreateUser.us1.getLogin()))));
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
 	

}
}

