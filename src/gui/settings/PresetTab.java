package gui.settings;


import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import settings.SettingsManager;

public class PresetTab {
	
	@FXML
	private TextArea semesters;
	@FXML
	private TextArea courses;
	@FXML
	private TextArea programs;
	@FXML
	private TextArea personal;
	@FXML
	private TextArea academic;
	@FXML
	private Button save;
	@FXML
	private Label info;
	
	private SettingsManager sM;
	
	/*
	 * Returns list of strings as content of text area
	 */
	public static List<String> parseTextArea(TextArea tA) {
		List<String> text = new ArrayList<String>();
		
		for (CharSequence cS : tA.getParagraphs()) {
			text.add(cS.toString());
		}
		
		return text;
	}
	
	/*
	 * Returns text formatted for text area given list of strings
	 */
	public static String getTextAreaFormat(List<String> stringsToFormat) {
		if (stringsToFormat != null) {
			return String.join("\n", stringsToFormat);
		}
		else {
			return "";
		}
	}
	
	/*
	 * Initializes class settings manager
	 */
	public PresetTab() {
		sM = SettingsManager.getSettingsManager();
	}
	
	/*
	 * Initializes save button and starting text for PrefillTab
	 */
	public void initialize() {
		
		info.setVisible(false);
		
		semesters.setText(getTextAreaFormat(sM.getSemesters()));
		courses.setText(getTextAreaFormat(sM.getCourses()));
		programs.setText(getTextAreaFormat(sM.getPrograms()));
		personal.setText(getTextAreaFormat(sM.getPersonalCharacteristics()));
		academic.setText(getTextAreaFormat(sM.getAcademicCharacteristics()));
		
		// When save button pressed, all text areas are parsed out into lists of strings and saved
		save.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			
			List<String> semestersList = parseTextArea(semesters);
			List<String> coursesList = parseTextArea(courses);
			List<String> programsList = parseTextArea(programs);
			List<String> personalList = parseTextArea(personal);
			List<String> academicList = parseTextArea(academic);
			
			sM.setSemesters(semestersList);
			sM.setCourses(coursesList);
			sM.setPrograms(programsList);
			sM.setPersonalCharacteristics(personalList);
			sM.setAcademicCharacteristics(academicList);
			
			
			info.setVisible(false);
			info.setTextFill(Color.color(0, 1, 0));
			info.setText("Preset information has been succesfully saved");
			info.setVisible(true);
		});
		
	}
	
}
