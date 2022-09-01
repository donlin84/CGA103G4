package com.backend_login.controller;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chef.model.ChefService;
import com.chef.model.ChefVO;
import com.emp.model.EmpService;
import com.emp.model.EmpVO;

@WebServlet("/BackendLogin")
public class BackendLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		
		// 員工登入
		if ("employee".equals(req.getParameter("idname"))) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String employeename = req.getParameter("employeename");
			String employeepassword = req.getParameter("employeepassword");
			
			EmpService empSrv = new EmpService();
			EmpVO empVO=empSrv.getoneaccount(employeename);
			
			if(empVO!=null) {
				if(!employeepassword.equals(empVO.getEmpPassword())) {
					errorMsgs.add("管理員您的密碼不正確");
				}
			}else {
				errorMsgs.add("管理員您輸入的帳號並未與系統連結");
			}
			
			
			//有錯誤訊息
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failview = req.getRequestDispatcher("/back-end/backend_login/BackendLogin.jsp");
				failview.forward(req, resp);
				return;
			}
		}

		// 私廚登入
		if ("chef".equals(req.getParameter("idname"))) {
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			String employeename = req.getParameter("employeename");
			String employeepassword = req.getParameter("employeepassword");
			
			ChefService chefSrv = new ChefService();
			ChefVO chefVO=chefSrv.getoneaccount(employeename);
			
			if(chefVO!=null) {
				if(!employeepassword.equals(chefVO.getChefPassword())) {
					errorMsgs.add("私廚您的密碼不正確");
				}
			}else {
				errorMsgs.add("私廚您輸入的帳號並未與系統連結");
			}
			//有錯誤訊息
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failview = req.getRequestDispatcher("/back-end/backend_login/BackendLogin.jsp");
				req.setAttribute("abc", employeename);
				failview.forward(req, resp);
				return;
			}
		}
	}

}
