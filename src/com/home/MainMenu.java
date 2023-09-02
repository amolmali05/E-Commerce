package com.home;

import java.sql.SQLException;
import java.util.Scanner;

import com.admin.AddProductData;

public class MainMenu {
	
	public static void main(String[] args) throws SQLException {
		
		AddProductData addproductdata=new AddProductData();
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
	}

}
