package services;

import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.util.JSON;

import bd.CommentBD;
import tools.AuthentificationTools;
import tools.ErrorJSON;

public class Comment {

	public static JSONObject addComment(String key, String text) throws JSONException {

		try {
			if (key == null || text == null) {
				return ErrorJSON.serviceRefused("Wrong Arguments", -1);
			}

			int myId = AuthentificationTools.getIdUserByKey(key);
			if (myId < 0)
				return ErrorJSON.serviceRefused("Unknows User", -1);

			if (!AuthentificationTools.checkSaissionExist(myId))
				return ErrorJSON.serviceRefused("you are not logged in please login before adding a comment", -1);

			CommentBD.addCommentBD(myId, text);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ErrorJSON.serviceAccepted("Comment added", 1);
	}

	public static JSONObject removeComment(String key, String commentKey) throws JSONException {

		try {
			if (key == null || commentKey == null) {
				return ErrorJSON.serviceRefused("wrong Arguments  ", -1);
			}

			int myId = AuthentificationTools.getIdUserByKey(key);
			if (myId < 0)
				return ErrorJSON.serviceRefused(" Unknows User", -1);

			if (!AuthentificationTools.checkSaissionExist(myId))
				return ErrorJSON.serviceRefused(" you are not logged in please login before adding a comment", -1);

			CommentBD.RemoveCommentBD(myId, commentKey);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			return ErrorJSON.serviceRefused("Error", -1);
		}
		return null;

	}
	public static JSONObject listeMessage(String key, int id) {
		JSONObject retour = new JSONObject();
		try {
			if (key == null)
				ErrorJSON.serviceRefused(" must login befor", -1);
			if (!AuthentificationTools.checkSaissionExist(id)) {
				retour = ErrorJSON.serviceRefused(" must login before", -1);
			} 
			if (id < 0)
				retour = CommentBD.getComments();
			retour = CommentBD.getComments(id);
			retour.put("key",AuthentificationTools.getKey(id));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}
	public static JSONObject Comments(String key) throws JSONException {
		JSONObject retour = new JSONObject();
		try {
			if (key == null)
				ErrorJSON.serviceRefused(" must login befor", -1);
			int id=AuthentificationTools.getIdUserByKey(key);
			if (!AuthentificationTools.checkSaissionExist(id)) {
				retour = ErrorJSON.serviceRefused(" must login before", -1);
			} 
			if (id < 0)
				retour = CommentBD.getComments();

			return CommentBD.getComments();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ErrorJSON.serviceRefused("check your session", -1);
	}
}
