package connectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;  


public class DBManager implements PasswordDAO{
	static Connection conn = DBConnection.getConnection();
	
	public void createTable() { 
		String sql = "CREATE TABLE IF NOT EXISTS passwordTable (\n"  
	                + " password text\n);";
	          
	        try {  
	            Statement stmt = conn.createStatement();  
	            stmt.execute(sql);  
	        } 
	        catch (SQLException e) {  
	            System.out.println(e.getMessage());  
	        }  
	}  
	
	public void insert(String password) {  //Assumes there are no duplicate,
										//duplicate will be verified before calling this function
        String sqlInsert = "INSERT INTO passwordTable(password) VALUES(?);";
        
        try{  
            PreparedStatement pstmt = conn.prepareStatement(sqlInsert);  
            pstmt.setString(1, password);  
            pstmt.executeUpdate();  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
        

	}
	public void query() throws SQLException {
        String sqlQuery = "SELECT * FROM passwordTable;";      
        Statement stmt  = conn.createStatement();  
        ResultSet rs    = stmt.executeQuery(sqlQuery);  
        while (rs.next()) {  
            System.out.println(rs.getString("password"));  
        }  
    } 
	
	public void deleteTable() { 
        String sqlDelete = "DROP TABLE IF EXISTS passwordTable;";
        try {  
            Statement stmt = conn.createStatement();  
            stmt.execute(sqlDelete);  
        } 
        catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
	}
}

	

