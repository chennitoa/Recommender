package application;

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
	
	private Scene entryScene;
	private Scene menuScene;
	private Scene settingsScene;
	
	
	public Main() {
		
	}
	
	/*
	 * Goes back to login screen
	 */
	public void logout() {
		displayEntryScene();
	}
	
	/*
	 * Diplays minimized entry scene with new stage
	 */
	public void displayEntryScene() {
		mainStage.close();
		mainStage = new Stage();
		mainStage.setScene(entryScene);
		mainStage.setResizable(false);
		mainStage.show();
	}
	
	/*
	 * Displays maxmized menu scene with new stage
	 */
	public void displayMenuScene(boolean createNewStage) {
		if (createNewStage) {
			mainStage.close();
			mainStage = new Stage();
		}
		mainStage.setMaximized(true);
		mainStage.setWidth(Screen.getPrimary().getBounds().getMaxX());
		mainStage.setHeight(Screen.getPrimary().getBounds().getMaxY());
		mainStage.setScene(menuScene);
		mainStage.show();
	}
	
	/*
	 * Displays settings scene
	 */
	public void displaySettingsScene() {
		mainStage.setScene(settingsScene);
		mainStage.show();
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
	        entryScene = new Scene(entryRoot);
	        entryScene.getStylesheets().add(getClass().getResource("../gui/style.css").toExternalForm());
	        entryController.setMain(this);
	        
	        FXMLLoader menuLoader = new FXMLLoader(getClass().getResource("../gui/MenuScreen.fxml"));
	        VBox menuRoot = menuLoader.load();
	        MenuScreen menuController = menuLoader.getController();
	        menuScene = new Scene(menuRoot);
	        menuScene.getStylesheets().add(getClass().getResource("../gui/style.css").toExternalForm());
	        menuController.setMain(this);
	        
	        FXMLLoader settingsLoader = new FXMLLoader(getClass().getResource("../gui/SettingsScreen.fxml"));
	        VBox settingsRoot = settingsLoader.load();
	        SettingsScreen settingsController = settingsLoader.getController();
	        settingsScene = new Scene(settingsRoot);
	        settingsScene.getStylesheets().add(getClass().getResource("../gui/style.css").toExternalForm());
	        settingsController.setMain(this);

	        mainStage = primaryStage;
	        
	        displayEntryScene();
	        
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
