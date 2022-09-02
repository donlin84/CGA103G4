package com.orders.controller;

import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.orders.model.*;

public class OrdersServlet extends HttpServlet {
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
			String str = req.getParameter("ordid");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入訂單編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/orders/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer ordid = null;
			try {
				ordid = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("訂單編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/orders/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			OrdersService ordsv = new OrdersService();
			OrdersVO ordersVO = ordsv.getOne(ordid);
			if (ordersVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/orders/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("ordersVO", ordersVO); // 資料庫取出的empVO物件,存入req
			String url = "/back-end/orders/listOneOrders.jsp";
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

			/*************************** 2.開始查詢資料 ****************************************/
			OrdersService ordsv = new OrdersService();
			OrdersVO ordersVO = ordsv.getOne(ordid);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("ordersVO", ordersVO); // 資料庫取出的empVO物件,存入req
			String url = "/back-end/orders/update_orders_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer ordid = Integer.valueOf(req.getParameter("ordid"));

			Integer memid = Integer.valueOf(req.getParameter("memid"));

			Integer ordSubTotal = Integer.valueOf(req.getParameter("ordSubTotal"));

			Integer ordTotal = Integer.valueOf(req.getParameter("ordTotal"));

			Integer ordStatus = Integer.valueOf(req.getParameter("ordStatus"));

			LocalDateTime ordCreate = LocalDateTime.parse(req.getParameter("ordCreate"));

//String memid = req.getParameter("memid");
//				String memidReg = "^[(a-zA-Z0-9)]{3}$";
//				if (memid == null || memid.trim().length() == 0) {
//					errorMsgs.add("會員編號: 請勿空白");
//				} else if(!memid.trim().matches(memidReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("會員編號: 只能是數字 , 且長度必需為3");
//	            }
//			Integer memCpid = 0;
//			if(req.getParameter("memCpid") == "") {
//				memCpid = 0;
//			} else {
//				memCpid = Integer.valueOf(req.getParameter("memCpid"));
//			}
			
			Integer memCpid = 0;
			
			String memCpidStr = req.getParameter("memCpid");
			String cpidReg = "^[0-9]{1,2}$";
			if(!memCpidStr.trim().matches(cpidReg) && memCpidStr.trim().length() != 0) {
//				errorMsgs.add("請輸入正確優惠券編號");
				memCpid = null;
			} else if(memCpidStr.trim().length() == 0) {
				memCpidStr = "";
				memCpid = null;
			} else {
				memCpid = Integer.valueOf(memCpidStr);
			}
			
//			String memCpidStr = req.getParameter("memCpid");
//			String cpidReg = "^[0-9]{1,2}$";
//			if(!memCpidStr.trim().matches(cpidReg) && memCpidStr.trim().length() != 0) {
//				errorMsgs.add("請輸入正確優惠券編號");
//			} else if(memCpidStr.trim().length() == 0) {
//				memCpid = null;
//			} else {
//				memCpid = Integer.valueOf(memCpidStr);
//			}
//			memCpid = Integer.valueOf(memCpidStr);
			
			
//			Integer memCpid = null;
//			try {
//				memCpid = Integer.valueOf(req.getParameter("memCpid"));
//			} catch (NumberFormatException e1) {
//				memCpid = null;
//				errorMsgs.add("優惠券編號請填數字");
//			}

			String ordRecipient = req.getParameter("ordRecipient").trim();
			if (ordRecipient == null || ordRecipient.trim().length() == 0) {
				errorMsgs.add("取貨人姓名請勿空白");
			}
			
			String ordPhoneReg = "^[0]{1}[9]{1}[0-9]{8}$";
			String ordRecPhone = req.getParameter("ordRecPhone").trim();
			if (ordRecPhone == null || ordRecPhone.trim().length() == 0) {
				errorMsgs.add("取貨人連絡電話請勿空白");
			} else if(!ordRecPhone.trim().matches(ordPhoneReg)) {
				errorMsgs.add("請輸入正確手機聯絡電話");
			}

//				java.sql.Date hiredate = null;
//				try {
//hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//				} catch (IllegalArgumentException e) {
//					hiredate=new java.sql.Date(System.currentTimeMillis());
//					errorMsgs.add("請輸入日期");
//				}

//				Double sal = null;
//				try {
//sal = Double.valueOf(req.getParameter("sal").trim());
//				} catch (NumberFormatException e) {
//					sal = 0.0;
//					errorMsgs.add("薪水請填數字.");
//				}
//
//				Double comm = null;
//				try {
//comm = Double.valueOf(req.getParameter("comm").trim());
//				} catch (NumberFormatException e) {
//					comm = 0.0;
//					errorMsgs.add("獎金請填數字.");
//				}

			Integer ordPayment = Integer.valueOf(req.getParameter("ordPayment"));

			Integer ordDelivery = Integer.valueOf(req.getParameter("ordDelivery"));

			String ordAddress = req.getParameter("ordAddress").trim();

//			LocalDateTime ordHopetime = LocalDateTime.parse(req.getParameter("ordHopetime"));

			OrdersVO ordersVO = new OrdersVO();
			ordersVO.setOrdid(ordid);
			ordersVO.setMemid(memid);
			ordersVO.setMemCpid(memCpid);
			ordersVO.setOrdSubTotal(ordSubTotal);
			ordersVO.setOrdTotal(ordTotal);
			ordersVO.setOrdStatus(ordStatus);
			ordersVO.setOrdCreate(ordCreate);
			ordersVO.setOrdRecipient(ordRecipient);
			ordersVO.setRecPhone(ordRecPhone);
			ordersVO.setOrdPayment(ordPayment);
			ordersVO.setOrdDelivery(ordDelivery);
			ordersVO.setOrdAddress(ordAddress);
//			ordersVO.setOrdHopetime(ordHopetime);

//				Integer deptno = Integer.valueOf(req.getParameter("deptno").trim());	
//
//				EmpVO empVO = new EmpVO();
//				empVO.setEmpno(empno);
//				empVO.setEname(ename);
//				empVO.setJob(job);
//				empVO.setHiredate(hiredate);
//				empVO.setSal(sal);
//				empVO.setComm(comm);
//				empVO.setDeptno(deptno);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("ordersVO", ordersVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/orders/update_orders_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			OrdersService ordSvc = new OrdersService();
			ordersVO = ordSvc.updateOrder(ordid, memid, memCpid, ordSubTotal, ordTotal, ordStatus, ordCreate, ordRecipient,
					ordRecPhone, ordPayment, ordDelivery, ordAddress);

//				EmpService empSvc = new EmpService();
//				empVO = empSvc.updateEmp(empno, ename, job, hiredate, sal,comm, deptno);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("ordersVO", ordersVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back-end/orders/listOneOrders.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
//			Integer ordid = Integer.valueOf(req.getParameter("ordid").trim());

//			String memid = req.getParameter("memid");
			Integer memid = 0;
			String memidStr = req.getParameter("memid");
			String memidReg = "^[(0-9)]{3}$";
			
			if(!memidStr.matches(memidReg)) {
				errorMsgs.add("會員編號: 只能是數字 , 且長度必需為3");
			} else if(memidStr == null || memidStr.trim().length() == 0) {
				errorMsgs.add("會員編號: 請勿空白");
				memidStr = "";
			} else {
				memid = Integer.valueOf(memidStr);
			}
			
			

			Integer ordSubTotal = null;
			try {
				ordSubTotal = Integer.valueOf(req.getParameter("ordSubTotal"));
				if(ordSubTotal < 0) {
					errorMsgs.add("訂單小計不會低於0元, 請重新輸入");
				}
			} catch (NumberFormatException e) {
				errorMsgs.add("訂單小計請填數字");
			}

			Integer ordTotal = null;
			try {
				ordTotal = Integer.valueOf(req.getParameter("ordTotal"));
				if(ordTotal < 0) {
					errorMsgs.add("訂單小計不會低於0元, 請重新輸入");
				} else if(ordTotal > ordSubTotal) {
					errorMsgs.add("訂單總計不會高於小計, 請重新輸入");
				}
			} catch (NumberFormatException e) {
				errorMsgs.add("訂單總計請填數字");
			}

//			Integer ordStatus = Integer.valueOf(req.getParameter("ordStatus"));

//			LocalDateTime ordCreate = LocalDateTime.parse(req.getParameter("ordCreate").trim());

			// String memid = req.getParameter("memid");
//							String memidReg = "^[(a-zA-Z0-9)]{3}$";
//							if (memid == null || memid.trim().length() == 0) {
//								errorMsgs.add("會員編號: 請勿空白");
//							} else if(!memid.trim().matches(memidReg)) { //以下練習正則(規)表示式(regular-expression)
//								errorMsgs.add("會員編號: 只能是數字 , 且長度必需為3");
//				            }

			Integer memCpid = 0;
			
			String memCpidStr = req.getParameter("memCpid");
			String cpidReg = "^[0-9]{1,2}$";
			if(!memCpidStr.trim().matches(cpidReg) && memCpidStr.trim().length() != 0) {
				errorMsgs.add("請輸入正確優惠券編號");
				memCpid = null;
			} else if(memCpidStr.trim().length() == 0) {
				memCpidStr = "";
				memCpid = null;
			} else {
				memCpid = Integer.valueOf(memCpidStr);
			}

			String ordRecipient = req.getParameter("ordRecipient").trim();
			if (ordRecipient == null || ordRecipient.trim().length() == 0) {
				errorMsgs.add("取貨人姓名請勿空白");
			}

			String ordRecPhone = req.getParameter("ordRecPhone").trim();
			if (ordRecPhone == null || ordRecPhone.trim().length() == 0) {
				errorMsgs.add("取貨人連絡電話請勿空白");
			}

//							java.sql.Date hiredate = null;
//							try {
			// hiredate = java.sql.Date.valueOf(req.getParameter("hiredate").trim());
//							} catch (IllegalArgumentException e) {
//								hiredate=new java.sql.Date(System.currentTimeMillis());
//								errorMsgs.add("請輸入日期");
//							}

//							Double sal = null;
//							try {
			// sal = Double.valueOf(req.getParameter("sal").trim());
//							} catch (NumberFormatException e) {
//								sal = 0.0;
//								errorMsgs.add("薪水請填數字.");
//							}
			//
//							Double comm = null;
//							try {
			// comm = Double.valueOf(req.getParameter("comm").trim());
//							} catch (NumberFormatException e) {
//								comm = 0.0;
//								errorMsgs.add("獎金請填數字.");
//							}

			Integer ordPayment = Integer.valueOf(req.getParameter("ordPayment").trim());

			Integer ordDelivery = Integer.valueOf(req.getParameter("ordDelivery").trim());

			String ordAddress = req.getParameter("ordAddress").trim();

//			LocalDateTime ordHopetime = LocalDateTime.parse(req.getParameter("ordHopetime").trim());

			OrdersVO ordersVO = new OrdersVO();
//			ordersVO.setOrdid(ordid);
			ordersVO.setMemid(memid);
			ordersVO.setMemCpid(memCpid);
			ordersVO.setOrdSubTotal(ordSubTotal);
			ordersVO.setOrdTotal(ordTotal);
//			ordersVO.setOrdStatus(ordStatus);
			ordersVO.setOrdRecipient(ordRecipient);
			ordersVO.setRecPhone(ordRecPhone);
			ordersVO.setOrdPayment(ordPayment);
			ordersVO.setOrdDelivery(ordDelivery);
			ordersVO.setOrdAddress(ordAddress);
//			ordersVO.setOrdHopetime(ordHopetime);

//							Integer deptno = Integer.valueOf(req.getParameter("deptno").trim());	
			//
//							EmpVO empVO = new EmpVO();
//							empVO.setEmpno(empno);
//							empVO.setEname(ename);
//							empVO.setJob(job);
//							empVO.setHiredate(hiredate);
//							empVO.setSal(sal);
//							empVO.setComm(comm);
//							empVO.setDeptno(deptno);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("ordersVO", ordersVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/orders/addOrders.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始新增資料 ***************************************/
			OrdersService ordSvc = new OrdersService();
			ordersVO = ordSvc.addOrder(memid, memCpid, ordSubTotal, ordTotal, ordRecipient,
					ordRecPhone, ordPayment, ordDelivery, ordAddress);
			
//			EmpService empSvc = new EmpService();
//			empVO = empSvc.addEmp(ename, job, hiredate, sal, comm, deptno);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/orders/listAllOrders.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
			successView.forward(req, res);
		}

//		if ("delete".equals(action)) { // 來自listAllEmp.jsp
//
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//
//			/*************************** 1.接收請求參數 ***************************************/
//			Integer empno = Integer.valueOf(req.getParameter("empno"));
//
//			/*************************** 2.開始刪除資料 ***************************************/
//			EmpService empSvc = new EmpService();
//			empSvc.deleteEmp(empno);
//
//			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
//			String url = "/emp/listAllEmp.jsp";
//			RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
//			successView.forward(req, res);
//		}
	}
}
