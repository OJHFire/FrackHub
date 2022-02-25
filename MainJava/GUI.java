package code;

import javax.swing.*;
//import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GUI implements ActionListener{
	
	private JFrame frame;
	private JPanel mainMenu, signIn, signIn2, newUser, newUser2, userInfo, userInfo2, warningPanel;
	private JButton btnNewUser, btnSignIn, btnConNewUser, btnConSignIn, btnReturnMM;
	private JLabel lblFirstName, lblLastName, lblAddress, lblPhoneNum, lblEmail, lblPassword;
	private JTextField txtFirstName, txtLastName, txtAddress, txtPhoneNum, txtEmail, txtPassword;
	

	public GUI() {
		
		frame = new JFrame("FracHub");
		frame.setBounds(50,50, 450, 500);
		frame.getContentPane().setBackground(Color.white);
		
		mainMenu();
		frame.setVisible(true);

	}
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == btnNewUser)
		{
			newUser();
		}
		else if(e.getSource() == btnConNewUser)
		{
			conNewUser();
		}
		else if(e.getSource() == btnSignIn)
		{
			signIn();
		}
		else if(e.getSource() == btnConSignIn)
		{
			conSignIn();
		}
		else if(e.getSource() == btnReturnMM)
		{
			mainMenu();
		}
	}
	public void mainMenu() {
		
		createVariables();		
		
		mainMenu = new JPanel();
		mainMenu.setBackground(Color.white);
		mainMenu.setLayout(new GridLayout(0,2,10,20));		
		btnNewUser = new JButton("Sign Up");
		btnSignIn = new JButton("Sign In");
		btnNewUser.addActionListener(this);
		btnSignIn.addActionListener(this);
		mainMenu.add(btnNewUser);
		mainMenu.add(btnSignIn);
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(mainMenu, BorderLayout.NORTH);
		frame.repaint();
		frame.revalidate();		
	}
	
	public void newUser() {
		newUser = new JPanel();
		newUser2 = new JPanel();
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
	
	public void userInfo(Customer cust) {
		userInfo = new JPanel();
		userInfo2 = new JPanel();
		userInfo.setLayout(new GridLayout(0,2,0,20));
		userInfo2.setLayout(new GridLayout(0,2,20,10));
		userInfo.setBackground(Color.white);
		userInfo2.setBackground(Color.white);
				
		btnReturnMM = new JButton("Main Menu");
		btnReturnMM.addActionListener(this);
		
		JLabel lblFirstName2 = new JLabel(cust.getName().getFirstName());
		JLabel lblLastName2 = new JLabel(cust.getName().getSurname());
		JLabel lblAddress2 = new JLabel(cust.getAddress());
		JLabel lblEmail2 = new JLabel(cust.getEmail());
		JLabel lblPhoneNum2 = new JLabel(cust.getPhone_num());
		JLabel lblPassword2 = new JLabel(cust.getPassword());


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
		userInfo.add(lblPassword);
		userInfo.add(lblPassword2);
		userInfo2.add(btnReturnMM);
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(userInfo, BorderLayout.NORTH);
		frame.getContentPane().add(userInfo2, BorderLayout.SOUTH);
		frame.repaint();
		frame.revalidate();		
	}
	
	public void signIn() {
		signIn = new JPanel();
		signIn2 = new JPanel();
		signIn.setLayout(new GridLayout(0,2,0,20));	
		signIn2.setLayout(new GridLayout(0,2,20,10));
		signIn.setBackground(Color.white);
		signIn2.setBackground(Color.white);
		btnConSignIn = new JButton("Continue");
		btnConSignIn.addActionListener(this);
		btnReturnMM = new JButton("Main Menu");
		btnReturnMM.addActionListener(this);
		
		signIn.add(lblEmail);
		signIn.add(txtEmail);
		signIn.add(lblPassword);
		signIn.add(txtPassword);
		signIn2.add(btnConSignIn);
		signIn2.add(btnReturnMM);
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(signIn, BorderLayout.NORTH);
		frame.getContentPane().add(signIn2, BorderLayout.SOUTH);
		frame.repaint();
		frame.revalidate();		
	}
	
	public void conNewUser() {
		
		if ((!txtFirstName.getText().isEmpty()) && (!txtLastName.getText().isEmpty()) && (!txtAddress.getText().isEmpty()) &&
				(!txtEmail.getText().isEmpty()) && (!txtPhoneNum.getText().isEmpty()) && (!txtPassword.getText().isEmpty())) {
			if (checkNumeric(txtPhoneNum.getText())) {
				if (isValid(txtEmail.getText())) {
					Name userName = new Name(txtFirstName.getText() + " " + txtLastName.getText());
					Customer user = new Customer(userName, txtPassword.getText(), 
					txtAddress.getText(), txtEmail.getText(), txtPhoneNum.getText());
					user.printCust();
					user.saveCust();
					userInfo(user);
				}
				else {
					newUser();
					inputWarning("Please enter a valid email address.");
				}
			}
			else {
				newUser();
				inputWarning("Please enter a valid phone number.");
			}
		}
		else {
			newUser();
			inputWarning("Please complete all of the fields.");
		}
	}
	
	public void conSignIn() {
		if (!txtEmail.getText().isEmpty()) {
			if (!txtPassword.getText().isEmpty()) {
				Customer cust = new Customer();
				cust = cust.custSignIn(txtEmail.getText(), txtPassword.getText());
				if (!cust.getName().getFullName().equals(" ")) {
					userInfo(cust);
				}
				else {
					signIn();
					inputWarning("Email or password is not valid.");
				}
			}
			else {
				signIn();
				inputWarning("Please enter a password.");
			}
		}
		else {
			signIn();
			inputWarning("Please enter an email address.");
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
	
	public void createVariables() {
		txtEmail = new JTextField();
		txtPassword = new JTextField();
		txtFirstName = new JTextField();
		txtLastName = new JTextField();		
		txtAddress = new JTextField();
		txtEmail = new JTextField();
		txtPhoneNum = new JTextField();
		txtPassword = new JTextField();
		
		lblPassword = new JLabel("Password");
		lblEmail = new JLabel("Email");
		lblFirstName = new JLabel("First Name");
		lblLastName = new JLabel("Last Name");
		lblAddress = new JLabel("Full Address");
		lblEmail = new JLabel("Email");
		lblPhoneNum = new JLabel("Phone Number");
		lblPassword = new JLabel("Password");
	}

}