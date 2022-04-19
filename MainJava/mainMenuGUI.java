package code;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Contains GUI to for the start menu to sign in or sign up.
 */

public class mainMenuGUI implements ActionListener{
	
	JFrame frame;
	
	JPanel mainMenu;
	JPanel mainMenu2;
	
	JButton btnNewUser;
	JButton btnSignIn;
	
	NimbusButton nimbusButton = new NimbusButton();
	
	// Function to create GUI page.
	public void mainMenu(JFrame new_frame) {
	
		frame = new_frame;
		
		// Layout settings for the page.
		mainMenu = new JPanel();
		mainMenu2 = new JPanel();
		mainMenu.setBorder(BorderFactory.createEmptyBorder(50,120,20,120));
		mainMenu.setBackground(Color.white);
		mainMenu2.setBackground(Color.white);
		mainMenu.setLayout(new GridLayout(2,0,0,10));
		
		//Import Logo
		try {
			BufferedImage logo = ImageIO.read(this.getClass().getResource("Logo.png"));
			JLabel picLabel = new JLabel(new ImageIcon(logo));
			mainMenu2.add(picLabel);
		}
		catch (IOException ex) {
			System.out.println(ex);
		}
		
		
		// Control buttons to sign in or sign up.
		
		btnNewUser = nimbusButton.generateNimbusButton("Sign Up");
		btnNewUser.putClientProperty("JComponent.sizeVariant", "large");
		btnSignIn = nimbusButton.generateNimbusButton("Sign In");
		btnSignIn.putClientProperty("JComponent.sizeVariant", "large");
		btnNewUser.addActionListener(this);
		btnSignIn.addActionListener(this);
		mainMenu.add(btnNewUser, new GridLayout(1,0));
		mainMenu.add(btnSignIn, new GridLayout(2,0));
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(BorderLayout.SOUTH, mainMenu);
		frame.getContentPane().add(BorderLayout.NORTH, mainMenu2);
		frame.repaint();
		frame.revalidate();
	}
	
	// Function for any events.
	public void actionPerformed(ActionEvent e) {

		// When the sign up button is pressed the newUserGUI will be created.
		if(e.getSource() == btnNewUser)
		{
			new newUserGUI(frame);
		}
		// When the sign in button is pressed the signInGUI will be created.
		else if(e.getSource() == btnSignIn)
		{
			new signInGUI(frame);
		}
	}

}

