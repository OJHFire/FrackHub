package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Contains GUI to edit member details in the database.
 */

public class editUserGUI implements ActionListener{
	
	JFrame frame;
	Customer cust;
	GUI gui;
	
	JPanel newUser1;
	JPanel newUser2;
	
	JButton btnConEditUser;
	JButton btnReturnMM;

	JTextField txtFirstName = new JTextField();
	JTextField txtLastName = new JTextField();		
	JTextField txtAddress = new JTextField();
	JTextField txtEmail = new JTextField();
	JTextField txtPhoneNum = new JTextField();
	
	JLabel lblFirstName = new JLabel("First Name");
	JLabel lblLastName = new JLabel("Last Name");
	JLabel lblAddress = new JLabel("Full Address");
	JLabel lblEmail = new JLabel("Email");
	JLabel lblPhoneNum = new JLabel("Phone Number");
	
	NimbusButton nimbusButton = new NimbusButton();
	Font font = new Font("Calibri", Font.BOLD, 15);
	
	// Function to create GUI page.
	public editUserGUI(Customer new_cust, JFrame new_frame) {
		
		frame = new_frame;
		cust = new_cust;
		
		// Remove all previous content on frame.
		frame.getContentPane().removeAll();
		
		// Layout settings for the page.
		
		// NORTH PANEL - Create header with given title.
		gui = new GUI();
		gui.pageHeader(frame, "Edit Customer Details");
		
		
		// CENTRE PANEL - Create customer detail labels and textfields.
		newUser1 = new JPanel();
		newUser1.setBorder(BorderFactory.createEmptyBorder(30,20,105,20));
		newUser1.setLayout(new GridLayout(0,2,0,20));
		newUser1.setBackground(Color.white);
		
		txtFirstName.setText(cust.getName().getFirstName());
		txtLastName.setText(cust.getName().getSurname());
		txtAddress.setText(cust.getAddress());
		txtEmail.setText(cust.getEmail());
		txtPhoneNum.setText(cust.getPhone_num());
	
		lblFirstName.setFont(font);
		lblLastName.setFont(font);
		lblAddress.setFont(font);
		lblEmail.setFont(font);
		lblPhoneNum.setFont(font);
	
		newUser1.add(lblFirstName);
		newUser1.add(txtFirstName);
		newUser1.add(lblLastName);
		newUser1.add(txtLastName);
		newUser1.add(lblAddress);
		newUser1.add(txtAddress);
		newUser1.add(lblEmail);
		newUser1.add(txtEmail);
		newUser1.add(lblPhoneNum);
		newUser1.add(txtPhoneNum);
		
		
		// SOUTH PANEL - Add navigation and confirmation buttons.
		newUser2 = new JPanel();
		newUser2.setBorder(BorderFactory.createEmptyBorder(0,50,20,50));
		newUser2.setLayout(new GridLayout(0,2,20,10));	
		newUser2.setBackground(Color.white);
				
		// Control buttons to confirm edit of customer details or go back to main menu.
		btnConEditUser = nimbusButton.generateNimbusButton("Confirm Update");
		btnConEditUser.putClientProperty("JComponent.sizeVariant", "large");
		btnConEditUser.addActionListener(this);
		btnReturnMM = nimbusButton.generateNimbusButton("Main Menu");
		btnReturnMM.putClientProperty("JComponent.sizeVariant", "large");
		btnReturnMM.addActionListener(this);
				
		newUser2.add(btnConEditUser);
		newUser2.add(btnReturnMM);
		
		frame.getContentPane().add(newUser1, BorderLayout.CENTER);
		frame.getContentPane().add(newUser2, BorderLayout.SOUTH);
		frame.repaint();
		frame.revalidate();		
	}
	
	// Function to edit member on the database if all the input details are as expected.
	public void conEditUser() {
			
			if ((!txtFirstName.getText().isEmpty()) && (!txtLastName.getText().isEmpty()) && (!txtAddress.getText().isEmpty()) &&
					(!txtEmail.getText().isEmpty()) && (!txtPhoneNum.getText().isEmpty())) {
				if (checkNumeric(txtPhoneNum.getText())) {
					String phoneNum = txtPhoneNum.getText().replace(" ","");
					if (isValid(txtEmail.getText())) {
						Name userName = new Name(txtFirstName.getText() + " " + txtLastName.getText());
						Customer edit_cust = new Customer(cust.getCust_num(), userName, 
						txtAddress.getText(), txtEmail.getText(), phoneNum);
						int canCustBeEdited = edit_cust.editCust();
						if (canCustBeEdited == 1) {
							optionMenuGUI new_panel = new optionMenuGUI();
							new_panel.optionMenu(edit_cust, frame);
						}
						else {
							if (canCustBeEdited == 3) {
								newUser1.setBorder(BorderFactory.createEmptyBorder(10,20,105,20));
								gui.inputWarning("Connection Issue - Please try again later.");
							}
							else {
								newUser1.setBorder(BorderFactory.createEmptyBorder(10,20,105,20));
								gui.inputWarning("Email is already in use.");
							}
						}
					}
					else {
						newUser1.setBorder(BorderFactory.createEmptyBorder(10,20,105,20));
						gui.inputWarning("Please enter a valid email address.");
					}
				}
				else {
					newUser1.setBorder(BorderFactory.createEmptyBorder(10,20,105,20));
					gui.inputWarning("Please enter a valid phone number.");
				}
			}
			else {
				newUser1.setBorder(BorderFactory.createEmptyBorder(10,20,105,20));
				gui.inputWarning("Please complete all of the fields.");
			}
		}
	
	// Adapted from code on https://www.tutorialspoint.com/validate-email-address-in-java
	// Function to check the String is in the correct form for an email.
	public boolean isValid(String email) {
	      String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	      return email.matches(regex);
	   }
	
	// Function to check a String is made up of numbers.
	public boolean checkNumeric(String s) {
		try 
		{
			s = s.replace(" ","");
			Long.parseLong(s);
			return true;
			
		}
		catch (NumberFormatException error) 
		{
			return false;
		}
	}	
	
	// Function for any events.
	public void actionPerformed(ActionEvent e) {

		// When the confirm button is pressed the customer details are checked and if ok updated on the database.
		if(e.getSource() == btnConEditUser)
		{
			conEditUser();
		}
		// When the main menu button is pressed the optionMenuGUI is created.
		else if(e.getSource() == btnReturnMM)
		{
			optionMenuGUI new_panel = new optionMenuGUI();
			new_panel.optionMenu(cust, frame);
		}
	}

}
