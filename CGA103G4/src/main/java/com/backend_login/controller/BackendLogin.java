package com.backend_login.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/BackendLogin")
public class BackendLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		//員工登入
		if("employee".equals(req.getParameter("idname"))) {
			
			String employeename = req.getParameter("employeename");
			String employeepassword = req.getParameter("employeepassword");
		}
		
		
		
		//私廚登入
		if("chef".equals(req.getParameter("idname"))) {
				
			String employeename = req.getParameter("employeename");
			String employeepassword = req.getParameter("employeepassword");
		}
	}

}
