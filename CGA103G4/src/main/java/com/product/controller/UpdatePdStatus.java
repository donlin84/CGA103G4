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

/**
 * Servlet implementation class UpdatePdStatus
 */
@WebServlet("/back-end/product/UpdatePdStatus.do")
public class UpdatePdStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
	
		doPost(req, res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String updatePdStatus = req.getParameter("UpdatePdStatus");
		System.out.println(updatePdStatus);
		if("updatePdStatus".equals(updatePdStatus)) {
			
			
		Integer pdid = Integer.valueOf(req.getParameter("Pdid").trim());
		System.out.println(pdid);
		
		Integer pdStatus = Integer.valueOf(req.getParameter("PdStatus").trim());
		
		
		ProductVO productVO = new ProductVO();
		productVO.setPdStatus(pdStatus);
		productVO.setPdid(pdid);

		
		
		ProductService pdSvc = new ProductService();
		productVO = pdSvc.updateProduct(pdStatus, pdid);
		
		String url = "/back-end/product/productShowAll.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); 
		successView.forward(req, res);
	}

	}
}
