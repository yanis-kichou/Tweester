package bd;

import java.sql.Statement;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import tools.AuthentificationTools;

public class UserBD {

	public static final String BD = "User";

	public static void AddNewUser(String nom, String prenom, String login, String password, String mail, String tel,char sexe)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
		
		String requete = String.format("insert into " + BD + " values (null,'%s','%s','%s','%s','%s','%s','%c')",nom ,prenom, login, password, mail, tel,sexe);
		
			Connection c = Database.getMySQLConnection();
			
			Statement s = c.createStatement();
			
			s.executeUpdate(requete);
			s.close();
			c.close();
		

	}

	public static JSONObject deleteUser(String login) {
		String requete=String.format("DELETE FROM %s WHERE login = '%s'",BD,login);
		JSONObject json=new JSONObject();
		try {
			Connection c=Database.getMySQLConnection();
			System.out.println("connection ok");
			Statement stm=c.createStatement();
			stm.executeUpdate(requete);
			stm.close();
			c.close();
			json.put(login," has been deleted from the Users");
		}catch (Exception e) {
			System.err.println("bye bye ");
		}
		
		return json;
		
	}

	public static JSONObject MyProfile(int id) throws JSONException {
		String requete=String.format("select * from %s WHERE idUser = '%d'",BD,id);
		JSONObject json=new JSONObject();
		try {
			Connection c=Database.getMySQLConnection();
			System.out.println("connection ok");
			Statement stm=c.createStatement();
			ResultSet s=stm.executeQuery(requete);
			if(s.next()) {
				json.put("nom",s.getString("nom") );
				json.put("prenom",s.getString("prenom") );
				json.put("login", s.getString("login"));
				json.put("mail", s.getString("mail"));
				json.put("tel", s.getString("tel"));
				json.put("sexe",s.getString("sexe"));
				json.put("key", AuthentificationTools.getKey(id));
			}else {
				json.put("Error", -1);
			}
			s.close();
			stm.close();
			c.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		json.put("key", AuthentificationTools.getKey(id));
		return json;
	}

	

}
