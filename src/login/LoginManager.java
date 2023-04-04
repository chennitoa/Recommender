package login;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

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
	
	
	public static void main(String args[]) throws IOException, FileNotFoundException {
		LoginManager loginSession = new LoginManager();
		Scanner scnr = new Scanner(System.in);
		
		System.out.print("Login password: ");
		System.out.flush();
		String password = scnr.nextLine();
		System.out.println("Result of logging in is " + loginSession.login(password));
		
		System.out.print("Reset your password: Write your old password: ");
		System.out.flush();
		String oldPassword = scnr.nextLine();
		System.out.print("Write your new password: ");
		System.out.flush();
		String newPassword = scnr.nextLine();
		System.out.println("Result of resetting password is " + loginSession.resetPassword(oldPassword, newPassword));
		scnr.close();
	}
}
