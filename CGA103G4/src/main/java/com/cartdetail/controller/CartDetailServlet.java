package com.cartdetail.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cartdetail.model.*;
import com.membercoupon.model.MemberCouponService;
import com.membercoupon.model.MemberCouponVO;

@WebServlet("/front-end/cartdetail/CartDetail.do")
public class CartDetailServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		if("get_value".equals(action)) {
			System.out.println(action);
			Integer cptpid = Integer.valueOf(req.getParameter("cptpid")); 
			System.out.println(cptpid);
			
			MemberCouponService mcSvc = new MemberCouponService();
			MemberCouponVO mcVO = mcSvc.getOneMemberCoupon(cptpid);
			Integer dis = mcVO.getCpTpVO().getCpDiscount();
			Integer memcpid	= mcVO.getMemCpid();		
			JSONArray json = new JSONArray();
			try {
				json.put(dis);
				json.put(memcpid);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			res.getWriter().print(json);
			
		}
		
		if("checkout".equals(action)) {
			String list = req.getParameter("jsonstr");
			JSONArray array = new JSONArray(list);
			
			
			JSONObject one = array.getJSONObject(0);
			Integer oneid = one.getInt("memid");
			
			CartDetailService cartSvc = new CartDetailService();
			List<CartDetailVO> vo = cartSvc.getOnes(oneid);
			req.setAttribute("list", vo);
			
			Integer subtotal = Integer.valueOf(req.getParameter("subtotal"));			Integer memid = Integer.valueOf(req.getParameter("memid"));
			Integer total = Integer.valueOf(req.getParameter("total"));
			Integer memCpid = Integer.valueOf(req.getParameter("memCpid_form"));
			String pdidstr = req.getParameter("pdid");
			String pdNumberstr = req.getParameter("pdNumber");
			String pdPrice = req.getParameter("pdPrice");
			String pdDiscount = req.getParameter("pdDiscount");
//			String vo = req.getParameter("list");
//			System.out.println(vo);
			
			JSONArray pdidArray = new JSONArray(pdidstr);
			JSONArray pdNumberArray = new JSONArray(pdNumberstr);
			JSONArray pdPriceArray = new JSONArray(pdPrice);
			JSONArray pdDisArray = new JSONArray(pdDiscount);
			
			List<Integer> pdidList = new ArrayList<Integer>();
			List<Integer> pdNumberList = new ArrayList<Integer>();
			List<Integer> pdPriceList = new ArrayList<Integer>();
			List<Integer> pdDisList = new ArrayList<Integer>();
			
			for(int i = 0; i < pdidArray.length(); i++) {
				pdidList.add(pdidArray.getInt(i));
				pdNumberList.add(pdNumberArray.getInt(i));
				pdPriceList.add(pdPriceArray.getInt(i));
				pdDisList.add(pdDisArray.getInt(i));
			}
			
			req.setAttribute("memid", memid);
			req.setAttribute("subtotal", subtotal);
			req.setAttribute("total", total);
			req.setAttribute("memCpid", memCpid);
			req.setAttribute("pdid", pdidList);
			req.setAttribute("pdNumber", pdNumberList);
			req.setAttribute("pdPrice", pdPriceList);
			req.setAttribute("pdDis", pdDisList);
			
			String url = "/front-end/cartdetail/checkout.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		
//		if ("checkoutDisplay".equals(action)){
//			HttpSession session = req.getSession();
//			Integer memid =  Integer.valueOf( (String) session.getAttribute("memid") );
//			session.setAttribute("memid", memid);
//			
//			CartDetailService cartDetailService = new CartDetailService();
//			List<CartDetailVO> cartDetailVOs = cartDetailService.getOnes(memid);
//			JSONArray carts = new JSONArray(cartDetailVOs );
//			res.getWriter().print(carts);
//			 
//			
//		}
		
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
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
			req.setAttribute("memid", memid);
			
//			String pdidstr = req.getParameter("pdid");
//			
//			Integer pdid = Integer.valueOf(pdidstr);
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/cartdetail/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷 
			}

			/*************************** 2.開始查詢資料 *****************************************/
			req.setAttribute("memid", memid);
			CartDetailService resv = new CartDetailService();
			List<CartDetailVO> list = resv.getOnes(memid);
			if (list == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/cartdetail/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("cartDetailVO", list); // 資料庫取出的empVO物件,存入req
			String url = "/front-end/cartdetail/NewFile.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer memid = Integer.valueOf(req.getParameter("memid"));
			
			Integer pdid = Integer.valueOf(req.getParameter("pdid"));

			/*************************** 2.開始查詢資料 ****************************************/
			CartDetailService resv = new CartDetailService();
			CartDetailVO cartDetailVO = resv.getOne(memid, pdid);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("cartDetailVO", cartDetailVO); 
			String url = "/front-end/cartdetail/update_cartdetail_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

			Integer memid = Integer.valueOf(req.getParameter("memid").trim());
			
			Integer pdid = Integer.valueOf(req.getParameter("pdid").trim());
			
			Integer pdNumber = null;

			String pdNumstr = req.getParameter("pdNumber");
			if (pdNumstr == null || (pdNumstr.trim()).length() == 0) {
				errorMsgs.add("請輸入商品數量");
			} else {
				try {
					pdNumber = Integer.valueOf(pdNumstr);
					if(pdNumber <= 0) {
						errorMsgs.add("商品數量請輸入大於0的整數");
					}
				} catch (NumberFormatException e) {
					errorMsgs.add("商品數量請輸入數字");
				}
			}
			
			CartDetailVO cartDetailVO = new CartDetailVO();
			cartDetailVO.setMemid(memid);
			cartDetailVO.setPdid(pdid);
			cartDetailVO.setPdNumber(pdNumber);
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("cartDetailVO", cartDetailVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/cartdetail/update_cartdetail_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			CartDetailService ordSvc = new CartDetailService();
			cartDetailVO = ordSvc.updateCartDetail(pdNumber, memid, pdid);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("cartDetailVO", cartDetailVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url ="CartDetail.do?action=getOne_For_Display";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
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
			
			Integer pdNumber = 0;
			String pdNumstr = req.getParameter("pdNumber");
			if (pdNumstr == null || (pdNumstr.trim()).length() == 0) {
				errorMsgs.add("請輸入商品數量");
			} else {
				try {
					pdNumber = Integer.valueOf(pdNumstr);
					if(pdNumber <= 0) {
						errorMsgs.add("商品數量請輸入大於0的整數");
					}
				} catch (NumberFormatException e) {
					errorMsgs.add("商品數量請輸入數字");
				}
			}
			
			CartDetailVO cartDetailVO = new CartDetailVO();
			
			cartDetailVO.setMemid(memid);
			cartDetailVO.setPdid(pdid);
			cartDetailVO.setPdNumber(pdNumber);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("cartDetailVO", cartDetailVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/front-end/cartdetail/addCartDetail.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始新增資料 ***************************************/
			CartDetailService cartSvc = new CartDetailService();
			cartDetailVO = cartSvc.addCartDetail(memid, pdid, pdNumber);
			
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/front-end/cartdetail/listAllCartDetail.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
			successView.forward(req, res);
		}
		
		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer memid = Integer.valueOf(req.getParameter("memid"));
			Integer pdid = Integer.valueOf(req.getParameter("pdid"));

			/*************************** 2.開始刪除資料 ***************************************/
			CartDetailService cartSvc = new CartDetailService();
			cartSvc.deleteCartDeatil(memid, pdid);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			req.setAttribute("memid", memid);
			String url = "/front-end/cartdetail/NewFile.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
			successView.forward(req, res);
		}
		
		if ("minus".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			/*************************** 1.接收請求參數 ***************************************/
			Integer memid = Integer.valueOf(req.getParameter("memid"));
			Integer pdid = Integer.valueOf(req.getParameter("pdid"));
			Integer pdNumber = Integer.valueOf(req.getParameter("pdNumber"));
			/*************************** 2.開始修改資料 ***************************************/
			CartDetailService cartSvc = new CartDetailService();
			if(pdNumber == 1) {
				cartSvc.deleteCartDeatil(memid, pdid);
				req.setAttribute("memid", memid);
				String url = "/front-end/cartdetail/NewFile.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
				successView.forward(req, res);
				return;
			}
			
			cartSvc.minus(memid, pdid);
			/*************************** 3.修改完成,準備轉交(Send the Success view) ***********/
			req.setAttribute("memid", memid);
			String url = "/front-end/cartdetail/NewFile.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
			successView.forward(req, res);
		}
		
		if ("plus".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			/*************************** 1.接收請求參數 ***************************************/
			Integer memid = Integer.valueOf(req.getParameter("memid"));
			Integer pdid = Integer.valueOf(req.getParameter("pdid"));
			/*************************** 2.開始修改資料 ***************************************/
			CartDetailService cartSvc = new CartDetailService();
			cartSvc.plus(memid, pdid);
			/*************************** 3.修改完成,準備轉交(Send the Success view) ***********/
			req.setAttribute("memid", memid);
			String url = "/front-end/cartdetail/NewFile.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
			successView.forward(req, res);
		}
		
//		if ("checkout".equals(action)) {
//			List<String> errorMsgs = new LinkedList<String>();
//			// Store this set in the request scope, in case we need to
//			// send the ErrorPage view.
//			req.setAttribute("errorMsgs", errorMsgs);
//			
//			/*************************** 1.接收請求參數 ***************************************/
//			Integer total = Integer.valueOf(req.getParameter("total"));
////			System.out.println(total);
//			JSONArray jpdid = null; 
//			JSONArray jpdNumber = null;
//			JSONArray jpdPrice = null;
//			JSONArray jpdDiscount = null;
//			Integer memid = Integer.valueOf(req.getParameter("memid"));
//			try {
//				jpdid = new JSONArray(req.getParameter("pdid"));
//				jpdNumber = new JSONArray(req.getParameter("pdNumber"));
//				jpdPrice = new JSONArray(req.getParameter("pdPrice"));
//				jpdDiscount = new JSONArray(req.getParameter("pdDiscount"));
////				System.out.println(jpdid);
////				System.out.println(jpdNumber);
////				System.out.println(jpdPrice);
////				System.out.println(jpdDiscount);
////				System.out.println(jpdid.getInt(0));
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
//		}
	}
}
