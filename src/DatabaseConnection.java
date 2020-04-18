import java.sql.*;

public class DatabaseConnection {
	
	private static Connection con = null;
	
	  static
	    { 
		  	//set to a local mySQL database
			String url = "jdbc:mysql://localhost:3306/personaldiet_db?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	        String user = "root"; 
	        String pass = "password123!"; 
	        try { 
	            Class.forName("com.mysql.cj.jdbc.Driver"); 
	            con = DriverManager.getConnection(url, user, pass); 
	            System.out.println("Connected to database");
	        } 
	        catch (ClassNotFoundException | SQLException e) { 
	            e.printStackTrace(); 
	        } 
	    } 
	    public static Connection getConnection() 
	    { 
	        return con; 
	    } 
}
