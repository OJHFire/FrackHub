package Test;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Test_Oracle_Database implements ActionListener {
	// Creating object of JFrame class
	JFrame frame;

	// Creating objects
	JLabel nameLabel = new JLabel("NAME");

	JLabel surnameLabel = new JLabel("Surname");
	JLabel contactNumberLabel = new JLabel("Contact Number");
	JLabel addressLabel = new JLabel("Address");
	JLabel emailLabel = new JLabel("EMAIL");
	JLabel passwordLabel = new JLabel("PASSWORD");

	JTextField nameTextField = new JTextField();

	JTextField surnameTextField = new JTextField();
	JTextField contactNumberTextField = new JTextField();
	JTextField addressTextField = new JTextField();
	JTextField emailTextField = new JTextField();
	JPasswordField passwordField = new JPasswordField();

	JButton registerButton = new JButton("REGISTER");
	JButton resetButton = new JButton("RESET");

	Test_Oracle_Database() {
		createWindow();
		setLocationAndSize();
		addComponentsToFrame();
		actionEvent();
	}

	// Creating user-defined method
	public void createWindow() {
		// Setting properties of JFrame
		frame = new JFrame();
		frame.setTitle("User Regiostration");
		frame.setBounds(40, 40, 380, 600);
		frame.getContentPane().setBackground(Color.white);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	}

	public void setLocationAndSize() {
		// Setting Location and Size of Each Component
		nameLabel.setBounds(20, 20, 40, 70);

		surnameLabel.setBounds(20, 60, 100, 70);
		addressLabel.setBounds(20, 120, 100, 70);
		contactNumberLabel.setBounds(20, 270, 100, 70);
		emailLabel.setBounds(20, 320, 100, 70);
		passwordLabel.setBounds(20, 170, 100, 70);

		nameTextField.setBounds(180, 43, 165, 23);
		surnameTextField.setBounds(180, 70, 165, 23);
		addressTextField.setBounds(180, 143, 165, 23);
		contactNumberTextField.setBounds(180, 293, 165, 23);
		emailTextField.setBounds(180, 343, 165, 23);
		passwordField.setBounds(180, 193, 165, 23);

		registerButton.setBounds(70, 400, 100, 35);
		resetButton.setBounds(220, 400, 100, 35);
	}

	public void addComponentsToFrame() {
		// Adding components to Frame
		frame.add(nameLabel);

		frame.add(surnameLabel);
		frame.add(addressLabel);
		frame.add(contactNumberLabel);
		frame.add(emailLabel);
		frame.add(passwordLabel);

		frame.add(nameTextField);
		frame.add(surnameTextField);
		frame.add(addressTextField);
		frame.add(contactNumberTextField);
		frame.add(emailTextField);
		frame.add(passwordField);

		frame.add(registerButton);
		frame.add(resetButton);
	}

	public void actionEvent() {
		registerButton.addActionListener(this);
		resetButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == registerButton) {
			try {
				System.out.println("Register");
				// Registering drivers
				DriverManager.registerDriver(new oracle.jdbc.OracleDriver());// Creating Connection Object
				System.out.println("Connecting to Database...");
				Connection connection = DriverManager
						.getConnection("jdbc:oracle:thin:OPS$2042387/P46919@ora-srv.wlv.ac.uk:1521/catdb.wlv.ac.uk");
				System.out.println("COnnected with database");
				// Prepared Statement
				PreparedStatement Pstatement = connection
						.prepareStatement("insert into MemberV2(Id, Name, Surname, Address, Contact_Number, Email, Password) values(seq_member.nextval,?,?,?,?,?,STANDARD_HASH(?,'SHA1'))");
				// Specifying the values of it's parameter
				Pstatement.setString(1, nameTextField.getText());
				System.out.println("Name: Done");
				Pstatement.setString(2, surnameTextField.getText());
				System.out.println("SurName: Done");
				Pstatement.setString(3, addressTextField.getText());
				System.out.println("Address: Done");
				Pstatement.setString(4, contactNumberTextField.getText());
				System.out.println("Num: Done");
				Pstatement.setString(5, emailTextField.getText());
				System.out.println("Email: Done");
				Pstatement.setString(6, new String(passwordField.getPassword()));
				System.out.println("Pass: Done");
				
				Pstatement.executeUpdate();

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
		if (e.getSource() == resetButton) {
			// Clearing Fields
			nameTextField.setText("");
			
		}

	}
}
