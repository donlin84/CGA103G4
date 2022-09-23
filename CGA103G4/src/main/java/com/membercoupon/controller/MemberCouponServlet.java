package com.membercoupon.controller;

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

import com.membercoupon.model.MemberCouponService;
import com.membercoupon.model.MemberCouponVO;
import com.promotionsdetail.model.PromotionsDetailService;

@WebServlet("/back-end/membercoupon/MemberCouponServlet")
public class MemberCouponServlet extends HttpServlet {
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
			
			String str = req.getParameter("memCpid");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入會員優惠券編號");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/discount-management/discount-management.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}
			Integer memCpid = null;
			try {
				memCpid = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("會員優惠券編號格式不正確");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/discount-management/discount-management.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始查詢資料 *****************************************/

			MemberCouponService memCpSvc = new MemberCouponService();
			MemberCouponVO memberCouponVO = memCpSvc.getOneMemberCoupon(memCpid);
			if (memberCouponVO == null) {
				errorMsgs.add("查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/discount-management/discount-management.jsp");
				failureView.forward(req, res);
				return;
			}
			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/

			req.setAttribute("memberCouponVO", memberCouponVO);
			String url = "/back-end/membercoupon/listOneMemberCoupon.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOnePromotion.jsp
			successView.forward(req, res);
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllPromotions.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/

			Integer memCpid = Integer.valueOf(req.getParameter("memCpid"));

			/*************************** 2.開始查詢資料 ****************************************/

			MemberCouponService memCpSvc = new MemberCouponService();
			MemberCouponVO memberCouponVO = memCpSvc.getOneMemberCoupon(memCpid);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/

			req.setAttribute("memberCouponVO", memberCouponVO);
			String url = "/back-end/membercoupon/updateMemberCoupon.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOnePromotion.jsp
			successView.forward(req, res);
		
		}
		

		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			Integer memCpid = Integer.valueOf(req.getParameter("memCpid").trim());
			Integer memid = Integer.valueOf(req.getParameter("memid").trim());
			Integer cpTpid = Integer.valueOf(req.getParameter("cpTpid").trim());
			

			java.sql.Date memCpDate = null;
			try {
				memCpDate = java.sql.Date.valueOf(req.getParameter("memCpDate").trim());
			} catch (Exception e) {
				memCpDate = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入有效日期");
			}
			
			Integer memCpStatus = Integer.valueOf(req.getParameter("memCpStatus").trim());

			java.sql.Date memCpRecord = null;
//			memCpRecord = java.sql.Date.valueOf(req.getParameter("memCpRecord").trim());
				
			MemberCouponVO memberCouponVO = new MemberCouponVO();
			
			memberCouponVO.setMemCpid(memCpid);
			memberCouponVO.setMemid(memid);
			memberCouponVO.setCpTpid(cpTpid);
			memberCouponVO.setMemCpDate(memCpDate);
			memberCouponVO.setMemCpStatus(memCpStatus);
			memberCouponVO.setMemCpRecord(memCpRecord);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memberCouponVO", memberCouponVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/membercoupon/updateMemberCoupon.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始修改資料 *****************************************/
			
			MemberCouponService memCpSvc = new MemberCouponService();
			memberCouponVO = memCpSvc.updateMemberCoupon(memCpid, memid, cpTpid, memCpDate, memCpStatus, memCpRecord);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/

			req.setAttribute("memberCouponVO", memberCouponVO);
			String url = "/back-end/membercoupon/listOneMemberCoupon.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("insert".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			
			MemberCouponService memCpSvc = new MemberCouponService();
			String[] count = req.getParameterValues("count");
			String[] memid = req.getParameterValues("memid");
			String[] cpTpid = req.getParameterValues("cpTpid");
			String[] memCpDate = req.getParameterValues("memCpDate");
			String[] memCpStatus = req.getParameterValues("memCpStatus");
			String[] memCpRecord = req.getParameterValues("memCpRecord");
			
			System.out.println();
			try {
			
			for(int i =0 ; i<count.length ; i++) {
				System.out.println("Date " + memCpDate[i]);
				System.out.println("UseDate " + memCpRecord[i]);
				memCpSvc.addMemberCoupon(Integer.valueOf(memid[Integer.valueOf(count[i])]), Integer.valueOf(cpTpid[Integer.valueOf(count[i])]),
				java.sql.Date.valueOf(memCpDate[i]), Integer.valueOf(memCpStatus[Integer.valueOf(count[i])]), null);
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				errorMsgs.add("此優惠券已經發放過了");
			}
			
			
//			Integer memid = Integer.valueOf(req.getParameter("memid").trim());
//			Integer cpTpid = Integer.valueOf(req.getParameter("cpTpid").trim());
			

//			java.sql.Date memCpDate = null;
//			try {
//				memCpDate = java.sql.Date.valueOf(req.getParameter("memCpDate").trim());
//			} catch (Exception e) {
//				memCpDate = new java.sql.Date(System.currentTimeMillis());
//				errorMsgs.add("請輸入有效日期");
//			}
			
//			Integer memCpStatus = Integer.valueOf(req.getParameter("memCpStatus").trim());

//			java.sql.Date memCpRecord = null;
			
//			MemberCouponVO memberCouponVO = new MemberCouponVO();
//			memberCouponVO.setMemid(memid);
//			memberCouponVO.setCpTpid(cpTpid);
//			memberCouponVO.setMemCpDate(memCpDate);
//			memberCouponVO.setMemCpStatus(memCpStatus);
//			memberCouponVO.setMemCpRecord(memCpRecord);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("memCpSvc", memCpSvc);
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/membercoupon/batchAddMemberCoupon.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始修改資料 *****************************************/

//			MemberCouponService memCpSvc = new MemberCouponService();
//			memberCouponVO = memCpSvc.addMemberCoupon(memid, cpTpid, memCpDate, memCpStatus, memCpRecord);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			String url = "listAllMemberCoupons.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
			
			
//			String url = "/back-end/membercoupon/listAllMemberCoupons.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);
//			successView.forward(req, res);
		}

		if ("delete".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/

			Integer memCpid = Integer.valueOf(req.getParameter("memCpid"));

			/*************************** 2.開始刪除資料 ***************************************/

			MemberCouponService memCpSvc = new MemberCouponService();
			memCpSvc.deleteMemberCoupon(memCpid);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/

			String url = "/back-end/membercoupon/listAllMemberCoupon.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
		if ("batchAddMemberCoupon".equals(action)) { // 來自select_page.jsp的複合查詢請求
			List<String> errorMsgs = new LinkedList<String>();
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
//				PromotionsDetailService pmtDetailSvc = new PromotionsDetailService();
//				List<PromotionsDetailVO> list  = pmtDetailSvc.getAll(map);	
				MemberCouponService memCpSvc = new MemberCouponService();
				List<MemberCouponVO> list  = memCpSvc.getAll(map);
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("batchAddMemberCoupon", list); // 資料庫取出的list物件,存入request
				RequestDispatcher successView = req.getRequestDispatcher("batchAddMemberCoupon.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
				successView.forward(req, res);
		}
		
	}

}
