package com.product.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.product.model.ProductService;
import com.product.model.ProductVO;


@WebServlet("/back-end/product/PdSearchBackend.do")
public class PdSearchBackend extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		doPost(req,  res);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action4");
//		System.out.println("NowDoing:"+action);
		
		
//		req.setAttribute("errorMsgs", errorMsgs);
		
		if ("list_pd_by_sort".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			String str = req.getParameter("pdsid");
			
			 
			Integer pdsid = Integer.valueOf(str);
			 System.out.println(pdsid);
			 
			 ProductService pdSvc = new ProductService();
			 List<ProductVO> productVO = pdSvc.listByPdSort(pdsid);
			System.out.println(productVO);
			if(productVO.isEmpty()) {
				errorMsgs.add("此類別目前無資料");
			}
				
			String url = "/back-end/product/productSearchList.jsp";
			req.setAttribute("productVO", productVO); 
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		
		}
		if ("list_pd_by_status".equals(action)) {
			String str = req.getParameter("pdStatus");
			 
			Integer pdStatus = Integer.valueOf(str);
			 System.out.println(pdStatus);
			 
			 
			ProductService pdSvc = new ProductService();
			List<ProductVO> productVO = pdSvc.listByPdStatus(pdStatus); 

			 
			String url = "/back-end/product/productSearchList.jsp";
			req.setAttribute("productVO", productVO); 
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);
		}
		if ("listProduct_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
				HttpSession session = req.getSession();
				Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
				
				// 以下的 if 區塊只對第一次執行時有效
				if (req.getParameter("whichPage") == null){
					Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
					session.setAttribute("map",map1);
					map = map1;
				} 
				
				/***************************2.開始複合查詢***************************************/
				ProductService pdSvc = new ProductService();
				List<ProductVO> list  = pdSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listProduct_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/product/productSearchList2.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
		}
			
	}

}
