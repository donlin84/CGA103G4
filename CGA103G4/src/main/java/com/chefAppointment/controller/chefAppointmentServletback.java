package com.chefAppointment.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.json.JSONArray;
import org.json.JSONObject;
import static com.util.JSONTrans.*;

import com.chefappointmentform.model.ChefAppointmentFormService;
import com.chefappointmentform.model.ChefAppointmentFormVO;
import com.chefschedule.model.ChefScheduleService;
import com.chefschedule.model.ChefScheduleVO;

import com.util.MailService2;



@WebServlet("/back-end/chefAppointment/chefappb.do") 
public class chefAppointmentServletback extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		res.setContentType("application/json; charset=UTF-8");

		// Fetch 查詢該私廚全部班次
		if ("getAllById".equals(action)) {
			Integer chef = null;
			HttpSession session = req.getSession();
			session.setAttribute("chefid", "302");
			try {
				chef = Integer.valueOf((String) session.getAttribute("chefid"));
			} catch (Exception e) {
				res.getWriter().print("noInfo");
				return;
			}
			JSONArray jsons = new JSONArray();

			ChefScheduleService chefschSvc = new ChefScheduleService();

			List<ChefScheduleVO> list = chefschSvc.getAllById(chef);

			for (ChefScheduleVO cs : list) {
				JSONObject json = new JSONObject();
				if(cs.getSchTime()==1) {
					json.put("title", "午餐");				
				}else if(cs.getSchTime()==2) {
					json.put("title", "晚餐");
				}else if(cs.getSchTime()==3){
					json.put("title", "預約已額滿");
				}
				json.put("start", cs.getSchDate());
				jsons.put(json);
			}
			res.getWriter().print(jsons);

		}
		
		
		//私廚查詢修改
		if ("ChefgetOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer apmid = Integer.valueOf(req.getParameter("apmid"));

			/*************************** 2.開始查詢資料 ****************************************/
			ChefAppointmentFormService chefAppsvc = new ChefAppointmentFormService();
			ChefAppointmentFormVO chefAppVO = chefAppsvc.getOneChefApp(apmid);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("chefAppVO", chefAppVO); // 資料庫取出的empVO物件,存入req
			String url = "/back-end/chefAppointment/update.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}
		
		
		/* 私廚修改 */
		if ("updateChef".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer apmid = Integer.valueOf(req.getParameter("apmid"));

			// 預約時段
			Integer apmTime = Integer.valueOf(req.getParameter("apmTime"));

			// 預約狀態
			Integer apmStatus = Integer.valueOf(req.getParameter("apmStatus"));

			//預約價格
			Integer ampPrice = Integer.valueOf(req.getParameter("ampPrice"));
			if (ampPrice == null || ampPrice== 0) {
				errorMsgs.add("金額請勿空白或為0");
			} else if (ampPrice <1000 ) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("不能低於起始金額:1000");
			}
			// 驗證預約日期
			java.sql.Date apmDate = null;
			try {
				apmDate = java.sql.Date.valueOf(req.getParameter("apmDate").trim());
			} catch (IllegalArgumentException e) {
				apmDate = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入日期!");
			}
			
			ChefAppointmentFormVO chefAppVO = new ChefAppointmentFormVO();
			chefAppVO.setApmid(apmid);
			chefAppVO.setApmDate(apmDate);
			chefAppVO.setApmTime(apmTime);
			chefAppVO.setApmStatus(apmStatus);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("chefAppVO", chefAppVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/chefAppointment/update.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			ChefAppointmentFormService chefAppsvc = new ChefAppointmentFormService();
			chefAppVO = chefAppsvc.updteAppointmentByChef(apmid,apmDate, apmTime, ampPrice, apmStatus);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("chefAppVO", chefAppVO); // 
			String url = "/back-end/chefAppointment/select_page.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交
			successView.forward(req, res);
		}
		
		     
				
	}
	
}
