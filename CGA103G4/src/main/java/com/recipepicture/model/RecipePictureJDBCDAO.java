package com.recipepicture.model;

import static com.util.Common.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecipePictureJDBCDAO implements RecipePictureDAO_interface{
	private static final String INSERT = 
			"insert into recipepicture (reid, rePic) values (?, ?)";
	private static final String UPDATE = 
			"update recipepicture set reid = ?, rePic = ? where rePicid = ?";
	private static final String DELETE = 
			"delte from recipepicture where rePicid = ?";
	private static final String SELECT_ALL = 
			"select rePicid, reid, rePic from recipepicture order by rePicid";
	private static final String SELECT_ONE = 
			"select rePicid, reid, rePic from recipepicture where rePicid = ?";
	@Override
	public void insert(RecipePictureVO recipePictureVO) {
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = con.prepareStatement(INSERT);	
		){
			ps.setInt(1, recipePictureVO.getReid());
			ps.setBytes(2, recipePictureVO.getRePic());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void update(RecipePictureVO recipePictureVO) {
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = con.prepareStatement(UPDATE);	
		){
			ps.setInt(1, recipePictureVO.getReid());
			ps.setBytes(2, recipePictureVO.getRePic());
			ps.setInt(3, recipePictureVO.getRePicid());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void delete(Integer rePicid) {
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = con.prepareStatement(DELETE);	
		){
			ps.setInt(1, rePicid);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<RecipePictureVO> selectALL() {
		List<RecipePictureVO> list = new ArrayList<RecipePictureVO>();
		RecipePictureVO recipePictureVO = null;
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = con.prepareStatement(SELECT_ALL);	
		){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				recipePictureVO = new RecipePictureVO();
				recipePictureVO.setRePicid(rs.getInt(1));
				recipePictureVO.setReid(rs.getInt(2));
				recipePictureVO.setRePic(rs.getBytes(3));
				
				list.add(recipePictureVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public RecipePictureVO findByPrimaryKey(Integer rePicid) {
		RecipePictureVO recipePictureVO = null;
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = con.prepareStatement(SELECT_ONE);	
		){
			ps.setInt(1, rePicid);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				recipePictureVO = new RecipePictureVO();
				recipePictureVO.setRePicid(rs.getInt(1));
				recipePictureVO.setReid(rs.getInt(2));
				recipePictureVO.setRePic(rs.getBytes(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return recipePictureVO;
	}
}
