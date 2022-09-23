package com.chef.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.ClassIfm.model.ClassIfmService;
import com.ClassIfm.model.ClassIfmVO;
import com.chef.model.*;
import com.chefsubscription.model.ChefSubscriptionService;


@WebServlet("/front-end/chef/frontEndChefSubscription.do")
public class FrontEndChefSubscriptionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

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
				String url = "/front-end/chef/addChefSubscription.jsp";
				
	
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
		
		
		
		
	}

	

}
