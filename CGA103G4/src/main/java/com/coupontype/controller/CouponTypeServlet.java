package com.coupontype.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.coupontype.model.CouponTypeService;
import com.coupontype.model.CouponTypeVO;

@WebServlet("/back-end/coupontype/CoupontypeServlet2")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class CouponTypeServlet extends HttpServlet {
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

			String str = req.getParameter("cpTpid");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入優惠券種類編號");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coupontype/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			Integer cpTpid = null;
			try {
				cpTpid = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("活動優惠券種類編號格式不正確");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coupontype/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始查詢資料 *****************************************/

			CouponTypeService cpTpSvc = new CouponTypeService();
			CouponTypeVO couponTypeVO = cpTpSvc.getOneCouponType(cpTpid);
			if (couponTypeVO == null) {
				errorMsgs.add("查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coupontype/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

			req.setAttribute("couponTypeVO", couponTypeVO);
			String url = "/back-end/coupontype/listOneCouponType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOnePromotion.jsp
			successView.forward(req, res);
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllPromotions.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/

			Integer cpTpid = Integer.valueOf(req.getParameter("cpTpid"));

			/*************************** 2.開始查詢資料 ****************************************/

			CouponTypeService cpTpSvc = new CouponTypeService();
			CouponTypeVO couponTypeVO = cpTpSvc.getOneCouponType(cpTpid);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/

			req.setAttribute("couponTypeVO", couponTypeVO);
			String url = "/back-end/coupontype/updateCouponType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			Integer cpTpid = Integer.valueOf(req.getParameter("cpTpid").trim());
			String cpName = req.getParameter("cpName");
			String cpNameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,45}$";

			if (cpName == null || cpName.trim().length() == 0) {
				errorMsgs.add("優惠券名稱尚未填寫");
			} else if (!cpName.trim().matches(cpNameReg)) {
				errorMsgs.add("優惠券名稱需介於2~15字之間");
			}

			Integer cpDiscount = null;
			try {
				cpDiscount = Integer.valueOf(req.getParameter("cpDiscount").trim());
			} catch (Exception e) {
				cpDiscount = 0;
				errorMsgs.add("請填寫優惠券折扣價格");
			}

			java.sql.Date cpStart = null;
			try {
				cpStart = java.sql.Date.valueOf(req.getParameter("cpStart").trim());
			} catch (Exception e) {
				cpStart = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入起始時間");
			}

			java.sql.Date cpEnd = null;
			try {
				cpEnd = java.sql.Date.valueOf(req.getParameter("cpEnd").trim());
			} catch (Exception e) {
				cpEnd = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入截止時間");
			}

			if ((cpEnd.getTime() - cpStart.getTime()) < 0) {
				errorMsgs.add("截止時間必須在開始時間之後");
			}

			Integer cpStatus = Integer.valueOf(req.getParameter("cpStatus").trim());

			byte[] cpPic = null;

			try {
				cpPic = req.getPart("cpPic").getInputStream().readAllBytes();
			} catch (Exception e) {
				errorMsgs.add("請上傳正確格式檔案");
				System.out.println(cpPic);
			}
			
			CouponTypeVO couponTypeVO = new CouponTypeVO();
			couponTypeVO.setCpTpid(cpTpid);
			couponTypeVO.setCpName(cpName);
			couponTypeVO.setCpDiscount(cpDiscount);
			couponTypeVO.setCpStart(cpStart);
			couponTypeVO.setCpEnd(cpEnd);
			couponTypeVO.setCpStatus(cpStatus);
			couponTypeVO.setCpPic(cpPic);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("couponTypeVO", couponTypeVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coupontype/updateCouponType.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始修改資料 *****************************************/

			CouponTypeService cpTpSvc = new CouponTypeService();
			couponTypeVO = cpTpSvc.updateCouponType(cpTpid, cpName, cpDiscount, cpStart, cpEnd, cpStatus, cpPic);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/

			req.setAttribute("couponTypeVO", couponTypeVO);
			String url = "/back-end/coupontype/listOneCouponType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			String cpName = req.getParameter("cpName");
			String cpNameReg = "^[(\\u4e00-\\u9fa5)(a-zA-Z0-9_)]{2,45}$";

			if (cpName == null || cpName.trim().length() == 0) {
				errorMsgs.add("優惠券名稱尚未填寫");
			} else if (!cpName.trim().matches(cpNameReg)) {
				errorMsgs.add("優惠券名稱需介於2~15字之間");
			}

			Integer cpDiscount = null;
			try {
				cpDiscount = Integer.valueOf(req.getParameter("cpDiscount").trim());
			} catch (Exception e) {
				cpDiscount = 0;
				errorMsgs.add("請填寫優惠券折扣價格");
			}

			java.sql.Date cpStart = null;
			try {
				cpStart = java.sql.Date.valueOf(req.getParameter("cpStart").trim());
			} catch (Exception e) {
				cpStart = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入起始時間");
			}

			java.sql.Date cpEnd = null;
			try {
				cpEnd = java.sql.Date.valueOf(req.getParameter("cpEnd").trim());
			} catch (Exception e) {
				cpEnd = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入截止時間");
			}

			if ((cpEnd.getTime() - cpStart.getTime()) < 0) {
				errorMsgs.add("截止時間必須在開始時間之後");
			}

			Integer cpStatus = Integer.valueOf(req.getParameter("cpStatus").trim());

			byte[] cpPic = null;

			try {
				cpPic = req.getPart("cpPic").getInputStream().readAllBytes();
			} catch (Exception e) {
				errorMsgs.add("請上傳正確格式檔案");
				System.out.println(cpPic);
			}

			CouponTypeVO couponTypeVO = new CouponTypeVO();
			couponTypeVO.setCpName(cpName);
			couponTypeVO.setCpDiscount(cpDiscount);
			couponTypeVO.setCpStart(cpStart);
			couponTypeVO.setCpEnd(cpEnd);
			couponTypeVO.setCpStatus(cpStatus);
			couponTypeVO.setCpPic(cpPic);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("couponTypeVO", couponTypeVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coupontype/addCouponType.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始修改資料 *****************************************/

			CouponTypeService cpTpSvc = new CouponTypeService();
			couponTypeVO = cpTpSvc.addCouponType(cpName, cpDiscount, cpStart, cpEnd, cpStatus, cpPic);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/

			String url = "/back-end/coupontype/listAllCouponType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/

			Integer cpTpid = Integer.valueOf(req.getParameter("cpTpid"));

			/*************************** 2.開始刪除資料 ***************************************/

			CouponTypeService cpTpSvc = new CouponTypeService();
			cpTpSvc.deleteCouponType(cpTpid);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/

			String url = "/back-end/coupontype/listAllCouponType.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
		
	}
	
}
