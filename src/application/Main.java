package application;
	
import java.util.HashMap;

import gui.MenuScreen;
import gui.SettingsScreen;
import gui.entry.EntryScreen;
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
	 * Goes back to login screen
	 */
	public void logout() {
		changeLoginWindow();
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
	 * Upon clicking logout button, the login screen will pop up
	 */
	public void changeLoginWindow(){
		mainStage.close();
		mainStage = new Stage();
		mainStage.setMaximized(false);
		if (sceneMap.containsKey("Login")) {
			mainStage.setScene(sceneMap.get("Login"));
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
			
			FXMLLoader entryLoader = new FXMLLoader(getClass().getResource("../gui/entry/EntryScreen.fxml"));
			VBox entryRoot = entryLoader.load();
			EntryScreen entryController = entryLoader.getController();
	        Scene entryScene = new Scene(entryRoot);
	        entryScene.getStylesheets().add(getClass().getResource("../gui/style.css").toExternalForm());
	        entryController.setMain(this);
	        
	        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("../gui/MenuScreen.fxml"));
	        VBox menuRoot = menuLoader.load();
	        MenuScreen menuController = menuLoader.getController();
	        Scene menuScene = new Scene(menuRoot);
	        menuScene.getStylesheets().add(getClass().getResource("../gui/style.css").toExternalForm());
	        menuController.setMain(this);
	        
	        FXMLLoader settingsLoader = new FXMLLoader(getClass().getResource("../gui/SettingsScreen.fxml"));
	        VBox settingsRoot = settingsLoader.load();
	        SettingsScreen settingsController = settingsLoader.getController();
	        Scene settingsScene = new Scene(settingsRoot);
	        settingsScene.getStylesheets().add(getClass().getResource("../gui/style.css").toExternalForm());
	        settingsController.setMain(this);
	        
	        sceneMap.put("Entry", entryScene);
	        sceneMap.put("Menu", menuScene);
	        sceneMap.put("Settings", settingsScene);

	        mainStage = primaryStage;
	        
			mainStage.setScene(entryScene);
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
