import javax.swing.*;
import java.awt.Dimension;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;
import java.util.ArrayList;

public class View extends JFrame implements Observer {
	
	//This is the main window called mainDietTypePanel:
    private JPanel mainPanel = new JPanel(new GridLayout(2,0));
    
    //The main panel is divided into a top and a bottom panel:
    private JPanel topPanel = new JPanel(new BorderLayout());
    private JPanel bottomPanel = new JPanel(new GridLayout(3,0));
    
    //Top panel
    
    //The top panel includes the table and the filter panel.
    private JTable table;
    private DefaultTableModel defaultTable;
    private JPanel mainFilterPanel = new JPanel(new GridLayout(0,3));
    private JPanel filterPanel = new JPanel();
    
    //The filter panel includes a text field for search and a label
	private JTextField jtfFilter = new JTextField(15);
	private JLabel labelFilter = new JLabel("Specify a word to filter view:");
    
	//Bottom panel
	
	//The bottom panel includes 3 panels: Main Diet Type panel, Hierarchy View panel, Buttons + Food Groups panel.
	 private JPanel mainDietTypePanel = new JPanel(new GridLayout(2,0));
	 private JPanel hierarchyViewPanel = new JPanel(new GridLayout(0,2));
	 private JPanel buttonAndFoodGroupPanel = new JPanel(new GridLayout(2,0));
	
	//The Main Diet Type panel includes 2 sub-panels:
	 private JPanel mainDiet = new JPanel();
	 private JPanel foodGroup = new JPanel();
	 
	 //The Main Diet title panel includes the following:
	 
	private String[] options = new String[]{"Indining", "Outdining"};
    private JComboBox<String> diningOptions = new JComboBox<>(options);
    private JTextField jtfName = new JTextField(15);
    private JLabel labelName = new JLabel("Name");
      
    //The Food Groups panel includes the following:
    JCheckBox upperCheckbox1 = new JCheckBox("Fruits and Vegetables");
    JCheckBox upperCheckbox2 = new JCheckBox("Grain Products");
    JCheckBox upperCheckbox3 = new JCheckBox("Milk and Alternatives");
    JCheckBox upperCheckbox4 = new JCheckBox("Meat and Alternatives");
    
    //Creating an Array List of checkboxes
    private ArrayList <JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
    
    private ButtonGroup checkBoxGroup = new ButtonGroup(); 
    
    //The Hierarchy View panel includes 2 panels:
    
    private JPanel dateAndTimePanel = new JPanel();
    private JPanel servingPanel = new JPanel();
    
    //The Date and Time panel includes the following:

    private JTextField jtfTime = new JTextField(10);
    private JLabel labelTime = new JLabel("Time");
    
    private JTextField jtfDate = new JTextField(15);
    private JLabel labelDate = new JLabel("Date");
    
    //The Serving Panel includes 2 panels: the Serving Size Panel and the Nutrition Facts panel
    private JPanel servingSizePanel = new JPanel();
    private JPanel nutritionFactsPanel = new JPanel();
    
    //The Serving Size Panel includes the following:

    private JTextField jtfServing = new JTextField(15);
    private JLabel labelServing = new JLabel("Serving size");
    
    private String [] units = new String[] {"portion", "item / items","g","ml","tbsp","tsp","cup / cups"};
    private JComboBox<String> unitOptions = new JComboBox<>(units);
    private JLabel labelUnit = new JLabel("Unit");

    
    //The Nutrition facts panel includes the following:
 
    private JTextField jtfCalories = new JTextField(8);
    private JLabel labelCalories = new JLabel("Calories");
    
    private JTextField jtfFat = new JTextField(8);
    private JLabel labelFat = new JLabel("Fat");
    
    private JTextField jtfCarbs = new JTextField(8);
    private JLabel labelCarbs = new JLabel("Carbs");
    
    private JTextField jtfProtein = new JTextField(8);
    private JLabel labelProtein = new JLabel("Protein");
    
    //The Button and Food Group panel includes 2 panels:
    private JPanel buttonPanel = new JPanel();
	private JPanel foodGroupPanel = new JPanel();
    
	//The Buttons panel includes the following:
    private JButton addButton = new JButton("Add");
    private JButton deleteButton = new JButton("Delete selected row");
    
    //The Food Groups panel includes the following:
    private JLabel labelCheckbox1 = new JLabel("Fruits and Vegetables:");
    JCheckBox lowerCheckbox1 = new JCheckBox("eaten");
    private JLabel labelCheckbox2 = new JLabel("Grain Products:");
    JCheckBox lowerCheckbox2 = new JCheckBox("eaten");
    private JLabel labelCheckbox3 = new JLabel("Milk and Alternatives:");
    JCheckBox lowerCheckbox3 = new JCheckBox("eaten");
    private JLabel labelCheckbox4 = new JLabel("Meat and Alternatives:");
    JCheckBox lowerCheckbox4 = new JCheckBox("eaten");

    public View() {
   
        // Configuring the top panel
    	
    	//Adding column headers
        String[] columns = new String[]{"Dining type", "Name/Retailer", "Time", "Date", "Serving/Meal", 
        		"Unit", "Calories", "Fat (gr)", "Carbohydrate (gr)", "Protein (gr)", "Group"}; 

        //Data for the table in a 2d array
        Object[][] data = new Object[][]{};

        defaultTable = new DefaultTableModel(data, columns);
        table = new JTable(defaultTable);
        topPanel.add(table, BorderLayout.NORTH);
        
        //creating a sorter
        TableRowSorter<TableModel> rowSorter = new TableRowSorter<>(table.getModel());
        table.setRowSorter(rowSorter);
       
        
        //Creating a filter
        jtfFilter.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
        
        //Configuring filter
        filterPanel.add(labelFilter);
        filterPanel.add(jtfFilter);
        filterPanel.setBackground(Color.WHITE);
        mainFilterPanel.setBackground(Color.WHITE);
        mainFilterPanel.add(filterPanel);
        topPanel.add(mainFilterPanel, BorderLayout.SOUTH);
        
        //for testing
        table.setName("table");
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        topPanel.add(new JScrollPane(table), BorderLayout.NORTH);
        mainPanel.add(topPanel);
        
        // Bottom Half
        
        // settingUp time and date
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE MMMM dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat ("HH:mm a");
        String stringDate = sdf.format(new Date());
        String stringTime = sdf2.format(cal.getTime());
        jtfTime.setText(stringTime);
        jtfDate.setText(stringDate);


        bottomPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Personal Dietary Manager - PROTOTYPE", TitledBorder.CENTER, TitledBorder.TOP));
        
        bottomPanel.add(mainDietTypePanel);
        
        mainDietTypePanel.add(mainDiet);
     
        mainDiet.add(diningOptions);
        mainDiet.add(labelName);
        mainDiet.add(jtfName);
        
        mainDietTypePanel.add(foodGroup);
        
        foodGroup.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Select Food Group", TitledBorder.CENTER, TitledBorder.TOP));
        
        foodGroup.add(upperCheckbox1);
        foodGroup.add(upperCheckbox2);
        foodGroup.add(upperCheckbox3);
        foodGroup.add(upperCheckbox4);
        
        //Adding checkboxes to an array list
        checkBoxes.add(upperCheckbox1);
        checkBoxes.add(upperCheckbox2);
        checkBoxes.add(upperCheckbox3);
        checkBoxes.add(upperCheckbox4);
        
        //Adding checkboxes to a Button Group (ensuring that only one option could be selected)
        checkBoxGroup.add(upperCheckbox1);
        checkBoxGroup.add(upperCheckbox2);
        checkBoxGroup.add(upperCheckbox3);
        checkBoxGroup.add(upperCheckbox4);
        
        bottomPanel.add(hierarchyViewPanel);
        
        hierarchyViewPanel.add(dateAndTimePanel);
        
        dateAndTimePanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Time and Date", TitledBorder.CENTER, TitledBorder.TOP));
        dateAndTimePanel.add(labelTime);
        dateAndTimePanel.add(jtfTime);
        dateAndTimePanel.add(labelDate);
        dateAndTimePanel.add(jtfDate);
        
        hierarchyViewPanel.add(servingPanel);
        servingPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Nutrition Facts", TitledBorder.CENTER, TitledBorder.TOP));
        servingPanel.add(servingSizePanel);
       
        servingSizePanel.add(labelServing);
        servingSizePanel.add(jtfServing);
        servingSizePanel.add(labelUnit);
        servingSizePanel.add(unitOptions);
        
        servingPanel.add(nutritionFactsPanel);
        
        nutritionFactsPanel.add(labelCalories);
        nutritionFactsPanel.add(jtfCalories);
        nutritionFactsPanel.add(labelFat);
        nutritionFactsPanel.add(jtfFat);
        nutritionFactsPanel.add(labelCarbs);
        nutritionFactsPanel.add(jtfCarbs);
        nutritionFactsPanel.add(labelProtein);
        nutritionFactsPanel.add(jtfProtein);
        
        bottomPanel.add(buttonAndFoodGroupPanel);
        
        //Configuring the buttonPanel
        buttonAndFoodGroupPanel.add(buttonPanel);
        buttonPanel.setBorder(BorderFactory.createEtchedBorder());
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        // Configuring the foodGroupPanel.
        buttonAndFoodGroupPanel.add(foodGroupPanel);
        foodGroupPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Food Groups Eaten", TitledBorder.CENTER, TitledBorder.TOP));
        foodGroupPanel.add(labelCheckbox1);
        foodGroupPanel.add(lowerCheckbox1);
        foodGroupPanel.add(labelCheckbox2);
        foodGroupPanel.add(lowerCheckbox2);
        foodGroupPanel.add(labelCheckbox3);
        foodGroupPanel.add(lowerCheckbox3);
        foodGroupPanel.add(labelCheckbox4);
        foodGroupPanel.add(lowerCheckbox4);
        
        mainPanel.add(bottomPanel);
        add(mainPanel);

        // Default app details
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
   
  
    public JLabel getLabelName() {
        return labelName;
    }

    public JLabel getLabelTime() {
        return labelTime;
    }

    public JLabel getLabelServing() {
        return labelServing;
    }


    public String getItemType() {

        return (String) diningOptions.getSelectedItem();

    }

    public String getItemName() {

        return (jtfName.getText());
    }

    public String getItemServing() {

        return (jtfServing.getText());
    }

    public String getItemTime() {

        return (jtfTime.getText());
    }
    
    public String getFoodGroup() {

        return (jtfTime.getText());
    }
    
    public String getItemDate() {

        return (jtfDate.getText());
    }
    
    public String getItemUnit() {

        return (String) unitOptions.getSelectedItem();
    }
    
    public String getCalories() {

        return (jtfCalories.getText());
    }
    
    public String getFat() {

        return (jtfFat.getText());
    }
    
    public String getCarbs() {

        return (jtfCarbs.getText());
    }
    
    public String getProtein() {

        return (jtfProtein.getText());
    }
    
    public JTable getTable() {
        return table;
    }

    public DefaultTableModel getDefaultTable() {
        return defaultTable;
    }
    
    public String getSelectedCheckBox() {
    	for (JCheckBox checkBox : checkBoxes) {
            if (checkBox.isSelected()) {
                return (checkBox.getText());
            }
        }
		return null;
    }

    public void setJtfName(String jtfName) {
        this.jtfName.setText(jtfName);
    }


    public void setJtfServing(String jtfServing) {
        this.jtfServing.setText(jtfServing);
    }
    
    public void setCalories(String jtfCalories) {
    	this.jtfCalories.setText(jtfCalories);
    }
    
    public void setCarbs(String jtfCarbs) {
    	this.jtfCarbs.setText(jtfCarbs);
    }
    
    public void setFat(String jtfFat) {
    	this.jtfFat.setText(jtfFat);
    }
    
    public void setProtein(String jtfProtein) {
    	this.jtfProtein.setText(jtfProtein);
    }
    
    public void setCheckBoxOneToEaten() {
    	lowerCheckbox1.setSelected(true);
    }
    
    public void setCheckBoxTwoToEaten() {
    	lowerCheckbox2.setSelected(true);
    }
    
    public void setCheckBoxThreeToEaten() {
    	lowerCheckbox3.setSelected(true);
    }
    
    public void setCheckBoxFourToEaten() {
    	lowerCheckbox4.setSelected(true);
    }
    
    public void setCheckBoxOneToNotEaten() {
    	lowerCheckbox1.setSelected(false);
    }
    
    public void setCheckBoxTwoToNotEaten() {
    	lowerCheckbox2.setSelected(false);
    }
    
    public void setCheckBoxThreeToNotEaten() {
    	lowerCheckbox3.setSelected(false);
    }
    
    public void setCheckBoxFourToNotEaten() {
    	lowerCheckbox4.setSelected(false);
    }

    void diningOptionActionListener(ActionListener listenForTypeChosen) {
        diningOptions.addActionListener(listenForTypeChosen);
    }

    void addButtonActionListener(ActionListener listenForAddButton) {
        addButton.addActionListener(listenForAddButton);
    }

    void deleteButtonActionListener(ActionListener listenForDeleteButton) {
        deleteButton.addActionListener(listenForDeleteButton);
    }

    @Override
    public void update(Observable o, Object arg) {
    	JOptionPane.showMessageDialog(null, "A new item has been added to the Food List ");       
    }
}
