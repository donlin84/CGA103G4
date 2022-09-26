package com.product.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cartdetail.model.CartDetailService;
import com.cartdetail.model.CartDetailVO;

import com.google.gson.JsonObject;


@WebServlet("/front-end/shop/deleteCartPd.do")
public class DeleteCartPd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
			
		Integer memid = Integer.valueOf(req.getParameter("memid"));
		Integer pdid = Integer.valueOf(req.getParameter("pdid"));	
		
		CartDetailVO cartDetailVO = new CartDetailVO();
		
		cartDetailVO.setPdid(memid);
		cartDetailVO.setPdid(pdid);
		
		
//		addCartDetail
		
		CartDetailService CartDeSvc = new CartDetailService();
		CartDeSvc.deleteCartDeatil(memid, pdid);
		

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		
		
		doGet(req, res);
	}

}
