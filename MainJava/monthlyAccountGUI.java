package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;


public class monthlyAccountGUI implements ActionListener{
	
	JFrame frame;
	Customer cust;
		
	JPanel warningPanel;
	JPanel monthlyAccount;
	JPanel monthlyAccount2;
	JPanel monthlyAccount3;
	
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

	
	public void monthlyAccount(Customer new_cust, JFrame new_frame) {
		
		frame = new_frame;
		cust = new_cust;
		
		monthlyAccount = new JPanel();
		monthlyAccount2 = new JPanel();
		monthlyAccount3 = new JPanel();
		monthlyAccount.setLayout(new GridLayout(0,2,0,20));
		monthlyAccount2.setLayout(new GridLayout(0,2,20,10));
		monthlyAccount3.setLayout(new GridLayout(0,1,20,10));
		monthlyAccount.setBorder(BorderFactory.createEmptyBorder(50,20,0,20));
		monthlyAccount2.setBorder(BorderFactory.createEmptyBorder(20,50,100,50));
		monthlyAccount3.setBorder(BorderFactory.createEmptyBorder(0,140,20,140));
		monthlyAccount.setBackground(Color.white);
		monthlyAccount2.setBackground(Color.white);
		monthlyAccount3.setBackground(Color.white);

		btnReturnMM = new JButton("Main Menu");
		btnReturnMM.addActionListener(this);
			
		LocalDate today = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM");
		month = today.format(formatter);
		year = today.getYear();
		
		yearList = new JComboBox<String>(year_list);
		monthList = new JComboBox<String>(month_list);		
		yearList.setSelectedItem(String.valueOf(year));
		monthList.setSelectedItem(month);
		yearList.addActionListener(this);
		monthList.addActionListener(this);
		
		Booking booking = new Booking();
		yearInfo = booking.monthlyAccountBooking(year, cust);
		monthInfo = yearInfo[today.getMonthValue() - 1];
		
		lblNoOfLoans2 = new JLabel(String.valueOf((int)monthInfo[0]), JLabel.CENTER);
		lblNoOfBorrows2 = new JLabel(String.valueOf((int)monthInfo[1]), JLabel.CENTER);
		lblDebit2 = new JLabel(String.format("%.2f", monthInfo[2]), JLabel.CENTER);
		lblCredit2 = new JLabel(String.format("%.2f", monthInfo[3]), JLabel.CENTER);
		
		double totalCost = monthInfo[3] - monthInfo[2];
		lblTotalCost2 = new JLabel(String.format("%.2f", totalCost), JLabel.CENTER);
		
		monthlyAccount.add(lblMonth);
		monthlyAccount.add(monthList);
		monthlyAccount.add(lblYear);
		monthlyAccount.add(yearList);
		
		monthlyAccount2.add(lblNoOfLoans);
		monthlyAccount2.add(lblNoOfLoans2);
		monthlyAccount2.add(lblNoOfBorrows);
		monthlyAccount2.add(lblNoOfBorrows2);
		monthlyAccount2.add(lblCredit);
		monthlyAccount2.add(lblCredit2);
		monthlyAccount2.add(lblDebit);
		monthlyAccount2.add(lblDebit2);
		monthlyAccount2.add(lblTotalCost);
		monthlyAccount2.add(lblTotalCost2);
		
		monthlyAccount3.add(btnReturnMM);
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(monthlyAccount, BorderLayout.NORTH);
		frame.getContentPane().add(monthlyAccount2, BorderLayout.CENTER);
		frame.getContentPane().add(monthlyAccount3, BorderLayout.SOUTH);
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
	
	public void inputWarning(String message) {
		
		warningPanel = new JPanel();
		JLabel lblWarning = new JLabel(message);
		warningPanel.setBackground(Color.white);
		warningPanel.add(lblWarning);
		frame.getContentPane().add(warningPanel, BorderLayout.CENTER);
		frame.repaint();
		frame.revalidate();
	}
		
	public void actionPerformed(ActionEvent e) {
		
		String selectedMonth = monthList.getSelectedItem().toString();
		String selectedYear = yearList.getSelectedItem().toString();
		
		if (month != selectedMonth) {
			
			month = selectedMonth;
			monthInfo = yearInfo[map.get(month)];
			
			lblNoOfLoans2 = new JLabel(String.valueOf((int)monthInfo[0]), JLabel.CENTER);
			lblNoOfBorrows2 = new JLabel(String.valueOf((int)monthInfo[1]), JLabel.CENTER);
			lblDebit2 = new JLabel(String.format("%.2f", monthInfo[2]), JLabel.CENTER);
			lblCredit2 = new JLabel(String.format("%.2f", monthInfo[3]), JLabel.CENTER);
			
			double totalCost = monthInfo[3] - monthInfo[2];
			lblTotalCost2 = new JLabel(String.format("%.2f", totalCost), JLabel.CENTER);
			
			monthlyAccount2.removeAll();
			monthlyAccount2.add(lblNoOfLoans);
			monthlyAccount2.add(lblNoOfLoans2);
			monthlyAccount2.add(lblNoOfBorrows);
			monthlyAccount2.add(lblNoOfBorrows2);
			monthlyAccount2.add(lblCredit);
			monthlyAccount2.add(lblCredit2);
			monthlyAccount2.add(lblDebit);
			monthlyAccount2.add(lblDebit2);
			monthlyAccount2.add(lblTotalCost);
			monthlyAccount2.add(lblTotalCost2);

			frame.repaint();
			frame.revalidate();		
			
		}
		
		if (year != Integer.parseInt(selectedYear)) {
			
			year = Integer.parseInt(selectedYear);
			
			yearList.setSelectedItem(year);
			monthList.setSelectedItem(month);
			
			Booking booking = new Booking();
			yearInfo = booking.monthlyAccountBooking(year, cust);
			monthInfo = yearInfo[map.get(month)];
			
			lblNoOfLoans2 = new JLabel(String.valueOf((int)monthInfo[0]), JLabel.CENTER);
			lblNoOfBorrows2 = new JLabel(String.valueOf((int)monthInfo[1]), JLabel.CENTER);
			lblDebit2 = new JLabel(String.format("%.2f", monthInfo[2]), JLabel.CENTER);
			lblCredit2 = new JLabel(String.format("%.2f", monthInfo[3]), JLabel.CENTER);
			
			double totalCost = monthInfo[3] - monthInfo[2];
			lblTotalCost2 = new JLabel(String.format("%.2f", totalCost), JLabel.CENTER);
			
			monthlyAccount2.removeAll();
			monthlyAccount2.add(lblNoOfLoans);
			monthlyAccount2.add(lblNoOfLoans2);
			monthlyAccount2.add(lblNoOfBorrows);
			monthlyAccount2.add(lblNoOfBorrows2);
			monthlyAccount2.add(lblCredit);
			monthlyAccount2.add(lblCredit2);
			monthlyAccount2.add(lblDebit);
			monthlyAccount2.add(lblDebit2);
			monthlyAccount2.add(lblTotalCost);
			monthlyAccount2.add(lblTotalCost2);

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
