package com.chefskills.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.chefskills.model.*;

public class ChefSkillsServlet extends HttpServlet {

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
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/chefSkills/select_page.jsp");
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
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/chefSkills/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			
			String str1 = req.getParameter("skillid");
			if (str1 == null || (str1.trim()).length() == 0) {
				errorMsgs.add("請輸入專長編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/chefSkills/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			
			Integer skillid = null;
			try {
				skillid = Integer.valueOf(str1);
			} catch (Exception e) {
				errorMsgs.add("專長編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/chefSkills/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ChefSkillsService chefSkillsSvc = new ChefSkillsService();
			ChefSkillsVO chefSkillsVO = chefSkillsSvc.getOneChefSkills(chefid, skillid);
			if (chefSkillsVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/chefSkills/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("chefSkillsVO", chefSkillsVO); // 資料庫取出的chefVSkillsVO物件,存入req
			String url = "/back-end/chefSkills/listOneChefSkills.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneChefSkills.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllChefSkills.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer chefid = Integer.valueOf(req.getParameter("chefid"));
			Integer skillid = Integer.valueOf(req.getParameter("skillid"));

			/*************************** 2.開始查詢資料 ****************************************/
			ChefSkillsService chefSkillsSvc = new ChefSkillsService();
			ChefSkillsVO chefSkillsVO = chefSkillsSvc.getOneChefSkills(chefid, skillid);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("chefSkillsVO", chefSkillsVO); // 資料庫取出的chefSkillsVO物件,存入req
			String url = "/back-end/chefSkills/update_chefSkills_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_chefSkills_input.jsp
			successView.forward(req, res);
		}

//		if ("update".equals(action)) { // 來自update_chefSkills_input.jsp的請求
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
//			Integer chefid = Integer.valueOf(req.getParameter("chefid").trim());
//			Integer skillsid = Integer.valueOf(req.getParameter("skillsid").trim());
//
//		
//
//			ChefSkillsVO chefSkillsVO = new ChefSkillsVO();
//			chefSkillsVO.setChefid(chefid);
//			chefSkillsVO.setSkillsid(skillsid);
//			
//
//			// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				req.setAttribute("chefSkillsVO", chefSkillsVO); // 含有輸入格式錯誤的chefSkillsTypeVO物件,也存入req
//				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/chefSkills/update_chefSkills_input.jsp");
//				failureView.forward(req, res);
//				return; // 程式中斷
//			}
//
//			/*************************** 2.開始修改資料 *****************************************/
//			ChefSkillsService chefSkillsSvc = new ChefSkillsService();
//			chefSkillsVO = chefSkillsSvc.updateChefSkills(skillsid, skill);
//		
//			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
//			req.setAttribute("chefSkillsVO", chefSkillsVO); // 資料庫update成功後,正確的的empVO物件,存入req
//			String url = "/back-end/chefSkills/listOneChefSkills.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMember.jsp
//			successView.forward(req, res);
//		}

		if ("insert".equals(action)) { // 來自addChefSkills.jsp的請求

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
			Integer skillid = null;
			try {
				skillid = Integer.valueOf(req.getParameter("skillid").trim());
			} catch (NumberFormatException e) {
				skillid = 0;
				errorMsgs.add("專長編號請填數字.");
			}
			
			
			ChefSkillsVO chefSkillsVO = new ChefSkillsVO();
			chefSkillsVO.setChefid(chefid);
			chefSkillsVO.setSkillid(skillid);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("chefSkillsTypeVO", chefSkillsVO); // 含有輸入格式錯誤的chefSkillsVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/chefSkills/addChefSkills.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			ChefSkillsService chefSkillsSvc = new ChefSkillsService();
			chefSkillsVO = chefSkillsSvc.addChefSkills(chefid, skillid);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/chefSkills/listAllChefSkills.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMember.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllChefSkills.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
	
				/***************************1.接收請求參數***************************************/
				Integer chefid = Integer.valueOf(req.getParameter("chefid"));
				Integer skillid = Integer.valueOf(req.getParameter("skillid"));
				
				/***************************2.開始刪除資料***************************************/
				ChefSkillsService chefSkillsSvc = new ChefSkillsService();
				chefSkillsSvc.deleteChefSkills(chefid, skillid);
				
				/***************************3.刪除完成,準備轉交(Send the Success view)***********/								
				String url = "/back-end/chefSkills/listAllChefSkills.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);
		}
	}
	
}
