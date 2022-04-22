package adminAppCode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Contains GUI to view information about customers on the database.
 */

public class customerDataGUI implements ActionListener{
	
	JFrame frame;
	Administrator admin;
	GUI gui;
	
	JPanel custData1;
	JPanel custData2;
	
	JButton btnReturnMM;
	JButton btnSignOut;
	
	JLabel lblCustNo = new JLabel("Number of Customers");
	JLabel lblCustNo2;
	JLabel lblItemNo = new JLabel("Number of Items");
	JLabel lblItemNo2;
	
	NimbusButton nimbusButton = new NimbusButton();
	Font font = new Font("Calibri", Font.BOLD, 30);

	
	// Function to create GUI page.
	public customerDataGUI(Administrator new_admin, JFrame new_frame) {
		
		frame = new_frame;
		admin = new_admin;
		
		// Remove all previous content on frame.
		frame.getContentPane().removeAll();
		
		// Layout settings for the page.
		
		// NORTH PANEL - Create header with given title.
		gui = new GUI();
		gui.pageHeader(frame, "Customer Data");
		
		
		// CENTRE PANEL
		custData1 = new JPanel();
		custData1.setLayout(new GridLayout(0,2,20,20));
		custData1.setBorder(BorderFactory.createEmptyBorder(20,100,150,50));
		custData1.setBackground(Color.white);
		
		// Labels for details of item.
		String[] custData = admin.custNo();
		
		// Display warning if there is a connection issue.
		if (custData[0].equals("Error")) {
			
			JLabel lblWarning = new JLabel("Connection Issue - Please try again later.");
			lblWarning.setHorizontalAlignment(JLabel.CENTER);
			custData1.setLayout(new GridLayout(0,1,20,10));
			custData1.add(lblWarning);

			frame.getContentPane().add(custData1, BorderLayout.CENTER);
			
		}		
		else {
			
			lblCustNo2 = new JLabel(custData[0]);
			lblItemNo2 = new JLabel(custData[1]);
			lblCustNo.setFont(font);
			lblCustNo2.setFont(font);
			lblItemNo.setFont(font);
			lblItemNo2.setFont(font);
			lblCustNo2.setHorizontalAlignment(JLabel.CENTER);
			lblItemNo2.setHorizontalAlignment(JLabel.CENTER);
			custData1.add(lblCustNo);
			custData1.add(lblCustNo2);
			custData1.add(lblItemNo);
			custData1.add(lblItemNo2);
		}

				
		
		// SOUTH PANEL
		custData2 = new JPanel();
		custData2.setLayout(new GridLayout(0,2,20,10));
		custData2.setBorder(BorderFactory.createEmptyBorder(0,50,20,50));
		custData2.setBackground(Color.white);
				
		// Control buttons to sign out or go back to main menu.
		btnReturnMM = nimbusButton.generateNimbusButton("Main Menu");
		btnReturnMM.setPreferredSize(new Dimension(100,40));
		btnReturnMM.putClientProperty("JComponent.sizeVariant", "large");
		btnSignOut = nimbusButton.generateNimbusButton("Sign Out");
		btnSignOut.putClientProperty("JComponent.sizeVariant", "large");
		btnReturnMM.addActionListener(this);
		btnSignOut.addActionListener(this);
		
		custData2.add(btnSignOut);
		custData2.add(btnReturnMM);
		

		// Add panels to the frame.
		frame.getContentPane().add(custData1, BorderLayout.CENTER);
		frame.getContentPane().add(custData2, BorderLayout.SOUTH);
		frame.repaint();
		frame.revalidate();		
	}
	
	// Function for any events.
	public void actionPerformed(ActionEvent e) {

		// When the main menu button is pressed the optionMenuGUI is created.
		if(e.getSource() == btnReturnMM)
		{
			optionMenuGUI new_panel = new optionMenuGUI();
			new_panel.optionMenu(admin, frame);
		}
		// When the sign out button is pressed the mainMenuGUI is created.
		else if(e.getSource() == btnSignOut)
		{
			mainMenuGUI new_panel = new mainMenuGUI();
			new_panel.mainMenu(frame);
		}
	}

}
