package codeTest;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JPasswordField;

import org.junit.jupiter.api.Test;

import code.*;

class CustomerClassTest {
	
	String url = "jdbc:oracle:thin:OPS$2042387/P46919@ora-srv.wlv.ac.uk:1521/catdb.wlv.ac.uk";
	
	Connection con = null;

	// Test to save a new customer and confirm it is in the database.
	@Test
	void testSaveCust() {

			// Create and save a new customer.
			Name new_name = new Name("Billy Idol");
			Customer new_cust = new Customer(new_name, "Password123", "12 White Wedding Street"
											, "RebelYell@hotmail.com", "07756468712");
			
			new_cust.saveCust();
			
			int email_count = 0;
			
			try {
			       DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			       con = DriverManager.getConnection(url);
			       
			       // Query to find count of all emails matching 'RebelYell@hotmail.com',
			       Statement stmt = con.createStatement();
			       
			       ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS count FROM member WHERE email = 'RebelYell@hotmail.com'");  

			       while (rs.next()) {
			    	   email_count = rs.getInt("count");
			       }	       
			       
			       // Execute deletion of all rows with email 'RebelYell@hotmail.com'.
			       Statement stmt2 = con.createStatement();
			       
			       stmt2.executeUpdate("DELETE FROM member WHERE email = 'RebelYell@hotmail.com'");			       
			       
			       con.close();
			       
			   }

			catch (Exception ex) {
		
			       System.err.println(ex);
			   }
			
			// Check there is exactly 1 count of email when customer added.
			assertEquals(1, email_count);

	}

	// Test to check sign in will get the correct customer information from the database.
	@Test
	void testCustSignIn() {
		
		// Create and save a new customer.
		Name new_name = new Name("Billy Idol");
		Customer new_cust = new Customer(new_name, "Password123", "12 White Wedding Street"
										, "RebelYell@hotmail.com", "07756468712");
		
		new_cust.saveCust();
		
		Customer test_cust = new Customer();

		// Sign in with email and password.
		test_cust = test_cust.custSignIn("RebelYell@hotmail.com", new JPasswordField("Password123"));
		
		// Check all the customer details are the same.
		assertEquals(test_cust.getName().getFullName(), new_cust.getName().getFullName());
		assertEquals(test_cust.getAddress(), new_cust.getAddress());
		assertEquals(test_cust.getEmail(), new_cust.getEmail());
		assertEquals(test_cust.getPhone_num(), new_cust.getPhone_num());
		
		// Execute deletion of all rows with email 'RebelYell@hotmail.com'.
		try {
		       DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		       con = DriverManager.getConnection(url);

		       Statement stmt = con.createStatement();
		       
		       stmt.executeUpdate("DELETE FROM member WHERE email = 'RebelYell@hotmail.com'");			       
		       
		       con.close();
		       
		   }

		catch (Exception ex) {
	
		       System.err.println(ex);
		   }
		
	}
	
	// Test to check if there is a duplicate email.
		@Test
		void testEmailIsUnique() {
			
			// Create and save a new customer.
			Name new_name = new Name("Billy Idol");
			Customer new_cust = new Customer(new_name, "Password123", "12 White Wedding Street"
											, "RebelYell@hotmail.com", "07756468712");
			
			new_cust.saveCust();
			
			Name test_new_name = new Name("John Williams");
			Customer test_new_cust = new Customer(test_new_name, "Password96", "45 Tatooine Lane"
											, "RebelYell@hotmail.com", "07766611622");

			// Find number of identical emails in database.
			int num_emails = test_new_cust.emailIsUnique();
			
			// Check email is already in database.
			assertEquals(num_emails, 1);
			
			// Execute deletion of all rows with email 'RebelYell@hotmail.com'.
			try {
			       DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			       con = DriverManager.getConnection(url);

			       Statement stmt = con.createStatement();
			       
			       stmt.executeUpdate("DELETE FROM member WHERE email = 'RebelYell@hotmail.com'");			       
			       
			       con.close();
			       
			   }

			catch (Exception ex) {
		
			       System.err.println(ex);
			   }
			
		}

}
