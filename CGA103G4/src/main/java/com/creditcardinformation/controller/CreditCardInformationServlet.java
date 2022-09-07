package com.creditcardinformation.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import com.creditcardinformation.model.*;
@WebServlet("/back-end/creditCardInformation/CreditCardInformation.do")
public class CreditCardInformationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

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
			String str = req.getParameter("creditCardid");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入信用卡編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/creditCardInformation/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer creditCardid = null;
			try {
				creditCardid = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("信用卡編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/creditCardInformation/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			CreditCardInformationService creditCardInformationSvc = new CreditCardInformationService();
			CreditCardInformationVO creditCardInformationVO = creditCardInformationSvc.getOneCreditCardInformation(creditCardid);
			if (creditCardInformationVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/creditCardInformation/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("creditCardInformationVO", creditCardInformationVO); // 資料庫取出的memberVO物件,存入req
			String url = "/back-end/creditCardInformation/listOneCreditCardInformation.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneMember.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllCreditCardInformation.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer creditCardid = Integer.valueOf(req.getParameter("creditCardid"));

			/*************************** 2.開始查詢資料 ****************************************/
			CreditCardInformationService creditCardInformationSvc = new CreditCardInformationService();
			CreditCardInformationVO creditCardInformationVO = creditCardInformationSvc.getOneCreditCardInformation(creditCardid);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("creditCardInformationVO", creditCardInformationVO); // 資料庫取出的creditCardInformationVO物件,存入req
			String url = "/back-end/creditCardInformation/update_creditCardInformation_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_creditCardInformation_input.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addCreditCardInformation.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer memid = null;
			try {
				memid = Integer.valueOf(req.getParameter("memid").trim());
			} catch (NumberFormatException e) {
				memid = 0;
				errorMsgs.add("會員ID請填數字.");
			}

			String creditCardNumber = req.getParameter("creditCardNumber").trim();
			if (creditCardNumber == null || creditCardNumber.trim().length() == 0) {
				errorMsgs.add("信用卡卡號請勿空白");
			}

			String creditCardName = req.getParameter("creditCardName");
			String creditCardNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z)]{2,10}$";
			if (creditCardName == null || creditCardName.trim().length() == 0) {
				errorMsgs.add("持卡人姓名: 請勿空白");
			} else if (!creditCardName.trim().matches(creditCardNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("持卡人姓名: 只能是中、英文字母 , 且長度必需在2到10之間");
			}

			String creditCardTime = req.getParameter("creditCardTime").trim();
			if (creditCardTime == null || creditCardTime.trim().length() == 0) {
				errorMsgs.add("到期月/年份請勿空白");
			}

			String cvcCode = req.getParameter("cvcCode");
			String cvcCodeReg = "^[(0-9)]{3,3}$";
			if (cvcCode == null || cvcCode.trim().length() == 0) {
				errorMsgs.add("安全驗證碼: 請勿空白");
			} else if(!cvcCode.trim().matches(cvcCodeReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("安全驗證碼: 只能是數字 , 且長度必需3");
            }

			CreditCardInformationVO creditCardInformationVO = new CreditCardInformationVO();
			creditCardInformationVO.setMemid(memid);
			creditCardInformationVO.setCreditCardNumber(creditCardNumber);
			creditCardInformationVO.setCreditCardName(creditCardName);
			creditCardInformationVO.setCreditCardTime(creditCardTime);
			creditCardInformationVO.setCvcCode(cvcCode);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("creditCardInformationVO", creditCardInformationVO); // 含有輸入格式錯誤的creditCardInformationVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/creditCardInformation/addCreditCardInformation.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			CreditCardInformationService creditCardInformationSvc = new CreditCardInformationService();
			creditCardInformationVO = creditCardInformationSvc.addCreditCardInformation(memid, creditCardNumber,
					creditCardName, creditCardTime, cvcCode);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/creditCardInformation/listAllCreditCardInformation.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMember.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllCreditCardInformation.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer creditCardid = Integer.valueOf(req.getParameter("creditCardid"));

			/*************************** 2.開始刪除資料 ***************************************/
			CreditCardInformationService creditCardInformationSvc = new CreditCardInformationService();
			creditCardInformationSvc.deleteCreditCardInformation(creditCardid);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/creditCardInformation/listAllCreditCardInformation.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
		
		//============================================================================
		
		if ("listCreditCardInformation_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				
				/***************************1.將輸入資料轉為Map**********************************/ 
				//採用Map<String,String[]> getParameterMap()的方法 
				//注意:an immutable java.util.Map 
				//Map<String, String[]> map = req.getParameterMap();
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
				CreditCardInformationService creditCardInformationSvc = new CreditCardInformationService();
				List<CreditCardInformationVO> list  = creditCardInformationSvc.getAll(map);
				
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("listCreditCardInformation_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("/back-end/creditCardInformation/listCreditCardInformation_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
		}
	}
}
