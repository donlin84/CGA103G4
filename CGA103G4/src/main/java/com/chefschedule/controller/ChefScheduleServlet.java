package com.chefschedule.controller;

import java.io.IOException;
import java.sql.Date;

import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import static com.util.JSONTrans.*;
import com.chefschedule.model.ChefScheduleService;
import com.chefschedule.model.ChefScheduleVO;


@WebServlet("/back-end/chefSchedule/chefsch.do")
public class ChefScheduleServlet extends HttpServlet {
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
			Integer chefid = Integer.valueOf(req.getParameter("chefid"));
//			HttpSession session = req.getSession();
//			session.setAttribute("chefid", "302");
//			try {
//				chef = Integer.valueOf((String) session.getAttribute("chefid"));
//			} catch (Exception e) {
//				res.getWriter().print("noInfo");
//				return;
//			}
			JSONArray jsons = new JSONArray();

			ChefScheduleService chefschSvc = new ChefScheduleService();

			List<ChefScheduleVO> list = chefschSvc.getAllById(chefid);

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

		// Fetch 刪除一筆班次
		if ("deleteOne".equals(action)) {
			ChefScheduleVO chefScheduleVO = json2Pojo(req, ChefScheduleVO.class);
			ChefScheduleService chefschSvc = new ChefScheduleService();
			Integer chefid = chefScheduleVO.getChefid();
			Date schDate = chefScheduleVO.getSchDate();
			chefschSvc.deleteAnnouncement(chefid, schDate);
		}
		
		//fetch 新增一筆班次
		if ("addOne".equals(action)) {
			ChefScheduleVO chefScheduleVO = json2Pojo(req, ChefScheduleVO.class);
			ChefScheduleService chefschSvc = new ChefScheduleService();
			Integer chefid = chefScheduleVO.getChefid();
			Date schDate = chefScheduleVO.getSchDate();
			Integer schTime = chefScheduleVO.getSchTime();
			
			chefschSvc.addchefChefSchedule(chefid, schDate, schTime);
		}
	}

}
