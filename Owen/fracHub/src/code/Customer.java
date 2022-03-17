package code;

import java.sql.*;

import javax.swing.JPasswordField;

public class Customer {
	
	String url = "jdbc:oracle:thin:OPS$2042387/P46919@ora-srv.wlv.ac.uk:1521/catdb.wlv.ac.uk";
	
	Item[] item_list;
	
	private int cust_num;
	private Name name;
	private String password;
	private String address;
	private String email;
	private String phone_num;
	
	public Customer() {
		
		cust_num = 0;
		name = new Name();
		password = "";
		address = "";
		email = "";
		phone_num = "";
		
	}
	
	public Customer(Name name, String password, 
			String address, String email, String phone_num) {
		
		this.cust_num = 0;
		this.name = name;
		this.password = password;
		this.address = address;
		this.email = email;
		this.phone_num = phone_num;
		
	}
	
	public Customer(int cust_num, Name name, String address, String email, String phone_num) {
		
		this.cust_num = cust_num;
		this.name = name;
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
		
		String sql = ("INSERT into member VALUES (seq_member.nextval,'" + name.getFirstName() + 
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

	public Customer custSignIn(String custEmail, JPasswordField password) {
		
		String sql = ("SELECT * FROM member WHERE EMAIL = '" + custEmail + "' AND PASSWORD = '" + new String(password.getPassword()) + "'");
		
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
		    	   int new_cust_num = rs.getInt("id");
			       String new_name = rs.getString("name");
			       String last_name = rs.getString("surname");
			       String new_address = rs.getString("address");
			       String new_email = rs.getString("email");
			       String new_phone_num = rs.getString("contact_number");
			       
			       
			       new_cust = new Customer(new_cust_num, new Name(new_name + " " + last_name), 
			    		   					new_address, new_email, new_phone_num);
		       }
		       con.close();
		       
		   }

		catch (Exception ex) {
	
		       System.err.println(ex);
		   }
		
		return new_cust;
	}
	
	public Item[] viewItems() {
		
		String sql1 = ("SELECT COUNT(*) AS count FROM Items WHERE email = '" + email + "'");
		String sql2 = ("SELECT * FROM Items WHERE email = '" + email + "'");
		
		System.out.println(sql1);
		
		Connection con = null;
		
		int item_count = 0;
		
		try {
		       DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		       System.out.println("Connecting to Database...");
		       con = DriverManager.getConnection(url);
		       
		       Statement stmt = con.createStatement();
		       
		       ResultSet rs = stmt.executeQuery(sql1);

		       while (rs.next()) {
		    	   item_count = rs.getInt("count");
		       }
		       
		       item_list = new Item[item_count];
		       
		   }

		catch (Exception ex) {
	
		       System.err.println(ex);
		   }
		
		try {
		       DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		       System.out.println("Connecting to Database...");
		       con = DriverManager.getConnection(url);
		       
		       Statement stmt = con.createStatement();
		       
		       ResultSet rs = stmt.executeQuery(sql2);
		       
		       int i = 0;

		       while (rs.next()) {
		       int new_item_num = rs.getInt("item_num");
		       String new_name = rs.getString("name");
		       String new_description = rs.getString("description");
		       String new_type = rs.getString("type");
		       double new_value = rs.getDouble("value");
		       double new_daily_rate = rs.getDouble("daily_rate");
		       
		       Item new_item = new Item(new_item_num, this.cust_num, new_name, new_type, new_description, new_value, new_daily_rate);
		       item_list[i] = new_item;
		       i++;		    

		       }
		       
		   }

		catch (Exception ex) {
	
		       System.err.println(ex);
		   }
		
		return item_list;
		
	}
	
	public int emailIsUnique(){
		String sql = ("SELECT COUNT(*) AS count FROM member WHERE email = '" + email + "'");

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

	public int getCust_num() {
		return cust_num;
	}

	public void setCust_num(int cust_num) {
		this.cust_num = cust_num;
	}
	
	
}