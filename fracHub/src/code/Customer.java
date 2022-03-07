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
		       
		   }

		catch (Exception ex) {
	
		       System.err.println(ex);
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
		       
		   }

		catch (Exception ex) {
	
		       System.err.println(ex);
		   }
		
		return new_cust;
	}

	public int emailIsUnique(){
		String sql = ("SELECT COUNT(*) AS count FROM FrackHub_Test WHERE email = '" + email + "'");

		Connection con = null;
		int num = 2;
		try {
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			System.out.println("Connecting to Database...");
			con = DriverManager.getConnection(url);

			Statement stmt = con.createStatement();

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				num = rs.getInt("count");
			}
			System.out.println(num);

			con.close();
		}
		catch (Exception ex) {

			System.err.println(ex);
		}
		return num;
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