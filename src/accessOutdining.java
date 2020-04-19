import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.table.DefaultTableModel;


public class accessOutdining {

	static Connection con  = DatabaseConnection.getConnection();
	
	//add for outdining item
	 public static int add(Outdining out) 
		        throws SQLException, ParseException 
		    { 	 
		 		Time itemTime = convertTime(out.getTime());	
		 		
		        String query = "INSERT IGNORE INTO fooditem (foodname, calories, carbs, protein, fat, id_foodgroup)"
		        		+ " VALUES (?, ?, ?, ?, ?, ?);"; 
		        PreparedStatement ps  = con.prepareStatement(query); 
		        ps.setString(1, out.getRetailer()); 
		        ps.setString(2, out.getCalories());
		        ps.setString(3, out.getCarbs());
		        ps.setString(4, out.getProtein());	
		        ps.setString(5, out.getFat());
		        
		        if (out.getGroup().equals("Fruits and Vegetables")) {
		        	ps.setInt(6, 1);
		        }
		        else if (out.getGroup().equals("Grain Products")) {
		        	ps.setInt(6, 2);
		        }
		        else if (out.getGroup().equals("Milk and Alternatives")) {
		        	ps.setInt(6, 3);
		        }
		        else if (out.getGroup().equals("Meat and Alternatives")) {
		        	ps.setInt(6, 4);
		        }
		        int n = ps.executeUpdate(); 
		                
		        String query2 = "INSERT INTO Outfoodentries (entry_time, entry_date, meal, id_fooditem) VALUES (?, ?, ?, (SELECT id_fooditem from fooditem where foodname ='" +out.getRetailer()+"'))";
		        PreparedStatement ps2  = con.prepareStatement(query2);
		        
		        ps2.setTime(1, itemTime);
		        ps2.setString(2, out.getDate());
		        ps2.setString(3, out.getMeal());		        
		         n = ps2.executeUpdate();		        
		        System.out.println("item added to database");
		        return n; 
		    }
	 //delete for outdining item
	 public static void delete(String name, String time, String date)
	 throws SQLException, ParseException{
		 
		 Time itemTime = convertTime(time);
		 String query 
         = "DELETE from Outfoodentries WHERE " + 
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
	     
	        String query = "SELECT fi.foodname, entry_time, entry_date, meal, fi.calories,  fi.fat, fi.carbs, fi.protein, fg.fg_name \n" + 
	        		"from Outfoodentries fe\n" + 
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
	            	row[0] = "Outdining";
	            	row[1] = rs.getString("foodname");
	            	row[2] = rs.getTime("entry_time").toString();
	            	row[3] = rs.getString("entry_date");
	            	row[4] = rs.getString("meal");
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
	 //private methods to correctly input into database...
	 
	 private static Time convertTime(String s) throws ParseException {
		 SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			long ms = sdf.parse(s).getTime();
			Time t = new Time(ms);
			System.out.println(t);
			return t;
	 }
}
