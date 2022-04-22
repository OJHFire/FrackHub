package adminAppCode;

import java.awt.Color;
import javax.swing.UIManager;

/**
 *  The main class to start the application.
 */

public class Main {

	public static void main(String[] args) {
		
		UIManager.put("ScrollBar.background", new Color(0,179,80));
		
		GUI gui = new GUI();
		gui.startGUI();
		

	}	
}
