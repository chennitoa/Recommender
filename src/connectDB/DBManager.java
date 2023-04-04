package connectDB;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.SQLException;  
import java.sql.Statement;  


public class DBManager {
	
	
	public void createTable(String fileName) { //Just once
		String url = "jdbc:sqlite:" + fileName;
		String sql = "CREATE TABLE IF NOT EXISTS passwordTable (\n"  
	                + " password text PRIMARY KEY\n" 
	                + ");";  
	          
	        try {  
	            Connection conn = DriverManager.getConnection(url);  
	            Statement stmt = conn.createStatement();  
	            stmt.execute(sql);  
	        } 
	        catch (SQLException e) {  
	            System.out.println(e.getMessage());  
	        }  
	}  
	
}
