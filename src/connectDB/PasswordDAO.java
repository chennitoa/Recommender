package connectDB;

import java.sql.SQLException;

public interface PasswordDAO {
	public void insert(String pw);
	public void query() throws SQLException;
	public void deleteTable();
	
}
