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
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import settings.SettingsManager;
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
	private Button compile;
	@FXML
	private Button back;
	
	private List<SelectionTextOption> firstCourseControllers;
	private List<SelectionTextOption> otherControllers;
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
		otherControllers = new ArrayList<SelectionTextOption>();
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
	
	public List<String> parseSelectionOptions(List<SelectionOption> selectionOptions) {
		//TODO: Finish the method
		return null;
	}

	public List<CourseInfo> parseSelectionTextOptions(List<SelectionTextOption> selectionTextOptions) {
		//TODO: Finish the method.
		return null;
	}

	/*
	 * Adds functionality to buttons, initializes all the dropdown options
	 */
	public void initialize() {
		
		program.setItems(FXCollections.observableList(settingsManager.getPrograms()));
		semester.setItems(FXCollections.observableList(settingsManager.getSemesters()));
		
		compile.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			createScreen.createLetter();
		});
		
		back.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			createScreen.displayStudentTab();
		});
		
		try {
			initializeMenuTextOptions(settingsManager.getCourses(), firstCourseControllers, firstCourse);
			initializeMenuTextOptions(settingsManager.getCourses(), otherControllers, other);
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
