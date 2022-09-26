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


@WebServlet("/front-end/shop/AddIntoCart.do")
public class AddIntoCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		Integer pdid = Integer.valueOf(req.getParameter("pdid"));	
		System.out.println(pdid);
		Integer memid = Integer.valueOf(req.getParameter("memid"));
		Integer pdNumber = Integer.valueOf(req.getParameter("pdNumber"));
		
		System.out.println(pdid);
		System.out.println(memid);
		System.out.println(pdNumber);
		
		CartDetailVO cartDetailVO = new CartDetailVO();
		
		cartDetailVO.setPdid(memid);
		cartDetailVO.setPdid(pdid);
		cartDetailVO.setPdNumber(pdNumber);
		
//		addCartDetail
		
		CartDetailService CartDeSvc = new CartDetailService();
		cartDetailVO = CartDeSvc.addCartDetail(memid, pdid, pdNumber);
		req.setAttribute("cartDetailVO", cartDetailVO);
		
		JsonObject json = new JsonObject();
		res.getWriter().print(json);

}
		
	

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		doGet(req, res);
	}

}
