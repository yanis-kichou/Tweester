package services;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import bd.SessionBD;
import bd.UserBD;
import tools.AuthentificationTools;
import tools.ErrorJSON;

public class User {

	public static JSONObject CreateUser(String nom, String prenom, String login, String password, String mail,
			String tel,char sexe) throws JSONException {
		JSONObject retour = new JSONObject();

		try {

			if (nom == null || prenom == null || login == null || password == null || mail == null || tel == null)
				return ErrorJSON.serviceRefused("Argument missed ", -1);

			if (AuthentificationTools.userExists(login)) {
				return ErrorJSON.serviceRefused("User already exists", 1);
			}
			UserBD.AddNewUser(nom, prenom, login, password, mail, tel,sexe);
			retour.put(" WELCOME " + nom + " " + prenom + " login", login);

		} catch (JSONException | ClassNotFoundException | InstantiationException | IllegalAccessException
				| SQLException e) {
			return new JSONObject().put("error", e.getMessage());
		}
		return retour;
	}

	public static JSONObject Login(String login, String password) {

		JSONObject retour = new JSONObject();
		try {
			if (login == null || password == null)
				return ErrorJSON.serviceRefused("Wrong Argument", -1);

			if (!AuthentificationTools.userExists(login))
				return ErrorJSON.serviceRefused("Unknown logger " + login, 1);

			if (!AuthentificationTools.checkPassword(login, password))
				return ErrorJSON.serviceRefused(" Wrong Password", 1);

			int id_User = AuthentificationTools.getIdUser(login);

			String key = AuthentificationTools.insertSession(id_User, login);

			retour.put("idUser",id_User);
			retour.put("key",key);
			retour.put("logger",login);
			
			return retour;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return retour;
	}

	public static JSONObject Logout(String login) {

		JSONObject retour =new JSONObject();
		try {
			if (login == null)
				return ErrorJSON.serviceRefused("wrong Argument ", -1);

			if (!AuthentificationTools.userExists(login))
				return ErrorJSON.serviceRefused("Unknown user", -1);

			int id = AuthentificationTools.getIdUser(login);
			
			if (!AuthentificationTools.checkSaissionExist(id))
				return ErrorJSON.serviceRefused("you aren't logged in", -1);
			
			SessionBD.Logout(id);
			retour.put("User", login);
			retour.put("logged out",1);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return retour;
		
	}

	public static JSONObject Profile(String key) throws JSONException {
		try {
			if (key== null)
				return ErrorJSON.serviceRefused("wrong Argument ", -1);

			if (!AuthentificationTools.userExists(AuthentificationTools.getLogin((AuthentificationTools.getIdUserByKey(key)))))
				return ErrorJSON.serviceRefused("Unknown user", -1);

			int id = AuthentificationTools.getIdUser(AuthentificationTools.getLogin((AuthentificationTools.getIdUserByKey(key))));
			
			if (!AuthentificationTools.checkSaissionExist(id))
				return ErrorJSON.serviceRefused("you aren't logged in", -1);
			
			return UserBD.MyProfile(id);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return  ErrorJSON.serviceRefused("erreur", -1);
	}

	public static JSONObject loggers(String key) throws JSONException {
		try {
			if (key== null)
				return ErrorJSON.serviceRefused("wrong Argument ", -1);

			if (!AuthentificationTools.userExists(AuthentificationTools.getLogin((AuthentificationTools.getIdUserByKey(key)))))
				return ErrorJSON.serviceRefused("Unknown user", -1);

			int id = AuthentificationTools.getIdUser(AuthentificationTools.getLogin((AuthentificationTools.getIdUserByKey(key))));
			
			if (!AuthentificationTools.checkSaissionExist(id))
				return ErrorJSON.serviceRefused("you aren't logged in", -1);
			
			return SessionBD.loggers(id);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return  ErrorJSON.serviceRefused("erreur", -1);
	}

}
