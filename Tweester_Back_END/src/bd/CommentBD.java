package bd;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;


import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import tools.AuthentificationTools;

public class CommentBD{

	public static void addCommentBD(int myId, String text) {
		LocalDateTime date=LocalDateTime.now();
		
		MongoDatabase mDB= Database.getMongoDBConnetion();
		MongoCollection<Document> me=mDB.getCollection("Comments");
		String login=AuthentificationTools.getLogin(myId);
		Document query = new Document();
		query.append("id_User", myId);
		query.append("message", text);
		query.append("date", date);
		query.append("login", login);
		query.append("key", AuthentificationTools.initRandomKey());
		
		List <Document> comments =new ArrayList<>();
		
		query.append("comments",comments);
		
		me.insertOne(query);
	
	}
	public static void RemoveCommentBD(int myId, String key) {
		
		MongoDatabase mDB= Database.getMongoDBConnetion();
		MongoCollection<Document> me=mDB.getCollection("Comments");
		
		Document query = new Document();
		query.append("id_User", myId);
		query.append("key", key);
		
		
		
		me.deleteOne(query);
	
	}
	
	public static JSONObject getComments(int myId) throws JSONException{ 
		MongoDatabase mDB= Database.getMongoDBConnetion();
		MongoCollection<Document> mybase=mDB.getCollection("Comments");
		
		Document doc=new Document();
		doc.append("id_User", myId);
		
		List<JSONObject> message =new ArrayList<>();
		
		MongoCursor<Document > resultat=(MongoCursor<Document>)mybase.find(doc).iterator();
		while (resultat.hasNext()) {
			Document d=resultat.next();
			JSONObject ob=new JSONObject();
			ob.put("user",d.get("id_User"));
			ob.put("message ",d.get("message"));
			ob.put("date",d.get("date"));
			ob.put("login", d.get("login"));
			ob.put("key", d.get("key"));
			message.add(ob);		}
		return new JSONObject().put("comments", message );
	}
	
	public static JSONObject getComments() throws JSONException{ 
		MongoDatabase mDB= Database.getMongoDBConnetion();
		MongoCollection<Document> mybase=mDB.getCollection("Comments");
		
		
		List<JSONObject> message =new ArrayList<>();
		
		MongoCursor<Document > resultat=(MongoCursor<Document>)mybase.find().iterator();
		while (resultat.hasNext()) {
			Document d=resultat.next();
			JSONObject ob=new JSONObject();
			ob.put("user",d.get("id_User"));
			ob.put("message ",d.get("message"));
			ob.put("date",d.get("date"));
			ob.put("login", d.get("login"));

			message.add(ob);		}
		return new JSONObject().put("Comments", message );
	}
	

}
