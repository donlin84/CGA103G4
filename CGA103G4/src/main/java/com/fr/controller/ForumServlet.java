package com.fr.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.emp.model.EmpService;
import com.emp.model.EmpVO;
import com.fr.model.ForumService;
import com.fr.model.ForumVO;

@WebServlet("/back-end/forum/fr.do")
public class ForumServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		/* 新增討論版 */
		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			// 驗證名稱
			String frname = req.getParameter("frname");
			String frnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (frname == null || frname.trim().length() == 0) {
				errorMsgs.add("討論區名稱: 請勿空白");
			} else if (!frname.trim().matches(frnameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("討論區名稱: 只能是中、英文字母、數字和_, 且長度必需在2到10之間");
			}

			ForumVO frVO = new ForumVO();
			frVO.setFrName(frname);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("frVO", frVO); // 含有輸入格式錯誤的empVO物件,也存入req(保存輸入過的資料)
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/emp/addEmp.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			ForumService frsvc = new ForumService();
			frVO = frsvc.addfr(frname); // alt+/ call方法

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/forum/edit_forum.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		/* 管理員查詢修改 */
		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer frid = Integer.valueOf(req.getParameter("frid"));

			/*************************** 2.開始查詢資料 ****************************************/
			ForumService frSvc = new ForumService();
			ForumVO frVO = frSvc.getOnefr(frid);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("frVO", frVO); // 資料庫取出的empVO物件,存入req
			String url = "/back-end/forum/update.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

		/* 管理員修改 */
		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer frid = Integer.valueOf(req.getParameter("frid").trim());
			String frname = req.getParameter("frname");
			// 驗證名稱
			String frnameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (frname == null || frname.trim().length() == 0) {
				errorMsgs.add("討論區名稱: 請勿空白");
			} else if (!frname.trim().matches(frnameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("討論區名稱: 只能是中、英文字母、數字和_, 且長度必需在2到10之間");
			}

			ForumVO frVO = new ForumVO();
			frVO.setFrid(frid);
			frVO.setFrName(frname);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("frVO", frVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/forum/update.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			ForumService frsvc = new ForumService();
			frVO = frsvc.updatefr(frid, frname);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("frVO", frVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back-end/forum/edit_forum.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}
		/* 管理員修改 */
		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer frid = Integer.valueOf(req.getParameter("frid").trim());
			
			ForumVO frVO = new ForumVO();
			frVO.setFrid(frid);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("frVO", frVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/forum/update.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			ForumService frsvc = new ForumService();
			frsvc.deleteFr(frid);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			String url = "/back-end/forum/edit_forum.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}
	}
}
