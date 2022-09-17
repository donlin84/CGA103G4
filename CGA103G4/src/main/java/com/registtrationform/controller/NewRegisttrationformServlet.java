package com.registtrationform.controller;

import java.io.IOException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ClassIfm.model.ClassIfmService;
import com.ClassIfm.model.ClassIfmVO;
import com.member.model.MemberService;
import com.member.model.MemberVO;
import com.registtrationform.model.RegisttrationFormService;
import com.registtrationform.model.RegisttrationFormVO;


@WebServlet("/NewRegisttrationformServlet")
public class NewRegisttrationformServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");	
		
		//這是針對單一會員的修改
		if("forward_to_update".equals(action)) {
			
			Integer claid = Integer.valueOf(req.getParameter("claid"));
			Integer memid = Integer.valueOf(req.getParameter("memid"));
			
			ClassIfmService claSrv = new ClassIfmService();
			ClassIfmVO classifmvo=claSrv.getOneClassIfm(claid);
			
			RegisttrationFormService regSrv = new RegisttrationFormService();
			RegisttrationFormVO registtrationFormVO=regSrv.getOneRegisttrationForm(claid, memid);
			
			req.setAttribute("classifmvo", classifmvo);
			req.setAttribute("registtrationFormVO", registtrationFormVO);
			//跳轉道單查詢網頁
			String url ="/back-end/registtrationform/UpdateRegisttrationform.jsp";
			RequestDispatcher getOneClassIfm = req.getRequestDispatcher(url);
			getOneClassIfm.forward(req, resp);
			}
		
		if("myclassifm_cancel".equals(action)) {
			
			Integer claid = Integer.valueOf(req.getParameter("claid"));
			Integer memid = Integer.valueOf(req.getParameter("memid"));
			Integer regPeople = Integer.valueOf(req.getParameter("regPeople"));
			
			RegisttrationFormService refSrv = new RegisttrationFormService();
			refSrv.update_status(claid, memid);
			
			ClassIfmService claSrv = new ClassIfmService();
			ClassIfmVO classIfmVO=claSrv.getOneClassIfm(claid);//抓出人數
			
			claSrv.update_clapeople(classIfmVO.getClaPeople() - regPeople, claid);
			
			RequestDispatcher myclass = req.getRequestDispatcher("/back-end/classifm/MyClassIfm.jsp");
			myclass.forward(req, resp);
			
		}
		
		//真的要修改
		if("update".equals(action)) {
			
			Integer claid = Integer.valueOf(req.getParameter("claid"));
			Integer memid = Integer.valueOf(req.getParameter("memid"));
			
			
			Integer regPayment = Integer.valueOf(req.getParameter("regPayment"));
			Integer regStatus = Integer.valueOf(req.getParameter("regStatus"));
			Integer regPeople = Integer.valueOf(req.getParameter("regPeople"));
			
			
			//處理狀態改取消 課程人數直接扣掉
			if(regStatus==1) {
				ClassIfmService claSrv = new ClassIfmService();
				ClassIfmVO classIfmVO=claSrv.getOneClassIfm(claid);//抓出人數
				
				claSrv.update_clapeople(classIfmVO.getClaPeople() - regPeople, claid);
			}
			
			RegisttrationFormService regSrv = new RegisttrationFormService();
			
			//抓出舊的報名人數->再去CLASSIFM進行人數加減
			RegisttrationFormVO old_regvo=regSrv.getOneRegisttrationForm(claid, memid);
			if(regPeople > old_regvo.getRegPeople()) {
				//修改為增加人數
				//先抓出這個課程的people -> 再加上他們的結果
				ClassIfmService claSrv = new ClassIfmService();
				ClassIfmVO classIfmVO=claSrv.getOneClassIfm(claid);//抓出人數
				
				claSrv.update_clapeople(classIfmVO.getClaPeople()+(regPeople - old_regvo.getRegPeople()), claid);
			}else if(regPeople < old_regvo.getRegPeople()) {
				//修改為減少人數
				//先抓出這個課程的people -> 再減去他們的結果
				ClassIfmService claSrv = new ClassIfmService();
				ClassIfmVO classIfmVO=claSrv.getOneClassIfm(claid);//抓出人數
				
				claSrv.update_clapeople(classIfmVO.getClaPeople()-(old_regvo.getRegPeople() - regPeople), claid);
			}
			
			
			regSrv.updateRegisttrationForm(regPayment, regStatus, regPeople, claid, memid);
			
			RegisttrationFormVO regvo=regSrv.getOneRegisttrationForm(claid, memid);
			
			req.setAttribute("regvo", regvo);
			
			RequestDispatcher succse = req.getRequestDispatcher("/back-end/registtrationform/listOneRegisttrationForm.jsp");
			succse.forward(req, resp);
			
		}
		//針對提供claid跟memid的單查詢
		if("getOne_For_Display".equals(action)) {
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer claid = Integer.valueOf(req.getParameter("claid"));
			Integer memid = Integer.valueOf(req.getParameter("memid"));
			
			RegisttrationFormService regSrv = new RegisttrationFormService();
			RegisttrationFormVO regvo=regSrv.getOneRegisttrationForm(claid, memid);
			if(regvo==null) {
				errorMsgs.add("查無此資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/registtrationform/select_page.jsp");
				failureView.forward(req, resp);
				return;
			}
			req.setAttribute("regvo", regvo);
			
			RequestDispatcher succse = req.getRequestDispatcher("/back-end/registtrationform/listOneRegisttrationForm.jsp");
			succse.forward(req, resp);
			
			
		}
		
		if("getclaid_allmember".equals(action)) {
			
			Integer claid = Integer.valueOf(req.getParameter("claid"));
			ClassIfmService claSrv = new ClassIfmService();
			ClassIfmVO claifmvo=claSrv.getOneClassIfm(claid);
			
			List<MemberVO>list3= new ArrayList<MemberVO>();
			
			RegisttrationFormService regSrv = new RegisttrationFormService();
			List<RegisttrationFormVO> list=regSrv.timer_getmemid(claid);//看第幾個課程回傳報名的會員list
			
			for(RegisttrationFormVO reg : list) {//用會員list迭代
				MemberService memSrv = new MemberService();
				MemberVO membervo=memSrv.getOneMember(reg.getMemid());//透過會員id拿取那個會員整筆資料回傳vo
				
				RegisttrationFormService refVO = new RegisttrationFormService();
				RegisttrationFormVO regvo=refVO.getOneRegisttrationForm(claid, reg.getMemid());
				
				membervo.setPeople(regvo.getRegPeople());//找人數 塞進去
				membervo.setRegtime(regvo.getRegTime());//找報名時間 塞進去
				membervo.setRegpayment(regvo.getRegPayment());//付款方式 塞進去
				list3.add(membervo);//把vo加進 上面宣告的list裡 
				req.setAttribute("regall",list3);
			}
			req.setAttribute("claifmvo", claifmvo);
			
			RequestDispatcher succse = req.getRequestDispatcher("/back-end/registtrationform/getclaid_allmemid.jsp");
			succse.forward(req, resp);
			
		}
		
//		if("click_people".equals(action)) {
//			
//			Integer claid = Integer.valueOf(req.getParameter("claid"));
//			ClassIfmService claSrv = new ClassIfmService();
//			ClassIfmVO claifmvo=claSrv.getOneClassIfm(claid);
//			
//			List<MemberVO>list3= new ArrayList<MemberVO>();
//			
//			RegisttrationFormService regSrv = new RegisttrationFormService();
//			List<RegisttrationFormVO> list=regSrv.click_people(claid);//看第幾個課程回傳報名的會員list*********
//			
//			for(RegisttrationFormVO reg : list) {//用會員list迭代			<再拿出報名表狀態塞進去>
//				MemberService memSrv = new MemberService();
//				MemberVO membervo=memSrv.getOneMember(reg.getMemid());//透過會員id拿取那個會員整筆資料回傳vo
//				
//				RegisttrationFormService refVO = new RegisttrationFormService();
//				RegisttrationFormVO regvo=refVO.getOneRegisttrationForm(claid, reg.getMemid());
//				
//				membervo.setPeople(regvo.getRegPeople());//找人數 塞進去
//				membervo.setRegstatus(regvo.getRegStatus());//找狀態
//				list3.add(membervo);//把vo加進 上面宣告的list裡 
//				req.setAttribute("regall",list3);
//			}
//			req.setAttribute("claifmvo", claifmvo);
//			
//			RequestDispatcher succse = req.getRequestDispatcher("/back-end/registtrationform/click_people.jsp");
//			succse.forward(req, resp);
//			
//		}
		
		//後臺小複合查詢
		if("review_form".equals(action)) {
			
			String memname=req.getParameter("memname").trim();//用客人名字抓出會員id
			
			MemberService memberSrv = new MemberService();
			MemberVO membervo=memberSrv.othergetmemid(memname);
			
			Integer claid = Integer.valueOf(req.getParameter("claid"));
			Integer memid = membervo.getMemid();
			Integer regReview = Integer.valueOf(req.getParameter("regReview"));
			String regReviewContent=req.getParameter("regReviewContent");
			
			System.out.println("claid="+claid);
			System.out.println("memid="+memid);
			System.out.println("regReview="+regReview);
			System.out.println("regReviewContent="+regReviewContent);
			//去更新對應的 評分及評價
			RegisttrationFormService regSrv = new RegisttrationFormService();
			regSrv.update_review(regReview, regReviewContent, claid, memid);
			
			//跳轉道感謝填寫
			RequestDispatcher succse = req.getRequestDispatcher("/back-end/registtrationform/ReviewForm_page2.jsp");
			succse.forward(req, resp);
			
		}
		
		//看課程評價
		if("getreview".equals(action)) {
			
			Integer claid =Integer.valueOf(req.getParameter("claid"));
			
			RegisttrationFormService regSrv = new RegisttrationFormService();
			List<RegisttrationFormVO> list=regSrv.getAll();
			
			req.setAttribute("getreview", list.stream()
										.filter(c -> c.getClaid()==claid)
										.collect(Collectors.toList()));
			req.setAttribute("claid", claid);
			
			RequestDispatcher getreview = req.getRequestDispatcher("/back-end/registtrationform/listAllRegisttrationForm.jsp");
			getreview.forward(req, resp);
		}
		
		
		
	}
		
}


