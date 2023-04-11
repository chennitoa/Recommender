package gui.entry;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import login.LoginManager;

public class LoginTab {
	
	@FXML
	private PasswordField password;
	@FXML
	private Button loginButton;
	@FXML
	private Label firstAid;
	@FXML
	private Label info;
	@FXML
	private Label passwordChange;
	
	private LoginManager lM;
	private EntryScreen entryScreen;
	private boolean isFirstLogin;
	
	/*
	 * Grabs singleton instance of LoginManager and checks whether it is the first login
	 */
	public LoginTab() throws IOException {
		lM = LoginManager.getLoginManager();
		isFirstLogin = lM.getFirstLogin();
	}
	
	/*
	 * Adds functionality to the help, login, and reset password buttons
	 */
	@FXML
	public void initialize() {
		info.setVisible(false);
		
		if (!isFirstLogin) {
			firstAid.setVisible(false);
		}
		
		firstAid.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			info.setTextFill(Color.color(0, 0, 0));
			info.setText("Hint: Enter default password \"p\" to continue");
			info.setVisible(true);
		});
		
		/*
		 * Checks if password is correct, redirects to Menu normally but to Reset if it is the first time logging in
		 * Modifies text at bottom that gives information
		 */
		loginButton.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			String passwordAttempt = password.getCharacters().toString();
			password.clear();
			if (lM.checkPassword(passwordAttempt)) {
				if (!isFirstLogin) {
					entryScreen.displayMenuScreen();
				}
				else {
					entryScreen.displayResetScreen();
				}
				info.setVisible(false);
				firstAid.setVisible(false);
				isFirstLogin = false;
			}
			else {
				info.setTextFill(Color.color(1, 0, 0));
				info.setText("Password Incorrect: Try Again");
				info.setVisible(true);
			}
		});
		
		passwordChange.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			entryScreen.displayResetScreen();
			password.clear();
		});
		
	}
	
	/*
	 * Sets EntryScreen for navigation
	 */
	public void setEntryScreen(EntryScreen entryScreen) {
		this.entryScreen = entryScreen;
	}
	
}
