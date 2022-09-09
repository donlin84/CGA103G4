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
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

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
import org.hibernate.engine.query.spi.FilterQueryPlan;

import com.ClassIfm.model.ClassIfmJDBCDAO;
import com.ClassIfm.model.ClassIfmService;
import com.ClassIfm.model.ClassIfmVO;
import com.ClassPicture.model.ClassPictureJDBCDAO;
import com.ClassPicture.model.ClassPictureService;
import com.ClassPicture.model.ClassPictureVO;
import com.ClassTag.model.ClassTagVO;


@WebServlet("/ClassIfmServlet")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

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
			
			ClassIfmService classSrv_use = new ClassIfmService();
			List<ClassIfmVO> list=classSrv_use.getAll();
			for(ClassIfmVO classIfmVO:list) {
				if(clatitle.equals(classIfmVO.getClaTitle().trim())) {
					errorMsgs.add("課程名稱已經存在囉");
				}
			}
			
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
				errorMsgs.add("人數上限不得為空白");
			}
			Integer clapeoplemin = null;
			try {
				clapeoplemin = Integer.valueOf(req.getParameter("clapeoplemin").trim());
			} catch (NumberFormatException e) {
				errorMsgs.add("人數下限不得為空白");
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
			}else if(claintroduction.length()>250){
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
			
			Integer clapeople = Integer.valueOf(0);	//抓報名表的人數 不會顯示在新增表上 要拿掉
			
			Part part1 = req.getPart("clapic1");
			InputStream in1 = part1.getInputStream();
//			System.out.println(in1.available());
			byte[] clapic1 = new byte[in1.available()];
			in1.read(clapic1);
			in1.close();
			
			Part part2 = req.getPart("clapic2");
			InputStream in2 = part2.getInputStream();
//			System.out.println(in2.available());
			byte[] clapic2 = new byte[in2.available()];
			in2.read(clapic2);
			in2.close();
			
			Part part3 = req.getPart("clapic3");
			InputStream in3 = part3.getInputStream();
//			System.out.println(in3.available());
			byte[] clapic3 = new byte[in3.available()];
			in3.read(clapic3);
			in3.close();
			

			
//			System.out.println("clatitle:"+clatitle);
//			System.out.println("thrid:"+thrid);
//			System.out.println("clatagid:"+clatagid);
//			System.out.println("clatime_old:"+clatime_old);
//			System.out.println("clatime:"+clatime);
//			System.out.println("claprice:"+claprice);
//			System.out.println("clapeoplemax:"+clapeoplemax);
//			System.out.println("clapeoplemin:"+clapeoplemin);
//			System.out.println("clapeople:"+clapeople);
//			System.out.println("clastatus:"+clastatus);
//			System.out.println("clastrtime_old:"+clastrtime_old);
//			System.out.println("clastrtime:"+clastrtime);
//			System.out.println("clafintime_old:"+clafintime_old);
//			System.out.println("clafintime:"+clafintime);
//			
//			System.out.println("part1:"+part1);
//			System.out.println("part2:"+part2);
//			System.out.println("part3:"+part3);
//			System.out.println("in1:"+in1);
//			System.out.println("in2:"+in2);
//			System.out.println("in3:"+in3);
//			System.out.println("clapic1:"+clapic1);
//			System.out.println("clapic2:"+clapic2);
//			System.out.println("clapic3:"+clapic3);
			
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
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/classifm/addClassIfm.jsp");
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
			String url = "/back-end/classifm/listAllClassIfm.jsp";
			RequestDispatcher insertsuccess = req.getRequestDispatcher(url);
			insertsuccess.forward(req, resp);
		}
		
//		-------------------這是單查詢-------------------
		
		if("getone".equals(req.getParameter("action"))) {

		List<String> errorMsgs = new LinkedList<String>();
		req.setAttribute("errorMsgs", errorMsgs);

		Integer claid = Integer.valueOf(req.getParameter("claid"));
			
		ClassIfmService classifmSvc = new ClassIfmService();
		ClassIfmVO classIfmVO=classifmSvc.getOneClassIfm(claid);
		if(classIfmVO==null) {
			errorMsgs.add("沒有這一筆資料!");
		}
		
		if(!errorMsgs.isEmpty()) {
			RequestDispatcher failview = req.getRequestDispatcher("/back-end/classifm/index_ClassIfm.jsp");
			failview.forward(req, resp);
			return;
		}
		req.setAttribute("classIfmVO", classIfmVO);
		//跳轉道單查詢網頁
		String url ="/back-end/classifm/listOneClassIfm.jsp";
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
		String url ="/back-end/classifm/updateClassIfm.jsp";
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
		
		ClassIfmService classSrv_use = new ClassIfmService();
		List<ClassIfmVO> list_use=classSrv_use.getAll();
		ClassIfmService claSrv = new ClassIfmService();
		ClassIfmVO clavo=claSrv.getOneClassIfm(claid);
		for(ClassIfmVO classIfmVO:list_use) {
			if(clatitle.equals(classIfmVO.getClaTitle().trim()) && !clatitle.equals(clavo.getClaTitle())) {
				
				errorMsgs.add("課程名稱已經存在囉");
			}
		}
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
			errorMsgs.add("人數上限不得為空白");
		}
		Integer clapeoplemin = null;
		try {
			clapeoplemin = Integer.valueOf(req.getParameter("clapeoplemin").trim());
		} catch (NumberFormatException e) {
			errorMsgs.add("人數下限不得為空白");
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
		}else if(claintroduction.length()>250){
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
		
		//處理圖片
		
		
		ClassPictureJDBCDAO dao = new ClassPictureJDBCDAO();
		List<ClassPictureVO> list=dao.get_clapicid(claid);
		System.out.println(list.get(0).getClaPicid());	//第一章圖片的編號
		System.out.println(list.get(1).getClaPicid());	//第二章圖片的編號
		System.out.println(list.get(2).getClaPicid());	//第三章圖片的編號
		System.out.println(claid);
		
		ClassPictureService classPictureService1 = new ClassPictureService();
		
		//抓舊的classpicvo回來
		List<ClassPictureVO> list2 = classPictureService1.getOneClassPicture(claid);
		
		Part part1 = req.getPart("clapic1");
		InputStream in1 = part1.getInputStream();
		System.out.println("in2.available()="+in1.available());
		byte[] clapic1 = new byte[in1.available()];
		in1.read(clapic1);
		in1.close();
		//如果沒更新圖片要去抓舊的圖片byte[]回來
		if(clapic1.length==0) {
			clapic1 = list2.get(0).getClaPic();
		}
		
		
		Part part2 = req.getPart("clapic2");
		InputStream in2 = part2.getInputStream();
		System.out.println("in2.available()="+in2.available());
		byte[] clapic2 = new byte[in2.available()];
		in2.read(clapic2);
		in2.close();
		//如果沒更新圖片要去抓舊的圖片byte[]回來
		if(clapic2.length==0) {
			clapic2 = list2.get(1).getClaPic();
		}
		
		Part part3 = req.getPart("clapic3");
		InputStream in3 = part3.getInputStream();
		System.out.println("in3.available()="+in3.available());
		byte[] clapic3 = new byte[in3.available()];
		in3.read(clapic3);
		in3.close();
		//如果沒更新圖片要去抓舊的圖片byte[]回來
		if(clapic3.length==0) {
			clapic3 = list2.get(2).getClaPic();
		}
		
		ClassPictureService classpicsrv1 = new ClassPictureService();
		classpicsrv1.updateClassPicture(list.get(0).getClaPicid(), claid, clapic1);
		ClassPictureService classpicsrv2 = new ClassPictureService();
		classpicsrv2.updateClassPicture(list.get(1).getClaPicid(),claid, clapic2);
		ClassPictureService classpicsrv3 = new ClassPictureService();
		classpicsrv3.updateClassPicture(list.get(2).getClaPicid(),claid, clapic3);
		
		ClassIfmVO classIfmVO = new ClassIfmVO();
		
		
		
		classIfmVO.setClaid(claid);
		classIfmVO.setThrid(thrid);
		classIfmVO.setClaTagid(clatagid);
		classIfmVO.setClaTitle(clatitle);
		classIfmVO.setClaIntroduction(claintroduction);
		classIfmVO.setClaPrice(claprice);
		classIfmVO.setClaPeopleMax(clapeoplemax);
		classIfmVO.setClaPeopleMin(clapeoplemin);
		classIfmVO.setClaPeople(clapeople);
		classIfmVO.setClaStatus(clastatus);
		try {
			classIfmVO.setClaTime(LocalDateTime.parse(clatime));
			classIfmVO.setClaStrTime(LocalDateTime.parse(clastrtime));
			classIfmVO.setClaFinTime(LocalDateTime.parse(clafintime));
		}catch(Exception e) {
			classIfmVO.setClaTime(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
			classIfmVO.setClaStrTime(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
			classIfmVO.setClaFinTime(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
		}
		
		if (!errorMsgs.isEmpty()) {
			req.setAttribute("classIfmVO", classIfmVO);
			RequestDispatcher failureView = req.getRequestDispatcher("/back-end/classifm/updateClassIfm.jsp");
			failureView.forward(req, resp);
			return;
		}
		//更新課程資訊
		ClassIfmService classifmSvr = new ClassIfmService();
		classIfmVO=classifmSvr.updateClassIfm(claid,thrid, clatagid, clatitle, claintroduction, LocalDateTime.parse(clatime), claprice, clapeoplemax, clapeoplemin, clapeople, clastatus, LocalDateTime.parse(clastrtime), LocalDateTime.parse(clafintime));
		req.setAttribute("classIfmVO", classIfmVO);
		//更新課程圖片
		
		//跳轉道單查詢網頁
		String url ="/back-end/classifm/listOneClassIfm.jsp";
		RequestDispatcher getOneClassIfm = req.getRequestDispatcher(url);
		getOneClassIfm.forward(req, resp);
		}
		
		//前端篩選功能
		if("browse".equals(req.getParameter("action"))) {
			String thr[]=req.getParameterValues("teacher");
			System.out.println("thr[]="+Arrays.toString(thr));
			String tag[]=req.getParameterValues("clatag");
			System.out.println("tag[]="+Arrays.toString(tag));
			
			Integer claprice_min = Integer.valueOf(req.getParameter("claprice_min"));
			Integer claprice_max = Integer.valueOf(req.getParameter("claprice_max"));
			
			System.out.println("claprice_min="+claprice_min);
			System.out.println("claprice_max="+claprice_max);
			
			String cla_keyword = req.getParameter("cla_keyword");
			System.out.println("cla_keyword="+cla_keyword);
			
			String thr_sentence="";
			String tag_sentence="";
			String clatagid_string="";
			String thrid_string="";
			String cla_keyword_string="";
			//教師
			if(thr!=null) {
				for(int i =0;i<thr.length;i++) {
					if(i==0) {
						thr_sentence+=(thr[i]);
					}else {
						thr_sentence+=(","+thr[i]);
					}
				}
				thrid_string=" and thrid in ("+thr_sentence+")";
			}else {
				
			}
			
			System.out.println("教師語句結果="+thr_sentence);
			
			//課程種類
			if(tag!=null) {
				for(int i =0;i<tag.length;i++) {
					if(i==0) {
						tag_sentence+=(tag[i]);
					}else {
						tag_sentence+=(","+tag[i]);
					}
				}
				clatagid_string=" and clatagid in ("+tag_sentence+")";
			}else {
				
			}
			
			System.out.println("課程種類語句結果="+tag_sentence);
			
			String claprice_string = " and claprice between "+claprice_min+" and "+claprice_max;
			
			if(cla_keyword.trim().length()!=0) {
				cla_keyword_string=" and ( clatitle like '%"+cla_keyword+"%'" +" or claIntroduction like '%"+cla_keyword+"%' ) ";
			}
			
			System.out.println("cla_keyword_string="+cla_keyword_string);
			
			String dao_string;
			dao_string="select * from classifm where 1=1"+clatagid_string+thrid_string+claprice_string+cla_keyword_string+" and claStatus = 1"+";";
			
			System.out.println("dao_string="+dao_string);
			
			ClassIfmService classifmSrv = new ClassIfmService();
			List<ClassIfmVO> list=classifmSrv.cangetall(dao_string);
			
			//保留check box勾選過的
			if(Arrays.toString(thr)!=null) {
				req.setAttribute("thr", Arrays.toString(thr));
			}
			if(Arrays.toString(tag)!=null) {
				req.setAttribute("tag", Arrays.toString(tag));
			}
			
			req.setAttribute("claprice_min", claprice_min);
			req.setAttribute("claprice_max", claprice_max);
			req.setAttribute("cangetall", list);
			//印出list
//			for (ClassIfmVO cangetall : list) {
//				System.out.print(cangetall.getClaid() + ",");
//				System.out.print(cangetall.getThrid() + ",");
//				System.out.print(cangetall.getClaTagid() + ",");
//				System.out.print(cangetall.getClaTitle() + ",");
//				System.out.print(cangetall.getClaIntroduction() + ",");
//				System.out.print(cangetall.getClaTime() + ",");
//				System.out.print(cangetall.getClaPrice()+ ",");
//				System.out.print(cangetall.getClaPeopleMax()+ ",");
//				System.out.print(cangetall.getClaPeopleMin()+ ",");
//				System.out.print(cangetall.getClaPeople()+ ",");
//				System.out.print(cangetall.getClaStatus()+ ",");
//				System.out.print(cangetall.getClaStrTime()+ ",");
//				System.out.print(cangetall.getClaFinTime()+ ",");
//				System.out.println();
//				
//				}
			
			RequestDispatcher checkbox = req.getRequestDispatcher("/front-end/classifm/classifm_browse.jsp");
			checkbox.forward(req, resp);
			
			
		}
		
		if("second_page".equals(action)) {
			//前端課程資訊
			
			//拿到?後的課程ID 
			Integer claid = Integer.valueOf(req.getParameter("claid"));
			
			//看是哪個客程送過來的請求
			System.out.println(claid);	
			
			//去查出課程資訊
			ClassIfmService claSrv = new ClassIfmService();
			ClassIfmVO classifmvo=claSrv.getOneClassIfm(claid);
			
			req.setAttribute("classifmvo", classifmvo);
			//跳轉道前端頁面
			RequestDispatcher second_page = req.getRequestDispatcher("/front-end/classifm/classifm_second_browse.jsp");
			second_page.forward(req, resp); 
		}
		
		//給後台看每個課程狀態
		if("getall_status".equals(action)) {
			Integer claStatus = Integer.valueOf(req.getParameter("clastatus"));
			
			ClassIfmService claSrv = new ClassIfmService();
			List<ClassIfmVO> list=claSrv.getAll();
			
			RequestDispatcher getallstatusview = req.getRequestDispatcher("/back-end/classifm/listAllClassIfm.jsp");
			//下架
			if(claStatus==0) {
				System.out.println("下架");
				
				req.setAttribute("getallstatus", list.stream()
						.filter(c -> c.getClaStatus()== 0)
						.collect(Collectors.toList()));
				req.setAttribute("clastatus",0);
				
				getallstatusview.forward(req, resp);
				
				
			}
			//上架
			if(claStatus==1) {
				System.out.println("上架");
				
				req.setAttribute("getallstatus", list.stream()
						.filter(c -> c.getClaStatus()== 1)
						.collect(Collectors.toList()));
				req.setAttribute("clastatus",1);
				
				getallstatusview.forward(req, resp);
			}
			//已結束
			if(claStatus==2) {
				System.out.println("已結束");
				
				req.setAttribute("getallstatus", list.stream()
						.filter(c -> c.getClaStatus()== 2)
						.collect(Collectors.toList()));
				req.setAttribute("clastatus",2);
				
				
				getallstatusview.forward(req, resp);
			}
			//取消
			if(claStatus==3) {
				System.out.println("取消");
				
				req.setAttribute("getallstatus", list.stream()
						.filter(c -> c.getClaStatus()== 3)
						.collect(Collectors.toList()));
				req.setAttribute("clastatus",3);
				
				getallstatusview.forward(req, resp);
			}
			//通知已取消
			if(claStatus==4) {
				System.out.println("通知已取消");
				
				req.setAttribute("getallstatus", list.stream()
						.filter(c -> c.getClaStatus()== 4)
						.collect(Collectors.toList()));
				req.setAttribute("clastatus",4);
				
				getallstatusview.forward(req, resp);
			}
		}
		
	}
}
