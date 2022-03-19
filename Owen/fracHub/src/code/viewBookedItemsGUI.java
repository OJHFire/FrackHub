package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Map;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class viewBookedItemsGUI implements ActionListener{

	JFrame frame;
	Customer cust;
	
	JPanel viewBookedItems;
	JPanel viewBookedItems2;
	JPanel viewBookedItems3;
	
	JButton btnReturnMM;
	
	JLabel lblMonth = new JLabel("Month");
	JLabel lblYear = new JLabel("Year");
	JLabel lblItem = new JLabel("Item");
	
	JLabel lblType = new JLabel("Item type");
	JLabel lblName = new JLabel("Item Name");
	JLabel lblDescription = new JLabel("Description");
	JLabel lblStartDate = new JLabel("Start Date");
	JLabel lblEndDate = new JLabel("End Date");
	JLabel lblDailyCost = new JLabel("Daily Cost (�)");
	JLabel lblTotalCost = new JLabel("Total Cost (�)");
	JLabel lblType2 = new JLabel("Test Type");
	JLabel lblName2 = new JLabel("Test Name");
	JLabel lblItemDescription = new JLabel("test desc");
	JLabel lblStartDate2 = new JLabel("19/03/2022");
	JLabel lblEndDate2 = new JLabel("21/03/2022");
	JLabel lblItemDailyCost = new JLabel("0.75");
	JLabel lblTotalCost2 = new JLabel("2.25");
	

	JComboBox<String> itemList;
	JComboBox<String> yearList;
	JComboBox<String> monthList;
	//ArrayList<String> item_list;
	String[] item_list = {"TEST1", "test2", "test3"};
	String[] year_list = {"2022", "2023", "2024"};
	String[] month_list = {"January", "February", "March", "April", "May", "June",
							"July", "August", "September", "October", "November", "December"};
	
	Item[] item_list2;
	Item[] current_item_list2;
	String[] type_list2;
	Item currentItem;
	Item item = new Item();
	String type;
	String month;
	int year;
	double[][] yearInfo;
	double[] monthInfo;
	Map<String, Integer> map;
	
	public void viewBookedItems(Customer new_cust, JFrame new_frame) {
		frame = new_frame;
		cust = new_cust;
		
		viewBookedItems = new JPanel();
		viewBookedItems2 = new JPanel();
		viewBookedItems3 = new JPanel();
		viewBookedItems.setLayout(new GridLayout(0,2,0,20));
		viewBookedItems2.setLayout(new GridLayout(0,2,20,10));
		viewBookedItems3.setLayout(new GridLayout(0,1,20,10));
		viewBookedItems.setBorder(BorderFactory.createEmptyBorder(50,20,0,20));
		viewBookedItems.setBackground(Color.white);
		viewBookedItems2.setBackground(Color.white);
		viewBookedItems3.setBackground(Color.white);
		
		
		btnReturnMM = new JButton("Main Menu");
		btnReturnMM.addActionListener(this);
		
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM");
		month = today.format(formatter);
		year = today.getYear();
		
		yearList = new JComboBox<String>(year_list);
		monthList = new JComboBox<String>(month_list);
		itemList = new JComboBox<String>(item_list);
		yearList.setSelectedItem(String.valueOf(year));
		monthList.setSelectedItem(month);
		yearList.addActionListener(this);
		monthList.addActionListener(this);
		
		viewBookedItems.add(lblYear);
		viewBookedItems.add(yearList);
		viewBookedItems.add(lblMonth);
		viewBookedItems.add(monthList);
		viewBookedItems.add(lblItem);
		viewBookedItems.add(itemList);
		
		viewBookedItems2.add(lblType);
		viewBookedItems2.add(lblType2);
		viewBookedItems2.add(lblName);
		viewBookedItems2.add(lblName2);
		viewBookedItems2.add(lblDescription);
		viewBookedItems2.add(lblItemDescription);
		viewBookedItems2.add(lblDailyCost);
		viewBookedItems2.add(lblItemDailyCost);
		viewBookedItems2.add(lblTotalCost);
		viewBookedItems2.add(lblTotalCost2);
		viewBookedItems2.add(lblStartDate);
		viewBookedItems2.add(lblStartDate2);
		viewBookedItems2.add(lblEndDate);
		viewBookedItems2.add(lblEndDate2);
		
		viewBookedItems3.add(btnReturnMM);	
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(viewBookedItems, BorderLayout.NORTH);
		frame.getContentPane().add(viewBookedItems2, BorderLayout.CENTER);
		frame.getContentPane().add(viewBookedItems3, BorderLayout.SOUTH);
		frame.repaint();
		frame.revalidate();
	}
	
	public void updateItemList() {
		
	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == btnReturnMM)
		{
			optionMenuGUI new_panel = new optionMenuGUI();
			new_panel.optionMenu(cust, frame);
		}
	}
}
