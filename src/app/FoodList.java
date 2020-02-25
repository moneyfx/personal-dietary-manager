package app;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class FoodList extends JFrame {
    private JTable table;
    private DefaultTableModel model;
    private JButton button;

    static final long serialVersionUID = 100;

    public FoodList() {
        // headers for the table
        String[] columns = new String[] { "Name/Retailer", "Time", "Serving/Meal", "Type/Group" };

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

        add(button, BorderLayout.SOUTH);

        JPanel panel = new JPanel();

        JTextField jtf = new JTextField(15);
        JLabel label = new JLabel("Name/Retailer");

        JTextField jtfTime = new JTextField(15);
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        jtfTime.setText(dtf.format(now));
        JLabel labelTime = new JLabel("Time");

        JTextField jtf2 = new JTextField(15);
        JLabel label2 = new JLabel("Serving/Meal");

        JTextField jtf3 = new JTextField(15);
        JLabel label3 = new JLabel("Type/Group");


        JButton addButton = new JButton("Add");
        addButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
               //this will give you myText's contents. Do what you will with them.
               String f1 = jtf.getText();
               String f2 = jtf2.getText();
               String f3 = jtf3.getText();
               //DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
               //LocalDateTime now = LocalDateTime.now();
               String timeNow = jtfTime.getText();
               Object[] row = { f1, timeNow, f2, f3 };
               model.addRow(row);
               jtf.setText("");
               jtf2.setText("");
               jtf3.setText("");
               jtf.setText("");
            }
        });

        panel.add(label);
        panel.add(jtf);
        panel.add(labelTime);
        panel.add(jtfTime);
        panel.add(label2);
        panel.add(jtf2);
        panel.add(label3);
        panel.add(jtf3);

        panel.add(addButton);

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
