package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Array;
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
	
	JLabel lblItem = new JLabel("Item");
	
	JLabel lblType = new JLabel("Item type");
	JLabel lblName = new JLabel("Item Name");
	JLabel lblDescription = new JLabel("Description");
	JLabel lblStartDate = new JLabel("Start Date");
	JLabel lblEndDate = new JLabel("End Date");
	JLabel lblDailyCost = new JLabel("Daily Cost (£)");
	JLabel lblTotalCost = new JLabel("Total Cost (£)");
	JLabel lblType2;
	JLabel lblName2;
	JLabel lblItemDescription;
	JLabel lblStartDate2;
	JLabel lblEndDate2;
	JLabel lblItemDailyCost;
	JLabel lblTotalCost2;
	
	Booking[] b_list;
	

	JComboBox<String> itemList;
	
	Item currentItem;
	Item item = new Item();
	String bookingNums;
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
		
		Booking b1 = new Booking();
		b_list = b1.viewAllBookings(cust);
		double totalCost = b_list[0].getTotal_cost();
		
		String[] booking_num_list = new String[b_list.length];
		
		
		for(int i = 1; i <= b_list.length; i++) {
			booking_num_list[i-1] = Integer.toString(i);
		}
		
		itemList = new JComboBox<String>(booking_num_list);
		itemList.addActionListener(this);
		bookingNums = itemList.getSelectedItem().toString();
		
		
		lblName2 = new JLabel(Integer.toString(b_list[0].getBooking_num()));
		lblStartDate2 = new JLabel(b_list[0].getStart_date().toString());
		lblEndDate2 = new JLabel(b_list[0].getEnd_date().toString());
		lblTotalCost2 = new JLabel(Double.toString(totalCost));
		

		viewBookedItems.add(lblItem);
		viewBookedItems.add(itemList);
		
		
		viewBookedItems2.add(lblName);
		viewBookedItems2.add(lblName2);
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
	
	public void actionPerformed(ActionEvent e) {
		
		String newBookingNums = itemList.getSelectedItem().toString();
		
		if(newBookingNums != bookingNums) {
			bookingNums = newBookingNums;
			double totalCost = b_list[Integer.parseInt(bookingNums) - 1].getTotal_cost();
			
			lblName2 = new JLabel(Integer.toString(b_list[Integer.parseInt(bookingNums) - 1].getBooking_num()));
			lblStartDate2 = new JLabel(b_list[Integer.parseInt(bookingNums) - 1].getStart_date().toString());
			lblEndDate2 = new JLabel(b_list[Integer.parseInt(bookingNums) - 1].getEnd_date().toString());
			lblTotalCost2 = new JLabel(Double.toString(totalCost));
			
			viewBookedItems2.removeAll();
			viewBookedItems2.add(lblName);
			viewBookedItems2.add(lblName2);
			viewBookedItems2.add(lblTotalCost);
			viewBookedItems2.add(lblTotalCost2);
			viewBookedItems2.add(lblStartDate);
			viewBookedItems2.add(lblStartDate2);
			viewBookedItems2.add(lblEndDate);
			viewBookedItems2.add(lblEndDate2);
			
			
			frame.getContentPane().remove(viewBookedItems2);
			frame.getContentPane().add(viewBookedItems2, BorderLayout.CENTER);
			frame.repaint();
			frame.revalidate();
			
		}

		if(e.getSource() == btnReturnMM)
		{
			optionMenuGUI new_panel = new optionMenuGUI();
			new_panel.optionMenu(cust, frame);
		}
	}
}
