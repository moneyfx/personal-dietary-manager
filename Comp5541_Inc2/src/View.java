import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Observer;

public class View extends JFrame implements Observer {

    private Model m = null;

    private JTable table;
    private DefaultTableModel model;

    private String[] options = new String[]{"Indining", "Outdining"};
    private JComboBox<String> diningOptions = new JComboBox<>(options);

    private String [] units = new String[] {"portion","g","ml","tbsp","tsp","cup"};
    private JComboBox<String> unitOptions = new JComboBox<>(units);
    private JLabel labelUnit = new JLabel("Unit");


    private JTextField jtfName = new JTextField(15);
    private JLabel labelName = new JLabel("Name");

    private JTextField jtfTime = new JTextField(25);
    private JLabel labelTime = new JLabel("Time");

    private JTextField jtfServing = new JTextField(15);
    private JLabel labelServing = new JLabel("Serving");

    private JTextField jtfTypeGroup = new JTextField(15);
    private JLabel labelTypeGroup = new JLabel("Type/Group");

    private JButton addButton = new JButton("Add");
    private JButton deleteButton = new JButton("Delete selected row");

    public View() {
        // Top Half
        // headers for the table
        String[] columns = new String[]{"Dining type", "Name/Retailer", "Time", "Serving/Meal", "Type/Group"};

        // actual data for the table in a 2d array
        Object[][] data = new Object[][]{};

        model = new DefaultTableModel(data, columns);
        table = new JTable(model);
        //for testing
        table.setName("table");
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        add(new JScrollPane(table), BorderLayout.NORTH);

        // Bottom Half
        JPanel panel = new JPanel();

        // settingUp time
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE MMMM dd, HH:mm a");
        String stringDate = sdf.format(new Date());
        jtfTime.setText(stringDate);


        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Personal Dietary Manager - PROTOTYPE", TitledBorder.CENTER, TitledBorder.TOP));

        panel.add(diningOptions);
        panel.add(labelName);
        panel.add(jtfName);
        panel.add(labelTime);
        panel.add(jtfTime);
        panel.add(labelServing);
        panel.add(jtfServing);
        panel.add(labelUnit);
        panel.add(unitOptions);
        panel.add(labelTypeGroup);
        panel.add(jtfTypeGroup);

        panel.add(addButton);

        panel.add(deleteButton);

        add(panel, BorderLayout.CENTER);

        // Footer for categorization
        JPanel panelBottom = new JPanel();
        JCheckBox checkbox1 = new JCheckBox("Vegetables and Fruit");
        JCheckBox checkbox2 = new JCheckBox("Grain Products");
        JCheckBox checkbox3 = new JCheckBox("Milk and Alternatives");
        JCheckBox checkbox4 = new JCheckBox("Meat and Alternatives");
        panelBottom.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Mark Food Groups Eaten", TitledBorder.CENTER, TitledBorder.TOP));
        panelBottom.add(checkbox1);
        panelBottom.add(checkbox2);
        panelBottom.add(checkbox3);
        panelBottom.add(checkbox4);
        add(panelBottom, BorderLayout.SOUTH);

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


