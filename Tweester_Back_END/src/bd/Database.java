package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Database {
	private DataSource dataSource;
	private static Database database=null;
	
	public Database(String jndiname) throws SQLException {
		try {
			dataSource = (DataSource) new InitialContext().lookup("java:comp/env/" + jndiname);
		} catch (NamingException e) {
			throw new SQLException(jndiname + " is missing in JNDI! : " + e.getMessage());
		}
	}

	public Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public static Connection getMySQLConnection() throws SQLException {
		if (DBStatic.mysqlpooling == false) {
			return (DriverManager.getConnection("jdbc:mysql://" + DBStatic.mysql_host + "/" + DBStatic.mysql_db,
					DBStatic.mysql_username, DBStatic.mysql_password));
		}

		else {
			if (database == null) {
				database = new Database("jdbc/"+DBStatic.mysql_db);
			}
			return (database.getConnection());

		}

	}
	
	public static MongoDatabase getMongoDBConnetion() {
		
		com.mongodb.client.MongoClient mongo = MongoClients.create(DBStatic.mongo_URL);
		MongoDatabase mDB= mongo.getDatabase(DBStatic.mongo_bd);
		return mDB;
	}
	
}
