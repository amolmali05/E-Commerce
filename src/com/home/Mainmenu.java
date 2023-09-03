package com.home;

import java.sql.SQLException;
import java.util.Scanner;
import com.admin.Adminoperation;
import com.user.Useroperation;

public class Mainmenu {
	
	public static void main(String[] args) throws SQLException {
		
		Adminoperation adminoperation=new Adminoperation();
		Scanner scanner=new Scanner(System.in);
		System.out.println("Product Name>>");
		String productname=scanner.nextLine();
		System.out.println("Product Description>>");
		String productdesc=scanner.nextLine();
		System.out.println("Quantity>>");
		int quantity=scanner.nextInt();		
		System.out.println("Price>>");
		double price=scanner.nextDouble();
    // for adding product into product table
		adminoperation.addProduct(productname, productdesc,quantity,price);
		
		Useroperation useroperation=new Useroperation();
		useroperation.getProduct();//get product in sorted order to user
		
		//login user with correct authentication
		System.out.println("Enter the username>>");
		String username=scanner.next();
		System.out.println("Enter the password>>");		
		String password=scanner.next();
		useroperation.userLogin(username, password);
		
		//for buy product we are accepting data from user
		System.out.println("Enter the product id to buy product>>");
		int productid=scanner.nextInt();
		System.out.println("Enter the quantity>>");
		int cartquantity=scanner.nextInt();
		
		System.out.println("Do you want to view cart (yes/no)");
		String choice=scanner.next();
		if (choice.equals("yes")) {
			useroperation.showCart();
		}
		else if (choice.equals("no")) {
			
			useroperation.buyProduct(productid,cartquantity);
		}
		
		
	}

}
