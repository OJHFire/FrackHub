package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Contains GUI to view the account statement for each month.
 */

public class monthlyAccountGUI implements ActionListener{
	
	JFrame frame;
	Customer cust;
	GUI gui;
		
	JPanel monthlyAccount1;
	JPanel monthlyAccount2;
	
	JButton btnReturnMM;
	
	JLabel lblMonth = new JLabel("Month");
	JLabel lblYear = new JLabel("Year");
	JLabel lblNoOfLoans = new JLabel("Items loaned to others");
	JLabel lblNoOfLoans2;
	JLabel lblNoOfBorrows = new JLabel("Items loaned to you");
	JLabel lblNoOfBorrows2;
	JLabel lblDebit = new JLabel("Debit (£)");
	JLabel lblDebit2;
	JLabel lblCredit = new JLabel("Credit (£)");
	JLabel lblCredit2;
	JLabel lblTotalCost = new JLabel("Total Monthly Fee (£)");
	JLabel lblTotalCost2;
	JLabel space1 = new JLabel("    ");
	JLabel space2 = new JLabel("    ");
	
	JComboBox<String> yearList;
	JComboBox<String> monthList;
	String[] year_list = {"2022", "2023", "2024"};
	String[] month_list = {"January", "February", "March", "April", "May", "June",
							"July", "August", "September", "October", "November", "December"};
	
	String month;
	int year;
	double[][] yearInfo;
	double[] monthInfo;
	Map<String, Integer> map;
	boolean noConnection = false;
	Booking booking;
	
	NimbusButton nimbusButton = new NimbusButton();
	ComboBox new_combo = new ComboBox();
	Font font = new Font("Calibri", Font.BOLD, 15);

	// Function to create GUI page.
	public monthlyAccountGUI(Customer new_cust, JFrame new_frame) {
		
		frame = new_frame;
		cust = new_cust;
		
		// Remove all previous content on frame.
		frame.getContentPane().removeAll();
		
		// Layout settings for the page.
		
		// NORTH PANEL - Create header with given title.
		gui = new GUI();
		gui.pageHeader(frame, "Monthly Statement");
		
		
		// CENTRE PANEL - Create labels for monthly statements and combobox to select month and year.
		monthlyAccount1 = new JPanel();
		monthlyAccount1.setLayout(new GridLayout(0,2,20,10));
		monthlyAccount1.setBorder(BorderFactory.createEmptyBorder(30,50,30,50));
		monthlyAccount1.setBackground(Color.white);
		
		// Selection lists for month and year.
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM");
		month = today.format(formatter);
		year = today.getYear();
		
		yearList = new JComboBox<String>(year_list);
		new_combo.setComboBox(yearList);
		yearList.setBackground(Color.WHITE);
		((JLabel)yearList.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((JLabel)yearList.getRenderer()).setVerticalAlignment(JLabel.TOP);
		monthList = new JComboBox<String>(month_list);
		new_combo.setComboBox(monthList);
		monthList.setBackground(Color.WHITE);
		((JLabel)monthList.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		((JLabel)monthList.getRenderer()).setVerticalAlignment(JLabel.TOP);
		yearList.setSelectedItem(String.valueOf(year));
		monthList.setSelectedItem(month);
		yearList.addActionListener(this);
		monthList.addActionListener(this);
		
		// Account statement for selected month.
		booking = new Booking();
		yearInfo = booking.monthlyAccountBooking(year, cust);
		
		// The list will have a length of 12 unless there has been no connection with the database.
		if (yearInfo.length == 12) {
			monthInfo = yearInfo[today.getMonthValue() - 1];
					
			lblNoOfLoans2 = new JLabel(String.valueOf((int)monthInfo[0]), JLabel.CENTER);
			lblNoOfBorrows2 = new JLabel(String.valueOf((int)monthInfo[1]), JLabel.CENTER);
			lblDebit2 = new JLabel(String.format("%.2f", monthInfo[2]), JLabel.CENTER);
			lblCredit2 = new JLabel(String.format("%.2f", monthInfo[3]), JLabel.CENTER);
				
			double totalCost = monthInfo[3] - monthInfo[2];
			lblTotalCost2 = new JLabel(String.format("%.2f", totalCost), JLabel.CENTER);
			
			yearList.setFont(font);
			lblYear.setFont(font);
			monthList.setFont(font);
			lblMonth.setFont(font);
			lblNoOfLoans.setFont(font);
			lblNoOfBorrows.setFont(font);
			lblDebit.setFont(font);
			lblCredit.setFont(font);
			lblTotalCost.setFont(font);
			lblNoOfLoans2.setFont(font);
			lblNoOfBorrows2.setFont(font);
			lblDebit2.setFont(font);
			lblCredit2.setFont(font);
			lblTotalCost2.setFont(font);
			
			monthlyAccount1.add(lblMonth);
			monthlyAccount1.add(monthList);
			monthlyAccount1.add(lblYear);
			monthlyAccount1.add(yearList);
			monthlyAccount1.add(space1);
			monthlyAccount1.add(space2);
			monthlyAccount1.add(lblNoOfLoans);
			monthlyAccount1.add(lblNoOfLoans2);
			monthlyAccount1.add(lblNoOfBorrows);
			monthlyAccount1.add(lblNoOfBorrows2);
			monthlyAccount1.add(lblCredit);
			monthlyAccount1.add(lblCredit2);
			monthlyAccount1.add(lblDebit);
			monthlyAccount1.add(lblDebit2);
			monthlyAccount1.add(lblTotalCost);
			monthlyAccount1.add(lblTotalCost2);
		}
		// Display warning if the information could not be retrieved from the database.
		else {
			
			JLabel lblWarning = new JLabel("Connection Issue - Please try again later.");
			lblWarning.setHorizontalAlignment(JLabel.CENTER);
			monthlyAccount1.setLayout(new GridLayout(0,1,20,10));
			monthlyAccount1.add(lblWarning);
			
		}
		
		
		// SOUTH PANEL - Add navigation and confirmation buttons.
		monthlyAccount2 = new JPanel();
		monthlyAccount2.setLayout(new GridLayout(0,1,20,10));
		monthlyAccount2.setBorder(BorderFactory.createEmptyBorder(0,140,20,140));
		monthlyAccount2.setBackground(Color.white);

		// Control button to go back to main menu.
		btnReturnMM = nimbusButton.generateNimbusButton("Main Menu");
		btnReturnMM.putClientProperty("JComponent.sizeVariant", "large");
		btnReturnMM.addActionListener(this);
			
		monthlyAccount2.add(btnReturnMM);
		
		frame.getContentPane().add(monthlyAccount1, BorderLayout.CENTER);
		frame.getContentPane().add(monthlyAccount2, BorderLayout.SOUTH);
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
	
		
	// Function for any events.
	public void actionPerformed(ActionEvent e) {
		
		String selectedMonth = monthList.getSelectedItem().toString();
		String selectedYear = yearList.getSelectedItem().toString();
		
		monthlyAccount1.setBorder(BorderFactory.createEmptyBorder(30,50,30,50));
		gui.removeInputWarning();
			
		// When the month type in the JComboBox is changed the account statement is updated to the chosen month.
		if (month != selectedMonth) {
			
			month = selectedMonth;
			if (noConnection) {
				yearInfo = booking.monthlyAccountBooking(year, cust);
			}
			
			// Check for connection with database.
			if (yearInfo.length == 12) {
				
				noConnection = false;
				monthInfo = yearInfo[map.get(month)];
				
				lblNoOfLoans2 = new JLabel(String.valueOf((int)monthInfo[0]), JLabel.CENTER);
				lblNoOfBorrows2 = new JLabel(String.valueOf((int)monthInfo[1]), JLabel.CENTER);
				lblDebit2 = new JLabel(String.format("%.2f", monthInfo[2]), JLabel.CENTER);
				lblCredit2 = new JLabel(String.format("%.2f", monthInfo[3]), JLabel.CENTER);
				
				double totalCost = monthInfo[3] - monthInfo[2];
				lblTotalCost2 = new JLabel(String.format("%.2f", totalCost), JLabel.CENTER);
				
				lblNoOfLoans2.setFont(font);
				lblNoOfBorrows2.setFont(font);
				lblDebit2.setFont(font);
				lblCredit2.setFont(font);
				lblTotalCost2.setFont(font);
				
				monthlyAccount1.removeAll();
				monthlyAccount1.add(lblMonth);
				monthlyAccount1.add(monthList);
				monthlyAccount1.add(lblYear);
				monthlyAccount1.add(yearList);
				monthlyAccount1.add(space1);
				monthlyAccount1.add(space2);
				monthlyAccount1.add(lblNoOfLoans);
				monthlyAccount1.add(lblNoOfLoans2);
				monthlyAccount1.add(lblNoOfBorrows);
				monthlyAccount1.add(lblNoOfBorrows2);
				monthlyAccount1.add(lblCredit);
				monthlyAccount1.add(lblCredit2);
				monthlyAccount1.add(lblDebit);
				monthlyAccount1.add(lblDebit2);
				monthlyAccount1.add(lblTotalCost);
				monthlyAccount1.add(lblTotalCost2);
		
				frame.repaint();
				frame.revalidate();	
			}			
			else {
				monthlyAccount1.setBorder(BorderFactory.createEmptyBorder(10,50,30,50));
				if (noConnection) {
					monthlyAccount1.setBorder(BorderFactory.createEmptyBorder(10,50,210,50));
				}
				gui.inputWarning("Connection Issue - Please try again later.");
			}
		}
		
		// When the year type in the JComboBox is changed the new account details are loaded from the database.
		if (year != Integer.parseInt(selectedYear)) {
			
			year = Integer.parseInt(selectedYear);
			
			yearList.setSelectedItem(year);
			monthList.setSelectedItem(month);
			
			yearInfo = booking.monthlyAccountBooking(year, cust);
			
			// Check for connection with database.
			if (yearInfo.length == 12) {
				
				noConnection = false;
				monthInfo = yearInfo[map.get(month)];
				
				lblNoOfLoans2 = new JLabel(String.valueOf((int)monthInfo[0]), JLabel.CENTER);
				lblNoOfBorrows2 = new JLabel(String.valueOf((int)monthInfo[1]), JLabel.CENTER);
				lblDebit2 = new JLabel(String.format("%.2f", monthInfo[2]), JLabel.CENTER);
				lblCredit2 = new JLabel(String.format("%.2f", monthInfo[3]), JLabel.CENTER);
				
				double totalCost = monthInfo[3] - monthInfo[2];
				lblTotalCost2 = new JLabel(String.format("%.2f", totalCost), JLabel.CENTER);
				
				lblNoOfLoans2.setFont(font);
				lblNoOfBorrows2.setFont(font);
				lblDebit2.setFont(font);
				lblCredit2.setFont(font);
				lblTotalCost2.setFont(font);
				
				monthlyAccount1.removeAll();
				monthlyAccount1.add(lblMonth);
				monthlyAccount1.add(monthList);
				monthlyAccount1.add(lblYear);
				monthlyAccount1.add(yearList);
				monthlyAccount1.add(space1);
				monthlyAccount1.add(space2);
				monthlyAccount1.add(lblNoOfLoans);
				monthlyAccount1.add(lblNoOfLoans2);
				monthlyAccount1.add(lblNoOfBorrows);
				monthlyAccount1.add(lblNoOfBorrows2);
				monthlyAccount1.add(lblCredit);
				monthlyAccount1.add(lblCredit2);
				monthlyAccount1.add(lblDebit);
				monthlyAccount1.add(lblDebit2);
				monthlyAccount1.add(lblTotalCost);
				monthlyAccount1.add(lblTotalCost2);
	
				frame.repaint();
				frame.revalidate();		
			}
			// Display warning if the information could not be retrieved from the database.
			else {
				
				monthlyAccount1.setBorder(BorderFactory.createEmptyBorder(10,50,210,50));
				gui.inputWarning("Connection Issue - Please try again later.");
				noConnection = true;
				
				monthlyAccount1.remove(lblNoOfLoans);
				monthlyAccount1.remove(lblNoOfLoans2);
				monthlyAccount1.remove(lblNoOfBorrows);
				monthlyAccount1.remove(lblNoOfBorrows2);
				monthlyAccount1.remove(lblCredit);
				monthlyAccount1.remove(lblCredit2);
				monthlyAccount1.remove(lblDebit);
				monthlyAccount1.remove(lblDebit2);
				monthlyAccount1.remove(lblTotalCost);
				monthlyAccount1.remove(lblTotalCost2);
	
				frame.repaint();
				frame.revalidate();	
			}
		}
		
		// When the main menu button is pressed the optionMenuGUI is created.
		if(e.getSource() == btnReturnMM)
		{
			optionMenuGUI new_panel = new optionMenuGUI();
			new_panel.optionMenu(cust, frame);
		}

	}

}
