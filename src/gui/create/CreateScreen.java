package gui.create;

import java.io.IOException;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import letter.AcademicInfo;
import letter.LetterInfo;
import letter.StudentInfo;

public class CreateScreen {
	
	@FXML
	private ScrollPane content;
	@FXML
	private ImageView back;
	
	private StudentInfo studentInfo;
	private AcademicInfo academicInfo;
	
	private FXMLLoader studentLoader;
	private VBox studentTab;
	private StudentTab studentController;
	
	private FXMLLoader academicLoader;
	private VBox academicTab;
	private AcademicTab academicController;
	
	private Main main;
	
	/*
	 * Initializes all the different tabs
	 */
	public CreateScreen() {
		try {
			studentLoader = new FXMLLoader();
			studentTab = studentLoader.load(getClass().getResourceAsStream("./StudentTab.fxml"));
			studentController = studentLoader.getController();
			studentController.setCreateScreen(this);
			
			academicLoader = new FXMLLoader();
			academicTab = academicLoader.load(getClass().getResourceAsStream("./AcademicTab.fxml"));
			academicController = academicLoader.getController();
			academicController.setCreateScreen(this);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/*
	 * Resets scene to original state
	 */
	public void resetTabs() throws IOException {
		studentLoader = new FXMLLoader();
		studentTab = studentLoader.load(getClass().getResourceAsStream("./StudentTab.fxml"));
		studentController = studentLoader.getController();
		studentController.setCreateScreen(this);
		
		academicLoader = new FXMLLoader();
		academicTab = academicLoader.load(getClass().getResourceAsStream("./AcademicTab.fxml"));
		academicController = academicLoader.getController();
		academicController.setCreateScreen(this);
		
		studentInfo = null;
		academicInfo = null;
		displayStudentTab();
	}
	
	/*
	 * Opens student tab and sets back to go back if clicked
	 */
	public void initialize() {
		
		displayStudentTab();
		
		back.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			main.displayMenuScene(false);
			try {
				resetTabs();
			}
			catch (IOException e1) {
				e1.printStackTrace();
			}
		});
		
	}
	
	/*
	 * Sets student info for letter creation
	 */
	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}
	
	/*
	 * Sets academic info for letter creation
	 */
	public void setAcademicInfo(AcademicInfo academicInfo) {
		this.academicInfo = academicInfo;
	}
	
	/*
	 * Sets main for navigation
	 */
	public void setMain(Main main) {
		this.main = main;
	}
	
	/*
	 * Displays the student options
	 */
	public void displayStudentTab() {
		content.setContent(studentTab);
	}
	
	/*
	 * Displays the academic options
	 */
	public void displayAcademicTab() {
		content.setContent(academicTab);
	}
	
	/*
	 * Resets the scene and creates a new letter
	 */
	public void createLetter() {
		LetterInfo letterInfo = new LetterInfo(studentInfo, academicInfo);
		studentInfo = null;
		academicInfo = null;
		main.displayMenuScene(false);
		main.openLetterWithInfo(letterInfo);
		try {
			resetTabs();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}