package app;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class FoodList extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JButton button;

    static final long serialVersionUID = 100;

    public FoodList() {
        // headers for the table
        String[] columns = new String[] { "Name/Retailer", "Time", "Serving/Meal", "Type/Group" };

        // actual data for the table in a 2d array
        Object[][] data = new Object[][] { { "Banana", "11:00", "1", "bought" }, { "Apple", "12:50", "1", "bought" },
                { "Mangp", "13:56", "1", "bought" }, { "McDonalds", "16:00", "Lunch", "meat and alternatives" } };

        model = new DefaultTableModel(data, columns);
        table = new JTable(model);
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        button = new JButton("Delete row");
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

        add(button, BorderLayout.SOUTH);

        JPanel panel = new JPanel();

        JTextField jtf = new JTextField(20);
        JLabel label = new JLabel("Name/Retailer");

        JTextField jtf2 = new JTextField(20);
        JLabel label2 = new JLabel("Serving/Meal");

        panel.add(label);
        panel.add(jtf);
        panel.add(label2);
        panel.add(jtf2);

        add(panel, BorderLayout.CENTER);
        

        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setSize(600, 600);
        setLocationRelativeTo(null);
        setVisible(true);

        // create table with data
        // JTable table = new JTable(data, columns);

        // add the table to the frame
        /*
         * this.add(new JScrollPane(table));
         * 
         * this.setTitle("Consumed food list");
         * this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); this.pack();
         * this.setVisible(true);
         */
    }
}
