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

public class FriendBD {
	
	public static String BD="Friends";
	public static void addFriend(int myId, int id) {
	
			String requete=String.format("insert into "+BD+"(idUser1,idUser2) values (%d,%d) ",myId,id );
			System.out.println(myId+" "+id);
			Statement stm;
			try {
				Connection c=Database.getMySQLConnection();
				stm = c.createStatement();
				stm.executeUpdate(requete);
				stm.close();
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	public static void removeFriend(int myId, int id) {
		
		String requete=String.format("delete from "+BD+" where idUser2=%d ",id );
		Statement stm;
		try {
			Connection c=Database.getMySQLConnection();
			
			stm = c.createStatement();
			stm.executeUpdate(requete);
			stm.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
}
	public static JSONObject getListFiends(int myId) {
		
		String requete=String.format("select idUser2,login,nom,prenom from %s c,%s d where idUser1=%d and c.idUser2=d.idUser ", BD,UserBD.BD,myId,myId);
		JSONObject retour =new JSONObject();
		
		List<JSONObject> friend =new ArrayList<>();
		Connection c;
		try {
			c = Database.getMySQLConnection();
			Statement stm=c.createStatement();
			ResultSet r=stm.executeQuery(requete);
			
			while (r.next()) {
				JSONObject tmp=new JSONObject();
				tmp.append("IdUSer",r.getInt("idUser2"));
				tmp.append("login",r.getString("login"));
				tmp.append("nom",r.getString("nom"));
				tmp.append("prenom",r.getString("prenom"));
				friend.add(tmp);
			}
		retour.put("Friend", friend);
		retour.put("key", AuthentificationTools.getKey(myId));
		stm.close();
		c.close();
		} catch (SQLException | JSONException e) {
			e.printStackTrace();
		}
	return retour;
	}

}
