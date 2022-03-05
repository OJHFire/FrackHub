// Call this function from the Main Menu sending the userName and the User ID

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.Format;

public class newItemGUI implements ActionListener {
	
	JFrame frame;
	
	String userName;
	double value;
	double costPerDay;
	int userId;
	
	JPanel contentPane;
	JPanel topPanel;
	JPanel centerPanel;
	JPanel bottomPanel;

	JButton btnSubmit;
	JButton btnBack;

	JTextField txtFieldItemName = new JTextField();
	JComboBox<String> comboItemType = new JComboBox<String>();
	JTextArea textAreaDescription = new JTextArea();
	JTextField txtFieldValue = new JTextField();
	JTextField txtFieldCostPerDay = new JTextField();
	
	GridBagLayout gbl_topPanel;
	GridBagConstraints gbc_lblTitle;
	GridBagConstraints gbc_lblUser;
	
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
	
	
	
	JLabel lblTitle = new JLabel("FRACKHUB");
	JLabel lblUser = new JLabel("User");
	JLabel lblItemName = new JLabel("Item Name");
	JLabel lblItemType = new JLabel("Item Type");
	JLabel lblDescription = new JLabel("Description");
	JLabel lblValue = new JLabel("Value");
	JLabel lblCostPerDay = new JLabel("Cost per Day");
	
	newItemGUI(String name, int id){
		this.userName = name;
		this.userId = id;
		newItem();
		
	}
	

	public void newItem() {

		frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 871, 358);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		frame.setContentPane(contentPane);
		
		//TOP PANEL
		topPanel = new JPanel();
		contentPane.add(topPanel, BorderLayout.NORTH);
		gbl_topPanel = new GridBagLayout();
		gbl_topPanel.columnWidths = new int[] { 280, 280, 280, 0 };
		gbl_topPanel.rowHeights = new int[] { 14, 0 };
		gbl_topPanel.columnWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		gbl_topPanel.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		topPanel.setLayout(gbl_topPanel);
		
		//Title
		lblTitle.setFont(new Font("Serif", Font.BOLD, 30));
		gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.fill = GridBagConstraints.VERTICAL;
		gbc_lblTitle.insets = new Insets(0, 0, 0, 5);
		gbc_lblTitle.gridx = 1;
		gbc_lblTitle.gridy = 0;
		topPanel.add(lblTitle, gbc_lblTitle);
		
		//User
		lblUser.setText("Welcome: " + userName);
		gbc_lblUser = new GridBagConstraints();
		gbc_lblUser.anchor = GridBagConstraints.EAST;
		gbc_lblUser.fill = GridBagConstraints.VERTICAL;
		gbc_lblUser.gridx = 2;
		gbc_lblUser.gridy = 0;
		topPanel.add(lblUser, gbc_lblUser);
		
		//CENTER PANEL
		centerPanel = new JPanel();
		contentPane.add(centerPanel, BorderLayout.CENTER);
		gbl_centerPanel = new GridBagLayout();
		gbl_centerPanel.columnWidths = new int[] { 150, 180, 180, 150, 0 };
		gbl_centerPanel.rowHeights = new int[] { 14, 20, 0, 0, 0, 0, 0, 0 };
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
		
		//Item Type
		comboItemType.setBackground(Color.WHITE);
		comboItemType.setModel(
				new DefaultComboBoxModel<String>(new String[] { "Electricronic", "Non-Electricronic", "Book", "Digital" }));
		gbc_comboItemType = new GridBagConstraints();
		gbc_comboItemType.insets = new Insets(0, 0, 5, 5);
		gbc_comboItemType.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboItemType.gridx = 2;
		gbc_comboItemType.gridy = 2;
		centerPanel.add(comboItemType, gbc_comboItemType);
		
		//Item Description
		gbc_lblDescription = new GridBagConstraints();
		gbc_lblDescription.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescription.gridx = 1;
		gbc_lblDescription.gridy = 3;
		centerPanel.add(lblDescription, gbc_lblDescription);
		
		textAreaDescription.setToolTipText("Enter Item Description");
		textAreaDescription.setLineWrap(true);
		gbc_textAreaDescription = new GridBagConstraints();
		gbc_textAreaDescription.insets = new Insets(0, 0, 5, 5);
		gbc_textAreaDescription.fill = GridBagConstraints.HORIZONTAL;
		gbc_textAreaDescription.gridx = 2;
		gbc_textAreaDescription.gridy = 3;
		centerPanel.add(textAreaDescription, gbc_textAreaDescription);
		
		//Value
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
		
		//Label Cost per Day
		gbc_lblCostPerDay = new GridBagConstraints();
		gbc_lblCostPerDay.insets = new Insets(0, 0, 5, 5);
		gbc_lblCostPerDay.gridx = 1;
		gbc_lblCostPerDay.gridy = 5;
		centerPanel.add(lblCostPerDay, gbc_lblCostPerDay);
		
		//Cost per Day Field
		txtFieldCostPerDay = new JTextField();
		gbc_txtFieldCostPerDay = new GridBagConstraints();
		gbc_txtFieldCostPerDay.insets = new Insets(0, 0, 5, 5);
		gbc_txtFieldCostPerDay.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFieldCostPerDay.gridx = 2;
		gbc_txtFieldCostPerDay.gridy = 5;
		centerPanel.add(txtFieldCostPerDay, gbc_txtFieldCostPerDay);
		
		//Bottom Panel
		bottomPanel = new JPanel();
		contentPane.add(bottomPanel, BorderLayout.SOUTH);
		bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 150, 5));
		
		

		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(this);
		btnBack = new JButton("Back");
		btnBack.addActionListener(this);
		
		bottomPanel.add(btnSubmit);
		bottomPanel.add(btnBack);
		
		


	}
	
	public void addToDatabase(double value, double costPerDay ) {
		
		try {
			System.out.println("Item Registration");
			// Registering drivers
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());// Creating Connection Object
			System.out.println("Connecting to Database...");
			Connection connection = DriverManager
					.getConnection("jdbc:oracle:thin:OPS$2042387/P46919@ora-srv.wlv.ac.uk:1521/catdb.wlv.ac.uk");
			System.out.println("Connected with database");
			// Prepared Statement
			PreparedStatement Pstatement = connection
					.prepareStatement("insert into Items(Id, UserID, Name, Type, Description, Value, Borrow_Cost) values(seq_item.nextval,?,?,?,?,?,?)");
			// Specifying the values of it's parameter
			Pstatement.setInt(1, userId);
			System.out.println("userId: Done");
			Pstatement.setString(2, txtFieldItemName.getText());
			System.out.println("Item Name: Done");
			Pstatement.setString(3, comboItemType.getSelectedItem().toString());
			System.out.println("Item Type: Done");
			Pstatement.setString(4, textAreaDescription.getText());
			System.out.println("Description : Done");
			Pstatement.setDouble(5, value);
			System.out.println("Value: Done");
			Pstatement.setDouble(6, costPerDay);
			System.out.println("Cost per Day: Done");
			
			Pstatement.executeUpdate();

		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		
		
	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == btnSubmit) {
			try {
				value = Double.parseDouble(txtFieldValue.getText());
				System.out.println("Value: " + userName);
				costPerDay = Double.parseDouble(txtFieldCostPerDay.getText());
				System.out.println("Cost Per Day: " + costPerDay);
				addToDatabase(value, costPerDay );
			} catch(Exception error) {
				System.out.println("INVALID DATA\n Please check the Value and the Cost Per Day");			}
			
		} else if (e.getSource() == btnBack) {
			//Main Menu
			System.out.println("BackToMenu");
		}
	}
}
