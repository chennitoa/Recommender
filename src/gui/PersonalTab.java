package gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import settings.ProfessorInfo;
import settings.SettingsManager;

public class PersonalTab {
	
	@FXML
	private TextField name;
	@FXML
	private TextField title;
	@FXML
	private TextField email;
	@FXML
	private TextField phone;
	@FXML
	private TextField school;
	@FXML
	private TextField department;
	@FXML
	private Button save;
	@FXML
	private Label info;
	
	private SettingsManager sM;
	
	public PersonalTab() {
		sM = SettingsManager.getSettingsManager();
	}
	
	public void initialize() {
		
		info.setVisible(false);
		
		ProfessorInfo currentInfo = sM.getProfessorInfo();
		if (currentInfo != null) {
			name.setText(currentInfo.getName());
			title.setText(currentInfo.getTitle());
			email.setText(currentInfo.getEmail());
			phone.setText(currentInfo.getPhone());
			school.setText(currentInfo.getSchool());
			department.setText(currentInfo.getDepartment());
		}
		
		save.addEventFilter(MouseEvent.MOUSE_CLICKED, e -> {
			
			String nameInfo = name.getCharacters().toString();
			String titleInfo = title.getCharacters().toString();
			String emailInfo = email.getCharacters().toString();
			String phoneInfo = phone.getCharacters().toString();
			String schoolInfo = school.getCharacters().toString();
			String departmentInfo = department.getCharacters().toString();
			
			ProfessorInfo pI = new ProfessorInfo(nameInfo, titleInfo, schoolInfo,
					departmentInfo, phoneInfo, emailInfo);
			
			sM.setProfessorInfo(pI);
			
			info.setVisible(false);
			info.setTextFill(Color.color(0, 1, 0));
			info.setText("Personal information has been successfully saved");
			info.setVisible(true);
		});
		
	}
	
}
