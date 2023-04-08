package settings;

import java.util.Arrays;
import java.util.List;

public class settingsManager {
	
	private List<String> semesters = Arrays.asList("Spring" , "Fall", "Summer");
	private List<String> courses = Arrays.asList("CS151: Object-Oriented Design","CS166: Information Security",
			"CS154: Theory of Computation","CS160: Software Engineering","CS256: Cryptography","CS146: Data Structures and Algorithms",
			"CS152: Programming Languages Paradigm");
	private List<String> programs = Arrays.asList("Master of science (MS)","Master of business administration (MBA)",
			"Doctor of philosophy (PhD)");
	private List<String> personalCharacteristics = Arrays.asList("very passionate","very enthusiastic","punctual","attentive","polite");
	private List<String> academicCharacteristics = Arrays.asList("submitted well-written asisgnments", "participated in all of my class activities",
			"worked hard", "was very well prepared for every exam and assignment", "picked up new skills quickly",
			"was able to excel academically at the top of my class");
	
	/*
	 * Single design
	 */
	private settingsManager() {}
	
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
	
	
	
}
