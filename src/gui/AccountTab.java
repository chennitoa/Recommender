package gui;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import login.LoginManager;

public class AccountTab {
	
	@FXML
	private PasswordField oldPassword;
	@FXML
	private PasswordField newPassword;
	@FXML
	private PasswordField newConfirm;
	@FXML
	private Button reset;
	@FXML
	private Button logout;
	@FXML
	private Label info;
	
	private LoginManager lM;
	private Main m;
	
	public AccountTab() {
		lM = LoginManager.getLoginManager();
	}
	
	public void initialize() {
		
		info.setVisible(false);
		
		reset.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
		
			String oldPass = oldPassword.getCharacters().toString();
			String newPass = newPassword.getCharacters().toString();
			String newConf = newConfirm.getCharacters().toString();
			
			oldPassword.clear();
			newPassword.clear();
			newConfirm.clear();
			
			if (!newPass.equals(newConf)) {
				info.setText("New Passwords Are Different: Try Again");
			}
			else {
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
					info.setVisible(false);
					info.setTextFill(Color.color(0, 1, 0));
					info.setText("Password Succesfully Reset");
					info.setVisible(true);
				}
			}
		});
		
		logout.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			m.logout();
		});
		
	}
	
	public void setMain(Main m) {
		this.m = m;
	}
	
	
}
