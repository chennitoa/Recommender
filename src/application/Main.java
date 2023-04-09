package application;
	
import java.util.HashMap;

import gui.LoginScreen;
import gui.MenuScreen;
import gui.ResetScreen;
import gui.SettingsScreen;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	private Stage mainStage;
	private HashMap<String, Scene> sceneMap;
	
	public Main() {
		sceneMap = new HashMap<String, Scene>();
	}
	
	/*
	 * Closes application
	 */
	public void logout() {
		mainStage.close();
		System.exit(0);
	}
	
	/*
	 * Changes scene to given scene name
	 */
	public void changeScene(String sceneName) {
		if (sceneMap.containsKey(sceneName)) {
			if (!sceneName.equals("Login") || !sceneName.equals("Reset")) {
			}
			mainStage.setScene(sceneMap.get(sceneName));
			mainStage.show();
		}
		else {
			System.out.println("No such scene");
		}
	}
	
	/*
	 * Closes current window and opens a new window with maxmized window size
	 */
	public void changeSceneWithNewWindow(String sceneName) {
		mainStage.close();
		mainStage = new Stage();
		mainStage.setMaximized(true);
		mainStage.setWidth(Screen.getPrimary().getBounds().getMaxX());
		mainStage.setHeight(Screen.getPrimary().getBounds().getMaxY());
		if (sceneMap.containsKey(sceneName)) {
			mainStage.setScene(sceneMap.get(sceneName));
			mainStage.show();
		}
		else {
			System.out.println("No such scene");
		}
	}
	
	/*
	 * Loads all pages, links them to names and opens the primary stage
	 */
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
	        
	        FXMLLoader settingsLoader = new FXMLLoader(getClass().getResource("../gui/SettingsScreen.fxml"));
	        VBox settingsRoot = settingsLoader.load();
	        SettingsScreen settingsController = settingsLoader.getController();
	        Scene settingsScene = new Scene(settingsRoot);
	        settingsScene.getStylesheets().add(getClass().getResource("../gui/style.css").toExternalForm());
	        settingsController.setMain(this);
	        
	        sceneMap.put("Login", loginScene);
	        sceneMap.put("Menu", menuScene);
	        sceneMap.put("Reset", resetScene);
	        sceneMap.put("Settings", settingsScene);

	        mainStage = primaryStage;
	        
			mainStage.setScene(loginScene);
			mainStage.setResizable(false);
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
