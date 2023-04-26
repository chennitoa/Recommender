package gui;

import java.io.File;
import java.io.FileNotFoundException;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
	
	private Main main;
	
	public MenuScreen() {
		
	}
	
	/*
	 * Adds settings and logout functionality as well
	 * TODO: Create open letter and new letter functionality
	 */
	@FXML
	public void initialize() {
		info.setVisible(false);
		
		newLetter.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
			main.displayCreateScene();
		});
		
		openLetter.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
			info.setVisible(false);
			String pathToFile = FileSelector.getChooseFilePath(FileSelector.LETTER_FILTER);
			if (pathToFile != null) { 
				try {
					main.openLetterWithFile(new File(pathToFile));
				}
				catch (FileNotFoundException e) {
					displayErrorMessage("Failed to open file");
				}
			}
		});
		
		settings.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
			main.displaySettingsScene();
		});
		
		logout.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
			main.logout();
		});
		
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
