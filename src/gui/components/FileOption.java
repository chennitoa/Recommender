package gui.components;

import filetracker.FileInfo;
import gui.MenuScreen;
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
	private Label path;
	
	private FileInfo fileInfo;
	private MenuScreen menuScreen;
	
	public FileOption() {
		
	}
	
	public void initialize() {
		
		fileSelect.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
			if (event.getClickCount() >= 2) {
				menuScreen.openLetter(fileInfo.getPathToFile());
			}
		});
	}
	
	public void setFileInfo(FileInfo fileInfo) {
		firstName.setText(fileInfo.getFirstName());
		lastName.setText(fileInfo.getLastName());
		date.setText(fileInfo.getLetterYear());
		path.setText(fileInfo.getPathToFile());
		
		this.fileInfo = fileInfo;
	}
	
	public void setMenuScreen(MenuScreen menuScreen) {
		this.menuScreen = menuScreen;
	}
	
}
