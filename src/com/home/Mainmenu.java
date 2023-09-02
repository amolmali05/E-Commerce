package com.home;

import java.sql.SQLException;
import java.util.Scanner;

import com.admin.Addproductdata;
import com.user.Getproductdata;

public class Mainmenu {
	
	public static void main(String[] args) throws SQLException {
		
		Addproductdata addproductdata=new Addproductdata();
		Scanner scanner=new Scanner(System.in);
		System.out.println("Product Name>>");
		String productname=scanner.nextLine();
		System.out.println("Product Description>>");
		String productdesc=scanner.nextLine();
		System.out.println("Quantity>>");
		int quantity=scanner.nextInt();
		System.out.println("Price>>");
		double price=scanner.nextDouble();

		addproductdata.addProduct(productname, productdesc,quantity,price);
		
		Getproductdata getproductdata=new Getproductdata();
		getproductdata.getProduct();
		
	}

}
