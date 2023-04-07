package connectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;  


public class DBManager implements PasswordDAO{
	static Connection conn = DBConnection.getConnection();
	
	public void createInfoTable() { //Creating a that is related to the user
		String sql = "CREATE TABLE IF NOT EXISTS infoTable (stuID integer PRIMARY KEY, ID_FK integer references passwordTable(ID), "
				+ "name text, gpa double, major text, schoolName text\n);";
		try {  
            Statement stmt = conn.createStatement();  
            stmt.execute(sql);  
        } 
        catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  

	}
	
//	public void deleteTable() { //For testing purposes
//		String sql = "DROP TABLE passwordTable;";
//		String sql2 = "DROP TABLE infoTable;";
//		try {  
//            Statement stmt = conn.createStatement();  
//            stmt.execute(sql2);  
//        } 
//        catch (SQLException e) {  
//            System.out.println(e.getMessage());  
//        }  
//		try {  
//            Statement stmt = conn.createStatement();  
//            stmt.execute(sql);  
//        } 
//        catch (SQLException e) {  
//            System.out.println(e.getMessage());  
//        }  
//	}
	
	public void insertInfo(int stuID, String password, String name, double gpa, String major, String schoolName) throws SQLException { //Given a password, determine ID_FK
		DBManager obj = new DBManager();
		int ID_FK = obj.getID(password);
		String sqlInsert = "INSERT INTO infoTable values(?,?,?,?,?,?);";
		try{  
            PreparedStatement pstmt = conn.prepareStatement(sqlInsert);  
            pstmt.setInt(1, stuID);
            pstmt.setInt(2, ID_FK);
            pstmt.setString(3, name);
            pstmt.setDouble(4, gpa);
            pstmt.setString(5, major);
            pstmt.setString(6, schoolName);
            pstmt.executeUpdate();  
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
	}
	
	public void getInfo(int ID_FK) throws SQLException {
		String sqlSelect = "SELECT * FROM infoTable WHERE ID_FK = ?;";
		PreparedStatement stmt  = conn.prepareStatement(sqlSelect);  
		stmt.setInt(1, ID_FK);
        ResultSet rs    = stmt.executeQuery();  
        while (rs.next()) {  
        	System.out.println(rs.getInt("stuID"));
            System.out.println(rs.getString("name"));
            System.out.println(rs.getDouble("gpa"));
            System.out.println(rs.getString("major"));
            System.out.println(rs.getString("schoolName"));
        }  
	}
	
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
	
	public boolean passwordExists(String password) throws SQLException {
		String sqlSelect = "SELECT * FROM passwordTable where password = '" + password + "';";
		Statement stmt  = conn.createStatement();  
        ResultSet rs    = stmt.executeQuery(sqlSelect);  
        while (rs.next()) {  
            return true;  
        }  
		return false;
	}
	public void getAllPassword() throws SQLException {
        String sqlQuery = "SELECT * FROM passwordTable;";      
        Statement stmt  = conn.createStatement();  
        ResultSet rs    = stmt.executeQuery(sqlQuery);  
        while (rs.next()) {  
            System.out.println(rs.getString("password"));  
        }  
    } 
	
	public int getID(String password) throws SQLException {
		String sqlSelect = "SELECT ID FROM passwordTable WHERE password = '" + password + "';";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sqlSelect);
		return rs.getInt("ID");
	}
	
//	public void deleteAccount(String password) { //Delete faculty account
//        String sqlDelete = "DELETE FROM passwordTable WHERE password = '" + password + "';";
//        try {  
//            Statement stmt = conn.createStatement();  
//            stmt.execute(sqlDelete);  
//        } 
//        catch (SQLException e) {  
//            System.out.println(e.getMessage());  
//        }  
//	}
	
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

	

