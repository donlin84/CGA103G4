package com.product.controller;

import java.io.*;

import java.time.LocalDateTime;

import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.product.model.ProductService;
import com.product.model.ProductVO;

import com.productPicture.model.ProductpicService;
import com.productPicture.model.ProductpicVO;

import com.promotionsdetail.model.PromotionsDetailService;
import com.promotionsdetail.model.PromotionsDetailVO;
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
@WebServlet("/back-end/product/ProductServlet.do")
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
	String insert = req.getParameter("Insert");
	String addpicForNewPd = req.getParameter("AddpicForNewPd");
	String pdInfoUpdate = req.getParameter("PdInfoUpdate");
	String getOne_For_Update = req.getParameter("GetOne_For_Update");
	String PromoAdd = req.getParameter("PromoAdd");
	String addpicForPdUpdate = req.getParameter("AddpicForPdUpdate");
	
	
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
	
	if ("insert".equals(insert)) {   
		
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
		
			/***********************1.接收請求參數 - 輸入格式的錯誤處理*************************/
		
		Integer pdid = Integer.valueOf(req.getParameter("Pdid"));
		
		Integer pdsid = Integer.valueOf(req.getParameter("Pdsid"));
		
		String pdName = null;
			
		pdName = req.getParameter(("PdName").trim());

		ProductService pdSvcForRepeatPdName = new ProductService();
		List<ProductVO> pdNameList = pdSvcForRepeatPdName.getAllProductName();
		
		String pdNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
			if (pdName == null || pdName.trim().length() == 0) {
				errorMsgs.add("商品名稱請勿空白");
			} else if(!pdName.trim().matches(pdNameReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
            } 
			
			for (ProductVO aProduct : pdNameList) {
				String str = aProduct.getPdName();
				
				if (pdName.equals(str)) {
					errorMsgs.add("商品名稱請勿重複");
				}	
			}
		Integer pdPrice = null;
			try {
				pdPrice = Integer.valueOf(req.getParameter("PdPrice").trim());
			} catch (NumberFormatException e) {
				pdPrice = 0;
				errorMsgs.add("商品價格請填數字");
			}

		Integer pdDiscountPrice = null;
		try {
			pdDiscountPrice = Integer.valueOf(req.getParameter("PdDiscountPrice").trim());
		} catch (NumberFormatException e) {
			pdDiscountPrice = 0;
			errorMsgs.add("");
		}

		
		String pdDescription = req.getParameter("PdDescription").trim();

		
		if(pdDescription.trim().length() > 500) { //以下練習正則(規)表示式(regular-expression)
			errorMsgs.add("商品描述不超過500字");
		}
		
		
		Integer pdStatus = null;
		try {
			pdStatus = Integer.valueOf(req.getParameter("PdStatus").trim());
		} catch (NumberFormatException e) {
			pdStatus = 0;
			errorMsgs.add("請決定是否立刻上架");
		}

		System.out.println(pdStatus);
		LocalDateTime pdUpdate = LocalDateTime.now();
		
		
			ProductVO productVO = new ProductVO();
			
			productVO.setPdsid(pdsid);
			productVO.setPdName(pdName);
			productVO.setPdPrice(pdPrice);
			productVO.setPdDiscountPrice(pdDiscountPrice);
			productVO.setPdDescription(pdDescription);
			productVO.setPdStatus(pdStatus);
			productVO.setPdUpdate(pdUpdate);
			

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("productVO", productVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req
						.getRequestDispatcher("/back-end/product/AddPd.do");
				failureView.forward(req, res);
				return;
			}
			
			/***************************2.開始新增資料***************************************/
			
			ProductService pdSvc = new ProductService();
			productVO = pdSvc.addProduct(pdsid, pdName, pdPrice, pdDiscountPrice, pdDescription, pdStatus, pdUpdate);
			System.out.println(productVO);
			
			
			
	//=================== 新商品新增圖片=========================
	if("addpicForNewPd".equals(addpicForNewPd)) {
		
		req.setAttribute("errorMsgs", errorMsgs);

		ProductpicVO productpicVO = new ProductpicVO();
		
		Part part = req.getPart("PdPic1");
		InputStream in = part.getInputStream();
		byte[] pdpicbyte = new byte[in.available()];
		in.read(pdpicbyte);
		in.close();

		if (pdpicbyte.length != 0) {
		productpicVO.setPdPic(pdpicbyte);
		ProductpicService pdpicSvc = new ProductpicService();
		productpicVO = pdpicSvc.insert(pdpicbyte);
		}
		else {
		req.setAttribute("productpicVO", productpicVO);  
			}

		
//		第二張		
		ProductpicVO productpicVO2 = new ProductpicVO();
		
		Part part2 = req.getPart("PdPic2");
		InputStream in2=part2.getInputStream();
		byte[] pdpicbyte2 = new byte[in2.available()];
		in2.read(pdpicbyte2);
		in2.close();
		
		if (pdpicbyte2.length != 0) {
			productpicVO2.setPdPic(pdpicbyte2);
			ProductpicService pdpicSvc2 = new ProductpicService();
			productpicVO2 = pdpicSvc2.insert(pdpicbyte2);
			}
			else {
			req.setAttribute("productpicVO", productpicVO);  
				}
		
//		第三張
ProductpicVO productpicVO3 = new ProductpicVO();
		
		Part part3 = req.getPart("PdPic3");
		InputStream in3 = part3.getInputStream();
		byte[] pdpicbyte3 = new byte[in3.available()];
		in3.read(pdpicbyte3);
		in3.close();
		
		if (pdpicbyte3.length != 0) {
			productpicVO3.setPdPic(pdpicbyte3);
			ProductpicService pdpicSvc3 = new ProductpicService();
			productpicVO3 = pdpicSvc3.insert(pdpicbyte3);
			}
			else {
			req.setAttribute("productpicVO", productpicVO);  
				}

		ProductService pdSvc2 = new ProductService();
		ProductVO productVO2 = pdSvc2.getOneproduct(pdid);
		if (productVO == null) {
			errorMsgs.add("查無資料");
		}
		// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			RequestDispatcher failureView = req
					.getRequestDispatcher("/back-end/product/productAdd.jsp");
			failureView.forward(req, res);
			return;//程式中斷
		} else {

			
		req.setAttribute("productVO2", productVO2); 
		String url = "/back-end/product/productShowOne.jsp";
		
		RequestDispatcher successView = req.getRequestDispatcher(url); 
		successView.forward(req, res);

		}
	}
//=====================================================新商品圖片新增結束
	
	if ("PromoAdd".equals(PromoAdd)) {
		System.out.println(PromoAdd);
		
		Integer pmid = Integer.valueOf(req.getParameter("Pmid"));
		Integer pmPdDiscountPrice = Integer.valueOf(req.getParameter("PdDiscountPrice"));
		
		PromotionsDetailVO promotionsDetailVO = new PromotionsDetailVO();
		 
		promotionsDetailVO.setPdid(pdid);
		System.out.println(pdid);
		promotionsDetailVO.setPmid(pmid);
		System.out.println(pmid);
		promotionsDetailVO.setPmPdDiscountPrice(pmPdDiscountPrice);
		System.out.println(pmPdDiscountPrice);
		
		
		PromotionsDetailService promoDetailSvc = new PromotionsDetailService();
		promotionsDetailVO = promoDetailSvc.addPromotionsDetail(pmid, pdid, pmPdDiscountPrice);
		System.out.println(promotionsDetailVO);
		
		
	}
	}

	
	//=========================================== query before update ==================================================	

	if ("getOne_For_Update".equals(getOne_For_Update)) {
		List<String>errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
//		step1
		
		Integer pdid = Integer.valueOf(req.getParameter("pdid"));
	
//		step2
		ProductService pdSvc = new ProductService();
		ProductVO productVO = pdSvc.getOneproduct(pdid);
		
//		step3
		req.setAttribute("productVO", productVO);
		String url = "/back-end/product/productModify.jsp";
		RequestDispatcher successView = req.getRequestDispatcher(url);
		successView.forward(req,res);
		
		//=========================================== update ==================================================		
	}
if("addpicForPdUpdate".equals(addpicForPdUpdate)) {
		
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
		
		Integer pdid = Integer.valueOf(req.getParameter("Pdid").trim());


		ProductpicVO productpicVO = new ProductpicVO();
		Part part = req.getPart("OldPdAddPic");
		InputStream in=part.getInputStream();
		byte[] pdpicbyte = new byte[in.available()];
		in.read(pdpicbyte);
		in.close();
		
		
		if (pdpicbyte.length != 0) {
		productpicVO.setPdPic(pdpicbyte);
		ProductpicService pdpicSvc = new ProductpicService();
		productpicVO = pdpicSvc.existedInsert(pdid, pdpicbyte);
		}
		
		else {
		req.setAttribute("productpicVO", productpicVO); // 資料庫取出的empVO物件,存入req 
			}
		}

	if("pdInfoUpdate".equals(pdInfoUpdate)) {
		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);
	Integer pdid = Integer.valueOf(req.getParameter("Pdid").trim());

	
	Integer pdsid = Integer.valueOf(req.getParameter("Pdsid").trim());
  
	String pdName = req.getParameter(("PdName").trim());
	
	String pdNameReg = "^[(\u4e00-\u9fa5)(a-zA-Z0-9_)]{2,10}$";
		if (pdName == null || pdName.trim().length() == 0) {
			errorMsgs.add("商品名稱請勿空白");
		} else if(!pdName.trim().matches(pdNameReg)) { //以下練習正則(規)表示式(regular-expression)
			errorMsgs.add("商品名稱: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
        };
		
	Integer pdPrice = null;
		try {
			pdPrice = Integer.valueOf(req.getParameter("PdPrice").trim());
		} catch (NumberFormatException e) {
			pdPrice = 0;
			errorMsgs.add("商品價格請填數字");
		};

	
	Integer pdDiscountPrice = null;
		try {
			pdDiscountPrice = Integer.valueOf(req.getParameter("PdDiscountPrice").trim());
		} catch (NumberFormatException e) {
			pdDiscountPrice = 0;
			errorMsgs.add("");
		}

	
	String pdDescription = req.getParameter("PdDescription").trim();

	if(pdDescription.trim().length() > 500) { 
		errorMsgs.add("商品描述不超過500字");
	}
			
	Integer pdStatus = Integer.valueOf(req.getParameter("PdStatus").trim());
	
	LocalDateTime pdUpdate = LocalDateTime.now();

	
	ProductVO productVO2 = new ProductVO();
	productVO2.setPdid(pdid);
	productVO2.setPdsid(pdsid);
	productVO2.setPdName(pdName);
	productVO2.setPdPrice(pdPrice);
	productVO2.setPdDiscountPrice(pdDiscountPrice);
	productVO2.setPdDescription(pdDescription);
	productVO2.setPdStatus(pdStatus);
	productVO2.setPdUpdate(pdUpdate);
	
	// Send the use back to the form, if there were errors
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("productVO", productVO2); // 含有輸入格式錯誤的productVO物件,也存入req
			RequestDispatcher failureView = req
				.getRequestDispatcher("/back-end/product/productModify.jsp");
			failureView.forward(req, res);

			return;
		}
//	Step 2
	ProductService pdSvc = new ProductService();
	productVO2 = pdSvc.updateProduct(pdid, pdsid, pdName, pdPrice, pdDiscountPrice, pdDescription, pdStatus, pdUpdate);
//	Step 3
	
	req.setAttribute("productVO2", productVO2); // 資料庫update成功後,正確的的productVO物件,存入req
	String url = "/back-end/product/productShowOne.jsp";
	RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
	successView.forward(req, res);
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
		
	







