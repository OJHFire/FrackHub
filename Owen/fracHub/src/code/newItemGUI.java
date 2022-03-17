package code;

//Call this function from the Main Menu sending the userName and the User ID

import javax.swing.*;

import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Document;
import javax.swing.text.DocumentFilter;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.Format;

public class newItemGUI implements ActionListener {

	JFrame frame;
	Customer cust;

	String userName;

	String itemName;
	String itemType;
	String itemDescription;
	double value;
	double costPerDay;
	int userId;

	JPanel contentPane;
	JPanel topPanel;
	JPanel centerPanel;
	JPanel bottomPanel;
	JPanel warningPanel;

	JButton btnSubmit;
	JButton btnBack;

	JTextField txtFieldItemName = new JTextField();
	JComboBox<String> comboItemType = new JComboBox<String>();
	JTextArea textAreaDescription = new JTextArea();
	DefaultStyledDocument doc = new DefaultStyledDocument();
	JTextField txtFieldValue = new JTextField();
	JTextField txtFieldCostPerDay = new JTextField();

	GridBagLayout gbl_topPanel;
	GridBagConstraints gbc_lblTitle;

	GridBagLayout gbl_centerPanel;
	GridBagConstraints gbc_lblItemName;
	GridBagConstraints gbc_txtFieldItemName;
	GridBagConstraints gbc_lblItemType;
	GridBagConstraints gbc_comboItemType;
	GridBagConstraints gbc_lblDescription;
	GridBagConstraints gbc_textAreaDescription;
	GridBagConstraints gbc_lblValue;
	GridBagConstraints gbc_txtFieldValue;
	GridBagConstraints gbc_lblCostPerDay;
	GridBagConstraints gbc_txtFieldCostPerDay;
	GridBagConstraints gbc_warning;

	JLabel lblTitle = new JLabel("ITEM REGISTRATION");
	JLabel lblItemName = new JLabel("Item Name");
	JLabel lblItemType = new JLabel("Item Type");
	JLabel lblDescription = new JLabel("Description");
	JLabel lblValue = new JLabel("Value");
	JLabel lblCostPerDay = new JLabel("Cost per Day");
	int count;

	public void newItem(Customer new_cust, JFrame new_Frame) {

		frame = new_Frame;
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		cust = new_cust;

		// 50, 50 , 450, 500
		frame.setBounds(50, 50, 450, 500);

		// TOP PANEL
		topPanel = new JPanel();
		gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[] { 50, 10, 30, 20 };
		gbl_topPanel.rowHeights = new int[] { 14, 0 };
		gbl_topPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_topPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		topPanel.setLayout(gbl_topPanel);

		// Title
		lblTitle.setFont(new Font("Serif", Font.BOLD, 30));
		gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.fill = GridBagConstraints.VERTICAL;
		gbc_lblTitle.insets = new Insets(0, 0, 0, 5);
		gbc_lblTitle.gridx = 1;
		gbc_lblTitle.gridy = 0;
		topPanel.add(lblTitle, gbc_lblTitle);

		// CENTER PANEL
		centerPanel = new JPanel();
		gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[] { 50, 20, 0, 20 };
		gbl_centerPanel.rowHeights = new int[] { 14, 5, 0, 0, 0, 0, 0, 0 };
		gbl_centerPanel.columnWeights = new double[] { 0.0, 1.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_centerPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		centerPanel.setLayout(gbl_centerPanel);

		// Item Name Label
		gbc_lblItemName = new GridBagConstraints();
		gbc_lblItemName.insets = new Insets(0, 0, 5, 5);
		gbc_lblItemName.gridx = 1;
		gbc_lblItemName.gridy = 1;
		centerPanel.add(lblItemName, gbc_lblItemName);

		// Item Name Field
		txtFieldItemName.setToolTipText("Enter Item Name");
		txtFieldItemName.setForeground(Color.BLACK);
		gbc_txtFieldItemName = new GridBagConstraints();
		gbc_txtFieldItemName.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldItemName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldItemName.gridx = 2;
		gbc_txtFieldItemName.gridy = 1;
		centerPanel.add(txtFieldItemName, gbc_txtFieldItemName);
		txtFieldItemName.setColumns(10);

		gbc_lblItemType = new GridBagConstraints();
		gbc_lblItemType.insets = new Insets(0, 0, 5, 5);
		gbc_lblItemType.gridx = 1;
		gbc_lblItemType.gridy = 2;
		centerPanel.add(lblItemType, gbc_lblItemType);

		// Item Type
		comboItemType.setBackground(Color.WHITE);
		comboItemType.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Book", "Craft", "Digital", "Electronic", "Garden"  }));
		gbc_comboItemType = new GridBagConstraints();
		gbc_comboItemType.insets = new Insets(0, 0, 5, 5);
		gbc_comboItemType.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboItemType.gridx = 2;
		gbc_comboItemType.gridy = 2;
		centerPanel.add(comboItemType, gbc_comboItemType);

		// Item Description
		gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescription.gridx = 1;
		gbc_lblDescription.gridy = 3;
		centerPanel.add(lblDescription, gbc_lblDescription);

		textAreaDescription.setToolTipText("Enter Item Description");
		textAreaDescription.setLineWrap(true);
		//doc = new DefaultStyledDocument();
		doc.setDocumentFilter(new DocumentSizeFilter(100));
		doc.addDocumentListener(new DocumentListener() {
			@Override
			public void changedUpdate(DocumentEvent e) {
				updateCount();
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				updateCount();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateCount();
			}

		});

		textAreaDescription.setDocument(doc);

		count = updateCount();

		gbc_textAreaDescription = new GridBagConstraints();
		gbc_textAreaDescription.insets = new Insets(0, 0, 5, 5);
		gbc_textAreaDescription.fill = GridBagConstraints.HORIZONTAL;
		gbc_textAreaDescription.gridx = 2;
		gbc_textAreaDescription.gridy = 3;

		centerPanel.add(textAreaDescription, gbc_textAreaDescription);

		// Value
		gbc_lblValue = new GridBagConstraints();
		gbc_lblValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblValue.gridx = 1;
		gbc_lblValue.gridy = 4;
		centerPanel.add(lblValue, gbc_lblValue);

		gbc_txtFieldValue = new GridBagConstraints();
		gbc_txtFieldValue.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldValue.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldValue.gridx = 2;
		gbc_txtFieldValue.gridy = 4;
		centerPanel.add(txtFieldValue, gbc_txtFieldValue);

		// Label Cost per Day
		gbc_lblCostPerDay = new GridBagConstraints();
		gbc_lblCostPerDay.insets = new Insets(0, 0, 5, 5);
		gbc_lblCostPerDay.gridx = 1;
		gbc_lblCostPerDay.gridy = 5;
		centerPanel.add(lblCostPerDay, gbc_lblCostPerDay);

		// Cost per Day Field
		//txtFieldCostPerDay = new JTextField();
		gbc_txtFieldCostPerDay = new GridBagConstraints();
		gbc_txtFieldCostPerDay.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldCostPerDay.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldCostPerDay.gridx = 2;
		gbc_txtFieldCostPerDay.gridy = 5;
		centerPanel.add(txtFieldCostPerDay, gbc_txtFieldCostPerDay);
		
		

		// Bottom Panel
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 20));

		btnSubmit = new JButton("Confirm");
		btnSubmit.addActionListener(this);
		btnBack = new JButton("Main Menu");
		btnBack.addActionListener(this);

		bottomPanel.add(btnSubmit);
		bottomPanel.add(btnBack);

		frame.getContentPane().removeAll();
		frame.getContentPane().add(topPanel, BorderLayout.NORTH);
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);

		frame.repaint();
		frame.revalidate();

	}

	private int updateCount() {
		count = 100 - doc.getLength();

		// if count < 0 show warning Panel

		return count;
	}

	public void conNewItem() {
		if ((!txtFieldItemName.getText().isEmpty()) && (count < 100) && (!txtFieldValue.getText().isEmpty())
				&& (!txtFieldCostPerDay.getText().isEmpty())) {
			
			itemName = txtFieldItemName.getText();
			itemType = comboItemType.getSelectedItem().toString();
			
			if (count > 0) {
				//Save Description
				itemDescription = textAreaDescription.getText();
				
				if(checkNumeric(txtFieldValue.getText())) {
					//Save value of Item
					value = Double.parseDouble(txtFieldValue.getText());
					
					if(checkNumeric(txtFieldCostPerDay.getText())) {
						//Save Cost per Day of the Item
						costPerDay = Double.parseDouble(txtFieldCostPerDay.getText());
						//Add to database
						Item new_item = new Item(cust.getCust_num(), itemName, itemType, itemDescription, value, costPerDay);
						new_item.saveItem();						
						
					}else 
					{
						newItem(cust, frame);
						inputWarning("Invalid item Cost Per Day");
					}
				}else 
				{
					newItem(cust, frame);
					inputWarning("Invalid item Value");
				}

			}else 
			{
				newItem(cust, frame);
				inputWarning("Description exceeds 100 characters");
			}
		}
		else {
			newItem(cust, frame);
			inputWarning("Compile all the fields");
		}
	}

	public void inputWarning(String message) {


		// Warning
		JLabel lblWarning = new JLabel(message);
		
		gbc_warning = new GridBagConstraints();
		gbc_warning.insets = new Insets(0, 5, 5, 5);
		gbc_warning.gridx = 2;
		gbc_warning.gridy = 6;
		centerPanel.add(lblWarning, gbc_warning);
		frame.repaint();
		frame.revalidate();
	}


	public boolean checkNumeric(String s) {
		try {
			Double.parseDouble(s);
			return true;

		} catch (NumberFormatException error) {
			return false;
		}
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnSubmit) {
			conNewItem();

		} else if (e.getSource() == btnBack) {
			optionMenuGUI new_panel = new optionMenuGUI();
			new_panel.optionMenu(cust, frame);
		}
	}
}