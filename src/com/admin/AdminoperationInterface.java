package com.admin;

import java.sql.SQLException;

public interface AdminoperationInterface {

	abstract void addProduct(String productname,String productdesc,int quantity,double price) throws SQLException;
	abstract void calculateBill(String username);
	abstract void checkQuantity(int productid);
	abstract void checkRegisteredUser();
	abstract void checkUserHistory(String username);
}
