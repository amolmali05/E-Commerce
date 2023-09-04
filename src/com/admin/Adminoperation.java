package com.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.database.Dbconnection;
import com.mysql.cj.protocol.Resultset;;

public class Adminoperation implements AdminoperationInterface{

	Connection connection=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	
	Dbconnection connectiontest=new Dbconnection();
	
	@Override
	public void addProduct(String productname,String productdesc,int quantity,double price) throws SQLException
	{
	
		try {
		connection=connectiontest.getConnection();
		
			ps=connection.prepareStatement("insert into product(productname,productdesc,quantity,price)values(?,?,?,?)");
			ps.setString(1, productname);
			ps.setString(2, productdesc);
			ps.setInt(3, quantity);
			ps.setDouble(4, price);
			int a=ps.executeUpdate();
			System.out.println("\nProduct Added" +a);
			
		} catch (Exception e) {
	
			e.printStackTrace();
		}finally {
			connection.close();
			ps.close();
		}
	}

	
	@Override
	public void calculateBill(String username) {
		
		connection=connectiontest.getConnection();
		try {
			
			ps=connection.prepareStatement("select sum(totalprice) as result from cart where username=?");
			ps.setString(1, username);
			rs=ps.executeQuery();
			System.out.println("\nUsername>>" +username);
			while (rs.next()) {
				System.out.println("Total Bill Amount>>" +rs.getString("result"));
				
			}
		}catch (Exception e) {
			System.out.println(e);
		}
	}



	@Override
	public void checkQuantity(int product_id) {
		
	 	connection=connectiontest.getConnection();
		try {
			
			ps=connection.prepareStatement("select quantity from product where productid=?");
			ps.setInt(1, product_id);
			rs=ps.executeQuery();
			
			while (rs.next()) {
				System.out.println("Quantity is>>" +rs.getInt("quantity"));
				
			}
		}catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void checkRegisteredUser() {
		
		connection=connectiontest.getConnection();
		try {
			
			ps=connection.prepareStatement("select username,firstname,lastname,mailid,mobilenumber,city from user");
			rs=ps.executeQuery();
			
			while (rs.next()) {
				System.out.println("\nUsername>>" +rs.getString("username"));
				System.out.println("First name>>" +rs.getString("firstname"));
				System.out.println("Last name>>" +rs.getString("lastname"));
				System.out.println("Email id>>" +rs.getString("mailid"));
				System.out.println("Mobile>>" +rs.getString("mobilenumber"));
				System.out.println("City>>" +rs.getString("city"));
			}
		}catch (Exception e) {
			System.out.println(e);
		}
	}
	

	@Override
	public void checkUserHistory(String username) {
		connection=connectiontest.getConnection();
		try {
			
			ps=connection.prepareStatement("select product.productid,productdesc,cart.cartquantity from product right join cart on product.productid=cart.productid where username=?");
			ps.setString(1, username);
			rs=ps.executeQuery();
			while (rs.next()) {
				System.out.println("\nProduct id>>" +rs.getInt("productid"));
				System.out.println("Product Description>>" +rs.getString("productdesc"));
				System.out.println("Quantity>>" +rs.getInt("cartquantity"));

			}
	}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	}

