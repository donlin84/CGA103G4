package com.product.controller;

import java.io.*;
import java.time.LocalDateTime;

import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.product.model.ProductService;
import com.product.model.ProductVO;

import com.productPicture.model.ProductpicService;
import com.productPicture.model.ProductpicVO;
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ProductServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException{
		doPost(req,  res);	
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) 
	throws ServletException, IOException{


		
//=========================================== get one product===============================================	
	req.setCharacterEncoding("UTF-8");
	String action = req.getParameter("action");
	String action2 = req.getParameter("action2");
	String action3 = req.getParameter("action3");
	if("getOne_For_Display".equals(action)) {
		
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		String str = null;
		if (req.getAttribute("pdid")!=null) {
			str = (String) req.getAttribute("pdid");
		}else {
			str = req.getParameter("pdid");}
		
//		String str = req.getParameter("pdid");
		if(str == null ||(str.trim()).length() == 0){
			errorMsgs.add("請輸入商品編號");
		}
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/backend/productFindOne.jsp");
			failureView.forward(req, res);
			return;//程式中斷
		}	
		
		Integer pdid = null;
		try {
			pdid = Integer.valueOf(str);
		} catch (Exception e) {
			errorMsgs.add("編號格式不正確, 請輸入數字");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/backend/productShowAll.jsp");
			failureView.forward(req, res);
			return;//程式中斷
		}
		ProductService pdSvc = new ProductService();
		ProductVO productVO = pdSvc.getOneproduct(pdid);
		if (productVO == null) {
			errorMsgs.add("查無資料");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/backend/productShowAll.jsp");
			failureView.forward(req, res);
			return;//程式中斷
		}
		req.setAttribute("productVO", productVO); 
		String url = "/backend/productShowOne.jsp";
		
		RequestDispatcher successView = req.getRequestDispatcher(url); 
		successView.forward(req, res);
		
	}
	
//	===============================================================
	//單一查詢
//	System.out.println("123");
//	String action2 = (String) req.getAttribute("action");
//	if("getOne_For_Display".equals(action2)) {
//		
//		List<String> errorMsgs = new LinkedList<String>();
//		req.setAttribute("errorMsgs", errorMsgs);
//
//		String str = (String) req.getAttribute("pdid");
////		String str = req.getParameter("pdid");
//		if(str == null ||(str.trim()).length() == 0){
//			errorMsgs.add("請輸入商品編號");
//		}
//		if (!errorMsgs.isEmpty()) {
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/backend/productFindOne.jsp");
//			failureView.forward(req, res);
//			return;//程式中斷
//		}	
//		
//		Integer pdid = null;
//		try {
//			pdid = Integer.valueOf(str);
//		} catch (Exception e) {
//			errorMsgs.add("編號格式不正確");
//		}
//		// Send the use back to the form, if there were errors
//		if (!errorMsgs.isEmpty()) {
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/backend/productAdd.jsp");
//			failureView.forward(req, res);
//			return;//程式中斷
//		}
//		ProductService pdSvc = new ProductService();
//		ProductVO productVO = pdSvc.getOneproduct(pdid);
//		if (productVO == null) {
//			errorMsgs.add("查無資料");
//		}
//		// Send the use back to the form, if there were errors
//		if (!errorMsgs.isEmpty()) {
//			RequestDispatcher failureView = req
//					.getRequestDispatcher("/backend/productAdd.jsp");
//			failureView.forward(req, res);
//			return;//程式中斷
//		}
//		req.setAttribute("productVO", productVO); // 資料庫取出的empVO物件,存入req
//		String url = "/backend/productShowOne.jsp";
//		
//		RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
//		successView.forward(req, res);
//		System.out.println("321");
//	}
	
//===========================================insert==================================================	
	if ("insert".equals(action)) {   
		
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		
			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
		
		Integer pdsid = Integer.valueOf(req.getParameter("Pdsid"));
//		System.out.println(pdsid);   
		
		String pdName = null;

		pdName = req.getParameter(("PdName").trim());
		String pdNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (pdName == null || pdName.trim().length() == 0) {
				errorMsgs.add("商品名稱請勿空白");
			} else if(!pdName.trim().matches(pdNameReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            } 
		Integer pdPrice = null;
			try {
				pdPrice = Integer.valueOf(req.getParameter("PdPrice").trim());
			} catch (NumberFormatException e) {
				pdPrice = 0;
				errorMsgs.add("商品價格請填數字");
			}
//		System.out.println(pdPrice); 
		
		Integer pdDiscountPrice = null;
			try {
				pdDiscountPrice = Integer.valueOf(req.getParameter("PdDiscountPrice").trim());
				System.out.println("優惠價格為" + pdDiscountPrice);
			} catch (NumberFormatException e) {
				pdDiscountPrice = 0;
				errorMsgs.add("商品優惠價格請填數字");
			}
			
			
		String pdDescription = req.getParameter("PdDescription").trim();
			
//		System.out.println(pdDescription);
		
		Integer pdStatus = null;
		try {
			pdStatus = Integer.valueOf(req.getParameter("PdStatus").trim());
		} catch (NumberFormatException e) {
			pdStatus = 0;
			errorMsgs.add("請決定是否立刻上架");
		}
			
		
		LocalDateTime pdUpdate = LocalDateTime.now();
		
			ProductVO productVO = new ProductVO();
			productVO.setPdsid(pdsid);
			productVO.setPdName(pdName);
			productVO.setPdPrice(pdPrice);
			productVO.setPdDiscountPrice(pdDiscountPrice);
			productVO.setPdDescription(pdDescription);
			productVO.setPdStatus(pdStatus);
			productVO.setPdUpdate(pdUpdate);
			System.out.println(pdUpdate);
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/backend/productAdd.jsp");
				failureView.forward(req, res);
				return;
			}
			
			/***************************2.開始新增資料***************************************/
			ProductService pdSvc = new ProductService();
			productVO = pdSvc.addProduct(pdsid, pdName, pdPrice, pdDiscountPrice, pdDescription, pdStatus, pdUpdate);
			
			/***************************3.新增完成,準備轉交(Send the Success view)***********/
			
			
			String url = "/backend/productShowAll.jsp";
//			String url2 = "/backend/productShowOne.jsp";
//			System.out.println(req.getParameter("pdid"));
			req.setAttribute("pdid",req.getParameter("pdid"));
			req.setAttribute("action2", "getOne_For_Display");
			RequestDispatcher successView = req.getRequestDispatcher(url); 
			successView.forward(req, res);				
		}
	
	//=========================================== query before update ==================================================	
	System.out.println(action);
	if ("getOne_For_Update".equals(action)) {
		List<String>errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
//		step1
		
		Integer pdid = Integer.valueOf(req.getParameter("pdid"));
		System.out.println(pdid + "  step1");
//		step2
		ProductService pdSvc = new ProductService();
		ProductVO productVO = pdSvc.getOneproduct(pdid);
		
//		step3
		req.setAttribute("productVO", productVO);
		String url = "/backend/productModify.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req,res);
		
		//=========================================== update ==================================================		
	}

	
	if("update".equals(action)) {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		System.out.println(action);
	Integer pdid = Integer.valueOf(req.getParameter("Pdid").trim());
	System.out.println(pdid);
	
	Integer pdsid = Integer.valueOf(req.getParameter("Pdsid").trim());
	System.out.println(pdsid);   
	
	
	String pdName = null;
	try {
		pdName = req.getParameter(("PdName").trim());
	} catch (RuntimeException e) {
		errorMsgs.add("商品名稱請勿重複");
	}
	System.out.println(pdName);   
	
	String pdNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
		if (pdName == null || pdName.trim().length() == 0) {
			errorMsgs.add("商品名稱請勿空白");
		} else if(!pdName.trim().matches(pdNameReg)) { //以下練習正則(規)表示式(regular-expression)
			errorMsgs.add("員工姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
        }
		
	Integer pdPrice = null;
		try {
			pdPrice = Integer.valueOf(req.getParameter("PdPrice").trim());
		} catch (NumberFormatException e) {
			pdPrice = 0;
			errorMsgs.add("商品價格請填數字");
		}
	System.out.println(pdPrice); 
	
	Integer pdDiscountPrice = null;
		try {
			pdDiscountPrice = Integer.valueOf(req.getParameter("PdDiscountPrice").trim());
		} catch (NumberFormatException e) {
			pdDiscountPrice = 0;
			errorMsgs.add("商品優惠價格請填數字");
		}
		System.out.println(pdDiscountPrice);
		
	String pdDescription = req.getParameter("PdDescription").trim();
		
	System.out.println(pdDescription);
	
	
	Integer pdStatus = Integer.valueOf(req.getParameter("PdStatus").trim());
	
	LocalDateTime pdUpdate = LocalDateTime.now();
	System.out.println(pdUpdate);
	
	ProductVO productVO = new ProductVO();
	productVO.setPdid(pdid);
	productVO.setPdsid(pdsid);
	productVO.setPdName(pdName);
	productVO.setPdPrice(pdPrice);
	productVO.setPdDiscountPrice(pdDiscountPrice);
	productVO.setPdDescription(pdDescription);
	productVO.setPdStatus(pdStatus);
	productVO.setPdUpdate(pdUpdate);
	
	// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的productVO物件,也存入req
			RequestDispatcher failureView = req
				.getRequestDispatcher("/backend/productAdd.jsp");
			failureView.forward(req, res);
			return;
		}
//	Step 2
	ProductService pdSvc = new ProductService();
	productVO = pdSvc.updateProduct(pdid, pdsid, pdName, pdPrice, pdDiscountPrice, pdDescription, pdStatus, pdUpdate);
//	Step 3
	req.setAttribute("productVO", productVO); // 資料庫update成功後,正確的的productVO物件,存入req
	String url = "/backend/productShowOne.jsp";
	RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
	successView.forward(req, res);
	}
	
	
//	================================寫到這
// 新商品新增圖片		

	if("addpic".equals(action2)) {
		System.out.println(action2);
		System.out.println(req.getParameter("PdPic1"));
		
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		ProductpicVO productpicVO = new ProductpicVO();
		
		Part part = req.getPart("PdPic1");
		InputStream in=part.getInputStream();
		byte[] pdpicbyte = new byte[in.available()];
		in.read(pdpicbyte);
		in.close();
		System.out.println(pdpicbyte);
		System.out.println("pdpicbyte="+ pdpicbyte.length);
		if (pdpicbyte.length != 0) {
		productpicVO.setPdPic(pdpicbyte);
		ProductpicService pdpicSvc = new ProductpicService();
		productpicVO = pdpicSvc.insert(pdpicbyte);
		}
		else {System.out.println("無圖片新增");
		req.setAttribute("productpicVO", productpicVO); // 資料庫取出的empVO物件,存入req 
			}
		}
//	舊商品新增圖片	
	
	if("addpic2".equals(action3)) {
		System.out.println(action3);
		System.out.println(req.getParameter("PdPic2"));
		
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		Integer pdid = Integer.valueOf(req.getParameter("Pdid").trim());
		System.out.println(pdid);

		ProductpicVO productpicVO = new ProductpicVO();
		Part part = req.getPart("PdPic2");
		InputStream in=part.getInputStream();
		byte[] pdpicbyte = new byte[in.available()];
		in.read(pdpicbyte);
		in.close();
		
		System.out.println(pdpicbyte);
		System.out.println("pdpicbyte="+ pdpicbyte.length);
		
		if (pdpicbyte.length != 0) {
		productpicVO.setPdPic(pdpicbyte);
		ProductpicService pdpicSvc = new ProductpicService();
		productpicVO = pdpicSvc.existedInsert(pdid, pdpicbyte);
		}
		
		else {System.out.println("無圖片上傳");
		req.setAttribute("productpicVO", productpicVO); // 資料庫取出的empVO物件,存入req 
			}
		}
	
	
	
}
}

		
		

		
		
		
//		String pdpic = null;
//		try {
//			pdpic = Base64.getEncoder().encodeToString(productpicVO.getPdPic());	
//			System.out.println("pdpci="+pdpic);
//		} catch (NullPointerException e) {
//
//			ServletContext servletContext = req.getServletContext();
//			String filepath = servletContext.getRealPath("/picture/nopicture.jpg");
//			File file=new File(filepath);
//			
//			FileInputStream fis = new FileInputStream(file);
//			byte[] b1 = new byte[fis.available()];
//			fis.read(b1);
//			fis.close();
//			pdpic = Base64.getEncoder().encodeToString(b1);	
//			System.out.println(pdpic);

		
		// Send the use back to the form, if there were errors
//			if (!errorMsgs.isEmpty()) {
//				req.setAttribute("productpicVO", productpicVO); // 含有輸入格式錯誤的productVO物件,也存入req
//				RequestDispatcher failureView = req.getRequestDispatcher("/backend/productAdd.jsp");
//				failureView.forward(req, res);
//				return;
		
	







