package com.shopping.service.impl;

import java.util.Iterator;
import java.util.Map;

import com.shopping.po.Buyer;
import com.shopping.po.Commodity;
import com.shopping.po.Seller;
import com.shopping.service.Operate;

public class OperateImpl implements Operate {

	@Override
	public boolean buyerAdd(String name, Integer id, Integer count, Map<String, Buyer> listB, Map<String, Seller> listS,
			String owner) {
		// TODO Auto-generated method stub
		if (count < 1) {
			System.out.println("购买数量错误，添加购物车失败");
			return false;
		} else {
			Iterator<Map.Entry<String, Commodity>> it = listS.get(name).getProductList().entrySet().iterator();
			for (int i = 1; i < id; i++) {
				it.next();
			}
			Map.Entry<String, Commodity> entry = it.next();
			if (entry.getValue().getCount() < count) {
				System.out.println("库存不足，添加购物车失败");
				return false;
			} else {
				Commodity co = new Commodity();
				co.setName(entry.getValue().getName());
				co.setMoney(entry.getValue().getMoney());
				co.setOwner(entry.getValue().getOwner());
				if (listB.get(owner).getOrderList().get(entry.getKey()) == null) {
					co.setCount(count);
				} else {
					co.setCount(count + listB.get(owner).getOrderList().get(entry.getKey()).getCount());
				}
				listB.get(owner).getOrderList().put(entry.getKey(), co);
				System.out.println("添加购物车成功");
				return true;
			}
		}
	}

	@Override
	public boolean sellerAdd(String name, Double money, Integer count, Map<String, Seller> list, String owner) {
		// TODO Auto-generated method stub
		if (list.get(owner).getProductList().get(name) != null) {
			System.out.println("此商品已存在，添加商品失败");
			return false;
		} else if (count < 0 || money <= 0) {
			System.out.println("商品信息有误，添加商品失败");
			return false;
		} else {
			Commodity co = new Commodity();
			co.setName(name);
			co.setMoney(money);
			co.setCount(count);
			co.setOwner(owner);
			list.get(owner).getProductList().put(name, co);
			System.out.println("添加商品成功");
			return true;
		}
	}

	@Override
	public boolean buyerDel(Integer id, Map<String, Buyer> list, String owner) {
		// TODO Auto-generated method stub
		if (list.get(owner).getOrderList().size() == 0) {
			System.out.println("您的订单已经为空，清空订单失败");
			return false;
//		} else if (id > (list.get(owner).getOrderList().size() + 1) || id < 0) {
//			System.out.println("输入编号错误，删除条目失败");
//			return false;
		} else if (id == 0) {
			list.get(owner).getOrderList().clear();
			System.out.println("清空订单成功");
			return true;
		} else {
			Iterator<Map.Entry<String, Commodity>> it = list.get(owner).getOrderList().entrySet().iterator();
			for (int i = 1; i < id; i++) {
				it.next();
			}
			Map.Entry<String, Commodity> entry = it.next();
			list.get(owner).getOrderList().remove(entry.getValue().getName());
			System.out.println("删除条目成功");
			return true;
		}
	}

	@Override
	public boolean sellerDel(Integer id, Map<String, Seller> list, String owner) {
		// TODO Auto-generated method stub
		if (list.get(owner).getProductList().size() == 0) {
			System.out.println("您的商品列表已经为空，清空商品失败");
			return false;
//		} else if (id > (list.get(owner).getProductList().size() + 1) || id < 0) {
//			System.out.println("输入编号错误，删除条目失败");
//			return false;
		} else if (id == 0) {
			list.get(owner).getProductList().clear();
			System.out.println("清空商品成功");
			return true;
		} else {
			Iterator<Map.Entry<String, Commodity>> it = list.get(owner).getProductList().entrySet().iterator();
			for (int i = 1; i < id; i++) {
				it.next();
			}
			Map.Entry<String, Commodity> entry = it.next();
			list.get(owner).getProductList().remove(entry.getValue().getName());
			System.out.println("删除条目成功");
			return true;
		}
	}

	@Override
	public boolean buyerChange(Integer id, Integer count, Map<String, Buyer> listB, Map<String, Seller> listS, String owner) {
		// TODO Auto-generated method stub
		if (count < 0) {
			System.out.println("数量错误，修改订单失败");
			return false;
		} else {
			Iterator<Map.Entry<String, Commodity>> it = listB.get(owner).getOrderList().entrySet().iterator();
			for (int i = 1; i < id; i++) {
				it.next();
			}
			Map.Entry<String, Commodity> entry = it.next();
			if (count > listS.get(entry.getValue().getOwner()).getProductList().get(entry.getValue().getName()).getCount()) {
				System.out.println("商家库存不足，修改订单失败");
				return false;
			} else {
				entry.getValue().setCount(count);
				System.out.println("修改订单成功");
				return true;
			}
		}
	}

	@Override
	public boolean sellerChange(Integer id, String name, Double money, Integer count, Map<String, Buyer> list, String owner) {
		// TODO Auto-generated method stub
		if (list.get(owner).getOrderList().get(name) != null) {
			System.out.println("商品名称已存在，修改商品失败");
			return false;
		} else if (money < 0 || count <= 0) {
			System.out.println("商品信息错误，修改失败");
			return false;
		} else {
			Iterator<Map.Entry<String, Commodity>> it = list.get(owner).getOrderList().entrySet().iterator();
			for (int i = 1; i < id; i++) {
				it.next();
			}
			Map.Entry<String, Commodity> entry = it.next();
			Commodity co = new Commodity();
			co.setName(name);
			co.setMoney(money);
			co.setCount(count);
			co.setOwner(owner);
			list.get(owner).getOrderList().remove(entry.getKey());
			list.get(owner).getOrderList().put(name, co);
			System.out.println("修改商品信息成功");
			return true;
		}
	}

	@Override
	public boolean submitOrder(Map<String, Buyer> listB, Map<String, Seller> listS, String owner) {
		// TODO Auto-generated method stub
		if (listB.get(owner).getOrderList().size() == 0) {
			System.out.println("订单为空，提交订单失败");
			return false;
		} else {
			Double sum = 0.0;
			for (Map.Entry<String, Commodity> key : listB.get(owner).getOrderList().entrySet()) {
				if (key.getValue().getCount() > listS.get(key.getValue().getOwner()).getProductList().get(key.getValue().getName()).getCount()) {
					System.out.println("商家" + key.getValue().getOwner() + "的"+ key.getValue().getName() +"库存不足，请修改订单");
					return false;
				} else {
					sum += key.getValue().getCount() * listS.get(key.getValue().getOwner()).getProductList().get(key.getValue().getName()).getMoney();
				}
			}
			if (sum > listB.get(owner).getMoney()) {
				System.out.println("余额不足，提交订单失败");
				return false;
			} else {
				for (Map.Entry<String, Commodity> key : listB.get(owner).getOrderList().entrySet()) {
					listS.get(key.getValue().getOwner()).setMoney(listS.get(key.getValue().getOwner()).getMoney() + key.getValue().getCount() * listS.get(key.getValue().getOwner()).getProductList().get(key.getValue().getName()).getMoney());
					listS.get(key.getValue().getOwner()).getProductList().get(key.getValue().getName()).setCount(listS.get(key.getValue().getOwner()).getProductList().get(key.getValue().getName()).getCount() - key.getValue().getCount());
				}
				listB.get(owner).setMoney(listB.get(owner).getMoney() - sum);
				listB.get(owner).getOrderList().clear();
				System.out.println("订单提交成功");
				System.out.println("共支付：" + sum + "元，余额：" + listB.get(owner).getMoney() + "元");
				System.out.println("您的商品已经发货，请耐心等待");
				return true;
			}
		}
	}

	@Override
	public boolean saveMoney(Double money, Map<String, Buyer> list, String owner) {
		// TODO Auto-generated method stub
		if (money > 0) {
			list.get(owner).setMoney(list.get(owner).getMoney() + money);
			System.out.println("存款" +money + "元成功，您的余额为：" + list.get(owner).getMoney());
			return true;
		} else {
			System.out.println("存款失败");
			return false;
		}
	}
}
