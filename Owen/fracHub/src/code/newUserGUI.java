package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class newUserGUI implements ActionListener{
	
	JFrame frame;
	
	JPanel warningPanel;
	JPanel newUser;
	JPanel newUser2;
	
	JButton btnConNewUser;
	JButton btnReturnMM;

	JTextField txtFirstName = new JTextField();
	JTextField txtLastName = new JTextField();		
	JTextField txtAddress = new JTextField();
	JTextField txtEmail = new JTextField();
	JTextField txtPhoneNum = new JTextField();
	JTextField txtPassword = new JTextField();
	
	JLabel lblFirstName = new JLabel("First Name");
	JLabel lblLastName = new JLabel("Last Name");
	JLabel lblAddress = new JLabel("Full Address");
	JLabel lblEmail = new JLabel("Email");
	JLabel lblPhoneNum = new JLabel("Phone Number");
	JLabel lblPassword = new JLabel("Password");
	
	public void newUser(JFrame new_frame) {
		
		frame = new_frame;
		newUser = new JPanel();
		newUser2 = new JPanel();
		newUser.setBorder(BorderFactory.createEmptyBorder(50,20,0,20));
		newUser2.setBorder(BorderFactory.createEmptyBorder(0,50,20,50));
		newUser.setLayout(new GridLayout(0,2,0,20));	
		newUser2.setLayout(new GridLayout(0,2,20,10));	
		newUser.setBackground(Color.white);
		newUser2.setBackground(Color.white);
		btnConNewUser = new JButton("Confirm");
		btnConNewUser.addActionListener(this);
		btnReturnMM = new JButton("Main Menu");
		btnReturnMM.addActionListener(this);
	
		newUser.add(lblFirstName);
		newUser.add(txtFirstName);
		newUser.add(lblLastName);
		newUser.add(txtLastName);
		newUser.add(lblAddress);
		newUser.add(txtAddress);
		newUser.add(lblEmail);
		newUser.add(txtEmail);
		newUser.add(lblPhoneNum);
		newUser.add(txtPhoneNum);
		newUser.add(lblPassword);
		newUser.add(txtPassword);
		
		newUser2.add(btnConNewUser);
		newUser2.add(btnReturnMM);
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(newUser, BorderLayout.NORTH);
		frame.getContentPane().add(newUser2, BorderLayout.SOUTH);
		frame.repaint();
		frame.revalidate();		
	}
	
	public void conNewUser() {
			
			if ((!txtFirstName.getText().isEmpty()) && (!txtLastName.getText().isEmpty()) && (!txtAddress.getText().isEmpty()) &&
					(!txtEmail.getText().isEmpty()) && (!txtPhoneNum.getText().isEmpty()) && (!txtPassword.getText().isEmpty())) {
				if (checkNumeric(txtPhoneNum.getText())) {
					if (isValid(txtEmail.getText())) {
						Name userName = new Name(txtFirstName.getText() + " " + txtLastName.getText());
						Customer cust = new Customer(userName, txtPassword.getText(), 
						txtAddress.getText(), txtEmail.getText(), txtPhoneNum.getText());
						if (cust.emailIsUnique() == 0) {
							cust.saveCust();
							optionMenuGUI new_panel = new optionMenuGUI();
							new_panel.optionMenu(cust, frame);
						}
						else {
							newUser(frame);
							inputWarning("Email is already in use.");
						}
					}
					else {
						newUser(frame);
						inputWarning("Please enter a valid email address.");
					}
				}
				else {
					newUser(frame);
					inputWarning("Please enter a valid phone number.");
				}
			}
			else {
				newUser(frame);
				inputWarning("Please complete all of the fields.");
			}
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
	
	// Adapted from code on https://www.tutorialspoint.com/validate-email-address-in-java
	public boolean isValid(String email) {
	      String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
	      return email.matches(regex);
	   }
	
	public boolean checkNumeric(String s) {
		try 
		{
			Integer.parseInt(s);
			return true;
			
		}
		catch (NumberFormatException error) 
		{
			return false;
		}
	}	
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == btnConNewUser)
		{
			conNewUser();
		}
		else if(e.getSource() == btnReturnMM)
		{
			mainMenuGUI new_panel = new mainMenuGUI();
			new_panel.mainMenu(frame);
		}
	}

}
