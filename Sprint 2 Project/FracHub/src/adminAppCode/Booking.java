package adminAppCode;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
/**
 * Contains details of item bookings and the ability to retrieve all bookings for start date or end date and retrieve booking data.
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

	// Function to return all bookings on the database for a given date.
	public String[][] viewAllBookingDeliveries(LocalDate date, Administrator admin) {
		
		ArrayList<String[]> booking_list_delivery = new ArrayList<String[]>();
		String[][] dailyBookingDelivery;
		
		Connection con = null;
		
		try {
		       DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		       System.out.println("Connecting to Database...");
		       con = DriverManager.getConnection(url);
		       
		       Statement stmt = con.createStatement();
	    	   
	    	   String sql = ("SELECT itemname, itemid, orderid, a.address as Owner_Address, b.address as Borrower_Address "
	    	   		+ "from memberv2 a join bookingsv2 on a.id = userid join memberv2 b on b.id = borrowerid "
	    	   		+ "where startdate = '" + date.format(DateTimeFormatter.ofPattern("dd-MMM-uuuu")) + "'");
	    	   
	    	   System.out.println(sql);
	  		       
		       ResultSet rs = stmt.executeQuery(sql);

		       while (rs.next()) {
		    	   	
		    	   int new_order_id = rs.getInt("ORDERID");
		    	   int new_item_id = rs.getInt("ITEMID");
		    	   String new_item_name = rs.getString("ITEMNAME");
		    	   String new_owner_address = rs.getString("Owner_Address");
		    	   String new_borrower_address = rs.getString("Borrower_Address");
		    	   
		    	   String[] booking = {new_item_name, Integer.toString(new_item_id), Integer.toString(new_order_id),
		    			   				new_owner_address, new_borrower_address};
		    	   booking_list_delivery.add(booking);
		       }	
		       
		       con.close();
		   }

		catch (Exception ex) {
	
		       System.err.println(ex);
		       String[] booking = {"Error"};
		       booking_list_delivery.add(booking);

		   }
		
		dailyBookingDelivery = booking_list_delivery.toArray(new String[booking_list_delivery.size()][5]);

		return dailyBookingDelivery;
	}
	
	public String[][] viewAllBookingReturns(LocalDate date, Administrator admin) {
		
		ArrayList<String[]> booking_list_return = new ArrayList<String[]>();
		String[][] dailyBookingReturn;
		
		Connection con = null;
		
		try {
		       DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		       System.out.println("Connecting to Database...");
		       con = DriverManager.getConnection(url);
		       
		       Statement stmt = con.createStatement();
		    	   	       
		       String sql = ("SELECT itemname, itemid, orderid, a.address as Owner_Address, b.address as Borrower_Address "
		    	   		+ "from memberv2 a join bookingsv2 on a.id = userid join memberv2 b on b.id = borrowerid "
		    	   		+ "where enddate = '" + date.format(DateTimeFormatter.ofPattern("dd-MMM-uuuu")) + "'");
		       
		       ResultSet rs = stmt.executeQuery(sql);

		       while (rs.next()) {
		    	   	
		    	   int new_order_id = rs.getInt("ORDERID");
		    	   int new_item_id = rs.getInt("ITEMID");
		    	   String new_item_name = rs.getString("ITEMNAME");
		    	   String new_owner_address = rs.getString("Owner_Address");
		    	   String new_borrower_address = rs.getString("Borrower_Address");
		    	   
		    	   String[] booking = {new_item_name, Integer.toString(new_item_id), Integer.toString(new_order_id),
		    			   				new_borrower_address, new_owner_address};
		    	   booking_list_return.add(booking);
		       }	
		       
		       con.close();
		   }

		catch (Exception ex) {
	
		       System.err.println(ex);
		       String[] booking = {"Error"};
		       booking_list_return.add(booking);

		   }
		
		dailyBookingReturn = booking_list_return.toArray(new String[booking_list_return.size()][5]);
		return dailyBookingReturn;
	}

	// Function to return information about bookings.
	public String[] bookingsInfo() {
		
		String[] bookingData = new String[3];
		
		Connection con = null;
		
		try {
		       DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		       System.out.println("Connecting to Database...");
		       con = DriverManager.getConnection(url);
		       
		       Statement stmt = con.createStatement();
		       
		       String sql1 = ("SELECT COUNT(*) as count, SUM(totalcost) as costsum, "
		       					+ "SUM((enddate - startdate) + 1) as daysum FROM bookingsv2");
		       
		       ResultSet rs1 = stmt.executeQuery(sql1);

		       while (rs1.next()) {

		    	   int new_count = rs1.getInt("count");
		    	   int new_costsum = rs1.getInt("costsum");
		    	   int new_daysum = rs1.getInt("daysum");
		    	   
		    	   bookingData[0] = Integer.toString(new_count); 
		    	   bookingData[1] = Integer.toString(new_costsum); 
		    	   bookingData[2] = Integer.toString(new_daysum); 

		       }

		       con.close();
		   }

		catch (Exception ex) {
	
		       System.err.println(ex);
		       bookingData[0] = "Error";

		   }
		
		return bookingData;
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