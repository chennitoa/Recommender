package gui.edit;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class EditScreen {
	
	@FXML
	private TextArea editor;
	
	public EditScreen() {
		
	}
	
	public void initialize() {
		
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
