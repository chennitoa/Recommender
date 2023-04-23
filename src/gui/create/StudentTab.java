package gui.create;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import letter.StudentInfo;

public class StudentTab {
	
	@FXML
	private TextField first;
	@FXML
	private TextField last;
	@FXML
	private ComboBox<String> gender;
	@FXML
	private TextField school;
	@FXML
	private DatePicker date;
	@FXML
	private Label info;
	@FXML
	private Button next;
	
	private List<String> genderOptions;
	
	private CreateScreen createScreen;
	
	/*
	 * Loads default gender options
	 */
	public StudentTab() {
		genderOptions = Arrays.asList("Male", "Female", "Other");
	}
	
	/*
	 * Initializes buttons and dropdowns
	 */
	public void initialize() {
		
		info.setVisible(false);
		
		gender.setItems(FXCollections.observableList(genderOptions));
		date.setValue(LocalDate.now());
		
		next.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			
			String firstName = first.getText();
			String lastName = last.getText();
			String selectedGender = gender.getValue();
			String targetSchool = school.getText();
			LocalDate selectedDate = date.getValue();
			boolean error = false;

			/*
			 * Input validation, user must enter all of the following information.
			 */
			if(firstName.length() == 0) {
				info.setVisible(false);
				info.setTextFill(Color.color(1, 0, 0));
				info.setText("Please enter student's first name.");
				info.setVisible(true);
				error = true;
			}
			else if(lastName.length() == 0) {
				info.setVisible(false);
				info.setTextFill(Color.color(1, 0, 0));
				info.setText("Please enter student's last name.");
				info.setVisible(true);
				error = true;
			}
			else if(selectedGender == null) {
				info.setVisible(false);
				info.setTextFill(Color.color(1, 0, 0));
				info.setText("Please select student's gender.");
				info.setVisible(true);
				error = true;
			}
			else if(targetSchool.length() == 0) {
				info.setVisible(false);
				info.setTextFill(Color.color(1, 0, 0));
				info.setText("Please enter student's target school.");
				info.setVisible(true);
				error = true;
			}
			
			if(!error) {
				StudentInfo studentInfo = new StudentInfo(firstName, lastName,
						selectedGender, targetSchool, selectedDate);
				
				createScreen.setStudentInfo(studentInfo);
				createScreen.displayAcademicTab();
			}
		});
		
	}
	
	/*
	 * Sets create screen for navigation
	 */
	public void setCreateScreen(CreateScreen createScreen) {
		this.createScreen = createScreen;
	}
	
}
