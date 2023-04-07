package login;

import java.io.FileNotFoundException;
import java.io.IOException;

public class LoginManager {
	private boolean isFirstLogin;
	private PasswordManager pw;
	
	public LoginManager() throws FileNotFoundException {
		pw = new PasswordManager();
		isFirstLogin = pw.isFirstLogin();
	}
	
	public boolean login(String enteredPassword) {
		return pw.checkPassword(enteredPassword);
	}
	
	/**
	 * Given the new password and that it is first time reseting the password,
	 * returns true if password is succesfully reset, false otherwise
	 */
	public boolean resetFirstPassword(String newPassword) throws IOException {
		if (isFirstLogin) {
			return pw.changePassword("p", newPassword);
		}
		else {
			return false;
		}
	}
	
	/**
	 * Resets the password given the old password and the new password,
	 * returns true if the password is succesfully reset, false otherwise
	 */
	public boolean resetPassword(String oldPassword, String newPassword) throws IOException {
		return pw.changePassword(oldPassword, newPassword);
	}
}
