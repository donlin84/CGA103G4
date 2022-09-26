package com.product.controller;

import java.io.IOException;
import java.time.LocalDateTime;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orders.model.OrdersService;
import com.orders.model.OrdersVO;



/**
 * Servlet implementation class OrderDetailModify
 */
@WebServlet("/back-end/product/OrderInfoModify.do")
public class OrderInfoModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		doPost(req, res);
	}
//	private Integer ordid;
//	private Integer memid;
//	private Integer memCpid;
//	private Integer ordSubTotal;
//	private Integer ordTotal;
//	private Integer ordStatus;
//	private LocalDateTime ordCreate;
//	private String ordRecipient;
//	private String recPhone;
//	private Integer ordPayment;
//	private Integer ordDelivery;
//	private String ordAddress;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		String OrderModify = req.getParameter("OrderModify");
		System.out.println(OrderModify);
		if("OrderModify".equals(OrderModify)) {
		
		Integer ordid = Integer.valueOf(req.getParameter("Ordid").trim());
		System.out.println(ordid);
		
	
		String ordRecipient = req.getParameter(("OrdRecipient").trim());
		
		System.out.println(ordRecipient);
		String recPhone = req.getParameter(("RecPhone").trim());
		
		Integer ordDelivery = Integer.valueOf(req.getParameter("OrdDelivery").trim());
		
		System.out.println(ordDelivery);
		
		String ordAddress = req.getParameter(("OrdAddress").trim());
		System.out.println("add"+ordAddress);
		
		OrdersVO ordersVO = new OrdersVO();

		ordersVO.setOrdRecipient(ordRecipient);
		ordersVO.setRecPhone(recPhone);

		ordersVO.setOrdDelivery(ordDelivery);
		ordersVO.setOrdAddress(ordAddress);
		ordersVO.setOrdid(ordid);
		
		OrdersService pdSvc = new OrdersService();
		ordersVO = pdSvc.ModifyOrdInfo(ordRecipient, recPhone, ordDelivery, ordAddress, ordid);
		
		req.setAttribute("ordersVO", ordersVO); // 資料庫update成功後,正確的的productVO物件,存入req
		String url = "/back-end/product/orderShowAll.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
		successView.forward(req, res);
		}
		}
}

	


