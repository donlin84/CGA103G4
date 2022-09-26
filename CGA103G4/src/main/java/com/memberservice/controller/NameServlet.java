package com.memberservice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/memberservice/NameServlet.do")
public class NameServlet extends HttpServlet {
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String userName = req.getParameter("userName");
		
		req.setAttribute("userName", userName);
		RequestDispatcher dispatcher = null;
		if("Service".equals(userName)) {
			dispatcher = req.getRequestDispatcher("/back-end/memberservice/chat.jsp");
		}else {
			dispatcher = req.getRequestDispatcher("/front-end/memberservice/chat.jsp");
		}
		dispatcher.forward(req, res);
	}
}
