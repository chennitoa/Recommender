package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class DBManager {
	
	private static DBManager dbM;
	
	private DBConnection dbConn;
	private Connection conn;
	
	/*
	 * Connects to singleton instance of DBConnection and gets its connection
	 */
	private DBManager() {
		dbConn = DBConnection.getDBConnection();
		conn = dbConn.getConnection();
	}
	
	/*
	 * Get singleton instance of DBManager, creates a new instance if one does not exist
	 */
	public static DBManager getDBManager() {
		if (dbM == null) {
			dbM = new DBManager();
		}
		return dbM;
	}
	
	/*
	 * Runs query on database with given string
	 */
	public ResultSet query(String sqlQuery) {
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sqlQuery);
			return rs;
		}
		catch (SQLException e) {
			
        }
		return null;
	}
	
}

	

