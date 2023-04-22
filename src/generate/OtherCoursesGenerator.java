package generate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import letter.CourseInfo;

public class OtherCoursesGenerator {
	
	private String schema;
	
	public OtherCoursesGenerator() {
		try {
			File file = new File(getClass().getResource("OtherCoursesSchema.txt").toURI());
			Scanner scanner = new Scanner(file);
			schema = scanner.nextLine();
			scanner.close();
		}
		catch (Exception e) {
			schema = "";
		}
		
	}
	
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
