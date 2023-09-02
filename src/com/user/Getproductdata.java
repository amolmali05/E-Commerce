package com.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import com.database.Dbconnection;

public class Getproductdata {
	

	Connection connection=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	

	public void getProduct() throws SQLException
	{
		Dbconnection connectiontest=new Dbconnection();
		
     	try {
	        connection=connectiontest.getConnection();
			ps=connection.prepareStatement("select * from product");
			rs=ps.executeQuery();
			ArrayList<Productlist> arraylist=new ArrayList<Productlist>();

			while (rs.next()) {
							
				arraylist.add(new Productlist(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getDouble(5)));
				
			}
			Collections.sort(arraylist,new Productnamecomparator());
			
			for (Productlist product : arraylist) {
				
				System.out.println("Product Id>>" +product.getProductid());
				System.out.println("Product Name>>" +product.getProductname());
				System.out.println("Product Description>>" +product.getProductdesc());
				System.out.println("Available Quantity>>" +product.getQuantity());
				System.out.println("Price>>" +product.getPrice());
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
			ps.close();
			rs.close();
		}
		
	}
	

}
