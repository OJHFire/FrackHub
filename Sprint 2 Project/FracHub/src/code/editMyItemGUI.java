package code;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.DefaultStyledDocument;

import java.awt.*;
import java.awt.event.*;


public class editMyItemGUI implements ActionListener {

	JFrame frame;
	Customer cust;
	Item item;
	GUI gui;

	String userName;

	String itemName;
	String itemType;
	String itemDescription;
	double value;
	double costPerDay;
	int userId;

	JPanel centerPanel;
	JPanel bottomPanel;

	JButton btnSubmit;
	JButton btnBack;

	JTextField txtFieldItemName = new JTextField();
	JComboBox<String> comboItemType = new JComboBox<String>();
	JTextArea textAreaDescription = new JTextArea();
	DefaultStyledDocument doc = new DefaultStyledDocument();
	JTextField txtFieldValue = new JTextField();
	JTextField txtFieldCostPerDay = new JTextField();

	JLabel lblWarning = new JLabel();
	JLabel lblItemName = new JLabel("Item Name");
	JLabel lblItemType = new JLabel("Item Type");
	JLabel lblDescription = new JLabel("Description");
	JLabel lblValue = new JLabel("Value (£)");
	JLabel lblCostPerDay = new JLabel("Cost per Day (£)");
	int count;
	
	NimbusButton nimbusButton = new NimbusButton();
	ComboBox new_combo = new ComboBox();
	Font font = new Font("Calibri", Font.BOLD, 15);

	
	public editMyItemGUI(Item new_item, Customer new_cust, JFrame new_Frame) {

		frame = new_Frame;
		cust = new_cust;
		item = new_item;
		
		// Remove all previous content on frame.
		frame.getContentPane().removeAll();

		// TOP PANEL	
		gui = new GUI();
		gui.pageHeader(frame, "Edit My Items");


		// CENTER PANEL
		centerPanel = new JPanel();
		centerPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		centerPanel.setLayout(new GridBagLayout());	
		centerPanel.setBackground(Color.white);
		GridBagConstraints panel_constraints = new GridBagConstraints();
		new_combo.setComboBox(comboItemType);
		comboItemType.setBackground(Color.WHITE);
		comboItemType.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Arts and Crafts", "Books", "Clothing and Accessories", 
													"Electronics", "Family", "Garden", "Kitchen", "Stationary", 
													"Tools and Equipment", "Vehicles"  }));
		
		Border border = BorderFactory.createLineBorder(Color.BLACK, 2);
		textAreaDescription.setBorder(border);
		textAreaDescription.setLineWrap(true);
		textAreaDescription.setDocument(doc);
		
		lblItemName.setFont(font);
		lblItemType.setFont(font);	
		lblDescription.setFont(font);
		lblValue.setFont(font);
		lblCostPerDay.setFont(font);
		
		txtFieldItemName.setText(item.getName());
		comboItemType.setSelectedItem(item.getType());
		textAreaDescription.setText(item.getDescription());
		txtFieldValue.setText(String.format("%.2f",item.getValue()));
		txtFieldCostPerDay.setText(String.format("%.2f",item.getDaily_rate()));
		
		JLabel space1 = new JLabel("      ");
		panel_constraints.fill = GridBagConstraints.HORIZONTAL;
		panel_constraints.gridy = 0;
		panel_constraints.gridx = 0;
		panel_constraints.weightx = 1.0; 
		panel_constraints.weighty = 1.0; 
		panel_constraints.ipady = 10;
		panel_constraints.anchor = GridBagConstraints.NORTH;
		centerPanel.add(lblItemName, panel_constraints);
		panel_constraints.gridx = 1;
		panel_constraints.ipadx = 90;
		centerPanel.add(space1, panel_constraints);
		panel_constraints.gridx = 2;
		panel_constraints.ipadx = 0;
		centerPanel.add(txtFieldItemName, panel_constraints);
		panel_constraints.gridy = 1;
		panel_constraints.gridx = 0;
		panel_constraints.ipady = 10;
		centerPanel.add(lblItemType, panel_constraints);
		panel_constraints.gridx = 2;
		centerPanel.add(comboItemType, panel_constraints);
		panel_constraints.gridy = 2;
		panel_constraints.gridx = 0;
		panel_constraints.ipady = 10;
		centerPanel.add(lblDescription, panel_constraints);
		panel_constraints.gridx = 2;
		panel_constraints.ipady = 10;
		centerPanel.add(textAreaDescription, panel_constraints);
		panel_constraints.gridy = 3;
		panel_constraints.gridx = 0;
		panel_constraints.ipady = 10;
		panel_constraints.anchor = GridBagConstraints.CENTER;
		centerPanel.add(lblValue, panel_constraints);
		panel_constraints.gridx = 2;
		centerPanel.add(txtFieldValue, panel_constraints);
		panel_constraints.gridy = 4;
		panel_constraints.gridx = 0;
		centerPanel.add(lblCostPerDay, panel_constraints);
		panel_constraints.gridx = 2;
		centerPanel.add(txtFieldCostPerDay, panel_constraints);
		

		// Bottom Panel
		bottomPanel = new JPanel();
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(0,50,20,50));
		bottomPanel.setLayout(new GridLayout(0,2,20,10));	
		bottomPanel.setBackground(Color.white);

		btnSubmit = nimbusButton.generateNimbusButton("Confirm");
		btnSubmit.putClientProperty("JComponent.sizeVariant", "large");
		btnSubmit.addActionListener(this);
		btnBack = nimbusButton.generateNimbusButton("Main Menu");
		btnBack.putClientProperty("JComponent.sizeVariant", "large");
		btnBack.addActionListener(this);

		bottomPanel.add(btnSubmit);
		bottomPanel.add(btnBack);

		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);

		frame.repaint();
		frame.revalidate();
		
		// Code adapted from https://stackoverflow.com/questions/10136794/limiting-the-number-of-characters-in-a-jtextfield
		textAreaDescription.addKeyListener(new KeyAdapter() {
	        @Override
	        public void keyTyped(KeyEvent e) {
	            if (textAreaDescription.getText().length() >= 100 )  {
	                e.consume();
	                centerPanel.setBorder(BorderFactory.createEmptyBorder(0,20,20,20));
	            	gui.inputWarning("The description has a maximum of 100 characters.");
	        	}
	            else {
	        		centerPanel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
	        		gui.removeInputWarning();
	        	}
	        }
		});
	}

		public void conNewItem() {
			//count = updateCount();
			if ((!txtFieldItemName.getText().isEmpty()) && (count < 100) && (!txtFieldValue.getText().isEmpty())
					&& (!txtFieldCostPerDay.getText().isEmpty())) {
				
				itemName = txtFieldItemName.getText();
				itemType = comboItemType.getSelectedItem().toString();
				
				if (textAreaDescription.getText().length() <= 100) {
					//Save Description
					itemDescription = textAreaDescription.getText();
					
					if(checkNumeric(txtFieldValue.getText())) {
						//Save value of Item
						value = Double.parseDouble(txtFieldValue.getText());
						
						if(checkNumeric(txtFieldCostPerDay.getText())) {
							//Save Cost per Day of the Item
							costPerDay = Double.parseDouble(txtFieldCostPerDay.getText());
							//Add to database
							Item edited_item = new Item(item.getItem_num(), cust.getCust_num(), itemName, itemType, itemDescription, value, costPerDay);
							int canItemBeEdited = edited_item.editItem();
							if (canItemBeEdited == 1) {
								new itemInfoGUI(edited_item, cust, frame);
							}
							else {
								if (canItemBeEdited == 3){
									centerPanel.setBorder(BorderFactory.createEmptyBorder(0,20,20,20));
									gui.inputWarning("Connection Issue - Please try again later.");
								}
								else {
									centerPanel.setBorder(BorderFactory.createEmptyBorder(0,20,20,20));
									gui.inputWarning("Item cannot be edited with existing bookings.");
									
									bottomPanel.remove(btnSubmit);
									bottomPanel.remove(btnBack);
									bottomPanel.setLayout(new GridLayout(0,1,20,10));
									bottomPanel.setBorder(BorderFactory.createEmptyBorder(0,140,20,140));
									bottomPanel.add(btnBack);

									frame.repaint();
									frame.revalidate();
								}
							}
						}
						else  {
							
							centerPanel.setBorder(BorderFactory.createEmptyBorder(0,20,20,20));
							gui.inputWarning("Please enter a number for the Cost Per Day");
						}
					}else {

						centerPanel.setBorder(BorderFactory.createEmptyBorder(0,20,20,20));
						gui.inputWarning("Please enter a number for the Value");
					}
	
				}else {

					centerPanel.setBorder(BorderFactory.createEmptyBorder(0,20,20,20));
					gui.inputWarning("The description exceeds 100 characters");
				}
			}
			else {
				centerPanel.setBorder(BorderFactory.createEmptyBorder(0,20,20,20));
				gui.inputWarning("Please complete all of the fields.");
			}
		}
	
	
		public boolean checkNumeric(String s) {
			try {
				Double.parseDouble(s);
				return true;
	
			} 
			catch (NumberFormatException error) {
				return false;
			}
		}
	
		public void actionPerformed(ActionEvent e) {
	
			if (e.getSource() == btnSubmit) {
				conNewItem();
	
			} 
			else if (e.getSource() == btnBack) {
				optionMenuGUI new_panel = new optionMenuGUI();
				new_panel.optionMenu(cust, frame);
			}
		}
}