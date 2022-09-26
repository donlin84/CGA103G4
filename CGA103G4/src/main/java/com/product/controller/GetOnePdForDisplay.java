package com.product.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductService;
import com.product.model.ProductVO;


@WebServlet("/back-end/product/GetOnePdForDisplay.do")
public class GetOnePdForDisplay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doPost(req, res);	
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String action = req.getParameter("getOneForDisplay"); 
		
		if ("getOneForDisplay".equals(action)) {
			List<String>errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
//			step1
			
			Integer pdid = Integer.valueOf(req.getParameter("pdid"));
		
//			step2
			ProductService pdSvc = new ProductService();
			ProductVO productVO2 = pdSvc.getOneproduct(pdid);
			
//			step3
			req.setAttribute("productVO2", productVO2);
			String url = "/back-end/product/productShowOne.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req,res);
			
		
	}

}
}
