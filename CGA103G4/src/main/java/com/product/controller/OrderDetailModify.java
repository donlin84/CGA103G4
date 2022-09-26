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

import com.orderdetail.model.OrderDetailService;
import com.orderdetail.model.OrderDetailVO;



@WebServlet("/back-end/product/OrderDetailModify.do")
public class OrderDetailModify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		
		doPost(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		String action = req.getParameter("OrderDetailModify");
		System.out.println(action);
		if("OrderDetailModify".equals(action)) {
			
		List<String> errorMsgs = new LinkedList<String>();

		req.setAttribute("errorMsgs", errorMsgs);	
		
		
			
		Integer pdid = Integer.valueOf(req.getParameter("Pdid").trim());
		System.out.println(pdid);
		
		Integer ordid = Integer.valueOf(req.getParameter("Ordid").trim());
		System.out.println(ordid);
		
		Integer ordNumber = null;
		try {ordNumber = Integer.valueOf(req.getParameter("OrdPdNumber").trim());
		
		}catch (NumberFormatException e) {
			ordNumber = 0;
			errorMsgs.add("更改數量請輸入半形數字");
		}
		
		
		
		System.out.println(ordNumber);

		
		OrderDetailVO orderDetailVO = new OrderDetailVO();
		
		orderDetailVO.setDetailNumber(ordNumber);
		orderDetailVO.setOrdid(ordid);
		orderDetailVO.setPdid(pdid);
		
//		Step 2
		
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("orderDetailVO", orderDetailVO); // 含有輸入格式錯誤的empVO物件,也存入req
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/product/orderShowAll.jsp");
			failureView.forward(req, res);
			return;
		}

		OrderDetailService ordDetSvc = new OrderDetailService();
		orderDetailVO = ordDetSvc.UpdatePdNumber(ordNumber, ordid, pdid);
//		Step 3
		
		req.setAttribute("orderDetailVO", orderDetailVO); // 資料庫update成功後,正確的的productVO物件,存入req
		String url = "/back-end/product/orderShowAll.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
		successView.forward(req, res);
		}
		}
}

	


