package connectDB;

import java.sql.Connection;  
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;  
import java.sql.Statement;  


public class DBManager {
	private String fileName;
	
	
	public DBManager(String fileName){
		this.fileName = "jdbc:sqlite:" + fileName;
	}
	public void createTable() { //Just once, called in Create class
		String url = this.fileName;
		String sql = "CREATE TABLE IF NOT EXISTS passwordTable (\n"  
	                + " password text\n);";
	          
	        try {  
	            Connection conn = DriverManager.getConnection(url);  
	            Statement stmt = conn.createStatement();  
	            stmt.execute(sql);  
	        } 
	        catch (SQLException e) {  
	            System.out.println(e.getMessage());  
	        }  
	}  
	
	public void insert(String password) {  //Assumes there are no duplicate,
										//duplicate will be verified before calling this function
        Connection conn = null;  
        try {  
            conn = DriverManager.getConnection(this.fileName);  
        } catch (SQLException e) {  
            System.out.println(e.getMessage()); 
            return;
        }  
        //Connection established
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
		Connection conn = null;  
        try {  
            conn = DriverManager.getConnection(this.fileName);  
        } catch (SQLException e) {  
            System.out.println(e.getMessage()); 
            return;
        }  
        String sqlQuery = "SELECT * FROM passwordTable;";
//        String sqlQuery = "SELECT FROM passwordTable where password = '?';";
//        try{  
//            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);  
//            pstmt.setString(1, password);  
//            pstmt.executeUpdate();  
//        } catch (SQLException e) {  
//            System.out.println(e.getMessage());  
//            return;
//        }  
        
        Statement stmt  = conn.createStatement();  
        ResultSet rs    = stmt.executeQuery(sqlQuery);  
        while (rs.next()) {  
            System.out.println(rs.getString("password"));  
        }  
    } 
	
	public void deleteTable() { 
        String sqlDelete = "DROP TABLE IF EXISTS passwordTable;";
        try {  
            Connection conn = DriverManager.getConnection(this.fileName);  
            Statement stmt = conn.createStatement();  
            stmt.execute(sqlDelete);  
        } 
        catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
	}
}

	

