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
	private int item_num;
	private int lender_num;
	private int borrower_num;
	private LocalDate start_date;
	private LocalDate end_date;
	private double total_cost;
	private String item_name;	
	

	// Default constructor.
	public Booking() {
		
		booking_num = 0;
		item_num = 0;
		lender_num = 0;
		borrower_num = 0;
		start_date = LocalDate.now();
		end_date = LocalDate.now();
		total_cost = 0.00;	
		item_name = "";
		
	}
	
	// Parameterised constructor without booking number.
	public Booking(int item_num, int lender_num, int borrower_num, LocalDate start_date, LocalDate end_date, double total_cost, String item_name) {
		
		this.item_num = item_num;
		this.lender_num = lender_num;
		this.borrower_num = borrower_num;
		this.start_date = start_date;
		this.end_date = end_date;
		this.total_cost = total_cost;	
		this.item_name = item_name;
		
	}
	// Parameterised constructor with booking number.
	public Booking(int booking_num, int item_num, int lender_num, int borrower_num, LocalDate start_date, LocalDate end_date, double total_cost, String item_name) {
		
		this.booking_num = booking_num;
		this.item_num = item_num;
		this.lender_num = lender_num;
		this.borrower_num = borrower_num;
		this.start_date = start_date;
		this.end_date = end_date;
		this.total_cost = total_cost;	
		this.item_name = item_name;
		
	}
	
	// Function to save booking onto database.
	public boolean saveBooking() {
		
		String sql = ("INSERT into Bookingsv2 VALUES (seq_booking.nextval," + item_num + "," + 
						lender_num + "," + borrower_num + ",'" + start_date.format(DateTimeFormatter.ofPattern("dd-MMM-uuuu")) + 
						"','" + end_date.format(DateTimeFormatter.ofPattern("dd-MMM-uuuu")) + "'," + total_cost + ",'" + item_name + "')");
		
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
		       return false;
		   }
		
		return true;
	}
	
	// Function to delete booking from database.
	public boolean deleteBooking() {
		
		Connection con = null;
			
		try {
					
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			System.out.println("Connecting to Database...");
			con = DriverManager.getConnection(url);
			
			Statement stmt = con.createStatement();
			
			String sql = ("DELETE Bookingsv2 WHERE orderid = " + this.getBooking_num());
			System.out.println(sql);
				
		    stmt.executeUpdate(sql);
		     
			con.close();
			
		}
		
		catch (Exception ex) {
		
			System.err.println(ex);
			return false;
		}		
		return true;
	}

	// Function to check dates for booking are available for item.
	public int checkBooking(int itemNum, String date1, String date2) {
		
		int checkBooking = 0;
		
		String sql = ("SELECT COUNT(*) as count FROM bookingsv2 WHERE itemID = " + itemNum +
						" AND (('" + date1 + "' BETWEEN startdate AND enddate) OR ('"
						+ date2  + "' BETWEEN startdate AND enddate) OR (('"
						+ date1 + "' < startdate) and ('" + date2 + "' > enddate)))");
		
		int count = 99;
		
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
		    	   
		    	   if (count == 0) {
		    		   
		    		   checkBooking = 1;
		    	   }
			      
		       }
		       con.close();
		       		       
		   }

		catch (Exception ex) {
	
		       System.err.println(ex);
		       checkBooking = 3;
		   }
		return checkBooking;
	}
	
	// Function to return all bookings on the database for a given borrower in a given year.
	public Booking[][] viewAllBookings(int year, Customer cust) {
		
		ArrayList<Booking> booking_list;
		Booking[][] monthlyBooking;
		
		String[] month_list = {"January", "February", "March", "April", "May", "June",
				"July", "August", "September", "October", "November", "December"};
		
		Connection con = null;
		
		try {
		       DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		       System.out.println("Connecting to Database...");
		       con = DriverManager.getConnection(url);
		       
		       Statement stmt = con.createStatement();
		       
		       monthlyBooking = new Booking[12][];
		       int i = 0;
		       
		       for (String month : month_list) {
		    	   
		    	   booking_list = new ArrayList<Booking>();
		    	   
		    	   String sql = ("SELECT * FROM bookingsv2 WHERE (borrowerID = " + cust.getCust_num() +
		    			   " AND to_char(startdate,'fmMonth') = '" + month + "' AND EXTRACT(YEAR FROM startdate) = " + year + 
		    			   ") ORDER BY startdate");
		  		       
			       ResultSet rs1 = stmt.executeQuery(sql);
	
			       while (rs1.next()) {
	
			    	   int new_order_id = rs1.getInt("ORDERID");
			    	   int new_user_id = rs1.getInt("USERID");
			    	   int new_item_id = rs1.getInt("ITEMID");
			    	   int new_borrower_id = rs1.getInt("BORROWERID");
			    	   Date new_start_date = rs1.getDate("STARTDATE");
			    	   Date new_end_date = rs1.getDate("ENDDATE");
			    	   double new_total_cost = rs1.getDouble("TOTALCOST");
			    	   String new_item_name = rs1.getString("ITEMNAME");
			    	   
			    	   LocalDate start_date = LocalDate.parse(new_start_date.toString());
			    	   LocalDate end_date = LocalDate.parse(new_end_date.toString());
			    	   
			    	   Booking new_booking = new Booking(new_order_id, new_user_id, new_item_id, new_borrower_id,
			    			   				start_date, end_date, new_total_cost, new_item_name);
			    	   
			    	   booking_list.add(new_booking);   

			       }		
			       Booking[] booking_list2 = booking_list.toArray(new Booking[booking_list.size()]);
			       monthlyBooking[i] = booking_list2;
			       i++;
		       }	
		       con.close();
		   }

		catch (Exception ex) {
	
		       System.err.println(ex);
		       monthlyBooking = new Booking[1][];
		   }
		
		return monthlyBooking;
	}
	
	// Function to return all bookings on the database for customer items in a given year
	public Booking[][] viewAllMyItemBookings(int year, Customer cust) {
		
		ArrayList<Booking> booking_list;
		Booking[][] monthlyBooking;
		
		String[] month_list = {"January", "February", "March", "April", "May", "June",
				"July", "August", "September", "October", "November", "December"};
		
		Connection con = null;
		
		try {
		       DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		       System.out.println("Connecting to Database...");
		       con = DriverManager.getConnection(url);
		       
		       Statement stmt = con.createStatement();
		       
		       monthlyBooking = new Booking[12][];
		       int i = 0;
		       
		       for (String month : month_list) {
		    	   
		    	   booking_list = new ArrayList<Booking>();
		    	   
		    	   String sql = ("SELECT * FROM bookingsv2 WHERE (userID = " + cust.getCust_num() +
		    			   " AND to_char(startdate,'fmMonth') = '" + month + "' AND EXTRACT(YEAR FROM startdate) = " + year + 
		    			   ") ORDER BY startdate");
		  		       
			       ResultSet rs1 = stmt.executeQuery(sql);
	
			       while (rs1.next()) {
	
			    	   int new_order_id = rs1.getInt("ORDERID");
			    	   int new_user_id = rs1.getInt("USERID");
			    	   int new_item_id = rs1.getInt("ITEMID");
			    	   int new_borrower_id = rs1.getInt("BORROWERID");
			    	   Date new_start_date = rs1.getDate("STARTDATE");
			    	   Date new_end_date = rs1.getDate("ENDDATE");
			    	   double new_total_cost = rs1.getDouble("TOTALCOST");
			    	   String new_item_name = rs1.getString("ITEMNAME");
			    	   
			    	   LocalDate start_date = LocalDate.parse(new_start_date.toString());
			    	   LocalDate end_date = LocalDate.parse(new_end_date.toString());
			    	   
			    	   Booking new_booking = new Booking(new_order_id, new_user_id, new_item_id, new_borrower_id,
			    			   				start_date, end_date, new_total_cost, new_item_name);
			    	   
			    	   booking_list.add(new_booking);   

			       }		
			       Booking[] booking_list2 = booking_list.toArray(new Booking[booking_list.size()]);
			       monthlyBooking[i] = booking_list2;
			       i++;
		       }	
		       con.close();
		   }

		catch (Exception ex) {
	
		       System.err.println(ex);
		       monthlyBooking = new Booking[1][];
		   }
		
		return monthlyBooking;
	}
	
	// Function to return all bookings on the database for customer items.
	public ItemResult viewAllMyItemBookings2(int year, Customer cust) {
		
		ArrayList<String> name_list = new ArrayList<String>();
		ArrayList<Booking> booking_list = new ArrayList<Booking>();
		Booking[][] monthlyBooking;
		String[] name_list2;
		
		String[] month_list = {"January", "February", "March", "April", "May", "June",
				"July", "August", "September", "October", "November", "December"};
		
		Connection con = null;
		
		try {
		       DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		       System.out.println("Connecting to Database...");
		       con = DriverManager.getConnection(url);
		       
		       Statement stmt = con.createStatement();
		       
		       
		       monthlyBooking = new Booking[12][];
		       int i = 0;
		       
		       String sql1 = ("SELECT distinct itemname FROM bookingsv2 WHERE userid = " + cust.getCust_num() + " ORDER BY itemname ASC");
		       
		       ResultSet rs1 = stmt.executeQuery(sql1);

		       while (rs1.next()) {

		    	   String new_name = rs1.getString("itemname");
		    	   
		    	   name_list.add(new_name);   

		       }
		       
		       for (String month : month_list) {
		    	   
		    	   String sql2 = ("SELECT * FROM bookingsv2 WHERE userID = " + cust.getCust_num() + " ORDER BY startdate");
		    	   
		    	   System.out.println(month);
		  		       
			       ResultSet rs2 = stmt.executeQuery(sql2);
	
			       while (rs2.next()) {
	
			    	   int new_order_id = rs1.getInt("ORDERID");
			    	   int new_user_id = rs1.getInt("USERID");
			    	   int new_item_id = rs1.getInt("ITEMID");
			    	   int new_borrower_id = rs1.getInt("BORROWERID");
			    	   Date new_start_date = rs1.getDate("STARTDATE");
			    	   Date new_end_date = rs1.getDate("ENDDATE");
			    	   double new_total_cost = rs1.getDouble("TOTALCOST");
			    	   String new_item_name = rs1.getString("ITEMNAME");
			    	   
			    	   LocalDate start_date = LocalDate.parse(new_start_date.toString());
			    	   LocalDate end_date = LocalDate.parse(new_end_date.toString());
			    	   
			    	   Booking new_booking = new Booking(new_order_id, new_user_id, new_item_id, new_borrower_id,
			    			   				start_date, end_date, new_total_cost, new_item_name);
			    	   
			    	   booking_list.add(new_booking);   
			    	   System.out.println(new_booking.borrower_num);

			       }		
			       Booking[] booking_list2 = booking_list.toArray(new Booking[booking_list.size()]);
			       monthlyBooking[i] = booking_list2;
			       i++;
		       }	
		       con.close();
		   }

		catch (Exception ex) {
	
		       System.err.println(ex);
		       monthlyBooking = new Booking[1][];
		   }
		name_list2 = name_list.toArray(new String[0]);
		ItemResult result = new ItemResult(name_list2, monthlyBooking);
		
		return result;
	}
	
	// Function to return all debits and credits for a customer for a year.
	public double[][] monthlyAccountBooking(int year, Customer cust) {
		
		double[][] monthlyDetails;
		
		String[] month_list = {"January", "February", "March", "April", "May", "June",
				"July", "August", "September", "October", "November", "December"};
					
		Connection con = null;
			
		try {
					
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			System.out.println("Connecting to Database...");
			con = DriverManager.getConnection(url);
			
			Statement stmt = con.createStatement();
			
			int i = 0;
			monthlyDetails = new double[12][];
			       
			for (String month : month_list) {
			    	   
				double ownItemsBookedCount = 0;
				double bookedItemsCount = 0;
				double totalDebit = 0;
				double totalCredit = 0;
				
				String sql1 = ("SELECT COUNT(*) as count FROM bookingsv2 WHERE (userID = " + cust.getCust_num() +
								" AND to_char(enddate,'fmMonth') = '" + month + "' AND EXTRACT(YEAR FROM enddate) = " + year + ")");
				
				String sql2 = ("SELECT COUNT(*) as count FROM bookingsv2 WHERE (borrowerID = " + cust.getCust_num() + 
								" AND to_char(enddate,'fmMonth') = '" + month + "' AND EXTRACT(YEAR FROM enddate) = " + year + ")");
				
				String sql3 = ("SELECT SUM(totalcost) AS total FROM bookingsv2 WHERE (borrowerID = " + cust.getCust_num() +
						" AND to_char(enddate,'fmMonth') = '" + month + "' AND EXTRACT(YEAR FROM enddate) = " + year + ")");
				
				String sql4 = ("SELECT SUM(totalcost) AS total FROM bookingsv2 WHERE (userID = " + cust.getCust_num() +
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
			monthlyDetails = new double[1][];
		}		
		
		return monthlyDetails;
	}
	
	public class ItemResult {
		
		String[] name_list;
		Booking[][] booking_list;
		
		public ItemResult(String[] name_list ,Booking[][] booking_list) {
			this.name_list = name_list;
			this.booking_list = booking_list;
		}
	}

	public int getBooking_num() {
		return booking_num;
	}

	public void setBooking_num(int booking_num) {
		this.booking_num = booking_num;
	}

	public int getItem_num() {
		return item_num;
	}

	public void setItem_num(int item_num) {
		this.item_num = item_num;
	}

	public int getLender_num() {
		return lender_num;
	}

	public void setLender_num(int lender_num) {
		this.lender_num = lender_num;
	}

	public int getBorrower_num() {
		return borrower_num;
	}

	public void setBorrower_num(int borrower_num) {
		this.borrower_num = borrower_num;
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

	public String getItem_name() {
		return item_name;
	}

	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	
}