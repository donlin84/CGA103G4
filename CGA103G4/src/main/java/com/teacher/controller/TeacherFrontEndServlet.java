package com.teacher.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.teacher.model.TeacherService;
import com.teacher.model.TeacherVO;

@WebServlet("/front-end/teacher/teacher.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class TeacherFrontEndServlet extends HttpServlet {

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
							.getRequestDispatcher("listOneTeacher.jsp");
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
							.getRequestDispatcher("listOneTeacher.jsp");
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
							.getRequestDispatcher("listOneTeacher.jsp");
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
						String filepath = servletContext.getRealPath("/images/picture_15/nopicture.jpg");
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
				String url = "/front-end/teacher/listOneTeacher.jsp";
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
						String filepath = servletContext.getRealPath("/images/picture_15/nopicture.jpg");
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
			String url = "/front-end/teacher/listAllTeacher.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 listAll.jsp
			successView.forward(req, res);			
		}
		
		/***********************************************************************************/
		if ("getsome_For_condiction".equals(action)) {
			String thrid = req.getParameter("thrid");
			String thrName = req.getParameter("thrName");
			String thrGender = req.getParameter("thrGender");
			String thrPhone = "";
			String thrEmail = "";
			String thrStatus = req.getParameter("thrStatus");
			String thrIntroduction = req.getParameter("thrIntroduction");
			String thrComment = req.getParameter("thrComment");
			String thrCmnumber = req.getParameter("thrCmnumber");
	
			System.out.println("接收資料");
			System.out.println(thrid);
			System.out.println(thrName);
			System.out.println(thrGender);
			System.out.println(thrStatus);
			System.out.println(thrIntroduction);
			System.out.println(thrComment);
			System.out.println(thrCmnumber);

			
			Map<String, String[]> map = new TreeMap<String, String[]>();
			
			map.put("thrid", new String[] {thrid});
			map.put("thrName", new String[] {thrName});
			map.put("thrGender", new String[] {thrGender});
			map.put("thrPhone", new String[] {thrPhone });
			map.put("thrEmail", new String[] { thrEmail});
			map.put("thrStatus", new String[] {thrStatus});
			map.put("thrIntroduction", new String[] { thrIntroduction });
			map.put("thrComment", new String[] { thrComment });
			map.put("thrCmnumber", new String[] {thrCmnumber });
			map.put("action", new String[] { action }); // 注意Map裡面會含有action的key
			
			TeacherService teacherSvc = new TeacherService();
			List<TeacherVO> list = teacherSvc.getAll(map);
			
			req.setAttribute("list", list);
			
//			System.out.println(req.getAttribute("list"));
			String thrpic = null;
			Map<Integer, String> thrPicMap = new HashMap<Integer, String>()  ;
			for (TeacherVO VO: list) {
				Integer picthrid = VO.getThrid(); 
				try {
				thrpic = Base64.getEncoder().encodeToString(VO.getThrPic());	
				
				} catch (NullPointerException e) {
					try {
						ServletContext servletContext = req.getServletContext();
						String filepath = servletContext.getRealPath("/images/picture_15/nopicture.jpg");
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
				thrPicMap.put(picthrid, thrpic);
			}
			req.setAttribute("thrPicMap",thrPicMap);
			String url = "/front-end/teacher/mutilistAllTeacher.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 listAll.jsp
			successView.forward(req, res);				
			
			
		}		

		
	}	
}