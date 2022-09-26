package com.product.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

		String pdidstr = req.getParameter("pdid");
		  Integer pdid = Integer.valueOf(pdidstr); 
		  Integer memid = Integer.valueOf(req.getParameter("memid"));
		  Integer pdNumber = Integer.valueOf(req.getParameter("pdNumber"));
		  String cartStr = req.getParameter("cartPdids");
		  System.out.println(cartStr);
		  List<Integer> cartlist = new ArrayList<Integer>();
		  if(cartStr.contains(pdidstr)) {
		   CartDetailService cartSvc = new CartDetailService();
		   cartSvc.plus(memid, pdid);
		   
		   JsonObject json = new JsonObject();
		   try {
		    res.getWriter().print(json);
		   } catch (IOException e1) {
		    e1.printStackTrace();
		   }
		   return;
		  } else {
		   CartDetailService cartSvc = new CartDetailService();
		   cartSvc.addCartDetail(memid, pdid, pdNumber);
		   
		   JsonObject json = new JsonObject();
		   try {
		    res.getWriter().print(json);
		   } catch (IOException e1) {
		    e1.printStackTrace();
		   }
		   return;
		  }

}
		
	

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		doGet(req, res);
	}

}
