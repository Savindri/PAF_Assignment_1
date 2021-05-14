package com;


import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	
	public Connection connect() {
		Connection con = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); 
			// For testing
			con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/frontend_cart", "root", "mysql123");
			
			System.out.println("Successfully connected!");

		} catch (Exception e) {
			System.out.print("Connection failed!");
			e.printStackTrace();
			System.out.print(e);
		}

		return con;
	}
	

}