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
 * Contains GUI to view all booking delivery information for a selected date.
 */

public class bookingDeliveryInfoGUI implements ActionListener{
	
	static JFrame frame;
	static Administrator admin;
	static GUI gui;
		
	static JPanel bookingDelivery1;
	JPanel bookingDelivery2;
	
	static JTable table_deliveries;
	static JScrollPane scrollpane;
	
	JButton btnReturnMM;
	JButton btnSignOut;

	DatePicker datePicker;
	DatePickerSettings dateSettings;
	static LocalDate newDate;
	LocalDate today;
	
	static String[][] booking_list_deliveries;
    
    NimbusButton nimbusButton = new NimbusButton();
    ComboBox new_combo = new ComboBox();
    Font font = new Font("Calibri", Font.BOLD, 15);
    
	// Function to create GUI page.
	public bookingDeliveryInfoGUI(Administrator new_admin, JFrame new_frame) {
		
		frame = new_frame;
		admin = new_admin;
		
		// Remove all previous content on frame.
		frame.getContentPane().removeAll();
		
		// Layout settings for the page.
		
		// NORTH PANEL - Create header with given title and calendar set to todays date.
		gui = new GUI();
		gui.pageHeader(frame, "View Deliveries");
		today = LocalDate.now();
		datePicker = gui.calander(today);
		datePicker.addDateChangeListener(new SampleDateChangeListener());
		
		// CENTRE PANEL - Create JTable with delivery information.
		createDeliveryTable(today);

		
		// SOUTH PANEL - Add navigation button.
		bookingDelivery2 = new JPanel();
		bookingDelivery2.setLayout(new GridLayout(0,2,20,10));
		bookingDelivery2.setBorder(BorderFactory.createEmptyBorder(0,50,20,50));
		bookingDelivery2.setBackground(Color.white);
		
		// Control buttons to sign out or go back to main menu.
		btnReturnMM = nimbusButton.generateNimbusButton("Main Menu");
		btnReturnMM.setPreferredSize(new Dimension(100,40));
		btnReturnMM.putClientProperty("JComponent.sizeVariant", "large");
		btnSignOut = nimbusButton.generateNimbusButton("Sign Out");
		btnSignOut.putClientProperty("JComponent.sizeVariant", "large");
		btnReturnMM.addActionListener(this);
		btnSignOut.addActionListener(this);
		
		bookingDelivery2.add(btnSignOut);
		bookingDelivery2.add(btnReturnMM);
		
		frame.getContentPane().add(bookingDelivery2, BorderLayout.SOUTH);
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

				bookingDelivery1.removeAll();
				frame.remove(bookingDelivery1);
				createDeliveryTable(newDate);

		   }

	  }
	
	public static void createDeliveryTable(LocalDate date) {
		
		// Create new panel.
		bookingDelivery1 = new JPanel();
		bookingDelivery1.setLayout(new BorderLayout());
		bookingDelivery1.setBackground(Color.white);
		
		// Retrieve list of all bookings from database for the selected date.
		Booking booking = new Booking();
		booking_list_deliveries = booking.viewAllBookingDeliveries(date, admin);

		// Check if there are any bookings on the date.
		if (booking_list_deliveries.length != 0) {
			
			// Check there is a connection with the database.
			if (!booking_list_deliveries[0][0].equals("Error")) {
			
				// Create table
				String[] column_names = {"Item Name","Item Number","Booking Number","Pickup Address", "Delivery Address"};         
				table_deliveries = new JTable(booking_list_deliveries,column_names);
				scrollpane = new JScrollPane(bookingDelivery1);
				
				TableColumn column = null;
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment( JLabel.CENTER );
				
				for (int i = 0; i < 5; i++) {
				    column = table_deliveries.getColumnModel().getColumn(i);
				    if ((i == 4) || (i == 3)) {
				        column.setPreferredWidth(300);
				    } else {
				        column.setPreferredWidth(100);
				        column.setCellRenderer(centerRenderer);
				    }
				}

				// Add table and header to the panel.
				bookingDelivery1.add(table_deliveries.getTableHeader(), BorderLayout.PAGE_START);
				bookingDelivery1.add(table_deliveries);
				
				frame.getContentPane().add(scrollpane, BorderLayout.CENTER);
			}
			// Display warning if there is a connection issue.
			else {
				JLabel lblWarning = new JLabel("Connection Issue - Please try again later.");
				lblWarning.setHorizontalAlignment(JLabel.CENTER);
				bookingDelivery1.setLayout(new GridLayout(0,1,20,10));
				bookingDelivery1.add(lblWarning);
	
				frame.getContentPane().add(bookingDelivery1, BorderLayout.CENTER);
			}
				
		}
		// Display warning if there are no bookings for selected date.
		else {

			JLabel lblWarning = new JLabel("There are no bookings on this date.");
			lblWarning.setHorizontalAlignment(JLabel.CENTER);
			bookingDelivery1.setLayout(new GridLayout(0,1,20,10));
			bookingDelivery1.add(lblWarning);

			frame.getContentPane().add(bookingDelivery1, BorderLayout.CENTER);
			
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
