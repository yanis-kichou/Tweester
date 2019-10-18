package test;


import org.json.JSONException;

import bd.CommentBD;
import services.Comment;
import tools.AuthentificationTools;


public class testComments {

public static void main(String[] args) throws JSONException {
	Comment.addComment("6F0n3zMEssJ0ibHJ3RWEN9BAnN9feEwt", " je suis yanis  coucou tout le monde ");
	System.out.println(Comment.listeMessage("6F0n3zMEssJ0ibHJ3RWEN9BAnN9feEwt", AuthentificationTools.getIdUser((TestCreateUser.us1.getLogin()))));
	Comment.removeComment("6F0n3zMEssJ0ibHJ3RWEN9BAnN9feEwt", " je suis yanis  coucou tout le monde ");
	System.out.println(Comment.listeMessage("6F0n3zMEssJ0ibHJ3RWEN9BAnN9feEwt", AuthentificationTools.getIdUser((TestCreateUser.us1.getLogin()))));
	
	try {
		System.out.println(CommentBD.getComments());
	} catch (JSONException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

}
	