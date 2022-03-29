package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Contains GUI to for the start menu to sign in or sign up.
 */

public class mainMenuGUI implements ActionListener{
	
	JFrame frame;
	
	JPanel mainMenu;
	
	JButton btnNewUser;
	JButton btnSignIn;
	
	// Function to create GUI page.
	public void mainMenu(JFrame new_frame) {
	
		frame = new_frame;
		
		// Layout settings for the page.
		mainMenu = new JPanel();
		mainMenu.setBorder(BorderFactory.createEmptyBorder(50,120,20,120));
		mainMenu.setBackground(Color.white);
		mainMenu.setLayout(new GridLayout(2,0,0,10));
		
		// Control buttons to sign in or sign up.
		btnNewUser = new JButton("Sign Up");
		btnSignIn = new JButton("Sign In");
		btnNewUser.addActionListener(this);
		btnSignIn.addActionListener(this);
		mainMenu.add(btnNewUser, new GridLayout(1,0));
		mainMenu.add(btnSignIn, new GridLayout(2,0));
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(BorderLayout.NORTH, mainMenu);
		frame.repaint();
		frame.revalidate();
	}
	
	// Function for any events.
	public void actionPerformed(ActionEvent e) {

		// When the sign up button is pressed the newUserGUI will be created.
		if(e.getSource() == btnNewUser)
		{
			newUserGUI new_panel = new newUserGUI();
			new_panel.newUser(frame);
		}
		// When the sign in button is pressed the signInGUI will be created.
		else if(e.getSource() == btnSignIn)
		{
			signInGUI new_panel = new signInGUI();
			new_panel.signIn(frame);
		}
	}

}

