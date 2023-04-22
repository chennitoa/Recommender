package gui.edit;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;

public class EditScreen {
	
	@FXML
	private TextArea editor;
	@FXML
	private Button save;
	
	public EditScreen() {
		
	}
	
	public void initialize() {
		save.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			System.out.println(editor.getText());
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
