package com.shopping.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import com.shopping.po.Buyer;
import com.shopping.po.Commodity;
import com.shopping.po.Seller;
import com.shopping.service.impl.ListImpl;


public class TestData {
	
	public static void newData(Map<String, Buyer> buyer, Map<String, Seller> seller) {
		ListImpl list = new ListImpl();
		// 测试数据
		
		// 用户1 admin admin
		Map<String, Commodity> m1 = new LinkedHashMap<String, Commodity>();
		Buyer b = new Buyer();
		Commodity coU = new Commodity();
		b.setName("admin");
		b.setPassword("admin");
		b.setMoney(1000.0);
		coU.setName("笔记本电脑");
		coU.setMoney(50.0);
		coU.setCount(3);
		coU.setOwner("zpy");
		m1.put("笔记本电脑", coU);
		b.setOrderList(m1);
		buyer.put("admin", b);
		
		// 商家1 zpy 123456
		Map<String, Commodity> m2 = new LinkedHashMap<String, Commodity>();
		Seller s = new Seller();
		Commodity coS = new Commodity();
		s.setName("zpy");
		s.setPassword("123456");
		s.setMoney(0.0);
		coS.setName("笔记本电脑");
		coS.setMoney(60.0);
		coS.setCount(50);
		coS.setOwner("zpy");
		m2.put("笔记本电脑", coS);
		s.setProductList(m2);
		seller.put("zpy", s);
		
		// 商家2 老王 wangnima
		Map<String, Commodity> m3 = new LinkedHashMap<String, Commodity>();
		Seller s2 = new Seller();
		Commodity co3 = new Commodity();
		s2.setName("老王");
		s2.setPassword("wangnima");
		s2.setMoney(123.4);
		co3.setName("苹果手机");
		co3.setMoney(80.0);
		co3.setCount(100);
		co3.setOwner("老王");
		m3.put("苹果手机", co3);
		Commodity co4 = new Commodity();
		co4.setName("樱桃键盘");
		co4.setMoney(30.0);
		co4.setCount(10);
		co4.setOwner("老王");
		m3.put("樱桃键盘", co4);
		s2.setProductList(m3);
		seller.put("老王", s2);
		
		//测试数据导入是否成功
		list.orderList(buyer, seller, "admin");
		list.productList(seller);
	}
}
