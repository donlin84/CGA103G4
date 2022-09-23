package com.chef.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.ClassIfm.model.ClassIfmService;
import com.ClassIfm.model.ClassIfmVO;
import com.chef.model.*;


@WebServlet("/front-end/chef/frontEndChef.do")
public class FrontEndChefServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
//============================================================================
		
				if ("listChef_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
					List<String> errorMsgs = new LinkedList<String>();
					
					req.setAttribute("errorMsgs", errorMsgs);

						
						/***************************1.將輸入資料轉為Map**********************************/ 

						HttpSession session = req.getSession();
						@SuppressWarnings("unchecked")
						Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
						
						// 以下的 if 區塊只對第一次執行時有效
						if (req.getParameter("whichPage") == null){
							Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
							session.setAttribute("map",map1);
							map = map1;
						} 
						
						/***************************2.開始複合查詢***************************************/
						ChefService chefSvc = new ChefService();
						List<ChefVO> list  = chefSvc.getAll(map);
						
						/***************************3.查詢完成,準備轉交(Send the Success view)************/
						req.setAttribute("listChef_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
						RequestDispatcher successView = req.getRequestDispatcher("/front-end/chef/listChef_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
						successView.forward(req, res);
				}		

//============================================================================
		if ("getOne_For_Display".equals(action)) { // 來自listAllChef.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer chefid = Integer.valueOf(req.getParameter("chefid"));

			/*************************** 2.開始查詢資料 ****************************************/
			ChefService chefSvc = new ChefService();
			ChefVO chefVO = chefSvc.getOneChef(chefid);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("chefVO", chefVO); // 資料庫取出的chefVO物件,存入req

			String url = "/front-end/chef/chefIntroduce.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_chef_input.jsp
			successView.forward(req, res);

		}

		
		
//============================================================================
		
		if ("browse".equals(action)) { // 來自select_page.jsp的複合查詢請求

				RequestDispatcher successView = req.getRequestDispatcher("/front-end/chef/chef.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
		}		

		
		
		
		
	}

	

}
