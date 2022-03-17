package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class signInGUI implements ActionListener{
	
	JFrame frame;
	
	JPanel warningPanel;
	JPanel signIn;
	JPanel signIn2;
	
	JButton btnConSignIn;
	JButton btnReturnMM;
	
	JTextField txtEmail = new JTextField();
	JPasswordField txtPassword = new JPasswordField();

	JLabel lblEmail = new JLabel("Email");
	JLabel lblPassword = new JLabel("Password");
	
	public void signIn(JFrame new_frame) {
	
		frame = new_frame;
		signIn = new JPanel();
		signIn2 = new JPanel();
		signIn.setBorder(BorderFactory.createEmptyBorder(50,20,0,20));
		signIn2.setBorder(BorderFactory.createEmptyBorder(0,50,20,50));
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
	
	public void conSignIn() {
		if (!txtEmail.getText().isEmpty()) {
			if (txtPassword.getPassword().length != 0) {
				Customer cust = new Customer();
				cust = cust.custSignIn(txtEmail.getText(), txtPassword);
				if (!cust.getName().getFullName().equals(" ")) {

					optionMenuGUI new_panel = new optionMenuGUI();
					new_panel.optionMenu(cust, frame);
				}
				else {
					signIn(frame);
					inputWarning("Email or password is not valid.");
				}
			}
			else {
				signIn(frame);
				inputWarning("Please enter a password.");
			}
		}
		else {
			signIn(frame);
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
	
	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == btnConSignIn)
		{
			conSignIn();
		}
		else if(e.getSource() == btnReturnMM)
		{
			mainMenuGUI new_panel = new mainMenuGUI();
			new_panel.mainMenu(frame);
		}
	}

}

