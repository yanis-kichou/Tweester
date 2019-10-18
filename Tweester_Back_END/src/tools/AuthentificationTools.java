package tools;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import bd.Database;
import bd.FriendBD;
import bd.SessionBD;
import bd.UserBD;

public class AuthentificationTools {

	private static String valueOfkeys = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

	/**
	 * en ce qui concerne les teste
	 * 
	 */

	// ===========================================================
	public static boolean userExists(String login) {
		String requete = String.format("select * from " + UserBD.BD + " where login='%s'", login);
		boolean exist = false;
		try {
			Connection c = Database.getMySQLConnection();
			Statement stm = c.createStatement();
			ResultSet r = stm.executeQuery(requete);
			exist = r.next();
			stm.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return exist;
	}

	public static boolean userExists(int id) {
		String requete = String.format("select * from " + UserBD.BD + " where idUser=%d", id);
		boolean exist = false;
		try {
			Connection c = Database.getMySQLConnection();
			Statement stm = c.createStatement();
			ResultSet r = stm.executeQuery(requete);
			exist = r.next();
			stm.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return exist;
	}

	public static boolean checkPassword(String login, String password) {
		String requete = String.format("select password from " + UserBD.BD + " where login='%s'", login);
		boolean ok = false;

		try {
			Connection c = Database.getMySQLConnection();
			Statement stm = c.createStatement();
			ResultSet r = stm.executeQuery(requete);
			if (r.next())
				ok = r.getString("password").equals(password);
			
			stm.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ok;

	}

	public static boolean checkSaissionExist(int id_User) {
		String requete = String.format("select * from %s where idUser=%d", SessionBD.BD, id_User);
		Connection c;
		boolean ok = false;
		try {
			c = Database.getMySQLConnection();
			Statement stm = c.createStatement();
			ResultSet r = stm.executeQuery(requete);

			ok = r.next();
			stm.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ok;
	}

//	public static boolean checkActivateSaission( int id_User) {
//		String requete = String.format("select * from %s where idUser=%d", SessionBD.BD, id_User);
//		Connection c;
//		
//		boolean ok = false;
//		try {
//			c = Database.getMySQLConnection();
//			Statement stm = c.createStatement();
//			ResultSet r = stm.executeQuery(requete);
//			
//			long s=0,date_connection=0;
//			String login="";
//			if(r.next()) {
//				
//			s=Timestamp.valueOf(LocalDateTime.now()).getTime();
//			date_connection=r.getTimestamp("date").getTime();
//			login=r.getString("login");	
//			}
//			stm.close();
//			c.close();
//			SessionBD.Logout(id_User);
//			if (s-date_connection<1800000) {
//				SessionBD.AddLogger(id_User, AuthentificationTools.getKey(id_User), login);
//				ok= true;
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return ok;
//
//	}

	public static boolean checkFriendship(int myId, int friendId) {

		String requete = String.format("select * from %s where idUser1=%d and idUser2=%d ", FriendBD.BD, myId,
				friendId);
		Connection c;
		boolean ok = false;
		try {
			c = Database.getMySQLConnection();
			Statement stm = c.createStatement();
			ResultSet r = stm.executeQuery(requete);
			ok = r.next();
			stm.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ok;
	}

	// ================================================================================================================
	// =================================================================================================================

	/**
	 * en ce qui concerne les insertion dans les table
	 */

	// ==============================================================================
	public static String insertSession(int id_User, String login) {
		String key = null;
		if (checkSaissionExist(id_User)) {
			key = getKey(id_User);
		} else {
			key = initRandomKey();
			SessionBD.AddLogger(id_User, key, login);
		}
		return key;

	}

	public static int getIdUser(String login) {
		String requete = String.format("select idUser from %s where login='%s' ", UserBD.BD, login);
		int id = -1;
		try {
			Connection c = Database.getMySQLConnection();
			Statement stm = c.createStatement();
			ResultSet r = stm.executeQuery(requete);
			if (r.next())
				id = Integer.parseInt(r.getString("idUser"));
			stm.close();
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}

	public static String getKey(int idUser) {
		String requete = String.format(" select clef from %s where idUser=%d", SessionBD.BD, idUser);
		String key = null;
		Connection c;
		try {
			c = Database.getMySQLConnection();
			Statement stm = c.createStatement();
			ResultSet r = stm.executeQuery(requete);
			if (r.next())
				key = r.getString("clef");
			
			stm.close();
			c.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return key;
	}

	public static int getIdUserByKey(String key) {
		String requete = String.format("select idUser from %s where clef='%s'", SessionBD.BD, key);
		int id = -1;
		Connection c;
		try {
			c = Database.getMySQLConnection();
			Statement stm = c.createStatement();
			ResultSet r = stm.executeQuery(requete);
			if (r.next())
				id = r.getInt("idUser");
			stm.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return id;
	}
	public static String getLogin(int id) {
		String requete = String.format("select * from %s where idUser='%d'", UserBD.BD,id);
		Connection c;
		String login="";
		try {
			c = Database.getMySQLConnection();
			Statement stm = c.createStatement();
			ResultSet r = stm.executeQuery(requete);
			if (r.next())
				login = r.getString("login");
			stm.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return login;
	}
		public static String getPrenom(int id) {
			String requete = String.format("select idUser from %s where idUser='%d'", UserBD.BD,id);
			Connection c;
			String prenom="";
			try {
				c = Database.getMySQLConnection();
				Statement stm = c.createStatement();
				ResultSet r = stm.executeQuery(requete);
				if (r.next())
					prenom= r.getString("login");
				stm.close();
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return prenom;
		}
	public static String getNom(int id) {
		String requete = String.format("select idUser from %s where nom='%s'", UserBD.BD,id);
		Connection c;
		String nom="";
		try {
			c = Database.getMySQLConnection();
			Statement stm = c.createStatement();
			ResultSet r = stm.executeQuery(requete);
			if (r.next())
				nom = r.getString("nom");
			stm.close();
			c.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	return nom;	
	}

	public static final String initRandomKey() {
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < 32; i++)
			s.append(valueOfkeys.charAt(((int) (Math.random() * valueOfkeys.length()))));
		return s.toString();
	}
}
