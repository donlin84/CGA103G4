package com.product.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.favoriteProduct.model.FavoriteProductService;
import com.favoriteProduct.model.FavoriteProductVO;
import com.product.model.ProductVO;


@WebServlet("/front-end/shop/DeleteCollection.do")
public class DeleteCollection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		

		Integer memid = Integer.valueOf(req.getParameter("memid"));
		Integer pdid = Integer.valueOf(req.getParameter("pdid"));	
		
		FavoriteProductVO favoriteProductVO = new FavoriteProductVO();
		
		favoriteProductVO.setPdid(memid);
		favoriteProductVO.setPdid(pdid);
		
		FavoriteProductService fpdSvc = new FavoriteProductService();
		fpdSvc.deleteFavoriteProduct(memid, pdid);
	
		req.setAttribute("favoriteProductVO", favoriteProductVO);
		
		String url = "/front-end/shop/ListOneUserFavoritePd.do?memid="+memid;
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req, res);

		
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doGet(req, res);
	}

}
