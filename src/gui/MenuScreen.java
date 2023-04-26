package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import application.Main;
import filetracker.FileInfo;
import gui.components.FileOption;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import search.LetterManager;
import util.FileSelector;

public class MenuScreen {
	
	@FXML
	private AnchorPane newLetter;
	@FXML
	private AnchorPane openLetter;
	@FXML
	private Label settings;
	@FXML
	private Label logout;
	@FXML
	private TextField search;
	@FXML
	private VBox recentLetters;
	@FXML
	private Label info;
	
	private LetterManager letterManager;
	private Main main;
	
	public MenuScreen() {
		letterManager = LetterManager.getLetterManager();
	}
	
	/*
	 * Adds settings and logout functionality as well
	 * TODO: Create open letter and new letter functionality
	 */
	@FXML
	public void initialize() {
		info.setVisible(false);
		
		recentLetters.getChildren().clear();
	    for (FileInfo fileInfo : letterManager.getMatching("")) {
	    	try {
	    		FXMLLoader fileOptionLoader = new FXMLLoader();
				AnchorPane fileOption = fileOptionLoader.load(getClass().getResourceAsStream("components/FileOption.fxml"));
				FileOption fileOptionController = fileOptionLoader.getController();
				
				fileOptionController.setFileInfo(fileInfo);
				fileOptionController.setMenuScreen(this);
				
				recentLetters.getChildren().add(fileOption);
			}
	    	catch (IOException e) {
				throw new RuntimeException(e.getMessage());
			}
	    }
		
		newLetter.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
			main.displayCreateScene();
		});
		
		openLetter.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
			String pathToFile = FileSelector.getChooseFilePath(FileSelector.LETTER_FILTER);
			openLetter(pathToFile);
		});
		
		settings.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
			main.displaySettingsScene();
		});
		
		logout.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
			main.logout();
		});
		
		search.textProperty().addListener((observable, oldValue, newValue) -> {
		    String pattern = search.getText();
		    recentLetters.getChildren().clear();
		    for (FileInfo fileInfo : letterManager.getMatching(pattern)) {
		    	try {
		    		FXMLLoader fileOptionLoader = new FXMLLoader();
					AnchorPane fileOption = fileOptionLoader.load(getClass().getResourceAsStream("components/FileOption.fxml"));
					FileOption fileOptionController = fileOptionLoader.getController();
					
					fileOptionController.setFileInfo(fileInfo);
					fileOptionController.setMenuScreen(this);
					
					recentLetters.getChildren().add(fileOption);
				}
		    	catch (IOException e) {
					throw new RuntimeException(e.getMessage());
				}
		    }
		});
		
	}
	
	public void openLetter(String pathToFile) {
		info.setVisible(false);
		if (pathToFile != null) { 
			try {
				main.openLetterWithFile(new File(pathToFile));
			}
			catch (FileNotFoundException e) {
				displayErrorMessage("Failed to open file");
			}
		}
	}
	
	public void displayErrorMessage(String message) {
		info.setText(message);
		info.setTextFill(Color.color(1, 0, 0));
		info.setVisible(true);
	}
	
	/*
	 * Sets main for navigation
	 */
	public void setMain(Main main) {
		this.main = main;
	}
	
}
