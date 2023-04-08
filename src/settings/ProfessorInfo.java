package settings;

public class ProfessorInfo {
	private String name = "Ahmad Yazdankhah";
	private String title = "Lecturer";
	private String schoolName = "SJSU";
	private String department = "CS Department";
	private String email = "ahmad.yazdankhah@sjsu.edu";
	private String phoneNumber = "(123) 456-7890";
	
	/*
	 * Default constructor
	 */
	public ProfessorInfo() {
		
	}
	
	/*
	 * Overloaded constructor
	 */
	public ProfessorInfo(String name, String title, String schoolName, String department, String phoneNumber, String email) {
		this.setName(name);
		this.setTitle(title);
		this.setSchoolName(schoolName);
		this.setDepartment(department);
		this.setEmail(email);
		this.setPhoneNumber(phoneNumber);
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

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
//		StringBuilder sb = new StringBuilder();
//		for(char c : phoneNumber.toCharArray()) {
//			sb.append(c);
//		}
//		sb.append("(", 0, 1);
		
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
