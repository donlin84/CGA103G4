package com.announcement.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.announcement.model.AnnouncementService;
import com.announcement.model.AnnouncementVO;
import com.emp.model.EmpService;
import com.emp.model.EmpVO;

@WebServlet("/back-end/announcement/AnnouncementServlet2")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class AnnouncementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				String str = req.getParameter("annid");
				if (str == null || (str.trim()).length() == 0) {
					errorMsgs.add("請輸入公告編號");
				}
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}
				Integer annid = null;
				try {
					annid = new Integer(str);
				} catch (Exception e) {
					errorMsgs.add("公告編號格式不正確");
				}
				// Send the use back to the form, if there were errors
				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 2.開始查詢資料 *****************************************/
				AnnouncementService annSvc = new AnnouncementService();
				AnnouncementVO announcementVO = annSvc.getOneAnnouncement(annid);
				if (announcementVO == null) {
					errorMsgs.add("查無資料");
				}

				if (!errorMsgs.isEmpty()) {
					RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
					failureView.forward(req, res);
					return;// 程式中斷
				}

				/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
				req.setAttribute("announcementVO", announcementVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/announcement/listOneAnnouncement.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url);
				successView.forward(req, res);

			} catch (Exception e) {
				errorMsgs.add("無法取得資料:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/select_page.jsp");
				failureView.forward(req, res);
			}

		}

		if ("getOne_For_Update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.接收請求參數 ****************************************/
				Integer annid = new Integer(req.getParameter("annid"));

				/*************************** 2.開始查詢資料 ****************************************/
				AnnouncementService annSvc = new AnnouncementService();
				AnnouncementVO announcementVO = annSvc.getOneAnnouncement(annid);

				/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
				req.setAttribute("announcementVO", announcementVO); // 資料庫取出的empVO物件,存入req
				String url = "/back-end/announcement/updateAnnouncement.jsp";
				RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交update_emp_input.jsp
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 ************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料取出時失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}

		if ("update".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
				Integer annid = new Integer(req.getParameter("annid").trim());

				String annContent = req.getParameter("annContent");
				if (annContent == null || annContent.trim().length() == 0) {
					errorMsgs.add("請輸入公告內容");
				}

				byte[] annPic = null;
				try {
					annPic = req.getPart("annPic").getInputStream().readAllBytes();
				} catch (Exception e) {
					errorMsgs.add("請上傳正確格式檔案");
					System.out.println(annPic);
				}

				Integer annStatus = null;
				try {
					annStatus = Integer.valueOf(req.getParameter("annStatus").trim());
				} catch (NumberFormatException e) {
					annStatus = 0;
					errorMsgs.add("請填寫公告狀態.");
				}

				java.sql.Date annUpdate = null;
				try {
					annUpdate = java.sql.Date.valueOf(req.getParameter("annUpdate").trim());
				} catch (Exception e) {
					annUpdate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入更新時間");
				}
				java.sql.Date annTime = null;
				try {
					annTime = java.sql.Date.valueOf(req.getParameter("annTime").trim());
				} catch (Exception e) {
					annTime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入公告時間");
				}

				Integer empid = new Integer(req.getParameter("empid").trim());

				AnnouncementVO announcementVO = new AnnouncementVO();
				announcementVO.setAnnid(annid);
				announcementVO.setAnnContent(annContent);
				announcementVO.setAnnPic(annPic);
				announcementVO.setAnnStatus(annStatus);
				announcementVO.setAnnUpdate(annUpdate);
				announcementVO.setAnnTime(annTime);
				EmpVO empVO = new EmpVO();
				empVO.setEmpid(empid);
				announcementVO.setEmpVO(empVO);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("announcementVO", announcementVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/announcement/updateAnnouncement.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始修改資料 *****************************************/
				AnnouncementService annSvc = new AnnouncementService();
				announcementVO = annSvc.updateAnnouncement(annid, annContent, annPic, annStatus, annUpdate, annTime,
						empid);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				EmpService empSvc = new EmpService();
				if (requestURL.equals("/back-end/announcement/listAnnouncementByEmpid.jsp")
						|| requestURL.equals("/back-end/announcement/listAllAnnouncement.jsp"))
					req.setAttribute("listAnnouncementByEmpid", empSvc.getAnnouncementByEmpid(empid)); // 資料庫取出的list物件,存入request

				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/announcement/updateAnnouncement.jsp");
				failureView.forward(req, res);
			}
		}

		if ("insert".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/

				String annContent = req.getParameter("annContent");
				if (annContent == null || annContent.trim().length() == 0) {
					errorMsgs.add("請輸入公告內容");
				}

				byte[] annPic = null;
				try {
					annPic = req.getPart("annPic").getInputStream().readAllBytes();
				} catch (Exception e) {
					errorMsgs.add("請上傳正確格式檔案");
					System.out.println(annPic);
				}

				Integer annStatus = null;
				try {
					annStatus = Integer.valueOf(req.getParameter("annStatus").trim());
				} catch (NumberFormatException e) {
					annStatus = 0;
					errorMsgs.add("請填寫公告狀態.");
				}

				java.sql.Date annUpdate = null;
				try {
					annUpdate = java.sql.Date.valueOf(req.getParameter("annUpdate").trim());
				} catch (Exception e) {
					annUpdate = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入更新時間");
				}
				java.sql.Date annTime = null;
				try {
					annTime = java.sql.Date.valueOf(req.getParameter("annTime").trim());
				} catch (Exception e) {
					annTime = new java.sql.Date(System.currentTimeMillis());
					errorMsgs.add("請輸入公告時間");
				}

				Integer empid = new Integer(req.getParameter("empid").trim());

				AnnouncementVO announcementVO = new AnnouncementVO();

				announcementVO.setAnnContent(annContent);
				announcementVO.setAnnPic(annPic);
				announcementVO.setAnnStatus(annStatus);
				announcementVO.setAnnUpdate(annUpdate);
				announcementVO.setAnnTime(annTime);
				EmpVO empVO = new EmpVO();
				empVO.setEmpid(empid);
				announcementVO.setEmpVO(empVO);

				if (!errorMsgs.isEmpty()) {
					req.setAttribute("announcementVO", announcementVO); // 含有輸入格式錯誤的empVO物件,也存入req
					RequestDispatcher failureView = req
							.getRequestDispatcher("/back-end/announcement/addAnnouncement.jsp");
					failureView.forward(req, res);
					return; // 程式中斷
				}
				/*************************** 2.開始修改資料 *****************************************/
				AnnouncementService annSvc = new AnnouncementService();
				announcementVO = annSvc.addAnnouncement(annContent, annPic, annStatus, annUpdate, annTime, empid);

				/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
				EmpService empSvc = new EmpService();
				if (requestURL.equals("/back-end/announcement/listAnnouncementByEmpid.jsp")
						|| requestURL.equals("/back-end/announcement/listAllAnnouncement.jsp"))
					req.setAttribute("listAnnouncementByEmpid", empSvc.getAnnouncementByEmpid(empid)); // 資料庫取出的list物件,存入request

				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交回送出修改的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 *************************************/
			} catch (Exception e) {
				errorMsgs.add("修改資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher("/announcement/updateAnnouncement.jsp");
				failureView.forward(req, res);
			}

		}

		if ("delete".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			String requestURL = req.getParameter("requestURL");

			try {
				/*************************** 1.接收請求參數 ***************************************/
				Integer annid = new Integer(req.getParameter("annid"));

				/*************************** 2.開始刪除資料 ***************************************/
				AnnouncementService annSvc = new AnnouncementService();
				AnnouncementVO announcementVO = annSvc.getOneAnnouncement(annid);
				annSvc.deleteAnnouncement(annid);

				/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
				EmpService empSvc = new EmpService();
				if (requestURL.equals("/back-end/emp/listAnnouncementByEmp.jsp")
						|| requestURL.equals("/emp/listAllEmp.jsp"))
					req.setAttribute("listAnnouncementByEmp",empSvc.getAnnouncementByEmpid(announcementVO.getEmpVO().getEmpid())); // 資料庫取出的list物件,存入request

				String url = requestURL;
				RequestDispatcher successView = req.getRequestDispatcher(url); // 刪除成功後,轉交回送出刪除的來源網頁
				successView.forward(req, res);

				/*************************** 其他可能的錯誤處理 **********************************/
			} catch (Exception e) {
				errorMsgs.add("刪除資料失敗:" + e.getMessage());
				RequestDispatcher failureView = req.getRequestDispatcher(requestURL);
				failureView.forward(req, res);
			}
		}
	}
}
