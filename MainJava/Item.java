package code;

import java.sql.*;
import java.util.*;

public class Item {
	
	String url = "jdbc:oracle:thin:OPS$2042387/P46919@ora-srv.wlv.ac.uk:1521/catdb.wlv.ac.uk";
	
	private int item_num;
	private int cust_num;
	private String name;
	private String type;
	private String description;
	private double value;
	private double daily_rate;
	
	public Item() {
		
		item_num = 0;
		cust_num = 0;
		name = "";
		type = "";
		description = "";
		value = 0.00;
		daily_rate = 0.00;
		
	}
	
	public Item(int cust_num, String name, String type, String description, double value, double daily_rate) {
		
		this.item_num = 0;
		this.cust_num = cust_num;
		this.name = name;
		this.type = type;
		this.description = description;
		this.value = value;
		this.daily_rate = daily_rate;
		
	}
	
	public Item(int item_num, int cust_num, String name, String type, String description, double value, double daily_rate) {
		
		this.item_num = item_num;
		this.cust_num = cust_num;
		this.name = name;
		this.type = type;
		this.description = description;
		this.value = value;
		this.daily_rate = daily_rate;
		
	}
	
	public void saveItem() {
		
		String sql = ("INSERT into Items VALUES (seq_item.nextval," + cust_num + ",'" + name + "','" +
						type + "','" + description + "'," + value + "," + daily_rate + ")");
		
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
	
	public ItemResult viewAllItems() {
		
		String sql1 = ("SELECT distinct type FROM items");
		String sql2 = ("SELECT * FROM items");
		
		Connection con = null;
		
		ArrayList<String> type_list = new ArrayList<String>();
		ArrayList<Item> item_list = new ArrayList<Item>();
		
		try {
		       DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		       System.out.println("Connecting to Database...");
		       con = DriverManager.getConnection(url);
		       
		       Statement stmt = con.createStatement();
		       
		       ResultSet rs1 = stmt.executeQuery(sql1);

		       while (rs1.next()) {

		    	   String new_type = rs1.getString("type");
		    	   
		    	   type_list.add(new_type);   

		       }
		       
		       ResultSet rs2 = stmt.executeQuery(sql2);

		       while (rs2.next()) {

		    	   int new_item_num = rs2.getInt("id");
		    	   int new_cust_num = rs2.getInt("userid");
		    	   String new_name = rs2.getString("name");
		    	   String new_type = rs2.getString("type");
		    	   String new_description = rs2.getString("description");
		    	   double new_value = rs2.getDouble("value");
		    	   double new_daily_rate = rs2.getDouble("borrow_cost");
		    	   
		    	   Item new_item = new Item(new_item_num, new_cust_num, new_name, new_type,
		    			   				new_description, new_value, new_daily_rate);
		    	   
		    	   item_list.add(new_item);   

		       }
		       
		   }

		catch (Exception ex) {
	
		       System.err.println(ex);
		   }
		
		ItemResult result = new ItemResult(type_list, item_list);
		
		return result;
	}
	
	public class ItemResult {
		
		ArrayList<String> type_list;
		ArrayList<Item> item_list;
		
		public ItemResult(ArrayList<String> type_list ,ArrayList<Item> item_list) {
			this.type_list = type_list;
			this.item_list = item_list;
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public double getDaily_rate() {
		return daily_rate;
	}

	public void setDaily_rate(double daily_rate) {
		this.daily_rate = daily_rate;
	}

	public int getItem_num() {
		return item_num;
	}

	public void setItem_num(int item_num) {
		this.item_num = item_num;
	}

	public int getCust_num() {
		return cust_num;
	}

	public void setCust_num(int cust_num) {
		this.cust_num = cust_num;
	}
	
	

}