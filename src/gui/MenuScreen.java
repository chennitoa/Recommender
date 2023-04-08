package gui;

import java.io.File;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MenuScreen implements ApplicationScreen {
	
	@FXML
	private Label newLetter;
	@FXML
	private Label openLetter;
	@FXML
	private Label settings;
	@FXML
	private TextField search;
	@FXML
	private ScrollBar recentScroll;
	@FXML
	private VBox recentLetters;
	
	private Main m;
	
	public MenuScreen() {
		
	}
	
	@FXML
	public void initialize() {
		newLetter.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			m.changeScene("Draft");
		});
		openLetter.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			FileChooser letterFileChooser = new FileChooser();
			letterFileChooser.setTitle("Open Letter File");
			File f = letterFileChooser.showOpenDialog(new Stage());
		});
	}
	
	@Override
	public void setMain(Main m) {
		this.m = m;
	}
	
}
