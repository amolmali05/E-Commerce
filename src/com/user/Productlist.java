package com.user;

public class Productlist {

	int productid;String productname;String productdesc;int quantity;double price;
	public int getProductid() {
		return productid;
	}


	public void setProductid(int productid) {
		this.productid = productid;
	}


	public String getProductname() {
		return productname;
	}


	public void setProductname(String productname) {
		this.productname = productname;
	}


	public String getProductdesc() {
		return productdesc;
	}


	public void setProductdesc(String productdesc) {
		this.productdesc = productdesc;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public Productlist(int productid,String productname,String productdesc,int quantity,double price) {
		this.productid=productid;
		this.productname=productname;
		this.productdesc=productdesc;
		this.quantity=quantity;
		this.price=price;
	}
	
	
	

}
