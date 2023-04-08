package connectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;  


public class DBManager{
	static Connection conn = DBConnection.getConnection();
	
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

	

