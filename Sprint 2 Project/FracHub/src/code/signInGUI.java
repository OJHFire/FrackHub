package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Contains GUI to input email and password and sign in to the application.
 */

public class signInGUI implements ActionListener{
	
	JFrame frame;
	GUI gui;
	
	JPanel signIn1;
	JPanel signIn2;
	
	JButton btnConSignIn;
	JButton btnReturnMM;
	JButton btnShowPass;
	
	JTextField txtEmail = new JTextField();
	JPasswordField txtPassword = new JPasswordField();

	JLabel lblEmail = new JLabel("Email");
	JLabel lblPassword = new JLabel("Password");
	
	NimbusButton nimbusButton = new NimbusButton();
	Font font = new Font("Calibri", Font.BOLD, 15);
	
	boolean hidePassword = true;
	
	// Function to create GUI page.
	public signInGUI(JFrame new_frame) {
	
		frame = new_frame;
		
		// Remove all previous content on frame.
		frame.getContentPane().removeAll();
				
		// Layout settings for the page.
		
		// NORTH PANEL - Create header with given title.
		gui = new GUI();
		gui.pageHeader(frame, "Sign Me In");
		
		
		// CENTRE PANEL - Create email and password label and input.
		signIn1 = new JPanel();
		signIn1.setBorder(BorderFactory.createEmptyBorder(30,20,180,20));
		signIn1.setLayout(new GridLayout(0,2,0,20));
		signIn1.setBackground(Color.white);
		
		btnShowPass = nimbusButton.generateNimbusButton("Show Password");
		btnShowPass.putClientProperty("JComponent.sizeVariant", "large");
		btnShowPass.setBackground(new Color(255,153,204));
		btnShowPass.addActionListener(this);
		
		lblEmail.setFont(font);
		lblPassword.setFont(font);
		
		// Inputs for email and password.
		signIn1.add(lblEmail);
		signIn1.add(txtEmail);
		signIn1.add(lblPassword);
		signIn1.add(txtPassword);
		signIn1.add(btnShowPass);
		
		txtPassword.setEchoChar('*');
		
		
		// SOUTH PANEL - Add navigation and confirmation buttons.
		signIn2 = new JPanel();
		signIn2.setBorder(BorderFactory.createEmptyBorder(0,50,20,50));
		signIn2.setLayout(new GridLayout(0,2,20,10));	
		signIn2.setBackground(Color.white);
		
		// Control buttons to confirm sign in or go back to main menu.
		btnConSignIn = nimbusButton.generateNimbusButton("Continue");
		btnConSignIn.putClientProperty("JComponent.sizeVariant", "large");
		btnConSignIn.addActionListener(this);
		btnReturnMM = nimbusButton.generateNimbusButton("Main Menu");
		btnReturnMM.putClientProperty("JComponent.sizeVariant", "large");
		btnReturnMM.addActionListener(this);
		
		signIn2.add(btnConSignIn);
		signIn2.add(btnReturnMM);
		
		
		frame.getContentPane().add(signIn1, BorderLayout.CENTER);
		frame.getContentPane().add(signIn2, BorderLayout.SOUTH);
		frame.repaint();
		frame.revalidate();		
	}
	
	// Function to check inputs and if in the database go to the options menu.
	public void conSignIn() {
		if (!txtEmail.getText().isEmpty()) {
			if (txtPassword.getPassword().length != 0) {
				Customer cust = new Customer();
				cust = cust.custSignIn(txtEmail.getText(), txtPassword);
				if (!cust.getAddress().equals("")) {
					if (!cust.getAddress().equals("Error")) {
						optionMenuGUI new_panel = new optionMenuGUI();
						new_panel.optionMenu(cust, frame);
					}
					else {
						signIn1.setBorder(BorderFactory.createEmptyBorder(10,20,180,20));
						gui.inputWarning("Connection Issue - Please try again later.");
					}			
				}
				else {
					signIn1.setBorder(BorderFactory.createEmptyBorder(10,20,180,20));
					gui.inputWarning("Email or password is not valid.");
				}
			}
			else {
				signIn1.setBorder(BorderFactory.createEmptyBorder(10,20,180,20));
				gui.inputWarning("Please enter a password.");
			}
		}
		else {
			signIn1.setBorder(BorderFactory.createEmptyBorder(10,20,180,20));
			gui.inputWarning("Please enter an email address.");
		}
	}
	
	
	// Function for any events.
	public void actionPerformed(ActionEvent e) {

		// When the confirm button is pressed the details are checked in the database 
		//	and the options page is opened if correct.
		if(e.getSource() == btnConSignIn)
		{
			conSignIn();
		}
		// When the main menu button is pressed the mainMenuGUI is created.
		else if(e.getSource() == btnReturnMM)
		{
			mainMenuGUI new_panel = new mainMenuGUI();
			new_panel.mainMenu(frame);
		}
		else if(e.getSource() == btnShowPass) {
			if (hidePassword) {
				hidePassword = false;
				txtPassword.setEchoChar((char)0);
			}
			else {
				hidePassword = true;
				txtPassword.setEchoChar('*');
			}
		}
	}

}

