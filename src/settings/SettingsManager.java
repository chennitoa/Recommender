package settings;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DBManager;

public class SettingsManager {
	
	private static SettingsManager sM;
	private static DBManager dbM;
	
	private ProfessorInfo pI;
	private List<String> semesters;
	private List<String> courses;
	private List<String> programs;
	private List<String> personalCharacteristics;
	private List<String> academicCharacteristics;
	
	/*
	 * Given results of query, parses them into a list, returns null if list would be empty
	 */
	public static List<String> parseToList(ResultSet rs, String columnTitle) throws SQLException {
		if (rs == null || rs.next() == false) {
			return null;
		}
		ArrayList<String> resultsList = new ArrayList<String>();
		resultsList.add(rs.getString(columnTitle));
		while (rs.next() == true) {
			resultsList.add(rs.getString(columnTitle));
		}
		return resultsList;
	}
	
	/*
	 * Singleton design
	 */
	private SettingsManager() {
		dbM = DBManager.getDBManager();
		
		dbM.query(
				"CREATE TABLE IF NOT EXISTS professorInfo ("
				+ "Lock char(1) not null DEFAULT 'X',"
				+ "name string,"
				+ "title string,"
				+ "school string,"
				+ "department string,"
				+ "phone string,"
				+ "email string,"
				+ "constraint PK_T1 PRIMARY KEY (Lock),"
				+ "constraint CK_T1_Locked CHECK (Lock='X')"
				+ ");"
		);
		dbM.query("CREATE TABLE IF NOT EXISTS semesters(semester string);"); 
		dbM.query("CREATE TABLE IF NOT EXISTS courses(course string);");
		dbM.query("CREATE TABLE IF NOT EXISTS programs(program string);");
		dbM.query("CREATE TABLE IF NOT EXISTS personalCharacteristics(characteristic string);");
		dbM.query("CREATE TABLE IF NOT EXISTS academicCharacteristics(characteristic string);");
		
		try {
			ResultSet rs = dbM.query("SELECT * FROM professorInfo");
			if (rs.getString("name") == null) {
				pI = null;
			}
			else {
				pI = new ProfessorInfo(
						rs.getString("name"),
						rs.getString("title"),
						rs.getString("school"),
						rs.getString("department"),
						rs.getString("phone"),
						rs.getString("email")
				);
			}
			
			semesters = parseToList(dbM.query("SELECT * FROM semesters;"), "semester");
			courses = parseToList(dbM.query("SELECT * FROM courses;"), "course");
			programs = parseToList(dbM.query("SELECT * FROM programs;"), "program");
			personalCharacteristics = parseToList(dbM.query("SELECT * FROM personalCharacteristics;"), "characteristic");
			academicCharacteristics = parseToList(dbM.query("SELECT * FROM academicCharacteristics;"), "characteristic");
		}
		catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	/*
	 * Getter for SettingsManager object.
	 */
	public static SettingsManager getSettingsManager() {
		if(sM == null) {
			sM = new SettingsManager();
		}
		return sM;
	}
	
	/*
	 * Returns a professor java bean with initial data
	 */
	public ProfessorInfo getProfessorInfo() {
		return pI;
	}
	
	/*
	 * Change professor info
	 */
	public void setProfessorInfo(ProfessorInfo prof) {
		pI = prof;
		dbM.query("DELETE FROM professorInfo;");
		dbM.query(String.format("INSERT INTO professorInfo (name, title, school, department, phone, email)"
				+ " values('%s', '%s', '%s', '%s', '%s', '%s');",
				pI.getName(),
				pI.getTitle(),
				pI.getSchool(),
				pI.getDepartment(),
				pI.getPhone(),
				pI.getEmail()
		));
	}
	
	/*
	 * Gets semesters as list of strings
	 */
	public List<String> getSemesters() {
		return semesters;
	}

	/*
	 * Sets semesters given list of strings, and saves to database
	 */
	public void setSemesters(List<String> semesters) {
		this.semesters = semesters;
		dbM.query("DELETE FROM semesters;");
		for(String s : semesters){
			dbM.query(String.format("INSERT INTO semesters (semester) values('%s');", s));
		}
	}

	/*
	 * Gets courses as list of strings
	 */
	public List<String> getCourses() {
		return courses;
	}

	/*
	 * Sets courses given list of strings, and saves to database
	 */
	public void setCourses(List<String> courses) {
		this.courses = courses;
		dbM.query("DELETE FROM courses;");
		for(String s : courses){
			dbM.query(String.format("INSERT INTO courses (course) values('%s');", s));
		}
	}

	/*
	 * Gets programs as list of strings
	 */
	public List<String> getPrograms() {
		return programs;
	}

	/*
	 * Sets programs given list of strings, and saves to database
	 */
	public void setPrograms(List<String> programs) {
		this.programs = programs;
		dbM.query("DELETE FROM programs;");
		for(String s : programs){
			dbM.query(String.format("INSERT INTO programs (program) values('%s');", s));
		}
	}

	/*
	 * Gets personal characteristics as list of strings
	 */
	public List<String> getPersonalCharacteristics() {
		return personalCharacteristics;
	}
	
	/*
	 * Sets personal characteristics given list of strings, and saves to database
	 */
	public void setPersonalCharacteristics(List<String> personalCharacteristics) {
		this.personalCharacteristics = personalCharacteristics;
		dbM.query("DELETE FROM personalCharacteristics;");
		for(String s : personalCharacteristics){
			dbM.query(String.format("INSERT INTO personalCharacteristics (characteristic) values('%s');", s));
		}
	}

	/*
	 * Gets academic characteristics as list of strings
	 */
	public List<String> getAcademicCharacteristics() {
		return academicCharacteristics;
	}
	
	/*
	 * Sets academic characteristics given list of strings, and saves to database
	 */
	public void setAcademicCharacteristics(List<String> academicCharacteristics) {
		this.academicCharacteristics = academicCharacteristics;
		dbM.query("DELETE FROM academicCharacteristics;");
		for(String s : academicCharacteristics){
			dbM.query(String.format("INSERT INTO academicCharacteristics (characteristic) values('%s');", s));
		}
	}
	
}
