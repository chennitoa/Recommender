package gui.edit;

import application.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;

public class EditScreen {
	
	@FXML
	private HTMLEditor editor;
	@FXML
	private Button save;
	
	private Main main;
	
	public EditScreen() {
		
	}
	
	public void initialize() {
		save.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			System.out.println(editor.getHtmlText());
		});
	}
	
	public void setHTMLText(String text) {
		editor.setHtmlText(text);
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
	
}
