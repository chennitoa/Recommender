package connectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;  


public class DBManager implements PasswordDAO{
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
	
	public void storePassword(String password) {  //Assumes there are no duplicate,
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
	public void getAllPassword() throws SQLException {
        String sqlQuery = "SELECT * FROM passwordTable;";      
        Statement stmt  = conn.createStatement();  
        ResultSet rs    = stmt.executeQuery(sqlQuery);  
        while (rs.next()) {  
//        	System.out.println(rs.getInt("ID"));
            System.out.println(rs.getString("password"));  
        }  
    } 
	
	public void deleteAccount() { 
        String sqlDelete = "DROP TABLE IF EXISTS passwordTable;";
        try {  
            Statement stmt = conn.createStatement();  
            stmt.execute(sqlDelete);  
        } 
        catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
	}
	
	public void changePassword(String oldPassword, String newPassword) {
		String sqlUpdate = "UPDATE passwordTable SET password = ? WHERE password = ?";
		try{  
            PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);  
            pstmt.setString(1, newPassword); 
            pstmt.setString(2, oldPassword);
            pstmt.executeUpdate();  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
	}
	
	public void deleteEntry(String pw) {
		String sqlDelete = "DELETE FROM passwordTable WHERE password = ?;";
		try{  
            PreparedStatement pstmt = conn.prepareStatement(sqlDelete);  
            pstmt.setString(1, pw);  
            pstmt.executeUpdate();  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
	}
}

	

