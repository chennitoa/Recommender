package gui.edit;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class EditScreen {
	
	@FXML
	private MenuItem open;
	@FXML
	private MenuItem save;
	@FXML
	private MenuItem saveAs;
	@FXML
	private MenuItem close;
	@FXML
	private MenuItem redo;
	@FXML
	private MenuItem undo;
	@FXML
	private TextArea editor;
	
	public EditScreen() {
		
	}
	
	public void initialize() {
		
		open.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			
		});
		
		save.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			
		});
		
		saveAs.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			
		});
		
		close.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {

		});
		
		redo.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			editor.redo();
		});
		
		undo.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
			editor.undo();
		});
		
	}
	
	public void setText(List<String> textLines) {
		String text = "";
		for (String line : textLines) {
			if (!line.equals("%delete%")) {
				text += line + "\n";
			}
		}
		editor.setText(text);
	}
	
}
