import javax.swing.*;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class View extends JFrame implements Observer {
	
	//This is the main window called mainDietTypePanel:
    private JPanel mainPanel = new JPanel(new GridLayout(2,0));
    
    //The main panel is divided into a top and a bottom panel:
    private JPanel topPanel = new JPanel(new BorderLayout());
    private JPanel bottomPanel = new JPanel(new GridLayout(4,0));
    
    //Top panel
    
    //The top panel includes the table and the filter panel.
    private JTable table;
    private DefaultTableModel defaultTable;
    private JPanel mainFilterPanel = new JPanel(new GridLayout(0,3));
    private JPanel filterPanel = new JPanel();
    
    //The filter panel includes a text field for search and a label
	private JTextField jtfFilter = new JTextField(15);
	private JLabel labelFilter = new JLabel("Specify a word to filter:");
    
	//Bottom panel
	
	//The bottom panel includes 4 panels: Main Diet Type panel, Hierarchy View panel, Buttons panel and Food Groups panel.
	 private JPanel mainDietTypePanel = new JPanel(new GridLayout(2,0));
	 private JPanel hierarchyViewPanel = new JPanel(new GridLayout(0,2)); 
	 private JPanel buttonPanel = new JPanel();
	 private JPanel foodGroupPanel = new JPanel();
	
	//The Main Diet Type panel includes 2 sub-panels:
	 private JPanel mainDietTitle = new JPanel();
	 private JPanel otherFacts = new JPanel();
	 
	 //The Main Diet title panel only includes the Indining / Outdining options:
	 
	private String[] options = new String[]{"Indining", "Outdining"};
    private JComboBox<String> diningOptions = new JComboBox<>(options);
    
    //The Other Facts panel includes the following:
    private JTextField jtfName = new JTextField(15);
    private JLabel labelName = new JLabel("Name");
    
    private JTextField jtfTypeGroup = new JTextField(15);
    private JLabel labelTypeGroup = new JLabel("Type/Group");
    
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
    
    private String [] units = new String[] {"item / items","g","ml","tbsp","tsp","cup / cups"};
    private JComboBox<String> unitOptions = new JComboBox<>(units);
    private JLabel labelUnit = new JLabel("Unit");

    
    //The Nutrition facts panel includes the following:
 
    private JTextField jtfCalories = new JTextField(10);
    private JLabel labelCalories = new JLabel("Calories");
    
    private JTextField jtfFat = new JTextField(10);
    private JLabel labelFat = new JLabel("Fat");
    
    private JTextField jtfCarbs = new JTextField(10);
    private JLabel labelCarbs = new JLabel("Carbs");
    
    private JTextField jtfProtein = new JTextField(10);
    private JLabel labelProtein = new JLabel("Protein");
    
    
    //The Buttons panel includes the following:
 
    private JButton addButton = new JButton("Add");
    private JButton deleteButton = new JButton("Delete selected row");
    
    //The Food Groups panel includes the following:
    JCheckBox checkbox1 = new JCheckBox("Vegetables and Fruit");
    JCheckBox checkbox2 = new JCheckBox("Grain Products");
    JCheckBox checkbox3 = new JCheckBox("Milk and Alternatives");
    JCheckBox checkbox4 = new JCheckBox("Meat and Alternatives");

    public View() {
   
        // Configuring the top panel
    	
    	//Adding column headers
        String[] columns = new String[]{"Dining type", "Name/Retailer", "Time", "Date", "Serving/Meal", 
        		"Unit", "Calories", "Fat (gr)", "Carbohydrate (gr)", "Protein (gr)", "Type/Group"}; 

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
        
        mainDietTypePanel.add(mainDietTitle);
     
        mainDietTitle.add(diningOptions);
        
        mainDietTypePanel.add(otherFacts);
        otherFacts.add(labelName);
        otherFacts.add(jtfName);
        otherFacts.add(labelTypeGroup);
        otherFacts.add(jtfTypeGroup);
        
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
        
        //Configuring the buttonPanel
        bottomPanel.add(buttonPanel);
        buttonPanel.setBorder(BorderFactory.createEtchedBorder());
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        // Configuring the foodGroupPanel.
        
        foodGroupPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Mark Food Groups Eaten", TitledBorder.CENTER, TitledBorder.TOP));
       
        foodGroupPanel.add(checkbox1);
        foodGroupPanel.add(checkbox2);
        foodGroupPanel.add(checkbox3);
        foodGroupPanel.add(checkbox4);
        bottomPanel.add(foodGroupPanel);
        
        mainPanel.add(bottomPanel);
        add(mainPanel);

        // Default app details
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
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

    public JLabel getLabelTypeGroup() {
        return labelTypeGroup;
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

    public String getTypeGroup() {
        return (jtfTypeGroup.getText());

    }

    public String getItemTime() {

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


    public void setJtfName(String jtfName) {
        this.jtfName.setText(jtfName);
    }


    public void setJtfServing(String jtfServing) {
        this.jtfServing.setText(jtfServing);
    }

    public void setJtfTypeGroup(String jtfTypeGroup) {
        this.jtfTypeGroup.setText(jtfTypeGroup);
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
    	JOptionPane.showMessageDialog(null, "A new item has been added to the FoodList ");       
    }
}
