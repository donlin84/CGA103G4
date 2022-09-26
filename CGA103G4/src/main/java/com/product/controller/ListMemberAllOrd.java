package com.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orders.model.OrdersService;
import com.orders.model.OrdersVO;


/**
 * Servlet implementation class ListMemberAllOrd
 */
@WebServlet("/front-end/shop/ListMemberAllOrd.do")
public class ListMemberAllOrd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Integer memid = Integer.valueOf(req.getParameter("memid"));
		
		OrdersService ordSvc = new OrdersService();
		
		List<OrdersVO> ordersVO = ordSvc.getOneUserAllOrd(memid);
		
		String url = "/front-end/shop/UserOrderManagement.jsp";
		req.setAttribute("ordersVO", ordersVO); 
		RequestDispatcher successView = req.getRequestDispatcher(url); 
		successView.forward(req, res);
		
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String GoToMyCollection = req.getParameter("GoToMyOrders");
		if ("GoToMyOrders".equals(GoToMyCollection)) {
		Integer memid = Integer.valueOf(req.getParameter("memid"));
		
		OrdersService ordSvc = new OrdersService();
		
		List<OrdersVO> ordersVO = ordSvc.getOneUserAllOrd(memid);
		
		String url = "/front-end/shop/UserOrderManagement.jsp";
		req.setAttribute("ordersVO", ordersVO); 
		RequestDispatcher successView = req.getRequestDispatcher(url); 
		successView.forward(req, res);
	}
	}
}
