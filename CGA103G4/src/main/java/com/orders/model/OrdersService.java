package com.orders.model;


import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import com.orderdetail.model.OrderDetailVO;



public class OrdersService {
	private OrdersDAO_interface dao;

	public OrdersService() {
		dao = new OrdersDAO();
	}

	public OrdersVO addOrder(Integer memid, Integer memCpid, Integer ordSubTotal, Integer ordTotal, String ordRecipient,
			String recPhone, Integer ordPayment, Integer ordDelivery, String ordAddress) {

		OrdersVO ordersVO = new OrdersVO();

//		ordersVO.setOrdid(ordid);
		ordersVO.setMemid(memid);
		ordersVO.setMemCpid(memCpid);
		ordersVO.setOrdSubTotal(ordSubTotal);
		ordersVO.setOrdTotal(ordTotal);
//		ordersVO.setOrdStatus(ordStatus);
//		ordersVO.setOrdCreate(ordCreate);
		ordersVO.setOrdRecipient(ordRecipient);
		ordersVO.setRecPhone(recPhone);
		ordersVO.setOrdPayment(ordPayment);
		ordersVO.setOrdDelivery(ordDelivery);
		ordersVO.setOrdAddress(ordAddress);
//		ordersVO.setOrdHopetime(ordHopetime);
		dao.insert(ordersVO);

		return ordersVO;
	}

	public OrdersVO updateOrder(Integer ordid, Integer memid, Integer memCpid, Integer ordSubTotal, Integer ordTotal,
			Integer ordStatus, java.time.LocalDateTime ordCreate, String ordRecipient, String recPhone,
			Integer ordPayment, Integer ordDelivery, String ordAddress) {

		OrdersVO ordersVO = new OrdersVO();

		ordersVO.setOrdid(ordid);
		ordersVO.setMemid(memid);
		ordersVO.setMemCpid(memCpid);
		ordersVO.setOrdSubTotal(ordSubTotal);
		ordersVO.setOrdTotal(ordTotal);
		ordersVO.setOrdStatus(ordStatus);
		ordersVO.setOrdCreate(ordCreate);
		ordersVO.setOrdRecipient(ordRecipient);
		ordersVO.setRecPhone(recPhone);
		ordersVO.setOrdPayment(ordPayment);
		ordersVO.setOrdDelivery(ordDelivery);
		ordersVO.setOrdAddress(ordAddress);
//		ordersVO.setOrdHopetime(ordHopetime);
		dao.update(ordersVO);

		return ordersVO;
	}

	public OrdersVO getOne(Integer ordid) {
		return dao.findByPrimaryKey(ordid);
	}

	public List<OrdersVO> getAll() {
		return dao.selectAll();
	}
//==========================================冠銓新增========================================
	public List<OrdersVO> getOneUserAllOrd(Integer ordid){
		return dao.selectOneMemAll(ordid);
	}
	public OrdersVO ModifyOrdInfo (String recipient, String recPhone, Integer ordDelivery,String ordAddress, Integer ordid 
			) {
//		pstmt.setString(1, ordersVO.getOrdRecipient());
//		pstmt.setString(2, ordersVO.getRecPhone());
//		pstmt.setInt(3, ordersVO.getOrdDelivery());
//		pstmt.setString(4, ordersVO.getOrdAddress());
//		pstmt.setInt(5, ordersVO.getOrdid());
		
		OrdersVO ordersVO = new OrdersVO();
		ordersVO.setOrdRecipient(recipient);
		ordersVO.setRecPhone(recPhone);
		ordersVO.setOrdDelivery(ordDelivery);
		ordersVO.setOrdAddress(ordAddress);
		ordersVO.setOrdid(ordid);
		dao.modifyOrderInfo(ordersVO);

		return ordersVO;

	}
	public OrdersVO UserCancelOrder (Integer ordStatus, Integer ordid){

		OrdersVO ordersVO = new OrdersVO();

		ordersVO.setOrdStatus(ordStatus);
		ordersVO.setOrdid(ordid);
//		ordersVO.setOrdHopetime(ordHopetime);
		dao.cancelOrder(ordersVO);

		return ordersVO;
	}
	
//==========================================亦翔新增========================================
	
	public OrdersVO addOrder2(Integer memid, Integer memCpid, Integer ordSubTotal, Integer ordTotal, String ordRecipient,
			String recPhone, Integer ordPayment, Integer ordDelivery, String ordAddress, JSONArray pdid, JSONArray detailNumber,
			JSONArray detailPrice, JSONArray detailGood) {

		OrdersVO ordersVO = new OrdersVO();
		
		ordersVO.setMemid(memid);
		ordersVO.setMemCpid(memCpid);
		ordersVO.setOrdSubTotal(ordSubTotal);
		ordersVO.setOrdTotal(ordTotal);
		ordersVO.setOrdRecipient(ordRecipient);
		ordersVO.setRecPhone(recPhone);
		ordersVO.setOrdPayment(ordPayment);
		ordersVO.setOrdDelivery(ordDelivery);
		ordersVO.setOrdAddress(ordAddress);
		
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		
		for(int i = 0; i < pdid.length(); i++) {
			OrderDetailVO orderDetailVO = new OrderDetailVO();
			try {
				orderDetailVO.setPdid(pdid.getInt(i));
				orderDetailVO.setDetailNumber(detailNumber.getInt(i));
				orderDetailVO.setDetailPrice(detailPrice.getInt(i));
				orderDetailVO.setDetailGoodPrice(detailGood.getInt(i));
			} catch (JSONException e) {
				e.printStackTrace();
			}
			list.add(orderDetailVO);
		}
		
		dao.insert2(ordersVO, list);

		return ordersVO;
	}
}
