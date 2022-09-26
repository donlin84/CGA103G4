package com.memberservice.controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.websocketchat.jedis.ChatbotMessage;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.websocketchat.jedis.ChatbotMessage;
@WebServlet("/back-end/memberservice/KeywordServlet.do")
public class KeywordServlet extends HttpServlet{
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		
		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(req.getReader(), JsonObject.class);
		if ("update".equals(jsonObject.get("Type").getAsString()) ) {
			
			String keyword = jsonObject.get("keyword").getAsString();
			String answer = jsonObject.get("answer").getAsString();
			JSONObject json = new  JSONObject();
			if ( ChatbotMessage.sisKeyword(keyword,1) == 1L) {
				System.out.println("存在，更新");
				ChatbotMessage.updateKeyword(keyword, answer, 1);
				try {
					json.put("Result","更新成功");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				res.getWriter().print(json);
				
			}else{
				System.out.println("不存在，新增");
				ChatbotMessage.addKeyword(keyword, answer, 1);
				try {
					json.put("Result","新增成功");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				res.getWriter().print(json);		
				
			}
		
		}else if("getdata".equals(jsonObject.get("Type").getAsString())) {
			Set set = ChatbotMessage.getAllKey(1);
			JSONArray json = new JSONArray(set);
			System.out.println(json);
			res.getWriter().print(json);
		}else if("delete".equals(jsonObject.get("Type").getAsString())) {
			String selectkeyword = jsonObject.get("selectkeyword").getAsString();
			ChatbotMessage.deleteKeyword(selectkeyword,1);
			JSONObject json = new  JSONObject();
			try {
				json.put("Result","刪除成功");
			} catch (JSONException e) {
				e.printStackTrace();
			}
			res.getWriter().print(json);		
		
		}else if("selectAll".equals(jsonObject.get("Type").getAsString())){
			JSONObject jsonobject = ChatbotMessage.getAllkey_value(1);
			res.getWriter().print(jsonobject);
		}
		
	}

}
