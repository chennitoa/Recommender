package connectDB;

import java.sql.SQLException;

public interface PasswordDAO {
	public void storePassword(String pw);
	public void getAllPassword() throws SQLException;
	public void deleteAccount();
	public void changePassword(String oldPassword, String neePassowrd);
	
}
