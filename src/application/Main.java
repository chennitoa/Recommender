package application;

import gui.MenuScreen;
import gui.create.CreateScreen;
import gui.entry.EntryScreen;
import gui.settings.SettingsScreen;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Screen;
import javafx.stage.Stage;
import letter.LetterInfo;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	private Stage mainStage;
	
	private String styleCSS;
	
	private FXMLLoader entryLoader;
	private Scene entryScene;
	private EntryScreen entryController;
	
	private FXMLLoader menuLoader;
	private Scene menuScene;
	private MenuScreen menuController;
	
	private FXMLLoader settingsLoader;
	private Scene settingsScene;
	private SettingsScreen settingsController;
	
	
	private FXMLLoader createLoader;
	private Scene createScene;
	private CreateScreen createController;
	
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
		mainStage.setTitle("Recommender Login");
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
		mainStage.setTitle("Recommender");
		mainStage.show();
	}
	
	/*
	 * Displays settings scene
	 */
	public void displaySettingsScene() {
		mainStage.setScene(settingsScene);
		mainStage.show();
	}
	
	public void displayCreateScene() {
		mainStage.setScene(createScene);
		createController.displayStudentTab();
		mainStage.show();
	}
	
	public void displayLetterScene(LetterInfo letterInfo) {
		return;
	}
	
	/*
	 * Loads all pages, links them to names and opens the primary stage
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			
			styleCSS = getClass().getResource("../gui/style.css").toExternalForm();
			
			//Loading entry page
			entryLoader = new FXMLLoader(getClass().getResource("../gui/entry/EntryScreen.fxml"));
			
			VBox entryRoot = entryLoader.load();
			entryScene = new Scene(entryRoot);
	        entryScene.getStylesheets().add(styleCSS);
	        
	        entryController = entryLoader.getController();
	        entryController.setMain(this);
	        
	        
	        //Loading menu page
	        menuLoader = new FXMLLoader(getClass().getResource("../gui/MenuScreen.fxml"));
	        VBox menuRoot = menuLoader.load();
	        menuController = menuLoader.getController();
	        menuScene = new Scene(menuRoot);
	        menuScene.getStylesheets().add(styleCSS);
	        menuController.setMain(this);
	        
	        //Loading settings page
	        settingsLoader = new FXMLLoader(getClass().getResource("../gui/settings/SettingsScreen.fxml"));
	        VBox settingsRoot = settingsLoader.load();
	        settingsController = settingsLoader.getController();
	        settingsScene = new Scene(settingsRoot);
	        settingsScene.getStylesheets().add(styleCSS);
	        settingsController.setMain(this);
	        
	        
	        //Loading create page
	        createLoader = new FXMLLoader(getClass().getResource("../gui/create/CreateScreen.fxml"));
	        VBox createRoot = createLoader.load();
	        createController = createLoader.getController();
	        createScene = new Scene(createRoot);
	        createScene.getStylesheets().add(getClass().getResource("../gui/style.css").toExternalForm());
	        createController.setMain(this);
	        
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
