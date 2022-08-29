package com.recipe.model;

import static com.util.Common_22.PASSWORD;
import static com.util.Common_22.URL;
import static com.util.Common_22.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RecipeJDBCDAO implements RecipeDAO_interface{
	private static final String INSERT = 
			"insert into recipe (memid, recontext) values (?, ?)";
	private static final String UPDATE = 
			"update recipe set memid = ?, recontext = ? where reid = ?";
	private static final String SELECT_ALL = 
			"select reid, memid, recontext, reStime, reLtime from recipe order by reid";
	private static final String SELECT_ONE = 
			"select reid, memid, recontext, reStime, reLtime from recipe where reid = ?";
	@Override
	public void insert(RecipeVO recipeVO) {
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = con.prepareStatement(INSERT);	
		){
			ps.setInt(1, recipeVO.getMemid());
			ps.setString(2, recipeVO.getReContext());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void update(RecipeVO recipeVO) {
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = con.prepareStatement(UPDATE);	
		){
			ps.setInt(1, recipeVO.getMemid());
			ps.setString(2, recipeVO.getReContext());
			ps.setInt(3, recipeVO.getReid());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<RecipeVO> selectAll() {
		List<RecipeVO> list = new ArrayList<RecipeVO>();
		RecipeVO recipeVO = null;
		
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = con.prepareStatement(SELECT_ALL);	
		){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				recipeVO = new RecipeVO();
				
				recipeVO.setReid(rs.getInt(1));
				recipeVO.setMemid(rs.getInt(2));
				recipeVO.setReContext(rs.getString(3));
				recipeVO.setReSTime(rs.getObject("reStime", LocalDateTime.class));
				recipeVO.setReLTime(rs.getObject("reLtime", LocalDateTime.class));
				
				
				list.add(recipeVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public RecipeVO findByPrimaryKey(Integer reid) {
		RecipeVO recipeVO = null;
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = con.prepareStatement(SELECT_ONE);	
		){
			ps.setInt(1, reid);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				recipeVO = new RecipeVO();
				recipeVO.setReid(rs.getInt(1));
				recipeVO.setMemid(rs.getInt(2));
				recipeVO.setReContext(rs.getString(3));
				recipeVO.setReSTime(rs.getObject("reStime", LocalDateTime.class));
				recipeVO.setReLTime(rs.getObject("reLtime", LocalDateTime.class));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recipeVO;
	}
	
	
}
