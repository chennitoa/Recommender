package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	private static DBConnection dbConn;
	
	private Connection conn;
	
	/*
	 * Singleton design, the connection and passwordTable will be established just once.
	 */
	private DBConnection() { 
		String url = "jdbc:sqlite:password.db";
		try {
			conn = DriverManager.getConnection(url);
		}
		catch(SQLException e) {
			System.out.println("Error connecting to database.");
			System.exit(1);
		}
	}
	
	/*
	 * Returns singleton instance of DBConnection class, creates one if not already present
	 */
	public static DBConnection getDBConnection() {
		if (dbConn == null) {
			dbConn = new DBConnection();
		}
		return dbConn;
	}
	
	/*
	 * Returns the Connection
	 */
	public Connection getConnection() {
		return conn;
	}
	
	public static void main(String args[]) {
		DBConnection dbConn = DBConnection.getDBConnection();
		Connection conn = dbConn.getConnection();
	}
	
}
