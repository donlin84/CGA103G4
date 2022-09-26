package com.product.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductService;
import com.product.model.ProductVO;


@WebServlet("/front-end/shop/FrontEndListAllPdByName.do")
public class FrontEndListAllPdByName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		
		String pdName = null;
		
		pdName ="%" + req.getParameter(("pdName").trim()) +"%";
		System.out.println(pdName);
		
		String pdNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (pdName == null || pdName.trim().length() == 0) {
				errorMsgs.add("商品名稱請勿空白");
			} else if(!pdName.trim().matches(pdNameReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            } 
			
			ProductService pdSvc = new ProductService();
			List<ProductVO> list = pdSvc.ListAllPdByName(pdName);
			
			req.setAttribute("list", list);
			String url = "/front-end/shop/shopSearchResult.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req,res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		doGet(req, res);
	}

}
