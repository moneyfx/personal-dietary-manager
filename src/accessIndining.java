import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;


public class accessIndining {

	static Connection con  = DatabaseConnection.getConnection();
	
	//add for indining item
	 public static int add(Indining in) 
		        throws SQLException, ParseException 
		    { 	 
		 		Time itemTime = convertTime(in.getTime());	
		 		
		        String query = "INSERT IGNORE INTO fooditem (foodname, calories, carbs, protein, fat, id_foodgroup)"
		        		+ " VALUES (?, ?, ?, ?, ?, ?);"; 
		        PreparedStatement ps  = con.prepareStatement(query); 
		        ps.setString(1, in.getName()); 
		        ps.setString(2, in.getCalories());
		        ps.setString(3, in.getCarbs());
		        ps.setString(4, in.getProtein());	
		        ps.setString(5, in.getFat());
		        
		        if (in.getGroup().equals("Fruits and Vegetables")) {
		        	ps.setInt(6, 1);
		        }
		        else if (in.getGroup().equals("Grain Products")) {
		        	ps.setInt(6, 2);
		        }
		        else if (in.getGroup().equals("Milk and Alternatives")) {
		        	ps.setInt(6, 3);
		        }
		        else if (in.getGroup().equals("Meat and Alternatives")) {
		        	ps.setInt(6, 4);
		        }
		        int n = ps.executeUpdate(); 
		                
		        String query2 = "INSERT INTO Infoodentries (entry_time, entry_date, serving, id_fooditem) VALUES (?, ?, ?, (SELECT id_fooditem from fooditem where foodname ='" +in.getName()+"'))";
		        PreparedStatement ps2  = con.prepareStatement(query2);
		        
		        ps2.setTime(1, itemTime);
		        ps2.setString(2, in.getDate());
		        ps2.setString(3, in.getServing());		        
		         n = ps2.executeUpdate();		        
		        System.out.println("item added to database");
		        return n; 
		    }
	 //delete for indining item
	 public static void delete(String name, String time, String date)
	 throws SQLException, ParseException{
		 
		 Time itemTime = convertTime(time);
		 String query 
         = "DELETE from Infoodentries WHERE " + 
         		"entry_time =? " + 
         		"and " + 
         		"entry_date = ? " + 
         		"and " + 
         		"id_fooditem = (SELECT id_fooditem from fooditem where foodname =? );"; 
     PreparedStatement ps 
         = con.prepareStatement(query); 
     ps.setTime(1, itemTime);
     ps.setString(2, date);
     ps.setString(3, name);
     ps.executeUpdate(); 
     System.out.println("row deleted");
		 
	 }
		 
	 //method to fill table from database
	 public static void FillTable(DefaultTableModel table)
	 throws SQLException{
	     
	        String query = "SELECT fi.foodname, entry_time, entry_date, serving, fi.calories,  fi.fat, fi.carbs, fi.protein, fg.fg_name \n" + 
	        		"from Infoodentries fe\n" + 
	        		"left join fooditem fi on fe.id_fooditem = fi.id_fooditem\n" + 
	        		"left join foodgroup fg on fi.id_foodgroup = fg.id_foodgroup;";
	        Statement st = con.createStatement();
	        ResultSet rs = st.executeQuery(query);

	        int columns = rs.getMetaData().getColumnCount();
	        while(rs.next())
	        {  
	            Object[] row = new Object[columns+1];
	            for (int i = 0; i < columns; i++)
	            {  
	            	row[0] = "Indining";
	            	row[1] = rs.getString("foodname");
	            	row[2] = rs.getTime("entry_time").toString();
	            	row[3] = rs.getString("entry_date");
	            	row[4] = rs.getString("serving");
	            	row[5] = rs.getString("calories");
	            	row[6] = rs.getString("fat");
	            	row[7] = rs.getString("carbs");
	            	row[8] = rs.getString("protein");
	            	row[9] = rs.getString("fg_name");
	            }
	             table.addRow(row);
	        }
	       
	         rs.close();
	         st.close();
	        
	     }
	 //prefill nutrition fields when the item entered already exists in the DB
	 public static String[] fillNutrition(String name) 
	 throws SQLException{
		 String[] facts = new String[4];
		 String query = "SELECT calories, fat, carbs, protein FROM fooditem "
		 		+ "WHERE foodname='"+name+"';";
		 
		 Statement st = con.createStatement();
	     ResultSet rs = st.executeQuery(query);
	     	     
	     while(rs.next()) {
	    	 facts[0] = rs.getString("calories");
	    	 facts[1] = rs.getString("fat");
	    	 facts[2] = rs.getString("carbs");
	    	 facts[3] = rs.getString("protein"); 
	     }		 
		 return facts;
	 } 
	 
	 //private method to correctly input Time into database...
	 
	 private static Time convertTime(String s) throws ParseException {
		 SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			long ms = sdf.parse(s).getTime();
			Time t = new Time(ms);
			System.out.println(t);
			return t;
	 }
}


//TODO:
//mark food groups. FIX WITH DATABASE Should it only be today's items?
//use queries from the database to filter what is displayed in view?
//Should the mehod to load the database be used directly in the view? go through model?
//anyway for everyone to access the database?
//opening/closing db connections
//better exception handling
//use arraylists in model?