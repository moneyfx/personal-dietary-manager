

import static org.junit.Assert.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.junit.Test;

public class allTests {
	
	private static String name = "name";
	private static String time = "time";
	private static String date = "date";
	private static String unit = "unit";
	private static String serving = "serving";
	private static String group = "group";
	private static String calories ="calories";
	private static String fat = "fat";
	private static String carbs = "carbs";
	private static String protein = "protein";
	private static String retailer = "retailer";
	private static String meal = "meal";
			
	
	
	//creation of Indining object
	@Test
	 public void inDiningInstantiatesCorrectly() {
		    Indining testIndining = new Indining("name", "time", "date", "unit", "serving", 
		    		"group", "calories", "fat", "carbs", "protein");
		    assertEquals(true, testIndining instanceof Indining);
		  }
	//creation of Outdining object
	@Test
	 public void outDiningInstantiatesCorrectly() {
		    Outdining testOutdining = new Outdining("retailer", "time", "date", "unit", "meal", 
		    		"group", "calories", "fat", "carbs", "protein");
		    assertEquals(true, testOutdining instanceof Outdining);
		  }

	@Test
	public void viewInstantiatesCorrectly() {
		View testview = new View();
		testview.setVisible(true);
		assertNotNull(testview);
	}

   @Test
   public void controllerInstantiatesCorrectly() {
	   View testview = new View();
	   Model testmodel = new Model();
	   Controller testcontroller = new Controller(testview,testmodel);
       testview.setVisible(true);    
       assertNotNull(testcontroller);
			 	   
   }


 @Test
 public void textInputsIndining() {
	 View testview = new View();
	   Model testmodel = new Model();
	   Indining in = new Indining(name, time, date, unit, serving, 
	    		group, calories, fat, carbs, protein);
	   new Controller(testview,testmodel);
       testview.setVisible(true); 
       
       assertEquals(in.getCalories(), calories);
       assertEquals(in.getCarbs(), carbs);
       assertEquals(in.getProtein(), protein);
       assertEquals(in.getFat(), fat);
       assertEquals(in.getTime(), time);
       assertEquals(in.getDate(), date);
       assertEquals(in.getUnit(), unit);
       assertEquals(in.getServing(), serving);
       assertEquals(in.getGroup(), group);
       assertEquals(in.getName(), name);
	 
 }
 
 @Test
 public void textInputsOutdining() {
	 View testview = new View();
	   Model testmodel = new Model();
	   Outdining out = new Outdining(retailer, time, date, unit, meal, 
	    		group, calories, fat, carbs, protein);
	   new Controller(testview,testmodel);
       testview.setVisible(true); 
       
       assertEquals(out.getCalories(), calories);
       assertEquals(out.getCarbs(), carbs);
       assertEquals(out.getProtein(), protein);
       assertEquals(out.getFat(), fat);
       assertEquals(out.getTime(), time);
       assertEquals(out.getDate(), date);
       assertEquals(out.getUnit(), unit);
       assertEquals(out.getMeal(), meal);
       assertEquals(out.getGroup(), group);
       assertEquals(out.getRetailer(), retailer);
	 
 }
 
}

