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

/**
 * Servlet implementation class FrontEndIndexShowPdBSort
 */
@WebServlet("/front-end/shop/FrontEndIndexShowPdBSort.do")
public class FrontEndIndexShowPdBSort extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String str = req.getParameter("pdsid");
		Integer pdsid = Integer.valueOf(str);
		System.out.println(pdsid);
		
		ProductService pdSvc = new ProductService();
		List<ProductVO> list = pdSvc.listByPdSort(pdsid);
		System.out.println(list);
		
		String url = "/front-end/shop/shopSearchResult.jsp";
		req.setAttribute("list", list); 
		RequestDispatcher successView = req.getRequestDispatcher(url); 
		successView.forward(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, res);
	}

}
