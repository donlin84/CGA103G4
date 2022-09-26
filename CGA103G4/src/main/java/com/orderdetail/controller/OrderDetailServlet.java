package com.orderdetail.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.orderdetail.model.OrderDetailService;
import com.orderdetail.model.OrderDetailVO;

@WebServlet("/back-end/orderdetail/OrderDetail.do")
public class OrderDetailServlet extends HttpServlet{
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
			String ordidStr = req.getParameter("ordid");
			Integer ordid = Integer.valueOf(ordidStr);
			req.setAttribute("ordid", ordid);
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/orderdetail/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷 
			}

			/*************************** 2.開始查詢資料 *****************************************/
			OrderDetailService resv = new OrderDetailService();
			List<OrderDetailVO> list = resv.getOnes(ordid);
//			OrderDetailVO orderDetailVO = resv.getOne(ordid);
			if (list == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/orderdetail/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("orderDetailVO", list); // 資料庫取出的empVO物件,存入req
			String url = "/back-end/orderdetail/listOneOrderDetail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer ordid = Integer.valueOf(req.getParameter("ordid"));
			
			Integer pdid = Integer.valueOf(req.getParameter("pdid"));

			/*************************** 2.開始查詢資料 ****************************************/
			OrderDetailService resv = new OrderDetailService();
			OrderDetailVO orderDetailVO = resv.getOne(ordid, pdid);
//			OrderDetailVO orderDetailVO = resv.getOne(ordid);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("orderDetailVO", orderDetailVO); 
			String url = "/back-end/orderdetail/update_orderdetail_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			Integer ordid = Integer.valueOf(req.getParameter("ordid").trim());
			
			Integer pdid = Integer.valueOf(req.getParameter("pdid").trim());
			
			Integer detailNumber = null;
			
			String detailNumstr = req.getParameter("detailNumber");
			if (detailNumstr == null || (detailNumstr.trim()).length() == 0) {
				errorMsgs.add("請輸入商品數量");
			} else {
				try {
					detailNumber = Integer.valueOf(detailNumstr);
					if(detailNumber <= 0) {
						errorMsgs.add("商品數量請輸入大於0的整數");
					}
				} catch (NumberFormatException e) {
					errorMsgs.add("商品數量請輸入數字");
				}
			}

			Integer detailPrice = Integer.valueOf(req.getParameter("detailPrice").trim());
			
			Integer detailGoodPrice = Integer.valueOf(req.getParameter("detailGoodPrice").trim());

			
			OrderDetailVO orderDetailVO = new OrderDetailVO();
			orderDetailVO.setOrdid(ordid);
			orderDetailVO.setPdid(pdid);
			orderDetailVO.setDetailNumber(detailNumber);
			orderDetailVO.setDetailPrice(detailPrice);
			orderDetailVO.setDetailGoodPrice(detailGoodPrice);
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("orderDetailVO", orderDetailVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/orderdetail/update_orderdetail_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			OrderDetailService ordSvc = new OrderDetailService();
			orderDetailVO = ordSvc.updateOrderDetail(detailNumber, detailPrice, detailGoodPrice, ordid, pdid);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("orderDetailVO", orderDetailVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url ="OrderDetail.do?action=getOne_For_Display";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			Integer ordid = 0;
			String ordidstr = req.getParameter("ordid");
			if (ordidstr == null || (ordidstr.trim()).length() == 0) {
				errorMsgs.add("請輸入訂單編號");
			} else {
				try {
					ordid = Integer.valueOf(ordidstr);
					if(ordid <= 0) {
						errorMsgs.add("訂單編號請輸入大於0的整數");
					}
				} catch (NumberFormatException e) {
					errorMsgs.add("訂單編號請輸入數字");
				}
			}
			
			Integer pdid = 0;
			String pdidStr = req.getParameter("pdid");
			String pdidReg = "^[4]{1}[0-9]{3}$";
			
			if(!pdidStr.matches(pdidReg)) {
				errorMsgs.add("商品編號: 只能是以4開頭長度為4之數字");
			} else if(pdidStr == null || pdidStr.trim().length() == 0) {
				errorMsgs.add("商品編號請勿空白");
				pdidStr = "";
			} else {
				pdid = Integer.valueOf(pdidStr);
			}
			
			Integer detailNumber = 0;
			String detailNumstr = req.getParameter("detailNumber");
			if (detailNumstr == null || (detailNumstr.trim()).length() == 0) {
				errorMsgs.add("請輸入商品數量");
			} else {
				try {
					detailNumber = Integer.valueOf(detailNumstr);
					if(detailNumber <= 0) {
						errorMsgs.add("商品數量請輸入大於0的整數");
					}
				} catch (NumberFormatException e) {
					errorMsgs.add("商品數量請輸入數字");
				}
			}
			
			Integer detailPrice = 0;
			String detailPristr = req.getParameter("detailPrice");
			if (detailPristr == null || (detailPristr.trim()).length() == 0) {
				errorMsgs.add("請輸入商品單價");
			} else {
				try {
					detailPrice = Integer.valueOf(detailPristr);
					if(detailPrice <= 0) {
						errorMsgs.add("商品單價請輸入大於0的整數");
					}
				} catch (NumberFormatException e) {
					errorMsgs.add("商品單價請輸入數字");
				}
			}
			
			Integer detailGoodPrice = 0;
			String detailGoodPristr = req.getParameter("detailPrice");
			if (detailGoodPristr == null || (detailGoodPristr.trim()).length() == 0) {
				errorMsgs.add("請輸入商品單價");
			} else {
				try {
					detailGoodPrice = Integer.valueOf(detailGoodPristr);
					if(detailGoodPrice <= 0) {
						errorMsgs.add("商品單價請輸入大於0的整數");
					}
				} catch (NumberFormatException e) {
					errorMsgs.add("商品單價請輸入數字");
				}
			}
			
			OrderDetailVO orderDetailVO = new OrderDetailVO();
			
			orderDetailVO.setOrdid(ordid);
			orderDetailVO.setPdid(pdid);
			orderDetailVO.setDetailNumber(detailNumber);
			orderDetailVO.setDetailPrice(detailPrice);
			orderDetailVO.setDetailGoodPrice(detailGoodPrice);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("orderDetailVO", orderDetailVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/orderdetail/addOrderDetail.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始新增資料 ***************************************/
			OrderDetailService cartSvc = new OrderDetailService();
			orderDetailVO = cartSvc.addOrderDetail(ordid, pdid, detailNumber, detailPrice, detailGoodPrice);
			
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/orderdetail/listAllOrderDetail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
			successView.forward(req, res);
		}
	}
}
