package settings;

import java.sql.SQLException;
import java.util.List;

import database.DBManager;

public class SettingsManager {
	
	private static SettingsManager sM;
	private static DBManager dbM;
	
	private List<String> semesters;
	private List<String> courses;
	private List<String> programs;
	private List<String> personalCharacteristics;
	private List<String> academicCharacteristics;
	
	
	/*
	 * Singleton design
	 */
	private SettingsManager() {
		dbM = DBManager.getDBManager();
		
		dbM.query("CREATE TABLE IF NOT EXISTS semesters(semester text);"); 
		dbM.query("CREATE TABLE IF NOT EXISTS courses(course text);");
		dbM.query("CREATE TABLE IF NOT EXISTS programs(program text");
		dbM.query("CREATE TABLE IF NOT EXISTS personalCharacteristics(characteristics text");
		dbM.query("CREATE TABLE IF NOT EXISTS academicCharacteristics(characteristics text");
		
		dbM.query("SELECT * FROM semesters;");
		dbM.query("SELECT * FROM courses;");
		dbM.query("SELECT * FROM programs;");
		dbM.query("SELECT * FROM personalCharacteristics;");
		dbM.query("SELECT * FROM academicCharacteristics;");
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
	ProfessorInfo getProfessorInfo() {
		ProfessorInfo prof = new ProfessorInfo();
		return prof;
	}
	
	
	
	/*
	 * Change professor info
	 */
	void changeProfessorInfo(ProfessorInfo prof, String name, String title, String school, String department, String email, String phone) {
		prof.setName(name);
		prof.setTitle(title);
		prof.setSchoolName(school);
		prof.setDepartment(department);
		prof.setEmail(email);
		prof.setPhoneNumber(phone);
	}
	
	/*
	 * Getters and setters
	 */
	
	public List<String> getSemesters() {
		return semesters;
	}

	public void setSemesters(List<String> semesters) {
		this.semesters = semesters;
	}

	public List<String> getCourses() {
		return courses;
	}

	public void setCourses(List<String> courses) {
		this.courses = courses;
	}

	public List<String> getPrograms() {
		return programs;
	}

	public void setPrograms(List<String> programs) {
		this.programs = programs;
	}

	public List<String> getPersonalCharacteristics() {
		return personalCharacteristics;
	}

	public void setPersonalCharacteristics(List<String> personalCharacteristics) {
		this.personalCharacteristics = personalCharacteristics;
	}

	public List<String> getAcademicCharacteristics() {
		return academicCharacteristics;
	}

	public void setAcademicCharacteristics(List<String> academicCharacteristics) {
		this.academicCharacteristics = academicCharacteristics;
	}
	
//	public static void main(String args[]) throws SQLException {
////		SettingsManager sM = SettingsManager.getSettingsManager();
////		System.out.println(dbM.query("INSERT INTO semesters values('Spring');"));
////		System.out.println(dbM.query("SELECT * FROM semesters;").getString("semester"));
//		
//		
//		
////		semesters = Arrays.asList("Spring" , "Fall", "Summer");
////		courses = Arrays.asList("CS151: Object-Oriented Design","CS166: Information Security",
////				"CS154: Theory of Computation","CS160: Software Engineering","CS256: Cryptography","CS146: Data Structures and Algorithms",
////				"CS152: Programming Languages Paradigm");
////		programs = Arrays.asList("Master of science (MS)","Master of business administration (MBA)",
////				"Doctor of philosophy (PhD)");
////		personalCharacteristics = Arrays.asList("very passionate","very enthusiastic","punctual","attentive","polite");
////		academicCharacteristics = Arrays.asList("submitted well-written asisgnments", "participated in all of my class activities",
////				"worked hard", "was very well prepared for every exam and assignment", "picked up new skills quickly",
////				"was able to excel academically at the top of my class");
//	}
	
}
