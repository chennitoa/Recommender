package letter;

import java.time.LocalDate;

public class StudentInfo {
	
	private String firstName;
	private String lastName;
	private String gender;
	private String targetSchool;
	private LocalDate date;
	
	public StudentInfo(String firstName, String lastName, String gender, String targetSchool, LocalDate date) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.targetSchool = targetSchool;
		this.date = date;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getTargetSchool() {
		return targetSchool;
	}
	
	public void setTargetSchool(String targetSchool) {
		this.targetSchool = targetSchool;
	}
	
	public LocalDate getDate() {
		return date;
	}
	
	public void setDate(LocalDate date) {
		this.date = date;
	}
	
}
