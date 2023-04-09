package gui;

import java.io.IOException;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import login.LoginManager;

public class ResetScreen implements ApplicationScreen {
	
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
	private Main m;
	private boolean isFirstReset;
	
	public ResetScreen() {
		try {
			lM = LoginManager.getLoginManager();
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
		isFirstReset = lM.getFirstLogin();
	}
	
	@FXML
	public void initialize() {
		
		info.setVisible(false);
		
		if (isFirstReset) {
			oldPassword.setVisible(false);
		}
		
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
						m.changeScene("Login");
					}
				}
				else {
					try {
						lM.resetFirstPassword(newPass);
						isFirstReset = false;
						oldPassword.setVisible(true);
						m.changeScene("Login");
					}
					catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
			
			
			
		});
	}
	
	@Override
	public void setMain(Main m) {
		this.m = m;
	}

}
