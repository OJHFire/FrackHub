package adminAppCode;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;

/**
 * Contains GUI to view all booking return information for a selected date.
 */

public class bookingReturnInfoGUI implements ActionListener{
	
	static JFrame frame;
	static Administrator admin;
	static GUI gui;
		
	static JPanel bookingReturn1;
	JPanel bookingReturn2;
	
	static JTable table_returns;
	static JScrollPane scrollpane;
	
	JButton btnReturnMM;
	JButton btnSignOut;

	DatePicker datePicker;
	DatePickerSettings dateSettings;
	static LocalDate newDate;
	LocalDate today;
	
	static String[][] booking_list_returns;
    
    NimbusButton nimbusButton = new NimbusButton();
    ComboBox new_combo = new ComboBox();
    Font font = new Font("Calibri", Font.BOLD, 15);
    
	// Function to create GUI page.
	public bookingReturnInfoGUI(Administrator new_admin, JFrame new_frame) {
		
		frame = new_frame;
		admin = new_admin;
		
		// Remove all previous content on frame.
		frame.getContentPane().removeAll();
		
		// Layout settings for the page.
		
		// NORTH PANEL - Create header with given title and calendar set to todays date.
		gui = new GUI();
		gui.pageHeader(frame, "View Returns");
		today = LocalDate.now();
		datePicker = gui.calander(today);
		datePicker.addDateChangeListener(new SampleDateChangeListener());
		
		// CENTRE PANEL - Create JTable with delivery information.
		createDeliveryTable(today);

		
		// SOUTH PANEL - Add navigation button.
		bookingReturn2 = new JPanel();
		bookingReturn2.setLayout(new GridLayout(0,2,20,10));
		bookingReturn2.setBorder(BorderFactory.createEmptyBorder(0,50,20,50));
		bookingReturn2.setBackground(Color.white);
		
		// Control buttons to sign out or go back to main menu.
		btnReturnMM = nimbusButton.generateNimbusButton("Main Menu");
		btnReturnMM.setPreferredSize(new Dimension(100,40));
		btnReturnMM.putClientProperty("JComponent.sizeVariant", "large");
		btnSignOut = nimbusButton.generateNimbusButton("Sign Out");
		btnSignOut.putClientProperty("JComponent.sizeVariant", "large");
		btnReturnMM.addActionListener(this);
		btnSignOut.addActionListener(this);
		
		bookingReturn2.add(btnSignOut);
		bookingReturn2.add(btnReturnMM);
		
		frame.getContentPane().add(bookingReturn2, BorderLayout.SOUTH);
		frame.repaint();
		frame.revalidate();		
		
		
	}
	
	
	// Adapted from code on https://github.com/LGoodDatePicker/LGoodDatePicker/blob/master/Project/src/main/java/com/github/lgooddatepicker/demo/FullDemo.java
	// Function for the calendar date changing.
	// The booking for the new date are retrieved from the database and displayed.
	private static class SampleDateChangeListener implements DateChangeListener {


		   public void dateChanged(DateChangeEvent event) {

				newDate = event.getNewDate();
				
				try {frame.remove(scrollpane);}
				catch(Exception e) {}
				
				bookingReturn1.removeAll();
				frame.remove(bookingReturn1);
				createDeliveryTable(newDate);

		   }

	  }
	
	public static void createDeliveryTable(LocalDate date) {
		
		// Create new panel.
		bookingReturn1 = new JPanel();
		bookingReturn1.setLayout(new BorderLayout());
		bookingReturn1.setBackground(Color.white);
		
		// Retrieve list of all bookings from database for the selected date.
		Booking booking = new Booking();
		booking_list_returns = booking.viewAllBookingReturns(date, admin);

		// Check if there are any bookings on the date.
		if (booking_list_returns.length != 0) {
			
			// Check there is a connection with the database.
			if (!booking_list_returns[0][0].equals("Error")) {
			
				// Create table
				String[] column_names = {"Item Name","Item Number","Booking Number","Pickup Address", "Delivery Address"};         
				table_returns = new JTable(booking_list_returns,column_names);
				scrollpane = new JScrollPane(bookingReturn1);
				
				TableColumn column = null;
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment( JLabel.CENTER );
				
				for (int i = 0; i < 5; i++) {
				    column = table_returns.getColumnModel().getColumn(i);
				    if ((i == 4) || (i == 3)) {
				        column.setPreferredWidth(300);
				    } else {
				        column.setPreferredWidth(100);
				        column.setCellRenderer(centerRenderer);
				    }
				}

				// Add table and header to the panel.
				bookingReturn1.add(table_returns.getTableHeader(), BorderLayout.PAGE_START);
				bookingReturn1.add(table_returns);
				
				frame.getContentPane().add(scrollpane, BorderLayout.CENTER);
			}
			// Display warning if there is a connection issue.
			else {
				JLabel lblWarning = new JLabel("Connection Issue - Please try again later.");
				lblWarning.setHorizontalAlignment(JLabel.CENTER);
				bookingReturn1.setLayout(new GridLayout(0,1,20,10));
				bookingReturn1.add(lblWarning);
	
				frame.getContentPane().add(bookingReturn1, BorderLayout.CENTER);
			}
				
		}
		// Display warning if there are no bookings for selected date.
		else {

			JLabel lblWarning = new JLabel("There are no returns on this date.");
			lblWarning.setHorizontalAlignment(JLabel.CENTER);
			bookingReturn1.setLayout(new GridLayout(0,1,20,10));
			bookingReturn1.add(lblWarning);

			frame.getContentPane().add(bookingReturn1, BorderLayout.CENTER);
			
		}

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
