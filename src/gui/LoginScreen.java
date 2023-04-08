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

	private LoginManager lm;
	private Main m;
	
	public LoginScreen() throws IOException {
		lm = LoginManager.getLoginManager();
	}
	
	@FXML
	public void initialize() {
		info.setVisible(false);
		
		firstAid.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			info.setTextFill(Color.color(0, 0, 0));
			info.setText("Hint: Enter default password \"p\" to continue");
			info.setVisible(true);
		});
		
		loginButton.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			String passwordAttempt = password.getCharacters().toString();
			if (lm.checkPassword(passwordAttempt)) {
				if (m != null) {
					if (lm.getFirstLogin()) {
						try {
							System.out.println("Changing Scenes!");
							m.changeScene("Menu");
						}
						catch (Exception e1) {
							e1.printStackTrace();
						}
					}
					else {
						try {
							System.out.println("Changing Scenes!");
							m.changeScene("Reset");
						}
						catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				}
				System.out.println("Login button clicked, password is correct!");
			}
			else {
				info.setTextFill(Color.color(1, 0, 0));
				info.setText("Password Incorrect: Try Again");
				info.setVisible(true);
				System.out.println("Login button clicked, wrong password!");
			}
		});
		
		passwordChange.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			m.changeScene("Reset");
		});
		
	}
	
	@Override
	public void setMain(Main m) {
		this.m = m;
	}
	
}
