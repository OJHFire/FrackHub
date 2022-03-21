import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.*;

public class viewMyItemsGUI implements ActionListener {

	JFrame frame;

	JPanel topPanel;
	JPanel centerPanel;
	JPanel bottomPanel;

	JButton btnNext;
	JButton btnBack;

	JLabel lblTitle = new JLabel("My Items");
	JLabel lblNum = new JLabel("Number: ");
	JLabel lblName = new JLabel("Name: ");
	JLabel lblType = new JLabel("Type: ");
	JLabel lblDescription = new JLabel("Description: ");
	JLabel lblValue = new JLabel("Value: ");
	JLabel lblCostPerDay = new JLabel("Cost Per Day: ");
	
	JLabel lblItemNum = new JLabel();
	JLabel lblItemName = new JLabel();
	JLabel lblItemType = new JLabel();
	JLabel lblItemDescription = new JLabel();
	JLabel lblItemValue = new JLabel();
	JLabel lblItemCostPerDay = new JLabel();

	int count = 0;
	int totalItems = 0;
	int userId;

	String itemName;
	String itemType;
	String itemDescription;
	double itemValue;
	double itemCostPerDay;

	viewMyItemsGUI(int id) {
		userId = id;
		viewItem(new JFrame());
	}

	public void viewItem(JFrame new_Frame) {

		frame = new_Frame;
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setBounds(50, 50, 450, 500);

		// TOP PANEL
		topPanel = new JPanel();
		lblTitle.setFont(new Font("Serif", Font.BOLD, 30));
		topPanel.add(lblTitle);

		// CENTER PANEL
		centerPanel = new JPanel();
		GridLayout gl_center = new GridLayout(6,3);
		gl_center.setVgap(5);
		gl_center.setHgap(0);
		centerPanel.setLayout(gl_center);

		centerPanel.setBorder(BorderFactory.createEmptyBorder(0,20,0,20));

		// Get Total Items
		if (totalItems == 0) {
			getTotalItems();
		}
		// GET NEXT ITEM
		if (count + 1 <= totalItems) {
			count++;
			getItem(count);

			lblItemNum.setText(count+ " of " + totalItems);
			centerPanel.add(lblNum);
			centerPanel.add(lblItemNum);

			lblItemName.setText(itemName);
			centerPanel.add(lblName);
			centerPanel.add(lblItemName);

			lblItemType.setText(itemType);
			centerPanel.add(lblType);
			centerPanel.add(lblItemType);

			lblItemDescription.setText(itemDescription);
			centerPanel.add(lblDescription);
			centerPanel.add(lblItemDescription);

			lblItemValue.setText("£"+itemValue);
			centerPanel.add(lblValue);
			centerPanel.add(lblItemValue);

			lblItemCostPerDay.setText("£"+itemCostPerDay);
			centerPanel.add(lblCostPerDay);
			centerPanel.add(lblItemCostPerDay);
			
		}else {
			//Display NO Items Message
			lblNum.setText("You have not uploaded any items yet");
			
		}
		
		//BOTTOM PANEL
		bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridLayout(0, 2, 20,10));
		bottomPanel.setBorder(BorderFactory.createEmptyBorder(0,50,20,50));
		
		btnBack = new JButton("Main Menu");
		btnBack.addActionListener(this);
		btnNext = new JButton("Next Item");
		btnNext.addActionListener(this);
		
		bottomPanel.add(btnBack);
		if( count < totalItems) {
			bottomPanel.add(btnNext);
			
		}


		frame.getContentPane().removeAll();
		frame.getContentPane().add(topPanel, BorderLayout.NORTH);
		frame.getContentPane().add(centerPanel, BorderLayout.CENTER);
		frame.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
	}

	private void getTotalItems() {
		try {
			// Connect to the Database
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());// Creating Connection Object
			System.out.println("Connecting to Database...");
			Connection connection = DriverManager
					.getConnection("jdbc:oracle:thin:OPS$2042387/P46919@ora-srv.wlv.ac.uk:1521/catdb.wlv.ac.uk");
			System.out.println("COnnected with database");
			// Statement
			Statement selectStmt = connection.createStatement();

			// Search for the Email and passwords in the Database
			ResultSet result_set = selectStmt.executeQuery("SELECT COUNT(*) from Items where userID =" + userId);
			while (result_set.next()) {
				totalItems = result_set.getInt(1);
				System.out.println("Total Items are: " + totalItems);
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	private void getItem(int count) {
		try {
			// Connect to the Database
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());// Creating Connection Object
			System.out.println("Connecting to Database...");
			Connection connection = DriverManager
					.getConnection("jdbc:oracle:thin:OPS$2042387/P46919@ora-srv.wlv.ac.uk:1521/catdb.wlv.ac.uk");
			System.out.println("COnnected with database");
			// Statement
			Statement selectStmt = connection.createStatement();

			// Search for the Email and passwords in the Database
			ResultSet result_set = selectStmt
					.executeQuery("Select Id, Name, Type, Description, Value, Borrow_Cost FROM Items Where Id =" + count
							+ "and userId = " + userId);
			
			System.out.println("Count: "+ count);
			while (result_set.next()) {
				itemName = result_set.getString(2);
				itemType = result_set.getString(3);
				itemDescription = result_set.getString(4);
				itemValue = result_set.getDouble(5);
				itemCostPerDay = result_set.getDouble(6);
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnNext) {
			System.out.println("Next");
			viewItem(frame);
			
		}

	}

}
