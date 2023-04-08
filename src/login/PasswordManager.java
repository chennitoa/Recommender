package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.DBConnection;

final class PasswordManager {
	
	private static Connection conn = DBConnection.getConnection();
	private static PasswordManager pw;
	
	private String password;
	private boolean isFirstTimePassword;
	
	
	/*
	 * Singleton design, only one login manager will be used throughout the life of this application
	 */
	private PasswordManager() {
		
	}
	
	/*
	 * Returns the singleton instance of PasswordManager, creating one if one does not exist already
	 */
	public static PasswordManager getPasswordManager() {
		if (pw == null) {
			pw = new PasswordManager();
		}
		return pw;
	}
	
	/*
	 * This method returns true if the password entered exists in the database, isFirstTimePassword will be set to false
	 * This method returns false otherwise and setting isFirstTimePassword to true and setting password to p. 
	 */
	boolean passwordExists(String password) throws SQLException {
		String sqlSelect = "SELECT * FROM passwordTable where password = '" + password + "';";
		Statement stmt  = conn.createStatement();  
        ResultSet rs    = stmt.executeQuery(sqlSelect);  
        while (rs.next()) {  
        	isFirstTimePassword = false;
        	password = rs.getString("password");
            return true;  
        }  
        isFirstTimePassword = true;
        password = "p";
		return false;
	}
	
	/*
	 * Returns true if it is first time, false otherwise
	 */
	boolean isFirstLogin() {
		return isFirstTimePassword;
	}
	
	/*
	 * If it is not the first time login, this function will 
	 * be used to check if the password entered matches the password in db or not.
	 */
	boolean checkPassword(String enteredPassword) {
		return enteredPassword.equals(this.password);
	}
	
	/*
	 * Returns the password.
	 */
	String getPassword() {
		return this.password;
	}
	
	/*
	 * Given a newPassword, we update the database.
	 */
	void changePassword(String newPassword){
		String sqlUpdate = "UPDATE passwordTable SET password = ?";
		try{  
            PreparedStatement pstmt = conn.prepareStatement(sqlUpdate);  
            pstmt.setString(1, newPassword); 
            pstmt.executeUpdate();  
		} 
		catch (SQLException e) {  
            System.out.println(e.getMessage());  
        }  
	}
}
