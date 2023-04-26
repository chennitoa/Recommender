package generate;

import java.util.ArrayList;
import java.util.List;

import letter.CourseInfo;
import util.FileHandler;

public class OtherCoursesGenerator {
	
	private static OtherCoursesGenerator otherCoursesGenerator;
	
	private String schema;
	
	private OtherCoursesGenerator() {
		schema = FileHandler.getFileHandler().getFileContents("generate/OtherCoursesSchema.txt").get(0);
	}
	
	/**
	 * Returns the singleton instance of OtherCoursesGenerator.
	 * @return An instance of OtherCoursesGenerator
	 */
	public static OtherCoursesGenerator getOtherCoursesGenerator() {
		if (otherCoursesGenerator == null) {
			otherCoursesGenerator = new OtherCoursesGenerator();
		}
		return otherCoursesGenerator;
	}
	
	/**
	 * Generates a string from a list of courses.
	 * @param courses The list of courses to generate a string from
	 * @return The string as a grammatically correct list of strings
	 */
	public String generateOtherCoursesList(List<CourseInfo> courses) {
		if (courses == null || courses.size() == 0) {
			schema = "%delete%";
			return schema;
		}
		List<String> preparedCourseStatements = new ArrayList<String>();
		for (CourseInfo course : courses) {
			preparedCourseStatements.add("\"" + course.getCourseGrade() + "\" from my \"" + course.getCourseName() + "\"");
		}
		schema = schema.replaceAll("%otherCourses%", GrammarHelper.generateCommaSeperatedList(preparedCourseStatements));

		if (courses.size() > 1) {
			schema = schema.replaceAll("%coursePlural%", "s");
		}
		else {
			schema = schema.replaceAll("%coursePlural%", "");
		}
		return schema;
	}
	
}
