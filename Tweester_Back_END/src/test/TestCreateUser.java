package test;




import org.json.JSONException;

import services.User;
import tools.AuthentificationTools;

public class TestCreateUser {
	public static UserTest us1 =new UserTest("yanis","kichou","yanis","kichou","@gmail.com","123456789",'H');
	public static UserTest us2 =new UserTest("mohamed","kichou","mohamed","mohamed","@gmail.com","123456789",'H');
	
	public void CreateAccountTest() {
		try {
			System.out.println(User.CreateUser(us1.getNom(), us1.getPrenom(),us1.getLogin(),us1.getPassword(), us1.getMail(), us1.getTelephone(),us1.getSexe()));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < 10; i++) {
			try {
				System.out.println(User.CreateUser(us2.getNom()+i, us2.getPrenom()+i,us2.getLogin()+i+"",us2.getPassword(), us2.getMail(), us2.getTelephone(),us2.getSexe()));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		
		
	}
	
	
	public void TestExistance() {
		System.out.println(AuthentificationTools.userExists(us1.getLogin()));
		System.out.println("Compte de :"+us1.toString()+" Cree");
		
		System.out.println(AuthentificationTools.userExists(us2.getLogin()));
		System.out.println("Compte de :"+us2.toString()+" Cree");
		
	}
	public static void main(String[] args) {
		TestCreateUser e=new TestCreateUser();
		e.CreateAccountTest();
		e.TestExistance();
		}
}
