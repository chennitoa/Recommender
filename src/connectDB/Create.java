package connectDB;

import java.sql.Connection;  
import java.sql.DatabaseMetaData;  
import java.sql.DriverManager;  
import java.sql.SQLException;  
   
public class Create {  
  
    public static void createNewDatabase(String fileName) {  
   
        String url = "jdbc:sqlite:C:/sqlite/" + fileName;  //Needs to figure out how to set up 
        												   //an online DB so everyone who runs the application can connect to the DB
   
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
    }  
    public static void main(String[] args) {  
        createNewDatabase("password.db");  
    }  
}