package com.shopping.po;

import java.util.Map;

public class Buyer extends Person {

	private Double money;
	private Map<String, Commodity> orderList;
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Map<String, Commodity> getOrderList() {
		return orderList;
	}
	public void setOrderList(Map<String, Commodity> orderList) {
		this.orderList = orderList;
	}
}
