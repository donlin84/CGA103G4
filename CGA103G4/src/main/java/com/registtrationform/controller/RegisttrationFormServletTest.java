package com.registtrationform.controller;

import java.io.Console;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.json.JsonObject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.registtrationform.model.*;
import com.ClassIfm.model.*;
import com.google.gson.JsonArray;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
@WebServlet("/back-end/registtrationform/RegisttrationFormTest.do")
public class RegisttrationFormServletTest extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);

	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		System.out.println(action);
	

		
		RegisttrationFormService registtrationFormSvc = new RegisttrationFormService();

		ClassIfmService classIfmSvc = new ClassIfmService();
		// 取得陣列
		List<RegisttrationFormVO> registtrationFormList = registtrationFormSvc.getAll();
		
		List<ClassIfmVO> ClassIfmList = null;
		if( "Allclass".equals(action) ) {
			ClassIfmList = classIfmSvc.getAll();
		}else if("Listedclass".equals(action) ){
			ClassIfmList =	classIfmSvc.front_getall();
		}	
		
		Integer people = null;

		JSONArray jsonarray = new JSONArray();

//		localdatetime 轉 datetime
//		LocalDateTime nowLocalDate = LocalDateTime.now();
//		Date date  = Date.from(nowLocalDate.atZone(ZoneId.systemDefault()).toInstant());
		for (ClassIfmVO vo : ClassIfmList) {
			Integer claid = vo.getClaid();
			people = registtrationFormSvc.getConutPeople(claid);

			JSONObject json = new JSONObject();
			try {
				json.put("claid", vo.getClaid());
				json.put("thrid", vo.getThrid());
				json.put("claTagid", vo.getClaTagid());
				json.put("claTitle", vo.getClaTitle());
				json.put("claIntroduction", vo.getClaIntroduction());
				json.put("claTime", vo.getClaTime().toString());
				json.put("claPrice", vo.getClaPrice());
				json.put("claPeopleMax", vo.getClaPeopleMax());
				json.put("claPeopleMin", vo.getClaPeopleMin());
				json.put("claPeople", vo.getClaPeople());
				json.put("claStatus", vo.getClaStatus());
				json.put("claStrTime", vo.getClaStrTime().toString());
				json.put("claFinTime", vo.getClaFinTime().toString());
			} catch (JSONException e) {
				e.printStackTrace();
			}
			jsonarray.put(json);

		}


		System.out.println(jsonarray);
		res.getWriter().print(jsonarray);
		return;
		
		
	}
}
