package com.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.database.Dbconnection;;

public class Adminoperation implements AdminoperationInterface{

	Connection connection=null;
	PreparedStatement ps=null;
	
	@Override
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

	
	@Override
	public void calculateBill() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void displayAmount() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkQuantity() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkRegisteredUser() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void checkUserHistory() {
		// TODO Auto-generated method stub
		
	}
}
