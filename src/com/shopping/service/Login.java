package com.shopping.service;

import java.util.Map;

import com.shopping.po.Buyer;
import com.shopping.po.Seller;

public interface Login {

	boolean buyerLogin(String username, String password, Map<String, Buyer> list);
	boolean sellerLogin(String username, String password, Map<String, Seller> list);
	boolean buyerRegistered(String username, String password, Map<String, Buyer> list);
	boolean sellerRegistered(String username, String password, Map<String, Seller> list);
	boolean buyerChangePassword(String username, String Password1, String Password2, Map<String, Buyer> list);
	boolean sellerChangePassword(String username, String Password1, String Password2, Map<String, Seller> list);
}
