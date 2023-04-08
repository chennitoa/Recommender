package login;

import java.io.IOException;

public class LoginManager {
	
	private static LoginManager lm;
	
	private boolean isFirstLogin;
	private PasswordManager pw;
	
	
	/*
	 * Singleton design, only one login manager will be used throughout the life of this application
	 */
	private LoginManager() {
		pw = PasswordManager.getPasswordManager();
		isFirstLogin = pw.isFirstLogin();
	}
	
	/*
	 * Returns the singleton instance of LoginManager, creating one if one does not exist already
	 */
	public static LoginManager getLoginManager() {
		if (lm == null) {
			lm = new LoginManager();
		}
		return lm;
	}
	
	/*
	 * Checks if the password is correct, returns true if correct, false otherwise
	 */
	public boolean checkPassword(String enteredPassword) {
		return enteredPassword.equals(pw.getPassword());
	}
	
	/*
	 * Returns true if it is the first time logging in, false otherwise
	 */
	public boolean getFirstLogin() {
		return isFirstLogin;
	}
	
	/**
	 * Given the new password and that it is first time reseting the password,
	 * returns true if password is succesfully reset, false otherwise
	 */
	public void resetFirstPassword(String newPassword) throws IOException {
		pw.setPassword(newPassword);
	}
	
	/**
	 * Resets the password given the old password and the new password,
	 * returns true if the password is succesfully reset, false otherwise
	 */
	public boolean resetPassword(String oldPassword, String newPassword) throws IOException {
		if (checkPassword(oldPassword)) {
			pw.setPassword(newPassword);
			return true;
		}
		return false;
	}
}
