package generate;

import database.DBConnection;
import gui.create.AcademicTab;

public class StudentInfoDB {
	
	private static DBConnection conn; 
	private AcademicTab academicObj;
	
	/*
	 * 
	 */
	public StudentInfoDB(){
		conn = DBConnection.getDBConnection();
		academicObj = new AcademicTab();
	}
	
	public void saveStudentInfo() {
//		List<String> selectedLabel = academicObj.parseSelectionOptions();
	}
	
}



