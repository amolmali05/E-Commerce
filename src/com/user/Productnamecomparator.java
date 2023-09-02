package com.user;

import java.util.Comparator;

public class Productnamecomparator implements Comparator<Productlist>{

	@Override
	public int compare(Productlist p1, Productlist p2) {
		return p1.productname.compareTo(p2.productname);
	}

}
