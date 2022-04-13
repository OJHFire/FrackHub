package codeTest;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JPasswordField;

import org.junit.jupiter.api.Test;

import code.Customer;
import code.Item;

class ItemClassTest {

	// Test to ensure items are saved on database.
	@Test
	void testSaveItem() {
		
		String url = "jdbc:oracle:thin:OPS$2042387/P46919@ora-srv.wlv.ac.uk:1521/catdb.wlv.ac.uk";
		
		Connection con = null;
		
		// Create an item for customer 1111@google.com with saveItem function.
		Customer cust = new Customer();
		cust = cust.custSignIn("1111@google.com", new JPasswordField("1111"));
		
		Item item = new Item(1, "Grass Trimmer", "Garden", "Bosch EasyGrassCut 26 Corded - 280W", 28, 2.15);
		
		// Add item to items table.
		item.saveItem();

		int item_count = 0;
		
		try {
		       DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		       con = DriverManager.getConnection(url);
		       
		       // Query to find count of all bookings for the customer for item.
		       Statement stmt1 = con.createStatement();
		       
		       ResultSet rs = stmt1.executeQuery("SELECT COUNT(*) AS count FROM itemsv2 WHERE description = 'Bosch EasyGrassCut 26 Corded - 280W'");  

		       while (rs.next()) {
		    	   item_count = rs.getInt("count");
		       }	       
		       
		       // Execute deletion of all rows of item with matching description from items table.
		       Statement stmt2 = con.createStatement();
		       
		       stmt2.executeUpdate("DELETE FROM itemsv2 WHERE description = 'Bosch EasyGrassCut 26 Corded - 280W'");
		       
		       con.close();
		       
		   }

		catch (Exception ex) {
	
		       System.err.println(ex);
		   }
		
		// Check there is exactly 1 count of item when added.
		assertEquals(1, item_count);

	}

}
