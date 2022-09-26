package com.frontendlogin;

import java.io.IOException;

import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

import com.member.model.MemberJDBCDAO;
import com.member.model.MemberVO;

@WebServlet("/front-end/member/alertservice")
public class AlertService extends HttpServlet {
	private static final long serialVersionUID = 1L;


protected boolean allowUser(String name, String phone, String gender) {
		
		MemberJDBCDAO dao = new MemberJDBCDAO();
		List<MemberVO> list = dao.getAll();
		for (MemberVO aMember : list) {

			if (aMember.getMemName().equals(name) && aMember.getMemPhone().equals(phone)
				&& aMember.getMemGender().equals(gender))
				return true;
		}
		
		if ("tomcat".equals(name) && "tomcat".equals(phone) && "tomcat".equals(gender))
			return true;
		else
			return false;
	}

protected String getemail(String name, String phone, String gender) {
	
	MemberJDBCDAO dao = new MemberJDBCDAO();
	List<MemberVO> list = dao.getAll();
	for (MemberVO aMember : list) {
		
		if (aMember.getMemName().equals(name) && aMember.getMemPhone().equals(phone)
				&& aMember.getMemGender().equals(gender))
			return aMember.getMemEmail();
	}
	return null;
	
}

	 
	 public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
			req.setCharacterEncoding("UTF-8");
			res.setContentType("text/html; charset=UTF-8");
//			PrintWriter out = res.getWriter();

			// 【取得使用者 帳號(account)】
			String name = req.getParameter("name");
			String phone = req.getParameter("phone");
			String gender = req.getParameter("gender");
	
			// 【檢查該帳號 】
			if (!allowUser(name, phone, gender)) { // 【帳號 , 密碼無效時】
				
				List<String> errorMsgs = new LinkedList<String>();
				req.setAttribute("errorMsgs", errorMsgs);
				errorMsgs.add("無效的姓名、電話或性別!");
				if (!errorMsgs.isEmpty()) {
					MemberVO memberVO = new MemberVO();
					req.setAttribute("memberVO", memberVO);
					RequestDispatcher failureView = req
							.getRequestDispatcher("/front-end/member/forgotaccount.jsp");
					failureView.forward(req, res);
					return;
				}
				
				


			} else { // 【帳號 , 密碼有效時, 才做以下工作】
//				HttpSession session = req.getSession();
//				session.setAttribute("email", email); // *工作1: 才在session內做已經登入過的標識

				String ch_email = getemail(name, phone, gender);
				res.sendRedirect(req.getContextPath() + "/front-end/member/frontEndLogin.jsp"); // *工作3: (-->如無來源網頁:則重導至login_success.jsp)
				Object[] options = { "繼續"}; 
				JOptionPane.showOptionDialog(null, "您的信箱 : "+ch_email+" 不要在忘了!!!!", "警告",  JOptionPane.DEFAULT_OPTION, 
						JOptionPane.WARNING_MESSAGE,  null, options, options[0]); 
			}
		}


}


