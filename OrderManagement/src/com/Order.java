package com;

import java.sql.*; 
public class Order 
{ //Connection to the DB

private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 
 //DB access denied 
 con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/orders", "root", "mysql123"); 
 } 
    catch (Exception e) 
    {e.printStackTrace();} 
 
    return con; 
 } 


public String insertOrder (String name, String category, String paymethod, String payment) 
{ 
  String output = ""; 

try
{ 
	 
   Connection con = connect(); 
   
   if (con == null) 
   {return "Error while connecting to the database for inserting."; } 

   // create a prepared statement
   String query = " INSERT INTO orders VALUES (?, ?, ?, ?, ?)"; 

   PreparedStatement preparedStmt = con.prepareStatement(query); 

   // binding values
   preparedStmt.setInt(1, 0); 
   preparedStmt.setString(2, name); 
   preparedStmt.setString(3, category); 
   preparedStmt.setString(4, paymethod);
   preparedStmt.setString(5, payment);
   
   // execute the statement
   preparedStmt.execute(); 
   con.close(); 
    String newOrders = readOrders();
	 output =  "{\"status\":\"success\", \"data\": \"" + 
			 newOrders + "\"}"; 
	 } 

	catch (Exception e) 
	 { 
		output = "{\"status\":\"error\", \"data\": \"Error while inserting the order.\"}";  
	 System.err.println(e.getMessage()); 
	 } 
	return output; 
	}

public String readOrders() 
{ 
   String output = ""; 
   
   try
  { 
     Connection con = connect(); 
 
     if (con == null) 
     {return "Error while connecting to the database for reading."; } 
 
     // Prepare the HTML table to be displayed
     output = "<table border='1'><tr><th>Order Name</th><th>Order Category</th>" +
              "<th>Payment Method</th>" + 
              "<th>Order Payment</th>" +
              "<th>Update</th><th>Remove</th></tr>"; 
 
   String query = "select * from orders"; 
   Statement stmt = con.createStatement(); 
   ResultSet rs = stmt.executeQuery(query); 
 
   // iterate through the rows in the result set
  while (rs.next()) 
 { 
      String id = Integer.toString(rs.getInt("id")); 
      String name = rs.getString("name"); 
      String category = rs.getString("category"); 
      String paymethod = rs.getString("paymethod");
      String payment = rs.getString("payment");
       
   // Add a row into the HTML table
		 output += "<tr><td><input id='hidIdUpdate' name='hidIdUpdate' type='hidden' value='" + id + "'>"
				 + name + "</td>";
		 output += "<td>" + category + "</td>"; 
		 output += "<td>" + paymethod + "</td>"; 
		 output += "<td>" + payment + "</td>";
		 		
   // buttons
		 output += "<td><input name='btnUpdate' " 
		 + " type='button' value='Update' class =' btnUpdate btn btn-secondary'data-oid='" + id + "'></td>"
		 + "<td><form method='post' action='orders.jsp'>"
		 + "<input name='btnRemove' " 
		 + " type='button' value='Remove' class='btnRemove btn btn-danger' data-oid='" + id + "'>"
		 + "<input name='hidIdDelete' type='hidden' " 
		 + " value='" + id + "'>" + "</form></td></tr>"; 
		 } 
		 con.close(); 
		 // Complete the HTML table
		 output += "</table>"; 
		 } 
		catch (Exception e) 
		 { 
		 output = "Error while reading the orders."; 
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
	}

public String updateOrder(String ID, String name, String category, String paymethod, String payment)
 { 
   
	String output = ""; 
 
	try
   { 
      Connection con = connect(); 
 
      if (con == null) 
      {return "Error while connecting to the database for updating."; } 
 
     // create a prepared statement
     String query = "UPDATE orders SET name=?,category=?,paymethod=?,payment=? WHERE id=?"; 
     PreparedStatement preparedStmt = con.prepareStatement(query); 
 
 
     // binding values
    preparedStmt.setString(1, name); 
    preparedStmt.setString(2, category); 
    preparedStmt.setString(3, paymethod);
    preparedStmt.setString(4, payment);
    preparedStmt.setInt(5, Integer.parseInt(ID)); 
 
    // execute the statement
    preparedStmt.execute(); 
    con.close();
	String newOrders = readOrders();
	 output =  "{\"status\":\"success\", \"data\": \"" + 
			 newOrders + "\"}"; 
	 } 

	catch (Exception e) 
	 { 
		output = "{\"status\":\"error\", \"data\": \"Error while Updating the order.\"}";  
	
	System.err.println(e.getMessage());
	}
	return output;
	}


public String deleteOrder(String id) 
 { 
 String output = ""; 
 
 try
 { 
    Connection con = connect(); 
    if (con == null) 
    {return "Error while connecting to the database for deleting."; } 
 
    // create a prepared statement
    String query = "delete from orders where id=?"; 
    PreparedStatement preparedStmt = con.prepareStatement(query); 
 
    // binding values
    preparedStmt.setInt(1, Integer.parseInt(id)); 
 
 // execute the statement
 	 preparedStmt.execute(); 
 	 con.close(); 
 	 String newOrders = readOrders();
 	 output =  "{\"status\":\"success\", \"data\": \"" + 
 			 newOrders + "\"}"; 
 	 } 

 	catch (Exception e) 
 	 { 
 		output = "{\"status\":\"error\", \"data\": \"Error while Deleting the order.\"}";  
 	 System.err.println(e.getMessage()); 
 	 } 
 	return output; 
 		}








} 