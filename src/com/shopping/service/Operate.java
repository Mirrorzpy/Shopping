package com.shopping.service;

import java.util.Map;

import com.shopping.po.Buyer;
import com.shopping.po.Seller;

public interface Operate {

	boolean buyerAdd(String name, Integer id, Integer count, Map<String, Buyer> listB, Map<String, Seller> listS, String owner);
	boolean sellerAdd(String name, Double money, Integer count, Map<String, Seller> list, String owner);
	boolean buyerDel(Integer id, Map<String, Buyer> list, String owner);
	boolean sellerDel(Integer id, Map<String, Seller> list, String owner);
	boolean buyerChange(Integer id, Integer count, Map<String, Buyer> list, Map<String, Seller> listS, String owner);
	boolean sellerChange(Integer id, String name, Double money, Integer count, Map<String, Buyer> list, String owner);
	boolean submitOrder(Map<String, Buyer> listB, Map<String, Seller> listS, String owner);
	boolean saveMoney(Double money, Map<String, Buyer> list, String owner);
}
