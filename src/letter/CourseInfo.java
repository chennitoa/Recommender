package letter;

public class CourseInfo {
	
	private String courseName;
	private String courseYear;
	
	public CourseInfo(String courseName, String courseYear) {
		this.courseName = courseName;
		this.courseYear = courseYear;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getCourseYear() {
		return courseYear;
	}

	public void setCourseYear(String courseYear) {
		this.courseYear = courseYear;
	}
	
}
