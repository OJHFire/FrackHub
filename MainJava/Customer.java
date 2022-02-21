package FracHub;

import java.sql.*;

public class Customer {
	
	String url = "jdbc:oracle:thin:OPS$USERNAME/PASSWORD@ora-srv.wlv.ac.uk:1521/catdb.wlv.ac.uk";
	
	private Name name;
	private String cust_num;
	private String password;
	private String address;
	private String email;
	private String phone_num;
	
	public Customer() {
		
		name = new Name();
		cust_num = "";
		password = "";
		address = "";
		email = "";
		phone_num = "";
		
	}
	
	public Customer(Name name, String cust_num, String password, 
			String address, String email, String phone_num) {
		
		this.name = name;
		this.cust_num = cust_num;
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
		System.out.println("Customer Number: " + cust_num);
		System.out.println("Password: " + password);
	}
	
	public void saveCust() {
		
		String sql = ("INSERT into Member VALUES (PROJSEQ3.NEXTVAL,'" + name.getFirstName() + 
				"','" + name.getSurname() + "','" + address + "','" + phone_num + "','" + email + "','" + password + "')");
		
		System.out.println(sql);
		
		Connection con = null;
		
		try {
		       DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

		       con = DriverManager.getConnection(url);
		       
		       Statement stmt = con.createStatement();
		       
		       stmt.executeUpdate(sql);
		       
		       con.close();
		       
		   }

		catch (Exception ex) {
	
		       System.err.println(ex);
		   }
	}

	public Customer custSignIn(String custNum, String password) {
		
		String sql = ("SELECT * FROM Member WHERE cust_no = '" + custNum + "' AND password = '" + password + "'");
		
		System.out.println(sql);
		
		Connection con = null;
		
		Customer new_cust = new Customer();
		
		try {
		       DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

		       con = DriverManager.getConnection(url);
		       
		       Statement stmt = con.createStatement();
		       
		       ResultSet rs = stmt.executeQuery(sql);

		       while (rs.next()) {
		       String new_name = rs.getString("first_name");
		       String last_name = rs.getString("last_name");
		       String new_address = rs.getString("address");
		       String new_email = rs.getString("email");
		       String new_phone_num = rs.getString("contact_no");
		       
		       
		       new_cust = new Customer(new Name(new_name + " " + last_name), custNum, password, new_address, new_email, new_phone_num);
		       }
		       con.close();
		       
		   }

		catch (Exception ex) {
	
		       System.err.println(ex);
		   }
		
		return new_cust;
	}
	
	public String nextCustNum() {		
		
		String sql = ("SELECT projseq3.nextval from Member");
		String sql2 = ("SELECT projseq3.currval from Member");
		
		String cust_num5 = "";
		
		Connection con = null;
		
		try {
		       DriverManager.registerDriver(new oracle.jdbc.OracleDriver());

		       con = DriverManager.getConnection(url);
		       
		       Statement stmt = con.createStatement();
		       
		       ResultSet rs = stmt.executeQuery(sql);
		       //ResultSet rs2 = stmt.executeQuery(sql2);

		       while (rs.next()) {
		    	   cust_num5 = rs.getString("currval");   
		       }
		       
		       con.close();
		       
		   }

		catch (Exception ex) {
	
			System.err.println(ex);
			return "1000000";
		}
		
		return cust_num5;
	}
	
	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getCust_num() {
		return cust_num;
	}

	public void setCust_num(String cust_num) {
		this.cust_num = cust_num;
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
