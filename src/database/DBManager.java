package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class DBManager {
	
	private static DBManager dbM;
	
	private DBConnection dbConn;
	private Connection conn;
	
	private DBManager() {
		dbConn = DBConnection.getDBConnection();
		conn = dbConn.getConnection();
	}
	
	public static DBManager getDBManager() {
		if (dbM == null) {
			dbM = new DBManager();
		}
		return dbM;
	}
	
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

	

