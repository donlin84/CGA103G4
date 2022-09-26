package com.product.controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductService;
import com.product.model.ProductVO;


@WebServlet("/back-end/product/AddPd.do")
public class GetNewestPdid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ProductVO productVO = new ProductVO();
		ProductService pdSvc = new ProductService();
		productVO = pdSvc.getNewestPdid();

		
		
		String url = "/back-end/product/productAdd.jsp";
		req.setAttribute("productVO", productVO); 
		RequestDispatcher successView = req.getRequestDispatcher(url); 
		successView.forward(req, res);
		
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		doGet(req, res);
	}
		
		
		
		
		
		
		
	}


