package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Contains GUI to view item once added to the database.
 */

public class itemInfoGUI implements ActionListener{
	
	JFrame frame;
	Customer cust;
	
	JPanel itemInfo;
	JPanel itemInfo2;
	
	JButton btnReturnMM;
	JButton btnSignOut;
	
	JLabel lblItemName = new JLabel("Item Name");
	JLabel lblItemType = new JLabel("Item Type");
	JLabel lblDescription = new JLabel("Description");
	JLabel lblValue = new JLabel("Value (£)");
	JLabel lblCostPerDay = new JLabel("Cost per Day (£)");
	JLabel lblItemName2;
	JLabel lblItemType2;
	JLabel lblDescription2;
	JLabel lblValue2;
	JLabel lblCostPerDay2;
	
	// Function to create GUI page.
	public void itemInfo(Item item, Customer new_cust, JFrame new_frame) {
		
		frame = new_frame;
		cust = new_cust;
		
		// Layout settings for the page.
		itemInfo = new JPanel();
		itemInfo2 = new JPanel();
		itemInfo.setLayout(new GridLayout(0,2,0,20));
		itemInfo2.setLayout(new GridLayout(0,2,20,10));
		itemInfo.setBorder(BorderFactory.createEmptyBorder(50,20,0,20));
		itemInfo2.setBorder(BorderFactory.createEmptyBorder(0,50,20,50));
		itemInfo.setBackground(Color.white);
		itemInfo2.setBackground(Color.white);
				
		// Control buttons to sign out or go back to main menu.
		btnReturnMM = new JButton("Main Menu");
		btnSignOut = new JButton("Sign Out");
		btnReturnMM.addActionListener(this);
		btnSignOut.addActionListener(this);
		
		// Labels for details of item.
		lblItemName2 = new JLabel(item.getName());
		lblItemType2 = new JLabel(item.getType());
		lblDescription2 = new JLabel(item.getDescription());
		lblValue2 = new JLabel(String.format("%.2f", item.getValue()));
		lblCostPerDay2 = new JLabel(String.format("%.2f", item.getDaily_rate()));

		itemInfo.add(lblItemName);
		itemInfo.add(lblItemName2);
		itemInfo.add(lblItemType);
		itemInfo.add(lblItemType2);
		itemInfo.add(lblDescription);
		itemInfo.add(lblDescription2);
		itemInfo.add(lblValue);
		itemInfo.add(lblValue2);
		itemInfo.add(lblCostPerDay);
		itemInfo.add(lblCostPerDay2);
		itemInfo2.add(btnReturnMM);
		itemInfo2.add(btnSignOut);
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(itemInfo, BorderLayout.NORTH);
		frame.getContentPane().add(itemInfo2, BorderLayout.SOUTH);
		frame.repaint();
		frame.revalidate();		
	}
	
	// Function for any events.
	public void actionPerformed(ActionEvent e) {

		// When the main menu button is pressed the optionMenuGUI is created.
		if(e.getSource() == btnReturnMM)
		{
			optionMenuGUI new_panel = new optionMenuGUI();
			new_panel.optionMenu(cust, frame);
		}
		// When the sign out button is pressed the mainMenuGUI is created.
		else if(e.getSource() == btnSignOut)
		{
			mainMenuGUI new_panel = new mainMenuGUI();
			new_panel.mainMenu(frame);
		}
	}

}
