package com.promotionsdetail.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.promotionsdetail.model.PromotionsDetailService;
import com.promotionsdetail.model.PromotionsDetailVO;

@WebServlet("/back-end/promotionsdetail/PromotionsDetailServlet")
public class PromotionsDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String str1 = req.getParameter("pmid");
			String str2 = req.getParameter("pdid");

			if (str1 == null || (str1.trim()).length() == 0) {
				errorMsgs.add("請輸入優惠方案編號");
			}
			if (str2 == null || (str2.trim()).length() == 0) {
				errorMsgs.add("請輸入商品編號");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer pmid = null;
			Integer pdid = null;
			try {
				pmid = Integer.valueOf(str1);
			} catch (Exception e) {
				errorMsgs.add("優惠方案編號格式不正確");
			}
			try {
				pdid = Integer.valueOf(str2);
			} catch (Exception e) {
				errorMsgs.add("商品編號格式不正確");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			PromotionsDetailService pmtDetailSvc = new PromotionsDetailService();
			PromotionsDetailVO promotionsDetailVO = pmtDetailSvc.getOnePromotionDetail(pmid, pdid);

			if (promotionsDetailVO == null) {
				errorMsgs.add("查無資料");
			}

			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("promotionsDetailVO", promotionsDetailVO); // 資料庫取出的VO物件,存入req
			String url = "listOnePromotionsDetail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOne.jsp
			successView.forward(req, res);
		}
		if ("getOne_For_Update".equals(action)) { // 來自listAllPromotions.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/

			Integer pmid = Integer.valueOf(req.getParameter("pmid"));
			Integer pdid = Integer.valueOf(req.getParameter("pdid"));

			/*************************** 2.開始查詢資料 ****************************************/

			PromotionsDetailService pmtDetailSvc = new PromotionsDetailService();
			PromotionsDetailVO promotionsDetailVO = pmtDetailSvc.getOnePromotionDetail(pmid, pdid);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/

			req.setAttribute("promotionsDetailVO", promotionsDetailVO);
			String url = "updatePromotionsDetail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		/***********************************************************************************/
		if ("update".equals(action)) { // 來自listAll.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			Integer pmid = Integer.parseInt(req.getParameter("pmid"));
			Integer pdid = Integer.parseInt(req.getParameter("pdid"));
			Integer pmPdDiscountPrice = Integer.parseInt(req.getParameter("pmPdDiscountPrice"));

			PromotionsDetailVO promotionsDetailVO = new PromotionsDetailVO();

			promotionsDetailVO.setPmid(pmid);
			promotionsDetailVO.setPdid(pdid);
			promotionsDetailVO.setPmPdDiscountPrice(pmPdDiscountPrice);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("promotionsDetailVO", promotionsDetailVO); // 含有輸入格式錯誤的empVO物件,也存入req
				String url = "listOnePromotionsDetail.jsp";
				RequestDispatcher failureView = req.getRequestDispatcher(url);
				failureView.forward(req, res);
				return; // 程式中斷
			}
			/*************************** 2.開始修改資料 *****************************************/
			PromotionsDetailService pmtDetailSvc = new PromotionsDetailService();
			promotionsDetailVO = pmtDetailSvc.updatePromotionsDetail(pmid, pdid, pmPdDiscountPrice);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("promotionsDetailVO", promotionsDetailVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "listAllPromotionsDetail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);

		}

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			
			Integer pmid = Integer.parseInt(req.getParameter("pmid"));
			Integer pdid = Integer.parseInt(req.getParameter("pdid"));
			Integer pmPdDiscountPrice = Integer.parseInt(req.getParameter("pmPdDiscountPrice"));

			PromotionsDetailVO promotionsDetailVO = new PromotionsDetailVO();

			promotionsDetailVO.setPmid(pmid);
			promotionsDetailVO.setPdid(pdid);
			promotionsDetailVO.setPmPdDiscountPrice(pmPdDiscountPrice);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("promotionsDetailVO", promotionsDetailVO);
				RequestDispatcher failureView = req.getRequestDispatcher("addPromotionsDetail.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}
			/*************************** 2.開始修改資料 *****************************************/
			PromotionsDetailService pmtDetailSvc = new PromotionsDetailService();
			promotionsDetailVO = pmtDetailSvc.addPromotionsDetail(pmid, pdid, pmPdDiscountPrice);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			
			String url = "listAllPromotionsDetail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

	}

}
