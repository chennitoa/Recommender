package util;

import java.io.File;
import java.io.FileNotFoundException;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileSelector {
	
	/**
	 * Filters .recc files, which are Recommender Letter Files
	 */
	public static final FileChooser.ExtensionFilter LETTER_FILTER = new FileChooser.ExtensionFilter(
			"Recommneder Letter File", "*.recc");
	
	/**
	 * Filters .txt files
	 */
	public static final FileChooser.ExtensionFilter TEXT_FILTER = new FileChooser.ExtensionFilter(
			"Text File", "*.txt");
	
	
	/**
	 * Opens a file chooser for the file to open
	 * @param extensionFilter The extension filter to use in the file chooser
	 * @return The path of the file chosen
	 * @throws FileNotFoundException
	 */
	public static String getChooseFilePath(FileChooser.ExtensionFilter extensionFilter) {
		FileChooser letterFileChooser = new FileChooser();
		letterFileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		letterFileChooser.setTitle("Open");
		
		letterFileChooser.getExtensionFilters().add(extensionFilter);
		File f = letterFileChooser.showOpenDialog(new Stage());
		if (f != null) {
			return f.getPath();
		}
		else {
			return null;
		}
	}
	
	/**
	 * Opens a file chooser for the file to create
	 * @param extensionFilter The extension filter to use in the file chooser
	 * @return The path of the file chosen
	 */
	public static String getCreateFilePath(FileChooser.ExtensionFilter extensionFilter, String initialFileName) {
		FileChooser letterFileChooser = new FileChooser();
		letterFileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		letterFileChooser.setTitle("Save As...");
		
		letterFileChooser.getExtensionFilters().add(extensionFilter);
		letterFileChooser.setInitialFileName(initialFileName);
		File f = letterFileChooser.showSaveDialog(new Stage());
		if (f != null) {
			return f.getPath();
		}
		else {
			return null;
		}
	}
}
