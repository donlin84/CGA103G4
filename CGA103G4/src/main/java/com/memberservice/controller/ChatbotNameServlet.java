package com.memberservice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/front-end/memberservice/ChatbotNameServlet.do")
public class ChatbotNameServlet extends HttpServlet {
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String userName = req.getParameter("userName");
		
		req.setAttribute("userName", userName);
		RequestDispatcher dispatcher = null;
		dispatcher = req.getRequestDispatcher("/front-end/memberservice/chatbot.html");
		dispatcher.forward(req, res);
	}
}
