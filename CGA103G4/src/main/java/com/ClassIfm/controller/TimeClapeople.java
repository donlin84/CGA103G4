//package com.ClassIfm.controller;
//
//import java.io.IOException;
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
//import org.apache.tomcat.jni.Time;
//
//import com.ClassIfm.model.ClassIfmService;
//import com.ClassIfm.model.ClassIfmVO;
//import com.registtrationform.model.RegisttrationFormService;
//
////@WebServlet("/time_clapeople")
//public class TimeClapeople extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//       
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doPost(request, response);
//	}
//	
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//		
//	}
//	@Override
//	public void init() throws ServletException {
//
//		Timer timer = new Timer();
//		
//		TimerTask task = new TimerTask() {
//
//			@Override
//			public void run() {
//				ClassIfmService classIfmService = new ClassIfmService();
//				List<ClassIfmVO> list=classIfmService.getAll();
//				
//				RegisttrationFormService registtrationformService = new RegisttrationFormService();
//				for(ClassIfmVO c : list) {
//					//classifm_people.getClaPeople() => 這是以抓出的人數總數
//					//c.getClaid() => 這是claid
//					ClassIfmVO classifm_people = new ClassIfmVO();
//					classifm_people.setClaPeople(registtrationformService.getConutPeople(c.getClaid()));
//					
//					classIfmService.update_clapeople(classifm_people.getClaPeople(), c.getClaid());
//					
//					System.out.println("c.getClaid()="+c.getClaid());
//				}
//				
//			}
//			
//		};
//		
//		//每十秒更新一次
//		timer.schedule(task,5000,100000);	//地3個參數控制地二次後每幾秒一次
//	}
//
//}
