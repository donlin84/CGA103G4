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


@WebServlet("/back-end/product/ListAllPdOrderByPdPriceDesc.do")
public class ListAllPdOrderByPdPriceDesc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		
		ProductService pdSvc = new ProductService();
		List<ProductVO> list = pdSvc.listByPdPriceDesc();
		System.out.println(list);
		
		String url = "/back-end/product/productSearchList2.jsp";
		req.setAttribute("listProduct_ByCompositeQuery", list); 
		RequestDispatcher successView = req.getRequestDispatcher(url); 
		successView.forward(req, res);

	}
	
	

	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doGet(req, res);
	}

}
