package code;

import javax.swing.*;
import java.awt.*;


public class GUI {
	
	private JFrame frame;

	public GUI() {
		
		frame = new JFrame("FracHub");
		frame.setBounds(50,50, 450, 500);
		frame.getContentPane().setBackground(Color.white);
		frame.setVisible(true);

		mainMenuGUI new_panel = new mainMenuGUI();
		new_panel.mainMenu(frame);
		
	}
}