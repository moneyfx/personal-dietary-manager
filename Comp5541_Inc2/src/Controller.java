import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Controller {

    private View theView;
    private Model theModel;

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
                theView.getLabelTypeGroup().setText("Group");
            } else {
                theView.getLabelName().setText("Name");
                theView.getLabelServing().setText("Serving");
                theView.getLabelTime().setText("Type");
            }
        }
    }


    class AddButtonListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String dinningType = (String) theView.getItemType();
            String f1 = theView.getItemName();
            String f2 = theView.getItemServing();
            String f3 = theView.getTypeGroup();
            String timeNow = theView.getItemTime();

            if (dinningType == "Outdining") {
                Outdining outdining = new Outdining(f1, timeNow, f2, f3);
                theModel.setOutdining(outdining);
                Object[] row = { dinningType, outdining.getRetailer(), outdining.getTime(), outdining.getMeal(), outdining.getGroup()};
                theView.getModel().addRow(row);
            } else {
                Indining indining = new Indining(f1, timeNow, f2, f3);
                theModel.setIndining(indining);
                Object[] row = { dinningType, indining.getName(), indining.getTime(), indining.getServing(), indining.getType() };
                theView.getModel().addRow(row);
            }

            theView.setJtfName("");
            theView.setJtfServing("");
            theView.setJtfTypeGroup("");
        }
    }

    class DeleteButtonListener implements ActionListener {

            @Override
            public void actionPerformed (ActionEvent e){
                // check for selected row first
                if (theView.getTable().getSelectedRow() != -1) {
                    // remove selected row from the model
                    theView.getModel().removeRow(theView.getTable().getSelectedRow());
                    // JOptionPane.showMessageDialog(null, "Selected row deleted successfully");
                }
            }
        }
    }

