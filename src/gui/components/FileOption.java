package gui.components;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class FileOption {
	@FXML
	private AnchorPane fileSelect;
	@FXML
	private Label firstName;
	@FXML
	private Label lastName;
	@FXML
	private Label date;
	@FXML
	private Label lastModified;
	@FXML
	private Label pathToFile;
	
	public FileOption() {
		
	}
	
	public void initialize() {
		
		fileSelect.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			
		});
	}
	
	public void setFileInfo() {
		
	}
	
}
