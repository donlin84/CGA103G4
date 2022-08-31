package com.ClassTag.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ClassTag.model.ClassTagService;
import com.ClassTag.model.ClassTagVO;

import antlr.TokenWithIndex;
import javassist.expr.NewArray;
import net.bytebuddy.agent.builder.AgentBuilder.InitializationStrategy.Dispatcher;

@WebServlet("/ClassTagServlet")
public class ClassTagServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		//這是新增課程標籤
		if("add".equals(action)) {
			
			//錯誤處理
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String claTagName = req.getParameter("claTagName").trim();
			
			ClassTagService classSrv_use = new ClassTagService();
			List<ClassTagVO> list=classSrv_use.getAll();
			for(ClassTagVO clatagvo:list) {
				if(claTagName.equals(clatagvo.getClaTagName().trim())) {
					errorMsgs.add("課程標籤已經存在囉");
				}
			}
			
			if(claTagName.length()>25) {
				errorMsgs.add("字數不得超過25個字");
			}else if(claTagName == null || claTagName.trim().length() == 0) {
				errorMsgs.add("課程標籤名稱不得為空白");
			}
			
			Integer claTagStatus = Integer.valueOf(req.getParameter("claTagStatus"));
			
//			System.out.println("claTagName="+claTagName);
//			System.out.println("claTagStatus="+claTagStatus);
			//有錯誤就擋住送回addclasstag.jsp
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failview = req.getRequestDispatcher("/back-end/classtag/addClassTag.jsp");
				failview.forward(req, resp);
				return;
			}
			
			ClassTagService classSrv = new ClassTagService();
			classSrv.addClassTag(claTagName, claTagStatus);
			
			//成功的畫面
			RequestDispatcher sucessview = req.getRequestDispatcher("/back-end/classtag/listAllClassTag.jsp");
			sucessview.forward(req, resp);
			
		}
		
		//----------------------這是準備修改---------------------------
		
		if("forward_to_update".equals(action)) {
			
			Integer claTagid = Integer.valueOf(req.getParameter("claTagid"));
			
			ClassTagService classtagSrv = new ClassTagService();
			ClassTagVO classtagVO=classtagSrv.getOneClassTag(claTagid);
			
			req.setAttribute("classTagVO", classtagVO);
			
			RequestDispatcher forward_to_update = req.getRequestDispatcher("/back-end/classtag/updateClassTag.jsp");
			forward_to_update.forward(req, resp);
			
		}
		
		//----------------------這是單一查詢---------------------------
		
		if("getone_clatag".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer claTagid = Integer.valueOf(req.getParameter("claTagid"));

			
			ClassTagService classtagSrv = new ClassTagService();
			ClassTagVO classTagVO=classtagSrv.getOneClassTag(claTagid);
			if(classTagVO==null) {
				errorMsgs.add("沒有這一筆資料!");
			}
			
			
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failview = req.getRequestDispatcher("/back-end/classtag/index_ClassTag.jsp");
				failview.forward(req, resp);
				return;
			}
			req.setAttribute("classTagVO",classTagVO);
			
			RequestDispatcher get_one_sucessview = req.getRequestDispatcher("/back-end/classtag/listOneClassTag.jsp");
			get_one_sucessview.forward(req, resp);
			
		}
		
		
		//----------------------確定修改---------------------------
		
		if("update".equals(action)) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer claTagid = Integer.valueOf(req.getParameter("claTagid"));
			System.out.println("claTagid="+claTagid);
			String claTagName = req.getParameter("claTagName");
			Integer claTagStatus = Integer.valueOf(req.getParameter("claTagStatus"));
			
			//處理標題名字重複問題
			
			ClassTagService classSrv_use = new ClassTagService();
			List<ClassTagVO> list=classSrv_use.getAll();
			for(ClassTagVO clatagvo:list) {
				if(claTagName.equals(clatagvo.getClaTagName().trim())) {
					errorMsgs.add("課程標籤已經存在囉");
				}
			}
			
			if(claTagName.length()>25) {
				errorMsgs.add("字數不得超過25個字");
			}else if(claTagName == null || claTagName.trim().length() == 0) {
				errorMsgs.add("課程標籤名稱不得為空白");
			}
			
			ClassTagVO classTagVO_error = new ClassTagVO();
			classTagVO_error.setClaTagid(claTagid);
			classTagVO_error.setClaTagName(claTagName);
			classTagVO_error.setClaTagStatus(claTagStatus);
			
			if(!errorMsgs.isEmpty()) {
				req.setAttribute("classTagVO",classTagVO_error);
				RequestDispatcher failview = req.getRequestDispatcher("/back-end/classtag/updateClassTag.jsp");
				failview.forward(req, resp);
				return;
			}
			
			ClassTagService classtagSrv = new ClassTagService();
			ClassTagVO classTagVO=classtagSrv.updateClassTag(claTagName, claTagStatus,claTagid);
			
			req.setAttribute("classTagVO",classTagVO);
			
			RequestDispatcher update_sucessview = req.getRequestDispatcher("/back-end/classtag/listOneClassTag.jsp");
			update_sucessview.forward(req, resp);
			
			
		}
		
		
		
	}

}
