package gui.create;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gui.comboitem.SelectionOption;
import gui.comboitem.SelectionTextOption;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import settings.SettingsManager;
import letter.AcademicInfo;
import letter.CourseInfo;

public class AcademicTab {
	
	@FXML
	private ComboBox<String> program;
	@FXML
	private MenuButton firstCourse;
	@FXML
	private ComboBox<String> semester;
	@FXML
	private TextField year;
	@FXML
	private MenuButton other;
	@FXML
	private MenuButton personal;
	@FXML
	private MenuButton academic;
	@FXML
	private Label info;
	@FXML
	private Button compile;
	@FXML
	private Button back;
	
	private List<SelectionTextOption> firstCourseControllers;
	private List<SelectionTextOption> otherCourseControllers;
	private List<SelectionOption> personalControllers;
	private List<SelectionOption> academicControllers;
	
	private SettingsManager settingsManager;
	private CreateScreen createScreen;
	
	
	/*
	 * Initializes all lists and grabs singleton instance of settings manager
	 */
	public AcademicTab() {
		settingsManager = SettingsManager.getSettingsManager();
		firstCourseControllers = new ArrayList<SelectionTextOption>();
		otherCourseControllers = new ArrayList<SelectionTextOption>();
		personalControllers = new ArrayList<SelectionOption>();
		academicControllers = new ArrayList<SelectionOption>();
	}

	/*
	 * Takes selection options on list and puts them on menu button items
	 */
	public void initializeMenuOptions(List<String> options, List<SelectionOption> controllers,
			MenuButton buttonToInitialize) throws IOException {
		List<CustomMenuItem> menuItems = new ArrayList<CustomMenuItem>();
		for (String option : options) {
			FXMLLoader optionLoader = new FXMLLoader();
			AnchorPane optionPane = optionLoader.load(getClass().getResourceAsStream("../comboitem/SelectionOption.fxml"));
			SelectionOption optionController = optionLoader.getController();
			optionController.setLabel(option);
			
			CustomMenuItem optionItem = new CustomMenuItem(optionPane);
			optionItem.setHideOnClick(false);
			
			controllers.add(optionController);
			menuItems.add(optionItem);
		}
		buttonToInitialize.getItems().addAll(menuItems);
	}
	
	/*
	 * Takes selection options with text on list and puts them on menu button items
	 */
	public void initializeMenuTextOptions(List<String> options, List<SelectionTextOption> controllers, 
			MenuButton buttonToInitialize) throws IOException {
		List<CustomMenuItem> menuItems = new ArrayList<CustomMenuItem>();
		for (String option : options) {
			FXMLLoader textOptionLoader = new FXMLLoader();
			AnchorPane textOptionPane = textOptionLoader.load(getClass().getResourceAsStream("../comboitem/SelectionTextOption.fxml"));
			SelectionTextOption textOptionController = textOptionLoader.getController();
			textOptionController.setLabel(option);
			
			CustomMenuItem textOptionItem = new CustomMenuItem(textOptionPane);
			textOptionItem.setHideOnClick(false);
			
			controllers.add(textOptionController);
			menuItems.add(textOptionItem);
		}
		buttonToInitialize.getItems().addAll(menuItems);
	}
	
	/*
	 * Returns a list of label which user selected
	 */
	public List<String> parseSelectionOptions(List<SelectionOption> selectionOptions) {
		List<String> options = new ArrayList<>();
		for(SelectionOption s: selectionOptions) {
			if(s.getSelected()){
				options.add(s.getLabel());
			}
		}
		return options;
	}

	/*
	 * Returns a list of CourseInfo objects which has been selected by user.
	 */
	public List<CourseInfo> parseSelectionTextOptions(List<SelectionTextOption> selectionTextOptions) {
		List<CourseInfo> info = new ArrayList<>();
		for(SelectionTextOption s : selectionTextOptions) {
			if(s.getSelected()) {
				info.add(new CourseInfo(s.getLabel(), s.getGrade()));
			}
		}
		return info;
	}

	/*
	 * Adds functionality to buttons, initializes all the dropdown options
	 */
	public void initialize() {
		program.setItems(FXCollections.observableList(settingsManager.getPrograms()));
		semester.setItems(FXCollections.observableList(settingsManager.getSemesters()));
		info.setVisible(false);
		
		// Get information from all fillables and use it to compile an academic info, then signal to create letter
		compile.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			String programName = program.getValue();
			List<CourseInfo> firstCourses = parseSelectionTextOptions(firstCourseControllers);
			String semesterName = semester.getValue();
			String semesterYear = year.getText();
			List<CourseInfo> otherCourses = parseSelectionTextOptions(otherCourseControllers);
			List<String> personalCharacteristics = parseSelectionOptions(personalControllers);
			List<String> academicCharacteristics = parseSelectionOptions(academicControllers);
			boolean error = false;

			/*
			 * First priority input validation.
			 */
		
			if(programName == null) {
				info.setVisible(false);
				info.setTextFill(Color.color(1, 0, 0));
				info.setText("Please select a program.");
				info.setVisible(true);
				error = true;
			}
			else if(firstCourses.isEmpty()) {
				info.setVisible(false);
				info.setTextFill(Color.color(1, 0, 0));
				info.setText("Please select the first course.");
				info.setVisible(true);
				error = true;
			}
			else if(semesterName == null) {
				info.setVisible(false);
				info.setTextFill(Color.color(1, 0, 0));
				info.setText("Please select a semester");
				info.setVisible(true);
				error = true;
			}
			else if(semesterYear.length() == 0) {
				info.setVisible(false);
				info.setTextFill(Color.color(1, 0, 0));
				info.setText("Please enter the semester year.");
				info.setVisible(true);
				error = true;
			}
			else if(personalCharacteristics.isEmpty()) {
				info.setVisible(false);
				info.setTextFill(Color.color(1, 0, 0));
				info.setText("Please select personal characteristics.");
				info.setVisible(true);
				error = true;
			}
			else if(academicCharacteristics.isEmpty()) {
				info.setVisible(false);
				info.setTextFill(Color.color(1, 0, 0));
				info.setText("Please select academic characteristics.");
				info.setVisible(true);
				error = true;
			}

			/*
			 * Second priority input validation.
			 * If the first course tab has more than one selection or no grade entered, error message will display.
			 */
			if(firstCourses.get(0).getCourseGrade().length() == 0 || firstCourses.size() > 1) {
				info.setVisible(false);
				info.setTextFill(Color.color(1, 0, 0));
				info.setText("Please enter the course grade for " + firstCourses.get(0).getCourseName() + " in first course tab.");
				info.setVisible(true);
				error = true;
			}
			for(CourseInfo courseInfo : otherCourses) {
				if(courseInfo.getCourseGrade().length() == 0) {
					info.setVisible(false);
					info.setTextFill(Color.color(1, 0, 0));
					info.setText("Please enter the course grade for " + courseInfo.getCourseName() + " in other courses tab.");
					info.setVisible(true);
					error = true;
				}
			}
			if(!error) {
				AcademicInfo academicInfo = new AcademicInfo(programName, firstCourses, semesterName,
						semesterYear, otherCourses, personalCharacteristics, academicCharacteristics);
				
				createScreen.setAcademicInfo(academicInfo);
				
				createScreen.createLetter();
			}
		});
		
		back.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			createScreen.displayStudentTab();
		});
		
		try {
			initializeMenuTextOptions(settingsManager.getCourses(), firstCourseControllers, firstCourse);
			initializeMenuTextOptions(settingsManager.getCourses(), otherCourseControllers, other);
			initializeMenuOptions(settingsManager.getPersonalCharacteristics(), personalControllers, personal);
			initializeMenuOptions(settingsManager.getAcademicCharacteristics(), academicControllers, academic);
		}
		catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}
	
	/*
	 * Sets create screen for navigation
	 */
	public void setCreateScreen(CreateScreen createScreen) {
		this.createScreen = createScreen;
	}
	
}
