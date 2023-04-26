package search;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import database.DBManager;
import filetracker.FileInfo;

public class LetterManager {
	
	private static LetterManager letterManager;
	private DBManager dbManager; 
	
	private List<FileInfo> letterFiles;
	
	/**
	 * Private constructor for letter manager, sets up the database table
	 */
	private LetterManager(){
		dbManager = DBManager.getDBManager();
		
		dbManager.queryQuiet(
				"CREATE TABLE IF NOT EXISTS studentFiles ("
				+ "firstName string not null, "
				+ "lastName string not null, "
				+ "year string not null, "
				+ "pathToFile string not null"
				+ ");"
		);
		
		letterFiles = getFileInfo();
		validateFileInfo();
	}
	
	/**
	 * Gets the singleton instance of letter manager
	 * @return An instance of letter manager
	 */
	public static LetterManager getLetterManager() {
		if (letterManager == null) {
			letterManager = new LetterManager();
		}
		return letterManager;
	}
	
	/**
	 * Deletes all values in the table where the files do not exist
	 */
	public void validateFileInfo() {
		List<FileInfo> letterFilesClone = new ArrayList<FileInfo>(letterFiles); 
		for (FileInfo fileInfo : letterFilesClone) {
			File file = new File(fileInfo.getPathToFile());
			if (!file.isFile()) {
				dbManager.queryQuiet(
						"DELETE FROM studentFiles WHERE "
						+ "firstName=\"" + fileInfo.getFirstName() + "\" AND "
						+ "lastName=\"" + fileInfo.getLastName() + "\" AND "
						+ "year=\"" + fileInfo.getLetterYear() + "\" AND "
						+ "pathToFile=\"" + fileInfo.getPathToFile() + "\";"
				);
				letterFiles.remove(fileInfo);
			}
		}
	}
	
	/**
	 * Makes a database query for file info objects.
	 * @return The list of file info objects
	 */
	public List<FileInfo> getFileInfo() {
		ResultSet letterInfoSet = dbManager.query("SELECT * FROM studentFiles;");
		try {
			if (letterInfoSet == null || letterInfoSet.next() == false) {
				return new ArrayList<FileInfo>();
			}
			List<FileInfo> letterInfoList = new ArrayList<FileInfo>();
			letterInfoList.add(new FileInfo(
				letterInfoSet.getString("firstName"),
				letterInfoSet.getString("lastName"),
				letterInfoSet.getString("year"),
				letterInfoSet.getString("pathToFile")
			));
			while (letterInfoSet.next() == true) {
				letterInfoList.add(new FileInfo(
						letterInfoSet.getString("firstName"),
						letterInfoSet.getString("lastName"),
						letterInfoSet.getString("year"),
						letterInfoSet.getString("pathToFile")
				));
			}
			return letterInfoList;
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	/**
	 * Inserts a file info object into the database if an identical one is not already present
	 * @param fileInfo The file info object to insert
	 */
	public void insertIfAbsent(FileInfo fileInfo) {
		for (FileInfo fileInfoToCompare : letterFiles) {
			if (fileInfo.equals(fileInfoToCompare)) {
				return;
			}
		}
		if (fileInfo.getPathToFile() != null) {
			letterFiles.add(fileInfo);
			dbManager.queryQuiet("INSERT INTO studentFiles (firstName, lastName, year, pathToFile) VALUES ("
					+ "\"" + fileInfo.getFirstName() + "\", "
					+ "\"" + fileInfo.getLastName() + "\", "
					+ "\"" + fileInfo.getLetterYear() + "\", "
					+ "\"" + fileInfo.getPathToFile() + "\");"
			);
		}
	}
	
	/**
	 * Returns the subset of the list that matches the last name
	 * @param lastName The last name to check against other last names
	 * @return A list of partial / full matches to the last name
	 */
	public List<FileInfo> getMatching(String lastName) {
		letterFiles = getFileInfo();
		validateFileInfo();
		List<FileInfo> matchingFiles = new ArrayList<FileInfo>();
		for (FileInfo fileInfo : letterFiles) {
			if (fileInfo.getLastName().toLowerCase().indexOf(lastName.toLowerCase()) != -1) {
				matchingFiles.add(fileInfo);
			}
		}
		return matchingFiles;
	}
	
}



