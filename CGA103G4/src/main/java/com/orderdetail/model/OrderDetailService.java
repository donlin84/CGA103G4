package com.orderdetail.model;

import java.util.List;

public class OrderDetailService {
	private OrderDetailDAO_interface dao;
	
	public OrderDetailService() {
		dao = new OrderDetailDAO();
	}
	
	public OrderDetailVO addOrderDetail(Integer ordid, Integer pdid, Integer detailNumber,
			Integer detailPrice, Integer detailGoodPrice) {
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		
		orderDetailVO.setOrdid(ordid);
		orderDetailVO.setPdid(pdid);
		orderDetailVO.setDetailNumber(detailNumber);
		orderDetailVO.setDetailPrice(detailPrice);
		orderDetailVO.setDetailGoodPrice(detailGoodPrice);
		
		dao.insert(orderDetailVO);
		
		return orderDetailVO;
	}
	
	public OrderDetailVO updateOrderDetail(Integer detailNumber, Integer detailPrice, 
			Integer detailGoodPrice, Integer ordid, Integer pdid) {
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		
		orderDetailVO.setDetailNumber(detailNumber);
		orderDetailVO.setDetailPrice(detailPrice);
		orderDetailVO.setDetailGoodPrice(detailGoodPrice);
		orderDetailVO.setOrdid(ordid);
		orderDetailVO.setPdid(pdid);
		
		dao.update(orderDetailVO);
		
		return orderDetailVO;
	}
	
	public OrderDetailVO getOne(Integer ordid, Integer pdid) {
		return dao.findByPrimaryKey(ordid, pdid);
	}
	
	public List<OrderDetailVO> getAll() {
		return dao.selectAll();
	}
	
//	========================冠銓新增=====================
	public List<OrderDetailVO> getAllByOrdid(Integer ordid){
		return dao.selectAllByOrdid(ordid);
	}
	
	public OrderDetailVO UpdatePdNumber(Integer detailNumber, Integer ordid, Integer pdid) {
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		
		orderDetailVO.setDetailNumber(detailNumber);
		orderDetailVO.setOrdid(ordid);
		orderDetailVO.setPdid(pdid);
		
		dao.updatePdNumber(orderDetailVO);
		
		return orderDetailVO;
	}
	
	public OrderDetailVO getOneUserAllDetailJoin(Integer ordid) {
		return dao.getOneOrderDetailByOrdid(ordid);
	}
	
	
//	========================亦翔新增=====================
	
	public List<OrderDetailVO> getOnes(Integer ordid) {
		return dao.findByPrimaryKey(ordid);
	}
}


