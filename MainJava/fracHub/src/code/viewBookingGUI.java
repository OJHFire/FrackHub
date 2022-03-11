package code;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.format.DateTimeFormatter;

import com.github.lgooddatepicker.components.DatePicker;
import com.github.lgooddatepicker.components.DatePickerSettings;
import com.github.lgooddatepicker.components.DatePickerSettings.DateArea;
import com.github.lgooddatepicker.zinternaltools.DateChangeEvent;
import com.github.lgooddatepicker.optionalusertools.DateChangeListener;


public class viewBookingGUI implements ActionListener{
	
	JFrame frame;
	Customer cust;
		
	JPanel warningPanel;
	JPanel viewBooking;
	JPanel viewBooking2;
	
	JButton btnReturnMM;
	JButton btnSignOut;
	
	JLabel lblType = new JLabel("Item type");
	JLabel lblName = new JLabel("Item Name");
	JLabel lblDescription = new JLabel("Description");
	JLabel lblStartDate = new JLabel("Start Date");
	JLabel lblEndDate = new JLabel("End Date");
	JLabel lblDailyCost = new JLabel("Daily Cost (£)");
	JLabel lblTotalCost = new JLabel("Total Cost (£)");
	JLabel lblType2;
	JLabel lblName2;
	JLabel lblItemDescription;
	JLabel lblStartDate2;
	JLabel lblEndDate2;
	JLabel lblItemDailyCost;
	JLabel lblTotalCost2;
	
	JComboBox<String> itemTypeList;
	JComboBox<Item> currentItemList;
	ArrayList<Item> item_list;
	ArrayList<String> type_list;
	ArrayList<Item> current_item_list;
	
	Item[] item_list2;
	Item[] current_item_list2;
	String[] type_list2;
	Item currentItem;
	Item item = new Item();
	String type;

	
	public void viewBooking(Booking booking, Customer new_cust, JFrame new_frame) {
		
		frame = new_frame;
		cust = new_cust;
		
		viewBooking = new JPanel();
		viewBooking2 = new JPanel();
		viewBooking.setLayout(new GridLayout(0,2,0,20));
		viewBooking2.setLayout(new GridLayout(0,2,20,10));
		viewBooking.setBorder(BorderFactory.createEmptyBorder(50,20,0,20));
		viewBooking2.setBorder(BorderFactory.createEmptyBorder(0,50,20,50));
		viewBooking.setBackground(Color.white);
		viewBooking2.setBackground(Color.white);
				
		btnReturnMM = new JButton("Main Menu");
		btnSignOut = new JButton("Sign Out");
		btnReturnMM.addActionListener(this);
		btnSignOut.addActionListener(this);
		
		lblType2 = new JLabel(booking.getItem().getType());
		lblName2 = new JLabel(booking.getItem().getName());
		lblItemDescription = new JLabel(booking.getItem().getDescription());
		lblStartDate2 = new JLabel(booking.getStart_date().format(DateTimeFormatter.ofPattern("dd-MMM-uuuu")));
		lblEndDate2 = new JLabel(booking.getEnd_date().format(DateTimeFormatter.ofPattern("dd-MMM-uuuu")));
		lblItemDailyCost = new JLabel(String.format("%.2f", booking.getItem().getDaily_rate()));
		lblTotalCost2 = new JLabel(String.format("%.2f", booking.getTotal_cost()));
			
		
		viewBooking.add(lblType);
		viewBooking.add(lblType2);
		viewBooking.add(lblName);
		viewBooking.add(lblName2);
		viewBooking.add(lblDescription);
		viewBooking.add(lblItemDescription);
		viewBooking.add(lblDailyCost);
		viewBooking.add(lblItemDailyCost);
		viewBooking.add(lblTotalCost);
		viewBooking.add(lblTotalCost2);
		viewBooking.add(lblStartDate);
		viewBooking.add(lblStartDate2);
		viewBooking.add(lblEndDate);
		viewBooking.add(lblEndDate2);
		
		viewBooking2.add(btnReturnMM);
		viewBooking2.add(btnSignOut);

		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(viewBooking, BorderLayout.NORTH);
		frame.getContentPane().add(viewBooking2, BorderLayout.SOUTH);
		frame.repaint();
		frame.revalidate();		
	}
	

	public void actionPerformed(ActionEvent e) {

		if(e.getSource() == btnReturnMM)
		{
			optionMenuGUI new_panel = new optionMenuGUI();
			new_panel.optionMenu(cust, frame);
		}
		else if(e.getSource() == btnSignOut)
		{
			mainMenuGUI new_panel = new mainMenuGUI();
			new_panel.mainMenu(frame);
		}
	}

}
