package code;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Contains GUI to for the options menu once signed in.
 */

public class optionMenuGUI implements ActionListener{
	
	JFrame frame;
	Customer cust;
	
	JPanel optionMenu;
	JPanel optionMenu2;
	
	JButton btnCustInfo;
	JButton btnMyItems;
	JButton btnAddItem;
	JButton btnViewMyItems;
	JButton btnViewMyItemBookings;
	JButton btnMyBookings;
	JButton btnBookItem;
	JButton btnViewBookedItems;
	JButton btnViewMonthlyReport;
	JButton btnSignOut;
	
	boolean itemOptions = false;
	boolean bookingOptions = false;
	
	NimbusButton nimbusButton = new NimbusButton();

	// Function to create GUI page.
	public void optionMenu(Customer new_cust, JFrame new_frame) {
	
		frame = new_frame;
		cust = new_cust;
		
		// Layout settings for the page.
		
		//NORTH PANAL - Page header
		optionMenu2 = new JPanel();
		optionMenu2.setBackground(Color.white);
		optionMenu2.setLayout(new GridBagLayout());	
		GridBagConstraints c = new GridBagConstraints();
		
		//Import Logo
		try {
			BufferedImage logo = ImageIO.read(this.getClass().getResource("Logo.png"));

			Image resizedImage = logo.getScaledInstance(100, 100, Image.SCALE_DEFAULT);
		    BufferedImage outputImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_RGB);
		    outputImage.getGraphics().drawImage(resizedImage, 0, 0, null);
			
			JLabel picLabel = new JLabel(new ImageIcon(resizedImage));
			JLabel welcome = new JLabel("Welcome " + cust.getName().getFirstName());
			welcome.setFont(new Font("MV Boli", Font.BOLD, 20));
			JLabel welcome3 = new JLabel("                     ");
			c.gridx = 0;
			c.weightx = 1.0; 
			optionMenu2.add(picLabel, c);
			c.gridx = 1;
			optionMenu2.add(welcome, c);
			c.gridx = 3;
			optionMenu2.add(welcome3, c);
		}
		catch (IOException ex) {
			System.out.println(ex);
		}
		
		
		//SOUTH PANEL
		optionMenu = new JPanel();
		optionMenu.setBorder(BorderFactory.createEmptyBorder(10,120,50,120));
		optionMenu.setBackground(Color.white);
		optionMenu.setLayout(new GridLayout(8,0,0,10));
		
		// Buttons for each menu option.
		btnCustInfo = nimbusButton.generateNimbusButton("Customer Information");
		btnCustInfo.putClientProperty("JComponent.sizeVariant", "large");
		btnMyItems = nimbusButton.generateNimbusButton("My Items");
		btnMyItems.putClientProperty("JComponent.sizeVariant", "large");
		btnMyBookings = nimbusButton.generateNimbusButton("My Bookings");
		btnMyBookings.putClientProperty("JComponent.sizeVariant", "large");
		btnViewMonthlyReport = nimbusButton.generateNimbusButton("View Monthly Report");
		btnViewMonthlyReport.putClientProperty("JComponent.sizeVariant", "large");
		btnSignOut = nimbusButton.generateNimbusButton("Sign Out");
		btnSignOut.putClientProperty("JComponent.sizeVariant", "large");
		btnCustInfo.addActionListener(this);
		btnMyItems.addActionListener(this);
		btnMyBookings.addActionListener(this);
		btnViewMonthlyReport.addActionListener(this);
		btnSignOut.addActionListener(this);
		
		btnAddItem = nimbusButton.generateNimbusButton("Add Item");
		btnAddItem.putClientProperty("JComponent.sizeVariant", "large");
		btnViewMyItems = nimbusButton.generateNimbusButton("View My Items");
		btnViewMyItems.putClientProperty("JComponent.sizeVariant", "large");
		btnViewMyItemBookings = nimbusButton.generateNimbusButton("View My Item Bookings");
		btnViewMyItemBookings.putClientProperty("JComponent.sizeVariant", "large");
		btnBookItem = nimbusButton.generateNimbusButton("Book an Item");
		btnBookItem.putClientProperty("JComponent.sizeVariant", "large");
		btnViewBookedItems = nimbusButton.generateNimbusButton("View Booked Items");
		btnViewBookedItems.putClientProperty("JComponent.sizeVariant", "large");
		btnAddItem.setBackground(new Color(255,153,204));
		btnViewMyItems.setBackground(new Color(255,153,204));
		btnViewMyItemBookings.setBackground(new Color(255,153,204));
		btnBookItem.setBackground(new Color(255,153,204));
		btnViewBookedItems.setBackground(new Color(255,153,204));

		btnAddItem.addActionListener(this);
		btnViewMyItems.addActionListener(this);
		btnViewMyItemBookings.addActionListener(this);
		btnBookItem.addActionListener(this);
		btnViewBookedItems.addActionListener(this);
		
		optionMenu.add(btnCustInfo, new GridLayout(1,0));
		optionMenu.add(btnMyItems, new GridLayout(2,0));
		optionMenu.add(btnMyBookings, new GridLayout(3,0));
		optionMenu.add(btnViewMonthlyReport, new GridLayout(4,0));
		optionMenu.add(btnSignOut, new GridLayout(5,0));
				
		frame.getContentPane().removeAll();
		frame.getContentPane().add(BorderLayout.CENTER, optionMenu);
		frame.getContentPane().add(BorderLayout.NORTH, optionMenu2);
		frame.repaint();
		frame.revalidate();
	}
	
	// Function for any events.
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == btnCustInfo)
		{
			new userInfoGUI(cust, frame);
		}
		else if(e.getSource() == btnMyItems)
		{
			if (!itemOptions) {
				
				try {
					optionMenu.remove(btnBookItem);
					optionMenu.remove(btnViewBookedItems);
				}
				catch(Exception e2) {}
				optionMenu.remove(btnMyBookings);
				optionMenu.remove(btnViewMonthlyReport);
				optionMenu.remove(btnSignOut);
				optionMenu.add(btnAddItem, new GridLayout(3,0));
				optionMenu.add(btnViewMyItems, new GridLayout(4,0));
				optionMenu.add(btnViewMyItemBookings, new GridLayout(5,0));
				optionMenu.add(btnMyBookings, new GridLayout(6,0));
				optionMenu.add(btnViewMonthlyReport, new GridLayout(7,0));
				optionMenu.add(btnSignOut, new GridLayout(8,0));

				frame.repaint();
				frame.revalidate();
				
				itemOptions = true;
				bookingOptions = false;
			}
			else {
				optionMenu.remove(btnAddItem);
				optionMenu.remove(btnViewMyItems);
				optionMenu.remove(btnViewMyItemBookings);
				optionMenu.remove(btnMyBookings);
				optionMenu.remove(btnViewMonthlyReport);
				optionMenu.remove(btnSignOut);
				optionMenu.add(btnMyBookings, new GridLayout(3,0));
				optionMenu.add(btnViewMonthlyReport, new GridLayout(4,0));
				optionMenu.add(btnSignOut, new GridLayout(5,0));

				frame.repaint();
				frame.revalidate();
				
				itemOptions = false;
			}
			
		}
		else if(e.getSource() == btnAddItem)
		{
			new newItemGUI(cust, frame);
		}
		else if(e.getSource() == btnViewMyItems)
		{
			viewMyItemsGUI new_panel = new viewMyItemsGUI();
			new_panel.viewItem(cust, frame);
		}
		else if(e.getSource() == btnViewMyItemBookings)
		{
			new viewMyItemBookingsGUI(cust, frame);
		}
		else if(e.getSource() == btnMyBookings)
		{
			if (!bookingOptions) {
				try {
					optionMenu.remove(btnAddItem);
					optionMenu.remove(btnViewMyItems);
					optionMenu.remove(btnViewMyItemBookings);
				}
				catch(Exception e3) {}
				optionMenu.remove(btnMyBookings);
				optionMenu.remove(btnViewMonthlyReport);
				optionMenu.remove(btnSignOut);
				optionMenu.add(btnMyBookings, new GridLayout(3,0));
				optionMenu.add(btnBookItem, new GridLayout(4,0));
				optionMenu.add(btnViewBookedItems, new GridLayout(5,0));
				optionMenu.add(btnViewMonthlyReport, new GridLayout(6,0));
				optionMenu.add(btnSignOut, new GridLayout(7,0));

				frame.repaint();
				frame.revalidate();
				
				itemOptions = false;
				bookingOptions = true;
			}
			else {
				optionMenu.remove(btnBookItem);
				optionMenu.remove(btnViewBookedItems);
				optionMenu.remove(btnViewMonthlyReport);
				optionMenu.remove(btnSignOut);
				optionMenu.add(btnViewMonthlyReport, new GridLayout(4,0));
				optionMenu.add(btnSignOut, new GridLayout(5,0));

				frame.repaint();
				frame.revalidate();
				
				bookingOptions = false;
			}
		}
		else if(e.getSource() == btnBookItem)
		{
			new addBookingGUI(cust, frame);
		}
		else if(e.getSource() == btnViewBookedItems)
		{
			new viewBookedItemsGUI(cust, frame);
		}
		else if(e.getSource() == btnViewMonthlyReport)
		{
			new monthlyAccountGUI(cust, frame);
		}
		else if(e.getSource() == btnSignOut)
		{
			mainMenuGUI new_panel = new mainMenuGUI();
			new_panel.mainMenu(frame);
		}
	}

}

