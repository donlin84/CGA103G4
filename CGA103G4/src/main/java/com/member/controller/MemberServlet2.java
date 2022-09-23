package com.member.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;


import com.member.model.*;
@WebServlet("/front-end/member/Member.do")
public class MemberServlet2 extends HttpServlet{

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
//==================================================================================================

		if ("getOne_For_Update".equals(action)) { 

			List<String> errorMsgs = new LinkedList<String>();

			req.setAttribute("errorMsgs", errorMsgs);
			
				/***************************1.接收請求參數****************************************/
				Integer memid = Integer.valueOf(req.getParameter("memid"));
				
				/***************************2.開始查詢資料****************************************/
				MemberService memberSvc = new MemberService();
				MemberVO memberVO = memberSvc.getOneMember(memid);
								
				/***************************3.查詢完成,準備轉交(Send the Success view)************/
				req.setAttribute("memberVO", memberVO);         // 資料庫取出的memberVO物件,存入req
				String url = "/front-end/member/update_member_input.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_member_input.jsp
				successView.forward(req, res);
		}		
//==================================================================================================
		
		if ("update".equals(action)) { // 來自update_member_input.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer memid = Integer.valueOf(req.getParameter("memid"));

				String memName = req.getParameter("memName");
				String memNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memName == null || memName.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!memName.trim().matches(memNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String memAccount = req.getParameter("memAccount").trim();
				if (memAccount == null || memAccount.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				}	
				
				String memPassword = req.getParameter("memPassword").trim();
				if (memPassword == null || memPassword.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}	
				
				String memGender = req.getParameter("memGender").trim();
				if (memGender == null || memGender.trim().length() == 0) {
					errorMsgs.add("性別請勿空白");
				}	
				
				String memPhone = req.getParameter("memPhone").trim();
				if (memPhone == null || memPhone.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}	
				
				String memEmail = req.getParameter("memEmail").trim();
				if (memEmail == null || memEmail.trim().length() == 0) {
					errorMsgs.add("信箱請勿空白");
				}	

				String memAddres = req.getParameter("memAddres").trim();
				if (memAddres == null || memAddres.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}	
				
				java.sql.Date memBirthday = null;
				try {
					memBirthday = java.sql.Date.valueOf(req.getParameter("memBirthday").trim());
				} catch (IllegalArgumentException e) {
					memBirthday=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入生日!");
				}

	
				
				String memNation = req.getParameter("memNation").trim();
				if (memNation == null || memNation.trim().length() == 0) {
					errorMsgs.add("國家請勿空白");
				}	
				
				Integer memStatus =0;
				MemberVO memberVO = new MemberVO();
				memberVO.setMemid(memid);
				memberVO.setMemName(memName);
				memberVO.setMemAccount(memAccount);
				memberVO.setMemPassword(memPassword);
				memberVO.setMemGender(memGender);
				memberVO.setMemPhone(memPhone);
				memberVO.setMemEmail(memEmail);
				memberVO.setMemAddres(memAddres);
				memberVO.setMemBirthday(memBirthday);
				memberVO.setMemStatus(memStatus);
				memberVO.setMemNation(memNation);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/update_member_input.jsp");
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
				MemberService memberSvc = new MemberService();
				memberVO = memberSvc.updateMember(memid, memName, memAccount, memPassword, 
				memGender, memPhone, memEmail, memAddres, memBirthday, memStatus, memNation);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("memberVO", memberVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/front-end/member/update.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMember.jsp
				successView.forward(req, res);
		}
//==================================================================================================
        if ("insert".equals(action)) { // 來自addMember.jsp的請求  
//System.out.println(action);
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

				/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
				String memName = req.getParameter("memName");
				String memNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (memName == null || memName.trim().length() == 0) {
					errorMsgs.add("會員姓名: 請勿空白");
				} else if(!memName.trim().matches(memNameReg)) { //以下練習正則(規)表示式(regular-expression)
					errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				
				String memAccount = req.getParameter("memAccount").trim();
				if (memAccount == null || memAccount.trim().length() == 0) {
					errorMsgs.add("帳號請勿空白");
				}	
				
				String memPassword = req.getParameter("memPassword").trim();
				if (memPassword == null || memPassword.trim().length() == 0) {
					errorMsgs.add("密碼請勿空白");
				}	
				
				String memGender = req.getParameter("memGender");
				if (memGender == null || memGender.trim().length() == 0) {
					errorMsgs.add("性別請勿空白");
				}	
						
				String memPhone = req.getParameter("memPhone").trim();
				if (memPhone == null || memPhone.trim().length() == 0) {
					errorMsgs.add("電話請勿空白");
				}	
				
				String memEmail = req.getParameter("memEmail").trim();
				if (memEmail == null || memEmail.trim().length() == 0) {
					errorMsgs.add("信箱請勿空白");
				}	
				
				String memAddres1 = req.getParameter("memAddres1");
				String memAddres2 = req.getParameter("memAddres2");
				String memAddres3 = req.getParameter("memAddres3");
				String memAddres = memAddres1+memAddres2+memAddres3;
				if (memAddres1 == null || memAddres1.trim().length() == 0 || 
					memAddres2 == null || memAddres2.trim().length() == 0 ||
					memAddres3 == null || memAddres3.trim().length() == 0) {
					errorMsgs.add("地址請勿空白");
				}
				
				java.sql.Date memBirthday = null;
				try {
					memBirthday = java.sql.Date.valueOf(req.getParameter("memBirthday").trim());
				} catch (IllegalArgumentException e) {
					memBirthday=new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入生日!");
				}

			
				String memNation = req.getParameter("memNation").trim();
				if (memNation == null || memNation.trim().length() == 0) {
					errorMsgs.add("國家請勿空白");
				}	
				

				MemberVO memberVO = new MemberVO();
				memberVO.setMemName(memName);
				memberVO.setMemAccount(memAccount);
				memberVO.setMemPassword(memPassword);
				memberVO.setMemGender(memGender);
				memberVO.setMemPhone(memPhone);
				memberVO.setMemEmail(memEmail);
				memberVO.setMemAddres(memAddres);
				memberVO.setMemBirthday(memBirthday);
				memberVO.setMemNation(memNation);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("memberVO", memberVO); // 含有輸入格式錯誤的memberVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/addMember.jsp");
					failureView.forward(req, res);
					return;
				}
				
				/***************************2.開始新增資料***************************************/
				MemberService memberSvc = new MemberService();
//				System.out.println(memberSvc);
				memberVO = memberSvc.addMember(memName, memAccount, memPassword, 
						memGender, memPhone, memEmail, memAddres, memBirthday, memNation);
				
				/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "/front-end/member/frontEndLogin.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMember.jsp
				successView.forward(req, res);
				
		}
    	}
	}

//============================================================================
		
//      		if ("listMember_ByCompositeQuery".equals(action)) { // 來自select_page.jsp的複合查詢請求
//      			List<String> errorMsgs = new LinkedList<String>();
//      			// Store this set in the request scope, in case we need to
//      			// send the ErrorPage view.
//      			req.setAttribute("errorMsgs", errorMsgs);
//
//      				
//      				/***************************1.將輸入資料轉為Map**********************************/ 
//      				//採用Map<String,String[]> getParameterMap()的方法 
//      				//注意:an immutable java.util.Map 
//      				//Map<String, String[]> map = req.getParameterMap();
//      				HttpSession session = req.getSession();
//      				@SuppressWarnings("unchecked")
//					Map<String, String[]> map = (Map<String, String[]>)session.getAttribute("map");
//      				
//      				// 以下的 if 區塊只對第一次執行時有效
//      				if (req.getParameter("whichPage") == null){
//      					Map<String, String[]> map1 = new HashMap<String, String[]>(req.getParameterMap());
//      					session.setAttribute("map",map1);
//      					map = map1;
//      				} 
//      				
//      				/***************************2.開始複合查詢***************************************/
//      				MemberService memberSvc = new MemberService();
//      				List<MemberVO> list  = memberSvc.getAll(map);
//      				
//      				/***************************3.查詢完成,準備轉交(Send the Success view)************/
//      				req.setAttribute("listMember_ByCompositeQuery", list); // 資料庫取出的list物件,存入request
//      				RequestDispatcher successView = req.getRequestDispatcher("/back-end/member/listMember_ByCompositeQuery.jsp"); // 成功轉交listEmps_ByCompositeQuery.jsp
//      				successView.forward(req, res);
//      		}
		


