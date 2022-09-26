package com.product.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.promotionsdetail.model.PromotionsDetailService;


@WebServlet("/back-end/product/DeleteOnePromoDetailByPdid.do")
public class DeleteOnePromoDetailByPdid extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
	
		doPost(req, res);
	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		Integer pdid = Integer.valueOf(req.getParameter("Pdid"));	
		
		PromotionsDetailService promoDeSVC = new PromotionsDetailService();
		promoDeSVC.DeleteOneDetailByPdid(pdid);
		
		String url = "/back-end/product/productShowAll.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req,res);
		
	}

}
