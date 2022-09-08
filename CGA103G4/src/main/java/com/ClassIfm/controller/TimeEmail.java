//package com.ClassIfm.controller;
//
//import java.io.IOException;
//import java.time.format.DateTimeFormatter;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.Timer;
//import java.util.TimerTask;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.ClassIfm.model.ClassIfmService;
//import com.ClassIfm.model.ClassIfmVO;
//import com.member.model.MemberService;
//import com.member.model.MemberVO;
//import com.registtrationform.model.RegisttrationFormService;
//import com.registtrationform.model.RegisttrationFormVO;
//
////@WebServlet("/TimeEmail")
//public class TimeEmail extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doPost(request, response);
//	}
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//	}
//	
////		24 * 60 * 60 * 1000
//	private static final long PERIOD_DAY = 5000;
//	@Override
//	public void init() throws ServletException {
//		Timer timer = new Timer();
//		TimerTask task = new TimerTask() {
//
//			@Override
//			public void run() {
//				//抓出課程為狀態為3的
//				ClassIfmService claSrv = new ClassIfmService();
//				List<ClassIfmVO> list=claSrv.timer_getcancel();
//				//c.getClaid()課程狀態為3(已取消)的claid
//				for(ClassIfmVO c : list) {
//					
//					RegisttrationFormService regSrv = new RegisttrationFormService();
//					List<RegisttrationFormVO> list1=regSrv.timer_getmemid(c.getClaid());
//					//reg.getMemid()抓出報名到的會員id
//					for(RegisttrationFormVO reg : list1) {
//						//在用會員id抓出email
//						MemberService memSrv = new MemberService();
//						MemberVO memberVO=memSrv.getOneMember(reg.getMemid());
//						System.out.println(memberVO.getMemEmail());
//						//memberVO.getMemEmail()為會員的email
//						System.out.println(memberVO.getMemName());
//						//開始寄email
//						String to = memberVO.getMemEmail();
//					      
//					      String subject = "seefood課程通知";
//					      
//					      
//					      DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
//					      String ch_name = memberVO.getMemName();
//					      String claname=c.getClaTitle();
//					      String clatime=df.format(c.getClaTime());
//					      
//					      String messageText = "您好 不好意思打擾您 " + ch_name +" 貴賓"+"\n"+"由於您你所報名 "+clatime+"  "+claname
//					    		  +"的課程 因為人數不足開課標準"+"\n"+" 在這邊通知您課程已取消 造成你的困擾真的很不好意思"+"\n"+"更多的課程資訊可以在參考seefood官方網站 謝謝您!"; 
//					       
//					      PreTimer mailService = new PreTimer();
//					      mailService.sendMail(to, subject, messageText);
//						
//					}
//					//並把課程狀態改成4(取消已通知)
//					claSrv.update_clastatus(c.getClaid());
//				}
//
//				
//			}
//			
//		};
//		Calendar date = Calendar.getInstance();
//		date.set(Calendar.HOUR_OF_DAY, 0); // 每天 凌晨12点 
//		date.set(Calendar.MINUTE, 0);
//		date.set(Calendar.SECOND, 0);
//		
//		timer.schedule(task, date.getTime(),PERIOD_DAY);
//	}
//	
//	
//
//}
