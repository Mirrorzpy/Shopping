package com.shopping.service.impl;

import java.util.Iterator;
import java.util.Map;

import com.shopping.po.Buyer;
import com.shopping.po.Commodity;
import com.shopping.po.Seller;
import com.shopping.service.List;

public class ListImpl implements List {

	@Override
	public boolean productList(Map<String, Seller> list) {
		// TODO Auto-generated method stub
		if (list.size() == 0) {
			System.out.println("列表为空，暂无商户");
			return false;
		} else {
			for (Map.Entry<String, Seller> key1 : list.entrySet()) {
				System.out.println("商家名称：" + key1.getKey());
				if (key1.getValue().getProductList().size() == 0) {
					System.out.println("此商家暂无商品");
				} else {
					int i = 1;
					for (Map.Entry<String, Commodity> key2 : key1.getValue().getProductList().entrySet()) {
						System.out.println(i + ".商品名称：" + key2.getValue().getName() + "---价钱：" + key2.getValue().getMoney() + "---库存量：" + key2.getValue().getCount());
						i++;
					}
				}
			}
			return true;
		}
	}

	@Override
	public boolean sellerList(Map<String, Seller> list) {
		// TODO Auto-generated method stub
		if (list.size() == 0) {
			System.out.println("列表为空，暂无商户");
			return false;
		} else {
			int i = 1;
			for (Map.Entry<String, Seller> key : list.entrySet()) {
				System.out.println(i + ".商家名称：" + key.getKey());
				i++;
			}
			return true;
		}
	}

	@Override
	public String sellerProductList(Map<String, Seller> list, Integer id) {
		// TODO Auto-generated method stub
		if (id > 0) {
			if (id <= list.size()) {
				Iterator<Map.Entry<String, Seller>> it = list.entrySet().iterator();
				for (int i = 1; i < id; i++) {
					 it.next();
				}
				Map.Entry<String, Seller> entry = it.next();
				System.out.println("商家名称：" + entry.getKey());
				if (entry.getValue().getProductList().size() == 0) {
					System.out.println("此商家暂无商品");
				} else {
					int i = 1;
					for (Map.Entry<String, Commodity> key : entry.getValue().getProductList().entrySet()) {
						System.out.println(i + ".商品名称：" + key.getKey() + "---价钱：" + key.getValue().getMoney() + "---库存量：" + key.getValue().getCount());
						i++;
					}
				}
				return entry.getKey();
			} else {
				System.out.println("请输入0-" + list.size() + "之间的数字");
				return null;
			}
		} else {
			System.out.println("请输入0-" + list.size() + "之间的数字");
			return null;
		}
	}
	
	@Override
	public boolean sellerProductList(Map<String, Seller> list, String name) {
		// TODO Auto-generated method stub
		if (list.get(name).getProductList().size() == 0) {
			System.out.println("此商家暂无商品");
			return false;
		} else {
			int i = 1;
			for (Map.Entry<String, Commodity> key : list.get(name).getProductList().entrySet()) {
				System.out.println(i + ".商品名称：" + key.getKey() + "---价钱：" + key.getValue().getMoney() + "---库存量：" + key.getValue().getCount());
				i++;
			}
			return true;
		}
	}

	@Override
	public boolean orderList(Map<String, Buyer> listB, Map<String, Seller> listS, String name) {
		// TODO Auto-generated method stub
		if (listB.get(name).getOrderList().size() == 0) {
			System.out.println("购物车为空，查询完毕");
			return false;
		} else {
			int i = 1;
			Double sum = 0.0;
			for (Map.Entry<String, Commodity> key : listB.get(name).getOrderList().entrySet()) {
				System.out.println(i + ".商品名称：" + key.getKey() + "---现在价钱：" + listS.get(key.getValue().getOwner()).getProductList().get(key.getKey()).getMoney() +"---添加时价钱：" + key.getValue().getMoney() + "---购买量：" + key.getValue().getCount() + "---商家名称：" + key.getValue().getOwner() +"---单品总价：" + listS.get(key.getValue().getOwner()).getProductList().get(key.getKey()).getMoney() * key.getValue().getCount());
				sum = sum + listS.get(key.getValue().getOwner()).getProductList().get(key.getKey()).getMoney() * key.getValue().getCount();
				i++;
			}
			System.out.println("订单总价：" + sum);
			return true;
		}
	}
}
