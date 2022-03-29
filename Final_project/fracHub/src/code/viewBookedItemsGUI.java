package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class viewBookedItemsGUI implements ActionListener{

	JFrame frame;
	Customer cust;
	
	JPanel viewBookedItems;
	JPanel viewBookedItems2;
	JPanel viewBookedItems3;
	
	JButton btnReturnMM;
	JButton btnSignOut;
	
	JLabel lblBooking = new JLabel("Booking");
	JLabel lblItemNumber = new JLabel("Item Number");
	JLabel lblStartDate = new JLabel("Start Date");
	JLabel lblEndDate = new JLabel("End Date");
	JLabel lblTotalCost = new JLabel("Total Cost (£)");
	JLabel lblItemNumber2;
	JLabel lblStartDate2;
	JLabel lblEndDate2;
	JLabel lblTotalCost2;
	
	Booking[] booking_list;
	JComboBox<String> booking_numbers;
	
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
		viewBookedItems3.setLayout(new GridLayout(0,2,20,10));
		viewBookedItems.setBorder(BorderFactory.createEmptyBorder(50,50,0,50));
		viewBookedItems2.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
		viewBookedItems3.setBorder(BorderFactory.createEmptyBorder(0,50,20,50));
		viewBookedItems.setBackground(Color.white);
		viewBookedItems2.setBackground(Color.white);
		viewBookedItems3.setBackground(Color.white);
		
		
		btnReturnMM = new JButton("Main Menu");
		btnSignOut = new JButton("Sign Out");
		btnReturnMM.addActionListener(this);
		btnSignOut.addActionListener(this);
		
		Booking booking = new Booking();
		booking_list = booking.viewAllBookings(cust);

		
		String[] booking_num_list = new String[booking_list.length];
		for(int i = 1; i <= booking_list.length; i++) {
			booking_num_list[i-1] = Integer.toString(i);
		}
		
		booking_numbers = new JComboBox<String>(booking_num_list);
		booking_numbers.addActionListener(this);
		bookingNums = booking_numbers.getSelectedItem().toString();
		
		
		lblItemNumber2 = new JLabel(Integer.toString(booking_list[0].getBooking_num()));
		lblStartDate2 = new JLabel(booking_list[0].getStart_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		lblEndDate2 = new JLabel(booking_list[0].getEnd_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
		lblTotalCost2 = new JLabel(String.format("%.2f", booking_list[0].getTotal_cost()));
		

		viewBookedItems.add(lblBooking);
		viewBookedItems.add(booking_numbers);
		
		
		viewBookedItems2.add(lblItemNumber);
		viewBookedItems2.add(lblItemNumber2);
		viewBookedItems2.add(lblTotalCost);
		viewBookedItems2.add(lblTotalCost2);
		viewBookedItems2.add(lblStartDate);
		viewBookedItems2.add(lblStartDate2);
		viewBookedItems2.add(lblEndDate);
		viewBookedItems2.add(lblEndDate2);
		
		viewBookedItems3.add(btnSignOut);	
		viewBookedItems3.add(btnReturnMM);	
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(viewBookedItems, BorderLayout.NORTH);
		frame.getContentPane().add(viewBookedItems2, BorderLayout.CENTER);
		frame.getContentPane().add(viewBookedItems3, BorderLayout.SOUTH);
		frame.repaint();
		frame.revalidate();	
		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		String newBookingNums = booking_numbers.getSelectedItem().toString();
		
		if(newBookingNums != bookingNums) {
			bookingNums = newBookingNums;
			
			lblItemNumber2 = new JLabel(Integer.toString(booking_list[Integer.parseInt(bookingNums) - 1].getBooking_num()));
			lblStartDate2 = new JLabel(booking_list[Integer.parseInt(bookingNums) - 1].getStart_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			lblEndDate2 = new JLabel(booking_list[Integer.parseInt(bookingNums) - 1].getEnd_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			lblTotalCost2 = new JLabel(String.format("%.2f", booking_list[Integer.parseInt(bookingNums) - 1].getTotal_cost()));
			
			viewBookedItems2.removeAll();
			viewBookedItems2.add(lblItemNumber);
			viewBookedItems2.add(lblItemNumber2);
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
		else if(e.getSource() == btnSignOut)
		{
			mainMenuGUI new_panel = new mainMenuGUI();
			new_panel.mainMenu(frame);
		}
	}
}