package gui.entry;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import login.LoginManager;

public class ResetTab {
	
	@FXML
	private PasswordField oldPassword;
	@FXML
	private PasswordField newPassword;
	@FXML
	private PasswordField newConfirm;
	@FXML
	private Button reset;
	@FXML
	private Label info;
	
	private LoginManager lM;
	private EntryScreen entryScreen;
	private boolean isFirstReset;
	
	/*
	 * Grabs the singleton instance of LoginManager and checks whether it is the first login
	 */
	public ResetTab() {
		lM = LoginManager.getLoginManager();
		isFirstReset = lM.getFirstLogin();
	}
	
	/*
	 * Adds functionality to the reset button 
	 */
	@FXML
	public void initialize() {
		
		info.setVisible(false);
		
		if (isFirstReset) {
			oldPassword.setVisible(false);
		}
		
		/*
		 * Checks if the old password is correct, and the two new passwords are the same
		 */
		reset.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			
			String oldPass = oldPassword.getCharacters().toString();
			String newPass = newPassword.getCharacters().toString();
			String newConf = newConfirm.getCharacters().toString();
			
			oldPassword.clear();
			newPassword.clear();
			newConfirm.clear();
			
			if (!newPass.equals(newConf)) {
				info.setVisible(false);
				info.setTextFill(Color.color(1, 0, 0));
				info.setText("New Passwords Are Different: Try Again");
				info.setVisible(true);
			}
			else {
				if (!isFirstReset) {
					boolean resetSuccessful = false;
					try {
						resetSuccessful = lM.resetPassword(oldPass, newPass);
					}
					catch (Exception e1) {
						e1.printStackTrace();
					}
					if (!resetSuccessful) {
						info.setVisible(false);
						info.setTextFill(Color.color(1, 0, 0));
						info.setText("Password Incorrect: Try Again");
						info.setVisible(true);
					}
					else {
						entryScreen.displayLoginScreen();
					}
				}
				else {
					try {
						lM.resetFirstPassword(newPass);
						isFirstReset = false;
						oldPassword.setVisible(true);
						
						entryScreen.displayLoginScreen();
					}
					catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
			
		});
	}
	
	/*
	 * Sets EntryScreen for navigation
	 */
	public void setEntryScreen(EntryScreen entryScreen) {
		this.entryScreen = entryScreen;
	}

}
