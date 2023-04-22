package letter;

import java.util.ArrayList;
import java.util.List;

public class CourseInfo {
	
	private String courseName;
	private String courseGrade;
	
	/*
	 * Method for getting a list of names from a list of courses
	 */
	public static List<String> getCourseNameFromList(List<CourseInfo> coursesList) {
		List<String> courseNames = new ArrayList<String>();
		for (CourseInfo course : coursesList) {
			courseNames.add(course.getCourseName());
		}
		return courseNames;
	}
	
	/*
	 * Method for getting a list of grades from a list of courses
	 */
	public static List<String> getCourseGradeFromList(List<CourseInfo> coursesList) {
		List<String> courseGrades = new ArrayList<String>();
		for (CourseInfo course : coursesList) {
			courseGrades.add(course.getCourseGrade());
		}
		return courseGrades;
	}
	
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
