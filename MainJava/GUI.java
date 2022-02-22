package code;

import javax.swing.*;
//import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class GUI implements ActionListener{
	
	private JFrame frame;
	private JPanel mainMenu, signIn, signIn2, newUser, newUser2, userInfo, userInfo2;
	private JButton btnNewUser, btnSignIn, btnConNewUser, btnConSignIn, btnReturnMM;
	private JLabel lblName, lblAddress, lblPhoneNum, lblEmail, lblPassword, lblCustNum;
	private JTextField txtName, txtAddress, txtPhoneNum, txtEmail, txtPassword, txtCustNum;
	

	public GUI() {
		
		frame = new JFrame("FracHub");
		frame.setBounds(50,50, 450, 500);
		frame.getContentPane().setBackground(Color.white);
		
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
		
		lblName = new JLabel("Name");
		lblAddress = new JLabel("Full Address");
		lblEmail = new JLabel("Email");
		lblPhoneNum = new JLabel("Phone Number");
		lblPassword = new JLabel("Password");
		
		btnReturnMM = new JButton("Main Menu");
		btnReturnMM.addActionListener(this);
		
		JLabel lblName2 = new JLabel(cust.getName().getFirstAndLastName());
		JLabel lblAddress2 = new JLabel(cust.getAddress());
		JLabel lblEmail2 = new JLabel(cust.getEmail());
		JLabel lblPhoneNum2 = new JLabel(cust.getPhone_num());
		JLabel lblPassword2 = new JLabel(cust.getPassword());


		userInfo.add(lblName);
		userInfo.add(lblName2);
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
		
		lblPassword = new JLabel("Password");
		lblCustNum = new JLabel("Customer Number");
		txtCustNum = new JTextField();
		txtPassword = new JTextField();
		
		signIn.add(lblCustNum);
		signIn.add(txtCustNum);
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
		
		Name userName = new Name(txtName.getText());
		Customer user = new Customer(userName, "000001", txtPassword.getText(), 
				txtAddress.getText(), txtEmail.getText(), txtPhoneNum.getText());
		user.printCust();
		user.saveCust();
		userInfo(user);
	}
	
	public void conSignIn() {
		
		Customer cust = new Customer();
		cust = cust.custSignIn(txtCustNum.getText(), txtPassword.getText());
		userInfo(cust);

	}

}