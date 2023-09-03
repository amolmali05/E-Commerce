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
	//PreparedStatement ps1=null;
	ResultSet rs=null;
	String activeuser;
	double totalprice;
	Dbconnection connectiontest=new Dbconnection();
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
				
					System.out.println("Login Succesful >> Welcome.." +rs.getString("username"));
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
		try {
			
			ps=connection.prepareStatement("select price from product where productid=?");
			ps.setInt(1, productid);
			rs=ps.executeQuery();
			while (rs.next()) {
				double price=rs.getDouble("price");
				totalprice=price*cartquantity;
			}
			ps=connection.prepareStatement("insert into cart(username,productid_cart,cartquantity,totalprice)values(?,?,?,?)");
			ps.setString(1, activeuser);
			ps.setInt(2, productid);
			ps.setInt(3, cartquantity);
			ps.setDouble(4, totalprice);
			int x=ps.executeUpdate();
			System.out.println("Cart Updated Succesfully" +x);
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			connection.close();
			ps.close();
			//ps1.close();
			rs.close();
		}
	}


	@Override
	public void showCart() {
		
		
	}


	@Override
	public void purchaseProduct() {
		// TODO Auto-generated method stub
		
	}




}
