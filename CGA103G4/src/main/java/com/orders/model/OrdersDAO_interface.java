package com.orders.model;

import java.util.List;

import com.orderdetail.model.OrderDetailVO;

public interface OrdersDAO_interface {
	public void insert(OrdersVO ordersVO);
	public void update(OrdersVO ordersVO);
	public List<OrdersVO> selectAll();
	public OrdersVO findByPrimaryKey(Integer ordid);
	
//===============================冠銓新增=================================
	
	public List<OrdersVO> selectOneMemAll(Integer ordid);
	public void modifyOrderInfo(OrdersVO ordersVO);
	public void cancelOrder(OrdersVO ordersVO);

//===============================亦翔新增=================================
	public void insert2(OrdersVO ordersVO, List<OrderDetailVO> list);
}
