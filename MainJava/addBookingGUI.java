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
	static GUI gui;
		
	static JPanel addBooking1;
	static JPanel addBooking2;
	
	JButton btnReturnMM;
	JButton btnConType;
	
	JLabel lblType = new JLabel("Item type");
	JLabel lblName = new JLabel("Item Name");
	JLabel lblDescription = new JLabel("Description");
	JLabel lblItemDescription;
	JTextArea lblItemDescription2;
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
    
    NimbusButton nimbusButton = new NimbusButton();
    ComboBox new_combo = new ComboBox();
    static Font font = new Font("Calibri", Font.BOLD, 15);
    
    static GridBagConstraints panel_constraints;

	// Function to create GUI page.
	public addBookingGUI(Customer new_cust, JFrame new_frame) {
		
		frame = new_frame;
		cust = new_cust;
		
		// Remove all previous content on frame.
		frame.getContentPane().removeAll();
		
		// Layout settings for the page.
		
		// NORTH PANEL - Create header with given title.
		gui = new GUI();
		gui.pageHeader(frame, "Book an Item");
		
		
		// CENTRE PANEL - Create labels for item details.
		addBooking1 = new JPanel();
		addBooking1.setLayout(new GridBagLayout());
		panel_constraints = new GridBagConstraints();
		addBooking1.setBorder(BorderFactory.createEmptyBorder(30,20,20,20));
		addBooking1.setBackground(Color.white);
		
		// Item description text area.
		lblItemDescription2 = new JTextArea(2,30);
		lblItemDescription2.setMaximumSize(new Dimension(10, 10));
		lblItemDescription2.setWrapStyleWord(true);
		lblItemDescription2.setLineWrap(true);
		lblItemDescription2.setOpaque(false);
		lblItemDescription2.setEditable(false);
		lblItemDescription2.setFocusable(false);
		
		// Retrieve list of items available for customer from database.
		type_list = new ArrayList<String>();
		ItemResult itemResult = item.viewAllItems(cust);
		
		type_list = itemResult.type_list;
		type_list2 = type_list.toArray(new String[0]);
		type = type_list2[0];

		item_list = itemResult.item_list;
		
		// Check there is a connection with the database.
		if (!type.equals("Error")) {

			// Creating list of items for given type.
			current_item_list = new ArrayList<Item>();
			
			boolean firstItem = true;
			
			for (int i = 0; i < item_list.size(); i++) {
				
				if (item_list.get(i).getType().equals(type)) {
				
					current_item_list.add(item_list.get(i));
					if (firstItem) {
						currentItem = item_list.get(i);
						lblItemDescription2.setText(item_list.get(i).getDescription());
						lblItemDailyCost = new JLabel(String.format("%.2f", item_list.get(i).getDaily_rate()));
						lblItemDailyCost.setFont(font);
						lblTotalCost2 = new JLabel(String.format("%.2f", item_list.get(i).getDaily_rate()));
						lblTotalCost2.setFont(font);
						totalCost = item_list.get(i).getDaily_rate();
						firstItem = false;
					}
					
				}
			}
					
			current_item_list2 = current_item_list.toArray(new Item[current_item_list.size()]);
	
			itemTypeList = new JComboBox<String>(type_list2);
			new_combo.setComboBox(itemTypeList);
			itemTypeList.setBackground(Color.WHITE);
			((JLabel)itemTypeList.getRenderer()).setVerticalAlignment(JLabel.TOP);
			itemTypeList.addActionListener(this);
			currentItemList = new JComboBox<Item>(current_item_list2);
			currentItemList.setBackground(Color.WHITE);
			currentItemList.setFont(font);
			currentItemList.addActionListener(this);
			
			setListToName(currentItemList);
	
			// Create two calendars for start and end dates.
			LocalDate today = LocalDate.now();
			
			datePicker1 = new DatePicker();
			datePicker2 = new DatePicker();;
			dateSettings = new DatePickerSettings();
			dateSettings2 = new DatePickerSettings();
			
			datePicker1.setDate(today.plusDays(1));
			datePicker1.setSettings(dateSettings);
			datePicker2.setDate(today.plusDays(1));
			datePicker2.setSettings(dateSettings2);
			
			datePicker1.addDateChangeListener(new SampleDateChangeListener());
			datePicker2.addDateChangeListener(new SampleDateChangeListener2());
			
			dateSettings.setColor(DateArea.CalendarBackgroundVetoedDates, Color.RED);
			dateSettings.setDateRangeLimits(today.plusDays(1), null);
			dateSettings2.setColor(DateArea.CalendarBackgroundVetoedDates, Color.RED);
			dateSettings2.setDateRangeLimits(today.plusDays(1), null);
			
			newFirstDate = LocalDate.now();
		    secondDate = LocalDate.now();
		   
		    // Set font of labels.
			lblType.setFont(font);
			itemTypeList.setFont(font);
			lblName.setFont(font);
			lblDescription.setFont(font);
			lblItemDescription2.setFont(font);
			lblDailyCost.setFont(font);
			lblItemDailyCost.setFont(font);
			lblTotalCost.setFont(font);
			lblTotalCost2.setFont(font);
			lblStartDate.setFont(font);
			lblEndDate.setFont(font);
			
			// Add labels to the panel
			addCentrePanel();
			
			// SOUTH PANEL - Add navigation and confirmation buttons.
			addBooking2 = new JPanel();
			addBooking2.setLayout(new GridLayout(0,2,20,10));
			addBooking2.setBorder(BorderFactory.createEmptyBorder(0,50,20,50));
			addBooking2.setBackground(Color.white);
			
			// Control buttons to confirm booking or go back to main menu.
			btnConType = nimbusButton.generateNimbusButton("Confirm");
			btnConType.putClientProperty("JComponent.sizeVariant", "large");
			btnReturnMM = nimbusButton.generateNimbusButton("Main Menu");
			btnReturnMM.putClientProperty("JComponent.sizeVariant", "large");
			btnConType.addActionListener(this);
			btnReturnMM.addActionListener(this);
			
			addBooking2.add(btnConType);
			addBooking2.add(btnReturnMM);
			
			frame.getContentPane().add(addBooking1, BorderLayout.CENTER);
			frame.getContentPane().add(addBooking2, BorderLayout.SOUTH);
			frame.repaint();
			frame.revalidate();		
		}
		// There is no connection with the database so display a warning.
		else {
			String[] list = {"Error"};
			Item[] list2 = {new Item()};
			itemTypeList = new JComboBox<String>(list);
			currentItemList = new JComboBox<Item>(list2);
			currentItem = new Item();
			
			JLabel lblWarning = new JLabel("Connection Issue - Please try again later.");
			lblWarning.setHorizontalAlignment(JLabel.CENTER);
			addBooking1.setLayout(new GridLayout(0,1,20,10));
			addBooking1.add(lblWarning);
			
			// SOUTH PANEL - Add navigation and confirmation buttons.
			addBooking2 = new JPanel();
			addBooking2.setLayout(new GridLayout(0,1,20,10));
			addBooking2.setBorder(BorderFactory.createEmptyBorder(0,140,20,140));
			addBooking2.setBackground(Color.white);
			
			// Control buttons to confirm booking or go back to main menu.
			btnReturnMM = new JButton("Main Menu");
			btnReturnMM.addActionListener(this);
			
			addBooking2.add(btnReturnMM);
			
			frame.getContentPane().add(addBooking1, BorderLayout.CENTER);
			frame.getContentPane().add(addBooking2, BorderLayout.SOUTH);
			frame.repaint();
			frame.revalidate();	
		}
	}
	
	// Function to check save new booking if dates do not clash.
	public void conNewBooking() {
		
		Booking booking = new Booking();
		
		int checkBooking = booking.checkBooking(currentItem.getItem_num(), 
								datePicker1.getDate().format(DateTimeFormatter.ofPattern("dd-MMM-uuuu")), 
								datePicker2.getDate().format(DateTimeFormatter.ofPattern("dd-MMM-uuuu")));
		
		// Booking dates do not clash.
		if (checkBooking == 1) {
			booking = new Booking(currentItem.getItem_num(),currentItem.getCust_num() , cust.getCust_num(), 
								datePicker1.getDate(), datePicker2.getDate(), totalCost, currentItem.getName());
			// Check there is a connection with the database.
			if (booking.saveBooking()) {
				new viewBookingGUI(currentItem, booking, cust, frame);	
			}
			// There is no connection with the database.
			else {
				addBooking1.setBorder(BorderFactory.createEmptyBorder(10,20,20,20));
				gui.inputWarning("Connection Issue - Please try again later.");
			}		
		}	
		else {
			// There is no connection with the database.
			if (checkBooking == 3) {
				addBooking1.setBorder(BorderFactory.createEmptyBorder(10,20,20,20));
				gui.inputWarning("Connection Issue - Please try again later.");
			}
			// Booking dates clash with previous booking.
			else {
				addBooking1.setBorder(BorderFactory.createEmptyBorder(10,20,20,20));
				gui.inputWarning("This item is unavailable on the selected dates.");
			}
		}
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
				addBooking1.remove(lblTotalCost2);
				addBooking1.remove(lblStartDate);
				addBooking1.remove(datePicker1);
				addBooking1.remove(lblEndDate);
				addBooking1.remove(datePicker2);
				long dayDiff = Math.abs(ChronoUnit.DAYS.between(secondDate, newFirstDate)) + 1;
				totalCost = (currentItem.getDaily_rate() * dayDiff);
				lblTotalCost2.setText(String.format("%.2f", totalCost));
				panel_constraints.anchor = GridBagConstraints.CENTER;
				panel_constraints.ipady = 10;
				panel_constraints.gridy = 4;
				panel_constraints.gridx = 2;
				addBooking1.add(lblTotalCost2, panel_constraints);
				panel_constraints.gridy = 5;
				panel_constraints.gridx = 0;
				addBooking1.add(lblStartDate, panel_constraints);
				panel_constraints.gridx = 2;
				panel_constraints.ipady = 0;
				addBooking1.add(datePicker1, panel_constraints);
				panel_constraints.gridy = 6;
				panel_constraints.gridx = 0;
				panel_constraints.ipady = 10;
				panel_constraints.anchor = GridBagConstraints.SOUTH;
				addBooking1.add(lblEndDate, panel_constraints);
				panel_constraints.gridx = 2;
				panel_constraints.ipady = 0;
				addBooking1.add(datePicker2, panel_constraints);
	      
				addBooking1.setBorder(BorderFactory.createEmptyBorder(30,20,20,20));
			    gui.removeInputWarning();
			      
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
	      
		    addBooking1.remove(lblTotalCost2);
		    addBooking1.remove(lblStartDate);
		    addBooking1.remove(datePicker1);
		    addBooking1.remove(lblEndDate);
		    addBooking1.remove(datePicker2);
		    long dayDiff = Math.abs(ChronoUnit.DAYS.between(secondDate, newFirstDate)) + 1;
		    totalCost = (currentItem.getDaily_rate() * dayDiff);
			lblTotalCost2.setText(String.format("%.2f", totalCost));
			panel_constraints.anchor = GridBagConstraints.CENTER;
			panel_constraints.ipady = 10;
			panel_constraints.gridy = 4;
			panel_constraints.gridx = 2;
			addBooking1.add(lblTotalCost2, panel_constraints);
			panel_constraints.gridy = 5;
			panel_constraints.gridx = 0;
			addBooking1.add(lblStartDate, panel_constraints);
			panel_constraints.gridx = 2;
			panel_constraints.ipady = 0;
			addBooking1.add(datePicker1, panel_constraints);
			panel_constraints.gridy = 6;
			panel_constraints.gridx = 0;
			panel_constraints.ipady = 10;
			panel_constraints.anchor = GridBagConstraints.SOUTH;
			addBooking1.add(lblEndDate, panel_constraints);
			panel_constraints.gridx = 2;
			panel_constraints.ipady = 0;
			addBooking1.add(datePicker2, panel_constraints);
	      
			addBooking1.setBorder(BorderFactory.createEmptyBorder(30,20,20,20));
	        gui.removeInputWarning();
	      
	        frame.repaint();
	        frame.revalidate();
	      
		  }
	  }
	
	
	// Adapted from https://stackoverflow.com/questions/7732331/modify-combobox-display-in-swing.
	// The JComboBox for items has been adjusted to display the item name.
	@SuppressWarnings("serial")
	public void setListToName(JComboBox<Item> list) {
	
			list.setRenderer(new DefaultListCellRenderer() {
		        @SuppressWarnings("rawtypes")
				@Override
		        public Component getListCellRendererComponent(final JList list, Object value, final int index, final boolean isSelected,
		                final boolean cellHasFocus) {

		        			Item new_item = new Item();
		        		
		        			if (value.getClass().isInstance(new Item())) {
		        				
		        					new_item = (Item)value;
		        					value = new_item.getName();
		        					
		        			}
		        			
		        			list.setSelectionBackground(new Color(0,102,204));
		        			list.setSelectionForeground(Color.white);
		        			list.setBackground(Color.white);
		        	
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
						lblItemDescription2.setText(currentItem.getDescription());
						lblItemDailyCost = new JLabel(String.format("%.2f", item_list.get(i).getDaily_rate()));
						lblItemDailyCost.setFont(font);
						long dayDiff = Math.abs(ChronoUnit.DAYS.between(secondDate, newFirstDate)) + 1;
					    totalCost = (currentItem.getDaily_rate() * dayDiff);
					    lblTotalCost2 = new JLabel(String.format("%.2f", totalCost));
					    lblTotalCost2.setFont(font);
						firstItem = false;
					}
					
				}
			}
					
			current_item_list2 = current_item_list.toArray(new Item[current_item_list.size()]);

			currentItemList = new JComboBox<Item>(current_item_list2);
			currentItemList.setBackground(Color.WHITE);
			currentItemList.setFont(font);
			currentItemList.addActionListener(this);
			
			setListToName(currentItemList);
			
		    addBooking1.removeAll();
		    addCentrePanel();
			
			frame.getContentPane().remove(addBooking1);
			frame.getContentPane().add(addBooking1, BorderLayout.CENTER);
			
			addBooking1.setBorder(BorderFactory.createEmptyBorder(30,20,20,20));
		    gui.removeInputWarning();
		      
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

			lblItemDescription2.setText(currentItem.getDescription());
			lblItemDailyCost = new JLabel(String.format("%.2f", currentItem.getDaily_rate()));
			lblItemDailyCost.setFont(font);
			long dayDiff = Math.abs(ChronoUnit.DAYS.between(secondDate, newFirstDate)) + 1;
		    totalCost = (currentItem.getDaily_rate() * dayDiff);
		    lblTotalCost2 = new JLabel(String.format("%.2f", totalCost));
		    lblTotalCost2.setFont(font);

		    addBooking1.removeAll();
		    addCentrePanel();
			
			frame.getContentPane().remove(addBooking1);
			frame.getContentPane().add(addBooking1, BorderLayout.CENTER);
			
			addBooking1.setBorder(BorderFactory.createEmptyBorder(30,20,20,20));
		    gui.removeInputWarning();
		      
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
	
	// Function to add labels to the panel
	public void addCentrePanel() {
		JLabel space1 = new JLabel("      ");
		panel_constraints.fill = GridBagConstraints.HORIZONTAL;
		panel_constraints.gridy = 0;
		panel_constraints.gridx = 0;
		panel_constraints.weightx = 1.0; 
		panel_constraints.weighty = 1.0; 
		panel_constraints.ipady = 10;
		panel_constraints.anchor = GridBagConstraints.NORTH;
		addBooking1.add(lblType, panel_constraints);
		panel_constraints.gridx = 1;
		panel_constraints.ipadx = 90;
		addBooking1.add(space1, panel_constraints);
		panel_constraints.gridx = 2;
		panel_constraints.ipadx = 0;
		panel_constraints.ipady = -4;
		addBooking1.add(itemTypeList, panel_constraints);
		panel_constraints.gridy = 1;
		panel_constraints.gridx = 0;
		panel_constraints.ipady = 10;
		addBooking1.add(lblName, panel_constraints);
		panel_constraints.gridx = 2;
		panel_constraints.ipady = -4;
		addBooking1.add(currentItemList, panel_constraints);
		panel_constraints.gridy = 2;
		panel_constraints.gridx = 0;
		panel_constraints.ipady = 10;
		addBooking1.add(lblDescription, panel_constraints);
		panel_constraints.gridx = 2;
		panel_constraints.ipady = 0;
		addBooking1.add(lblItemDescription2, panel_constraints);
		panel_constraints.gridy = 3;
		panel_constraints.gridx = 0;
		panel_constraints.ipady = 10;
		panel_constraints.anchor = GridBagConstraints.CENTER;
		addBooking1.add(lblDailyCost, panel_constraints);
		panel_constraints.gridx = 2;
		addBooking1.add(lblItemDailyCost, panel_constraints);
		panel_constraints.gridy = 4;
		panel_constraints.gridx = 0;
		addBooking1.add(lblTotalCost, panel_constraints);
		panel_constraints.gridx = 2;
		addBooking1.add(lblTotalCost2, panel_constraints);
		panel_constraints.gridy = 5;
		panel_constraints.gridx = 0;
		addBooking1.add(lblStartDate, panel_constraints);
		panel_constraints.gridx = 2;
		panel_constraints.ipady = 0;
		addBooking1.add(datePicker1, panel_constraints);
		panel_constraints.gridy = 6;
		panel_constraints.gridx = 0;
		panel_constraints.ipady = 10;
		panel_constraints.anchor = GridBagConstraints.SOUTH;
		addBooking1.add(lblEndDate, panel_constraints);
		panel_constraints.gridx = 2;
		panel_constraints.ipady = 0;
		addBooking1.add(datePicker2, panel_constraints);
	}

}
