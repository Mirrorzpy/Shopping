package com.shopping.service;

import java.util.Map;

import com.shopping.po.Buyer;
import com.shopping.po.Seller;

public interface List {

	boolean productList(Map<String, Seller> list);
	boolean sellerList(Map<String, Seller> list);
	String sellerProductList(Map<String, Seller> list, Integer id);
	boolean sellerProductList(Map<String, Seller> list, String name);
	boolean orderList(Map<String, Buyer> listB, Map<String, Seller> listS, String name);
}
