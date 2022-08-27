package com.chefskillstype.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.chefskillstype.model.*;

public class ChefSkillsTypeServlet extends HttpServlet {

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
			String str = req.getParameter("skillid");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入專長編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/chefSkillsType/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer skillid = null;
			try {
				skillid = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("專長編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/chefSkillsType/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ChefSkillsTypeService chefSkillsTypeSvc = new ChefSkillsTypeService();
			ChefSkillsTypeVO chefSkillsTypeVO = chefSkillsTypeSvc.getOneChefSkillsType(skillid);
			if (chefSkillsTypeVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/chefSkillsType/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("chefSkillsTypeVO", chefSkillsTypeVO); // 資料庫取出的chefVSkillsTypeO物件,存入req
			String url = "/back-end/chefSkillsType/listOneChefSkillsType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneChefSkillsType.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllChefSkillsType.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer skillid = Integer.valueOf(req.getParameter("skillid"));

			/*************************** 2.開始查詢資料 ****************************************/
			ChefSkillsTypeService chefSkillsTypeSvc = new ChefSkillsTypeService();
			ChefSkillsTypeVO chefSkillsTypeVO = chefSkillsTypeSvc.getOneChefSkillsType(skillid);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("chefSkillsTypeVO", chefSkillsTypeVO); // 資料庫取出的chefSkillsTypeVO物件,存入req
			String url = "/back-end/chefSkillsType/update_chefSkillsType_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_chefSkillsType_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_chefSkillsType_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer skillid = Integer.valueOf(req.getParameter("skillid").trim());

			String skill = req.getParameter("skill");
			String skillReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (skill == null || skill.trim().length() == 0) {
				errorMsgs.add("專長: 請勿空白");
			} else if (!skill.trim().matches(skillReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("專長: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			ChefSkillsTypeVO chefSkillsTypeVO = new ChefSkillsTypeVO();
			chefSkillsTypeVO.setSkillid(skillid);
			chefSkillsTypeVO.setSkill(skill);
			

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("chefSkillsTypeVO", chefSkillsTypeVO); // 含有輸入格式錯誤的chefSkillsTypeVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/chefSkillsType/update_chefSkillsType_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			ChefSkillsTypeService chefSkillsTypeSvc = new ChefSkillsTypeService();
			chefSkillsTypeVO = chefSkillsTypeSvc.updateChefSkillsType(skillid, skill);
		
			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("chefSkillsTypeVO", chefSkillsTypeVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back-end/chefSkillsType/listOneChefSkillsType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMember.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addChefSkillsType.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
		
			String skill = req.getParameter("skill");
			String chefNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (skill == null || skill.trim().length() == 0) {
				errorMsgs.add("專長: 請勿空白");
			} else if (!skill.trim().matches(chefNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("專長: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			ChefSkillsTypeVO chefSkillsTypeVO = new ChefSkillsTypeVO();
			chefSkillsTypeVO.setSkill(skill);
		
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("chefSkillsTypeVO", chefSkillsTypeVO); // 含有輸入格式錯誤的chefSkillsTypeVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/chefSkillsType/addChefSkillsType.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			ChefSkillsTypeService chefSkillsTypeSvc = new ChefSkillsTypeService();
			chefSkillsTypeVO = chefSkillsTypeSvc.addChefSkillsType(skill);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/chefSkillsType/listAllChefSkillsType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMember.jsp
			successView.forward(req, res);
		}

//		
	}
}
