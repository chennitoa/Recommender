package settings;

public class ProfessorInfo {
	private String name;
	private String title;
	private String schoolName;
	private String department;
	private String email;
	private String phoneNumber;
	
	public ProfessorInfo(String name, String title, String schoolName, String department, String phoneNumber, String email) {
		this.setName(name);
		this.setTitle(title);
		this.setSchool(schoolName);
		this.setDepartment(department);
		this.setEmail(email);
		this.setPhone(phoneNumber);
	}

	/*
	 * Setters and getters
	 */
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSchool() {
		return schoolName;
	}

	public void setSchool(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPhone() {
		return phoneNumber;
	}

	public void setPhone(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	
	
}
