package letter;

import java.util.List;

public class AcademicInfo {
	
	private String programName;
	private List<CourseInfo> firstCourses;
	private String semesterName;
	private String semesterYear;
	private List<CourseInfo> otherCourses;
	private List<String> personalCharacteristicList;
	private List<String> academicCharacteristicList;
	
	
	/*
	 *  Java bean for academic info, includes all getters and setters
	 */
	public AcademicInfo(String programName, List<CourseInfo> firstCourses, 
			String semesterName, String semesterYear, List<CourseInfo> otherCourses,
			List<String> personalCharacteristicList, List<String> academicCharacteristicList) {
		this.programName = programName;
		this.firstCourses = firstCourses;
		this.semesterName = semesterName;
		this.semesterYear = semesterYear;
		this.otherCourses = otherCourses;
		this.personalCharacteristicList = personalCharacteristicList;
		this.academicCharacteristicList = academicCharacteristicList;
	}
	
	public String getProgramName() {
		return programName;
	}
	
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	
	public List<CourseInfo> getFirstCourse() {
		return firstCourses;
	}

	public void setFirstCourse(List<CourseInfo> firstCourses) {
		this.firstCourses = firstCourses;
	}

	public String getSemesterName() {
		return semesterName;
	}
	
	public void setSemesterName(String semesterName) {
		this.semesterName = semesterName;
	}
	
	public String getSemesterYear() {
		return semesterYear;
	}
	
	public void setSemesterYear(String semesterYear) {
		this.semesterYear = semesterYear;
	}
	
	public List<CourseInfo> getCourseInfoList() {
		return otherCourses;
	}
	
	public void setCourseInfoList(List<CourseInfo> otherCourses) {
		this.otherCourses = otherCourses;
	}
	
	public List<String> getPersonalCharacteristicList() {
		return personalCharacteristicList;
	}
	
	public void setPersonalCharacteristicList(List<String> personalCharacteristicList) {
		this.personalCharacteristicList = personalCharacteristicList;
	}
	
	public List<String> getAcademicCharacteristicList() {
		return academicCharacteristicList;
	}
	
	public void setAcademicCharacteristicList(List<String> academicCharacteristicList) {
		this.academicCharacteristicList = academicCharacteristicList;
	}
	
}
