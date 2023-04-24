package gui.edit;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;

public class EditScreen {
	
	@FXML
	private MenuItem open;
	@FXML
	private MenuItem save;
	@FXML
	private MenuItem saveAs;
	@FXML
	private MenuItem export;
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
	
	public List<String> exportToList() {
		List<String> letterParagraphs = new ArrayList<String>();
		for (CharSequence line : editor.getParagraphs()) {
			letterParagraphs.add(line.toString());
		}
		return letterParagraphs;
	}
	
	public void initialize() {
		
		//Menu items require setOnAction() method to function, do not respond to EventHandler
		
		open.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
			}
		});
		
		save.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
			}
		});
		
		saveAs.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
			}
		});
		
		export.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
			}
		});
		
		close.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
			}
		});
		
		undo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				editor.redo();
			}
		});
		
		undo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				editor.undo();
			}
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
