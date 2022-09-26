package com.websocketchat.jedis;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class ChatbotMessage {
	// 此範例key的設計為(發送者名稱:接收者名稱)，實際應採用(發送者會員編號:接收者會員編號)

	private static JedisPool pool = JedisPoolUtil.getJedisPool();

	public static String getKeyword(String keyWord,int db) { 
		Jedis jedis = pool.getResource();
		jedis.select(db);
		String answer = jedis.srandmember(keyWord);
		jedis.close();
		return answer;
	}

	public static void addKeyword(String keyWord,String message,int db) {
		Jedis jedis = pool.getResource();
		jedis.select(db);
		jedis.sadd(keyWord, message);
		jedis.close();
	}
	
	public static Long sisKeyword(String keyWord,int db) {
		Jedis jedis = pool.getResource();
		jedis.select(db);
		Long number = jedis.scard(keyWord);
		jedis.close();	
		return number;
	}
	
	public static void updateKeyword(String keyWord,String message,int db) {
		Jedis jedis = pool.getResource();
		jedis.select(db);
		jedis.spop(keyWord);
		jedis.sadd(keyWord, message);
		jedis.close();	
	}
	public static Set getAllKey(int db) {
		Jedis jedis = pool.getResource();
		jedis.select(db);
		Set<String> keySet = jedis.keys("*");
		jedis.close();	
		return keySet;
	}
	public static void deleteKeyword(String keyWord,int db) {
		Jedis jedis = pool.getResource();
		jedis.select(db);
		String answer = jedis.srandmember(keyWord);
		jedis.srem(keyWord,answer);
		jedis.close();			
	}
	
	public static JSONObject getAllkey_value(int db) {
		Jedis jedis = pool.getResource();
		jedis.select(db);
		Set<String> keySet = jedis.keys("*");
		JSONObject json = new JSONObject();
		for (String s :  keySet) {
			try {
				json.put(s,jedis.srandmember(s));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		jedis.close();	
		return json;
	}	
}
