package login;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

final class PasswordManager {
	private String password;
	private File passwordFile;
	private boolean isFirstTimePassword;
	
	PasswordManager() throws FileNotFoundException {
		passwordFile = new File("password.txt");
		if (passwordFile.exists()) {
			Scanner scnr = new Scanner(passwordFile);
			password = scnr.nextLine();
			scnr.close();
			isFirstTimePassword = false;
		}
		else {
			password = "p";
			isFirstTimePassword = true;
		}
	}
	
	boolean isFirstLogin() {
		return isFirstTimePassword;
	}
	
	boolean checkPassword(String enteredPassword) {
		return (enteredPassword.equals(password));
	}
	
	boolean changePassword(String oldPassword, String newPassword) throws IOException {
		if (oldPassword.equals(password)) {
			password = newPassword;
			if (isFirstTimePassword) {
				passwordFile.createNewFile();
			}
			PrintWriter passwordWriter = new PrintWriter(passwordFile);
			passwordWriter.print(password);
			passwordWriter.close();
			return true;
		}
		else {
			return false;
		}
	}
}
