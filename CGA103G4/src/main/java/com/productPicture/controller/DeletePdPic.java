package com.productPicture.controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.product.model.ProductService;
import com.product.model.ProductVO;
import com.productPicture.model.ProductpicService;


@WebServlet("/back-end/product/DeletePdPic.do")
public class DeletePdPic extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String action = req.getParameter("action");
		
		if ("deletePdPic".equals(action)) { // 來自listAllEmp.jsp
			
				/***************************1.接收請求參數***************************************/
				Integer pdPicid = Integer.valueOf(req.getParameter("pdPicid"));
				
				String str = null;
				if (req.getAttribute("pdid")!=null) {
					str = (String) req.getAttribute("pdid");
				}else {
					str = req.getParameter("pdid");}
			
				Integer pdid = Integer.valueOf(str);

				/***************************2.開始刪除資料***************************************/
				ProductpicService pdpicSvc = new ProductpicService();
				pdpicSvc.deletePicture(pdPicid);
				
				ProductService pdSvc = new ProductService();
				ProductVO productVO = pdSvc.getOneproduct(pdid);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				req.setAttribute("productVO", productVO);
				
				String url = "/back-end/product/productModify.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

	}

}
}
