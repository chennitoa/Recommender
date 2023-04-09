package application;
	
import java.util.HashMap;

import gui.LoginScreen;
import gui.MenuScreen;
import gui.ResetScreen;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	private Stage mainStage;
	private HashMap<String, Scene> sceneMap;
	
	public Main() {
		sceneMap = new HashMap<String, Scene>();
	}
	
	public void logout() {
		mainStage.close();
		System.exit(0);
	}
	
	public void changeScene(String sceneName) {
		if (sceneName.equals("Menu")) {
			mainStage.close();
			mainStage = new Stage();
		}
		if (sceneMap.containsKey(sceneName)) {
			mainStage.setScene(sceneMap.get(sceneName));
			mainStage.show();
		}
		else {
			System.out.println("No such scene");
		}
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("../gui/LoginScreen.fxml"));
			VBox loginRoot = loginLoader.load();
			LoginScreen loginController = loginLoader.getController();
	        Scene loginScene = new Scene(loginRoot);
	        loginScene.getStylesheets().add(getClass().getResource("../gui/style.css").toExternalForm());
	        loginController.setMain(this);
	        
	        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("../gui/MenuScreen.fxml"));
	        VBox menuRoot = menuLoader.load();
	        MenuScreen menuController = menuLoader.getController();
	        Scene menuScene = new Scene(menuRoot);
	        menuScene.getStylesheets().add(getClass().getResource("../gui/style.css").toExternalForm());
	        menuController.setMain(this);
	        
	        FXMLLoader resetLoader = new FXMLLoader(getClass().getResource("../gui/ResetScreen.fxml"));
	        VBox resetRoot = resetLoader.load();
	        ResetScreen resetController = resetLoader.getController();
	        Scene resetScene = new Scene(resetRoot);
	        resetScene.getStylesheets().add(getClass().getResource("../gui/style.css").toExternalForm());
	        resetController.setMain(this);
	        
	        sceneMap.put("Login", loginScene);
	        sceneMap.put("Menu", menuScene);
	        sceneMap.put("Reset", resetScene);

	        mainStage = primaryStage;
	        
			mainStage.setScene(loginScene);
			mainStage.show();	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
