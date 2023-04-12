package letter;

public class CourseInfo {
	
	private String courseName;
	private String courseGrade;
	
	/*
	 *  Java bean for course info, includes all getters and setters
	 */
	public CourseInfo(String courseName, String courseGrade) {
		this.courseName = courseName;
		this.courseGrade = courseGrade;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseGrade() {
		return courseGrade;
	}

	public void setCourseGrade(String courseGrade) {
		this.courseGrade = courseGrade;
	}
	
}
