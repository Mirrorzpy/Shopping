package com.shopping.controller;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import com.shopping.po.Buyer;
import com.shopping.po.Seller;
import com.shopping.service.impl.ListImpl;
import com.shopping.service.impl.LoginImpl;
import com.shopping.service.impl.OperateImpl;

public class ShoppingProcess{

	public static void start() {
		Map<String, Buyer> buyer = new LinkedHashMap<String, Buyer>();
		Map<String, Seller> seller = new LinkedHashMap<String, Seller>();
		Scanner sc = new Scanner(System.in);
		LoginImpl login = new LoginImpl();
		ListImpl list = new ListImpl();
		OperateImpl operate = new OperateImpl();
		int select;
		String sellername;
		String username;
		String password;
		
		// 导入测试数据
		TestData.newData(buyer, seller);
		
		try {
			a:while (true) {
				System.out.println("\n---欢迎使用不成熟的某宝购物平台---\n");
				System.out.println("1.查询全部商品");
				System.out.println("2.查询全部商家");
				System.out.println("3.登录");
				System.out.println("4.注册");
				System.out.println("5.退出");
				select = sc.nextInt();
				if (select < 1 || select > 5) {
					System.out.println("请输入1 - 5之间的数");
					continue a;
				} else {
					if (select == 1) {
						list.productList(seller);
						continue a;
					}
					if (select == 2) {
						if (list.sellerList(seller)) {
							System.out.println("请选择商家，输入0返回主菜单：");
							b1:while (true) {
								select = sc.nextInt();
								if (select < 0 || select > seller.size()) {
									System.out.println("请输入0 - " + seller.size() + "之间的数");
									continue b1;
								} else {
									if (select == 0) {
										continue a;
									} else {
										list.sellerProductList(seller, select);
										continue a;
									}
								}
							}
						} else {
							continue a;
						}
					}
					if (select == 3) {
						System.out.println("1.商家登录");
						System.out.println("2.用户登录");
						System.out.println("3.返回主页");
						b2:while (true) {
							select = sc.nextInt();
							if (select < 1 || select > 3) {
								System.out.println("请输入1 - 3之间的数");
								continue b2;
							} else {
								if (select == 3) {
									System.out.println("正在返回主页");
									continue a;
								} else {
									System.out.println("请输入用户名和密码");
									System.out.print("username:");
									username = sc.next();
									System.out.print("password:");
									password = sc.next();
									if (select == 1 && login.sellerLogin(username, password, seller)) {
										System.out.println("欢迎回来，" + username);
										System.out.println("请选择操作");
										c2:while (true) {
											System.out.println("1.查询自己全部商品");
											System.out.println("2.增加商品");
											System.out.println("3.删除商品");
											System.out.println("4.修改商品");
											System.out.println("5.修改密码");
											System.out.println("6.查询余额");
											System.out.println("7.退出并返回主页");
											select = sc.nextInt();
											if (select < 1 || select > 7) {
												System.out.println("请输入1 - 7之间的数");
												continue c2;
											} else {
												if (select == 1) {
													list.sellerProductList(seller, username);
													continue c2;
												}
												if (select == 2) {
													System.out.println("请依次输入要增加的商品名称、商品价钱、商品数量");
													operate.sellerAdd(sc.next(), sc.nextDouble(), sc.nextInt(), seller, username);
													continue c2;
												}
												if (select == 3) {
													System.out.println("0.删除全部商品");
													list.sellerProductList(seller, username);
													System.out.println((seller.get(username).getProductList().size() + 1) +".返回登录页");
													System.out.println("请输入要删除的商品序号或执行的操作");
													d4:while (true) {
														select = sc.nextInt();
														if (select < 0 || select > (seller.get(username).getProductList().size() + 1)) {
															System.out.println("请输入0 - " + (seller.get(username).getProductList().size() + 1) + "之间的数");
															continue d4;
														} else  if (select == (seller.get(username).getProductList().size() + 1)) {
															continue c2;
														} else {
															operate.sellerDel(select, seller, username);
															continue c2;
														}
													}
												}
												if (select == 4) {
													list.sellerProductList(seller, username);
													System.out.println((seller.get(username).getProductList().size() + 1) +".返回登录页");
													System.out.println("请输入要修改的商品序号或执行的操作");
													d5:while (true) {
														select = sc.nextInt();
														if (select < 0 || select > (seller.get(username).getProductList().size() + 1)) {
															System.out.println("请输入0 - " + (seller.get(username).getProductList().size() + 1) + "之间的数");
															continue d5;
														} else  if (select == (seller.get(username).getProductList().size() + 1)) {
															continue c2;
														} else {
															System.out.println("请依次输入商品的新名称，新价钱，新数量");
															operate.sellerChange(select, sc.next(), sc.nextDouble(), sc.nextInt(), buyer, username);
															continue c2;
														}
													}
												}
												if (select == 5) {
													System.out.println("请输入要修改的密码");
													password = sc.next();
													System.out.println("请再次输入要修改的密码");
													login.sellerChangePassword(username, password, sc.next(), seller);
													continue c2;
												}
												if (select == 6) {
													System.out.println("您的余额为：" + seller.get(username).getMoney());
													continue c2;
												}
												System.out.println("正在注销并返回主页");
												continue a;
											}
										}
									}
									if (select == 2 && login.buyerLogin(username, password, buyer)) {
										System.out.println("欢迎回来，" + username);
										System.out.println("请选择操作");
										c1:while (true) {
											System.out.println("请选择操作");
											System.out.println("1.查询全部商品");
											System.out.println("2.查询全部商家（购买）");
											System.out.println("3.查询购物车（删除修改提交订单）");
											System.out.println("4.查询余额（充值）");
											System.out.println("5.修改密码");
											System.out.println("6.退出并返回主页");
											select = sc.nextInt();
											if (select < 1 || select > 6) {
												System.out.println("请输入1 - 6之间的数");
												continue c1;
											} else {
												if (select == 1) {
													list.productList(seller);
													continue c1;
												}
												if (select == 2) {
													if (list.sellerList(seller)) {
														System.out.println("请选择商家，输入0返回登录页：");
														d1:while (true) {
															select = sc.nextInt();
															if (select == 0) {
																continue c1;
															} else {
																sellername = list.sellerProductList(seller, select);
																if (sellername == null) {
																	continue d1;
																} else {
																	System.out.println("请选择商品，输入0返回登录页");
																	e1:while (true) {
																		select = sc.nextInt();
																		if (select < 0 || select > seller.get(sellername).getProductList().size()) {
																			System.out.println("请输入0 - " + seller.get(sellername).getProductList().size() + "之间的数");
																			continue e1;
																		} else if (select == 0) {
																			continue c1;
																		} else {
																			System.out.println("请输入购买数量");
																			operate.buyerAdd(sellername, select, sc.nextInt(), buyer, seller, username);
																			continue c1;
																		}
																	}
																}
															}
														}
													} else {
														continue c1;
													}
												}
												if (select == 3) {
													if (list.orderList(buyer, seller, username)) {
														d2:while (true) {
															System.out.println("请选择操作");
															System.out.println("1.提交订单");
															System.out.println("2.删除订单");
															System.out.println("3.修改订单");
															System.out.println("4.返回登录页");
															select = sc.nextInt();
															if (select < 1 || select > 4) {
																System.out.println("请输入1 - 4之间的数");
																continue d2;
															} else {
																if (select == 1) {
																	operate.submitOrder(buyer, seller, username);
																	continue c1;
																}
																if (select == 2) {
																	System.out.println("0.删除全部订单");
																	list.orderList(buyer, seller, username);
																	System.out.println((buyer.get(username).getOrderList().size() + 1) +".返回登录页");
																	System.out.println("请输入要删除的订单序号或执行的操作");
																	e2:while (true) {
																		select = sc.nextInt();
																		if (select < 0 || select > (buyer.get(username).getOrderList().size() + 1)) {
																			System.out.println("请输入0 - " + (buyer.get(username).getOrderList().size() + 1) + "之间的数");
																			continue e2;
																		} else  if (select == (buyer.get(username).getOrderList().size() + 1)) {
																			continue c1;
																		} else {
																			operate.buyerDel(select, buyer, username);
																			continue c1;
																		}
																	}
																}
																if (select == 3) {
																	list.orderList(buyer, seller, username);
																	System.out.println((buyer.get(username).getOrderList().size() + 1) +".返回登录页");
																	System.out.println("请输入要修改的订单序号或执行的操作");
																	e3:while (true) {
																		select = sc.nextInt();
																		if (select < 1 || select > (buyer.get(username).getOrderList().size() + 1)) {
																			System.out.println("请输入1 - " + (buyer.get(username).getOrderList().size() + 1) + "之间的数");
																			continue e3;
																		} else if (select == (buyer.get(username).getOrderList().size() + 1)) {
																			continue c1;
																		} else {
																			System.out.println("请输入要修改的数量");
																			operate.buyerChange(select, sc.nextInt(), buyer, seller, username);
																			continue c1;
																		}
																	}
																}
																System.out.println("正在返回登录页");
																continue c1;
															}
														}
													} else {
														continue c1;
													}
												}
												if (select == 4) {
													System.out.println("您的余额为：" + buyer.get(username).getMoney());
													d3:while (true) {
														System.out.println("请选择操作");
														System.out.println("1.充值");
														System.out.println("2.返回上一页");
														select = sc.nextInt();
														if (select == 1) {
															System.out.println("请输入存款金额");
															operate.saveMoney(sc.nextDouble(), buyer, username);
															continue c1;
														} else if (select == 2) {
															System.out.println("请输入1 - 2之间的数");
															continue c1;
														} else {
															System.out.println("请输入1 - 2之间的数");
															continue d3;
														}
													}
												}
												if (select == 5) {
													System.out.println("请输入要修改的密码");
													password = sc.next();
													System.out.println("请再次输入要修改的密码");
													login.buyerChangePassword(username, password, sc.next(), buyer);
													continue c1;
												}
												System.out.println("正在注销并返回主页");
												continue a;
											}
										}
									}
									continue a;
								}
							}
						}
					}
					if (select == 4) {
						System.out.println("1.商家注册");
						System.out.println("2.用户注册");
						System.out.println("3.返回主页");
						b3:while (true) {
							select = sc.nextInt();
							if (select < 1 || select > 3) {
								System.out.println("请输入1 - 3之间的数");
								continue b3;
							} else {
								if (select == 3) {
									System.out.println("正在返回主页");
									continue a;
								} else {
									System.out.println("请输入用户名和密码");
									System.out.print("username:");
									username = sc.next();
									System.out.print("password:");
									password = sc.next();
									if (select == 1) {
										login.sellerRegistered(username, password, seller);
									}
									if (select == 2) {
										login.buyerRegistered(username, password, buyer);
									}
									continue a;
								}
							}
						}
					}
				System.out.println("已退出");
				break a;
				}
			}
		} catch (Exception e) {
			System.out.println("输入错误");
			e.printStackTrace();
		} finally {
			sc.close();
		}
	}
}
