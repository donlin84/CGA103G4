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

import com.promotions.model.PromotionsVO;


@WebServlet("/back-end/product/PdShowAllPageGetPmDiscount.do")
public class PdShowAllPageGetPmDiscount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		

		
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		String PromoAdd = req.getParameter("PromoAdd");
		if ("PromoAdd".equals(PromoAdd)) {
			System.out.println(PromoAdd);

		Integer pdid = Integer.valueOf(req.getParameter("Pdid").trim());
		System.out.println(pdid);
		Integer pdPrice = Integer.valueOf(req.getParameter("PdPrice").trim());
		System.out.println(pdPrice);
		ProductVO productVO = new ProductVO();
		
		productVO.setPdPrice(pdPrice);
		productVO.setPdid(pdid);
		
		Integer pmid = Integer.valueOf(req.getParameter("Pmid"));
		
		System.out.println("pmid="+pmid);
		
		ProductService pdSvc = new ProductService();
		PromotionsVO promotionsVO = pdSvc.getPmDiscount(pmid); 
		promotionsVO.setPmid(pmid);
		
		System.out.println("pmid="+pmid);
		
		req.setAttribute("promotionsVO", promotionsVO);
		req.setAttribute("productVO", productVO);
		
		String url = "/back-end/product/PdShowAllPageAddPromo.do";
		RequestDispatcher successView = req.getRequestDispatcher(url); 
		successView.forward(req, res);
		
	}
	}
	
}

		
	