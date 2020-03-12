import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
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

    private Model m = null;

    private JTable table;
    private DefaultTableModel model;
    
    //OM: One panel created in the main method includes 4 panels: 
    //mainPanel, nutrition facts, buttons panel and food group panel.
    
    private JPanel mainPanel = new JPanel();

    private String[] options = new String[]{"Indining", "Outdining"};
    private JComboBox<String> diningOptions = new JComboBox<>(options);

    private String [] units = new String[] {"portion","g","ml","tbsp","tsp","cup"};
    private JComboBox<String> unitOptions = new JComboBox<>(units);
    private JLabel labelUnit = new JLabel("Unit");


    private JTextField jtfName = new JTextField(15);
    private JLabel labelName = new JLabel("Name");

    private JTextField jtfTime = new JTextField(10);
    private JLabel labelTime = new JLabel("Time");
    
    private JTextField jtfDate = new JTextField(15);
    private JLabel labelDate = new JLabel("Date");

    private JTextField jtfServing = new JTextField(15);
    private JLabel labelServing = new JLabel("Serving");

    private JTextField jtfTypeGroup = new JTextField(15);
    private JLabel labelTypeGroup = new JLabel("Type/Group");
    
    //OM: added Nutrition facts panel
    private JPanel jtfNutritionFacts = new JPanel();
    
    private JTextField jtfCalories = new JTextField(10);
    private JLabel labelCalories = new JLabel("Calories");
    
    private JTextField jtfFat = new JTextField(10);
    private JLabel labelFat = new JLabel("Fat");
    
    private JTextField jtfCarbs = new JTextField(10);
    private JLabel labelCarbs = new JLabel("Carbs");
    
    private JTextField jtfProtein = new JTextField(10);
    private JLabel labelProtein = new JLabel("Protein");
    /* OM: maybe - for future conversion of carbs, etc. into integers:
    String fieldText = jtfCarbs.getText();
    int fieldNumber = Integer.parseInt(fieldText);
     */
    
    //OM: added Button panel
    private JPanel jtfButtonPanel = new JPanel();

    private JButton addButton = new JButton("Add");
    private JButton deleteButton = new JButton("Delete selected row");

    public View() {
        // Top Half
        // headers for the table
        String[] columns = new String[]{"Dining type", "Name/Retailer", "Time", "Date", "Serving/Meal", 
        		"Unit", "Calories", "Fat (gr)", "Carbohydrate (gr)", "Protein (gr)", "Type/Group"}; 

        // actual data for the table in a 2d array
        Object[][] data = new Object[][]{};

        model = new DefaultTableModel(data, columns);
        //OM - changed to this:
        //model = new DefaultTableModel();
        table = new JTable(model);
        //for testing
        table.setName("table");
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(new JScrollPane(table), BorderLayout.NORTH);

        
        // Bottom Half
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4,0));

        // settingUp time and date
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE MMMM dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat ("HH:mm a");
        String stringDate = sdf.format(new Date());
        String stringTime = sdf2.format(cal.getTime());
        jtfTime.setText(stringTime);
        jtfDate.setText(stringDate);


        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Personal Dietary Manager - PROTOTYPE", TitledBorder.CENTER, TitledBorder.TOP));
        
        panel.add(mainPanel);
        mainPanel.add(diningOptions);
        mainPanel.add(labelName);
        mainPanel.add(jtfName);
        mainPanel.add(labelTime);
        mainPanel.add(jtfTime);
        mainPanel.add(labelDate);
        mainPanel.add(jtfDate);
        mainPanel.add(labelServing);
        mainPanel.add(jtfServing);
        mainPanel.add(labelUnit);
        mainPanel.add(unitOptions);
        mainPanel.add(labelTypeGroup);
        mainPanel.add(jtfTypeGroup);
        mainPanel.add(jtfTypeGroup);
        
  
        panel.add(jtfNutritionFacts);
        jtfNutritionFacts.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Nutrition Facts", TitledBorder.CENTER, TitledBorder.TOP));
        jtfNutritionFacts.add(labelCalories);
        jtfNutritionFacts.add(jtfCalories);
        jtfNutritionFacts.add(labelFat);
        jtfNutritionFacts.add(jtfFat);
        jtfNutritionFacts.add(labelCarbs);
        jtfNutritionFacts.add(jtfCarbs);
        jtfNutritionFacts.add(labelProtein);
        jtfNutritionFacts.add(jtfProtein);
        
        panel.add(jtfButtonPanel);
        jtfButtonPanel.setBorder(BorderFactory.createEtchedBorder());
        jtfButtonPanel.add(addButton);
        jtfButtonPanel.add(deleteButton);

        // Footer for categorization
        JPanel panelBottom = new JPanel();
        panelBottom.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Mark Food Groups Eaten", TitledBorder.CENTER, TitledBorder.TOP));
        
        JCheckBox checkbox1 = new JCheckBox("Vegetables and Fruit");
        JCheckBox checkbox2 = new JCheckBox("Grain Products");
        JCheckBox checkbox3 = new JCheckBox("Milk and Alternatives");
        JCheckBox checkbox4 = new JCheckBox("Meat and Alternatives");
        
        panelBottom.add(checkbox1);
        panelBottom.add(checkbox2);
        panelBottom.add(checkbox3);
        panelBottom.add(checkbox4);
        panel.add(panelBottom);
        
        add(panel);

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

    public DefaultTableModel getModel() {
        return model;
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

        if(addButton.isEnabled()){
            System.out.println(getItemName()+" is added to the FoodList");

        }







    }
}


