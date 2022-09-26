package com.product.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orders.model.OrdersService;
import com.orders.model.OrdersVO;
import com.product.model.ProductService;
import com.product.model.ProductVO;

/**
 * Servlet implementation class UserCancelOrder
 */
@WebServlet("/front-end/shop/UserCancelOrder.do")
public class UserCancelOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		Integer ordid = Integer.valueOf(req.getParameter("ordid").trim());
		Integer memid = 201;
		
		Integer ordStatus = Integer.valueOf(req.getParameter("ordStatus").trim());
		
		
		OrdersVO ordersVO = new OrdersVO();
		ordersVO.setOrdStatus(ordStatus);
		ordersVO.setOrdid(ordid);
		
		
		
		OrdersService ordSvc = new OrdersService();
		ordersVO = ordSvc.UserCancelOrder(ordStatus, ordid);
		
		String url = "/front-end/shop/ListMemberAllOrd.do?memid="+memid;
		RequestDispatcher successView = req.getRequestDispatcher(url); 
		successView.forward(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
