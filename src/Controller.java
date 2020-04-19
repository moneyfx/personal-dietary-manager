import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.regex.Pattern;

import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;

public class Controller {

    private View theView;
    private Model theModel;
    
    public static int vegCounter = 0;
    public static int grainCounter = 0;
    public static int milkCounter = 0;
    public static int meatCounter = 0;

    public Controller(View theView, Model theModel) {
        this.theView = theView;
        this.theModel = theModel;

        this.theView.diningOptionActionListener(new DiningOptionListener());
        this.theView.addButtonActionListener(new AddButtonListener());
        this.theView.deleteButtonActionListener(new DeleteButtonListener());
        this.theView.nameFieldFocusListener(new nameFocusListener()); 
    }
    
    //checks if input is a valid number accepts integers and decimal numbers
    private boolean inputInt(String text, String name) {
    
    	if (!text.equals("") && !(Pattern.matches("^[0-9]*\\.?[0-9]+$", text))) {
            JOptionPane.showMessageDialog(null, " Input in " + name +" invalid");
            return false;
    	}  	
    	else {
    		return true;
    	} 
    	
    }	
    
  //Check if time format is valid xx:xx, otherwise can't be converted to SQL time format.
	private boolean checkTimeInput(String time) {
		
		if ( !(Pattern.matches("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$", time))) {
            JOptionPane.showMessageDialog(null, " Time should be formatted as valid HH:mm");
            return false;
    	}  	
    	else {
    		return true;
    	} 
		
	}
	
    //method to clear fields once add button pressed
    private void clearFields() {
    	theView.setJtfName("");
        theView.setJtfServing("");
        theView.setCalories("");
        theView.setCarbs("");
        theView.setFat("");
        theView.setProtein("");
    }
    
    
    class DiningOptionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedDining = theView.getItemType();
            if (selectedDining == "Outdining") {
                theView.getLabelName().setText("Retailer");
                theView.getLabelServing().setText("Meal");
            } else {
                theView.getLabelName().setText("Name");
                theView.getLabelServing().setText("Serving");
            }
        }
    }

    class AddButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e)  {
        	
        	
            String dinningType = (String) theView.getItemType();
            String f1 = theView.getItemName();
            String f2 = theView.getItemServing();
            String selectedCheckbox = theView.getSelectedCheckBox();
            String timeNow = theView.getItemTime();
            String date = theView.getItemDate();
            String unit = theView.getItemUnit();
            String calories =theView.getCalories();
            String fat = theView.getFat();
            String carbs = theView.getCarbs();
            String protein = theView.getProtein();
            
            
           //Adding validation to the nutrition facts and time 
           boolean readyToAdd = true;
            if (!inputInt(calories, "calories")) {
            	theView.setCalories("");
            	readyToAdd = false;
            	
            }
            if (!inputInt(fat, "fat")) {
            	theView.setFat("");	
            	readyToAdd = false;
            	
            }
            if (!inputInt(carbs, "carbs")) {
            	theView.setCarbs("");
            	readyToAdd = false;
            	
            }
            if (!inputInt(protein, "protein")) {
            	theView.setProtein("");	
            	readyToAdd = false;
            }
            if (!checkTimeInput(timeNow)) {
            	readyToAdd = false;
            }
            

             if (dinningType == "Outdining" && readyToAdd) {
                Outdining outdining = new Outdining(f1, timeNow, date,f2, selectedCheckbox, calories, fat, carbs, protein);
                theModel.setOutdining(outdining);
                Object[] row = {dinningType, outdining.getRetailer(), outdining.getTime(), outdining.getDate(), 
                		outdining.getMeal(), outdining.getCalories(), outdining.getFat(),
                		outdining.getCarbs(), outdining.getProtein(), outdining.getGroup()};
                theView.getDefaultTable().addRow(row);
                
                try {
					accessOutdining.add(outdining);
				} catch (SQLException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                
                clearFields();
                
            } else if (dinningType == "Indining" && readyToAdd){
                Indining indining = new Indining(f1, timeNow, date, unit, f2, selectedCheckbox, calories, fat, carbs, protein);
                theModel.setIndining(indining);
                Object[] row = {dinningType, indining.getName(), indining.getTime(), indining.getDate(), 
                		indining.getServing(), indining.getCalories(), indining.getFat(),
                		indining.getCarbs(), indining.getProtein(), indining.getGroup()};
                
                theView.getDefaultTable().addRow(row);
                
                try {
					accessIndining.add(indining);
				} catch (SQLException | ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                
                clearFields();
            }
            // clearing fields
            
            
            
            
            //When a row is added, one of the food groups is marked as "eaten"
            if (selectedCheckbox == "Fruits and Vegetables") {
            	vegCounter = vegCounter + 1;
    			theView.setCheckBoxOneToEaten(); 
    		}
    		
    		else if (selectedCheckbox == "Grain Products") {
    			grainCounter = grainCounter + 1;
    			theView.setCheckBoxTwoToEaten(); 
    		}
    		
    		else if (selectedCheckbox == "Milk and Alternatives") {
    			milkCounter = milkCounter + 1;
    			theView.setCheckBoxThreeToEaten(); 
    		}
    		
    		else if (selectedCheckbox == "Meat and Alternatives") {
    			meatCounter = meatCounter + 1;
    			theView.setCheckBoxFourToEaten(); 
    		} 
        }
    }

    class DeleteButtonListener implements ActionListener {

            @Override
            public void actionPerformed (ActionEvent e){
                // check for selected row first
                if (theView.getTable().getSelectedRow() != -1) {
                    // remove selected row from the model
                	int row = theView.getTable().getSelectedRow();
                	String groupValue = (String)theView.getTable().getValueAt(row, 9);
                	
                	String name = (String)theView.getTable().getValueAt(row, 1);
                	String time = (String)theView.getTable().getValueAt(row, 2);
                	String date = (String)theView.getTable().getValueAt(row, 3);
                	
                //DELETE from view                	
                	theView.getDefaultTable().removeRow(row);
                //DELETE from database
                	try {
						accessIndining.delete(name, time, date);
						accessOutdining.delete(name, time, date);
					} catch (SQLException | ParseException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
 
                	
                	//Update eaten / not eaten boxes, if needed
                    if (groupValue == "Fruits and Vegetables") {
                    	vegCounter = vegCounter - 1;
                    	if (vegCounter == 0) {
                			theView.setCheckBoxOneToNotEaten(); 
                		}
            		}
            		
            		else if (groupValue == "Grain Products") {
            			grainCounter = grainCounter - 1;
            			if (grainCounter == 0) {
                			theView.setCheckBoxTwoToNotEaten(); 
                		}
            		}
            		
            		else if (groupValue == "Milk and Alternatives") {
            			milkCounter = milkCounter - 1;
            			if (milkCounter == 0) {
                			theView.setCheckBoxThreeToNotEaten();
            			}
            		}
            		
            		else if (groupValue == "Meat and Alternatives") {
            			meatCounter = meatCounter - 1;
            			 if (meatCounter == 0) {
                			theView.setCheckBoxFourToNotEaten(); 
                		}
            		}
                }
            }
        }
    //prefill nutrition facts from database
    class nameFocusListener extends FocusAdapter {
     	
    	public void focusLost(FocusEvent e) {
    		
    	try {
			String[] texttoEnter = accessIndining.fillNutrition(theView.getItemName());
			theView.setCalories(texttoEnter[0]);
			theView.setFat(texttoEnter[1]);
			theView.setCarbs(texttoEnter[2]);
			theView.setProtein(texttoEnter[3]);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}  	
    	}
    	}
    }
    

