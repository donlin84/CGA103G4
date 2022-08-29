package com.cartdetail.model;

import java.util.List;

public interface CartDetailDAO_interface {
	public void insert(CartDetailVO cartDetailVO);
	public void update(CartDetailVO cartDetailVO);
	public List<CartDetailVO> selectAll();
	public CartDetailVO findByPrimaryKey(Integer memid, Integer pdid);
	public void delete(Integer memid, Integer pdid);
}
