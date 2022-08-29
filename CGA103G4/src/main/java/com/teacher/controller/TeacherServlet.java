package com.teacher.controller;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.jasper.tagplugins.jstl.core.ForEach;

//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;

import com.teacher.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class TeacherServlet extends HttpServlet {

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
				String str = req.getParameter("thrid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入教師編號");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				Integer third = null;
				try {
					third = Integer.valueOf(str);
				} catch (Exception e) {
					errorMsgs.add("教師編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req
							.getRequestDispatcher("select_page.jsp");
					failureView.forward(req, res);
					return;//程式中斷
				}
				
				/***************************2.開始查詢資料*****************************************/
				TeacherService teacherSvc = new TeacherService();
				TeacherVO teacherVO = teacherSvc.getOneTeacher(third);  
				
				if (teacherVO == null) {
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
				req.setAttribute("teacherVO", teacherVO); // 資料庫取出的VO物件,存入req 
				String thrpic = null;
				try {
					thrpic = Base64.getEncoder().encodeToString(teacherVO.getThrPic());	
					
				} catch (NullPointerException e) {
					try {
						ServletContext servletContext = req.getServletContext();
						String filepath = servletContext.getRealPath("/picture/nopicture.jpg");
						File file=new File(filepath);
						
						FileInputStream fis = new FileInputStream(file);
						byte[] b = new byte[fis.available()];
						fis.read(b);
						fis.close();
						thrpic = Base64.getEncoder().encodeToString(b);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}	
				}
				req.setAttribute("thrpic",thrpic);
				String url = "/back-end/teacher/listOneTeacher.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOne.jsp
				successView.forward(req, res);
		
		}
		
		/***********************************************************************************/
		
		if ("showpicture".equals(action)) { // 來自select_page.jsp的請求
			req.setAttribute("show",0);
			List<TeacherVO> list=(List<TeacherVO>) req.getAttribute("list");
//			System.out.println(req.getAttribute("list"));
			String thrpic = null;
			Map<Integer, String> thrPicMap = new HashMap<Integer, String>()  ;
			for (TeacherVO VO: list) {
				Integer thrid = VO.getThrid(); 
				try {
				thrpic = Base64.getEncoder().encodeToString(VO.getThrPic());	
				
				} catch (NullPointerException e) {
					try {
						ServletContext servletContext = req.getServletContext();
						String filepath = servletContext.getRealPath("/picture/nopicture.jpg");
						File file=new File(filepath);
						
						FileInputStream fis = new FileInputStream(file);
						byte[] b = new byte[fis.available()];
						fis.read(b);
						fis.close();
						thrpic = Base64.getEncoder().encodeToString(b);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}	
				}
				thrPicMap.put(thrid, thrpic);
			}
			req.setAttribute("thrPicMap",thrPicMap);
			String url = "/back-end/teacher/listAllTeacher.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 listAll.jsp
			successView.forward(req, res);			
		}
		
		/***********************************************************************************/
		
		if ("update".equals(action)) { // 來自listAll.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
		
				/***************************1.接收請求參數 - 輸入格式的錯誤處理**********************/
				Integer thrid = Integer.parseInt(req.getParameter("thrid"));
				
				//修改前的版本
				TeacherService teacherSvc = new TeacherService();
				TeacherVO teacherVO_old = teacherSvc.getOneTeacher(thrid);
				

				String thrName = req.getParameter("thrName");
				String thrNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
				if (thrName == null || thrName.trim().length() == 0) {
					errorMsgs.add("教師姓名: 請勿空白");
				} else if(!thrName.trim().matches(thrNameReg)) { 
					errorMsgs.add("教師姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
	            }
				String thrGender = req.getParameter("thrGender").trim();
				if (thrGender == null || thrGender.trim().length() == 0) {
					errorMsgs.add("性別請勿空白");
				}	
				
				String thrPhone = req.getParameter("thrPhone").trim();
				String thrPhoneReg = "^[(0-9]{10}$";
				if (thrPhone == null || thrPhone.trim().length() == 0) {
					errorMsgs.add("教師電話: 請勿空白");
				} else if(!thrPhone.trim().matches(thrPhoneReg)) {
					errorMsgs.add("教師電話: 只能是數字,且長度必需在10個數字");
	            }
				
				String thrEmail = req.getParameter("thrEmail").trim();
				if (thrEmail == null || thrEmail.trim().length() == 0) {
					errorMsgs.add("教師信箱請勿空白");
				}	
				
				Integer thrStatus = Integer.valueOf(req.getParameter("thrStatus").trim());
				
				String thrIntroduction = req.getParameter("thrIntroduction");
				
				Integer thrComment = teacherVO_old.getThrComment();
				Integer thrCmnumber= teacherVO_old.getThrCmnumber();
				
				byte[] thrPic = null;
				//io
				Part part = req.getPart("picio");
				String filename =  part.getSubmittedFileName();
				
				
				//判斷檔案格式
				String filenameReg = ".+(.JPEG|.jpeg|.JPG|.jpg)$";
				if (filename == null || filename.trim().length() == 0) {
//					errorMsgs.add("教師圖片: 請勿空白");
				} else if(!filename.trim().matches(filenameReg)) { 
					errorMsgs.add("圖片: 只能是jpg或jpge格式");
				}else {
					//io讀取
//					InputStream in = part.getInputStream();
//					thrPic = new byte[in.available()];
//					in.read(thrPic);
//					in.close();
				}
				
				
//				base64
				String thrpic = req.getParameter("thrPic");
				if (thrpic.length()==0) {
					thrPic = teacherVO_old.getThrPic();
	 			}else {
	 				if (thrpic.startsWith("data:image/jpeg;base64")) {
	 					String thrpicURL = thrpic.substring(23);
	 					thrPic = Base64.getDecoder().decode(thrpicURL);
	 				}else if (thrpic.startsWith("data:image/jpg;base64")){
						String thrpicURL = thrpic.substring(22);
	 					thrPic = Base64.getDecoder().decode(thrpicURL); 					
	 				}
	 				
	 			}
//				
//				存錯誤值返回
//				req.setAttribute("thrpic", thrpic);
				
//				req.setAttribute("picio", part);
				

				TeacherVO teacherVO = new TeacherVO();
				teacherVO.setThrName(thrName);
				teacherVO.setThrGender(thrGender);
				teacherVO.setThrPhone(thrPhone);
				teacherVO.setThrEmail(thrEmail);
				teacherVO.setThrStatus(thrStatus);
				teacherVO.setThrIntroduction(thrIntroduction);
				teacherVO.setThrComment(thrComment);
				teacherVO.setThrCmnumber(thrCmnumber);
				teacherVO.setThrPic(thrPic);

				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("teacherVO", teacherVO); // 含有輸入格式錯誤的VO物件,也存入req
					String url = "listAllTeacher.jsp";
					RequestDispatcher failureView = req.getRequestDispatcher(url);
					failureView.forward(req, res);
					return; //程式中斷
				}
				
				/***************************2.開始修改資料*****************************************/
//				TeacherService teacherSvc = new TeacherService();
				teacherVO = teacherSvc.updateTeacher(thrName,thrGender,thrPhone,thrEmail,
				thrStatus,thrIntroduction,thrComment,thrCmnumber,thrPic,thrid);
				
				/***************************3.修改完成,準備轉交(Send the Success view)*************/
				req.setAttribute("teacherVO", teacherVO); // 資料庫update成功後,正確的的VO物件,存入req
				String url = "listAllTeacher.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOne.jsp
				successView.forward(req, res);
		}
		
		/***********************************************************************************/
		
        if ("insert".equals(action)) { // 來自add.jsp的請求  
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
		

			String thrName = req.getParameter("thrName");
			String thrNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (thrName == null || thrName.trim().length() == 0) {
				errorMsgs.add("教師姓名: 請勿空白");
			} else if(!thrName.trim().matches(thrNameReg)) { 
				errorMsgs.add("教師姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            }
			String thrGender = req.getParameter("thrGender").trim();
			if (thrGender == null || thrGender.trim().length() == 0) {
				errorMsgs.add("性別請勿空白");
			}	
			
			String thrPhone = req.getParameter("thrPhone").trim();
			String thrPhoneReg = "^[(0-9]{10}$";
			if (thrPhone == null || thrPhone.trim().length() == 0) {
				errorMsgs.add("教師電話: 請勿空白");
			} else if(!thrPhone.trim().matches(thrPhoneReg)) { 
				errorMsgs.add("教師電話: 只能是數字,且長度必需在10個數字");
            }
			
			String thrEmail = req.getParameter("thrEmail").trim();
			if (thrEmail == null || thrEmail.trim().length() == 0) {
				errorMsgs.add("教師信箱請勿空白");
			}	
			
			Integer thrStatus = Integer.valueOf(req.getParameter("thrStatus").trim());
			
			String thrIntroduction = req.getParameter("thrIntroduction");
			
			Integer thrComment = null;
			Integer thrCmnumber= null;
			
			byte[] thrPic = null;
			
			
			Part part = req.getPart("picio");
			String filename =  part.getSubmittedFileName();
			
			//判斷檔案格式
			String filenameReg = ".+(.JPEG|.jpeg|.JPG|.jpg)$";
			if (filename == null || filename.trim().length() == 0) {
//				errorMsgs.add("教師圖片: 請勿空白");
			} else if(!filename.trim().matches(filenameReg)) { 
				errorMsgs.add("圖片: 只能是jpg或jpge格式");
			}else {
				//io讀取
//				InputStream in = part.getInputStream();
//				thrPic = new byte[in.available()];
//				in.read(thrPic);
//				in.close();
			}
				
			//base64
			String thrpic = req.getParameter("thrPic");
			if (thrpic.length()==0) {
 			}else {
 				if (thrpic.startsWith("data:image/jpeg;base64")) {
 					String thrpicURL = thrpic.substring(23);
 					thrPic = Base64.getDecoder().decode(thrpicURL);
 				}else if (thrpic.startsWith("data:image/jpg;base64")){
					String thrpicURL = thrpic.substring(22);
 					thrPic = Base64.getDecoder().decode(thrpicURL); 					
 				}
 				
 			}
			
//			存錯誤值返回
//			req.setAttribute("thrpic", thrpic);
			
//			req.setAttribute("picio", part);
			
			TeacherVO teacherVO = new TeacherVO();
			teacherVO.setThrName(thrName);
			teacherVO.setThrGender(thrGender);
			teacherVO.setThrPhone(thrPhone);
			teacherVO.setThrEmail(thrEmail);
			teacherVO.setThrStatus(thrStatus);
			teacherVO.setThrIntroduction(thrIntroduction);
			teacherVO.setThrComment(thrComment);
			teacherVO.setThrCmnumber(thrCmnumber);
			teacherVO.setThrPic(thrPic);

			// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					req.setAttribute("teacherVO", teacherVO); // 含有輸入格式錯誤的VO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("addTeacher.jsp");
				failureView.forward(req, res);
				return;
			}
				
			/***************************2.開始新增資料***************************************/
				TeacherService teacherSvc = new TeacherService();
				teacherVO = teacherSvc.addTeacher(thrName,thrGender,thrPhone,thrEmail,
						thrStatus,thrIntroduction,thrComment,thrCmnumber,thrPic);
				
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
				String url = "listAllTeacher.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAll.jsp
				successView.forward(req, res);				
		}		
        
        /***********************************************************************************/
		
		
		
		
		
		
		
	}	
}
