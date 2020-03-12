package app;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BorderFactory;
import javax.swing.border.TitledBorder;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class FoodList extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JButton button;

    static final long serialVersionUID = 100L;

    public FoodList() {
        // Top Half
        // headers for the table
        String[] columns = new String[] { "Dining type", "Name/Retailer", "Time", "Serving/Meal", "Type/Group" };

        // actual data for the table in a 2d array
        Object[][] data = new Object[][] {};

        model = new DefaultTableModel(data, columns);
        table = new JTable(model);
      //for testing
        table.setName("table");
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        add(new JScrollPane(table), BorderLayout.NORTH);

        // Bottom Half
        JPanel panel = new JPanel();

        String[] options = new String[] { "Indining", "Outdining" };
        JComboBox<String> diningOptions = new JComboBox<>(options);

        JTextField jtfName = new JTextField(15);
        JLabel labelName = new JLabel("Name");

        JTextField jtfTime = new JTextField(25);
        // DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        // LocalDateTime now = LocalDateTime.now();
        // jtfTime.setText(dtf.format(now));

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE MMMM dd, HH:mm a");
        String stringDate = sdf.format(new Date());

        jtfTime.setText(stringDate);
        JLabel labelTime = new JLabel("Time");

        JTextField jtfServing = new JTextField(15);
        JLabel labelServing = new JLabel("Serving");

        JTextField jtfTypeGroup = new JTextField(15);
        JLabel labelTypeGroup = new JLabel("Type/Group");
        
        
        //for testing
        jtfName.setName("field1");
        jtfTime.setName("fieldtime");
        jtfServing.setName("field2");
        jtfTypeGroup.setName("field3");
        
        diningOptions.setName("DiningOptions");
        
        labelName.setName("label1");
        labelServing.setName("label2");
        labelTypeGroup.setName("label3");

        diningOptions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedDining = (String) diningOptions.getSelectedItem();
                if (selectedDining == "Outdining") {
                    labelName.setText("Retailer");
                    labelServing.setText("Meal");
                    labelTypeGroup.setText("Group");
                } else {
                    labelName.setText("Name");
                    labelServing.setText("Serving");
                    labelTypeGroup.setText("Type");
                }
            }
        });

        JButton addButton = new JButton("Add");
      //for testing
        addButton.setName("addButton");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dinningType = (String) diningOptions.getSelectedItem();
                String f1 = jtfName.getText();
                String f2 = jtfServing.getText();
                String f3 = jtfTypeGroup.getText();
                String timeNow = jtfTime.getText();

                if (dinningType == "Outdining") {
                    Outdining outdining = new Outdining(f1, timeNow, f2, f3);
                    Object[] row = { dinningType, outdining.retailer, outdining.time, outdining.meal, outdining.group };
                    model.addRow(row);
                } else {
                    Indining indining = new Indining(f1, timeNow, f2, f3);
                    Object[] row = { dinningType, indining.name, indining.time, indining.serving, indining.type };
                    model.addRow(row);
                }

                jtfName.setText("");
                jtfServing.setText("");
                jtfTypeGroup.setText("");
                jtfName.setText("");
            }
        });

        button = new JButton("Delete selected row");
      //for testing
        button.setName("delete");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                // check for selected row first
                if (table.getSelectedRow() != -1) {
                    // remove selected row from the model
                    model.removeRow(table.getSelectedRow());
                    // JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
                }
            }
        });

        panel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Personal Dietary Manager - PROTOTYPE", TitledBorder.CENTER, TitledBorder.TOP));

        panel.add(diningOptions);
        panel.add(labelName);
        panel.add(jtfName);
        panel.add(labelTime);
        panel.add(jtfTime);
        panel.add(labelServing);
        panel.add(jtfServing);
        panel.add(labelTypeGroup);
        panel.add(jtfTypeGroup);

        panel.add(addButton);

        panel.add(button);

        add(panel, BorderLayout.CENTER);

        // Footer for categorization
        JPanel panelBottom = new JPanel();

        JCheckBox checkbox1 = new JCheckBox("Vegetables and Fruit");
        JCheckBox checkbox2 = new JCheckBox("Grain Products");
        JCheckBox checkbox3 = new JCheckBox("Milk and Alternatives");
        JCheckBox checkbox4 = new JCheckBox("Meat and Alternatives");
        
      //for testing
        checkbox1.setName("box1");
        checkbox2.setName("box2");
        checkbox3.setName("box3");
        checkbox4.setName("box4");
        
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
}
