package generate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import database.DBManager;
import database.QueryManager;

public class LetterManager {
	
	private static LetterManager letterManager;
	private DBManager dbManager; 
	
	private List<String> lastNames;
	private List<String> studentFiles;

	private LetterManager(){
		dbManager = DBManager.getDBManager();
		
		dbManager.queryQuiet(
				"CREATE TABLE IF NOT EXISTS studentFiles ("
				+ "firstName string not null"
				+ "lastName string not null"
				+ "pathToFile string not null"
				+ "year string not null"
				+ ");"
		);
		
	}
	
	public static LetterManager getStudentManager() {
		if (letterManager == null) {
			letterManager = new LetterManager();
		}
		return letterManager;
	}
	
	public void getLetterInfo() {
		ResultSet letterInfoSet = null;
		letterInfoSet = dbManager.query("SELECT lastName FROM studentFiles;");
		
		try {
			lastNames = QueryManager.parseToList(letterInfoSet, "lastName");
			studentFiles = QueryManager.parseToList(letterInfoSet, "pathToFile");
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}



