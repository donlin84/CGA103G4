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
import com.promotionsdetail.model.PromotionsDetailService;
import com.promotionsdetail.model.PromotionsDetailVO;


@WebServlet("/front-end/shop/FrontEndListPdOnShelfByPromo.do")
public class FrontEndListPdOnShelfByPromo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		Integer pmid = Integer.valueOf(req.getParameter("Pmid").trim());
		

		PromotionsDetailService pmDeSvc = new PromotionsDetailService();
		List<PromotionsDetailVO> promotionsDetailVO = pmDeSvc.getParticipatePromotionsProduct(pmid);
				
		
		String url = "/front-end/shop/shopSearchResultPromo.jsp";
		req.setAttribute("promotionsDetailVO", promotionsDetailVO); 
		RequestDispatcher successView = req.getRequestDispatcher(url); 
		successView.forward(req, res);
	}
	

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		doGet(req, res);
	}

}
