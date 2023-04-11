package gui.create;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;

public class CreateScreen {
	
	@FXML
	private ScrollPane content;
	
	private Main main;
	
	public CreateScreen() {
		
	}
	
	public void initialize() {
		
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
	
}