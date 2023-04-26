package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import filetracker.FileInfo;
import filetracker.MetadataManager;
import generate.LetterGenerator;
import gui.MenuScreen;
import gui.create.CreateScreen;
import gui.edit.EditScreen;
import gui.entry.EntryScreen;
import gui.settings.SettingsScreen;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Screen;
import javafx.stage.Stage;
import letter.LetterInfo;
import util.FileHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;


public class Main extends Application {
	
	private Stage mainStage;
	
	private String editCSS;
	private String styleCSS;
	private Image favicon;
	
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
		mainStage.getIcons().add(favicon);
		mainStage.show();
	}
	
	/*
	 * Displays maxmized menu scene with new stage
	 */
	public void displayMenuScene(boolean createNewStage) {
		if (createNewStage) {
			mainStage.close();
			mainStage = new Stage();
			mainStage.getIcons().add(favicon);
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
	
	/*
	 * Displays create letter scene
	 */
	public void displayCreateScene() {
		mainStage.setScene(createScene);
		createController.displayStudentTab();
		try {
			createController.resetTabs();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		mainStage.show();
	}
	
	private void displayLetterScene(List<String> letterText, FileInfo fileInfo) {
		FXMLLoader editLoader = new FXMLLoader();
        VBox editRoot;
		try {
        	editRoot = editLoader.load(getClass().getResourceAsStream("../gui/edit/EditScreen.fxml"));
        }
        catch (IOException e) {
        	e.printStackTrace();
        	editRoot = new VBox();
        }
		
		EditScreen editController = editLoader.getController();
        Scene editScene = new Scene(editRoot);
        editScene.getStylesheets().add(styleCSS);
		editScene.getStylesheets().add(editCSS);
		
		Stage letterStage = new Stage();
		letterStage.setMaximized(true);
		letterStage.setWidth(Screen.getPrimary().getBounds().getMaxX());
		letterStage.setHeight(Screen.getPrimary().getBounds().getMaxY());
		letterStage.getIcons().add(favicon);
		letterStage.setTitle("Recommender: Edit Letter");
		letterStage.setScene(editScene);
		
		editController.setText(letterText);
		editController.setFileInfo(fileInfo);
		editController.setMain(this);
		
		letterStage.show();
	}
	
	/*
	 * Compiles a letter from a LetterInfo object and shows a screen with that letter
	 */
	public void openLetterWithInfo(LetterInfo letterInfo) {
		LetterGenerator letterGenerator = new LetterGenerator();
		List<String> letterText = letterGenerator.generateLetter(letterInfo);
		
		FileInfo incompleteFileInfo = new FileInfo(
			letterInfo.getStudentInfo().getFirstName(),
			letterInfo.getStudentInfo().getLastName(),
			letterInfo.getAcademicInfo().getSemesterYear(),
			null
		);
		displayLetterScene(letterText, incompleteFileInfo);
	}
	
	public void openLetterWithFile(File letterFile) throws FileNotFoundException {
		List<String> letterText = FileHandler.getFileHandler().getFileContents(letterFile);
		FileInfo fileInfo;
		
		if (MetadataManager.containsMetadata(letterText)) {
			fileInfo = MetadataManager.splitMetadata(letterText);
		}
		else {
			fileInfo = null;
		}
		displayLetterScene(letterText, fileInfo);
	}
	
	/*
	 * Loads all pages, links them to names and opens the primary stage
	 */
	@Override
	public void start(Stage primaryStage) {
		try {
			
			editCSS = getClass().getResource("../gui/resources/edit.css").toExternalForm();
			styleCSS = getClass().getResource("../gui/resources/style.css").toExternalForm();
			favicon = new Image(getClass().getResourceAsStream("../gui/resources/Rounded_v2.png"));
			
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
	        createScene.getStylesheets().add(styleCSS);
	        createController.setMain(this);
	        
	        //Loading edit page
	        
	        
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
