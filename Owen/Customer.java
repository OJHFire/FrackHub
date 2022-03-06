package code;

import java.sql.*;

public class Customer {
	
	String url = "jdbc:oracle:thin:OPS$2042387/P46919@ora-srv.wlv.ac.uk:1521/catdb.wlv.ac.uk";
	
	private Name name;
	private String password;
	private String address;
	private String email;
	private String phone_num;
	
	public Customer() {
		
		name = new Name();
		password = "";
		address = "";
		email = "";
		phone_num = "";
		
	}
	
	public Customer(Name name, String password, 
			String address, String email, String phone_num) {
		
		this.name = name;
		this.password = password;
		this.address = address;
		this.email = email;
		this.phone_num = phone_num;
		
	}
	
	public void printCust() {
		System.out.println("Name: " + name.getLastCommaFirst());
		System.out.println("Address: " + address);
		System.out.println("Email: " + email);
		System.out.println("Phone Number: " + phone_num);
		System.out.println("Password: " + password);
	}
	
	public void saveCust() {
		if(emailIsUnique(email)) {

			String sql = ("INSERT into FrackHub_Test VALUES (seq_person.nextval,'" + name.getFirstName() +
					"','" + name.getSurname() + "','" + address + "','" + phone_num + "','" + email + "','" + password + "')");

			System.out.println(sql);

			Connection con = null;

			try {
				DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
				System.out.println("Connecting to Database...");
				con = DriverManager.getConnection(url);

				Statement stmt = con.createStatement();

				stmt.executeUpdate(sql);

				con.close();

			} catch (Exception ex) {

				System.err.println(ex);
			}
		}else{
			System.out.println("ERROR: Email is not unique!");
		}
	}

	public Customer custSignIn(String custEmail, String password) {


		String sql = ("SELECT * FROM FrackHub_Test WHERE EMAIL = '" + custEmail + "' AND PASSWORD = '" + password + "'");

		System.out.println(sql);

		Connection con = null;

		Customer new_cust = new Customer();

		try {
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			System.out.println("Connecting to Database...");
			con = DriverManager.getConnection(url);

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				String new_name = rs.getString("name");
				String last_name = rs.getString("surname");
				String new_address = rs.getString("address");
				String new_email = rs.getString("email");
				String new_phone_num = rs.getString("contact_number");


				new_cust = new Customer(new Name(new_name + " " + last_name), password, new_address, new_email, new_phone_num);
			}
			con.close();

		} catch (Exception ex) {

			System.err.println(ex);
		}

		return new_cust;
	}

	public Boolean emailIsUnique(String userEmail){
		//not sure if this query is correct, sorry I'm not great at sql...
		String sql = ("SELECT email FROM FrackHub_Test");
		Connection con = null;
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		System.out.println("Connecting to Database...");
		con = DriverManager.getConnection(url);

		Statement stmt = con.createStatement();

		//create a result from the sql query
		ResultSet rs = stmt.execute(sql);

		//got the while loop thing from the official oracle docs
		//https://docs.oracle.com/javase/tutorial/jdbc/basics/retrieving.html

		//the while loop will run until the rs.next command can't continue
		while(rs.next()){
			//every rs.next the userEmail is compared to the one in the data set
			if (userEmail == rs.getString("email")){
				//if a match is found false is returned
				con.close();
				return false;
			}
		}
		//once rs.next stops it returns true as that means there is no matches
		con.close();
		return true;
	}
	
	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone_num() {
		return phone_num;
	}

	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}
	
	

}