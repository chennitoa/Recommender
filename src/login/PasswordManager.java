package login;

import java.sql.SQLException;

import database.DBManager;

final class PasswordManager {
	
	private static PasswordManager pw;
	
	private String password;
	private boolean isFirstTimePassword;
	private DBManager dbM;
	
	
	/*
	 * Singleton design, only one password manager will be used throughout the life of this application
	 */
	private PasswordManager() throws SQLException {
		dbM = DBManager.getDBManager();
		dbM.queryQuiet(
				"CREATE TABLE IF NOT EXISTS password ("
				+ "Lock char(1) not null DEFAULT 'X',"
				+ "passwordString string NOT NULL,"
				+ "constraint PK_T1 PRIMARY KEY (Lock),"
				+ "constraint CK_T1_Locked CHECK (Lock='X')"
				+ ");"
		);
		password = dbM.query("SELECT * FROM password;").getString("passwordString");
		if (password == null) {
			isFirstTimePassword = true;
		}
	}
	
	/*
	 * Returns the singleton instance of PasswordManager, creating one if one does not exist already
	 */
	public static PasswordManager getPasswordManager() {
		if (pw == null) {
			try {
				pw = new PasswordManager();
			}
			catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return pw;
	}
	/*
	 * Returns true if no password has been set, false otherwise
	 */
	public boolean isFirstLogin() {
		return isFirstTimePassword;
	}
	
	/*
	 * Returns the password as a string
	 */
	public String getPassword() {
		return password;
	}
	
	/*
	 * Given newPassword, inserts new password into SQL database and changes password String in object
	 */
	public void setPassword(String newPassword) {
		dbM.query("DELETE FROM password;");
		password = newPassword;
		dbM.query(String.format("INSERT INTO password (passwordString) VALUES ('%s');", newPassword));
	}

}
