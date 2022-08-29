package com.orders.model;

import java.util.List;

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

}
