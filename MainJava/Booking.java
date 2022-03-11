package code;

import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Booking {
	
	String url = "jdbc:oracle:thin:OPS$2042387/P46919@ora-srv.wlv.ac.uk:1521/catdb.wlv.ac.uk";
	
	private int booking_num;
	private Item item;
	private int lenderNum;
	private Customer borrower;
	private LocalDate start_date;
	private LocalDate end_date;
	private double total_cost;

	
	public Booking() {
		
		booking_num = 0;
		item = new Item();
		lenderNum = 0;
		borrower = new Customer();
		start_date = LocalDate.now();
		end_date = LocalDate.now();
		total_cost = 0.00;		
		
	}
	
	public Booking(Item item, int lenderNum, Customer borrower, LocalDate start_date, LocalDate end_date, double total_cost) {
		
		booking_num = 0;
		this.item = item;
		this.lenderNum = lenderNum;
		this.borrower = borrower;
		this.start_date = start_date;
		this.end_date = end_date;
		this.total_cost = total_cost;		
		
	}
	
	public Booking(int booking_num, Item item, int lenderNum, Customer borrower, LocalDate start_date, LocalDate end_date, double total_cost) {
		
		this.booking_num = booking_num;
		this.item = item;
		this.lenderNum = lenderNum;
		this.borrower = borrower;
		this.start_date = start_date;
		this.end_date = end_date;
		this.total_cost = total_cost;	
		
	}
	
	public void saveBooking() {
		
		String sql = ("INSERT into Bookings VALUES (seq_booking.nextval," + item.getItem_num() + "," + 
						lenderNum + "," + borrower.getCust_num() + ",'" + start_date.format(DateTimeFormatter.ofPattern("dd-MMM-uuuu")) + 
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
		return lenderNum;
	}

	public void setLenderNum(int lenderNum) {
		this.lenderNum = lenderNum;
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