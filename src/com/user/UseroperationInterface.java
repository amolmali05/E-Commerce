package com.user;

import java.sql.SQLException;

public interface UseroperationInterface {
	abstract void userRegistration(String firstname,String lastname,String username,String password,String city,String mailid,String mobilenumber) throws SQLException;
	abstract void getProduct() throws SQLException;
	abstract void userLogin(String username,String password) throws SQLException;
	abstract void buyProduct(int productid, int cartquantity) throws SQLException;
    abstract void showCart();
	abstract void purchaseProduct();
	
}