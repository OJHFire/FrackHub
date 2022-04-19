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
	GUI gui;
	
	JPanel userInfo1;
	JPanel userInfo2;
	
	
	JButton btnReturnMM;
	JButton btnEditCust;
	
	JLabel lblFirstName = new JLabel("First Name");
	JLabel lblLastName = new JLabel("Last Name");
	JLabel lblAddress = new JLabel("Full Address");
	JLabel lblEmail = new JLabel("Email");
	JLabel lblPhoneNum = new JLabel("Phone Number");
	//JLabel lblPassword = new JLabel("Password");
	
	JLabel lblFirstName2;
	JLabel lblLastName2;
	JLabel lblAddress2;
	JLabel lblEmail2;
	JLabel lblPhoneNum2;
	//JLabel lblPassword2;
	
	NimbusButton nimbusButton = new NimbusButton();
	Font font = new Font("Calibri", Font.BOLD, 15);
	
	// Function to create GUI page.
	public userInfoGUI(Customer new_cust, JFrame new_frame) {
		
		frame = new_frame;
		cust = new_cust;
		
		// Remove all previous content on frame.
		frame.getContentPane().removeAll();
		
		
		// Layout settings for the page.
		
		// NORTH PANEL - Create header with given title.
		gui = new GUI();
		gui.pageHeader(frame, "Customer Information");
		
		
		// CENTRE PANEL - Create customer detail labels.
		userInfo1 = new JPanel();
		userInfo1.setLayout(new GridLayout(0,2,20,20));
		userInfo1.setBorder(BorderFactory.createEmptyBorder(20,50,100,50));
		userInfo1.setBackground(Color.white);
		
		// Labels for details of customer.
		lblFirstName2 = new JLabel(cust.getName().getFirstName());
		lblLastName2 = new JLabel(cust.getName().getSurname());
		lblAddress2 = new JLabel(cust.getAddress());
		lblEmail2 = new JLabel(cust.getEmail());
		lblPhoneNum2 = new JLabel(cust.getPhone_num());		
		//lblPassword2 = new JLabel(cust.getPassword());
		
		// Set font for labels.
		lblFirstName.setFont(font);
		lblLastName.setFont(font);
		lblAddress.setFont(font);
		lblEmail.setFont(font);
		lblPhoneNum.setFont(font);
		//lblPassword.setFont(font);
		lblFirstName2.setFont(font);
		lblLastName2.setFont(font);
		lblAddress2.setFont(font);
		lblEmail2.setFont(font);
		lblPhoneNum2.setFont(font);
		//lblPassword2.setFont(font);
		
		userInfo1.add(lblFirstName);
		userInfo1.add(lblFirstName2);
		userInfo1.add(lblLastName);
		userInfo1.add(lblLastName2);
		userInfo1.add(lblAddress);
		userInfo1.add(lblAddress2);
		userInfo1.add(lblEmail);
		userInfo1.add(lblEmail2);
		userInfo1.add(lblPhoneNum);
		userInfo1.add(lblPhoneNum2);
		//userInfo1.add(lblPassword);
		//userInfo1.add(lblPassword2);
		
		
		// SOUTH PANEL - Add navigation and confirmation buttons.
		userInfo2 = new JPanel();
		userInfo2.setLayout(new GridLayout(0,2,20,10));		
		userInfo2.setBorder(BorderFactory.createEmptyBorder(0,50,20,50));
		userInfo2.setBackground(Color.white);

		// Control buttons to sign out or go back to main menu.
		btnReturnMM = nimbusButton.generateNimbusButton("Main Menu");
		btnReturnMM.putClientProperty("JComponent.sizeVariant", "large");
		btnEditCust = nimbusButton.generateNimbusButton("Edit Details");
		btnEditCust.putClientProperty("JComponent.sizeVariant", "large");
		btnReturnMM.addActionListener(this);
		btnEditCust.addActionListener(this);
		
		userInfo2.add(btnEditCust);
		userInfo2.add(btnReturnMM);
		

		frame.getContentPane().add(userInfo1, BorderLayout.CENTER);
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
		// When the edit button is pressed the editUserGUI is created.
		else if(e.getSource() == btnEditCust)
		{
			new editUserGUI(cust, frame);
		}
	}

}
