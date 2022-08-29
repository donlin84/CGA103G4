package com.orders.model;

import java.util.List;

public interface OrdersDAO_interface {
	public void insert(OrdersVO ordersVO);
	public void update(OrdersVO ordersVO);
	public List<OrdersVO> selectAll();
	public OrdersVO findByPrimaryKey(Integer ordid);
}
