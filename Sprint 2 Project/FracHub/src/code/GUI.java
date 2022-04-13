package code;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * The GUI class to create the GUI and set the frame parameters.
 */

public class GUI {
	
	private JFrame frame;
	
	JPanel new_panel;
	JLabel lblWarning;
	GridBagConstraints panel_constraints;
	
	Font font = new Font("Calibri", Font.BOLD, 15);

	public void startGUI() {
		
		frame = new JFrame("FracHub");
		frame.setBounds(50,50, 450, 500);
		frame.getContentPane().setBackground(Color.white);
		frame.setVisible(true);
	
		mainMenuGUI new_panel = new mainMenuGUI();
		new_panel.mainMenu(frame);
		
	}
	
	//Function to add header to page with given title.
	public void pageHeader(JFrame new_frame, String title) {
		
		frame = new_frame;
		new_panel = new JPanel();
		JLabel picLabel;
		JLabel lblTitle = new JLabel(title);

		new_panel.setLayout(new GridBagLayout());
		new_panel.setBackground(Color.white);
		panel_constraints = new GridBagConstraints();
		
		try {
			BufferedImage logo = ImageIO.read(this.getClass().getResource("Logo.png"));
	
			Image resizedImage = logo.getScaledInstance(80, 80, Image.SCALE_DEFAULT);
		    BufferedImage outputImage = new BufferedImage(80, 80, BufferedImage.TYPE_INT_RGB);
		    outputImage.getGraphics().drawImage(resizedImage, 0, 0, null);
			
			picLabel = new JLabel(new ImageIcon(resizedImage));
			
			lblTitle.setFont(new Font("MV Boli", Font.BOLD, 25));
			lblTitle.setForeground(Color.GRAY);
			JLabel space1 = new JLabel("      ");
			JLabel space2 = new JLabel("      ");
			panel_constraints.fill = GridBagConstraints.HORIZONTAL;
			panel_constraints.gridx = 0;
			panel_constraints.weightx = 1.0; 
			new_panel.add(space1, panel_constraints);
			panel_constraints.gridx = 1;
			new_panel.add(lblTitle, panel_constraints);
			panel_constraints.gridx = 3;
			new_panel.add(space2, panel_constraints);
			panel_constraints.gridx = 4;
			new_panel.add(picLabel, panel_constraints);
		}
		catch (IOException ex) {
			System.out.println(ex);
		}
		
		frame.getContentPane().add(new_panel, BorderLayout.NORTH);
		frame.repaint();
		frame.revalidate();		
	}
	
	// Function to display warning message.
	public void inputWarning(String message) {
		
		try {new_panel.remove(lblWarning);}
		catch (Exception e) {}
		
		lblWarning = new JLabel(message);
		lblWarning.setFont(font);
		panel_constraints.fill = GridBagConstraints.CENTER;	
		panel_constraints.weightx = 0.0;
		panel_constraints.gridy = 1;
		panel_constraints.gridx = 1;
		panel_constraints.gridwidth = 3;
		new_panel.add(lblWarning, panel_constraints);
		
		frame.repaint();
		frame.revalidate();	
	}
	
	// Function to remove warning message.
	public void removeInputWarning() {

		try {new_panel.remove(lblWarning);}
		catch (Exception e) {}
		
		frame.repaint();
		frame.revalidate();
	}
}