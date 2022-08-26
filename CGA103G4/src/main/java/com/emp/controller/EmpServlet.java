package com.emp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.emp.model.EmpDAO;
import com.emp.model.EmpService;
import com.emp.model.EmpVO;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class EmpServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		/* 查詢全部 */
		if ("getAll".equals(action)) {
			/*************************** 開始查詢資料 ****************************************/
			EmpDAO dao = new EmpDAO();
			List<EmpVO> list = dao.getAll();

			/**************************** 查詢完成,準備轉交(Send the Success view) *************/
			HttpSession session = req.getSession();
			session.setAttribute("list", list);// 資料庫取出的list物件,存入session
			// Send the Success view
			String url = "/back-end/emp/listAllEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交listAllEmp2_getFromSession.jsp
			successView.forward(req, res);
			return;
		}

		/* 單一查詢 */
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求，action放後面防止null。

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("empid");
			if (str == null || (str.trim()).length() == 0) { // null，防止出現NULLPOINTEXCEPTION。
				errorMsgs.add("請輸入管理員編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer empid = null;
			try {
				empid = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("管理員編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			EmpDAO dao = new EmpDAO();
			EmpVO empVO = dao.findByPrimaryKey(empid);
			if (empVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("empVO", empVO); // 資料庫取出的empVO物件,存入req
			String empPicture ="";
			try {
				System.out.println(empVO.getEmpPicture());
			empPicture = Base64.getEncoder().encodeToString(empVO.getEmpPicture());
			
			}catch(NullPointerException ne){
				empPicture ="";
				ServletContext servletContext = req.getServletContext();
				String filepath = servletContext.getRealPath("/back-end/emp/images/noImage.jpg");
				File file=new File(filepath);
				FileInputStream fis = new FileInputStream(file);
				byte[] b = new byte[fis.available()];
				fis.read(b);
				fis.close();
				empPicture = Base64.getEncoder().encodeToString(b);	
			}
			req.setAttribute("empPicture", empPicture);
			String url = "/back-end/emp/listOneEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res); // forward to view
		}

		/* 新增管理員 */
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			// 驗證姓名
			String ename = req.getParameter("empName");
			String enameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (ename == null || ename.trim().length() == 0) {
				errorMsgs.add("員工姓名: 請勿空白");
			} else if (!ename.trim().matches(enameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			// 圖片
			Part part = req.getPart("empPicture");
			InputStream in = part.getInputStream();
			byte[] picture = new byte[in.available()];
			in.read(picture);
			in.close();

			// 驗證電話
			String phone = req.getParameter("empPhone").trim();
			String phoneReg = "[0-9]{8,10}$";
			if (phone == null || phone.trim().length() == 0) {
				errorMsgs.add("電話請勿空白");
			} else if (!phone.trim().matches(phoneReg)) {
				errorMsgs.add("電話只能是數字!且長度必須介於8-10之間");
			}

			// 帳號
			String account = req.getParameter("empAccount");

			// 驗證密碼
			String password = req.getParameter("empPassword");
			String passwordReg = "^[(a-zA-Z0-9]{5,20}$";
			if (password == null || password.trim().length() == 0) {
				errorMsgs.add("密碼請勿空白");
			} else if (!password.trim().matches(passwordReg)) {
				errorMsgs.add("密碼必須為英文字母、數字組成，且長度必須在");
			}

			// 管理員權限等級
			Integer level = Integer.valueOf(req.getParameter("empLevel"));

			// 驗證入職日期
			java.sql.Date hiredate = null;
			try {
				hiredate = java.sql.Date.valueOf(req.getParameter("empHiredate").trim());
			} catch (IllegalArgumentException e) {
				hiredate = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}

			EmpVO empVO = new EmpVO();
			empVO.setEmpName(ename);
			empVO.setEmpPhone(phone);
			empVO.setEmpPicture(picture);
			empVO.setEmpAccount(account);
			empVO.setEmpPassword(password);
			empVO.setEmpLevel(level);
			empVO.setEmpHiredate(hiredate);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("empVO", empVO); // 含有輸入格式錯誤的empVO物件,也存入req(保存輸入過的資料)
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/addEmp.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			EmpService empSvc = new EmpService();
			empVO = empSvc.addEmp(ename, phone, picture, account, password, level, hiredate); // alt+/ call方法

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/emp/listAllEmp.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}
	}

}
