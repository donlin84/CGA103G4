package com.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductService;
import com.product.model.ProductVO;



public class PdSearchBackend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		doPost(req,  res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action4");
		System.out.println(action);
		if ("list_pd_by_sort".equals(action)) {
			
			String str = req.getParameter("pdsid");
			 
			Integer pdsid = Integer.valueOf(str);
			 System.out.println(pdsid);
			 
			 ProductService pdSvc = new ProductService();
			 List<ProductVO> productVO = pdSvc.listByPdSort(pdsid);
			System.out.println(productVO);
				
			String url = "/back-end/product/productSearchList.jsp";
			req.setAttribute("productVO", productVO); 
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		
		}
		
	}

}
