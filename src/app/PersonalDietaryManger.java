package app;

import javax.swing.*;        

public class PersonalDietaryManger  {
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FoodList();
            }
        });
    }   
}
