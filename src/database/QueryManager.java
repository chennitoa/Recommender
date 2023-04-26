package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 * Utility class for parsing queries
 */
public class QueryManager {
	/*
	 * Given results of query, parses them into a list, returns null if list would be empty
	 */
	public static List<String> parseToList(ResultSet rs, String columnTitle) {
		try {
			if (rs == null || rs.next() == false) {
				return Collections.emptyList();
			}
			ArrayList<String> resultsList = new ArrayList<String>();
			resultsList.add(rs.getString(columnTitle));
			while (rs.next() == true) {
				if (!rs.getString(columnTitle).equals("")) {
					resultsList.add(rs.getString(columnTitle));
				}
			}
			return resultsList;
		}
		catch (SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
}
