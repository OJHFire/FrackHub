package adminAppCode;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Contains GUI to for the start menu to sign in.
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
		mainMenu.setBorder(BorderFactory.createEmptyBorder(10,300,20,300));
		mainMenu.setBackground(Color.white);
		mainMenu2.setBackground(Color.white);
		mainMenu.setLayout(new GridLayout(1,0,0,10));
		
		//Import Logo
		try {
			BufferedImage logo = ImageIO.read(this.getClass().getResource("AdminLogo.png"));
			Image resizedImage = logo.getScaledInstance(380, 380, Image.SCALE_DEFAULT);
		    BufferedImage outputImage = new BufferedImage(380, 380, BufferedImage.TYPE_INT_RGB);
		    outputImage.getGraphics().drawImage(resizedImage, 0, 0, null);
			
			JLabel picLabel = new JLabel(new ImageIcon(resizedImage));
			mainMenu2.add(picLabel);
		}
		catch (IOException ex) {
			System.out.println(ex);
		}
		
		
		// Control buttons to sign in or sign up.
		
		btnSignIn = nimbusButton.generateNimbusButton("Sign In");
		btnSignIn.setPreferredSize(new Dimension(100,40));
		btnSignIn.putClientProperty("JComponent.sizeVariant", "large");
		btnSignIn.addActionListener(this);
		mainMenu.add(btnSignIn, new GridLayout(1,0));
		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(BorderLayout.SOUTH, mainMenu);
		frame.getContentPane().add(BorderLayout.NORTH, mainMenu2);
		frame.repaint();
		frame.revalidate();
	}
	
	// Function for any events.
	public void actionPerformed(ActionEvent e) {

		// When the sign in button is pressed the signInGUI will be created.
		if(e.getSource() == btnSignIn)
		{
			new signInGUI(frame);
		}
	}

}

