package com.registtrationform.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.groovy.transform.SourceURIASTTransformation;
import org.json.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.propertyeditors.InputStreamEditor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.registtrationform.model.*;

import oracle.net.aso.g;

import com.ClassIfm.model.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024
* 1024)
public class CheckoutServlet extends HttpServlet{

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		HttpSession session = req.getSession();
		session.setAttribute("member","201");
		Integer memid =  Integer.valueOf( (String) session.getAttribute("member") );		
		String action = req.getParameter("action");
		System.out.println(action);
		//取得資訊
		if ("ClassIinfo".equals(action)) {
//			get request body寫法
//			Gson gson = new Gson();
//			JsonObject jsonObject = gson.fromJson(req.getReader(), JsonObject.class);
//			System.out.println(jsonObject);
			
			Integer claid = null;
			try {
				claid = Integer.valueOf(req.getParameter("claid"));
			} catch (Exception e) {
				//返回查無此課程
				res.getWriter().print("noclass");
				return;
			}				
			
			RegisttrationFormService registtrationFormSvc = new RegisttrationFormService();
			
			JSONObject json = new JSONObject();
			
			
			ClassIfmService classIfmSvc = new ClassIfmService();
			ClassIfmVO classIfmVO = classIfmSvc.getOneClassIfm(claid);
			try {
				json.put("claid", classIfmVO.getClaid());
//					json.put("thrid", classIfmVO.getThrid());
				json.put("thridName", classIfmVO.getTeacherVO().getThrName());
//					json.put("claTagid", classIfmVO.getClaTagid());
				json.put("claTagName", classIfmVO.getClassTagVO().getClaTagName());
				json.put("claTitle", classIfmVO.getClaTitle());
				json.put("claIntroduction", classIfmVO.getClaIntroduction());
				json.put("claTime", classIfmVO.getClaTime().toString());
				json.put("claPrice", classIfmVO.getClaPrice());
				json.put("claPeopleMax", classIfmVO.getClaPeopleMax());
				json.put("claPeople", classIfmVO.getClaPeople());
			} catch (JSONException e) {
				e.printStackTrace();
			}				
			

			res.getWriter().print(json);
			return;
		}
		
			
		if ("transfer_finish".equals(action)) {
			
			//存入資料庫
			RegisttrationFormService registtrationFormSvc = new RegisttrationFormService();
			ClassIfmService classIfmSvc = new ClassIfmService();
			
			Integer claid = null;
			try {
				claid = Integer.valueOf(req.getParameter("claid").trim());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			Integer people = null;
			try {
				people = Integer.valueOf(req.getParameter("people").trim());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
//			Integer memid = null;
//			try {
//				memid = Integer.valueOf(req.getParameter("memid").trim());
//			} catch (NumberFormatException e) {
//				e.printStackTrace();
//			}
			
			Integer regPayment = null;
			try {
				regPayment = Integer.valueOf(req.getParameter("regPayment").trim());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			
			//存值
			
			JSONObject jsonString = new JSONObject();

			Integer maxPeople = classIfmSvc.getOneClassIfm(claid).getClaPeopleMax();
			//不允許送出訂單條件:
			//1.課程非上架狀態 2.已報名過 3.報名人數已達到最大人數 4.已報名人數+欲報名人數 > 最大上限 
			if(	classIfmSvc.getOneClassIfm(claid).getClaStatus()!=1) {
				System.out.println("課程未上架或已結束");
				jsonString = jsonString(jsonString,"課程未上架或已結束");	
			}
			else if (		
				registtrationFormSvc.getOneRegisttrationForm(claid, memid)!=null)
			{ 
				System.out.println("已報名過");
				jsonString = jsonString(jsonString,"已報名過");	
				
			}else if (		
				registtrationFormSvc.getConutPeople(claid) >=  maxPeople||
				registtrationFormSvc.getConutPeople(claid) +people > maxPeople)
			{ 
				System.out.println("超過人數上限");
				jsonString = jsonString(jsonString,"超過人數上限")	;	
					
			}else {	
				//資料庫添加紀錄
				registtrationFormSvc.addRegisttrationForm(claid, memid, regPayment, 0, people,null,null);
				//更新課程報名人數
				classIfmSvc.update_clapeople(registtrationFormSvc.getConutPeople(claid), claid);
				jsonString = jsonString(jsonString,"success");
		}
		//送出訊息
		res.getWriter().print(jsonString);	
		return;
	}
		
		//刷卡
		if ("card".equals(action)) {
			
			//驗證卡號
			HashMap<String, String> errorMsgs = new HashMap<String, String>();
			

			//format
			String cardNumber = "^[(0-9]{4}$";
			
			String cardNumber1 = req.getParameter("cardNumber1");
			if (cardNumber1 == null || cardNumber1.trim().length() == 0) {
				errorMsgs.put("cardNumber1","請輸入卡號");
			} else if(!cardNumber1.trim().matches(cardNumber)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("cardNumber1","卡號格式錯誤");
            }
			String cardNumber2 = req.getParameter("cardNumber2");
			if (cardNumber2 == null || cardNumber2.trim().length() == 0) {
				errorMsgs.put("cardNumber2","請輸入卡號");
			} else if(!cardNumber2.trim().matches(cardNumber)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("cardNumber2","卡號格式錯誤");
			}
			String cardNumber3 = req.getParameter("cardNumber3");
			if (cardNumber3 == null || cardNumber3.trim().length() == 0) {
				errorMsgs.put("cardNumber3","請輸入卡號");
			} else if(!cardNumber3.trim().matches(cardNumber)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("cardNumber3","卡號格式錯誤");
			}
			
			String cardNumber4 = req.getParameter("cardNumber4");
			if (cardNumber4 == null || cardNumber4.trim().length() == 0) {
				errorMsgs.put("cardNumber4","請輸入卡號");
			} else if(!cardNumber4.trim().matches(cardNumber)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("cardNumber4","卡號格式錯誤");
			}
			
			String cardHolder = req.getParameter("cardHolder");
			String cardHolderReg = "^[(\u4e00-\u9fa5)(a-zA-Z_)]{2,10}$";
			if (cardHolder == null || cardHolder.trim().length() == 0) {
				errorMsgs.put("cardHolder","請輸入持卡人姓名");
			} else if(!cardHolder.trim().matches(cardHolderReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("cardHolder","持卡人姓名: 只能是中、英文字母、數字和_ , 且長度必需在2到10之間");
			}	
			
			String cardMonth = req.getParameter("cardMonth");
			if (cardMonth == null || cardMonth.trim().length() == 0) {
				errorMsgs.put("cardMonth","請選擇卡片有效月份");
			} 	
			String cardYear = req.getParameter("cardYear");
			if (cardYear == null || cardYear.trim().length() == 0) {
				errorMsgs.put("cardYear","請選擇卡片有效年份");
			} 	
			
			String cardCcvReg = "^[(0-9]{3}$";
			String cardCcv = req.getParameter("cardCcv");
			if (cardCcv == null || cardCcv.trim().length() == 0) {
				errorMsgs.put("cardCcv","請輸入信用卡安全代碼");
			} else if(!cardCcv.trim().matches(cardCcvReg)) { //以下練習正則(規)表示式(regular-expression)
				errorMsgs.put("cardCcv","請輸入數字");
			}	
			
			if (!errorMsgs.isEmpty()) {
				JSONObject jsonmap = new JSONObject(errorMsgs) ;
				res.getWriter().print(jsonmap);
				return;
			}
			
			
			//存入資料庫
			RegisttrationFormService registtrationFormSvc = new RegisttrationFormService();
			ClassIfmService classIfmSvc = new ClassIfmService();
			
			Integer claid = null;
			try {
				claid = Integer.valueOf(req.getParameter("claid").trim());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			Integer people = null;
			try {
				people = Integer.valueOf(req.getParameter("people").trim());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
//			Integer memid = null;
//			try {
//				memid = Integer.valueOf(req.getParameter("memid").trim());
//			} catch (NumberFormatException e) {
//				e.printStackTrace();
//			}
			
			Integer regPayment = null;
			try {
				regPayment = Integer.valueOf(req.getParameter("regPayment").trim());
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			
			//存值
			
			JSONObject jsonString = new JSONObject();

			Integer maxPeople = classIfmSvc.getOneClassIfm(claid).getClaPeopleMax();
			//不允許送出訂單條件:
			//1.課程非上架狀態 2.已報名過 3.報名人數已達到最大人數 4.已報名人數+欲報名人數 > 最大上限 
			if(	classIfmSvc.getOneClassIfm(claid).getClaStatus()!=1) {
				System.out.println("課程未上架或已結束");
				jsonString = jsonString(jsonString,"課程未上架或已結束");	
			}
			else if (		
				registtrationFormSvc.getOneRegisttrationForm(claid, memid)!=null)
			{ 
				System.out.println("已報名過");
				jsonString = jsonString(jsonString,"已報名過");	
				
			}else if (		
				registtrationFormSvc.getConutPeople(claid) >=  maxPeople||
				registtrationFormSvc.getConutPeople(claid) +people > maxPeople)
			{ 
				System.out.println("超過人數上限");
				jsonString = jsonString(jsonString,"超過人數上限")	;	
					
			}else {	
				//資料庫添加紀錄
				registtrationFormSvc.addRegisttrationForm(claid, memid, regPayment, 0, people,null,null);
				//更新課程報名人數
				classIfmSvc.update_clapeople(registtrationFormSvc.getConutPeople(claid), claid);
				jsonString = jsonString(jsonString,"success");
		}
		//送出訊息
		res.getWriter().print(jsonString);	
		return;
			
		}
	}
	
	
	
	public JSONObject jsonString(JSONObject jsonString, String str) {
		try {
			jsonString.put("payment_commit",str);
		} catch (JSONException e) {
			e.printStackTrace();	}		
		return jsonString;
	}
	

	
//	@ResponseBody
//	public JSONObject TestUrl(HttpServletRequest request,
//		@RequestBody JSONObject jsonObject){
//	   try {
//		String username = jsonObject.get("username").toString();
//		System.out.println(username);
//	} catch (JSONException e) {
//		e.printStackTrace();
//	}
//	   
//	   
//	   return jsonObject;
//	}
//	
}
