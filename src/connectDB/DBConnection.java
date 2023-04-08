package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static Connection conn = null;
	
	
	private DBConnection(){ //Singleton design pattern
		String url = "jdbc:sqlite:password.db";
		try {
			conn = DriverManager.getConnection(url);
		}
		catch(SQLException e){
			System.out.println("Error connecting to database.");
			System.exit(1);
		}
	}
	
	public static Connection getConnection() {
		if(conn == null)
			new DBConnection();
		
		return conn;
	}
}
