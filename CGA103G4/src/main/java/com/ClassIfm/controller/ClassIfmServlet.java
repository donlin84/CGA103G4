package com.ClassIfm.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DomainCombiner;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.eclipse.jdt.internal.compiler.batch.Main;

import com.ClassIfm.model.ClassIfmJDBCDAO;
import com.ClassIfm.model.ClassIfmService;
import com.ClassIfm.model.ClassIfmVO;
import com.ClassPicture.model.ClassPictureJDBCDAO;
import com.ClassPicture.model.ClassPictureVO;


@WebServlet("/ClassIfmServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
//貼又遇給的
public class ClassIfmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	Connection con;
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		
		
//		-------------------這是新增-------------------
		if("add".equals(req.getParameter("action"))) {
			
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			//看前端送來的值
			
			
			String clatitle = req.getParameter("clatitle");
			if(clatitle == null || clatitle.trim().length() == 0) {
				errorMsgs.add("課程標題 請勿空白!");
			}else if(clatitle.length()>25){
				errorMsgs.add("字數請在25字以下");
			}
			
			Integer thrid = Integer.valueOf(req.getParameter("thrid")); 
			Integer clatagid = Integer.valueOf(req.getParameter("clatagid"));; 
			Integer claprice = Integer.valueOf(req.getParameter("claprice"));
			if(claprice == 0) {
				errorMsgs.add("課程價格 請勿為0");
			}
			Integer clapeoplemax = null;
			try {
				clapeoplemax = Integer.valueOf(req.getParameter("clapeoplemax").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("人數上限不得為0");
			}
			Integer clapeoplemin = null;
			try {
				clapeoplemin = Integer.valueOf(req.getParameter("clapeoplemin").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("人數下限不得為0");
			}
			
			
			if(clapeoplemax!=null && clapeoplemin!=null) {
				if((clapeoplemax.compareTo(clapeoplemin))==-1) {
					errorMsgs.add("人數上限 不得低於人數下限");
				}else if((clapeoplemax.compareTo(clapeoplemin))==0) {
					errorMsgs.add("人數 上限/下限 不得一樣");
				}
			}else {
				
			}
			
			
			Integer clastatus =Integer.valueOf(req.getParameter("clastatus"));
			String claintroduction = req.getParameter("claintroduction");
			if(claintroduction == null || claintroduction.trim().length() == 0) {
				errorMsgs.add("課程簡介 請勿空白!");
			}else if(claintroduction.length()>25){
				errorMsgs.add("字數請在250字以下");
			}
			
			//做時間處理=>符合localdatetime格式
			
			
			String clatime_old = req.getParameter("clatime");
			if(clatime_old.trim().equals("")) {
				errorMsgs.add("課程日期 請輸入日期");
			}

			String clatime = clatime_old.replace(" ", "T");
			
			String clastrtime_old = req.getParameter("clastrtime");
			if(clastrtime_old.trim().equals("")) {
				errorMsgs.add("課程報名時間 請輸入日期");
			}
			String clastrtime = clastrtime_old.replace(" ", "T");
			
			String clafintime_old = req.getParameter("clafintime");
			if(clafintime_old.trim().equals("")) {
				errorMsgs.add("結束報名時間 請輸入日期");
			}
			String clafintime = clafintime_old.replace(" ", "T");
			
			Integer clapeople = Integer.valueOf(3);	//抓報名表的人數 不會顯示在新增表上
			
			Part part1 = req.getPart("clapic1");
			InputStream in1 = part1.getInputStream();
			System.out.println(in1.available());
			byte[] clapic1 = new byte[in1.available()];
			in1.read(clapic1);
			in1.close();
			
			Part part2 = req.getPart("clapic2");
			InputStream in2 = part2.getInputStream();
			System.out.println(in2.available());
			byte[] clapic2 = new byte[in2.available()];
			in2.read(clapic2);
			in2.close();
			
			Part part3 = req.getPart("clapic3");
			InputStream in3 = part3.getInputStream();
			System.out.println(in3.available());
			byte[] clapic3 = new byte[in3.available()];
			in3.read(clapic3);
			in3.close();
			

			
			System.out.println("clatitle:"+clatitle);
			System.out.println("thrid:"+thrid);
			System.out.println("clatagid:"+clatagid);
			System.out.println("clatime_old:"+clatime_old);
			System.out.println("clatime:"+clatime);
			System.out.println("claprice:"+claprice);
			System.out.println("clapeoplemax:"+clapeoplemax);
			System.out.println("clapeoplemin:"+clapeoplemin);
			System.out.println("clapeople:"+clapeople);
			System.out.println("clastatus:"+clastatus);
			System.out.println("clastrtime_old:"+clastrtime_old);
			System.out.println("clastrtime:"+clastrtime);
			System.out.println("clafintime_old:"+clafintime_old);
			System.out.println("clafintime:"+clafintime);
			
			System.out.println("part1:"+part1);
			System.out.println("part2:"+part2);
			System.out.println("part3:"+part3);
			System.out.println("in1:"+in1);
			System.out.println("in2:"+in2);
			System.out.println("in3:"+in3);
			System.out.println("clapic1:"+clapic1);
			System.out.println("clapic2:"+clapic2);
			System.out.println("clapic3:"+clapic3);
			
			ClassIfmVO classIfmVO = new ClassIfmVO();
//			
//			classIfmVO.setThrid(thrid);
//			classIfmVO.setClaTagid(clatagid);
//			classIfmVO.setClaTitle(clatitle);
//			classIfmVO.setClaIntroduction(claintroduction);
//			classIfmVO.setClaTime(LocalDateTime.parse(clatime));
			classIfmVO.setClaPrice(claprice);	//給前台抓價格
//			classIfmVO.setClaPeopleMax(clapeoplemax);
//			classIfmVO.setClaPeopleMin(clapeoplemin);
//			classIfmVO.setClaPeople(clapeople);
//			classIfmVO.setClaStatus(clastatus);
//			classIfmVO.setClaStrTime(LocalDateTime.parse(clastrtime));
//			classIfmVO.setClaFinTime(LocalDateTime.parse(clafintime));
			
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("classIfmVO", classIfmVO);
				RequestDispatcher failureView = req.getRequestDispatcher("/backend/classifm/addClassIfm.jsp");
				failureView.forward(req, resp);
				return;
			}
			
//			ClassIfmService classifmSvc = new ClassIfmService();
//			classifmSvc.addClassIfm(thrid, clatagid, clatitle, claintroduction, LocalDateTime.parse(clatime), claprice, clapeoplemax, clapeoplemin, clapeople, clastatus, LocalDateTime.parse(clastrtime), LocalDateTime.parse(clafintime));
			//已下測試抓字曾主建新增圖片
			ClassIfmJDBCDAO dao = new ClassIfmJDBCDAO();
			
			ClassIfmVO classifmVO = new ClassIfmVO();
			
			classifmVO.setThrid(thrid);
			classifmVO.setClaTagid(clatagid);
			classifmVO.setClaTitle(clatitle);
			classifmVO.setClaIntroduction(claintroduction);
			classifmVO.setClaTime(LocalDateTime.parse(clatime));
			classifmVO.setClaPrice(claprice);
			classifmVO.setClaPeopleMax(clapeoplemax);
			classifmVO.setClaPeopleMin(clapeoplemin);
			classifmVO.setClaPeople(clapeople);
			classifmVO.setClaStatus(clastatus);
			classifmVO.setClaStrTime(LocalDateTime.parse(clastrtime));
			classifmVO.setClaFinTime(LocalDateTime.parse(clafintime));

			List<ClassPictureVO> testList = new ArrayList<ClassPictureVO>();
			ClassPictureVO classPictureVO1 = new ClassPictureVO();
			classPictureVO1.setClaPic(clapic1);
			
			ClassPictureVO classPictureVO2 = new ClassPictureVO();
			classPictureVO2.setClaPic(clapic2);
			
			ClassPictureVO classPictureVO3 = new ClassPictureVO();
			classPictureVO3.setClaPic(clapic3);
			
			testList.add(classPictureVO1);
			testList.add(classPictureVO2);
			testList.add(classPictureVO3);
			
			dao.insertwithclapic(classifmVO, testList);
			
//		--新增成功跳轉到查詢葉面確認--
			String url = "/backend/classifm/listAllClassIfm.jsp";
			RequestDispatcher insertsuccess = req.getRequestDispatcher(url);
			insertsuccess.forward(req, resp);
		}
		
//		-------------------這是單查詢-------------------
		
		if("getone".equals(req.getParameter("action"))) {
		
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
			
		Integer claid = Integer.valueOf(req.getParameter("claid"));
			
		ClassIfmService classifmSvc = new ClassIfmService();
		ClassIfmVO classIfmVO=classifmSvc.getOneClassIfm(claid);
		req.setAttribute("classIfmVO", classIfmVO);
		//跳轉道單查詢網頁
		String url ="/backend/classifm/listOneClassIfm.jsp";
		RequestDispatcher getOneClassIfm = req.getRequestDispatcher(url);
		getOneClassIfm.forward(req, resp);;
		}
		
//		-------------------這是準備修改-------------------
		
		if("forward_to_update".equals(req.getParameter("action"))) {
			
		Integer claid = Integer.valueOf(req.getParameter("claid"));
			
		ClassIfmService classifmSvc = new ClassIfmService();
		ClassIfmVO classIfmVO=classifmSvc.getOneClassIfm(claid);
		req.setAttribute("classIfmVO", classIfmVO);
		//跳轉道單查詢網頁
		String url ="/backend/classifm/updateClassIfm.jsp";
		RequestDispatcher getOneClassIfm = req.getRequestDispatcher(url);
		getOneClassIfm.forward(req, resp);;
		}
		
//		-------------------這是修改-------------------
		
		if("update".equals(req.getParameter("action"))) {
			
		List<String> errorMsgs = new LinkedList<String>();
		// Store this set in the request scope, in case we need to
		// send the ErrorPage view.
		req.setAttribute("errorMsgs", errorMsgs);
			
		Integer claid = Integer.valueOf(req.getParameter("claid"));	//拿claid去修改那堂課資訊
			
		String clatitle = req.getParameter("clatitle"); 
		Integer thrid = Integer.valueOf(req.getParameter("thrid")); 
		Integer clatagid = Integer.valueOf(req.getParameter("clatagid"));; 
		Integer claprice = Integer.valueOf(req.getParameter("claprice"));
		Integer clapeoplemax = Integer.valueOf(req.getParameter("clapeoplemax")); 
		Integer clapeoplemin = Integer.valueOf(req.getParameter("clapeoplemin")); 
		Integer clastatus =Integer.valueOf(req.getParameter("clastatus"));
		String claintroduction = req.getParameter("claintroduction"); 
		
		//做時間處理=>符合localdatetime格式
		String clatime_old = req.getParameter("clatime");
		String clatime = clatime_old.replace(" ", "T");
		
		String clastrtime_old = req.getParameter("clastrtime");
		String clastrtime = clastrtime_old.replace(" ", "T");
		
		String clafintime_old = req.getParameter("clafintime");
		String clafintime = clafintime_old.replace(" ", "T");
		
		Integer clapeople = Integer.valueOf(3);	//抓報名表的人數 不會顯示在新增表上
		
		ClassIfmVO classIfmVO = new ClassIfmVO();
		
		classIfmVO.setClaid(claid);
		classIfmVO.setThrid(thrid);
		classIfmVO.setClaTagid(clatagid);
		classIfmVO.setClaTitle(clatitle);
		classIfmVO.setClaIntroduction(claintroduction);
		classIfmVO.setClaTime(LocalDateTime.parse(clatime));
		classIfmVO.setClaPrice(claprice);
		classIfmVO.setClaPeopleMax(clapeoplemax);
		classIfmVO.setClaPeopleMin(clapeoplemin);
		classIfmVO.setClaPeople(clapeople);
		classIfmVO.setClaStatus(clastatus);
		classIfmVO.setClaStrTime(LocalDateTime.parse(clastrtime));
		classIfmVO.setClaFinTime(LocalDateTime.parse(clafintime));
		
		ClassIfmService classifmSvr = new ClassIfmService();
		classIfmVO=classifmSvr.updateClassIfm(claid,thrid, clatagid, clatitle, claintroduction, LocalDateTime.parse(clatime), claprice, clapeoplemax, clapeoplemin, clapeople, clastatus, LocalDateTime.parse(clastrtime), LocalDateTime.parse(clafintime));
		req.setAttribute("classIfmVO", classIfmVO);
		//跳轉道單查詢網頁
		String url ="/backend/classifm/listOneClassIfm.jsp";
		RequestDispatcher getOneClassIfm = req.getRequestDispatcher(url);
		getOneClassIfm.forward(req, resp);
		}
		

		
	}
}
