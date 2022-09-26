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


@WebServlet("/front-end/shop/FrontEndPdDetail.do")
public class FrontEndPdDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		

			Integer pdid = Integer.valueOf(req.getParameter("pdid"));	
			
			ProductService pdSvc = new ProductService();
			ProductVO productVO = pdSvc.getOneproduct(pdid);
			
			req.setAttribute("productVO", productVO);
			String url = "/front-end/shop/oneProductDetail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req,res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		doGet(req, res);
	}

}
