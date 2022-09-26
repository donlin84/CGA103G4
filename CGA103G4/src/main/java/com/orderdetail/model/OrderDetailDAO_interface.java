package com.orderdetail.model;

import java.sql.Connection;
import java.util.List;

public interface OrderDetailDAO_interface {
	public void insert(OrderDetailVO orderDetailVO);
	public void update(OrderDetailVO orderDetailVO);
	public List<OrderDetailVO> selectAll();
	public OrderDetailVO findByPrimaryKey(Integer ordid, Integer pdid);
//	========================冠銓新增=====================
	public List<OrderDetailVO> selectAllByOrdid(Integer ordid);
	public OrderDetailVO getOneOrderDetailByOrdid(Integer ordid);
	public void updatePdNumber(OrderDetailVO orderDetailVO);
//	========================亦翔新增=====================
	public void insert2(OrderDetailVO orderDetailVO, Connection con);
	public List<OrderDetailVO> findByPrimaryKey(Integer ordid);
}
