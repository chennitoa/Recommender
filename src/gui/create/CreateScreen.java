package gui.create;

import java.io.IOException;

import application.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import letter.AcademicInfo;
import letter.LetterInfo;
import letter.StudentInfo;

public class CreateScreen {
	
	@FXML
	private ScrollPane content;
	@FXML
	private Label back;
	
	private StudentInfo studentInfo;
	private AcademicInfo academicInfo;
	
	private FXMLLoader studentLoader;
	private VBox studentTab;
	private StudentTab studentController;
	
	private FXMLLoader academicLoader;
	private VBox academicTab;
	private AcademicTab academicController;
	
	private Main main;
	
	
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
	
	public void resetTabs() throws IOException {
		studentLoader = new FXMLLoader();
		studentTab = studentLoader.load(getClass().getResourceAsStream("./StudentTab.fxml"));
		studentController = studentLoader.getController();
		studentController.setCreateScreen(this);
		
		academicLoader = new FXMLLoader();
		academicTab = academicLoader.load(getClass().getResourceAsStream("./AcademicTab.fxml"));
		academicController = academicLoader.getController();
		academicController.setCreateScreen(this);
	}
	
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
	
	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}
	
	public void setAcademicInfo(AcademicInfo academicInfo) {
		this.academicInfo = academicInfo;
	}
	
	public void setMain(Main main) {
		this.main = main;
	}
	
	public void displayStudentTab() {
		content.setContent(studentTab);
	}
	
	public void displayAcademicTab() {
		content.setContent(academicTab);
	}
	
	public void createLetter() {
		LetterInfo letterInfo = new LetterInfo(studentInfo, academicInfo);
		
		main.displayLetterScene(letterInfo);
		try {
			resetTabs();
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	
}