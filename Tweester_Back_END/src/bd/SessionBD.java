package bd;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import tools.AuthentificationTools;

public class SessionBD {
	public static String BD="Saission";
	
	public static void AddLogger(int id_User, String key, String login) {
		
		String requete=String.format("Insert into %s (idUser,clef,login) values ('%s','%s','%s')",BD,id_User,key,login);
		try {
			Connection c =Database.getMySQLConnection();
			Statement stm=c.createStatement();
			stm.executeUpdate(requete);
			stm.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	

	public static void Logout(int id) {
	
		String requete=String.format("delete from %s where idUser=%d",BD,id);
		
		try {
			Connection c =Database.getMySQLConnection();
			Statement stm=c.createStatement();
			stm.executeUpdate(requete);
			stm.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public static JSONObject loggers(int id) throws JSONException {

		String requete=String.format("select nom,prenom,login,idUser from %s where idUser not in (select idUser2 from %s where idUser1=%d) and idUser<> %d",UserBD.BD,FriendBD.BD,id,id);
		JSONObject r=new JSONObject();
		List<JSONObject> loggers =new ArrayList<>();
		try {
			Connection c =Database.getMySQLConnection();
			Statement stm=c.createStatement();
			ResultSet s=stm.executeQuery(requete);
			while(s.next()) {
				JSONObject retour =new JSONObject();
				retour.put("login",s.getString("login"));
				retour.put("nom",s.getString("nom"));
				retour.put("prenom",s.getString("prenom"));
				retour.put("id",s.getString("idUser"));
				loggers.add(retour);
			}
			stm.close();
			c.close();
		} catch (SQLException | JSONException e) {
			e.printStackTrace();
		}
		r.put("key", AuthentificationTools.getKey(id));
		r.put("loggers", loggers);
		return r;
	}

	

}
