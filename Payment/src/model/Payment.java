package model;
import java.sql.*;


public class Payment {
	
	private Connection connect() 
	 { 
	 Connection con = null; 
	 try
	 { 
		 Class.forName("com.mysql.jdbc.Driver"); 
		 //Provide the correct details: DBServer/DBName, username, password 
		 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/pay?serverTimezone=UTC", "root", ""); 
	 } 
	 catch (Exception e) 
	 {e.printStackTrace();} 
	 return con; 
	 } 
	
	public String insertPayment(String 	customer_n, String amount, String email, String card_type, String card_no, String exp_month, String exp_year, String cvn) 
	 { 
		String output = ""; 
		try
		{ 
			Connection con = connect(); 
			if (con == null) 
			{return "Error while connecting to the database for inserting."; } 
			
			// create a prepared statement
			String query = " insert into payment_table (payment_id,customer_n,amount,email,card_type,card_no,exp_month,exp_year,cvn)" +  "values (?,?,?,?,?,?,?,?,?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
			
			// binding values
			preparedStmt.setInt(1, 0); 
			preparedStmt.setString(2, customer_n);  
			preparedStmt.setString(3, amount); 
			preparedStmt.setString(4, email); 
			preparedStmt.setString(5, card_type);
			preparedStmt.setString(6, card_no); 
			preparedStmt.setString(7, exp_month); 
			preparedStmt.setString(8, exp_year); 
			preparedStmt.setString(9, cvn); 
			
			// execute the statement3
			preparedStmt.execute(); 
			con.close(); 
			
			String newPayment = readItems();
			output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";
			
	 } 
	 catch (Exception e) 
	 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while inserting the payment.\"}"; 
		 System.err.println(e.getMessage());
	 } 
	 return output; 
	 } 
	
	public String readItems() 
	 { 
		String output = ""; 
	 try
	 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for reading."; } 
		 
		 // Prepare the html table to be displayed
		 output = "<table border='1'><tr><th>Customer ID</th><th>Customer Name</th><th>Amount</th><th>Email</th><th>Card type</th><th>Card Number</th><th>Expiration Month</th><th>Expiration Year</th><th>CVN</th><th>Update</th><th>Remove</th></tr>"; 
	 
		 String query = "select * from payment_table"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 
		 // iterate through the rows in the result set
		 
	 while (rs.next()) 
	 { 
		 String payment_id = Integer.toString(rs.getInt("payment_id")); 
		 String customer_n = rs.getString("customer_n");  
		 String amount = rs.getString("amount"); 
		 String email = rs.getString("email");
		 String card_type = rs.getString("card_type");
		 String card_no = rs.getString("card_no");
		 String exp_month = rs.getString("exp_month");
		 String exp_year = rs.getString("exp_year");
		 String cvn = rs.getString("cvn");
		 
		 // Add into the html table
		 output += "<tr><td><input id='hidPaymentIDUpdate' name='hidPaymentIDUpdate'type='hidden' value='" + payment_id + "'>"+ payment_id + "</td>";
		 output += "<td>" + customer_n + "</td>"; 
		 output += "<td>" + amount + "</td>"; 
		 output += "<td>" + email + "</td>"; 
		 output += "<td>" + card_type + "</td>";
		 output += "<td>" + card_no + "</td>";
		 output += "<td>" + exp_month + "</td>";
		 output += "<td>" + exp_year + "</td>";
	 	 output += "<td>" + cvn + "</td>";
	 	 
	 	// buttons
			output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-paymentid='" + payment_id + "'></td>" 
			+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-paymentid='" + payment_id + "'>"
			+ "</td></tr>";
	 } 
	 con.close(); 
	 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 
	 catch (Exception e) 
	 { 
		 output = "Error while reading the items."; 
		 System.err.println(e.getMessage()); 
	 } 
	 	return output; 
	 } 
	
	
	public String updatePayment(String payment_id, String customer_n, String amount, String email, String card_type,String 	card_no,String exp_month, String exp_year, String cvn)
	 { 
		String output = ""; 
	 try
	 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE payment_table SET customer_n=?,amount=?,email=?,card_type=?,card_no=?,exp_month=?,exp_year=?,cvn=? WHERE payment_id=?"; 
		PreparedStatement preparedStmt = con.prepareStatement(query); 
		
		// binding values
		preparedStmt.setString(1, customer_n); 
		preparedStmt.setString(2, amount); 
		preparedStmt.setString(3, email);
		preparedStmt.setString(4, card_type);
		preparedStmt.setString(5, card_no);
		preparedStmt.setString(6, exp_month);
		preparedStmt.setString(7, exp_year);
		preparedStmt.setString(8, cvn);
		preparedStmt.setInt(9, Integer.parseInt(payment_id)); 
		
		// execute the statement
		preparedStmt.execute(); 
	 	con.close(); 
	 	
	 	String newPayment = readItems();
		output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}"; 
		
	 } 
	 
	 catch (Exception e) 
	 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while updating the payment.\"}"; 
		 System.err.println(e.getMessage()); 
	 } 
	 
	 return output; 
 } 
	
	public String deletePayment(String payment_id) 
	 { 
		String output = ""; 
	 try
	 { 
		 Connection con = connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for deleting."; } 
		 
		 // create a prepared statement
		 String query = "delete from payment_table where payment_id=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 
		 // binding values
		 preparedStmt.setInt(1, Integer.parseInt(payment_id)); 
		 
		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 
		 String newPayment = readItems();
		 output = "{\"status\":\"success\", \"data\": \"" + newPayment + "\"}";
	 } 
	 catch (Exception e) 
	 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while deleting the payment.\"}"; 
		 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 

}
