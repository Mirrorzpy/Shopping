package com.shopping.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;

import com.shopping.po.Buyer;
import com.shopping.po.Commodity;
import com.shopping.po.Seller;
import com.shopping.service.Login;

public class LoginImpl implements Login {

	@Override
	public boolean buyerLogin(String username, String password, Map<String, Buyer> list) {
		// TODO Auto-generated method stub
		if (list.get(username) == null) {
			System.out.println("用户名不存在，登录失败");
			return false;
		} else if (!list.get(username).getPassword().equals(password)) {
			System.out.println("用户名或密码错误，登录失败");
			return false;
		} else {
			System.out.println("登录成功");
			return true;
		}
	}
	
	@Override
	public boolean sellerLogin(String username, String password, Map<String, Seller> list) {
		// TODO Auto-generated method stub
		if (list.get(username) == null) {
			System.out.println("用户名不存在，登录失败");
			return false;
		} else if (!list.get(username).getPassword().equals(password)) {
			System.out.println("用户名或密码错误，登录失败");
			return false;
		} else {
			System.out.println("登录成功");
			return true;
		}
	}
	
	@Override
	public boolean buyerRegistered(String username, String password, Map<String, Buyer> list) {
		// TODO Auto-generated method stub
		if (list.get(username) == null) {
			Buyer b = new Buyer();
			b.setName(username);
			b.setPassword(password);
			b.setMoney(0.0);
			Map<String, Commodity> m = new LinkedHashMap<String, Commodity>();
			b.setOrderList(m);
			list.put(username, b);
			System.out.println("创建用户成功");
			return true;
		} else {
			System.out.println("昵称已被占用，创建失败");
			return false;
		}
	}

	@Override
	public boolean sellerRegistered(String username, String password, Map<String, Seller> list) {
		// TODO Auto-generated method stub
		if (list.get(username) == null) {
			Seller s = new Seller();
			s.setName(username);
			s.setPassword(password);
			s.setMoney(0.0);
			Map<String, Commodity> m = new LinkedHashMap<String, Commodity>();
			s.setProductList(m);
			list.put(username, s);
			System.out.println("创建商家成功");
			return true;
		} else {
			System.out.println("昵称已被占用，创建失败");
			return false;
		}
	}

	@Override
	public boolean buyerChangePassword(String username, String Password1, String Password2, Map<String, Buyer> list) {
		// TODO Auto-generated method stub
		if (!Password1.equals(Password2)) {
			System.out.println("两次输入的密码不一致，修改密码失败");
			return false;
		} else {
			list.get(username).setPassword(Password1);
			return true;
		}
	}
	
	@Override
	public boolean sellerChangePassword(String username, String Password1, String Password2, Map<String, Seller> list) {
		// TODO Auto-generated method stub
		if (!Password1.equals(Password2)) {
			System.out.println("两次输入的密码不一致，修改密码失败");
			return false;
		} else {
			list.get(username).setPassword(Password1);
			System.out.println("修改密码成功");
			return true;
		}
	}
}
