package gui;

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import login.LoginManager;

public class LoginScreen {
	
	@FXML
	PasswordField password;
	@FXML
	Button loginButton;
	@FXML
	Label firstAid;
	@FXML
	Label info;
	@FXML
	Label passwordChange;
	
	VBox root;
	LoginManager loginM;
	
	public LoginScreen() throws IOException {
		try {
			loginM = new LoginManager();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
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
			if (loginM.login(passwordAttempt)) {
				//TODO: Redirect to next screen
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
			//TODO: Change to password reset scene
		});
		
	}
	
}
