package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Contains GUI to for the options menu once signed in.
 */

public class optionMenuGUI implements ActionListener{
	
	JFrame frame;
	Customer cust;
	
	JPanel optionMenu;
	
	JButton btnCustInfo;
	JButton btnAddItem;
	JButton btnBookItem;
	JButton btnSignOut;
	JButton btnViewMyItems;
	JButton btnViewBookedItems;
	JButton btnViewMonthlyReport;

	// Function to create GUI page.
	public void optionMenu(Customer new_cust, JFrame new_frame) {
	
		frame = new_frame;
		cust = new_cust;
		
		// Layout settings for the page.
		optionMenu = new JPanel();
		optionMenu.setBorder(BorderFactory.createEmptyBorder(50,120,20,120));
		optionMenu.setBackground(Color.white);
		optionMenu.setLayout(new GridLayout(7,0,0,10));	
		
		// Buttons for each menu option.
		btnCustInfo = new JButton("Customer Information");
		btnAddItem = new JButton("Add Item");
		btnBookItem = new JButton("Book an Item");
		btnViewMyItems = new JButton("View My Items");
		btnViewBookedItems = new JButton("View Booked Items");
		btnViewMonthlyReport = new JButton("View Monthly Report");
		btnSignOut = new JButton("Sign Out");
		btnCustInfo.addActionListener(this);
		btnAddItem.addActionListener(this);
		btnBookItem.addActionListener(this);
		btnViewMyItems.addActionListener(this);
		btnViewBookedItems.addActionListener(this);
		btnViewMonthlyReport.addActionListener(this);
		btnSignOut.addActionListener(this);
		
		optionMenu.add(btnCustInfo, new GridLayout(1,0));
		optionMenu.add(btnAddItem, new GridLayout(2,0));
		optionMenu.add(btnBookItem, new GridLayout(3,0));
		optionMenu.add(btnViewMyItems, new GridLayout(4,0));
		optionMenu.add(btnViewBookedItems, new GridLayout(5,0));
		optionMenu.add(btnViewMonthlyReport, new GridLayout(6,0));
		optionMenu.add(btnSignOut, new GridLayout(7,0));
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(BorderLayout.NORTH, optionMenu);
		frame.repaint();
		frame.revalidate();
	}
	
	// Function for any events.
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == btnCustInfo)
		{
			userInfoGUI new_panel = new userInfoGUI();
			new_panel.userInfo(cust, frame);
		}
		else if(e.getSource() == btnAddItem)
		{
			newItemGUI new_panel = new newItemGUI();
			new_panel.newItem(cust, frame);
		}
		else if(e.getSource() == btnBookItem)
		{
			addBookingGUI new_panel = new addBookingGUI();
			new_panel.addBooking(cust, frame);
		}
		else if(e.getSource() == btnViewMyItems)
		{
			viewMyItemsGUI new_panel = new viewMyItemsGUI();
			new_panel.viewItem(cust, frame);
		}
		else if(e.getSource() == btnViewBookedItems)
		{
			viewBookedItemsGUI new_panel = new viewBookedItemsGUI();
			new_panel.viewBookedItems(cust, frame);
		}
		else if(e.getSource() == btnViewMonthlyReport)
		{
			monthlyAccountGUI new_panel = new monthlyAccountGUI();
			new_panel.monthlyAccount(cust, frame);
		}
		else if(e.getSource() == btnSignOut)
		{
			mainMenuGUI new_panel = new mainMenuGUI();
			new_panel.mainMenu(frame);
		}
	}

}

