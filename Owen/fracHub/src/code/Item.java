package code;

import java.sql.*;

public class Item {
	
	String url = "jdbc:oracle:thin:OPS$2042387/P46919@ora-srv.wlv.ac.uk:1521/catdb.wlv.ac.uk";
	
	private String name;
	private String description;
	private String type;
	private double value;
	private double daily_rate;
	
	public Item() {
		
		name = "";
		description = "";
		type = "";
		value = 0.00;
		daily_rate = 0.00;
		
	}
	
	public Item(String name, String description, String type, double value, double daily_rate) {
		
		this.name = name;
		this.description = description;
		this.type = type;
		this.value = value;
		this.daily_rate = daily_rate;
		
	}
	
	public void saveItem() {
		
		String sql = ("INSERT into Item VALUES (seq_item.nextval,'" + name + 
				"','" + description + "','" + value + "','" + daily_rate + "','" + type + "')");
		
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

}