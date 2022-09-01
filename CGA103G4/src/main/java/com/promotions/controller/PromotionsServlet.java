package com.promotions.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.promotions.model.PromotionsService;
import com.promotions.model.PromotionsVO;

@WebServlet("/back-end/promotions/PromotionsServlet2")
public class PromotionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");// 編碼此專案 註冊在web.xml
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			
			String str = req.getParameter("pmid");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入活動優惠編號");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/promotions/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			Integer pmid = null;
			try {
				pmid = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("活動優惠編號格式不正確");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/promotions/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			
			/*************************** 2.開始查詢資料 *****************************************/
			
			PromotionsService pmtSvc = new PromotionsService();
			PromotionsVO promotionsVO = pmtSvc.getOnePromotion(pmid);
			if (promotionsVO == null) {
				errorMsgs.add("查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/promotions/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			
			req.setAttribute("promotionsVO", promotionsVO); // 資料庫取出的promotionsVO物件,存入req
			String url = "/back-end/promotions/listOnePromotion.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOnePromotion.jsp
			successView.forward(req, res);
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllPromotions.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/*************************** 1.接收請求參數 ****************************************/
			
			Integer pmid = Integer.valueOf(req.getParameter("pmid"));
			
			/*************************** 2.開始查詢資料 ****************************************/
			
			PromotionsService pmtSvc = new PromotionsService();
			PromotionsVO promotionsVO = pmtSvc.getOnePromotion(pmid);
			
			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			
			req.setAttribute("promotionsVO", promotionsVO); // 資料庫取出的promotionsVO物件,存入req
			String url = "/back-end/promotions/updatePromotion.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 updatePromotion.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自updatePromotion.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			
			Integer pmid = Integer.valueOf(req.getParameter("pmid").trim());
			String pmName = req.getParameter("pmName");
			String pmNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,45}$";
			
			if (pmName == null || pmName.trim().length() == 0) {
				errorMsgs.add("活動名稱尚未填寫");
			} else if (!pmName.trim().matches(pmNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("活動名稱需介於2~15字之間");
			}
			
			String pmDescription = req.getParameter("pmDescription");
			String pmNameReg2 = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,255}$";
			if (pmDescription == null || pmDescription.trim().length() == 0) {
				errorMsgs.add("活動描述尚未填寫");
			} else if (!pmDescription.trim().matches(pmNameReg2)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("活動描述名稱需介於2~255字之間");
			}

			Double pmDiscount = null;
			try {
				pmDiscount = Double.valueOf(req.getParameter("pmDiscount").trim());
			} catch (NumberFormatException e) {
				pmDiscount = 0.0;
				errorMsgs.add("請填寫折扣幅度");
			}

			java.sql.Date pmStart = null;
			try {
				pmStart = java.sql.Date.valueOf(req.getParameter("pmStart").trim());
			} catch (IllegalArgumentException e) {
				pmStart = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入起始時間");
			}
			
			java.sql.Date pmEnd = null;
			try {
				pmEnd = java.sql.Date.valueOf(req.getParameter("pmEnd").trim());
			} catch (IllegalArgumentException e) {
				pmEnd = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入截止時間");
			}
			
			if ((pmEnd.getTime() - pmStart.getTime()) < 0) {
				errorMsgs.add("截止時間必須在開始時間之後");
			}
			
			Integer pmStatus = Integer.valueOf(req.getParameter("pmStatus").trim());

			PromotionsVO promotionsVO = new PromotionsVO();
			promotionsVO.setPmid(pmid);
			promotionsVO.setPmName(pmName);
			promotionsVO.setPmDescription(pmDescription);
			promotionsVO.setPmDiscount(pmDiscount);
			promotionsVO.setPmStart(pmStart);
			promotionsVO.setPmEnd(pmEnd);
			promotionsVO.setPmStatus(pmStatus);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("promotionsVO", promotionsVO); // 含有輸入格式錯誤的promotionsVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/promotions/updatePromotion.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			
			/*************************** 2.開始修改資料 *****************************************/

			PromotionsService pmtSvc = new PromotionsService();
			promotionsVO = pmtSvc.updatePromotions(pmid, pmName, pmDescription, pmDiscount, pmStart, pmEnd, pmStatus);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			
			req.setAttribute("promotionsVO", promotionsVO); // 資料庫update成功後,正確的的promotionsVO物件,存入req
			String url = "/back-end/promotions/listOnePromotion.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOnePromotion.jsp
			successView.forward(req, res);
		}
		
		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			
			String pmName = req.getParameter("pmName");
			String pmNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,45}$";
			if (pmName == null || pmName.trim().length() == 0) {
				errorMsgs.add("活動名稱尚未填寫");
			} else if (!pmName.trim().matches(pmNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("活動名稱需介於2~15字之間");
			}
			
			String pmDescription = req.getParameter("pmDescription");
			String pmNameReg2 = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,255}$";
			if (pmDescription == null || pmDescription.trim().length() == 0) {
				errorMsgs.add("活動描述尚未填寫");
			} else if (!pmDescription.trim().matches(pmNameReg2)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("活動描述名稱需介於2~255字之間");
			}

			Double pmDiscount = null;
			try {
				pmDiscount = Double.valueOf(req.getParameter("pmDiscount").trim());
			} catch (NumberFormatException e) {
				pmDiscount = 0.0;
				errorMsgs.add("請填寫折扣幅度");
			}

			java.sql.Date pmStart = null;
			try {
				pmStart = java.sql.Date.valueOf(req.getParameter("pmStart").trim());
			} catch (IllegalArgumentException e) {
				pmStart = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入起始時間!");
			}

			java.sql.Date pmEnd = null;
			try {
				pmEnd = java.sql.Date.valueOf(req.getParameter("pmEnd").trim());
			} catch (IllegalArgumentException e) {
				pmEnd = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入截止時間!");
			}
			
			if ((pmEnd.getTime() - pmStart.getTime()) < 0) {
				errorMsgs.add("截止時間必須在開始時間之後!");
			}
			
			Integer pmStatus = Integer.valueOf(req.getParameter("pmStatus").trim());

			PromotionsVO promotionsVO = new PromotionsVO();
			promotionsVO.setPmName(pmName);
			promotionsVO.setPmDescription(pmDescription);
			promotionsVO.setPmDiscount(pmDiscount);
			promotionsVO.setPmStart(pmStart);
			promotionsVO.setPmEnd(pmEnd);
			promotionsVO.setPmStatus(pmStatus);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("promotionsVO", promotionsVO); // 含有輸入格式錯誤的promotionsVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/promotions/addPromotions.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			
			PromotionsService pmtSvc = new PromotionsService();
			promotionsVO = pmtSvc.addPromotions(pmName, pmDescription, pmDiscount, pmStart, pmEnd, pmStatus);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			
			String url = "/back-end/promotions/listAllPromotions.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listAllPromotions.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllPromotions.jsp
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			
			Integer pmid = Integer.valueOf(req.getParameter("pmid"));

			/*************************** 2.開始刪除資料 ***************************************/
			
			PromotionsService pmtSvc = new PromotionsService();
			pmtSvc.deletePromotions(pmid);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			
			String url = "/back-end/promotions/listAllPromotions.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
}
