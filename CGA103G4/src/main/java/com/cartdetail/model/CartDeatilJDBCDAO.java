package com.cartdetail.model;

import static com.util.Common.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CartDeatilJDBCDAO implements CartDetailDAO_interface{
	
	private static final String INSERT = 
			"insert into cartdetail (memid, pdid, pdnumber) values (?, ?, ?)";
	private static final String UPDATE = 
			"update cartdetail set pdnumber = ? where memid = ? && pdid = ?";
	private static final String SELECT_ALL = 
			"select memid, pdid, pdnumber from cartdetail order by memid";
	private static final String SELECT_ONE = 
			"select memid, pdid, pdnumber from cartdetail where memid = ? && pdid = ?";
	private static final String DELETE = 
			"delete from cartdetail where memid = ? && pdid = ?";

	@Override
	public void insert(CartDetailVO cartDetailVO) {
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = con.prepareStatement(INSERT);
		){
			ps.setInt(1, cartDetailVO.getMemid());
			ps.setInt(2, cartDetailVO.getPdid());
			ps.setInt(3, cartDetailVO.getPdNumber());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(CartDetailVO cartDetailVO) {
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = con.prepareStatement(UPDATE);
		){
			ps.setInt(1, cartDetailVO.getPdNumber());
			ps.setInt(2, cartDetailVO.getMemid());
			ps.setInt(3, cartDetailVO.getPdid());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<CartDetailVO> selectAll() {
		List<CartDetailVO> list = new ArrayList<CartDetailVO>();
		CartDetailVO cartDetailVO = null;
		
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = con.prepareStatement(SELECT_ALL);	
		){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				cartDetailVO = new CartDetailVO();
				
				cartDetailVO.setMemid(rs.getInt(1));
				cartDetailVO.setPdid(rs.getInt(2));
				cartDetailVO.setPdNumber(rs.getInt(3));
				
				list.add(cartDetailVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public CartDetailVO findByPrimaryKey(Integer memid, Integer pdid) {
		CartDetailVO cartDetailVO = null;
		try (
				Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = con.prepareStatement(SELECT_ONE);	
				){
			ps.setInt(1, memid);
			ps.setInt(2, pdid);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				cartDetailVO = new CartDetailVO();
				cartDetailVO.setMemid(rs.getInt(1));
				cartDetailVO.setPdid(rs.getInt(2));
				cartDetailVO.setPdNumber(rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cartDetailVO;
	}

	@Override
	public void delete(Integer memid, Integer pdid) {
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = con.prepareStatement(DELETE);
		){
			ps.setInt(1, memid);
			ps.setInt(2, pdid);
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<CartDetailVO> findByPrimaryKey(Integer memid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete2(Integer memid, Connection con) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void minus(CartDetailVO cartDetailVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void plus(CartDetailVO cartDetailVO) {
		// TODO Auto-generated method stub
		
	}

}
