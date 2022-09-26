package com.product.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.favoriteProduct.model.FavoriteProductService;
import com.favoriteProduct.model.FavoriteProductVO;


/**
 * Servlet implementation class ListOneUserFavoritePd
 */
@WebServlet("/front-end/shop/ListOneUserFavoritePd.do")
public class ListOneUserFavoritePd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		
		String str = req.getParameter("memid");
		Integer memid = Integer.valueOf(str);
		System.out.println(memid);
		
		FavoriteProductService fpdSvc = new FavoriteProductService();
		List<FavoriteProductVO> favoriteProductVO = fpdSvc.getAllFavoriteOneUser(memid);
		System.out.println(favoriteProductVO);
		
		String url = "/front-end/shop/UserPdCollection.jsp";
		req.setAttribute("favoriteProductVO", favoriteProductVO); 
		RequestDispatcher successView = req.getRequestDispatcher(url); 
		successView.forward(req, res);
		
		
		
		doPost(req, res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		
	}
}
