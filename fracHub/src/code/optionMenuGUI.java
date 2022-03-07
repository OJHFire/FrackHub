package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class optionMenuGUI implements ActionListener{
	
	JFrame frame;
	Customer cust;
	
	JPanel optionMenu;
	
	JButton btnCustInfo;
	JButton btnAddItem;
	JButton btnBookItem;
	JButton btnSignOut;
	JButton btnViewBookedItems;
	
	public JLabel blankLabel() {
		JLabel blank = new JLabel("");
		
		return blank;
	}
	
	public void optionMenu(Customer new_cust, JFrame new_frame) {
	
		frame = new_frame;
		cust = new_cust;
		optionMenu = new JPanel();
		
		optionMenu.setBorder(BorderFactory.createEmptyBorder(50,120,20,120));

		optionMenu.setBackground(Color.white);
		optionMenu.setLayout(new GridLayout(5,0,0,10));		
		btnCustInfo = new JButton("Customer Information");
		btnAddItem = new JButton("Add Item");
		btnBookItem = new JButton("Book an Item");
		btnViewBookedItems = new JButton("View Booked Items");
		btnSignOut = new JButton("Sign Out");
		btnCustInfo.addActionListener(this);
		btnAddItem.addActionListener(this);
		btnBookItem.addActionListener(this);
		btnViewBookedItems.addActionListener(this);
		btnSignOut.addActionListener(this);
		
		optionMenu.add(btnCustInfo, new GridLayout(1,0));
		optionMenu.add(btnAddItem, new GridLayout(2,0));
		optionMenu.add(btnBookItem, new GridLayout(3,0));
		optionMenu.add(btnViewBookedItems, new GridLayout(4,0));
		optionMenu.add(btnSignOut, new GridLayout(5,0));
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(BorderLayout.NORTH, optionMenu);
		frame.repaint();
		frame.revalidate();
	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == btnCustInfo)
		{
			userInfoGUI new_panel = new userInfoGUI();
			new_panel.userInfo(cust, frame);
		}
		/*else if(e.getSource() == btnAddItem)
		{
			signInGUI new_panel = new signInGUI();
			new_panel.signIn(frame);
		}
		else if(e.getSource() == btnBookItem)
		{
			signInGUI new_panel = new signInGUI();
			new_panel.signIn(frame);
		}
		else if(e.getSource() == btnViewBookedItems)
		{
			signInGUI new_panel = new signInGUI();
			new_panel.signIn(frame);
		}*/
		else if(e.getSource() == btnSignOut)
		{
			mainMenuGUI new_panel = new mainMenuGUI();
			new_panel.mainMenu(frame);
		}
	}

}

