package gui.comboitem;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

public class SelectionOption {
	
	@FXML
	private CheckBox selection;
	
	public SelectionOption() {
		
	}
	
	public void initialize() {
		
	}
	
	public String getLabel() {
		return selection.getText();
	}
	
	public void setLabel(String labelText) {
		selection.setText(labelText);
	}
	
	public boolean getSelected() {
		return selection.isSelected();
	}
	
}
