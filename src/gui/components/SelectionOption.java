package gui.components;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

public class SelectionOption {
	
	@FXML
	private CheckBox selection;
	
	public SelectionOption() {
		
	}
	
	public void initialize() {
		
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
	public void setLabel(String labelText) {
		selection.setText(labelText);
	}
	
	/*
	 * Returns true if the checkbox is checked, false otherwise
	 */
	public boolean getSelected() {
		return selection.isSelected();
	}
	
}
