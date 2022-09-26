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



@WebServlet("/back-end/product/GetOneOrderForModify.do")
public class GetOneOrderForModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			
		Integer ordid = Integer.valueOf(req.getParameter("ordid"));
		
//		step2
		OrdersService ordSvc = new OrdersService();
		OrdersVO ordersVO = ordSvc.getOne(ordid);
		
//		step3
		req.setAttribute("ordersVO", ordersVO);
		String url = "/back-end/product/orderModify.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req,res);
		
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doGet(req, res);
	}

}
