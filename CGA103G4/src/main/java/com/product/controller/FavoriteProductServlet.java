package com.product.controller;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.favoriteProduct.model.FavoriteProductService;
import com.favoriteProduct.model.FavoriteProductVO;
import com.google.gson.JsonObject;

@WebServlet("/front-end/shop/FavoriteProductServlet.do")
public class FavoriteProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   



	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		Integer pdid = Integer.valueOf(req.getParameter("pdid"));	
		Integer memid = Integer.valueOf(req.getParameter("memid"));
		System.out.println(pdid);
		FavoriteProductVO favoriteProductVO = new FavoriteProductVO();
		
		favoriteProductVO.setPdid(memid);
		favoriteProductVO.setPdid(pdid);


		
		FavoriteProductService fpdSvc = new FavoriteProductService();
		favoriteProductVO = fpdSvc.addFavoriteProduct(pdid, memid);
		req.setAttribute("favoriteProductVO", favoriteProductVO);
		
		JsonObject json = new JsonObject();
		res.getWriter().print(json);

}



	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		doGet(req, res);
	}

}
