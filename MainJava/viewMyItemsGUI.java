package code;
   
import java.util.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class viewMyItemsGUI implements ActionListener {

	JFrame frame;
	Customer cust;

	JPanel topPanel;
	JPanel centerPanel;
	JPanel bottomPanel;

	JButton btnNext;
	JButton btnBack;

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
	JLabel lblItemDescription = new JLabel();
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
	
	
	public void viewItem(Customer new_cust, JFrame new_frame) {

		frame = new_frame;
		cust = new_cust;
		
		//frame.setVisible(true);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//frame.setBounds(50, 50, 450, 500);
		
		// Get Total Items
		if (count == 0) {
			loadItemList();
		}
				
		// TOP PANEL
		topPanel = new JPanel();
		lblTitle.setFont(new Font("Serif", Font.BOLD, 30));
		topPanel.add(lblTitle);

		// CENTER PANEL
		centerPanel = new JPanel();
		GridLayout gl_center = new GridLayout(6,3);
		gl_center.setVgap(5);
		gl_center.setHgap(0);
		centerPanel.setLayout(gl_center);

		centerPanel.setBorder(BorderFactory.createEmptyBorder(0,20,0,20));


		// GET NEXT ITEM
		if (count < totalItems) {
			
			itemName = item_list_array[count].getName();
			itemType = item_list_array[count].getType();
			itemDescription = item_list_array[count].getDescription();
			itemValue = item_list_array[count].getValue();
			itemCostPerDay = item_list_array[count].getDaily_rate();
			

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
			
			count++;
			
		}else {
			//Display NO Items Message
			lblNum.setText("You have not uploaded any items yet.");
			centerPanel.add(lblNum);
			
		}
		
		//BOTTOM PANEL
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(0, 2, 20,10));
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(0,50,20,50));
		
		btnBack = new JButton("Main Menu");
		btnBack.addActionListener(this);
		btnNext = new JButton("Next Item");
		btnNext.addActionListener(this);
		
		bottomPanel.add(btnBack);
		if( count < totalItems) {
			bottomPanel.add(btnNext);
			
		}


		frame.getContentPane().removeAll();
		frame.getContentPane().add(topPanel, BorderLayout.NORTH);
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

	}

}