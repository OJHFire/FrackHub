package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Contains GUI to view member information once added to the database.
 */

public class userInfoGUI implements ActionListener{
	
	JFrame frame;
	Customer cust;
	
	JPanel userInfo;
	JPanel userInfo2;
	
	JButton btnReturnMM;
	JButton btnSignOut;
	
	JLabel lblFirstName = new JLabel("First Name");
	JLabel lblLastName = new JLabel("Last Name");
	JLabel lblAddress = new JLabel("Full Address");
	JLabel lblEmail = new JLabel("Email");
	JLabel lblPhoneNum = new JLabel("Phone Number");
	//JLabel lblPassword = new JLabel("Password");
	
	// Function to create GUI page.
	public void userInfo(Customer new_cust, JFrame new_frame) {
		
		frame = new_frame;
		cust = new_cust;
		
		// Layout settings for the page.
		userInfo = new JPanel();
		userInfo2 = new JPanel();
		userInfo.setLayout(new GridLayout(0,2,0,20));
		userInfo2.setLayout(new GridLayout(0,2,20,10));
		userInfo.setBorder(BorderFactory.createEmptyBorder(50,20,0,20));
		userInfo2.setBorder(BorderFactory.createEmptyBorder(0,50,20,50));
		userInfo.setBackground(Color.white);
		userInfo2.setBackground(Color.white);
				
		// Control buttons to sign out or go back to main menu.
		btnReturnMM = new JButton("Main Menu");
		btnSignOut = new JButton("Sign Out");
		btnReturnMM.addActionListener(this);
		btnSignOut.addActionListener(this);
		
		// Labels for details of customer.
		JLabel lblFirstName2 = new JLabel(cust.getName().getFirstName());
		JLabel lblLastName2 = new JLabel(cust.getName().getSurname());
		JLabel lblAddress2 = new JLabel(cust.getAddress());
		JLabel lblEmail2 = new JLabel(cust.getEmail());
		JLabel lblPhoneNum2 = new JLabel(cust.getPhone_num());
		//JLabel lblPassword2 = new JLabel(cust.getPassword());

		userInfo.add(lblFirstName);
		userInfo.add(lblFirstName2);
		userInfo.add(lblLastName);
		userInfo.add(lblLastName2);
		userInfo.add(lblAddress);
		userInfo.add(lblAddress2);
		userInfo.add(lblEmail);
		userInfo.add(lblEmail2);
		userInfo.add(lblPhoneNum);
		userInfo.add(lblPhoneNum2);
		//userInfo.add(lblPassword);
		//userInfo.add(lblPassword2);
		userInfo2.add(btnReturnMM);
		userInfo2.add(btnSignOut);
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(userInfo, BorderLayout.NORTH);
		frame.getContentPane().add(userInfo2, BorderLayout.SOUTH);
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
