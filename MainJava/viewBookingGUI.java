package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.format.DateTimeFormatter;

/**
 * Contains GUI to view booking once added to the database.
 */

public class viewBookingGUI implements ActionListener{
	
	JFrame frame;
	Customer cust;
	GUI gui;
		
	JPanel viewBooking1;
	JPanel viewBooking2;
	
	JButton btnReturnMM;
	JButton btnSignOut;
	
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
	
	NimbusButton nimbusButton = new NimbusButton();
	Font font = new Font("Calibri", Font.BOLD, 15);
	
	// Function to create GUI page.
	public viewBookingGUI(Item item, Booking booking, Customer new_cust, JFrame new_frame) {
		
		frame = new_frame;
		cust = new_cust;
		
		// Remove all previous content on frame.
		frame.getContentPane().removeAll();
		
		// Layout settings for the page.
		
		// NORTH PANEL - Create header with given title.
		gui = new GUI();
		gui.pageHeader(frame, "New Booking Details");
		
		
		// CENTRE PANEL - Create booking detail labels.
		viewBooking1 = new JPanel();
		viewBooking1.setLayout(new GridLayout(0,2,0,20));
		viewBooking1.setBorder(BorderFactory.createEmptyBorder(20,50,80,30));
		viewBooking1.setBackground(Color.white);
		
		// Labels for details of booking.
		lblType2 = new JLabel(item.getType());
		lblName2 = new JLabel(item.getName());
		lblItemDescription = new JLabel(item.getDescription());
		lblStartDate2 = new JLabel(booking.getStart_date().format(DateTimeFormatter.ofPattern("dd-MMM-uuuu")));
		lblEndDate2 = new JLabel(booking.getEnd_date().format(DateTimeFormatter.ofPattern("dd-MMM-uuuu")));
		lblItemDailyCost = new JLabel(String.format("%.2f", item.getDaily_rate()));
		lblTotalCost2 = new JLabel(String.format("%.2f", booking.getTotal_cost()));
		
		lblType.setFont(font);
		lblName.setFont(font);
		lblDescription.setFont(font);
		lblStartDate.setFont(font);
		lblEndDate.setFont(font);
		lblDailyCost.setFont(font);
		lblTotalCost.setFont(font);
		lblType2.setFont(font);
		lblName2.setFont(font);
		lblItemDescription.setFont(font);
		lblStartDate2.setFont(font);
		lblEndDate2.setFont(font);
		lblItemDailyCost.setFont(font);
		lblTotalCost2.setFont(font);
		
		viewBooking1.add(lblType);
		viewBooking1.add(lblType2);
		viewBooking1.add(lblName);
		viewBooking1.add(lblName2);
		//viewBooking1.add(lblDescription);
		//viewBooking1.add(lblItemDescription);
		viewBooking1.add(lblDailyCost);
		viewBooking1.add(lblItemDailyCost);
		viewBooking1.add(lblTotalCost);
		viewBooking1.add(lblTotalCost2);
		viewBooking1.add(lblStartDate);
		viewBooking1.add(lblStartDate2);
		viewBooking1.add(lblEndDate);
		viewBooking1.add(lblEndDate2);
		
		
		
		// SOUTH PANEL - Add navigation and confirmation buttons.
		viewBooking2 = new JPanel();	
		viewBooking2.setLayout(new GridLayout(0,2,20,10));
		viewBooking2.setBorder(BorderFactory.createEmptyBorder(0,50,20,50));
		viewBooking2.setBackground(Color.white);
				
		// Control buttons to sign out or go back to main menu.
		btnReturnMM = nimbusButton.generateNimbusButton("Main Menu");
		btnSignOut = nimbusButton.generateNimbusButton("Sign Out");
		btnReturnMM.putClientProperty("JComponent.sizeVariant", "large");
		btnSignOut.putClientProperty("JComponent.sizeVariant", "large");
		btnReturnMM.addActionListener(this);
		btnSignOut.addActionListener(this);
		
		viewBooking2.add(btnSignOut);
		viewBooking2.add(btnReturnMM);
		
		frame.getContentPane().add(viewBooking1, BorderLayout.CENTER);
		frame.getContentPane().add(viewBooking2, BorderLayout.SOUTH);
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
