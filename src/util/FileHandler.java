package util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileHandler {
	
	private static FileHandler fileHandler;
	
	/**
	 * Private constructor to restrict access.
	 */
	private FileHandler() {
		
	}
	
	/**
	 * Returns singleton instance of FileHandler.
	 */
	public static FileHandler getFileHandler() {
		if (fileHandler == null) {
			fileHandler = new FileHandler();
		}
		return fileHandler;
	}
	
	/**
	 * Get list of strings from file.
	 * Use for internal files only.
	 * @Precondition pathToFile is a valid file.
	 */
	public List<String> getFileContents(String pathToFile) {
		InputStream fileStream = this.getClass().getResourceAsStream("../" + pathToFile);
		Scanner scanner = new Scanner(fileStream);
		
		List<String> fileContents = new ArrayList<String>();
		while (scanner.hasNextLine()) {
			fileContents.add(scanner.nextLine());
		}
		
		scanner.close();
		return fileContents;
	}
	
	/**
	 * Get list of strings from file.
	 * Use for external files only.
	 */
	public List<String> getFileContents(File file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		
		List<String> fileContents = new ArrayList<String>();
		while (scanner.hasNextLine()) {
			fileContents.add(scanner.nextLine());
		}
		
		scanner.close();
		return fileContents;
	}
	
	/**
	 * Write list of strings to file.
	 * Use for external files.
	 */
	public void writeToFile(String pathToFile, List<String> contentToWrite) throws IOException {
		FileOutputStream fileOutputStream;
		try {
			fileOutputStream = new FileOutputStream(pathToFile);
		}
		catch (FileNotFoundException e) {
			File file = new File(pathToFile);
			file.createNewFile();
			fileOutputStream = new FileOutputStream(file);
		}
		PrintWriter fileWriter = new PrintWriter(fileOutputStream);
		
		for (String content: contentToWrite) {
			fileWriter.println(content);
		}
		fileWriter.close();
		fileOutputStream.close();
	}
	
}
