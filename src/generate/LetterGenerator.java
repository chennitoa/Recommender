package generate;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import letter.LetterInfo;

public class LetterGenerator {
	
	private static LetterGenerator letterGenerator;
	
	private String schema;
	
	private LetterGenerator() {
		File file = new File("src/generate/LetterSchema.html");
		System.out.println(file.getAbsolutePath());
		Scanner scanner;
		try {
			scanner = new Scanner(file);
			schema = scanner.nextLine();
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static LetterGenerator getLetterGenerator() {
		if (letterGenerator == null) {
			letterGenerator = new LetterGenerator();
		}
		return letterGenerator;
	}
	
	public String replaceSchemas(String schema, LetterInfo letterInfo) {
		schema = schema.replaceAll("%firstName%", letterInfo.getStudentInfo().getFirstName());
		schema = schema.replaceAll("%lastName%", letterInfo.getStudentInfo().getLastName());
		return schema;
	}
	
	/*
	 * Takes letterInfo object, replaces empty space in schema with letterInfo
	 */
	public String generateLetter(LetterInfo letterInfo) {
		replaceSchemas(schema, letterInfo);
		return schema;
	}
	
}
