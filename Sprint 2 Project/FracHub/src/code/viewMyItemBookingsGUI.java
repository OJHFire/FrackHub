package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Contains GUI to view all the bookings for a customer and function to delete a booking.
 */

public class viewMyItemBookingsGUI implements ActionListener{

	JFrame frame;
	Customer cust;
	GUI gui;

	JPanel viewMyItemBookings2;
	JPanel viewMyItemBookings3;
	
	JButton btnReturnMM;
	
	JLabel lblWarning = new JLabel();
	JLabel lblMonth = new JLabel("Month");
	JLabel lblYear = new JLabel("Year");
	JLabel lblBooking = new JLabel("Booking");
	JLabel lblItemName = new JLabel("Item");
	JLabel lblBookingNo = new JLabel("Booking Number");
	JLabel lblStartDate = new JLabel("Start Date");
	JLabel lblEndDate = new JLabel("End Date");
	JLabel lblTotalCost = new JLabel("Total Cost (£)");
	JLabel lblItemName2;
	JLabel lblBookingNo2;
	JLabel lblStartDate2;
	JLabel lblEndDate2;
	JLabel lblTotalCost2;
	JLabel space1 = new JLabel("    ");
	JLabel space2 = new JLabel("    ");
	
	JComboBox<String> yearList;
	JComboBox<String> monthList;
	JComboBox<String> booking_numbers;
	String[] year_list = {"2022", "2023", "2024"};
	String[] month_list = {"January", "February", "March", "April", "May", "June",
							"July", "August", "September", "October", "November", "December"};
	String[] booking_num_list;
	
	LocalDate today;
	String month;
	int year;
	Booking booking;
	Booking[][] year_booking_list;
	Booking[] month_booking_list;
	Map<String, Integer> map;
	String currentBookingNum;
	String selectedBooking;
	
	NimbusButton nimbusButton = new NimbusButton();
	ComboBox new_combo = new ComboBox();
	Font font = new Font("Calibri", Font.BOLD, 15);
	
	boolean noConnection = false;
	
	// Function to create GUI page.
	public viewMyItemBookingsGUI(Customer new_cust, JFrame new_frame) {
		frame = new_frame;
		cust = new_cust;
		
		// Remove all previous content on frame.
		frame.getContentPane().removeAll();
		
		map = new HashMap<String, Integer>();
		map.put("January", 0);
		map.put("February", 1);
		map.put("March", 2);
		map.put("April", 3);
		map.put("May", 4);
		map.put("June", 5);
		map.put("July", 6);
		map.put("August", 7);
		map.put("September", 8);
		map.put("October", 9);
		map.put("November", 10);
		map.put("December", 11);

		//NORTH PANEL
		gui = new GUI();
		//Create title at the top of the page.
		gui.pageHeader(frame, "View My Item Bookings");
		
		
		//CENTRE PANEL
		viewMyItemBookings2 = new JPanel();
		viewMyItemBookings2.setLayout(new GridLayout(0,2,20,10));
		viewMyItemBookings2.setBorder(BorderFactory.createEmptyBorder(30,50,10,50));
		viewMyItemBookings2.setBackground(Color.white);
		
		// Finds todays date to set the drop down box to current month.
		today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM");
		month = today.format(formatter);
		year = today.getYear();
		
		yearList = new JComboBox<String>(year_list);
		new_combo.setComboBox(yearList);
		yearList.setBackground(Color.WHITE);
		yearList.setSelectedItem(String.valueOf(year));
		((JLabel)yearList.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((JLabel)yearList.getRenderer()).setVerticalAlignment(JLabel.TOP);
		yearList.addActionListener(this);
		
		monthList = new JComboBox<String>(month_list);
		new_combo.setComboBox(monthList);
		monthList.setBackground(Color.WHITE);
		((JLabel)monthList.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((JLabel)monthList.getRenderer()).setVerticalAlignment(JLabel.TOP);
		monthList.setSelectedItem(month);
		monthList.addActionListener(this);
		
		// Retrieve all the bookings for the customer.
		booking = new Booking();
		year_booking_list = booking.viewAllMyItemBookings(year, cust);
		
		// The list will have a length of 12 unless there has been no connection with the database.
		if (year_booking_list.length == 12) {
			
			// Select current month for list.
			month_booking_list = year_booking_list[today.getMonthValue() - 1];
			
			// Display the details if there are bookings in the chosen month.
			if (month_booking_list.length > 0) {
				booking_num_list = new String[month_booking_list.length];
				for(int i = 0; i < month_booking_list.length; i++) {
					booking_num_list[i] = Integer.toString(i + 1);
				}
						
				booking_numbers = new JComboBox<String>(booking_num_list);
				new_combo.setComboBox(booking_numbers);
				booking_numbers.setBackground(Color.WHITE);
				((JLabel)booking_numbers.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
				((JLabel)booking_numbers.getRenderer()).setVerticalAlignment(JLabel.TOP);
				booking_numbers.addActionListener(this);
				currentBookingNum = booking_numbers.getSelectedItem().toString();
						
				lblItemName2 = new JLabel(month_booking_list[0].getItem_name());
				lblBookingNo2 = new JLabel(Integer.toString(month_booking_list[0].getBooking_num()));
				lblStartDate2 = new JLabel(month_booking_list[0].getStart_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
				lblEndDate2 = new JLabel(month_booking_list[0].getEnd_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
				lblTotalCost2 = new JLabel(String.format("%.2f", month_booking_list[0].getTotal_cost()));
				
				// Set font of labels.
				yearList.setFont(font);
				lblYear.setFont(font);
				monthList.setFont(font);
				lblMonth.setFont(font);
				lblBooking.setFont(font);
				booking_numbers.setFont(font);
				//itemList.setFont(font);
				lblItemName.setFont(font);
				lblBookingNo.setFont(font);
				lblStartDate.setFont(font);
				lblEndDate.setFont(font);
				lblTotalCost.setFont(font);
				
				lblItemName2.setFont(font);
				lblBookingNo2.setFont(font);
				lblStartDate2.setFont(font);
				lblEndDate2.setFont(font);
				lblTotalCost2.setFont(font);
				
				// Add labels to the panel.
				viewMyItemBookings2.add(lblYear);
				viewMyItemBookings2.add(yearList);
				viewMyItemBookings2.add(lblMonth);
				viewMyItemBookings2.add(monthList);
				viewMyItemBookings2.add(lblBooking);
				viewMyItemBookings2.add(booking_numbers);
				viewMyItemBookings2.add(space1);
				viewMyItemBookings2.add(space2);	
				viewMyItemBookings2.add(lblItemName);
				viewMyItemBookings2.add(lblItemName2);
				viewMyItemBookings2.add(lblBookingNo);
				viewMyItemBookings2.add(lblBookingNo2);
				viewMyItemBookings2.add(lblTotalCost);
				viewMyItemBookings2.add(lblTotalCost2);
				viewMyItemBookings2.add(lblStartDate);
				viewMyItemBookings2.add(lblStartDate2);
				viewMyItemBookings2.add(lblEndDate);
				viewMyItemBookings2.add(lblEndDate2);
			}
			// Display warning if there are no bookings for chosen month.
			else {
				
				String[] list = {"0"};
				currentBookingNum = "0";
				booking_numbers = new JComboBox<String>(list);
				JLabel lblWarning = new JLabel("There are no bookings yet.");
				lblWarning.setHorizontalAlignment(JLabel.CENTER);
				viewMyItemBookings2.setLayout(new GridLayout(0,1,20,10));
				viewMyItemBookings2.add(lblWarning);
	
			}
		}
		// Display warning if the bookings could not be retrieved from the database.
		else {
			
			String[] list = {"0"};
			currentBookingNum = "0";
			booking_numbers = new JComboBox<String>(list);
			JLabel lblWarning = new JLabel("Connection Issue - Please try again later.");
			lblWarning.setHorizontalAlignment(JLabel.CENTER);
			viewMyItemBookings2.setLayout(new GridLayout(0,1,20,10));
			viewMyItemBookings2.add(lblWarning);
			
		}
				
		//SOUTH PANEL
		viewMyItemBookings3 = new JPanel();
		viewMyItemBookings3.setLayout(new GridLayout(0,1,20,10));
		viewMyItemBookings3.setBorder(BorderFactory.createEmptyBorder(0,140,20,140));
		viewMyItemBookings3.setBackground(Color.white);
		
		btnReturnMM = nimbusButton.generateNimbusButton("Main Menu");
		btnReturnMM.putClientProperty("JComponent.sizeVariant", "large");
		btnReturnMM.addActionListener(this);
		viewMyItemBookings3.add(btnReturnMM);
				
		// Add panels to the frame.
		frame.getContentPane().add(viewMyItemBookings2, BorderLayout.CENTER);
		frame.getContentPane().add(viewMyItemBookings3, BorderLayout.SOUTH);
		frame.repaint();
		frame.revalidate();	

	}
	
	// Function to display warning message.
	public void inputWarning(String message) {
		
		viewMyItemBookings2.removeAll();
		viewMyItemBookings2.setBorder(BorderFactory.createEmptyBorder(20,50,205,50));
		gui.inputWarning(message);
		viewMyItemBookings2.add(lblYear);
		viewMyItemBookings2.add(yearList);
		viewMyItemBookings2.add(lblMonth);
		viewMyItemBookings2.add(monthList);
		viewMyItemBookings2.add(space1);
		viewMyItemBookings2.add(space2);

		frame.repaint();
		frame.revalidate();

	}
	
	public void actionPerformed(ActionEvent e) {
		
		String selectedYear = yearList.getSelectedItem().toString();
		String selectedMonth = monthList.getSelectedItem().toString();
		selectedBooking = booking_numbers.getSelectedItem().toString();
				
		// When the month type in the JComboBox is changed the booking list is updated to the chosen month.
		if (!month.equals(selectedMonth)){

			month = selectedMonth;
			if (noConnection) {
				year_booking_list = booking.viewAllMyItemBookings(year, cust);
			}
			
			// Check for connection with the database.
			if (year_booking_list.length == 12) {
				
				noConnection = false;
				month_booking_list = year_booking_list[map.get(month)];
				
				// If there are no bookings a warning is displayed.
				if (month_booking_list.length == 0) {
					
					inputWarning("There are no bookings on the selected month.");
	
				}
				else {
					viewMyItemBookings2.setBorder(BorderFactory.createEmptyBorder(30,50,10,50));
					
					gui.removeInputWarning();
					
					booking_num_list = new String[month_booking_list.length];
					for(int i = 0; i < month_booking_list.length; i++) {
						booking_num_list[i] = Integer.toString(i + 1);
					}
					
					currentBookingNum = "1";

					lblItemName2 = new JLabel(month_booking_list[Integer.parseInt(currentBookingNum) - 1].getItem_name());
					lblBookingNo2 = new JLabel(Integer.toString(month_booking_list[Integer.parseInt(currentBookingNum) - 1].getBooking_num()));
					lblStartDate2 = new JLabel(month_booking_list[Integer.parseInt(currentBookingNum) - 1].getStart_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
					lblEndDate2 = new JLabel(month_booking_list[Integer.parseInt(currentBookingNum) - 1].getEnd_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
					lblTotalCost2 = new JLabel(String.format("%.2f", month_booking_list[Integer.parseInt(currentBookingNum) - 1].getTotal_cost()));
					
					lblItemName2.setFont(font);
					lblBookingNo2.setFont(font);
					lblStartDate2.setFont(font);
					lblEndDate2.setFont(font);
					lblTotalCost2.setFont(font);
					
					booking_numbers = new JComboBox<String>(booking_num_list);
					new_combo.setComboBox(booking_numbers);
					booking_numbers.setBackground(Color.WHITE);
					((JLabel)booking_numbers.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
					((JLabel)booking_numbers.getRenderer()).setVerticalAlignment(JLabel.TOP);
					booking_numbers.addActionListener(this);
					
					booking_numbers.setSelectedIndex(0);
					currentBookingNum = booking_numbers.getSelectedItem().toString();
					
					viewMyItemBookings2.removeAll();
					viewMyItemBookings2.add(lblYear);
					viewMyItemBookings2.add(yearList);
					viewMyItemBookings2.add(lblMonth);
					viewMyItemBookings2.add(monthList);
					viewMyItemBookings2.add(lblBooking);
					viewMyItemBookings2.add(booking_numbers);
					viewMyItemBookings2.add(space1);
					viewMyItemBookings2.add(space2);		
					viewMyItemBookings2.add(lblItemName);
					viewMyItemBookings2.add(lblItemName2);
					viewMyItemBookings2.add(lblBookingNo);
					viewMyItemBookings2.add(lblBookingNo2);
					viewMyItemBookings2.add(lblTotalCost);
					viewMyItemBookings2.add(lblTotalCost2);
					viewMyItemBookings2.add(lblStartDate);
					viewMyItemBookings2.add(lblStartDate2);
					viewMyItemBookings2.add(lblEndDate);
					viewMyItemBookings2.add(lblEndDate2);
								
					frame.getContentPane().add(viewMyItemBookings2, BorderLayout.CENTER);
					frame.repaint();
					frame.revalidate();
				}
			}
			// If there is no connection with the database a warning message is displayed.
			else {
				inputWarning("Connection Issue - Please try again later.");
	
			}
		}
		
		// When the year type in the JComboBox is changed the new booking list are loaded from the database.
		else if (year != Integer.parseInt(selectedYear)) {

			year = Integer.parseInt(selectedYear);
			
			year_booking_list = booking.viewAllMyItemBookings(year, cust);
			
			// Check for connection with the database.
			if (year_booking_list.length == 12) {
				noConnection = false;
				month_booking_list = year_booking_list[map.get(month)];
	
				// If there are no bookings for selected month a warning message is displayed.
				if (month_booking_list.length == 0) {
	
					inputWarning("There are no bookings on the selected month.");
	
				}
				else {
					viewMyItemBookings2.setBorder(BorderFactory.createEmptyBorder(30,50,10,50));
					
					gui.removeInputWarning();
					
					booking_num_list = new String[month_booking_list.length];
					for(int i = 0; i < month_booking_list.length; i++) {
						booking_num_list[i] = Integer.toString(i + 1);
					}
					
					currentBookingNum = "1";
					
					lblItemName2 = new JLabel(month_booking_list[Integer.parseInt(currentBookingNum) - 1].getItem_name());
					lblBookingNo2 = new JLabel(Integer.toString(month_booking_list[Integer.parseInt(currentBookingNum) - 1].getBooking_num()));
					lblStartDate2 = new JLabel(month_booking_list[Integer.parseInt(currentBookingNum) - 1].getStart_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
					lblEndDate2 = new JLabel(month_booking_list[Integer.parseInt(currentBookingNum) - 1].getEnd_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
					lblTotalCost2 = new JLabel(String.format("%.2f", month_booking_list[Integer.parseInt(currentBookingNum) - 1].getTotal_cost()));

					lblItemName2.setFont(font);
					lblBookingNo2.setFont(font);
					lblStartDate2.setFont(font);
					lblEndDate2.setFont(font);
					lblTotalCost2.setFont(font);
					
					booking_numbers = new JComboBox<String>(booking_num_list);
					new_combo.setComboBox(booking_numbers);
					booking_numbers.setBackground(Color.WHITE);
					((JLabel)booking_numbers.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
					((JLabel)booking_numbers.getRenderer()).setVerticalAlignment(JLabel.TOP);
					booking_numbers.addActionListener(this);
					
					booking_numbers.setSelectedIndex(0);
					currentBookingNum = booking_numbers.getSelectedItem().toString();
					
					viewMyItemBookings2.removeAll();
					viewMyItemBookings2.add(lblYear);
					viewMyItemBookings2.add(yearList);
					viewMyItemBookings2.add(lblMonth);
					viewMyItemBookings2.add(monthList);
					viewMyItemBookings2.add(lblBooking);
					viewMyItemBookings2.add(booking_numbers);
					viewMyItemBookings2.add(space1);
					viewMyItemBookings2.add(space2);		
					viewMyItemBookings2.add(lblItemName);
					viewMyItemBookings2.add(lblItemName2);
					viewMyItemBookings2.add(lblBookingNo);
					viewMyItemBookings2.add(lblBookingNo2);
					viewMyItemBookings2.add(lblTotalCost);
					viewMyItemBookings2.add(lblTotalCost2);
					viewMyItemBookings2.add(lblStartDate);
					viewMyItemBookings2.add(lblStartDate2);
					viewMyItemBookings2.add(lblEndDate);
					viewMyItemBookings2.add(lblEndDate2);
							
					frame.getContentPane().remove(viewMyItemBookings2);
					frame.getContentPane().add(viewMyItemBookings2, BorderLayout.CENTER);
					frame.repaint();
					frame.revalidate();
				}	
			}
			// If there is no connection with the database a warning message is displayed.
			else {
				noConnection = true;
				inputWarning("Connection Issue - Please try again later.");
	
			}
		}
		
		// When the booking number is changed the new booking details are add to the centre panel.
		else if(!currentBookingNum.equals(selectedBooking)) {

			currentBookingNum = selectedBooking;
			
			lblItemName2 = new JLabel(month_booking_list[Integer.parseInt(currentBookingNum) - 1].getItem_name());
			lblBookingNo2 = new JLabel(Integer.toString(month_booking_list[Integer.parseInt(currentBookingNum) - 1].getBooking_num()));
			lblStartDate2 = new JLabel(month_booking_list[Integer.parseInt(currentBookingNum) - 1].getStart_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			lblEndDate2 = new JLabel(month_booking_list[Integer.parseInt(currentBookingNum) - 1].getEnd_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			lblTotalCost2 = new JLabel(String.format("%.2f", month_booking_list[Integer.parseInt(currentBookingNum) - 1].getTotal_cost()));
			
			lblItemName2.setFont(font);
			lblBookingNo2.setFont(font);
			lblStartDate2.setFont(font);
			lblEndDate2.setFont(font);
			lblTotalCost2.setFont(font);
			
			viewMyItemBookings2.removeAll();
			viewMyItemBookings2.add(lblYear);
			viewMyItemBookings2.add(yearList);
			viewMyItemBookings2.add(lblMonth);
			viewMyItemBookings2.add(monthList);
			viewMyItemBookings2.add(lblBooking);
			viewMyItemBookings2.add(booking_numbers);
			viewMyItemBookings2.add(space1);
			viewMyItemBookings2.add(space2);		
			viewMyItemBookings2.add(lblItemName);
			viewMyItemBookings2.add(lblItemName2);
			viewMyItemBookings2.add(lblBookingNo);
			viewMyItemBookings2.add(lblBookingNo2);
			viewMyItemBookings2.add(lblTotalCost);
			viewMyItemBookings2.add(lblTotalCost2);
			viewMyItemBookings2.add(lblStartDate);
			viewMyItemBookings2.add(lblStartDate2);
			viewMyItemBookings2.add(lblEndDate);
			viewMyItemBookings2.add(lblEndDate2);	
			
			frame.getContentPane().remove(viewMyItemBookings2);
			frame.getContentPane().add(viewMyItemBookings2, BorderLayout.CENTER);
			frame.repaint();
			frame.revalidate();
			
		}

		// When the main menu button is pressed the optionMenuGUI is created.
		else if(e.getSource() == btnReturnMM)
		{
			optionMenuGUI new_panel = new optionMenuGUI();
			new_panel.optionMenu(cust, frame);
		}
	}	
}