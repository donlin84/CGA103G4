package com.cartdetail.model;

import java.util.List;

public class CartDetailService {
	private CartDetailDAO_interface dao;
	
	public CartDetailService() {
		dao = new CartDetailDAO();
	}
	
	public CartDetailVO addCartDetail(Integer memid, Integer pdid, Integer pdNumber) {
		CartDetailVO cartDetailVO = new CartDetailVO();
		
		cartDetailVO.setMemid(memid);
		cartDetailVO.setPdid(pdid);
		cartDetailVO.setPdNumber(pdNumber);
		
		dao.insert(cartDetailVO);
		
		return cartDetailVO;
	}
	
	public CartDetailVO updateCartDetail(Integer pdNumber, Integer memid, Integer pdid) {
		CartDetailVO cartDetailVO = new CartDetailVO();
		
		cartDetailVO.setPdNumber(pdNumber);
		cartDetailVO.setMemid(memid);
		cartDetailVO.setPdid(pdid);
		
		dao.update(cartDetailVO);
		
		return cartDetailVO;
	}
	
	public CartDetailVO getOne(Integer memid, Integer pdid) {
		return dao.findByPrimaryKey(memid, pdid);
	}
	
	public List<CartDetailVO> getAll() {
		return dao.selectAll();
	}
	
	public void deleteCartDeatil(Integer memid, Integer pdid) {
		dao.delete(memid, pdid);
	}
	
//	=============================================亦翔新增==================================================
	
	public CartDetailVO minus(Integer memid, Integer pdid) {
		CartDetailVO cartDetailVO = new CartDetailVO();
		
		cartDetailVO.setMemid(memid);
		cartDetailVO.setPdid(pdid);
		
		dao.minus(cartDetailVO);
		
		return cartDetailVO;
	}
	
	public CartDetailVO plus(Integer memid, Integer pdid) {
		CartDetailVO cartDetailVO = new CartDetailVO();
		
		cartDetailVO.setMemid(memid);
		cartDetailVO.setPdid(pdid);
		
		dao.plus(cartDetailVO);
		
		return cartDetailVO;
	}
	
	public List<CartDetailVO> getOnes(Integer memid) {
		return dao.findByPrimaryKey(memid);
	}
}
