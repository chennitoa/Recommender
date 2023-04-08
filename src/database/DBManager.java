package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;  


public class DBManager{
	private static Connection conn;
	
	public void createTable() { 
		String sql = "CREATE TABLE IF NOT EXISTS passwordTable (ID integer PRIMARY KEY AUTOINCREMENT, password text\n);";
	          
	        try {  
	            Statement stmt = conn.createStatement();  
	            stmt.execute(sql);  
	        } 
	        catch (SQLException e) {  
	            System.out.println(e.getMessage());  
	        }  
	}  
}

	

