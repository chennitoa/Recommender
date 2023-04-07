package application;
	
import java.util.HashMap;

import gui.LoginScreen;
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
	
	public void changeScene(String sceneName) throws Exception {
		if (sceneName.equals("Menu")) {
			mainStage.close();
			mainStage = new Stage();
		}
		System.out.println("Changing to " + sceneName);
		if (sceneMap.containsKey(sceneName)) {
			mainStage.setScene(sceneMap.get(sceneName));
			mainStage.show();
		}
		else {
			throw new Exception("No such scene");
		}
	}
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
			FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("../gui/LoginScreen.fxml"));
			VBox loginRoot = loginLoader.load();
			LoginScreen loginController = loginLoader.getController();
			
	        Scene loginScene = new Scene(loginRoot);
	        loginController.setMain(this);
	        
	        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("../gui/MenuScreen.fxml"));
	        VBox menuRoot = menuLoader.load();
	        
	        Scene menuScene = new Scene(menuRoot);
	        
	        sceneMap.put("Login", loginScene);
	        sceneMap.put("Menu", menuScene);

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
