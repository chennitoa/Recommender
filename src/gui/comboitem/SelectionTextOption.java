package gui.comboitem;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SelectionTextOption {
	
	@FXML
	private CheckBox selection;
	@FXML
	private TextField grade;
	
	public SelectionTextOption() {
		
	}
	
	public void initialize() {
		
		selection.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			grade.setEditable(selection.isSelected());
		});
		
		grade.setEditable(false);
		
	}
	
	public String getLabel() {
		return selection.getText();
	}
	
	public void setLabel(String text) {
		selection.setText(text);
	}
	
	public boolean getSelected() {
		return selection.isSelected();
	}
	
	public String getGrade() {
		return grade.getText();
	}
	
}
