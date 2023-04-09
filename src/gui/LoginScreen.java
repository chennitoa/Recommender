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

public class LoginScreen implements ApplicationScreen {
	
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
	private Main m;
	private boolean isFirstLogin;
	
	public LoginScreen() throws IOException {
		lM = LoginManager.getLoginManager();
		isFirstLogin = lM.getFirstLogin();
	}
	
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
		
		loginButton.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			String passwordAttempt = password.getCharacters().toString();
			password.clear();
			if (lM.checkPassword(passwordAttempt)) {
				if (m != null) {
					if (!isFirstLogin) {
						m.changeScene("Menu");
					}
					else {
						m.changeScene("Reset");
					}
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
			m.changeScene("Reset");
			password.clear();
		});
		
	}
	
	@Override
	public void setMain(Main m) {
		this.m = m;
	}
	
}
