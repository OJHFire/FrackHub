import javax.swing.*; //GUI Library
import java.awt.event.*; //AWT event handler
import java.awt.*; //API windows based application development
import java.sql.*; //API for accessing and processing data store in a database

public class Oracle_Login implements ActionListener {

	// Creating object of JFrame class
	JFrame frame;

	// Creating objects
	JLabel emailLabel = new JLabel("EMAIL");
	JLabel passwordLabel = new JLabel("PASSWORD");

	JTextField emailTextField = new JTextField();
	JPasswordField passwordField = new JPasswordField();

	JButton loginButton = new JButton("LOGIN");
	JButton resetButton = new JButton("RESET");

	String email;
	String password;
	String d_email;
	String d_password;

	Oracle_Login() {
		createWindow();
		setLocationAndSize();
		addComponentsToFrame();
		actionEvent();
	}

	// Creating user-defined method
	public void createWindow() {
		// Setting properties of JFrame
		frame = new JFrame();
		frame.setTitle("User Login");
		frame.setBounds(40, 40, 400, 300);
		frame.getContentPane().setBackground(Color.white);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	}

	public void setLocationAndSize() {
		// Setting Location and Size of Each Component
		emailLabel.setBounds(20, 20, 40, 70);

		passwordLabel.setBounds(20, 40, 80, 70);

		emailTextField.setBounds(180, 40, 165, 23);
		passwordField.setBounds(180, 64, 165, 23);

		loginButton.setBounds(60, 100, 80, 25);
		resetButton.setBounds(180, 100, 145, 25);
	}

	public void addComponentsToFrame() {
		// Adding components to Frame
		frame.add(emailLabel);
		frame.add(passwordLabel);

		frame.add(emailTextField);
		frame.add(passwordField);

		frame.add(loginButton);
		frame.add(resetButton);
	}

	public void actionEvent() {
		loginButton.addActionListener(this);
		resetButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			try {
				// Get the user details into variables
				email = emailTextField.getText();
				password = new String(passwordField.getPassword());

				// Connect to the Database
				DriverManager.registerDriver(new oracle.jdbc.OracleDriver());// Creating Connection Object
				System.out.println("Connecting to Database...");
				Connection connection = DriverManager
						.getConnection("jdbc:oracle:thin:OPS$2042387/P46919@ora-srv.wlv.ac.uk:1521/catdb.wlv.ac.uk");
				System.out.println("COnnected with database");
				// Statement
				Statement selectStmt = connection.createStatement();

				// Search for the Email and passwords in the Database
				ResultSet result_set = selectStmt.executeQuery("Select Email, Password From FrackHub_Test");
				while (result_set.next()) {
					d_email = result_set.getString(1); // Email Column
					System.out.println("Database email: " + result_set.getString(1));
					System.out.println("Email: " + email);

					// if the email matches
					if (d_email.equals(email)) {

						// Get the Password
						d_password = result_set.getString(2);// Password Column
						System.out.println("Database Pass: " + result_set.getString(2));
						System.out.println("Password: " + password);

						// If the Password matches
						if (d_password.equals(password)) {

							// Logged In
							System.out.println("LOGIN SUCCESSFULL!!");
							break;

						} else {

							// Incorrect Details
							System.out.println("Password INCORRECT!");
							break;

						}
					}
				}

			} catch (Exception e1) {
				e1.printStackTrace();
			}

		}
		if (e.getSource() == resetButton) {
			// Clearing Fields
			emailTextField.setText("");
			passwordField.setText("");

		}

	}

}
