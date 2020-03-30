import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
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
        public void actionPerformed(ActionEvent e) {
            String dinningType = (String) theView.getItemType();
            String f1 = theView.getItemName();
            String f2 = theView.getItemServing();
            String selectedCheckbox = theView.getSelectedCheckBox();
            String timeNow = theView.getItemTime();
            String date = theView.getItemDate();
            String unit = theView.getItemUnit();
            String calories = theView.getCalories();
            String fat = theView.getFat();
            String carbs = theView.getCarbs();
            String protein = theView.getProtein();

            if (dinningType == "Outdining") {
                Outdining outdining = new Outdining(f1, timeNow, date, unit, f2, selectedCheckbox, calories, fat, carbs, protein);
                theModel.setOutdining(outdining);
                Object[] row = {dinningType, outdining.getRetailer(), outdining.getTime(), outdining.getDate(), 
                		outdining.getMeal(), outdining.getUnit(), outdining.getCalories(), outdining.getFat(),
                		outdining.getCarbs(), outdining.getProtein(), outdining.getGroup()};
                theView.getDefaultTable().addRow(row);
            } else {
                Indining indining = new Indining(f1, timeNow, date, unit, f2, selectedCheckbox, calories, fat, carbs, protein);
                theModel.setIndining(indining);
                Object[] row = {dinningType, indining.getName(), indining.getTime(), indining.getDate(), 
                		indining.getServing(), indining.getUnit(), indining.getCalories(), indining.getFat(),
                		indining.getCarbs(), indining.getProtein(), indining.getGroup()};
                theView.getDefaultTable().addRow(row);
            }
            // clearing fields
            theView.setJtfName("");
            theView.setJtfServing("");
            theView.setCalories("");
            theView.setCarbs("");
            theView.setFat("");
            theView.setProtein("");
            
            
            
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
                	String groupValue = (String)theView.getTable().getValueAt(row, 10);
                	theView.getDefaultTable().removeRow(row);
                	
                	//Update eaten / not eaten boxes, if needed
                    if (groupValue == "Vegetables and Fruit") {
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
    }

