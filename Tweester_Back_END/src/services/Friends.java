package services;


import org.json.JSONException;
import org.json.JSONObject;



import bd.FriendBD;
import tools.AuthentificationTools;
import tools.ErrorJSON;

public class Friends {

	public static JSONObject RemoveFriend(String key, int idFriend) {
		
		JSONObject retour = new JSONObject();

		try {
			if (key == null || idFriend < 0)
				return ErrorJSON.serviceRefused(" Wrong Argument ", -1);

			int myId = AuthentificationTools.getIdUserByKey(key);
			if (!AuthentificationTools.userExists(myId))
				return ErrorJSON.serviceRefused("Unknown User " + myId, 0);
		
			if (!AuthentificationTools.checkSaissionExist(myId))
				return ErrorJSON.serviceRefused("you must login before deleting a friend ", 0);
		
			if (!AuthentificationTools.checkFriendship(myId, idFriend))
				return ErrorJSON.serviceRefused("User" + idFriend + " is not a friend or you're", 0);
		
			FriendBD.removeFriend(myId, idFriend);
			retour.put("User", idFriend);
			retour.put("was removed from you friend list", "ok");
			retour.put("key",AuthentificationTools.getKey(myId));

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}

	public static JSONObject addFriendship(int id, String key) {
		int myId = 0;
		JSONObject retour = new JSONObject();
		try {
			if (id < 0 || key == null)
				return ErrorJSON.serviceRefused("Wrong Argument ", -1);
			myId = AuthentificationTools.getIdUserByKey(key);

			if(myId<0)
				return ErrorJSON.serviceRefused(" Unknows User", -1);
			if (!AuthentificationTools.userExists(id))
				return ErrorJSON.serviceRefused("Unknown User " + id, -1);

			if (!AuthentificationTools.checkSaissionExist(myId) )
				return ErrorJSON.serviceRefused(" you must login before adding a friend", -1);

			if (!AuthentificationTools.checkFriendship(myId, id))
				FriendBD.addFriend(myId, id);
			else
				return ErrorJSON.serviceAccepted(id+" already your friend",-1);
			retour.put("User" , myId);
			retour.put("friend", id );
			retour.put("login", AuthentificationTools.getLogin(id));
			retour.put("key", AuthentificationTools.getKey(myId));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retour;
	}
		
	public static JSONObject getFriendOfMine(String key) {
		int myId=-1;
		try {
			if (key == null)
				return ErrorJSON.serviceRefused(" Login please youre logged out", -1);
			myId = AuthentificationTools.getIdUserByKey(key);
			if(myId<0)
				return ErrorJSON.serviceRefused(" Uknwon user ", -1);
			if (!AuthentificationTools.userExists(myId))
				return ErrorJSON.serviceRefused(" unknown Logger", -1);

			if (!AuthentificationTools.checkSaissionExist(myId))
				return ErrorJSON.serviceRefused(" login please ....", -1);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return FriendBD.getListFiends(myId);

	}
}
