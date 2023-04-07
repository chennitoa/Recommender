package gui;

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
	
	private LoginManager loginM;
	private Main m;
	
	public ResetScreen() {
		try {
			loginM = new LoginManager();
		}
		catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	@FXML
	public void initialize() {
		
		info.setVisible(false);
		
		reset.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			
			String oldPass = oldPassword.getCharacters().toString();
			String newPass = newPassword.getCharacters().toString();
			String newConf = newConfirm.getCharacters().toString();
			
			if (!newPass.equals(newConf)) {
				info.setText("New Passwords Are Different: Try Again");
			}
			else {
				boolean b = false;
				try {
					b = loginM.resetPassword(oldPass, newPass);
				}
				catch (Exception e1) {
					e1.printStackTrace();
				}
				if (!b) {
					info.setVisible(false);
					info.setTextFill(Color.color(1, 0, 0));
					info.setText("Password Incorrect: Try Again");
					info.setVisible(true);
				}
				else {
					m.changeScene("Login");
				}
			}
			
		});
	}

	@Override
	public void setMain(Main m) {
		this.m = m;
	}

}
