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

public class viewBookedItemsGUI implements ActionListener{

	JFrame frame;
	Customer cust;
	GUI gui;
	
	JPanel viewBookedItems1;
	JPanel viewBookedItems2;
	JPanel viewBookedItems3;
	
	JButton btnReturnMM;
	JButton btnDelete;
	JButton btnYes;
	JButton btnNo;
	
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
	
	LocalDate today;
	String month;
	int year;
	Booking booking;
	Booking[][] year_booking_list;
	Booking[] month_booking_list;
	Map<String, Integer> map;
	String bookingNums;
	
	NimbusButton nimbusButton = new NimbusButton();
	ComboBox new_combo = new ComboBox();
	Font font = new Font("Calibri", Font.BOLD, 15);
	
	boolean noConnection = false;
	
	// Function to create GUI page.
	public viewBookedItemsGUI(Customer new_cust, JFrame new_frame) {
		frame = new_frame;
		cust = new_cust;
		
		// Remove all previous content on frame.
		frame.getContentPane().removeAll();

		//NORTH PANEL
		gui = new GUI();
		//Create title at the top of the page.
		gui.pageHeader(frame, "View Booked Items");
		
		
		//CENTRE PANEL
		viewBookedItems2 = new JPanel();
		viewBookedItems2.setLayout(new GridLayout(0,2,20,10));
		viewBookedItems2.setBorder(BorderFactory.createEmptyBorder(40,50,10,50));
		viewBookedItems2.setBackground(Color.white);
		
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
		year_booking_list = booking.viewAllBookings(year, cust);
		
		// The list will have a length of 12 unless there has been no connection with the database.
		if (year_booking_list.length == 12) {
			
			// Select current month for list.
			month_booking_list = year_booking_list[today.getMonthValue() - 1];
			
			// Display the details if there are bookings in the chosen month.
			if (month_booking_list.length > 0) {
				String[] booking_num_list = new String[month_booking_list.length];
				for(int i = 0; i < month_booking_list.length; i++) {
					booking_num_list[i] = Integer.toString(i + 1);
				}
				
				booking_numbers = new JComboBox<String>(booking_num_list);
				new_combo.setComboBox(booking_numbers);
				booking_numbers.setBackground(Color.WHITE);
				((JLabel)booking_numbers.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
				((JLabel)booking_numbers.getRenderer()).setVerticalAlignment(JLabel.TOP);
				booking_numbers.addActionListener(this);
				bookingNums = booking_numbers.getSelectedItem().toString();
						
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
				booking_numbers.setFont(font);
				lblBooking.setFont(font);
				booking_numbers.setFont(font);
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
				viewBookedItems2.add(lblMonth);
				viewBookedItems2.add(monthList);
				viewBookedItems2.add(lblYear);
				viewBookedItems2.add(yearList);
				viewBookedItems2.add(lblBooking);
				viewBookedItems2.add(booking_numbers);
				viewBookedItems2.add(space1);
				viewBookedItems2.add(space2);		
				viewBookedItems2.add(lblItemName);
				viewBookedItems2.add(lblItemName2);
				viewBookedItems2.add(lblBookingNo);
				viewBookedItems2.add(lblBookingNo2);
				viewBookedItems2.add(lblTotalCost);
				viewBookedItems2.add(lblTotalCost2);
				viewBookedItems2.add(lblStartDate);
				viewBookedItems2.add(lblStartDate2);
				viewBookedItems2.add(lblEndDate);
				viewBookedItems2.add(lblEndDate2);
				
				
				//SOUTH PANEL
				viewBookedItems3 = new JPanel();
				viewBookedItems3.setLayout(new GridLayout(0,2,20,10));
				viewBookedItems3.setBorder(BorderFactory.createEmptyBorder(0,50,20,50));
				viewBookedItems3.setBackground(Color.white);
				
				btnReturnMM = nimbusButton.generateNimbusButton("Main Menu");
				btnDelete = nimbusButton.generateNimbusButton("Cancel Booking");
				btnReturnMM.putClientProperty("JComponent.sizeVariant", "large");
				btnDelete.putClientProperty("JComponent.sizeVariant", "large");
				btnReturnMM.addActionListener(this);
				btnDelete.addActionListener(this);
				
				// Only add delete button for bookings in the future.
				if (today.isBefore(month_booking_list[Integer.parseInt(bookingNums) - 1].getStart_date())) {
					viewBookedItems3.setLayout(new GridLayout(0,2,20,10));
					viewBookedItems3.setBorder(BorderFactory.createEmptyBorder(0,50,20,50));
					viewBookedItems3.add(btnDelete);
					viewBookedItems3.add(btnReturnMM);
				}
				else {
					viewBookedItems3.setLayout(new GridLayout(0,1,20,10));
					viewBookedItems3.setBorder(BorderFactory.createEmptyBorder(0,140,20,140));
					viewBookedItems3.add(btnReturnMM);
				}	
			}
			// Display warning if there are no bookings for chosen month.
			else {
				
				String[] list = {"0"};
				bookingNums = "0";
				booking_numbers = new JComboBox<String>(list);
				JLabel lblWarning = new JLabel("You have not booked any items yet.");
				lblWarning.setHorizontalAlignment(JLabel.CENTER);
				viewBookedItems2.setLayout(new GridLayout(0,1,20,10));
				viewBookedItems2.add(lblWarning);
				
				viewBookedItems3 = new JPanel();
				btnReturnMM = nimbusButton.generateNimbusButton("Main Menu");
				btnReturnMM.putClientProperty("JComponent.sizeVariant", "large");
				btnReturnMM.addActionListener(this);
				viewBookedItems3.setLayout(new GridLayout(0,1,20,10));
				viewBookedItems3.setBorder(BorderFactory.createEmptyBorder(0,140,20,140));
				viewBookedItems3.setBackground(Color.white);
				viewBookedItems3.add(btnReturnMM);
			}
		}
		// Display warning if the bookings could not be retrieved from the database.
		else {
			
			String[] list = {"0"};
			bookingNums = "0";
			booking_numbers = new JComboBox<String>(list);
			JLabel lblWarning = new JLabel("Connection Issue - Please try again later.");
			lblWarning.setHorizontalAlignment(JLabel.CENTER);
			viewBookedItems2.setLayout(new GridLayout(0,1,20,10));
			viewBookedItems2.add(lblWarning);
			
			viewBookedItems3 = new JPanel();
			btnReturnMM = nimbusButton.generateNimbusButton("Main Menu");
			btnReturnMM.putClientProperty("JComponent.sizeVariant", "large");
			btnReturnMM.addActionListener(this);
			viewBookedItems3.setLayout(new GridLayout(0,1,20,10));
			viewBookedItems3.setBorder(BorderFactory.createEmptyBorder(0,140,20,140));
			viewBookedItems3.setBackground(Color.white);
			viewBookedItems3.add(btnReturnMM);
			
		}
		
		
		// Add panels to the frame.
		frame.getContentPane().add(viewBookedItems2, BorderLayout.CENTER);
		frame.getContentPane().add(viewBookedItems3, BorderLayout.SOUTH);
		frame.repaint();
		frame.revalidate();	
		
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

	}
	
	// Function to display warning message.
	public void inputWarning(String message) {
		
		viewBookedItems2.removeAll();
		viewBookedItems3.removeAll();
		viewBookedItems3.setLayout(new GridLayout(0,1,20,10));
		viewBookedItems2.setBorder(BorderFactory.createEmptyBorder(20,50,205,50));
		viewBookedItems3.setBorder(BorderFactory.createEmptyBorder(0,140,20,140));
		gui.inputWarning(message);
		viewBookedItems2.add(lblMonth);
		viewBookedItems2.add(monthList);
		viewBookedItems2.add(lblYear);
		viewBookedItems2.add(yearList);
		viewBookedItems2.add(space1);
		viewBookedItems2.add(space2);

		viewBookedItems3.add(btnReturnMM);
		frame.repaint();
		frame.revalidate();

	}
	
	// Function to add confirmation buttons to delete a booking from the database.
	public void deleteBooking() {
		
		viewBookedItems2.removeAll();
		viewBookedItems2.setBorder(BorderFactory.createEmptyBorder(40,50,15,50));
		viewBookedItems2.add(lblMonth);
		JLabel month = new JLabel(monthList.getSelectedItem().toString());
		month.setFont(font);
		viewBookedItems2.add(month);
		viewBookedItems2.add(lblYear);
		JLabel year = new JLabel(yearList.getSelectedItem().toString());
		year.setFont(font);
		viewBookedItems2.add(year);
		viewBookedItems2.add(lblBooking);
		JLabel booking = new JLabel(booking_numbers.getSelectedItem().toString());
		booking.setFont(font);
		viewBookedItems2.add(booking);
		viewBookedItems2.add(space1);
		viewBookedItems2.add(space2);
		viewBookedItems2.add(lblItemName);
		viewBookedItems2.add(lblItemName2);
		viewBookedItems2.add(lblBookingNo);
		viewBookedItems2.add(lblBookingNo2);
		viewBookedItems2.add(lblTotalCost);
		viewBookedItems2.add(lblTotalCost2);
		viewBookedItems2.add(lblStartDate);
		viewBookedItems2.add(lblStartDate2);
		viewBookedItems2.add(lblEndDate);
		viewBookedItems2.add(lblEndDate2);
		
		viewBookedItems3.removeAll();
		JLabel warning = new JLabel("Are you sure?");
		warning.setFont(font);
		JLabel warning2 = new JLabel("            ");
		btnYes = nimbusButton.generateNimbusButton("Delete");
		btnNo = nimbusButton.generateNimbusButton("Undo");
		btnYes.putClientProperty("JComponent.sizeVariant", "large");
		btnNo.putClientProperty("JComponent.sizeVariant", "large");
		btnYes.addActionListener(this);
		btnNo.addActionListener(this);
		viewBookedItems3.add(warning);
		viewBookedItems3.add(warning2);
		viewBookedItems3.add(btnYes);
		viewBookedItems3.add(btnNo);
		frame.repaint();
		frame.revalidate();
	}
	
	// Function to return to the original page if booking request cancelled.
	public void cancelDeleteBooking() {

		viewBookedItems2.removeAll();
		viewBookedItems2.setBorder(BorderFactory.createEmptyBorder(40,50,10,50));
		viewBookedItems2.add(lblMonth);
		viewBookedItems2.add(monthList);
		viewBookedItems2.add(lblYear);
		viewBookedItems2.add(yearList);
		viewBookedItems2.add(lblBooking);
		viewBookedItems2.add(booking_numbers);
		viewBookedItems2.add(space1);
		viewBookedItems2.add(space2);
		viewBookedItems2.add(lblItemName);
		viewBookedItems2.add(lblItemName2);
		viewBookedItems2.add(lblBookingNo);
		viewBookedItems2.add(lblBookingNo2);
		viewBookedItems2.add(lblTotalCost);
		viewBookedItems2.add(lblTotalCost2);
		viewBookedItems2.add(lblStartDate);
		viewBookedItems2.add(lblStartDate2);
		viewBookedItems2.add(lblEndDate);
		viewBookedItems2.add(lblEndDate2);
		
		viewBookedItems3.removeAll();
		viewBookedItems3.add(btnDelete);	
		viewBookedItems3.add(btnReturnMM);
		frame.repaint();
		frame.revalidate();
	}
	
	public void actionPerformed(ActionEvent e) {
		
		String selectedBooking = booking_numbers.getSelectedItem().toString();
		String selectedMonth = monthList.getSelectedItem().toString();
		String selectedYear = yearList.getSelectedItem().toString();
		
		// When the month type in the JComboBox is changed the booking list is updated to the chosen month.
		if (!month.equals(selectedMonth)) {
			
			month = selectedMonth;
			
			if (noConnection) {
				year_booking_list = booking.viewAllBookings(year, cust);
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
					viewBookedItems2.setBorder(BorderFactory.createEmptyBorder(40,50,10,50));
					viewBookedItems3.setLayout(new GridLayout(0,2,20,10));
					viewBookedItems3.setBorder(BorderFactory.createEmptyBorder(0,50,20,50));
					
					gui.removeInputWarning();
					
					String[] booking_num_list = new String[month_booking_list.length];
					for(int i = 0; i < month_booking_list.length; i++) {
						booking_num_list[i] = Integer.toString(i + 1);
					}
					
					bookingNums = "1";
					
					lblItemName2 = new JLabel(month_booking_list[Integer.parseInt(bookingNums) - 1].getItem_name());
					lblBookingNo2 = new JLabel(Integer.toString(month_booking_list[Integer.parseInt(bookingNums) - 1].getBooking_num()));
					lblStartDate2 = new JLabel(month_booking_list[Integer.parseInt(bookingNums) - 1].getStart_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
					lblEndDate2 = new JLabel(month_booking_list[Integer.parseInt(bookingNums) - 1].getEnd_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
					lblTotalCost2 = new JLabel(String.format("%.2f", month_booking_list[Integer.parseInt(bookingNums) - 1].getTotal_cost()));
					
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
					bookingNums = booking_numbers.getSelectedItem().toString();
					
					viewBookedItems2.removeAll();
					viewBookedItems2.add(lblMonth);
					viewBookedItems2.add(monthList);
					viewBookedItems2.add(lblYear);
					viewBookedItems2.add(yearList);
					viewBookedItems2.add(lblBooking);
					viewBookedItems2.add(booking_numbers);
					viewBookedItems2.add(space1);
					viewBookedItems2.add(space2);
					viewBookedItems2.add(lblItemName);
					viewBookedItems2.add(lblItemName2);
					viewBookedItems2.add(lblBookingNo);
					viewBookedItems2.add(lblBookingNo2);
					viewBookedItems2.add(lblTotalCost);
					viewBookedItems2.add(lblTotalCost2);
					viewBookedItems2.add(lblStartDate);
					viewBookedItems2.add(lblStartDate2);
					viewBookedItems2.add(lblEndDate);
					viewBookedItems2.add(lblEndDate2);
					
					viewBookedItems3.removeAll();
					if (today.isBefore(month_booking_list[Integer.parseInt(bookingNums) - 1].getStart_date())) {
						viewBookedItems3.setLayout(new GridLayout(0,2,20,10));
						viewBookedItems3.setBorder(BorderFactory.createEmptyBorder(0,50,20,50));
						viewBookedItems3.add(btnDelete);
						viewBookedItems3.add(btnReturnMM);
					}
					else {
						viewBookedItems3.setLayout(new GridLayout(0,1,20,10));
						viewBookedItems3.setBorder(BorderFactory.createEmptyBorder(0,140,20,140));
						viewBookedItems3.add(btnReturnMM);
					}
					
					frame.getContentPane().add(viewBookedItems2, BorderLayout.CENTER);
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
			
			year_booking_list = booking.viewAllBookings(year, cust);
			
			// Check for connection with the database.
			if (year_booking_list.length == 12) {
				noConnection = false;
				month_booking_list = year_booking_list[map.get(month)];
	
				// If there are no bookings for selected month a warning message is displayed.
				if (month_booking_list.length == 0) {
	
					inputWarning("There are no bookings on the selected month.");
	
				}
				else {
					viewBookedItems2.setBorder(BorderFactory.createEmptyBorder(40,50,10,50));
					viewBookedItems3.setLayout(new GridLayout(0,2,20,10));
					viewBookedItems3.setBorder(BorderFactory.createEmptyBorder(0,50,20,50));
					
					gui.removeInputWarning();
					
					String[] booking_num_list = new String[month_booking_list.length];
					for(int i = 0; i < month_booking_list.length; i++) {
						booking_num_list[i] = Integer.toString(i + 1);
					}
					
					bookingNums = "1";
					
					lblItemName2 = new JLabel(month_booking_list[Integer.parseInt(bookingNums) - 1].getItem_name());
					lblBookingNo2 = new JLabel(Integer.toString(month_booking_list[Integer.parseInt(bookingNums) - 1].getBooking_num()));
					lblStartDate2 = new JLabel(month_booking_list[Integer.parseInt(bookingNums) - 1].getStart_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
					lblEndDate2 = new JLabel(month_booking_list[Integer.parseInt(bookingNums) - 1].getEnd_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
					lblTotalCost2 = new JLabel(String.format("%.2f", month_booking_list[Integer.parseInt(bookingNums) - 1].getTotal_cost()));
					
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
					bookingNums = booking_numbers.getSelectedItem().toString();
					
					viewBookedItems2.removeAll();
					viewBookedItems2.add(lblMonth);
					viewBookedItems2.add(monthList);
					viewBookedItems2.add(lblYear);
					viewBookedItems2.add(yearList);
					viewBookedItems2.add(lblBooking);
					viewBookedItems2.add(booking_numbers);
					viewBookedItems2.add(space1);
					viewBookedItems2.add(space2);
					viewBookedItems2.add(lblItemName);
					viewBookedItems2.add(lblItemName2);
					viewBookedItems2.add(lblBookingNo);
					viewBookedItems2.add(lblBookingNo2);
					viewBookedItems2.add(lblTotalCost);
					viewBookedItems2.add(lblTotalCost2);
					viewBookedItems2.add(lblStartDate);
					viewBookedItems2.add(lblStartDate2);
					viewBookedItems2.add(lblEndDate);
					viewBookedItems2.add(lblEndDate2);
					
					viewBookedItems3.removeAll();
					if (today.isBefore(month_booking_list[Integer.parseInt(bookingNums) - 1].getStart_date())) {
						viewBookedItems3.setLayout(new GridLayout(0,2,20,10));
						viewBookedItems3.setBorder(BorderFactory.createEmptyBorder(0,50,20,50));
						viewBookedItems3.add(btnDelete);
						viewBookedItems3.add(btnReturnMM);
					}
					else {
						viewBookedItems3.setLayout(new GridLayout(0,1,20,10));
						viewBookedItems3.setBorder(BorderFactory.createEmptyBorder(0,140,20,140));
						viewBookedItems3.add(btnReturnMM);
					}				
					
					frame.getContentPane().remove(viewBookedItems2);
					frame.getContentPane().add(viewBookedItems2, BorderLayout.CENTER);
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
		else if(!bookingNums.equals(selectedBooking)) {
			
			bookingNums = selectedBooking;
			
			lblItemName2 = new JLabel(month_booking_list[Integer.parseInt(bookingNums) - 1].getItem_name());
			lblBookingNo2 = new JLabel(Integer.toString(month_booking_list[Integer.parseInt(bookingNums) - 1].getBooking_num()));
			lblStartDate2 = new JLabel(month_booking_list[Integer.parseInt(bookingNums) - 1].getStart_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			lblEndDate2 = new JLabel(month_booking_list[Integer.parseInt(bookingNums) - 1].getEnd_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
			lblTotalCost2 = new JLabel(String.format("%.2f", month_booking_list[Integer.parseInt(bookingNums) - 1].getTotal_cost()));
			
			lblItemName2.setFont(font);
			lblBookingNo2.setFont(font);
			lblStartDate2.setFont(font);
			lblEndDate2.setFont(font);
			lblTotalCost2.setFont(font);
			
			viewBookedItems2.removeAll();
			viewBookedItems2.add(lblMonth);
			viewBookedItems2.add(monthList);
			viewBookedItems2.add(lblYear);
			viewBookedItems2.add(yearList);
			viewBookedItems2.add(lblBooking);
			viewBookedItems2.add(booking_numbers);
			viewBookedItems2.add(space1);
			viewBookedItems2.add(space2);
			viewBookedItems2.add(lblItemName);
			viewBookedItems2.add(lblItemName2);
			viewBookedItems2.add(lblBookingNo);
			viewBookedItems2.add(lblBookingNo2);
			viewBookedItems2.add(lblTotalCost);
			viewBookedItems2.add(lblTotalCost2);
			viewBookedItems2.add(lblStartDate);
			viewBookedItems2.add(lblStartDate2);
			viewBookedItems2.add(lblEndDate);
			viewBookedItems2.add(lblEndDate2);
			
			viewBookedItems3.removeAll();
			if (today.isBefore(month_booking_list[Integer.parseInt(bookingNums) - 1].getStart_date())) {
				viewBookedItems3.setLayout(new GridLayout(0,2,20,10));
				viewBookedItems3.setBorder(BorderFactory.createEmptyBorder(0,50,20,50));
				viewBookedItems3.add(btnDelete);
				viewBookedItems3.add(btnReturnMM);
			}
			else {
				viewBookedItems3.setLayout(new GridLayout(0,1,20,10));
				viewBookedItems3.setBorder(BorderFactory.createEmptyBorder(0,140,20,140));
				viewBookedItems3.add(btnReturnMM);
			}			
			
			frame.getContentPane().remove(viewBookedItems2);
			frame.getContentPane().add(viewBookedItems2, BorderLayout.CENTER);
			frame.repaint();
			frame.revalidate();
			
		}

		// When the main menu button is pressed the optionMenuGUI is created.
		if(e.getSource() == btnReturnMM)
		{
			optionMenuGUI new_panel = new optionMenuGUI();
			new_panel.optionMenu(cust, frame);
		}
		// When the delete button is pressed the confirmation buttons are created.
		else if(e.getSource() == btnDelete)
		{
			deleteBooking();
		}
		// When the yes button is pressed the booking is deleted.
		else if(e.getSource() == btnYes)
		{
			// Check for connection with database or warning message displayed.
			if (month_booking_list[Integer.parseInt(bookingNums) - 1].deleteBooking()) {
				new viewBookedItemsGUI(cust, frame);
			}
			else {
				viewBookedItems2.setBorder(BorderFactory.createEmptyBorder(20,50,15,50));
				gui.inputWarning("Connection Issue - Please try again later.");
			}
		}
		// When the no button is pressed page returns to the original place.
		else if(e.getSource() == btnNo)
		{
			gui.removeInputWarning();
			cancelDeleteBooking();
		}
	}
	
}