package adminAppCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Contains GUI to view information about the bookings on the database.
 */

public class bookingDataGUI implements ActionListener{
	
	JFrame frame;
	Administrator admin;
	GUI gui;
	
	JPanel bookingData1;
	JPanel bookingData2;
	
	JButton btnReturnMM;
	JButton btnSignOut;
	
	JLabel lblBookingNo = new JLabel("Number of Bookings");
	JLabel lblBookingNo2;
	JLabel lblTotalFunds = new JLabel("Total Funds (£)");
	JLabel lblTotalFunds2;
	JLabel lblBookingDays = new JLabel("Number of booking days");
	JLabel lblBookingDays2;
	
	NimbusButton nimbusButton = new NimbusButton();
	Font font = new Font("Calibri", Font.BOLD, 30);

	
	// Function to create GUI page.
	public bookingDataGUI(Administrator new_admin, JFrame new_frame) {
		
		frame = new_frame;
		admin = new_admin;
		
		// Remove all previous content on frame.
		frame.getContentPane().removeAll();
		
		// Layout settings for the page.
		
		// NORTH PANEL - Create header with given title.
		gui = new GUI();
		gui.pageHeader(frame, "Booking Data");
		
		
		// CENTRE PANEL
		bookingData1 = new JPanel();
		bookingData1.setLayout(new GridLayout(0,2,20,20));
		bookingData1.setBorder(BorderFactory.createEmptyBorder(20,100,150,50));
		bookingData1.setBackground(Color.white);
		
		// Labels for details of item.
		Booking booking = new Booking();
		String[] bookingInfo = booking.bookingsInfo();
		
		// Display warning if there is a connection issue.
		if (bookingInfo[0].equals("Error")) {
			
			JLabel lblWarning = new JLabel("Connection Issue - Please try again later.");
			lblWarning.setHorizontalAlignment(JLabel.CENTER);
			bookingData1.setLayout(new GridLayout(0,1,20,10));
			bookingData1.add(lblWarning);

			frame.getContentPane().add(bookingData1, BorderLayout.CENTER);
			
		}		
		else {
			
			lblBookingNo2 = new JLabel(bookingInfo[0]);
			lblTotalFunds2 = new JLabel(bookingInfo[1]);
			lblBookingDays2 = new JLabel(bookingInfo[2]);
			lblBookingNo.setFont(font);
			lblBookingNo2.setFont(font);
			lblTotalFunds.setFont(font);
			lblTotalFunds2.setFont(font);
			lblBookingDays.setFont(font);
			lblBookingDays2.setFont(font);
			lblBookingNo2.setHorizontalAlignment(JLabel.CENTER);
			lblTotalFunds2.setHorizontalAlignment(JLabel.CENTER);
			lblBookingDays2.setHorizontalAlignment(JLabel.CENTER);
			bookingData1.add(lblBookingNo);
			bookingData1.add(lblBookingNo2);
			bookingData1.add(lblTotalFunds);
			bookingData1.add(lblTotalFunds2);
			bookingData1.add(lblBookingDays);
			bookingData1.add(lblBookingDays2);
		}

				
		
		// SOUTH PANEL
		bookingData2 = new JPanel();
		bookingData2.setLayout(new GridLayout(0,2,20,10));
		bookingData2.setBorder(BorderFactory.createEmptyBorder(0,50,20,50));
		bookingData2.setBackground(Color.white);
				
		// Control buttons to sign out or go back to main menu.
		btnReturnMM = nimbusButton.generateNimbusButton("Main Menu");
		btnReturnMM.setPreferredSize(new Dimension(100,40));
		btnReturnMM.putClientProperty("JComponent.sizeVariant", "large");
		btnSignOut = nimbusButton.generateNimbusButton("Sign Out");
		btnSignOut.putClientProperty("JComponent.sizeVariant", "large");
		btnReturnMM.addActionListener(this);
		btnSignOut.addActionListener(this);
		
		bookingData2.add(btnSignOut);
		bookingData2.add(btnReturnMM);
		

		// Add panels to the frame.
		frame.getContentPane().add(bookingData1, BorderLayout.CENTER);
		frame.getContentPane().add(bookingData2, BorderLayout.SOUTH);
		frame.repaint();
		frame.revalidate();		
	}
	
	// Function for any events.
	public void actionPerformed(ActionEvent e) {

		// When the main menu button is pressed the optionMenuGUI is created.
		if(e.getSource() == btnReturnMM)
		{
			optionMenuGUI new_panel = new optionMenuGUI();
			new_panel.optionMenu(admin, frame);
		}
		// When the sign out button is pressed the mainMenuGUI is created.
		else if(e.getSource() == btnSignOut)
		{
			mainMenuGUI new_panel = new mainMenuGUI();
			new_panel.mainMenu(frame);
		}
	}

}
