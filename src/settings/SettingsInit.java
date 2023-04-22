package settings;

import java.util.ArrayList;
import java.util.Arrays;

public class SettingsInit {
	
	private static SettingsManager settingsManager = SettingsManager.getSettingsManager();
	
	public static void initializeDatabases() {
		ProfessorInfo professorInfo = new ProfessorInfo(
				"Ahmad Yazdankhah",
				"Lecturer",
				"SJSU",
				"CS Department",
				"ahmad.yazdankhah@sjsu.edu",
				"(123) 456-7890");
		ArrayList<String> semesters = new ArrayList<String>(Arrays.asList("Spring" , "Fall", "Summer"));
		ArrayList<String> courses = new ArrayList<String>(Arrays.asList("CS151: Objected-Oriented Design",
													"CS166: Information Security",
													"CS154: Theory of Computation",
													"CS160: Software Engineering",
													"CS256: Cryptography",
													"CS146: Data Structures and Algorithms",
													"CS152: Programming Languages Paradigm"));

		ArrayList<String> programs = new ArrayList<String>(Arrays.asList("Master of science (MS)",
													"Master of business administration (MBA)",
													"Doctor of philosophy (PhD)"));
		
		ArrayList<String> personalCharacteristics = new ArrayList<String>((Arrays.asList("very passionate",
																	"very enthusiastic",
																	"punctual",
																	"attentive",
																	"polite")));

		ArrayList<String> academicCharacteristics = new ArrayList<String>(Arrays.asList("submitted well-written assignments",
																	"participated in all of my class activities",
																	"worked hard",
																	"was very well prepared for every exam and assignment",
																	"picked up new skills quickly",
																	"was able to excel academically at the top of my class"));

		settingsManager.setProfessorInfo(professorInfo);
		settingsManager.setSemesters(semesters);
		settingsManager.setCourses(courses);
		settingsManager.setPrograms(programs);
		settingsManager.setPersonalCharacteristics(personalCharacteristics);
		settingsManager.setAcademicCharacteristics(academicCharacteristics);
	}
	
}
