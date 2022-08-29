package com.orderdetail.model;

import java.util.List;

public interface OrderDetailDAO_interface {
	public void insert(OrderDetailVO orderDetailVO);
	public void update(OrderDetailVO orderDetailVO);
	public List<OrderDetailVO> selectAll();
	public OrderDetailVO findByPrimaryKey(Integer ordid, Integer pdid);
}
