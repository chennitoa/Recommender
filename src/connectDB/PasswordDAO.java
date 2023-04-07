package connectDB;

import java.sql.SQLException;

public interface PasswordDAO {
	public void storePassword(String pw);
	public void getAllPassword() throws SQLException;
	public void changePassword(String oldPassword, String neePassowrd);
	public boolean passwordExists(String password) throws SQLException;
}
