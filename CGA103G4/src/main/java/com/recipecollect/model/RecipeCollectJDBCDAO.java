package com.recipecollect.model;

import static com.util.Common_22.PASSWORD;
import static com.util.Common_22.URL;
import static com.util.Common_22.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipeCollectJDBCDAO implements RecipeCollectDAO_interface{
	private static final String INSERT = 
			"insert into recipecollect (reid, memid) values (?, ?)";
	private static final String UPDATE = 
			"update recipecollect set reid = ? where memid = ?";
	private static final String DELETE = 
			"delete from recipecollect where reid = ? && memid = ?";
	private static final String SELECT_ALL = 
			"select reid, memid from recipecollect order by memid";
	private static final String SELECT_ONE = 
			"select reid, memid from recipecollect where reid = ? && memid = ?";
	@Override
	public void insert(RecipeCollectVO recipeCollectVO) {
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = con.prepareStatement(INSERT);	
		){
			ps.setInt(1, recipeCollectVO.getReid());
			ps.setInt(2, recipeCollectVO.getMemid());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void update(RecipeCollectVO recipeCollectVO) {
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = con.prepareStatement(UPDATE)	
		){
			ps.setInt(1, recipeCollectVO.getReid());
			ps.setInt(2, recipeCollectVO.getMemid());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void delete(Integer reid, Integer memid) {
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = con.prepareStatement(DELETE);	
		){
			ps.setInt(1, reid);
			ps.setInt(2, memid);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<RecipeCollectVO> selectAll() {
		List<RecipeCollectVO> list = new ArrayList<RecipeCollectVO>();
		RecipeCollectVO recipeCollectVO = null;
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = con.prepareStatement(SELECT_ALL);	
		){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				recipeCollectVO = new RecipeCollectVO();
				recipeCollectVO.setReid(rs.getInt(1));
				recipeCollectVO.setMemid(rs.getInt(2));
				
				list.add(recipeCollectVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public RecipeCollectVO findByPrimaryKey(Integer reid, Integer memid) {
		RecipeCollectVO recipeCollectVO = null;
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = con.prepareStatement(SELECT_ONE);	
		){
			ps.setInt(1, reid);
			ps.setInt(2, memid);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				recipeCollectVO = new RecipeCollectVO();
				recipeCollectVO.setReid(rs.getInt(1));
				recipeCollectVO.setMemid(rs.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recipeCollectVO;
	}
}
