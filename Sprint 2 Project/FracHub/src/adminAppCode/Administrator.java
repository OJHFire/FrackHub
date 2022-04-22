package adminAppCode;

import java.sql.*;

import javax.swing.JPasswordField;

/**
 * Contains details of administrator and the ability retrieve customer data.
 * 
 */

public class Administrator {
	
	String url = "jdbc:oracle:thin:OPS$2042387/P46919@ora-srv.wlv.ac.uk:1521/catdb.wlv.ac.uk";
	

	// Instance variables.
	private Name name;
	private String username;
	private String password;
	
	// Default constructor.
	public Administrator() {
		
		name = new Name();
		username = "";
		password = "";
		
	}
	
	// Parameterised constructor.
	public Administrator(Name name, String username, String password) {
		
		this.name = name;
		this.username = username;
		this.password = password;
		
	}
	

	// Function to find admin details from database with the username and password.
	public Administrator adminSignIn(String username, JPasswordField password) {
		Administrator new_admin;
		
		if ((username.equals("admin")) && (new String(password.getPassword()).equals("password"))) {
		
			       
			new_admin = new Administrator(new Name("Robin Warhurst"), "admin", "password"); 
		
		}
		else {
			new_admin = new Administrator();
		}


		return new_admin;
	}
	
	// Function to retrieve total number of customers from the database.
	public String[] custNo() {
		
		String[] custCount = new String[2];
		
		String sql1 = ("SELECT COUNT(*) AS count1 FROM memberv2");
		
		String sql2 = ("SELECT COUNT(*) AS count2 FROM itemsv2");
		
		Connection con = null;
		
		try {
		       DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		       System.out.println("Connecting to Database...");
		       con = DriverManager.getConnection(url);
		       
		       Statement stmt = con.createStatement();
		       
		       ResultSet rs1 = stmt.executeQuery(sql1);
		       
		       while (rs1.next()) {
		    	   
		    	   int count1 = rs1.getInt("count1");
		    	   custCount[0] = Integer.toString(count1);
		       }
		       
		       ResultSet rs2 = stmt.executeQuery(sql2);
		       
		       while (rs2.next()) {
		    	   
		    	   int count2 = rs2.getInt("count2");
		    	   custCount[1] = Integer.toString(count2);
		       }
		       
		       con.close();
		       
		   }

		catch (Exception ex) {
		       System.err.println(ex);
		       custCount[0] = "Error";
		   }
		
		return custCount;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	
}