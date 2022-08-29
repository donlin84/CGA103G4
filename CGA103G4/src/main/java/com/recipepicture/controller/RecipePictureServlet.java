package com.recipepicture.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;

import com.recipepicture.model.*;
@MultipartConfig
public class RecipePictureServlet extends HttpServlet {
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
			String str = req.getParameter("rePicid");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入食譜圖片編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/recipepicture/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer rePicid = null;
			try {
				rePicid = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("食譜圖片編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/recipepicture/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			RecipePictureService rePicsv = new RecipePictureService();
			RecipePictureVO recipePictureVO = rePicsv.getOne(rePicid);
			if (recipePictureVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/recipepicture/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("recipePictureVO", recipePictureVO); // 資料庫取出的empVO物件,存入req
			String url = "/recipepicture/listOneRecipePicture.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer rePicid = Integer.valueOf(req.getParameter("rePicid"));

			/*************************** 2.開始查詢資料 ****************************************/
			RecipePictureService rePicsv = new RecipePictureService();
			RecipePictureVO recipePictureVO = rePicsv.getOne(rePicid);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("recipePictureVO", recipePictureVO); // 資料庫取出的empVO物件,存入req
			String url = "/recipepicture/update_recipePicture_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// ���\��� update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			RecipePictureService ordSvc = new RecipePictureService();
			Integer rePicid = Integer.valueOf(req.getParameter("rePicid"));

			Integer reid = null;
			String reidstr = req.getParameter("reid");
			
			try {
				if(reidstr == null) {
					errorMsgs.add("食譜編號請勿空白");
				} else {
					reid = Integer.valueOf(reidstr);
				}
			} catch (NumberFormatException e) {
				errorMsgs.add("食譜編號請填數字");
			}
			RecipePictureVO old = ordSvc.getOne(rePicid);
			
			Part part = req.getPart("rePic");
			
			InputStream in = part.getInputStream();
			byte[] buf = new byte[in.available()];
			in.read(buf);
			in.close();
			if(buf.length == 0) {
				buf = old.getRePic();
			}

			RecipePictureVO recipePictureVO = new RecipePictureVO();
			recipePictureVO.setRePicid(rePicid);
			recipePictureVO.setReid(reid);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("recipePictureVO", recipePictureVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/recipepicture/update_recipepicture_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			
			recipePictureVO = ordSvc.updateRecipePicture(reid, buf, rePicid);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("recipePictureVO", recipePictureVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/recipepicture/listOneRecipePicture.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			
			Integer reid = null;
			String reidstr = req.getParameter("reid");
			
			try {
				if(reidstr == null) {
					errorMsgs.add("食譜編號請勿空白");
				} else {
					reid = Integer.valueOf(reidstr);
				}
			} catch (NumberFormatException e) {
				errorMsgs.add("食譜編號請填數字");
			}
			
			Part part = req.getPart("rePic");
			
			InputStream in = part.getInputStream();
			byte[] buf = new byte[in.available()];
			in.read(buf);
			in.close();

			if(buf.length==0) {
				buf=null;
			}			

			RecipePictureVO recipePictureVO = new RecipePictureVO();
			recipePictureVO.setReid(reid);
			recipePictureVO.setRePic(buf);
//			recipePictureVO.setMemCpid(memCpid);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("recipePictureVO", recipePictureVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/recipepicture/addRecipePicture.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始新增資料 ***************************************/
			RecipePictureService ordSvc = new RecipePictureService();
			recipePictureVO = ordSvc.addRecipePicture(reid, buf);
			
//			EmpService empSvc = new EmpService();
//			empVO = empSvc.addEmp(ename, job, hiredate, sal, comm, deptno);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/recipepicture/listAllRecipePicture.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllEmp.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer rePicid = Integer.valueOf(req.getParameter("rePicid"));

			/*************************** 2.開始刪除資料 ***************************************/
			RecipePictureService rePicSvc = new RecipePictureService();
			rePicSvc.deleteRecipePicture(rePicid);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/recipepicture/listAllRecipePicture.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// �R�����\��,���^�e�X�R�����ӷ�����
			successView.forward(req, res);
		}
	}
}
