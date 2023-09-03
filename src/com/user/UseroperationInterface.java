package com.user;

import java.sql.SQLException;

public interface UseroperationInterface {

	abstract void getProduct() throws SQLException;
	abstract void userLogin(String username,String password) throws SQLException;
	abstract void buyProduct(int productid, int cartquantity) throws SQLException;
    abstract void showCart();
	abstract void purchaseProduct();
	
}