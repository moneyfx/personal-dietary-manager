package app;

import static org.junit.Assert.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.junit.Test;

public class FoodListTest {
	
	//creation of Indining object
	@Test
	 public void inDiningInstantiatesCorrectly() {
		    Indining testIndining = new Indining("testname","testtime","testserving", "testtype");
		    assertEquals(true, testIndining instanceof Indining);
		  }
	//creation of Outdining object
	@Test
	 public void outDiningInstantiatesCorrectly() {
		    Outdining testOutdining = new Outdining("testretailer","testtime","testmeal", "testgroup");
		    assertEquals(true, testOutdining instanceof Outdining);
		  }
	
	//testing typing input it 4 text fields
	@Test
	public void testDiningInput() {
		FoodList foods = new FoodList();
		foods.setVisible(true);
		String testf1 = "testfield1";
		String testtime = "testfiledtime";
		String testf2 = "testfield2";
		String testf3 = "testfield3";
		
		assertNotNull(foods);
		
		JTextField input1 = (JTextField)TestUtils.getChildNamed(foods, "field1");
		        assertNotNull(input1);
		JTextField input2 = (JTextField)TestUtils.getChildNamed(foods, "fieldtime");
		       assertNotNull(input2);
		JTextField input3 = (JTextField)TestUtils.getChildNamed(foods, "field2");
		        assertNotNull(input3);
		JTextField input4 = (JTextField)TestUtils.getChildNamed(foods, "field3");
		        assertNotNull(input4);
		        
		input1.setText(testf1);
		input2.setText(testtime);
		input3.setText(testf2);
		input4.setText(testf3);
		
		assertEquals(testf1, input1.getText());
		assertEquals(testtime, input2.getText());
		assertEquals(testf2, input3.getText());
		assertEquals(testf3, input4.getText());
				
	}
	//testing that clicking add button adds a row to table
	@Test
	public void testAddButton() throws InterruptedException {
		FoodList foods = new FoodList();
		foods.setVisible(true);
		JButton add = (JButton)TestUtils.getChildNamed(foods, "addButton");
		JTable table = (JTable)TestUtils.getChildNamed(foods, "table");
		
		assertNotNull(add);
		assertNotNull(table);
		
		//add text to fields before triggering add
		JTextField input1 = (JTextField)TestUtils.getChildNamed(foods, "field1");
		JTextField input2 = (JTextField)TestUtils.getChildNamed(foods, "fieldtime");
		JTextField input3 = (JTextField)TestUtils.getChildNamed(foods, "field2");
		JTextField input4 = (JTextField)TestUtils.getChildNamed(foods, "field3");
		input1.setText("testtext");
		input2.setText("testtext");
		input3.setText("testtext");
		input4.setText("testtext");
		
		SwingUtilities.invokeLater(new Runnable() {
			            public void run() {
			               add.doClick();
		            }
		         });
		Thread.sleep(500);
		
		int rowCount = table.getRowCount(); //clicking add should add row
		assertEquals(1, rowCount); //checks that clicking add button adds a row			
	}
	
	@Test
	public void testDeleteRow() throws InterruptedException {
		FoodList foods = new FoodList();
		foods.setVisible(true);
		JButton delete = (JButton)TestUtils.getChildNamed(foods, "delete");
		JTable table = (JTable)TestUtils.getChildNamed(foods, "table");
		
		assertNotNull(delete);
		assertNotNull(table);
		
		//add one row to the table to be able to delete
		String[] dummyRow = {"field1", "field2", "field3", "field4", "field5"};
		((DefaultTableModel) table.getModel() ).addRow(dummyRow);
		
		//select 1st row
		table.setRowSelectionInterval(0,0);
		
		SwingUtilities.invokeLater(new Runnable() {
            public void run() {
               delete.doClick();
        }
     });
		Thread.sleep(500);
		int rowCount = table.getRowCount();
		assertEquals(0, rowCount);
			
		
	}
	
	@Test
	public void testCheckBoxes() {
		FoodList foods = new FoodList();
		foods.setVisible(true);
		
		JCheckBox checkbox1 = (JCheckBox)TestUtils.getChildNamed(foods, "box1");
		JCheckBox checkbox2 = (JCheckBox)TestUtils.getChildNamed(foods, "box2");
		JCheckBox checkbox3 = (JCheckBox)TestUtils.getChildNamed(foods, "box3");
		JCheckBox checkbox4 = (JCheckBox)TestUtils.getChildNamed(foods, "box4");
		
		assertNotNull(checkbox1);
		assertNotNull(checkbox2);
		assertNotNull(checkbox3);
		assertNotNull(checkbox4);	
	}
		

}


