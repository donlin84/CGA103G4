package com.chefsubscription.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import com.chefsubscription.model.*;
@WebServlet("/front-end/chef/ChefFrontEndSubscription.do")
public class ChefFrontEndSubscriptionServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

//	System.out.println(action);			
		if ("insert".equals(action)) { // 來自addChefSubscription.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/

			Integer chefid = null;
			try {
				chefid = Integer.valueOf(req.getParameter("chefid").trim());

			} catch (NumberFormatException e) {
				chefid = 0;
				errorMsgs.add("私廚編號請填數字.");
			}
			Integer memid = null;
			try {
				memid = Integer.valueOf(req.getParameter("memid").trim());

			} catch (NumberFormatException e) {
				memid = 0;
				errorMsgs.add("會員編號請填數字.");
			}
			
			
			ChefSubscriptionVO chefSubscriptionVO = new ChefSubscriptionVO();
			chefSubscriptionVO.setChefid(chefid);
			chefSubscriptionVO.setMemid(memid);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("chefSubscriptionTypeVO", chefSubscriptionVO); // 含有輸入格式錯誤的chefSubscriptionVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/chef/chef.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			ChefSubscriptionService chefSubscriptionSvc = new ChefSubscriptionService();
			chefSubscriptionVO = chefSubscriptionSvc.addChefSubscription(chefid, memid);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/


			String url = "/front-end/chef/chef.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交chefSubscription.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllChefSubscription.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer chefid = Integer.valueOf(req.getParameter("chefid"));
				Integer memid = Integer.valueOf(req.getParameter("memid"));
				
				/***************************2.開始刪除資料***************************************/
				ChefSubscriptionService chefSubscriptionSvc = new ChefSubscriptionService();
				chefSubscriptionSvc.deleteChefSubscription(chefid, memid);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/front-end/chef/chef.jsp";
				
	
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
		
//============================================================================
		
	}
	
	
	
}
