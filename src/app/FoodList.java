package app;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FoodList extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JButton button;

    static final long serialVersionUID = 100L;

    public FoodList() {
        // headers for the table
        String[] columns = new String[] { "Dining type", "Name/Retailer", "Time", "Serving/Meal", "Type/Group" };

        // actual data for the table in a 2d array
        Object[][] data = new Object[][] {};

        model = new DefaultTableModel(data, columns);
        table = new JTable(model);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        button = new JButton("Delete selected row");
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
        add(new JScrollPane(table), BorderLayout.NORTH);

        JPanel panel = new JPanel();

        String[] options = new String[] { "Indining", "Outdining" };

        JComboBox<String> diningOptions = new JComboBox<>(options);

        JTextField jtf = new JTextField(15);
        JLabel label = new JLabel("Name");

        JTextField jtfTime = new JTextField(25);
        // DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        // LocalDateTime now = LocalDateTime.now();
        // jtfTime.setText(dtf.format(now));

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE MMMM dd, HH:mm a");
        String stringDate = sdf.format(new Date());

        jtfTime.setText(stringDate);
        JLabel labelTime = new JLabel("Time");

        JTextField jtf2 = new JTextField(15);
        JLabel label2 = new JLabel("Serving");

        JTextField jtf3 = new JTextField(15);
        JLabel label3 = new JLabel("Type/Group");

        diningOptions.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedDining = (String) diningOptions.getSelectedItem();
                if (selectedDining == "Outdining") {
                    label.setText("Retailer");
                    label2.setText("Meal");
                    label3.setText("Group");
                } else {
                    label.setText("Name");
                    label2.setText("Serving");
                    label3.setText("Type");
                }
            }
        });

        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String dinningType = (String) diningOptions.getSelectedItem();
                String f1 = jtf.getText();
                String f2 = jtf2.getText();
                String f3 = jtf3.getText();
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

                jtf.setText("");
                jtf2.setText("");
                jtf3.setText("");
                jtf.setText("");
            }
        });

        panel.add(diningOptions);
        panel.add(label);
        panel.add(jtf);
        panel.add(labelTime);
        panel.add(jtfTime);
        panel.add(label2);
        panel.add(jtf2);
        panel.add(label3);
        panel.add(jtf3);

        panel.add(addButton);

        panel.add(button);

        add(panel, BorderLayout.CENTER);

        JPanel panelBottom = new JPanel();

        JCheckBox checkbox1 = new JCheckBox("vegetables and fruit");
        JCheckBox checkbox2 = new JCheckBox("grain products");
        JCheckBox checkbox3 = new JCheckBox("milk and alternatives");
        JCheckBox checkbox4 = new JCheckBox("meat and alternatives");

        panelBottom.add(checkbox1);
        panelBottom.add(checkbox2);
        panelBottom.add(checkbox3);
        panelBottom.add(checkbox4);

        add(panelBottom, BorderLayout.SOUTH);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
