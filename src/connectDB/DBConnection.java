package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static Connection conn = null;
	
	/*
	 * Singleton design
	 */
	private DBConnection(){ 
		String url = "jdbc:sqlite:password.db";
		try {
			conn = DriverManager.getConnection(url);
		}
		catch(SQLException e){
			System.out.println("Error connecting to database.");
			System.exit(1);
		}
	}
	
	/*
	 * If no connection in use, create the one and only. Otherwise just connect to the existing connection.
	 */
	public static Connection getConnection() {
		if(conn == null)
			new DBConnection();
		
		return conn;
	}
}
