package com.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Dbconnection {
	
  Connection con=null;
	
	public Connection getConnection()
	
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommerce","root","Pransh@777");
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return con;
	}
}


