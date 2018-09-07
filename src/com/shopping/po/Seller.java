package com.shopping.po;

import java.util.Map;

public class Seller extends Person {

	private Double money;
	//商品名称，商品类
	private Map<String, Commodity> productList;
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Map<String, Commodity> getProductList() {
		return productList;
	}
	public void setProductList(Map<String, Commodity> productList) {
		this.productList = productList;
	}
}
