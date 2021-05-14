package com;


import java.sql.*;

//==========================================================To implement the server-model================================================================ 
public class cart {

	
	DBConnection dbObj = new DBConnection();
	
	//=============================to insert items to cart=============================================
	public String insertToCart(String productId,String userId,String unitPrice, String quantity){ 
		 String output = ""; 
		 
		 try{ 
			 Connection con = dbObj.connect(); 
			 if (con == null) {
				 return "Error while connecting to the database for inserting."; 
			 } 
			 // create a prepared statement
			 String query = "insert into cart(`productId`,`userId`,`unitPrice`,`quantity`)"
			 			+ " values (?, ?, ?, ?)"; 
				
			 PreparedStatement preparedStmt = con.prepareStatement(query); 
			 
			 
				 // binding values
				 preparedStmt.setInt(1, 0);
				 preparedStmt.setInt(2, Integer.parseInt(productId));
				 preparedStmt.setInt(3, Integer.parseInt(userId));
				 preparedStmt.setDouble(4, Double.parseDouble(unitPrice)); 
				 preparedStmt.setInt(5, Integer.parseInt(quantity));
				 
				// execute the statement
				 preparedStmt.execute(); 
			 
			 con.close(); 
			// execute the statement
			   preparedStmt.execute(); 
			   con.close(); 
			    String crt = readCart();
				 output =  "{\"status\":\"success\", \"data\": \"" + 
						crt + "\"}"; 
				 } 

				catch (Exception e) 
				 { 
					output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}";  
				 System.err.println(e.getMessage()); 
				 } 
				return output; 
			}
	
	//==========================to read items in cart===================================================
	public String readCart(){ 
		 String output = ""; 
		 try{ 
			 Connection con = dbObj.connect(); 
			 if (con == null) {
				 return "Error while connecting to the database for reading!"; 
			 } 
			 // Prepare the html table to be displayed
			 output = "<table border='1'>"
			 			+ "<tr>"
			 			+"<th>Cart ID</th>"
			 			+ "<th>Product ID</th>" 
			 			+"<th>User ID</th>" 
			 			+ "<th>Unit_Price (Rs.)</th>"
			 			+ "<th>Quantity</th>"
			 			+ "<th>Update</th>"
						+ "<th>Remove</th>"
			 			+ "</tr>";  
			 
			 String query = "select * from cart"; 
			 Statement stmt = con.createStatement(); 
			 ResultSet rs = stmt.executeQuery(query); 
			 
			 // iterate through the rows in the result set
			 while (rs.next()) { 
				 String cartId = Integer.toString(rs.getInt("cartId"));
				 String productId = Integer.toString(rs.getInt("productId"));
				 String userId = Integer.toString(rs.getInt("userId"));
				 String unitPrice = Double.toString(rs.getDouble("unitPrice")); 
				 String quantity = Integer.toString(rs.getInt("quantity"));
				
				 //  Add a row into the html table
				 output += "<td>" + cartId + "</td>";
				 output += "<td>" + productId + "</td>";
				 output += "<td>" + userId + "</td>";
				 output += "<td>" + unitPrice + "</td>";
				 output += "<td>" + quantity + "</td>";
				
				 // buttons
				 output += "<td><input name='btnUpdate' " 
						 + " type='button' value='Update' class =' btnUpdate btn btn-secondary'data-uid='" + cartId + "'></td>"
						 + "<td><form method='post' action='cart.jsp'>"
						 + "<input name='btnRemove' " 
						 + " type='button' value='Remove' class='btnRemove btn btn-danger' data-uid='" + cartId + "'>"
						 + "<input name='Delete' type='hidden' " 
						 + " value='" + cartId + "'>" + "</form></td></tr>";
			 } 
			 con.close(); 
			 // Complete the html table
			 output += "</table>"; 
		 } 
		 catch (Exception e) { 
			 output = "Error while reading cart!"; 
			 System.err.println(e.getMessage()); 
		 } 
		 return output; 
	 }
	
	//==================================to delete items from cart by specific cartID=========================================
	public String deleteFromCart(String cartId) { 
		 String output = ""; 
		 try{ 
			 Connection con = dbObj.connect(); 
			 if (con == null) {
				 return "Error while connecting to the database for deleting."; 
			 } 
			 // create a prepared statement
			 String query = "delete from cart where cartId = ?";
					
			 PreparedStatement preparedStmt = con.prepareStatement(query);
			 // binding values
			 preparedStmt.setInt(1, Integer.parseInt(cartId)); 
			 // execute the statement
		
		 	 preparedStmt.execute(); 
		 	 con.close(); 
		 	 String crt = readCart();
		 	 output =  "{\"status\":\"success\", \"data\": \"" + 
		 			 crt + "\"}"; 
		 	 } 

		 	catch (Exception e) 
		 	 { 
		 		output = "{\"status\":\"error\", \"data\": \"Error while Deleting the item.\"}";  
		 	 System.err.println(e.getMessage()); 
		 	 } 
		 	return output; 
	 }
	
	//===================================to update items from cart=================================================================================================================================================
	public String updateCart(String cartId, String productId, String quantity ,String unitPrice) {
    	String output = "";    	
    	try{
    		Connection con = dbObj.connect();
    		if (con == null){
	    		return "Error while connecting to the database for updating!";
	    	}	
			String querry = "update cart set productId = ? , quantity = ? , unitPrice = ?  where cartId = ?";
			
			//create a prepared statement
			PreparedStatement preparedStmt = con.prepareStatement(querry);
			
			
			
			//binding values
			preparedStmt.setInt(1, Integer.parseInt(productId));
			preparedStmt.setDouble(2, Double.parseDouble(unitPrice));
			preparedStmt.setInt(3, Integer.parseInt(quantity));
			preparedStmt.setInt(4, Integer.parseInt(cartId));
			//execute the statement
			
			preparedStmt.execute();
			con.close();
			String crt = readCart();
			 output =  "{\"status\":\"success\", \"data\": \"" + 
					 crt + "\"}"; 
			 } 

			catch (Exception e) 
			 { 
				output = "{\"status\":\"error\", \"data\": \"Error while Updating the cart.\"}";  
			
			System.err.println(e.getMessage());
			}
			return output;
    }
}
