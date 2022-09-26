package com.memberservice.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.thymeleaf.standard.expression.Each;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.websocketchat.jedis.ChatbotMessage;
import com.memberservice.model.ChatMessage;
import com.memberservice.model.State;


@WebServlet("/front-end/memberservice/Chatbot.do")
public class Chatbot extends HttpServlet{                               
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		HttpSession session = req.getSession();
		req.setCharacterEncoding("UTF-8");
		res.setCharacterEncoding("UTF-8");
		
		Gson gson = new Gson();
		JsonObject jsonObject = gson.fromJson(req.getReader(), JsonObject.class);
		System.out.println(jsonObject);
		String message = jsonObject.get("message").getAsString();
		String sender = jsonObject.get("sender").getAsString();
		String receiver = jsonObject.get("receiver").getAsString();
		
		jsonObject.addProperty("sender",receiver);
		jsonObject.addProperty("receiver",sender);
		
		if ("help".equals(message) ) {
			Set set =  ChatbotMessage.getAllKey(1);
		
			StringBuilder SB = new StringBuilder();
			SB.append("關鍵字列表:<br>");
			for(Object key : set) {
				SB.append(key.toString());
				SB.append("<br>");
			}
			jsonObject.addProperty("message",SB.toString());
			
		}else {
			if (ChatbotMessage.sisKeyword(message,1) > 0) {
				String answer = ChatbotMessage.getKeyword(message, 1);
				jsonObject.addProperty("message",answer);
			
			}else {
				jsonObject.addProperty("message","輸入help會列出關鍵字列表");
				
			}
		}
		res.getWriter().print(jsonObject);

	}

}
