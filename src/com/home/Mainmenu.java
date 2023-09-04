package com.home;

import java.sql.SQLException;
import java.util.Scanner;
import com.admin.Adminoperation;
import com.guest.Guestoperation;
import com.user.Useroperation;

public class Mainmenu {
	
	public static void main(String[] args) throws SQLException {
		
		Useroperation useroperation=new Useroperation();
		Adminoperation adminoperation=new Adminoperation();
    	Guestoperation guestoperation=new Guestoperation();

		Scanner scanner=new Scanner(System.in);

		while (true) {
		System.out.println("\nWelcome to E-Commerce based application");
		System.out.println("\nUser Operation\n");
		System.out.println("1. User Registration");
		System.out.println("2. User Login");
		System.out.println("3. User view Product item as Sorted Order");
		System.out.println("4. Buy Product");
		System.out.println("5. View Cart"); 
		System.out.println("6. Purchase the item ");
		System.out.println("\nAdmin Operation\n");
		System.out.println("7. Add product item");
		System.out.println("8. Calculate Bill");
		System.out.println("9.Check Quantity");
		System.out.println("10. Check registered user");
		System.out.println("11. Check the particular user history");
		System.out.println("\nGuest Operation\n");
		System.out.println("12. View product item");
		System.out.println("13. Not purchase item");
		System.out.println("14. End or Exit");
		System.out.println("\nEnter your choice-");
		int selection=scanner.nextInt();
		
		switch (selection) {
		case 1:
			System.out.println("Enter the first name>>");
			String firstname=scanner.next();
			System.out.println("Enter the last name>>");
			String lastname=scanner.next();
			System.out.println("Enter the username>>");
			String user_name=scanner.next();
			System.out.println("Enter the password>>");
			String user_password=scanner.next();
			System.out.println("Enter the city>>");
			String city=scanner.next();
			System.out.println("Enter the mail id>>");
			String mailid=scanner.next();
			System.out.println("Enter the mobile number>>");
			String mobilenumber=scanner.next();
            useroperation.userRegistration(firstname,lastname,user_name,user_password,city,mailid,mobilenumber);

			break;
        case 2:
        	//login user with correct authentication
    		System.out.println("Enter the username>>");
    		String username=scanner.next();
    		System.out.println("Enter the password>>");		
    		String password=scanner.next();
    		useroperation.userLogin(username, password);
			break;
        case 3:
        	useroperation.getProduct();//get product in sorted order to user
			break;
        case 4:
        	//for buy product we are accepting data from user
    		System.out.println("Enter the product id to buy product>>");
    		int productid=scanner.nextInt();
    		System.out.println("Enter the quantity>>");
    		int cartquantity=scanner.nextInt();
    		
    		System.out.println("Do you want to view cart (yes/no)");
    		String choice=scanner.next();
    		useroperation.buyProduct(productid,cartquantity);
    		System.out.println("Product item has been added to cart");
    		if (choice.equals("yes")) {
    			useroperation.showCart();
    		}
    		else if (choice.equals("no")) {
    			break;    		
    		}
			break;
        case 5:
        	useroperation.showCart();
			break;
        case 6:
        	useroperation.purchaseProduct();
			break;
        case 7:
        	System.out.println("Product Name>>");
    		String productname=scanner.next();
    		System.out.println("Product Description>>");
    		String productdesc=scanner.next();
    		System.out.println("Quantity>>");
    		int quantity=scanner.nextInt();		
    		System.out.println("Price>>");
    		double price=scanner.nextDouble();
    		// for adding product into product table
    		adminoperation.addProduct(productname, productdesc,quantity,price);
			break;
        case 8:
			System.out.println("Enter Username to show Total Bill");
			String user=scanner.next();
			adminoperation.calculateBill(user);
			break;
        case 9:
        	System.out.println("Enter Product Id>>");
        	int product_id= scanner.nextInt();
			adminoperation.checkQuantity(product_id);
			break;
        case 10:
			//for display registered user 
        	adminoperation.checkRegisteredUser();
			break;
        case 11:
			//Check the particular user history
        	System.out.println("Enter the username>>");
        	String userid=scanner.next();
        	adminoperation.checkUserHistory(userid);
			break;
        case 12:
			//show product list to guest
        	guestoperation.getProduct();
			break;
        case 13:
			System.out.println("you are guest..please login First");
			break;
        case 14:
        	System.out.println("\nEnding the Portal");
			System.exit(0);
			break;
					
		default:
			System.out.println("\nInvalid choice\n");
			break;
		}
		
		}	
		
			
		
	}

}
