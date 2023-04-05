package connectDB;

import java.sql.Connection;  
import java.sql.DatabaseMetaData;  
import java.sql.DriverManager;  
import java.sql.SQLException;  
   
public class Create {  //Needs to be only called once
  
	
	
    public static void createNewDatabase(String fileName) {  
   
        String url = "jdbc:sqlite:" + fileName;  
   
        try {  
            Connection conn = DriverManager.getConnection(url);  
            if (conn != null) {  
                DatabaseMetaData meta = conn.getMetaData();  
                System.out.println("The driver name is " + meta.getDriverName());  
                System.out.println("A new database has been created.");  
            }  
   
        } catch (SQLException e) {  
            System.out.println(e.getMessage());  
        	}  
        
        DBManager obj = new DBManager(fileName);
        obj.createTable();
    }  
    public static void main(String[] args) throws SQLException {  
        createNewDatabase("password.db");  //Apply Singleton design pattern
        DBManager obj = new DBManager("password.db");
//        obj.insert("TestingPW55");
        obj.query();
//        obj.deleteTable();
    }  
}