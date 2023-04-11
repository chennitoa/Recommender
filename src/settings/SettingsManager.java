package settings;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import database.DBManager;

public class SettingsManager {
	
	private static SettingsManager sM;
	private static DBManager dbM;
	
	private ProfessorInfo professorInfo;
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
			return Collections.emptyList();
		}
		ArrayList<String> resultsList = new ArrayList<String>();
		resultsList.add(rs.getString(columnTitle));
		while (rs.next() == true) {
			if (!rs.getString(columnTitle).equals("")) {
				resultsList.add(rs.getString(columnTitle));
			}
		}
		return resultsList;
	}
	
	/*
	 * Singleton design
	 */
	private SettingsManager() {
		dbM = DBManager.getDBManager();
			
		dbM.queryQuiet(
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
		dbM.queryQuiet("CREATE TABLE IF NOT EXISTS semesters(semester string);"); 
		dbM.queryQuiet("CREATE TABLE IF NOT EXISTS courses(course string);");
		dbM.queryQuiet("CREATE TABLE IF NOT EXISTS programs(program string);");
		dbM.queryQuiet("CREATE TABLE IF NOT EXISTS personalCharacteristics(characteristic string);");
		dbM.queryQuiet("CREATE TABLE IF NOT EXISTS academicCharacteristics(characteristic string);");
		
		

		try {
			ResultSet rs = dbM.query("SELECT * FROM professorInfo");
			if (rs.getString("name") == null) {
				professorInfo = null;
			}
			else {
				professorInfo = new ProfessorInfo(
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
			
			//If there is no settings-related data
			if (professorInfo == null && semesters.size() == 0 && courses.size() == 0 && programs.size() == 0 &&
					personalCharacteristics.size() == 0 && academicCharacteristics.size() == 0) {
				professorInfo = new ProfessorInfo();
				semesters = new ArrayList<String>(Arrays.asList("Spring" , "Fall", "Summer"));
				courses = new ArrayList<String>(Arrays.asList("CS151: Objected-Oriented Design",
															"CS166: Information Security",
															"CS154: Theory of Computation",
															"CS160: Software Engineering",
															"CS256: Cryptography",
															"CS146: Data Structures and Algorithms",
															"CS152: Programming Languages Paradigm"));
		
				programs = new ArrayList<String>(Arrays.asList("Master of science (MS)",
															"Master of business administration (MBA)",
															"Doctor of philosophy (PhD)"));
				
				personalCharacteristics = new ArrayList<String>((Arrays.asList("very passionate",
																			"very enthusiastic",
																			"punctual",
																			"attentive",
																			"polite")));
		
				academicCharacteristics = new ArrayList<String>(Arrays.asList("submitted well-written assignments",
																			"participated in all of my class activities",
																			"worked hard",
																			"was very well prepared for every exam and assignment",
																			"picked up new skills quickly",
																			"was able to excel academically at the top of my class"));

				setProfessorInfo(professorInfo);
				setSemesters(semesters);
				setCourses(courses);
				setPrograms(programs);
				setPersonalCharacteristics(personalCharacteristics);
				setAcademicCharacteristics(academicCharacteristics);
			}
			
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
		return professorInfo;
	}
	
	/*
	 * Change professor info
	 */
	public void setProfessorInfo(ProfessorInfo prof) {
		professorInfo = prof;
		dbM.queryQuiet("DELETE FROM professorInfo;");
		dbM.queryQuiet(String.format("INSERT INTO professorInfo (name, title, school, department, phone, email)"
				+ " values('%s', '%s', '%s', '%s', '%s', '%s');",
				professorInfo.getName(),
				professorInfo.getTitle(),
				professorInfo.getSchool(),
				professorInfo.getDepartment(),
				professorInfo.getPhone(),
				professorInfo.getEmail()
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
		dbM.queryQuiet("DELETE FROM semesters;");
		for(String s : semesters){
			dbM.queryQuiet(String.format("INSERT INTO semesters (semester) values('%s');", s));
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
		dbM.queryQuiet("DELETE FROM courses;");
		for(String s : courses){
			dbM.queryQuiet(String.format("INSERT INTO courses (course) values('%s');", s));
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
		dbM.queryQuiet("DELETE FROM programs;");
		for(String s : programs){
			dbM.queryQuiet(String.format("INSERT INTO programs (program) values('%s');", s));
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
		dbM.queryQuiet("DELETE FROM personalCharacteristics;");
		for(String s : personalCharacteristics){
			dbM.queryQuiet(String.format("INSERT INTO personalCharacteristics (characteristic) values('%s');", s));
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
		dbM.queryQuiet("DELETE FROM academicCharacteristics;");
		for(String s : academicCharacteristics){
			dbM.queryQuiet(String.format("INSERT INTO academicCharacteristics (characteristic) values('%s');", s));
		}
	}
	
}
