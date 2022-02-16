package Database;

//Java Program to Establish Connection in JDBC

//Importing database
import java.sql.*;
//Importing required classes
import java.util.*;

//Main class
class SQL_Link {

 // Main driver method
 public static void main(String a[])
 {

     // Creating the connection using Oracle DB
     // Note: url syntax is standard, so do grasp
     String url = "jdbc:oracle:thin:OPS$username/password@ora-srv.wlv.ac.uk:1521/catdb.wlv.ac.uk";


     // Inserting data using SQL query
     String sql = "SELECT first_name FROM Customer WHERE last_name = 'Warhurst'";

     // Connection class object
     Connection con = null;

     // Try block to check for exceptions
     try {

         // Registering drivers
         DriverManager.registerDriver(
             new oracle.jdbc.OracleDriver());

         // Reference to connection interface
         con = DriverManager.getConnection(url);
         
         Statement stmt = con.createStatement();
         
         ResultSet rs = stmt.executeQuery(sql);
         while (rs.next()) {
        	 String name = rs.getString("first_name");

        	 System.out.println(name);
         }

         // Creating a statement
         Statement st = con.createStatement();

         // Executing query
         int m = st.executeUpdate(sql);
         if (m == 1)
             System.out.println(
                 "inserted successfully : " + sql);
         else
             System.out.println("insertion failed");

         // Closing the connections
         con.close();
     }

     // Catch block to handle exceptions
     catch (Exception ex) {
         // Display message when exceptions occurs
         System.err.println(ex);
     }
 }
}
