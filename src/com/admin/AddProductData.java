package com.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.database.Dbconnection;;

public class AddProductData {

	Connection connection=null;
	PreparedStatement ps=null;
	
	public void addProduct(String productname,String productdesc,int quantity,double price) throws SQLException
	{
		Dbconnection connectiontest=new Dbconnection();
		try {
		connection=connectiontest.getConnection();
		
			ps=connection.prepareStatement("insert into product(productname,productdesc,quantity,price)values(?,?,?,?)");
			ps.setString(1, productname);
			ps.setString(2, productdesc);
			ps.setInt(3, quantity);
			ps.setDouble(4, price);
			int a=ps.executeUpdate();
			System.out.println("Product Added" +a);
			
		} catch (Exception e) {
	
			e.printStackTrace();
		}finally {
			connection.close();
			ps.close();
		}
	}
}
