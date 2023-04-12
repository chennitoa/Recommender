package letter;

import java.util.List;

public class AcademicInfo {
	
	private String programName;
	private String firstCourse;
	private String semesterName;
	private String semesterYear;
	private List<CourseInfo> courseInfoList;
	private List<String> personalCharacteristicList;
	private List<String> academicCharacteristicList;
	
	public AcademicInfo(String programName, String semesterName, String semesterYear, List<CourseInfo> courseInfoList,
			List<String> personalCharacteristicList, List<String> academicCharacteristicList) {
		this.programName = programName;
		this.semesterName = semesterName;
		this.semesterYear = semesterYear;
		this.courseInfoList = courseInfoList;
		this.personalCharacteristicList = personalCharacteristicList;
		this.academicCharacteristicList = academicCharacteristicList;
	}
	
	public String getProgramName() {
		return programName;
	}
	
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	
	public String getFirstCourse() {
		return firstCourse;
	}

	public void setFirstCourse(String firstCourse) {
		this.firstCourse = firstCourse;
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
		return courseInfoList;
	}
	
	public void setCourseInfoList(List<CourseInfo> courseInfoList) {
		this.courseInfoList = courseInfoList;
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
