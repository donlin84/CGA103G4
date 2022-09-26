package com.product.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductVO;
import com.promotions.model.PromotionsVO;
import com.promotionsdetail.model.PromotionsDetailService;
import com.promotionsdetail.model.PromotionsDetailVO;

@WebServlet("/back-end/product/PdShowAllPageAddPromo.do")
public class PdShowAllPageAddPromo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		PromotionsVO promotionsVO = (PromotionsVO) req.getAttribute("promotionsVO");
		ProductVO productVO = (ProductVO) req.getAttribute("productVO");
	
			
			Integer pdid = Integer.valueOf(productVO.getPdid());
//			System.out.println(pdid);
			Integer pmid = Integer.valueOf(promotionsVO.getPmid());
			
			Integer pdPrice = Integer.valueOf(productVO.getPdPrice());
//			System.out.println(pmid);
			Double pmPdDiscount = promotionsVO.getPmDiscount();
			Integer pmPdDiscountPrice = (int) (pdPrice * pmPdDiscount);
			
			PromotionsDetailVO promotionsDetailVO = new PromotionsDetailVO();
			 
			promotionsDetailVO.setPdid(pdid);
			System.out.println(pdid);
			promotionsDetailVO.setPmid(pmid);
			System.out.println(pmid);
			promotionsDetailVO.setPmPdDiscountPrice(pmPdDiscountPrice);
			System.out.println(pmPdDiscountPrice);
			
			
			PromotionsDetailService promoDetailSvc = new PromotionsDetailService();
			promotionsDetailVO = promoDetailSvc.addPromotionsDetail(pmid, pdid, pmPdDiscountPrice);
			System.out.println(promotionsDetailVO);
			
			String url = "/back-end/product/productShowAll.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
			
		}
		
		
		
		
	}


