package FracHub;

import javax.swing.*;
//import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GUI implements ActionListener{
	
	private JFrame frame;
	private JPanel mainMenu, signIn, newUser, userInfo;
	private JButton btnNewUser, btnSignIn, btnConNewUser, btnConSignIn;
	private JLabel lblName, lblAddress, lblPhoneNum, lblEmail, lblPassword, lblCustNum;
	private JTextField txtName, txtAddress, txtPhoneNum, txtEmail, txtPassword, txtCustNum;

	public GUI() {
		frame = new JFrame("FracHub");
		frame.setBounds(50,50, 450, 500);
		
		mainMenu();
		frame.setVisible(true);
		
		lblName = new JLabel("Name");

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

	}
	public void mainMenu() {
		mainMenu = new JPanel();
		mainMenu.setLayout(new GridLayout(0,1));		
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
		newUser.setLayout(new GridLayout(0,2));		
		btnConNewUser = new JButton("Confirm");
		btnConNewUser.addActionListener(this);
		
		lblName = new JLabel("Name");
		lblAddress = new JLabel("Full Address");
		lblEmail = new JLabel("Email");
		lblPhoneNum = new JLabel("Phone Number");
		lblPassword = new JLabel("Password");
		txtName = new JTextField();
		txtAddress = new JTextField();
		txtEmail = new JTextField();
		txtPhoneNum = new JTextField();
		txtPassword = new JTextField();

		newUser.add(lblName);
		newUser.add(txtName);
		newUser.add(lblAddress);
		newUser.add(txtAddress);
		newUser.add(lblEmail);
		newUser.add(txtEmail);
		newUser.add(lblPhoneNum);
		newUser.add(txtPhoneNum);
		newUser.add(lblPassword);
		newUser.add(txtPassword);
		
		newUser.add(btnConNewUser);
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(newUser, BorderLayout.NORTH);
		frame.repaint();
		frame.revalidate();		
	}
	
	public void userInfo(Customer cust) {
		userInfo = new JPanel();
		userInfo.setLayout(new GridLayout(0,2));		
		
		lblName = new JLabel("Name");
		lblAddress = new JLabel("Full Address");
		lblEmail = new JLabel("Email");
		lblPhoneNum = new JLabel("Phone Number");
		lblPassword = new JLabel("Password");
		lblCustNum = new JLabel("Customer Number");
		
		JLabel lblName2 = new JLabel(cust.getName().getFirstAndLastName());
		JLabel lblAddress2 = new JLabel(cust.getAddress());
		JLabel lblEmail2 = new JLabel(cust.getEmail());
		JLabel lblPhoneNum2 = new JLabel(cust.getPhone_num());
		JLabel lblPassword2 = new JLabel(cust.getPassword());
		JLabel lblCustNum2 = new JLabel(cust.getCust_num());


		userInfo.add(lblName);
		userInfo.add(lblName2);
		userInfo.add(lblAddress);
		userInfo.add(lblAddress2);
		userInfo.add(lblEmail);
		userInfo.add(lblEmail2);
		userInfo.add(lblPhoneNum);
		userInfo.add(lblPhoneNum2);
		userInfo.add(lblCustNum);
		userInfo.add(lblCustNum2);
		userInfo.add(lblPassword);
		userInfo.add(lblPassword2);
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(userInfo, BorderLayout.NORTH);
		frame.repaint();
		frame.revalidate();		
	}
	
	public void signIn() {
		signIn = new JPanel();
		signIn.setLayout(new GridLayout(0,2));		
		btnConSignIn = new JButton("Continue");
		btnConSignIn.addActionListener(this);
		
		lblPassword = new JLabel("Password");
		lblCustNum = new JLabel("Customer Number");
		txtCustNum = new JTextField();
		txtPassword = new JTextField();

		signIn.add(lblCustNum);
		signIn.add(txtCustNum);
		signIn.add(lblPassword);
		signIn.add(txtPassword);
		signIn.add(btnConSignIn);
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(signIn, BorderLayout.NORTH);
		frame.repaint();
		frame.revalidate();		
	}
	
	public void conNewUser() {
		
		Name userName = new Name(txtName.getText());
		Customer user = new Customer(userName, "000001", txtPassword.getText(), 
				txtAddress.getText(), txtEmail.getText(), txtPhoneNum.getText());
		user.printCust();
		userInfo(user);
	}

}