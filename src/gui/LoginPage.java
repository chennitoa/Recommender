package gui;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class LoginPage {
	private Scene loginScene;
	
	public LoginPage() throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
		loginScene = new Scene(root, 600, 200);
	}
	
	public Scene getLoginPage() {
		return loginScene;
	}
	
}
