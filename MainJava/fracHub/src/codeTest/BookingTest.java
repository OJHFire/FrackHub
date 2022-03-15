package codeTest;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import javax.swing.JPasswordField;

import org.junit.jupiter.api.Test;

import code.Customer;
import code.Item;
import code.Booking;

class BookingTest {
	
	String url = "jdbc:oracle:thin:OPS$2042387/P46919@ora-srv.wlv.ac.uk:1521/catdb.wlv.ac.uk";
	
	Connection con = null;

	// Test to check booking has been added to the database.
	@Test
	void testSaveBooking() {
		
		// Create a booking for customer 1111@google.com with saveBooking function.
		Customer cust = new Customer();
		cust = cust.custSignIn("1111@google.com", new JPasswordField("1111"));
		
		LocalDate startDate = LocalDate.of(2022,11,23);
		LocalDate endDate = LocalDate.of(2022,11,24);
		
		Item item = new Item(999999, 1, "Grass Trimmer", "Garden", "Bosch EasyGrassCut 26 Corded - 280W", 28, 2.15);
		Booking booking = new Booking(111111, item, 1, cust, startDate, endDate, 4.30);
		
		 // Add item 999999 to items table.
		try {
		       DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		       con = DriverManager.getConnection(url);
		       
		      
		       Statement stmt = con.createStatement();
		       
		       stmt.executeUpdate("INSERT into Items VALUES (999999, 1, 'Grass Trimmer', 'Garden', 'Bosch EasyGrassCut 26 Corded - 280W', 28, 2.15)");
		       
		       con.close();
		       
		   }

		catch (Exception ex) {
	
		       System.err.println(ex);
		   }
		
		// Add a booking using the saveBooking function.
		booking.saveBooking();
		
		int booking_count = 0;
		
		try {
		       DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		       con = DriverManager.getConnection(url);
		       
		       // Query to find count of all bookings for the customer for item 999999.
		       Statement stmt1 = con.createStatement();
		       
		       ResultSet rs = stmt1.executeQuery("SELECT COUNT(*) AS count FROM bookings WHERE borrowerid = 4 AND itemid = 999999");  

		       while (rs.next()) {
		    	   booking_count = rs.getInt("count");
		       }	       
		       
		       // Execute deletion of all rows with booking of item 999999 and delete item from items.
		       Statement stmt2 = con.createStatement();
		       Statement stmt3 = con.createStatement();
		       
		       stmt2.executeUpdate("DELETE FROM bookings WHERE itemid = 999999");		
		       stmt3.executeUpdate("DELETE FROM items WHERE id = 999999");
		       
		       con.close();
		       
		   }

		catch (Exception ex) {
	
		       System.err.println(ex);
		   }
		
		// Check there is exactly 1 count of booking when booking added.
		assertEquals(1, booking_count);

	}

	// Test for checkBooking function to check any clashes of dates.
	@Test
	void testCheckBooking() {
		
		// Add item 999999 to item table.
		try {
		       DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		       con = DriverManager.getConnection(url);
		       
		       // Update to add item 999999 to items table.
		       Statement stmt = con.createStatement();
		       
		       stmt.executeUpdate("INSERT into Items VALUES (999999, 1, 'Grass Trimmer', 'Garden', 'Bosch EasyGrassCut 26 Corded - 280W', 28, 2.15)");
		       
		       con.close();
		       
		   }

		catch (Exception ex) {
	
		       System.err.println(ex);
		   }
		
		
		//Test check booking gives true if item not already booked on dates.
		Booking booking2 = new Booking();
		boolean functionCheck = booking2.checkBooking(999999, "20-dec-2022", "25-dec-2022");
		
		// Check for true if there are no bookings.
		assertEquals(true, functionCheck);
		
		// Add booking for date 23-dec-2022.
		try {
		       DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		       con = DriverManager.getConnection(url);
		       
		       Statement stmt = con.createStatement();
		       
		       stmt.executeUpdate("INSERT into Bookings VALUES (seq_booking.nextval,999999,1,4,'23-dec-2022','23-dec-2022',2.15)");	
		       
		       con.close();
		       
		   }

		catch (Exception ex) {
	
		       System.err.println(ex);
		   }
		
		//Test checkBooking is false when the booking dates overlap.
		functionCheck = booking2.checkBooking(999999, "20-dec-2022", "25-dec-2022");
		assertEquals(false, functionCheck);
		
		// Remove booking and item from tables.
		try {
		       DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		       con = DriverManager.getConnection(url);
		       
		       		       
		       // Execute deletion of all rows with booking of item 999999 and delete item from items.
		       Statement stmt1 = con.createStatement();
		       Statement stmt2 = con.createStatement();
		       
		       stmt1.executeUpdate("DELETE FROM bookings WHERE itemid = 999999");		
		       stmt2.executeUpdate("DELETE FROM items WHERE id = 999999");
		       
		       con.close();
		       
		   }

		catch (Exception ex) {
	
		       System.err.println(ex);
		   }
		

	}

}
