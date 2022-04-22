package adminAppCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Contains GUI to for the options menu once signed in.
 */

public class optionMenuGUI implements ActionListener{
	
	JFrame frame;
	Administrator admin;
	GUI gui;
	
	JPanel optionMenu;
	JPanel optionMenu2;
	
	JButton btnAnalytics;
	JButton btnBookings;
	JButton btnCustData;
	JButton btnBookingData;
	JButton btnSignOut;
	JButton btnBookingDelivery;
	JButton btnBookingReturn;
	
	boolean analyticOptions = false;
	boolean bookingOptions = false;
	
	NimbusButton nimbusButton = new NimbusButton();

	// Function to create GUI page.
	public void optionMenu(Administrator new_admin, JFrame new_frame) {
	
		frame = new_frame;
		admin = new_admin;
		
		// Remove all previous content on frame.
		frame.getContentPane().removeAll();
		
		// Layout settings for the page.
		
		//NORTH PANAL - Page header
		gui = new GUI();
		gui.pageHeader(frame, "Welcome " + admin.getName().getFirstName());
		
		
		//SOUTH PANEL
		optionMenu = new JPanel();
		optionMenu.setBorder(BorderFactory.createEmptyBorder(50,300,50,300));
		optionMenu.setBackground(Color.white);
		optionMenu.setLayout(new GridLayout(5,0,0,10));
		
		// Buttons for each menu option.
		btnAnalytics = nimbusButton.generateNimbusButton("Analytics");
		btnAnalytics.putClientProperty("JComponent.sizeVariant", "large");
		btnBookings = nimbusButton.generateNimbusButton("Bookings");
		btnBookings.putClientProperty("JComponent.sizeVariant", "large");
		btnSignOut = nimbusButton.generateNimbusButton("Sign Out");
		btnSignOut.putClientProperty("JComponent.sizeVariant", "large");
		btnAnalytics.addActionListener(this);
		btnBookings.addActionListener(this);
		btnSignOut.addActionListener(this);
		
		btnCustData = nimbusButton.generateNimbusButton("Customer Data");
		btnCustData.putClientProperty("JComponent.sizeVariant", "large");
		btnBookingData = nimbusButton.generateNimbusButton("Booking Data");
		btnBookingData.putClientProperty("JComponent.sizeVariant", "large");
		btnBookingDelivery = nimbusButton.generateNimbusButton("Booking Deliveries");
		btnBookingDelivery.putClientProperty("JComponent.sizeVariant", "large");
		btnBookingReturn = nimbusButton.generateNimbusButton("Booking Returns");
		btnBookingReturn.putClientProperty("JComponent.sizeVariant", "large");
		btnCustData.setBackground(new Color(255,153,204));
		btnBookingData.setBackground(new Color(255,153,204));
		btnBookingDelivery.setBackground(new Color(255,153,204));
		btnBookingReturn.setBackground(new Color(255,153,204));

		btnCustData.addActionListener(this);
		btnBookingData.addActionListener(this);
		btnBookingDelivery.addActionListener(this);
		btnBookingReturn.addActionListener(this);
		
		optionMenu.add(btnAnalytics, new GridLayout(1,0));
		optionMenu.add(btnBookings, new GridLayout(2,0));
		optionMenu.add(btnSignOut, new GridLayout(3,0));
				
		frame.getContentPane().add(BorderLayout.CENTER, optionMenu);
		frame.repaint();
		frame.revalidate();
	}
	
	// Function for any events.
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == btnAnalytics)
		{
			if (!analyticOptions) {
				
				try {
					optionMenu.remove(btnBookingDelivery);
					optionMenu.remove(btnBookingReturn);
				}
				catch (Exception e2) {}
				
				optionMenu.remove(btnBookings);
				optionMenu.remove(btnSignOut);
				optionMenu.add(btnCustData, new GridLayout(2,0));
				optionMenu.add(btnBookingData, new GridLayout(3,0));
				optionMenu.add(btnBookings, new GridLayout(4,0));
				optionMenu.add(btnSignOut, new GridLayout(5,0));

				frame.repaint();
				frame.revalidate();
				
				analyticOptions = true;
			}
			else {
				optionMenu.remove(btnCustData);
				optionMenu.remove(btnBookingData);
				optionMenu.remove(btnBookings);
				optionMenu.remove(btnSignOut);
				optionMenu.add(btnBookings, new GridLayout(2,0));
				optionMenu.add(btnSignOut, new GridLayout(3,0));

				frame.repaint();
				frame.revalidate();
				
				analyticOptions = false;
			}
			
		}
		else if(e.getSource() == btnCustData)
		{
			new customerDataGUI(admin, frame);
		}
		else if(e.getSource() == btnBookingData)
		{
			new bookingDataGUI(admin, frame);
		}
		else if(e.getSource() == btnBookings)
		{
			if (!bookingOptions) {
				
				try {
					optionMenu.remove(btnCustData);
					optionMenu.remove(btnBookingData);
				}
				catch (Exception e3) {}
				
				optionMenu.remove(btnBookings);
				optionMenu.remove(btnSignOut);
				optionMenu.add(btnBookings, new GridLayout(2,0));
				optionMenu.add(btnBookingDelivery, new GridLayout(3,0));
				optionMenu.add(btnBookingReturn, new GridLayout(4,0));
				optionMenu.add(btnSignOut, new GridLayout(5,0));

				frame.repaint();
				frame.revalidate();
				
				bookingOptions = true;
			}
			else {
				optionMenu.remove(btnBookingDelivery);
				optionMenu.remove(btnBookingReturn);
				optionMenu.remove(btnBookings);
				optionMenu.remove(btnSignOut);
				optionMenu.add(btnBookings, new GridLayout(2,0));
				optionMenu.add(btnSignOut, new GridLayout(3,0));

				frame.repaint();
				frame.revalidate();
				
				bookingOptions = false;
			}
		}
		else if(e.getSource() == btnBookingDelivery)
		{
			new bookingDeliveryInfoGUI(admin, frame);
		}
		else if(e.getSource() == btnBookingReturn)
		{
			new bookingReturnInfoGUI(admin, frame);
		}
		else if(e.getSource() == btnSignOut)
		{
			mainMenuGUI new_panel = new mainMenuGUI();
			new_panel.mainMenu(frame);
		}
	}

}

