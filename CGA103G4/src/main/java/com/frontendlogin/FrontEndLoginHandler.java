package com.frontendlogin;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.swing.JOptionPane;

import com.member.model.MemberJDBCDAO;
import com.member.model.MemberVO;

import javax.servlet.annotation.WebServlet;



@WebServlet("/front-end/member/frontendloginhandler")
public class FrontEndLoginHandler extends HttpServlet {
	
	
	private static final long serialVersionUID = 1L;

	// 【檢查使用者輸入的帳號(account) 密碼(password)是否有效】
	// 【實際上應至資料庫搜尋比對】
	protected boolean allowUser(String account, String password) {
		
		MemberJDBCDAO dao = new MemberJDBCDAO();
		List<MemberVO> list = dao.getAll();
//		System.out.println(list);
		for (MemberVO aMember : list) {
//			System.out.print(aMember.getMemAccount() + ",");
//			String acc = aMember.getMemAccount();
//			System.out.print(acc);
			if ((aMember.getMemAccount().equals(account) && aMember.getMemPassword().equals(password)) || 
				(aMember.getMemEmail().equals(account) && aMember.getMemPassword().equals(password)))
				return true;
			
		}
		
		if ("tomcat".equals(account) && "tomcat".equals(password))
			return true;
		else
			return false;
	}
	
	protected Integer getid(String account, String password) {
		
		MemberJDBCDAO dao = new MemberJDBCDAO();
		List<MemberVO> list = dao.getAll();
		for (MemberVO aMember : list) {
			
			if ((aMember.getMemAccount().equals(account) && aMember.getMemPassword().equals(password)))
				return aMember.getMemid();
		}
		return null;
		
	}
	protected Integer getmemStatus(String account, String password) {
		
		MemberJDBCDAO dao = new MemberJDBCDAO();
		List<MemberVO> list = dao.getAll();
		for (MemberVO aMember : list) {
			
			if ((aMember.getMemAccount().equals(account) && aMember.getMemPassword().equals(password)))
				return aMember.getMemStatus();
		}
		return null;
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		PrintWriter out = res.getWriter();

		// 【取得使用者 帳號(account) 密碼(password)】
		String account = req.getParameter("account");
		String password = req.getParameter("password");

		// 【檢查該帳號 , 密碼是否有效】
		if (!allowUser(account, password)) { // 【帳號 , 密碼無效時】
			
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			errorMsgs.add("無效的帳號或密碼!");
			if (!errorMsgs.isEmpty()) {
				MemberVO memberVO = new MemberVO();
				req.setAttribute("memberVO", memberVO);
				RequestDispatcher failureView = req
						.getRequestDispatcher("/front-end/member/frontEndLogin.jsp");
				failureView.forward(req, res);
				return;
			}
			
			


		} else { // 【帳號 , 密碼有效時, 才做以下工作】
			HttpSession session = req.getSession();
			session.setAttribute("account", account); // *工作1: 才在session內做已經登入過的標識
			session.setAttribute("id", getid(account, password)); 
			
			if(getmemStatus(account, password) == 0) {
			try {
				String location = (String) session.getAttribute("location");
				if (location != null) {
					session.removeAttribute("location"); // *工作2: 看看有無來源網頁 (-->如有來源網頁:則重導至來源網頁)
					res.sendRedirect(location);
					return;
				}
			} catch (Exception ignored) {
			}

			res.sendRedirect(req.getContextPath() + "/front-end/index-front.jsp"); // *工作3: (-->如無來源網頁:則重導至login_success.jsp)
		} else {
			res.sendRedirect(req.getContextPath() + "/front-end/member/frontEndLogin.jsp");
			Object[] options = { "確認"}; 
			JOptionPane.showOptionDialog(null, "您已被停權!故無法登入!有任何疑問請洽客服", "警告",  JOptionPane.DEFAULT_OPTION, 
					JOptionPane.WARNING_MESSAGE,  null, options, options[0]); 
		} 
			
		}
	}
}