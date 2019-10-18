package test;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import bd.Database;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MongoDatabase mDB= Database.getMongoDBConnetion();
		MongoCollection<Document> me=mDB.getCollection("Comments");
		me.drop();
	}

}
