package code;
   
import java.util.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class viewMyItemsGUI implements ActionListener {

	JFrame frame;
	Customer cust;
	GUI gui;

	JPanel topPanel;
	JPanel centerPanel;
	JPanel bottomPanel;

	JButton btnNext;
	JButton btnBack;
	JButton btnEdit;

	JLabel lblWarning;
	JLabel lblTitle = new JLabel("My Items");
	JLabel lblNum = new JLabel("Number: ");
	JLabel lblName = new JLabel("Name: ");
	JLabel lblType = new JLabel("Type: ");
	JLabel lblDescription = new JLabel("Description: ");
	JLabel lblValue = new JLabel("Value: ");
	JLabel lblCostPerDay = new JLabel("Cost Per Day: ");
	
	JLabel lblItemNum = new JLabel();
	JLabel lblItemName = new JLabel();
	JLabel lblItemType = new JLabel();
	JTextArea lblItemDescription;
	JLabel lblItemValue = new JLabel();
	JLabel lblItemCostPerDay = new JLabel();

	int count = 0;
	int totalItems = 0;
	int userId;

	ArrayList<Item> item_list;
	Item[] item_list_array;
	String itemName;
	String itemType;
	String itemDescription;
	double itemValue;
	double itemCostPerDay;
	
	NimbusButton nimbusButton = new NimbusButton();
	Font font = new Font("Calibri", Font.BOLD, 15);
	
	GridBagConstraints panel_constraints;
	boolean no_conn = false;
	
	public void viewItem(Customer new_cust, JFrame new_frame) {

		frame = new_frame;
		cust = new_cust;
		
		// Get Total Items
		if (count == 0) {
			loadItemList();
		}
		
		// Remove all previous content on frame.
		frame.getContentPane().removeAll();
				
		// TOP PANEL - Create header with given title.
		gui = new GUI();
		gui.pageHeader(frame, "My Items");


		// CENTER PANEL
		centerPanel = new JPanel();
		centerPanel.setLayout(new GridBagLayout());
		panel_constraints = new GridBagConstraints();
		centerPanel.setBorder(BorderFactory.createEmptyBorder(20,50,110,30));
		centerPanel.setBackground(Color.white);

		// Check there is a connection with the database.
		if (!item_list_array[0].getName().equals("")) {
			
			 try {
				 centerPanel.remove(lblWarning);
				 frame.repaint();
				 frame.revalidate();
			 }
			 catch (Exception e) {}
			
			
			// GET NEXT ITEM
			if (count < totalItems) {
				
				lblItemDescription = new JTextArea(2,30);
				lblItemDescription.setMaximumSize(new Dimension(10, 10));
				lblItemDescription.setWrapStyleWord(true);
				lblItemDescription.setLineWrap(true);
				lblItemDescription.setOpaque(false);
				lblItemDescription.setEditable(false);
				lblItemDescription.setFocusable(false);
				
				itemName = item_list_array[count].getName();
				itemType = item_list_array[count].getType();
				itemDescription = item_list_array[count].getDescription();
				itemValue = item_list_array[count].getValue();
				itemCostPerDay = item_list_array[count].getDaily_rate();
				
				lblTitle.setFont(font);
				lblNum.setFont(font);
				lblName.setFont(font);
				lblType.setFont(font);
				lblDescription.setFont(font);
				lblValue.setFont(font);
				lblCostPerDay.setFont(font);
				
				lblItemNum.setFont(font);
				lblItemName.setFont(font);
				lblItemType.setFont(font);
				lblItemDescription.setFont(font);
				lblItemValue.setFont(font);
				lblItemCostPerDay.setFont(font);
				
				
				lblItemNum.setText((count + 1)+ " of " + totalItems);
				centerPanel.add(lblNum);
				centerPanel.add(lblItemNum);
	
				lblItemName.setText(itemName);
				centerPanel.add(lblName);
				centerPanel.add(lblItemName);
	
				lblItemType.setText(itemType);
				centerPanel.add(lblType);
				centerPanel.add(lblItemType);
	
				lblItemDescription.setText(itemDescription);
				centerPanel.add(lblDescription);
				centerPanel.add(lblItemDescription);
	
				lblItemValue.setText("£"+String.format("%.2f",itemValue));
				centerPanel.add(lblValue);
				centerPanel.add(lblItemValue);
	
				lblItemCostPerDay.setText("£"+String.format("%.2f",itemCostPerDay));
				centerPanel.add(lblCostPerDay);
				centerPanel.add(lblItemCostPerDay);
				
				JLabel space1 = new JLabel("      ");
				JLabel space2 = new JLabel("      ");
				panel_constraints.fill = GridBagConstraints.HORIZONTAL;
				panel_constraints.gridy = 0;
				panel_constraints.gridx = 0;
				panel_constraints.weightx = 1.0; 
				panel_constraints.ipady = 60;
				panel_constraints.anchor = GridBagConstraints.NORTH;
				centerPanel.add(space1, panel_constraints);
				panel_constraints.gridy = 1;
				panel_constraints.gridx = 0;
				panel_constraints.ipady = 15;
				centerPanel.add(lblNum, panel_constraints);
				panel_constraints.gridx = 1;
				panel_constraints.ipadx = 60;
				centerPanel.add(space2, panel_constraints);
				panel_constraints.gridx = 2;
				panel_constraints.ipadx = 0;
				centerPanel.add(lblItemNum, panel_constraints);
				panel_constraints.gridy = 2;
				panel_constraints.gridx = 0;
				centerPanel.add(lblName, panel_constraints);
				panel_constraints.gridx = 2;
				centerPanel.add(lblItemName, panel_constraints);
				panel_constraints.gridy = 3;
				panel_constraints.gridx = 0;
				centerPanel.add(lblType, panel_constraints);
				panel_constraints.gridx = 2;
				centerPanel.add(lblItemType, panel_constraints);
				panel_constraints.gridy = 4;
				panel_constraints.gridx = 0;
				centerPanel.add(lblDescription, panel_constraints);
				panel_constraints.gridx = 2;
				panel_constraints.ipady = 0;
				centerPanel.add(lblItemDescription, panel_constraints);
				panel_constraints.gridy = 5;
				panel_constraints.gridx = 0;
				panel_constraints.ipady = 15;
				centerPanel.add(lblValue, panel_constraints);
				panel_constraints.gridx = 2;
				centerPanel.add(lblItemValue, panel_constraints);
				panel_constraints.gridy = 6;
				panel_constraints.gridx = 0;
				panel_constraints.anchor = GridBagConstraints.SOUTH;
				centerPanel.add(lblCostPerDay, panel_constraints);
				panel_constraints.gridx = 2;
				centerPanel.add(lblItemCostPerDay, panel_constraints);
				
				count++;
			
			}else {
				//Display NO Items Message
				lblNum.setText("You have not uploaded any items yet.");
				lblNum.setHorizontalAlignment(JLabel.CENTER);
				centerPanel.add(lblNum);
			}
		}
		else {
			
			//Display warning message if there is no connection.
			lblWarning = new JLabel("Connection Issue - Please try again later.");
			lblWarning.setHorizontalAlignment(JLabel.CENTER);
			centerPanel.add(lblWarning);
			no_conn = true;
		}
		
		//BOTTOM PANEL
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(0, 2, 20,10));
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(0,50,20,50));
		bottomPanel.setBackground(Color.white);
		
		btnBack = nimbusButton.generateNimbusButton("Main Menu");
		btnBack.putClientProperty("JComponent.sizeVariant", "large");
		btnBack.addActionListener(this);
		btnNext = nimbusButton.generateNimbusButton("Next Item");
		btnNext.putClientProperty("JComponent.sizeVariant", "large");
		btnNext.addActionListener(this);
		btnEdit = nimbusButton.generateNimbusButton("Edit Item");
		btnEdit.putClientProperty("JComponent.sizeVariant", "large");
		btnEdit.addActionListener(this);
		
		if(no_conn) {
			bottomPanel.setLayout(new GridLayout(0, 1, 20,10));
			bottomPanel.setBorder(BorderFactory.createEmptyBorder(0,140,20,140));
			bottomPanel.add(btnBack);
		}
		else if(count < totalItems) {
			bottomPanel.setLayout(new GridLayout(0, 3, 0,10));
			bottomPanel.setBorder(BorderFactory.createEmptyBorder(0,50,20,50));
			bottomPanel.add(btnNext);
			bottomPanel.add(btnEdit);
			bottomPanel.add(btnBack);			
		}
		else {
			bottomPanel.setLayout(new GridLayout(0, 2, 20,10));
			bottomPanel.setBorder(BorderFactory.createEmptyBorder(0,50,20,50));
			bottomPanel.add(btnEdit);
			bottomPanel.add(btnBack);
		}

		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
		frame.repaint();
		frame.revalidate();
	}
	
	public void loadItemList() {
		Item item = new Item();
		item_list = item.viewAllMyItems(cust);
		totalItems = item_list.size();
		item_list_array = item_list.toArray(new Item[totalItems]);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnNext) {
			viewItem(cust, frame);		
		}
		else if(e.getSource() == btnBack)
		{
			optionMenuGUI new_panel = new optionMenuGUI();
			new_panel.optionMenu(cust, frame);
		}
		else if(e.getSource() == btnEdit)
		{
			new editMyItemGUI(item_list_array[count - 1], cust, frame);
		}
	}

}