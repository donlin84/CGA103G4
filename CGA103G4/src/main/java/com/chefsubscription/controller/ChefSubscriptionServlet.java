package com.chefsubscription.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.chefsubscription.model.*;

public class ChefSubscriptionServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("chefid");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入私廚編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/chefSubscription/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer chefid = null;
			try {
				chefid = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("私廚編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/chefSubscription/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			
			String str1 = req.getParameter("memid");
			if (str1 == null || (str1.trim()).length() == 0) {
				errorMsgs.add("請輸入會員編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/chefSubscription/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			
			Integer memid = null;
			try {
				memid = Integer.valueOf(str1);
			} catch (Exception e) {
				errorMsgs.add("會員編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/chefSubscription/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ChefSubscriptionService chefSubscriptionSvc = new ChefSubscriptionService();
			ChefSubscriptionVO chefSubscriptionVO = chefSubscriptionSvc.getOneChefSubscription(chefid, memid);
			if (chefSubscriptionVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/chefSubscription/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("chefSubscriptionVO", chefSubscriptionVO); // 資料庫取出的chefVSubscriptionVO物件,存入req
			String url = "/back-end/chefSubscription/listOneChefSubscription.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneChefSubscription.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllChefSubscription.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer chefid = Integer.valueOf(req.getParameter("chefid"));
			Integer memid = Integer.valueOf(req.getParameter("memid"));

			/*************************** 2.開始查詢資料 ****************************************/
			ChefSubscriptionService chefSubscriptionSvc = new ChefSubscriptionService();
			ChefSubscriptionVO chefSubscriptionVO = chefSubscriptionSvc.getOneChefSubscription(chefid, memid);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("chefSubscriptionVO", chefSubscriptionVO); // 資料庫取出的chefSubscriptionVO物件,存入req
			String url = "/back-end/chefSubscription/update_chefSubscription_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_chefSubscription_input.jsp
			successView.forward(req, res);
		}


		
		
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
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/chefSubscription/addChefSubscription.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			ChefSubscriptionService chefSubscriptionSvc = new ChefSubscriptionService();
			chefSubscriptionVO = chefSubscriptionSvc.addChefSubscription(chefid, memid);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/chefSubscription/listAllChefSubscription.jsp";
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
				String url = "/back-end/chefSubscription/listAllChefSubscription.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	}
	
}
