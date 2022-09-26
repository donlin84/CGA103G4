package com.cartdetail.model;

import java.sql.Connection;
import java.util.List;

public interface CartDetailDAO_interface {
	public void insert(CartDetailVO cartDetailVO);
	public void update(CartDetailVO cartDetailVO);
	public List<CartDetailVO> selectAll();
	public List<CartDetailVO> findByPrimaryKey(Integer memid);
	public CartDetailVO findByPrimaryKey(Integer memid, Integer pdid);
	public void delete(Integer memid, Integer pdid);
	public void delete2(Integer memid, Connection con);
	public void minus(CartDetailVO cartDetailVO);
	public void plus(CartDetailVO cartDetailVO);
}
