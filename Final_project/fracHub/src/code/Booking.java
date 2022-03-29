package code;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Contains details of item bookings and the ability to save bookings to the database or check for clashes of dates.
 */

public class Booking {
	
	String url = "jdbc:oracle:thin:OPS$2042387/P46919@ora-srv.wlv.ac.uk:1521/catdb.wlv.ac.uk";
	
	// Instance variables.
	private int booking_num;
	private Item item;
	private int lender_num;
	private Customer borrower;
	private LocalDate start_date;
	private LocalDate end_date;
	private double total_cost;
	private int borrower_num;
	private int item_num;

	// Default constructor.
	public Booking() {
		
		booking_num = 0;
		item = new Item();
		lender_num = 0;
		borrower = new Customer();
		start_date = LocalDate.now();
		end_date = LocalDate.now();
		total_cost = 0.00;		
		
	}
	
	// Parameterised constructor without booking number.
	public Booking(Item item, int lender_num, Customer borrower, LocalDate start_date, LocalDate end_date, double total_cost) {
		
		booking_num = 0;
		this.item = item;
		this.lender_num = lender_num;
		this.borrower = borrower;
		this.start_date = start_date;
		this.end_date = end_date;
		this.total_cost = total_cost;		
		
	}
	
	// Parameterised constructor with booking number.
	public Booking(int booking_num, Item item, int lender_num, Customer borrower, LocalDate start_date, LocalDate end_date, double total_cost) {
		
		this.booking_num = booking_num;
		this.item = item;
		this.lender_num = lender_num;
		this.borrower = borrower;
		this.start_date = start_date;
		this.end_date = end_date;
		this.total_cost = total_cost;	
		
	}
	
	// Parameterised constructor with booking number and item number instead of item class.
	public Booking(int booking_num, int item_num, int lender_num, int borrower_num, LocalDate start_date, LocalDate end_date, double total_cost) {
		
		this.booking_num = booking_num;
		this.item_num = item_num;
		this.lender_num = lender_num;
		this.borrower_num = borrower_num;
		this.start_date = start_date;
		this.end_date = end_date;
		this.total_cost = total_cost;	
		
	}
	
	// Function to save booking onto database.
	public void saveBooking() {
		
		String sql = ("INSERT into Bookings VALUES (seq_booking.nextval," + item.getItem_num() + "," + 
						lender_num + "," + borrower.getCust_num() + ",'" + start_date.format(DateTimeFormatter.ofPattern("dd-MMM-uuuu")) + 
						"','" + end_date.format(DateTimeFormatter.ofPattern("dd-MMM-uuuu")) + "'," + total_cost + ")");
		
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

	// Function to check dates for booking are available for item.
	public boolean checkBooking(int itemNum, String date1, String date2) {
		
		String sql = ("SELECT COUNT(*) as count FROM bookings WHERE itemID = " + itemNum +
						" AND (('" + date1 + "' BETWEEN startdate AND enddate) OR ('"
						+ date2  + "' BETWEEN startdate AND enddate) OR (('"
						+ date1 + "' < startdate) and ('" + date2 + "' > enddate)))");
		
		int count = 0;
		
		System.out.println(sql);
		
		Connection con = null;
		
		try {
		       DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		       System.out.println("Connecting to Database...");
		       con = DriverManager.getConnection(url);
		       
		       Statement stmt = con.createStatement();
		       
		       ResultSet rs = stmt.executeQuery(sql);

		       while (rs.next()) {
		    	   count = rs.getInt("count");
		    	   
		    	   if (count > 0) {
		    		   
		    		   return false;
		    	   }
			      
		       }
		       con.close();
		       		       
		   }

		catch (Exception ex) {
	
		       System.err.println(ex);
		   }
		return true;
	}
	
	// Function to return all bookings in database for a given borrower.
	public Booking[] viewAllBookings(Customer cust) {
		
		String sql = ("SELECT * FROM bookings WHERE borrowerID = " + cust.getCust_num());
		
		Connection con = null;
		
		ArrayList<Booking> booking_list = new ArrayList<Booking>();
		
		try {
		       DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		       System.out.println("Connecting to Database...");
		       con = DriverManager.getConnection(url);
		       
		       Statement stmt = con.createStatement();
		  		       
		       ResultSet rs1 = stmt.executeQuery(sql);

		       while (rs1.next()) {

		    	   int new_order_id = rs1.getInt("ORDERID");
		    	   int new_user_id = rs1.getInt("USERID");
		    	   int new_item_id = rs1.getInt("ITEMID");
		    	   int new_borrower_id = rs1.getInt("BORROWERID");
		    	   Date new_start_date = rs1.getDate("STARTDATE");
		    	   Date new_end_date = rs1.getDate("ENDDATE");
		    	   double new_total_cost = rs1.getDouble("TOTALCOST");
		    	   
		    	   LocalDate start_date = LocalDate.parse(new_start_date.toString());
		    	   LocalDate end_date = LocalDate.parse(new_end_date.toString());
		    	   
		    	   Booking new_booking = new Booking(new_order_id, new_user_id, new_item_id, new_borrower_id,
		    			   				start_date, end_date, new_total_cost);
		    	   
		    	   booking_list.add(new_booking);   

		       }
		       
		   }

		catch (Exception ex) {
	
		       System.err.println(ex);
		   }
		
		Booking[] booking_list2 = booking_list.toArray(new Booking[booking_list.size()]);
		
		return booking_list2;
	}
	
	// Function to return all debits and credits for a customer for a year.
	public double[][] monthlyAccountBooking(int year, Customer cust) {
		
		double[][] monthlyDetails = new double[12][];
		
		String[] month_list = {"January", "February", "March", "April", "May", "June",
				"July", "August", "September", "October", "November", "December"};
					
		Connection con = null;
			
		try {
					
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			System.out.println("Connecting to Database...");
			con = DriverManager.getConnection(url);
			
			Statement stmt = con.createStatement();
			
			int i = 0;
			       
			for (String month : month_list) {
			    	   
				double ownItemsBookedCount = 0;
				double bookedItemsCount = 0;
				double totalDebit = 0;
				double totalCredit = 0;
				
				String sql1 = ("SELECT COUNT(*) as count FROM bookings WHERE (userID = " + cust.getCust_num() +
								" AND to_char(enddate,'fmMonth') = '" + month + "' AND EXTRACT(YEAR FROM enddate) = " + year + ")");
				
				String sql2 = ("SELECT COUNT(*) as count FROM bookings WHERE (borrowerID = " + cust.getCust_num() + 
								" AND to_char(enddate,'fmMonth') = '" + month + "' AND EXTRACT(YEAR FROM enddate) = " + year + ")");
				
				String sql3 = ("SELECT SUM(totalcost) AS total FROM bookings WHERE (borrowerID = " + cust.getCust_num() +
						" AND to_char(enddate,'fmMonth') = '" + month + "' AND EXTRACT(YEAR FROM enddate) = " + year + ")");
				
				String sql4 = ("SELECT SUM(totalcost) AS total FROM bookings WHERE (userID = " + cust.getCust_num() +
						" AND to_char(enddate,'fmMonth') = '" + month + "' AND EXTRACT(YEAR FROM enddate) = " + year + ")");
			       
		       ResultSet rs1 = stmt.executeQuery(sql1);

		       while (rs1.next()) {
		    	   ownItemsBookedCount = rs1.getInt("count");

		       }
		       
		       ResultSet rs2 = stmt.executeQuery(sql2);

		       while (rs2.next()) {
		    	   bookedItemsCount = rs2.getInt("count");

		       }
		       
		       ResultSet rs3 = stmt.executeQuery(sql3);

		       while (rs3.next()) {
		    	   totalDebit = rs3.getDouble("total");

		       }

		       ResultSet rs4 = stmt.executeQuery(sql4);

		       while (rs4.next()) {
		    	   totalCredit = rs4.getDouble("total");

		       }
		       
		       double[] count = {ownItemsBookedCount, bookedItemsCount, totalDebit, totalCredit};
		       monthlyDetails[i] = count;
		       i++;
		              
		   }
			con.close();
			
		}
		
		catch (Exception ex) {
		
			System.err.println(ex);
		}		
		
		return monthlyDetails;
	}

	public int getBooking_num() {
		return booking_num;
	}

	public void setBooking_num(int booking_num) {
		this.booking_num = booking_num;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public int getLenderNum() {
		return lender_num;
	}

	public void setLenderNum(int lender_num) {
		this.lender_num = lender_num;
	}

	public Customer getBorrower() {
		return borrower;
	}

	public void setBorrower(Customer borrower) {
		this.borrower = borrower;
	}

	public LocalDate getStart_date() {
		return start_date;
	}

	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}

	public LocalDate getEnd_date() {
		return end_date;
	}

	public void setEnd_date(LocalDate end_date) {
		this.end_date = end_date;
	}

	public double getTotal_cost() {
		return total_cost;
	}

	public void setTotal_cost(double total_cost) {
		this.total_cost = total_cost;
	}



}