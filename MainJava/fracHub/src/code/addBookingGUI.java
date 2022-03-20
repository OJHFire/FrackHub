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

import code.Item.ItemResult;

import com.github.lgooddatepicker.optionalusertools.DateChangeListener;

/**
 * Contains GUI to add bookings to the database.
 */

public class addBookingGUI implements ActionListener{
	
	static JFrame frame;
	Customer cust;
		
	JPanel warningPanel;
	static JPanel addBooking;
	JPanel addBooking2;
	
	JButton btnReturnMM;
	JButton btnConType;
	
	JLabel lblType = new JLabel("Item type");
	JLabel lblName = new JLabel("Item Name");
	JLabel lblDescription = new JLabel("Description");
	JLabel lblItemDescription;
	static JLabel lblStartDate = new JLabel("Start Date");
	static JLabel lblEndDate = new JLabel("End Date");
	JLabel lblDailyCost = new JLabel("Daily Cost (£)");
	JLabel lblItemDailyCost;
	JLabel lblTotalCost = new JLabel("Total Cost (£)");
	static JLabel lblTotalCost2;
	
	JComboBox<String> itemTypeList;
	JComboBox<Item> currentItemList;
	ArrayList<Item> item_list;
	ArrayList<String> type_list;
	ArrayList<Item> current_item_list;
	
	Item[] item_list2;
	Item[] current_item_list2;
	String[] type_list2;
	static Item currentItem;
	Item item = new Item();
	String type;
	static double totalCost;
	
	static DatePicker datePicker1;
	static DatePicker datePicker2;
	static DatePickerSettings dateSettings;
	static DatePickerSettings dateSettings2;
	static LocalDate newFirstDate;
    static LocalDate secondDate;

	// Function to create GUI page.
	public void addBooking(Customer new_cust, JFrame new_frame) {
		
		frame = new_frame;
		cust = new_cust;
		
		// Layout settings for the page.
		addBooking = new JPanel();
		addBooking2 = new JPanel();
		addBooking.setLayout(new GridLayout(0,2,0,20));
		addBooking2.setLayout(new GridLayout(0,2,20,10));
		addBooking.setBorder(BorderFactory.createEmptyBorder(50,20,0,20));
		addBooking2.setBorder(BorderFactory.createEmptyBorder(0,50,20,50));
		addBooking.setBackground(Color.white);
		addBooking2.setBackground(Color.white);
				
		// Control buttons to confirm booking or go back to main menu.
		btnConType = new JButton("Confirm");
		btnReturnMM = new JButton("Main Menu");
		btnConType.addActionListener(this);
		btnReturnMM.addActionListener(this);
		
		// Selection buttons for booking.
		type_list = new ArrayList<String>();
		ItemResult itemResult = item.viewAllItems(cust);
		
		type_list = itemResult.type_list;
		type_list2 = type_list.toArray(new String[0]);
		type = type_list2[0];

		item_list = itemResult.item_list;

		current_item_list = new ArrayList<Item>();
		
		boolean firstItem = true;
		
		// Creating list of items for given type.
		for (int i = 0; i < item_list.size(); i++) {
			
			if (item_list.get(i).getType().equals(type)) {
			
				current_item_list.add(item_list.get(i));
				if (firstItem) {
					currentItem = item_list.get(i);
					lblItemDescription = new JLabel(item_list.get(i).getDescription());
					lblItemDailyCost = new JLabel(String.format("%.2f", item_list.get(i).getDaily_rate()));
					lblTotalCost2 = new JLabel(String.format("%.2f", item_list.get(i).getDaily_rate()));
					totalCost = item_list.get(i).getDaily_rate();
					firstItem = false;
				}
				
			}
		}
				
		current_item_list2 = current_item_list.toArray(new Item[current_item_list.size()]);

		itemTypeList = new JComboBox<String>(type_list2);
		itemTypeList.addActionListener(this);
		currentItemList = new JComboBox<Item>(current_item_list2);
		currentItemList.addActionListener(this);
		
		setListToName(currentItemList);

		LocalDate today = LocalDate.now();
		
		datePicker1 = new DatePicker();
		datePicker2 = new DatePicker();;
		dateSettings = new DatePickerSettings();
		dateSettings2 = new DatePickerSettings();
		
		datePicker1.setDateToToday();
		datePicker1.setSettings(dateSettings);
		datePicker2.setDateToToday();
		datePicker2.setSettings(dateSettings2);
		
		datePicker1.addDateChangeListener(new SampleDateChangeListener());
		datePicker2.addDateChangeListener(new SampleDateChangeListener2());
		
		dateSettings.setColor(DateArea.CalendarBackgroundVetoedDates, Color.RED);
		dateSettings.setDateRangeLimits(today, null);
		dateSettings2.setColor(DateArea.CalendarBackgroundVetoedDates, Color.RED);
		dateSettings2.setDateRangeLimits(today, null);
		
		newFirstDate = LocalDate.now();
	    secondDate = LocalDate.now();
		
		addBooking.add(lblType);
		addBooking.add(itemTypeList);
		addBooking.add(lblName);
		addBooking.add(currentItemList);
		addBooking.add(lblDescription);
		addBooking.add(lblItemDescription);
		addBooking.add(lblDailyCost);
		addBooking.add(lblItemDailyCost);
		addBooking.add(lblTotalCost);
		addBooking.add(lblTotalCost2);
		addBooking.add(lblStartDate);
		addBooking.add(datePicker1);
		addBooking.add(lblEndDate);
		addBooking.add(datePicker2);
		
		addBooking2.add(btnConType);
		addBooking2.add(btnReturnMM);

		
		frame.getContentPane().removeAll();
		frame.getContentPane().add(addBooking, BorderLayout.NORTH);
		frame.getContentPane().add(addBooking2, BorderLayout.SOUTH);
		frame.repaint();
		frame.revalidate();		
	}
	
	// Function to check save new booking if dates do not clash.
	public void conNewBooking() {
		
		Booking booking = new Booking();
		
		if (booking.checkBooking(currentItem.getItem_num(), 
								datePicker1.getDate().format(DateTimeFormatter.ofPattern("dd-MMM-uuuu")), 
								datePicker2.getDate().format(DateTimeFormatter.ofPattern("dd-MMM-uuuu")))) {
			booking = new Booking(currentItem,currentItem.getCust_num() , cust, datePicker1.getDate(), datePicker2.getDate(), totalCost);
			booking.saveBooking();
			viewBookingGUI new_panel = new viewBookingGUI();
			new_panel.viewBooking(booking, cust, frame);		
		}		
		else {
			inputWarning("This item is unavailable on the selected dates.");
		}
	}
	
	// Function to display warning message.
	public void inputWarning(String message) {
		
		warningPanel = new JPanel();
		JLabel lblWarning = new JLabel(message);
		warningPanel.setBackground(Color.white);
		warningPanel.add(lblWarning);
		frame.getContentPane().add(warningPanel, BorderLayout.CENTER);
		frame.repaint();
		frame.revalidate();
	}

	// Adapted from code on https://github.com/LGoodDatePicker/LGoodDatePicker/blob/master/Project/src/main/java/com/github/lgooddatepicker/demo/FullDemo.java
	// Function for the start date calendar changing.
	// The total cost is recalculated and end date calendar adjusted to not be before start date.
	private static class SampleDateChangeListener implements DateChangeListener {

		    public void dateChanged(DateChangeEvent event) {

	      newFirstDate = event.getNewDate();
	      secondDate = datePicker2.getDate();
	      
	      if (newFirstDate.isAfter(secondDate)) {
	    	  datePicker2.setDate(newFirstDate);
	      }
	      dateSettings2.setDateRangeLimits(newFirstDate, null);
	      addBooking.remove(lblTotalCost2);
	      addBooking.remove(lblStartDate);
	      addBooking.remove(datePicker1);
	      addBooking.remove(lblEndDate);
	      addBooking.remove(datePicker2);
	      long dayDiff = Math.abs(ChronoUnit.DAYS.between(secondDate, newFirstDate)) + 1;
	      totalCost = (currentItem.getDaily_rate() * dayDiff);
	      lblTotalCost2 = new JLabel(String.format("%.2f", totalCost));
	      addBooking.add(lblTotalCost2);
	      addBooking.add(lblStartDate);
	      addBooking.add(datePicker1);
	      addBooking.add(lblEndDate);
	      addBooking.add(datePicker2);
	      
	      frame.repaint();
	      frame.revalidate();	
	      
	    }
	  }
	
	// Adapted from code on https://github.com/LGoodDatePicker/LGoodDatePicker/blob/master/Project/src/main/java/com/github/lgooddatepicker/demo/FullDemo.java
	// Function for the end date calendar changing.
	// The total cost is recalculated.
	private static class SampleDateChangeListener2 implements DateChangeListener {

		  public void dateChanged(DateChangeEvent event) {

		      newFirstDate = datePicker1.getDate();
		      secondDate = event.getNewDate();
	      
		      addBooking.remove(lblTotalCost2);
		      addBooking.remove(lblStartDate);
		      addBooking.remove(datePicker1);
		      addBooking.remove(lblEndDate);
		      addBooking.remove(datePicker2);
		      long dayDiff = Math.abs(ChronoUnit.DAYS.between(secondDate, newFirstDate)) + 1;
		      totalCost = (currentItem.getDaily_rate() * dayDiff);
		      lblTotalCost2 = new JLabel(String.format("%.2f", totalCost));
		      addBooking.add(lblTotalCost2);
		      addBooking.add(lblStartDate);
		      addBooking.add(datePicker1);
		      addBooking.add(lblEndDate);
		      addBooking.add(datePicker2);
		      
		      frame.repaint();
		      frame.revalidate();	
	      
		  }
	  }
	
	
	// Adapted from https://stackoverflow.com/questions/7732331/modify-combobox-display-in-swing.
	// The JComboBox for items has been adjusted to display the item name.
	public void setListToName(JComboBox<Item> list) {
	
			list.setRenderer(new DefaultListCellRenderer() {
		        @Override
		        public Component getListCellRendererComponent(final JList list, Object value, final int index, final boolean isSelected,
		                final boolean cellHasFocus) {

		        			Item new_item = new Item();
		        		
		        			if (value.getClass().isInstance(new Item())) {
		        				
		        					new_item = (Item)value;
		        					value = new_item.getName();
		        			}

		                

		            return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		        }
		    });
	}
		
	// Function for any events.
	public void actionPerformed(ActionEvent e) {
		
		String selectedType = itemTypeList.getSelectedItem().toString();
		
		Item selectedItem = (Item)currentItemList.getSelectedItem();
		int selectedItemNum = selectedItem.getItem_num();
		
		// When the item type in the JComboBox is changed the list of items are changed to that item type.
		if (selectedType != type) {
			
			selectedType = itemTypeList.getSelectedItem().toString();
			type = selectedType;

			current_item_list = new ArrayList<Item>();
			
			boolean firstItem = true;
			
			for (int i = 0; i < item_list.size(); i++) {
				
				if (item_list.get(i).getType().equals(type)) {
				
					current_item_list.add(item_list.get(i));
					if (firstItem) {
						currentItem = item_list.get(i);
						lblItemDescription = new JLabel(item_list.get(i).getDescription());
						lblItemDailyCost = new JLabel(String.format("%.2f", item_list.get(i).getDaily_rate()));
						long dayDiff = Math.abs(ChronoUnit.DAYS.between(secondDate, newFirstDate)) + 1;
					    totalCost = (currentItem.getDaily_rate() * dayDiff);
					    lblTotalCost2 = new JLabel(String.format("%.2f", totalCost));
						firstItem = false;
					}
					
				}
			}
					
			current_item_list2 = current_item_list.toArray(new Item[current_item_list.size()]);

			currentItemList = new JComboBox<Item>(current_item_list2);
			currentItemList.addActionListener(this);
			
			setListToName(currentItemList);
			
			addBooking.removeAll();
			addBooking.setLayout(new GridLayout(0,2,0,20));
			addBooking.setBorder(BorderFactory.createEmptyBorder(50,20,0,20));
			addBooking.setBackground(Color.white);			
			
			addBooking.add(lblType);
			addBooking.add(itemTypeList);
			addBooking.add(lblName);
			addBooking.add(currentItemList);
			addBooking.add(lblDescription);
			addBooking.add(lblItemDescription);
			addBooking.add(lblDailyCost);
			addBooking.add(lblItemDailyCost);
			addBooking.add(lblTotalCost);
			addBooking.add(lblTotalCost2);
			addBooking.add(lblStartDate);
			addBooking.add(datePicker1);
			addBooking.add(lblEndDate);
			addBooking.add(datePicker2);
			frame.getContentPane().remove(addBooking);
			frame.getContentPane().add(addBooking, BorderLayout.NORTH);
			frame.repaint();
			frame.revalidate();
			
		}
		
		// When the item class selected in the JComboBox is changed the details for the item are all changed. 
		else if (selectedItemNum != currentItem.getItem_num()) {
			
			selectedItem = (Item)currentItemList.getSelectedItem();
			selectedItemNum = selectedItem.getItem_num();
			currentItem = selectedItem;
			
			itemTypeList.setSelectedItem(type);
			currentItemList.setSelectedItem(currentItem);

			lblItemDescription = new JLabel(currentItem.getDescription());
			lblItemDailyCost = new JLabel(String.format("%.2f", currentItem.getDaily_rate()));
			long dayDiff = Math.abs(ChronoUnit.DAYS.between(secondDate, newFirstDate)) + 1;
		    totalCost = (currentItem.getDaily_rate() * dayDiff);
		    lblTotalCost2 = new JLabel(String.format("%.2f", totalCost));
			
			addBooking.removeAll();
			addBooking.setLayout(new GridLayout(0,2,0,20));
			addBooking.setBorder(BorderFactory.createEmptyBorder(50,20,0,20));
			addBooking.setBackground(Color.white);			
			
			addBooking.add(lblType);
			addBooking.add(itemTypeList);
			addBooking.add(lblName);
			addBooking.add(currentItemList);
			addBooking.add(lblDescription);
			addBooking.add(lblItemDescription);
			addBooking.add(lblDailyCost);
			addBooking.add(lblItemDailyCost);
			addBooking.add(lblTotalCost);
			addBooking.add(lblTotalCost2);
			addBooking.add(lblStartDate);
			addBooking.add(datePicker1);
			addBooking.add(lblEndDate);
			addBooking.add(datePicker2);
			frame.getContentPane().remove(addBooking);
			frame.getContentPane().add(addBooking, BorderLayout.NORTH);
			frame.repaint();
			frame.revalidate();
			
		}

		// When the main menu button is pressed the optionMenuGUI is created.
		if(e.getSource() == btnReturnMM)
		{
			optionMenuGUI new_panel = new optionMenuGUI();
			new_panel.optionMenu(cust, frame);
		}
		// When the confirm button is pressed the booking dates are checked and if available saved.
		else if(e.getSource() == btnConType)
		{
			conNewBooking();
		}
	}

}
