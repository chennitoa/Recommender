package generate;

import java.util.List;

import letter.CourseInfo;
import letter.LetterInfo;
import settings.SettingsManager;
import util.FileHandler;

public class LetterGenerator {
	
	private SettingsManager settingsManager;
	private OtherCoursesGenerator otherCoursesGenerator;
	
	private List<String> schema;
	
	/*
	 * Finds a schema in the same folder, if not found, defaults to empty schema
	 */
	public LetterGenerator() {
		schema = FileHandler.getFileHandler().getFileContents("generate/LetterSchema.txt");
		settingsManager = SettingsManager.getSettingsManager();
		otherCoursesGenerator = OtherCoursesGenerator.getOtherCoursesGenerator();
		
	}
	
	/*
	 * Replaces all empty spaces in line with given qualities
	 * Assumes all fields are filled out except maybe other courses
	 */
	public String replaceSchemas(String schemaLine, LetterInfo letterInfo) {
		schemaLine = schemaLine.replaceAll("%firstName%", letterInfo.getStudentInfo().getFirstName());
		schemaLine = schemaLine.replaceAll("%lastName%", letterInfo.getStudentInfo().getLastName());
		schemaLine = schemaLine.replaceAll("%date%", letterInfo.getStudentInfo().getDate().toString());
		
		schemaLine = schemaLine.replaceAll("%semester%", letterInfo.getAcademicInfo().getSemesterName());
		schemaLine = schemaLine.replaceAll("%program%", letterInfo.getAcademicInfo().getProgramName());
		
		schemaLine = schemaLine.replaceAll("%firstCourses%", GrammarHelper.generateCommaSeperatedListWithQuotes(
				CourseInfo.getCourseNameFromList(letterInfo.getAcademicInfo().getFirstCourse())));
		schemaLine = schemaLine.replaceAll("%firstGrades%", GrammarHelper.generateCommaSeperatedListWithQuotes(
				CourseInfo.getCourseGradeFromList(letterInfo.getAcademicInfo().getFirstCourse())));
		
		if (letterInfo.getAcademicInfo().getFirstCourse().size() > 1) {
			schemaLine = schemaLine.replaceAll("%thisPlural%", "these");
			schemaLine = schemaLine.replaceAll("%coursePlural%", "s");
		}
		else {
			schemaLine = schemaLine.replaceAll("%thisPlural%", "this");
			schemaLine = schemaLine.replaceAll("%coursePlural%", "");
		}
		
		schemaLine = schemaLine.replaceAll("%otherCourses%",
				otherCoursesGenerator.generateOtherCoursesList(letterInfo.getAcademicInfo().getCourseInfoList()));
		
		schemaLine = schemaLine.replaceAll("%academicCharacteristics%", GrammarHelper.generateCommaSeperatedList(
				letterInfo.getAcademicInfo().getAcademicCharacteristicList()));
		schemaLine = schemaLine.replaceAll("%personalCharacteristics%", GrammarHelper.generateCommaSeperatedList(
				letterInfo.getAcademicInfo().getPersonalCharacteristicList()));
		
		schemaLine = schemaLine.replaceAll("%professorName%", settingsManager.getProfessorInfo().getName());
		schemaLine = schemaLine.replaceAll("%title%", settingsManager.getProfessorInfo().getTitle());
		schemaLine = schemaLine.replaceAll("%school%", settingsManager.getProfessorInfo().getSchool());
		schemaLine = schemaLine.replaceAll("%department%", settingsManager.getProfessorInfo().getDepartment());
		schemaLine = schemaLine.replaceAll("%email%", settingsManager.getProfessorInfo().getEmail());
		schemaLine = schemaLine.replaceAll("%phone%", settingsManager.getProfessorInfo().getPhone());
		
		if (letterInfo.getStudentInfo().getGender().equals("Male")) {
			schemaLine = schemaLine.replaceAll("%gender%", "he");
			schemaLine = schemaLine.replaceAll("%genderCapitalized%", "He");
		}
		else if (letterInfo.getStudentInfo().getGender().equals("Female")) {
			schemaLine = schemaLine.replaceAll("%gender%", "she");
			schemaLine = schemaLine.replaceAll("%genderCapitalized%", "She");
		}
		else {
			schemaLine = schemaLine.replaceAll("%gender%", "they");
			schemaLine = schemaLine.replaceAll("%genderCapitalized%", "They");
		}
		
		return schemaLine;
	}
	
	/*
	 * Takes letterInfo object, replaces set spots in schema with letterInfo
	 */
	public List<String> generateLetter(LetterInfo letterInfo) {
		for (int i = 0; i < schema.size(); i++) {
			schema.set(i, replaceSchemas(schema.get(i), letterInfo));
		}
		return schema;
	}
	
}
