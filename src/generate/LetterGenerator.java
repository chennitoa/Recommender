package generate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import letter.CourseInfo;
import letter.LetterInfo;
import settings.SettingsManager;

public class LetterGenerator {
	
	private static LetterGenerator letterGenerator;
	
	private SettingsManager settingsManager;
	
	private List<String> schema;
	
	/*
	 * Finds a schema in the same folder, if not found, defaults to empty schema
	 */
	private LetterGenerator() {
		schema = new ArrayList<String>();
		settingsManager = SettingsManager.getSettingsManager();
		
		File file = new File("src/generate/LetterSchema.txt");
		Scanner scanner;
		try {
			scanner = new Scanner(file);
			while (scanner.hasNext()) {
				schema.add(scanner.nextLine());
			}
		}
		catch (FileNotFoundException e) {
			schema = new ArrayList<String>();
		}
	}
	
	public static LetterGenerator getLetterGenerator() {
		if (letterGenerator == null) {
			letterGenerator = new LetterGenerator();
		}
		return letterGenerator;
	}
	
	/*
	 * Replaces all empty spaces in line with given qualities
	 * Assumes all fields are filled out except maybe other courses
	 */
	public String replaceSchemas(String schemaLine, LetterInfo letterInfo) {
		schemaLine = schemaLine.replaceAll("%firstName%", letterInfo.getStudentInfo().getFirstName());
		schemaLine = schemaLine.replaceAll("%lastName%", letterInfo.getStudentInfo().getLastName());
		schemaLine = schemaLine.replaceAll("%date%", letterInfo.getStudentInfo().getDate().toString());
		
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
			schemaLine = schemaLine.replaceAll("%thesePlural%", "this");
			schemaLine = schemaLine.replaceAll("%coursePlural%", "");
		}
		
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
