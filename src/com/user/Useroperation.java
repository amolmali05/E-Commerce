package com.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import com.database.Dbconnection;

public class Useroperation implements UseroperationInterface{
	

	Connection connection=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	String activeuser;
	double totalprice;
	Dbconnection connectiontest=new Dbconnection();
	
	@Override
	public void userRegistration(String firstname, String lastname, String username, String password, String city,
			String mailid, String mobilenumber) throws SQLException {
		try {
		connection=connectiontest.getConnection();
		
			ps=connection.prepareStatement("insert into user(firstname,lastname,username,password,city,mailid,mobilenumber)values(?,?,?,?,?,?,?)");
			ps.setString(1, firstname);
			ps.setString(2, lastname);
			ps.setString(3, username);
			ps.setString(4, password);
			ps.setString(5, city);
			ps.setString(6, mailid);
			ps.setString(7, mobilenumber);
			int a=ps.executeUpdate();
			System.out.println("\nUser Registered Succesfully" +a);
			
		} catch (Exception e) {
	
			e.printStackTrace();
		}finally {
			connection.close();
			ps.close();
		}
		
	}
	public void getProduct() throws SQLException
	{
		
		
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

	@Override
	public void userLogin(String username, String password) throws SQLException {
		
		connection=connectiontest.getConnection();
		try {
			ps=connection.prepareStatement("select * from user where username=? and password=?");
			ps.setString(1, username);
			ps.setString(2, password);
			rs=ps.executeQuery();
			if (rs.next()) {
				
					System.out.println("\nLogin Succesful >> Welcome.." +rs.getString("username"));
					this.activeuser=rs.getString("username");
				}
				else
				{
					System.out.println("Invalid User");
				}
			}
		 catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
			ps.close();
			rs.close();
		}
		
	}
	
	
	@Override
	public void buyProduct(int productid, int cartquantity) throws SQLException {
				connection=connectiontest.getConnection();
				int availablequantity = 0;
		try {
			
			ps=connection.prepareStatement("select quantity,price from product where productid=?");
			ps.setInt(1, productid);
			rs=ps.executeQuery();
			while (rs.next()) {
				availablequantity=rs.getInt("quantity");
				double price=rs.getDouble("price");
				totalprice=price*cartquantity;
			}
			
			//insert cart item in cart table
			ps=connection.prepareStatement("insert into cart(username,productid,cartquantity,totalprice)values(?,?,?,?)");
			ps.setString(1, activeuser);
			ps.setInt(2, productid);
			ps.setInt(3, cartquantity);
			ps.setDouble(4, totalprice);
			int x=ps.executeUpdate();
			System.out.println("\nCart Updated Succesfully" +x);
			
			//Updating quantity in product table
			ps=connection.prepareStatement("update product set quantity=? where productid=?");
			availablequantity=availablequantity-cartquantity;
			ps.setInt(1, availablequantity);
			ps.setInt(2, productid);
			int a=ps.executeUpdate();
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
			ps.close();
			rs.close();
		}
	}


	@Override
	public void showCart() {
		
		connection=connectiontest.getConnection();
		try {
			
			ps=connection.prepareStatement("select product.productid,productname,productdesc,cart.username,cartquantity,totalprice from product right join cart on product.productid=cart.productid where username=?");
			ps.setString(1, activeuser);
			rs=ps.executeQuery();
			System.out.println("\nproductid-->productname-->productdesc-->username-->cartquantity-->totalprice");
			while (rs.next()) {
				System.out.println(rs.getInt("productid")+"--->" +rs.getString("productname")+"--->" +rs.getString("productdesc")+"--->" +rs.getString("username")+"--->" +rs.getInt("cartquantity")+"--->" +rs.getDouble("totalprice"));
			}
	}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void purchaseProduct() {
		connection=connectiontest.getConnection();
		try {
			
			ps=connection.prepareStatement("select sum(totalprice) as result from cart where username=?");
			ps.setString(1, activeuser);
			rs=ps.executeQuery();
			System.out.println("\nUsername>>" +activeuser);
			while (rs.next()) {
				System.out.println("Total Bill Amount>>" +rs.getString("result"));
				
			}
		}catch (Exception e) {
			System.out.println(e);
		}
	}

	




}
