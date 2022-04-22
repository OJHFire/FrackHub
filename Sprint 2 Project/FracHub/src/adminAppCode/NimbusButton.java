package adminAppCode;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

// Adapted from https://stackoverflow.com/questions/9364175/change-look-and-feel-of-jbutton
public class NimbusButton {
    private static LookAndFeel nimbus;

    public JButton generateNimbusButton(String message) {
        try {
            LookAndFeel current = UIManager.getLookAndFeel(); //capture the current look and feel

            if (nimbus == null) { //only initialize Nimbus once
                for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
                nimbus = UIManager.getLookAndFeel();
            }
            else
                UIManager.setLookAndFeel(nimbus); //set look and feel to nimbus
            UIManager.put("nimbusBlueGrey", new Color(255,220,35));
            JButton button = new JButton(message); //create the button
            UIManager.setLookAndFeel(current); //return the look and feel to its original state
            return button;
        }
        catch (Exception e) {
            e.printStackTrace();
            return new JButton();
        }
    }
}