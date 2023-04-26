package gui.components;

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
	
	/*
	 * Sets grade to be uneditable until the checkbox is clicked
	 */
	public void initialize() {
		
		selection.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
			grade.setEditable(selection.isSelected());
		});
		
		grade.setEditable(false);
		
	}
	
	/*
	 * Returns the text currently displayed
	 */
	public String getLabel() {
		return selection.getText();
	}
	
	/*
	 * Sets the text currently displayed
	 */
	public void setLabel(String text) {
		selection.setText(text);
	}
	
	/*
	 * Returns true if the checkbox is checked, false otherwise
	 */
	public boolean getSelected() {
		return selection.isSelected();
	}
	
	/*
	 * Returns the text inside the grade textbox
	 */
	public String getGrade() {
		return grade.getText();
	}
	
}
