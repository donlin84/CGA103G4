package com.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductService;
import com.product.model.ProductVO;


@WebServlet("/front-end/shop/FrontEndListPdOnShelfByPrice.do")
public class FrontEndListPdOnShelfByPrice extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		ProductService pdSvc = new ProductService();
		List<ProductVO> list = pdSvc.PdOnShelfArrangeByPrice();
		
		
		String url = "/front-end/shop/shopSearchResult.jsp";
		req.setAttribute("list", list); 
		RequestDispatcher successView = req.getRequestDispatcher(url); 
		successView.forward(req, res);
	}
	

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		doGet(req, res);
	}

}
