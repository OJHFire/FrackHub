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
	GUI gui;
	
	JPanel itemInfo1;
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
	JTextArea lblDescription2;
	JLabel lblValue2;
	JLabel lblCostPerDay2;
	
	NimbusButton nimbusButton = new NimbusButton();
	Font font = new Font("Calibri", Font.BOLD, 15);

	
	// Function to create GUI page.
	public itemInfoGUI(Item item, Customer new_cust, JFrame new_frame) {
		
		frame = new_frame;
		cust = new_cust;
		
		// Remove all previous content on frame.
		frame.getContentPane().removeAll();
		
		// Layout settings for the page.
		
		// NORTH PANEL - Create header with given title.
		gui = new GUI();
		gui.pageHeader(frame, "New Item Details");
		
		
		// CENTRE PANEL
		itemInfo1 = new JPanel();
		itemInfo1.setLayout(new GridBagLayout());
		GridBagConstraints panel_constraints = new GridBagConstraints();
		itemInfo1.setBorder(BorderFactory.createEmptyBorder(20,50,110,30));
		itemInfo1.setBackground(Color.white);
		
		lblDescription2 = new JTextArea(2,30);
		lblDescription2.setMaximumSize(new Dimension(10, 10));
		lblDescription2.setWrapStyleWord(true);
		lblDescription2.setLineWrap(true);
		lblDescription2.setOpaque(false);
		lblDescription2.setEditable(false);
		lblDescription2.setFocusable(false);
		
		// Labels for details of item.
		lblItemName2 = new JLabel(item.getName());
		lblItemType2 = new JLabel(item.getType());
		lblDescription2.setText(item.getDescription());
		lblValue2 = new JLabel(String.format("%.2f", item.getValue()));
		lblCostPerDay2 = new JLabel(String.format("%.2f", item.getDaily_rate()));
		
		lblItemName.setFont(font);
		lblItemType.setFont(font);
		lblDescription.setFont(font);
		lblValue.setFont(font);
		lblCostPerDay.setFont(font);
		lblItemName2.setFont(font);
		lblItemType2.setFont(font);
		lblDescription2.setFont(font);
		lblValue2.setFont(font);
		lblCostPerDay2.setFont(font);
		
		JLabel space1 = new JLabel("      ");
		JLabel space2 = new JLabel("      ");
		panel_constraints.fill = GridBagConstraints.HORIZONTAL;
		panel_constraints.gridy = 0;
		panel_constraints.gridx = 0;
		panel_constraints.weightx = 1.0; 
		panel_constraints.ipady = 60;
		itemInfo1.add(space1, panel_constraints);
		panel_constraints.gridy = 1;
		panel_constraints.gridx = 0;
		panel_constraints.ipady = 15;
		itemInfo1.add(lblItemName, panel_constraints);
		panel_constraints.gridx = 1;
		panel_constraints.ipadx = 60;
		itemInfo1.add(space2, panel_constraints);
		panel_constraints.gridx = 2;
		panel_constraints.ipadx = 0;
		itemInfo1.add(lblItemName2, panel_constraints);
		panel_constraints.gridy = 2;
		panel_constraints.gridx = 0;
		itemInfo1.add(lblItemType, panel_constraints);
		panel_constraints.gridx = 2;
		itemInfo1.add(lblItemType2, panel_constraints);
		panel_constraints.gridy = 3;
		panel_constraints.gridx = 0;
		itemInfo1.add(lblDescription, panel_constraints);
		panel_constraints.gridx = 2;
		panel_constraints.ipady = 0;
		itemInfo1.add(lblDescription2, panel_constraints);
		panel_constraints.gridy = 4;
		panel_constraints.gridx = 0;
		panel_constraints.ipady = 15;
		itemInfo1.add(lblValue, panel_constraints);
		panel_constraints.gridx = 2;
		itemInfo1.add(lblValue2, panel_constraints);
		panel_constraints.gridy = 5;
		panel_constraints.gridx = 0;
		itemInfo1.add(lblCostPerDay, panel_constraints);
		panel_constraints.gridx = 2;
		itemInfo1.add(lblCostPerDay2, panel_constraints);
				
		
		// SOUTH PANEL
		itemInfo2 = new JPanel();
		itemInfo2.setLayout(new GridLayout(0,2,20,10));
		itemInfo2.setBorder(BorderFactory.createEmptyBorder(0,50,20,50));
		itemInfo2.setBackground(Color.white);
				
		// Control buttons to sign out or go back to main menu.
		btnReturnMM = nimbusButton.generateNimbusButton("Main Menu");
		btnReturnMM.putClientProperty("JComponent.sizeVariant", "large");
		btnSignOut = nimbusButton.generateNimbusButton("Sign Out");
		btnSignOut.putClientProperty("JComponent.sizeVariant", "large");
		btnReturnMM.addActionListener(this);
		btnSignOut.addActionListener(this);
		
		itemInfo2.add(btnSignOut);
		itemInfo2.add(btnReturnMM);
		

		// Add panels to the frame.
		frame.getContentPane().add(itemInfo1, BorderLayout.CENTER);
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
