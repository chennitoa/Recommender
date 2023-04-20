package gui;

import java.io.File;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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
	
	private Main main;
	
	public MenuScreen() {
		
	}
	
	/*
	 * Adds settings and logout functionality as well
	 * TODO: Create open letter and new letter functionality
	 */
	@FXML
	public void initialize() {
		
		newLetter.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			main.displayCreateScene();
		});
		
		openLetter.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			FileChooser letterFileChooser = new FileChooser();
			letterFileChooser.setTitle("Open Letter File");
			File f = letterFileChooser.showOpenDialog(new Stage());
			if (f != null) {
				System.out.println(f.getPath());
			}
		});
		
		settings.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			main.displaySettingsScene();
		});
		
		logout.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			main.logout();
		});
		
	}
	
	/*
	 * Sets main for navigation
	 */
	public void setMain(Main main) {
		this.main = main;
	}
	
}
