package letter;

public class LetterInfo {
	
	private StudentInfo studentInfo;
	private AcademicInfo academicInfo;
	
	public LetterInfo(StudentInfo studentInfo, AcademicInfo academicInfo) {
		this.studentInfo = studentInfo;
		this.academicInfo = academicInfo;
	}
	
	public StudentInfo getStudentInfo() {
		return studentInfo;
	}

	public void setStudentInfo(StudentInfo studentInfo) {
		this.studentInfo = studentInfo;
	}

	public AcademicInfo getAcademicInfo() {
		return academicInfo;
	}

	public void setAcademicInfo(AcademicInfo academicInfo) {
		this.academicInfo = academicInfo;
	}
	
}
