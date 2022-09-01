package com.chef.controller;

import java.io.*;

import java.util.*;

import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import com.chef.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 500 * 1024 * 1024, maxRequestSize = 5 * 500 * 1024
		* 1024) // 上傳三要素之二
public class ChefServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String saveDirectory = "/images_uploaded"; // 上傳檔案的目的地目錄;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

//============================================================================
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("chefid");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入私廚編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/chef/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer chefid = null;
			try {
				chefid = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("私廚編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/chef/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			ChefService chefSvc = new ChefService();
			ChefVO chefVO = chefSvc.getOneChef(chefid);
			if (chefVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/chef/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("chefVO", chefVO); // 資料庫取出的chefVO物件,存入req

			String license = null;
			try {
				license = Base64.getEncoder().encodeToString(chefVO.getLicense());
			} catch (NullPointerException e) {
				ServletContext servletContext = req.getServletContext();
				String fileRealPath = servletContext.getRealPath("/back-end/chef/images/no.png");
				byte[] fileRealPathtoByte = getPictureByteArray(fileRealPath);
				license = Base64.getEncoder().encodeToString(fileRealPathtoByte);
			}
			req.setAttribute("license", license);

			String idCard = null;
			try {
				idCard = Base64.getEncoder().encodeToString(chefVO.getIdCard());
			} catch (NullPointerException e) {
				ServletContext servletContext = req.getServletContext();
				String fileRealPath = servletContext.getRealPath("/back-end/chef/images/no.png");
				byte[] fileRealPathtoByte = getPictureByteArray(fileRealPath);
				idCard = Base64.getEncoder().encodeToString(fileRealPathtoByte);
			}
			req.setAttribute("idCard", idCard);

			String idCardBack = null;
			try {
				idCardBack = Base64.getEncoder().encodeToString(chefVO.getIdCardBack());
			} catch (NullPointerException e) {
				ServletContext servletContext = req.getServletContext();
				String fileRealPath = servletContext.getRealPath("/back-end/chef/images/no.png");
				byte[] fileRealPathtoByte = getPictureByteArray(fileRealPath);
				idCardBack = Base64.getEncoder().encodeToString(fileRealPathtoByte);
			}
			req.setAttribute("idCardBack", idCardBack);

			String chefPhoto = null;
			try {
				chefPhoto = Base64.getEncoder().encodeToString(chefVO.getChefPhoto());
			} catch (NullPointerException e) {
				ServletContext servletContext = req.getServletContext();
				String fileRealPath = servletContext.getRealPath("/back-end/chef/images/no.png");
				byte[] fileRealPathtoByte = getPictureByteArray(fileRealPath);
				chefPhoto = Base64.getEncoder().encodeToString(fileRealPathtoByte);
			}
			req.setAttribute("chefPhoto", chefPhoto);

			String url = "/back-end/chef/listOneChef.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneChef.jsp
			successView.forward(req, res);
		}
//============================================================================
		if ("getOne_For_Update".equals(action)) { // 來自listAllChef.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer chefid = Integer.valueOf(req.getParameter("chefid"));

			/*************************** 2.開始查詢資料 ****************************************/
			ChefService chefSvc = new ChefService();
			ChefVO chefVO = chefSvc.getOneChef(chefid);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("chefVO", chefVO); // 資料庫取出的chefVO物件,存入req

			String url = "/back-end/chef/update_chef_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_chef_input.jsp
			successView.forward(req, res);
		}
//============================================================================
		if ("update".equals(action)) { // 來自update_chef_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer chefid = Integer.valueOf(req.getParameter("chefid").trim());

			String chefName = req.getParameter("chefName");
			String chefNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (chefName == null || chefName.trim().length() == 0) {
				errorMsgs.add("會員姓名: 請勿空白");
			} else if (!chefName.trim().matches(chefNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("會員姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			String chefNickname = req.getParameter("chefNickname");
			String chefNicknameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (!chefNickname.trim().matches(chefNicknameReg)) {
				errorMsgs.add("暱稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			String chefAccount = req.getParameter("chefAccount").trim();
//			if (chefAccount == null || chefAccount.trim().length() == 0) {
//				errorMsgs.add("帳號請勿空白");
//			}

			String chefPassword = req.getParameter("chefPassword").trim();
			if (chefPassword == null || chefPassword.trim().length() == 0) {
				errorMsgs.add("密碼請勿空白");
			}

			Integer chefStatus = null;
			try {
				chefStatus = Integer.valueOf(req.getParameter("chefStatus").trim());
			} catch (NumberFormatException e) {
				chefStatus = 0;
				errorMsgs.add("狀態請填數字.");
			}

			Integer chefPrice = null;
			try {
				chefPrice = Integer.valueOf(req.getParameter("chefPrice").trim());
			} catch (NumberFormatException e) {
				chefStatus = 0;
				errorMsgs.add("價格請填數字.");
			}

			String chefIntroduction = req.getParameter("chefIntroduction");

			req.setCharacterEncoding("UTF-8"); // 處理中文檔名
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
//			System.out.println("ContentType="+req.getContentType()); // 測試用

			String realPath = getServletContext().getRealPath(saveDirectory);
//			System.out.println("realPath="+realPath); // 測試用
			File fsaveDirectory = new File(realPath);
			if (!fsaveDirectory.exists())
				fsaveDirectory.mkdirs(); // 於 ContextPath 之下,自動建立目地目錄

			Collection<Part> parts = req.getParts(); // Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理 //上傳三要素之三

			for (Part part : parts) {
				String filename = part.getSubmittedFileName();
				if (filename != null && filename.length() != 0 && part.getContentType() != null) {
					File f = new File(fsaveDirectory, filename);
					// 利用File物件,寫入目地目錄,上傳成功
					part.write(f.toString());

				}
			}


			Part part = req.getPart("license");
			InputStream in = part.getInputStream();
			byte[] license = new byte[in.available()];
			in.read(license);
			in.close();
			ChefService chefSvc = new ChefService();
			ChefVO chefVOld = chefSvc.getOneChef(chefid);
			if (license.length == 0) {
				license = chefVOld.getLicense();
			}
//			System.out.println("req.getPart(\"license\") = "+req.getPart("license"));
//			System.out.println("in = "+in);
//			System.out.println("license.length = "+license.length);
//			System.out.println("chefVOOld = "+chefVOld);

			Part part1 = req.getPart("idCard");
			InputStream in1 = part1.getInputStream();
			byte[] idCard = new byte[in1.available()];
			in1.read(idCard);
			in1.close();
			ChefVO chefVO1ld = chefSvc.getOneChef(chefid);
			if (idCard.length == 0) {
				idCard = chefVO1ld.getIdCard();
			}

			Part part2 = req.getPart("idCardBack");
			InputStream in2 = part2.getInputStream();
			byte[] idCardBack = new byte[in2.available()];
			in2.read(idCardBack);
			in2.close();
			ChefVO chefVO2ld = chefSvc.getOneChef(chefid);
			if (idCardBack.length == 0) {
				idCardBack = chefVO2ld.getIdCardBack();
			}

			Part part3 = req.getPart("chefPhoto");
			InputStream in3 = part3.getInputStream();
			byte[] chefPhoto = new byte[in3.available()];
			in3.read(chefPhoto);
			in3.close();
			ChefVO chefVO3ld = chefSvc.getOneChef(chefid);
			if (chefPhoto.length == 0) {
				chefPhoto = chefVO3ld.getChefPhoto();
			}


			ChefVO chefVO = new ChefVO();
			chefVO.setChefid(chefid);
			chefVO.setChefName(chefName);
			chefVO.setChefNickname(chefNickname);
			chefVO.setChefAccount(chefAccount);
			chefVO.setChefPassword(chefPassword);
			chefVO.setChefStatus(chefStatus);
			chefVO.setChefPrice(chefPrice);
			chefVO.setLicense(license);
			chefVO.setIdCard(idCard);
			chefVO.setIdCardBack(idCardBack);
			chefVO.setChefPhoto(chefPhoto);
			chefVO.setChefIntroduction(chefIntroduction);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("chefVO", chefVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/chef/update_chef_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
//			ChefService chefSvc = new ChefService();
			chefVO = chefSvc.updateChef(chefid, chefName, chefNickname, chefAccount, chefPassword, chefStatus,
					chefPrice, license, idCard, idCardBack, chefPhoto, chefIntroduction);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("chefVO", chefVO); // 資料庫update成功後,正確的的empVO物件,存入req

			String url = "/back-end/chef/listOneChef.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneMember.jsp
			successView.forward(req, res);
		}
//============================================================================
		if ("insert".equals(action)) { // 來自addChef.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String chefAccount = req.getParameter("chefAccount").trim();
			if (chefAccount == null || chefAccount.trim().length() == 0) {
				errorMsgs.add("帳號請勿空白");
			}

			String chefPassword = req.getParameter("chefPassword").trim();
			if (chefPassword == null || chefPassword.trim().length() == 0) {
				errorMsgs.add("密碼請勿空白");
			}

			String chefName = req.getParameter("chefName");
			String chefNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (chefName == null || chefName.trim().length() == 0) {
				errorMsgs.add("私廚姓名: 請勿空白");
			} else if (!chefName.trim().matches(chefNameReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("私廚姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}

			String chefNickname = req.getParameter("chefNickname");
//				String chefNicknameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
//				if (chefNickname == null || chefNickname.trim().length() == 0) {
//					errorMsgs.add("私廚姓名: 請勿空白");
//				} else if(!chefNickname.trim().matches(chefNicknameReg)) { //以下練習正則(規)表示式(regular-expression)
//					errorMsgs.add("私廚姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
//				}

			Integer chefPrice = null;
			try {
				chefPrice = Integer.valueOf(req.getParameter("chefPrice").trim());
			} catch (NumberFormatException e) {
				chefPrice = 0;
				errorMsgs.add("價格請填數字.");
			}

			String chefIntroduction = req.getParameter("chefIntroduction").trim();
			if (chefIntroduction == null || chefIntroduction.trim().length() == 0) {
				errorMsgs.add("簡介請勿空白");
			}

			req.setCharacterEncoding("UTF-8"); // 處理中文檔名
			res.setContentType("text/html; charset=UTF-8");
			PrintWriter out = res.getWriter();
//			System.out.println("ContentType="+req.getContentType()); // 測試用

			String realPath = getServletContext().getRealPath(saveDirectory);
//			System.out.println("realPath="+realPath); // 測試用
			File fsaveDirectory = new File(realPath);
			if (!fsaveDirectory.exists())
				fsaveDirectory.mkdirs(); // 於 ContextPath 之下,自動建立目地目錄

			Collection<Part> parts = req.getParts(); // Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理 //上傳三要素之三

			for (Part part : parts) {
				String filename = part.getSubmittedFileName();
//				System.out.println("filename="+filename);
				if (filename != null && filename.length() != 0 && part.getContentType() != null) {
					File f = new File(fsaveDirectory, filename);
					// 利用File物件,寫入目地目錄,上傳成功
					part.write(f.toString());
				}
			}

			byte[] license = null;
			if (req.getPart("license").getSubmittedFileName().isEmpty()) {
				errorMsgs.add("請上傳廚師執照");
			} else {
				license = getPictureByteArray("C:\\CGA103_WebApp\\eclipse_WTP_workspace1\\.metadata\\."
						+ "plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\CGA103G4\\images_uploaded\\"
						+ req.getPart("license").getSubmittedFileName());
			}

			byte[] idCard = null;
			if (req.getPart("idCard").getSubmittedFileName().isEmpty()) {
				errorMsgs.add("請上傳身分證(正)");
			} else {
				idCard = getPictureByteArray("C:\\CGA103_WebApp\\eclipse_WTP_workspace1\\.metadata\\."
						+ "plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\CGA103G4\\images_uploaded\\"
						+ req.getPart("idCard").getSubmittedFileName());
			}

			byte[] idCardBack = null;
			if (req.getPart("idCardBack").getSubmittedFileName().isEmpty()) {
				errorMsgs.add("請上傳身分證(反)");
			} else {
				idCardBack = getPictureByteArray("C:\\CGA103_WebApp\\eclipse_WTP_workspace1\\.metadata\\."
						+ "plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\CGA103G4\\images_uploaded\\"
						+ req.getPart("idCardBack").getSubmittedFileName());
			}

			byte[] chefPhoto = null;
			if (req.getPart("chefPhoto").getSubmittedFileName().isEmpty()) {
				errorMsgs.add("請上傳個人照");
			} else {
				chefPhoto = getPictureByteArray("C:\\CGA103_WebApp\\eclipse_WTP_workspace1\\.metadata\\."
						+ "plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\CGA103G4\\images_uploaded\\"
						+ req.getPart("chefPhoto").getSubmittedFileName());
			}

			ChefVO chefVO = new ChefVO();
			chefVO.setChefAccount(chefAccount);
			chefVO.setChefPassword(chefPassword);
			chefVO.setChefName(chefName);
			chefVO.setChefNickname(chefNickname);
			chefVO.setChefPrice(chefPrice);
			chefVO.setLicense(license);
			chefVO.setIdCard(idCard);
			chefVO.setIdCardBack(idCardBack);
			chefVO.setChefPhoto(chefPhoto);
			chefVO.setChefIntroduction(chefIntroduction);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("chefVO", chefVO); // 含有輸入格式錯誤的chefVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/chef/addChef.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			ChefService chefSvc = new ChefService();
			chefVO = chefSvc.addChef(chefAccount, chefPassword, chefName, chefNickname, chefPrice, license, idCard,
					idCardBack, chefPhoto, chefIntroduction);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/

			String url = "/back-end/chef/listAllChef.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllMember.jsp
			successView.forward(req, res);
		}

//		
	}

	private byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}

	public String getFileNameFromPart(Part part) {
		String header = part.getHeader("content-disposition");
//		System.out.println("header=" + header); // 測試用
		String filename = new File(header.substring(header.lastIndexOf("=") + 2, header.length() - 1)).getName();
//		System.out.println("filename=" + filename); // 測試用
		if (filename.length() == 0) {
			return null;
		}
		return filename;
	}

}
