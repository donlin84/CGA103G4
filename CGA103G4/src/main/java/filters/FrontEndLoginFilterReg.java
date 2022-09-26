package filters;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.member.model.MemberService;
import com.member.model.MemberVO;

public class FrontEndLoginFilterReg implements Filter {

	private FilterConfig config;

	public void init(FilterConfig config) {
		this.config = config;
	}

	public void destroy() {
		config = null;
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws ServletException, IOException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		// 【取得 session】
		HttpSession session = req.getSession();
		// 【從 session 判斷此user是否登入過】
		String account = (String)session.getAttribute("account");
		System.out.println("過濾");
		MemberService memSvc = new MemberService();
		MemberVO memVO = memSvc.getOneMemberAcc(account);
	           session.setAttribute("memVO", memVO);
		if (account == null) {
			session.setAttribute("location",req.getContextPath() + "/front-end/registtrationform/fullcalendar.html");
			res.sendRedirect(req.getContextPath() + "/front-end/member/frontEndLogin.jsp");
			return;
		} else {
			chain.doFilter(request, response);
		}
	}
}