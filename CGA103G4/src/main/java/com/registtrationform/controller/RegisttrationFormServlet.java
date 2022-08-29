package com.registtrationform.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.registtrationform.model.*;
import com.teacher.model.TeacherService;
import com.teacher.model.TeacherVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class RegisttrationFormServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");				
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);			
			
			/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
			String str1 = req.getParameter("claid");
			String str2 = req.getParameter("memid");
			
			if (str1 == null || (str1.trim()).length() == 0) {
				errorMsgs.add("請輸入課程編號");
			}
			if (str2 == null || (str2.trim()).length() == 0) {
				errorMsgs.add("請輸入會員編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			Integer claid = null;
			Integer memid = null;
			try {
				claid = Integer.valueOf(str1);
			} catch (Exception e) {
				errorMsgs.add("課程編號格式不正確");
			}
			try {
				memid = Integer.valueOf(str2);
			} catch (Exception e) {
				errorMsgs.add("會員編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
			
			/***************************2.開始查詢資料*****************************************/
			RegisttrationFormService registtrationFormSvc = new RegisttrationFormService();
			RegisttrationFormVO registtrationFormVO = registtrationFormSvc.getOneRegisttrationForm(claid,memid);  
			
			if (registtrationFormVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("select_page.jsp");
				failureView.forward(req, res);
				return;//程式中斷
			}
								
			/***************************3.查詢完成,準備轉交(Send the Success view)*************/
			req.setAttribute("registtrationFormVO", registtrationFormVO); // 資料庫取出的VO物件,存入req 
			String url = "/back-end/registtrationform/listOneRegisttrationForm.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOne.jsp
			successView.forward(req, res);
	
			
		}		
		
		/***********************************************************************************/			
		
		if ("update".equals(action)) { // 來自listAll.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer claid = Integer.parseInt(req.getParameter("claid"));
							
						
				Integer memid = Integer.parseInt(req.getParameter("memid"));
				

				
				Integer regPayment = Integer.parseInt(req.getParameter("regPayment"));
				
				Integer regStatus = Integer.parseInt(req.getParameter("regStatus"));
				
				Integer regReview = Integer.parseInt(req.getParameter("regReview"));
				if(regReview == 0 ) {
					regReview = null;
				}

				String regReviewContent = req.getParameter("regReviewContent");
				
				RegisttrationFormVO registtrationFormVO = new RegisttrationFormVO();
				
				
				registtrationFormVO.setRegPayment(regPayment);

				registtrationFormVO.setRegStatus(regStatus);
				registtrationFormVO.setRegReview(regReview);
				registtrationFormVO.setRegReviewContent(regReviewContent);
				registtrationFormVO.setClaid(claid);
				registtrationFormVO.setMemid(memid);


//
//				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("registtrationFormVO", registtrationFormVO); // 含有輸入格式錯誤的empVO物件,也存入req
					String url = "/back-end/registtrationform/listOneRegisttrationForm.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return; //程式中斷
				}
				
//				/***************************2.開始修改資料*****************************************/
				RegisttrationFormService registtrationFormSvc = new RegisttrationFormService();
				registtrationFormVO = registtrationFormSvc.updateRegisttrationForm( regPayment,  regStatus,  regReview,
						 regReviewContent,  claid, memid);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("registtrationFormVO", registtrationFormVO); // 資料庫update成功後,正確的的empVO物件,存入req
				String url = "/back-end/registtrationform/listAllRegisttrationForm.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
				successView.forward(req, res);
		}
		/***********************************************************************************/
					
				

			
			
			
			
					

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		}
		
		
		
	}

