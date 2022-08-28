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

//			req.setCharacterEncoding("Big5"); // 處理中文檔名
//			res.setContentType("text/html; charset=Big5");
//			PrintWriter out = res.getWriter();
////			System.out.println("ContentType="+req.getContentType()); // 測試用
//
//			String realPath = getServletContext().getRealPath(saveDirectory);
////			System.out.println("realPath="+realPath); // 測試用
//			File fsaveDirectory = new File(realPath);
//			if (!fsaveDirectory.exists())
//				 fsaveDirectory.mkdirs(); // 於 ContextPath 之下,自動建立目地目錄
//
//			Collection<Part> parts = req.getParts(); // Servlet3.0新增了Part介面，讓我們方便的進行檔案上傳處理	//上傳三要素之三
//
//			for (Part part : parts) {
//
//				String filename = part.getSubmittedFileName();
//				if (filename!=null && filename.length()!=0 && part.getContentType()!=null) {
//
//					String name = part.getName();
//					String ContentType = part.getContentType();	//xx除了IE瀏覽器 其他無效
//					long size = part.getSize();
//					File f = new File(fsaveDirectory, filename);
//					System.out.println("test1");
//					System.out.println("name: " + name);
//					System.out.println("filename: " + filename);
//					System.out.println("ContentType: " + ContentType);	//xx
//					System.out.println("size: " + size);
//					System.out.println("File: " + f);
//					// 利用File物件,寫入目地目錄,上傳成功
//					part.write(f.toString());
//
//				}
//			}
//			System.out.println("test2");
			byte[] license2 = getPictureByteArray("C:\\CGA103_WebApp\\eclipse_WTP_workspace1\\.metadata\\."
					+ "plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\CGA103G4\\images_uploaded\\"
					+ "Tibame.png");
//					req.getPart("license").getSubmittedFileName());
			byte[] idCard2 = getPictureByteArray("C:\\CGA103_WebApp\\eclipse_WTP_workspace1\\.metadata\\."
					+ "plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\CGA103G4\\images_uploaded\\"
					+ "Tibame.png");
//					req.getPart("idCard").getSubmittedFileName());
			byte[] idCardBack2 = getPictureByteArray("C:\\CGA103_WebApp\\eclipse_WTP_workspace1\\.metadata\\."
					+ "plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\CGA103G4\\images_uploaded\\"
					+ "Tibame.png");
//					req.getPart("idCardBack").getSubmittedFileName());
			byte[] chefPhoto2 = getPictureByteArray("C:\\CGA103_WebApp\\eclipse_WTP_workspace1\\.metadata\\."
					+ "plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\CGA103G4\\images_uploaded\\"
					+ "Tibame.png");
//					req.getPart("chefPhoto").getSubmittedFileName());

			ChefVO chefVO = new ChefVO();
			chefVO.setChefid(chefid);
			chefVO.setChefName(chefName);
			chefVO.setChefNickname(chefNickname);
			chefVO.setChefAccount(chefAccount);
			chefVO.setChefPassword(chefPassword);
			chefVO.setChefStatus(chefStatus);
			chefVO.setChefPrice(chefPrice);
			chefVO.setLicense(license2);
			chefVO.setIdCard(idCard2);
			chefVO.setIdCardBack(idCardBack2);
			chefVO.setChefPhoto(chefPhoto2);
			chefVO.setChefIntroduction(chefIntroduction);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("chefVO", chefVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/chef/update_chef_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			ChefService chefSvc = new ChefService();
			chefVO = chefSvc.updateChef(chefid, chefName, chefNickname, chefAccount, chefPassword, chefStatus,
					chefPrice, license2, idCard2, idCardBack2, chefPhoto2, chefIntroduction);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("chefVO", chefVO); // 資料庫update成功後,正確的的empVO物件,存入req

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

			req.setCharacterEncoding("Big5"); // 處理中文檔名
			res.setContentType("text/html; charset=Big5");
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

//					String name = part.getName();
//					String ContentType = part.getContentType();	//xx除了IE瀏覽器 其他無效
//					long size = part.getSize();
					File f = new File(fsaveDirectory, filename);

//					System.out.println("name: " + name);
//					System.out.println("filename: " + filename);
//					System.out.println("ContentType: " + ContentType);	//xx
//					System.out.println("size: " + size);
//					System.out.println("File: " + f);
					// 利用File物件,寫入目地目錄,上傳成功
					part.write(f.toString());

				}
			}

			byte[] license = null;
			System.out.println(req.getParts().isEmpty());
			if (req.getPart("license").getSubmittedFileName() == null) {
				errorMsgs.add("XX請勿空白");
				license = getPictureByteArray("C:\\CGA103_WebApp\\eclipse_WTP_workspace1\\.metadata\\."
						+ "plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\CGA103G4\\images_uploaded\\"
						+ req.getPart("license").getSubmittedFileName());
						} else {
							license = getPictureByteArray("C:\\CGA103_WebApp\\eclipse_WTP_workspace1\\.metadata\\."
									+ "plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\CGA103G4\\back-end\\chef\\images\\no.png");
			}

			byte[] idCard = getPictureByteArray("C:\\CGA103_WebApp\\eclipse_WTP_workspace1\\.metadata\\."
					+ "plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\CGA103G4\\images_uploaded\\"
					+ req.getPart("idCard").getSubmittedFileName());
			byte[] idCardBack = getPictureByteArray("C:\\CGA103_WebApp\\eclipse_WTP_workspace1\\.metadata\\."
					+ "plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\CGA103G4\\images_uploaded\\"
					+ req.getPart("idCardBack").getSubmittedFileName());
			byte[] chefPhoto = getPictureByteArray("C:\\CGA103_WebApp\\eclipse_WTP_workspace1\\.metadata\\."
					+ "plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\CGA103G4\\images_uploaded\\"
					+ req.getPart("chefPhoto").getSubmittedFileName());

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
