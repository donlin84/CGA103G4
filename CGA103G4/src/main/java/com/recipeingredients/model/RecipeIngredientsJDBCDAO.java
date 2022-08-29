package com.recipeingredients.model;

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

public class RecipeIngredientsJDBCDAO implements RecipeIngredientsDAO_interface{
	private static final String INSERT = 
			"insert into recipeingredients (reid, pdid) values (?, ?)";
	private static final String UPDATE = 
			"update recipeingredients set pdid = ? where reid = ?";
	private static final String DELETE = 
			"delete from recipeingredients where reid = ? && pdid = ?";
	private static final String SELECT_ALL = 
			"select reid, pdid from recipeingredients order by reid";
	private static final String SELECT_ONE = 
			"select reid, pdid from recipeingredients where reid = ? && pdid = ?";
	@Override
	public void insert(RecipeIngredientsVO recipeIngredientsVO) {
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = con.prepareStatement(INSERT);	
		){
			ps.setInt(1, recipeIngredientsVO.getReid());
			ps.setInt(2, recipeIngredientsVO.getPdid());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void update(RecipeIngredientsVO recipeIngredientsVO) {
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = con.prepareStatement(UPDATE);	
		){
			ps.setInt(1, recipeIngredientsVO.getPdid());
			ps.setInt(2, recipeIngredientsVO.getReid());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<RecipeIngredientsVO> selectAll() {
		List<RecipeIngredientsVO> list = new ArrayList<RecipeIngredientsVO>();
		RecipeIngredientsVO recipeIngredientsVO = null;
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = con.prepareStatement(SELECT_ALL);	
		){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				recipeIngredientsVO = new RecipeIngredientsVO();
				recipeIngredientsVO.setReid(rs.getInt(1));
				recipeIngredientsVO.setPdid(rs.getInt(2));
				
				list.add(recipeIngredientsVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public void delete(Integer reid, Integer pdid) {
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = con.prepareStatement(DELETE);	
		){
			ps.setInt(1, reid);
			ps.setInt(2, pdid);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public RecipeIngredientsVO findByPrimaryKey(Integer reid, Integer pdid) {
		RecipeIngredientsVO recipeIngredientsVO = null;
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = con.prepareStatement(SELECT_ONE);	
		){
			ps.setInt(1, reid);
			ps.setInt(2, pdid);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				recipeIngredientsVO = new RecipeIngredientsVO();
				recipeIngredientsVO.setReid(rs.getInt(1));
				recipeIngredientsVO.setPdid(rs.getInt(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recipeIngredientsVO;
	}
}
