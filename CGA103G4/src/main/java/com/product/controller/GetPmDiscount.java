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


@WebServlet("/back-end/product/GetPmDiscount.do")
public class GetPmDiscount extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		

		
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		Integer pdid = Integer.valueOf(req.getParameter("Pdid").trim());
		
		String pdName = req.getParameter(("PdName").trim());
		
		Integer pdPrice = null;
		try {
			pdPrice = Integer.valueOf(req.getParameter("PdPrice").trim());
		} catch (NumberFormatException e) {
			pdPrice = 0;
		}
		
		System.out.println(pdPrice);
		
		ProductVO productVO = new ProductVO();
		productVO.setPdPrice(pdPrice);
		productVO.setPdName(pdName);
		productVO.setPdid(pdid);
		
		String str = req.getParameter("pmid");
		
		Integer pmid = Integer.valueOf(str);
		System.out.println("pmid="+pmid);
		ProductService pdSvc = new ProductService();
		PromotionsVO promotionsVO = pdSvc.getPmDiscount(pmid); 
		
		
		req.setAttribute("promotionsVO", promotionsVO);
		req.setAttribute("productVO", productVO);
		
		String url = "/back-end/product/productAddWithPM.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); 
		successView.forward(req, res);
		
	
	}
}

		
	