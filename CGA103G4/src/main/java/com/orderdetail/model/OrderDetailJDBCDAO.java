package com.orderdetail.model;

import static com.util.Common.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailJDBCDAO implements OrderDetailDAO_interface{
	
	private static final String INSERT = 
			"insert into orderdetail (ordid, pdid, detailNumber, detailPrice, detailGoodPrice) "
			+ "values (?, ?, ?, ?, ?)";
	private static final String UPDATE = 
			"update orderdetail set detailNumber = ?, detailPrice = ?, detailGoodPrice = ? "
			+ "where ordid = ? && pdid = ?";
	private static final String SELECT_ALL = 
			"select ordid, pdid, detailNumber, detailPrice, detailGoodPrice from orderdetail "
			+ "order by ordid";
	private static final String SELECT_ONE = 
			"select ordid, pdid, detailNumber, detailPrice, detailGoodPrice from orderdetail "
			+ "where ordid = ? && pdid = ?";

	@Override
	public void insert(OrderDetailVO orderDetailVO) {
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = con.prepareStatement(INSERT)
		){
			ps.setInt(1, orderDetailVO.getOrdid());
			ps.setInt(2, orderDetailVO.getPdid());
			ps.setInt(3, orderDetailVO.getDetailNumber());
			ps.setInt(4, orderDetailVO.getDetailPrice());
			ps.setInt(5, orderDetailVO.getDetailGoodPrice());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(OrderDetailVO orderDetailVO) {
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = con.prepareStatement(UPDATE);	
		){
			ps.setInt(1, orderDetailVO.getDetailNumber());
			ps.setInt(2, orderDetailVO.getDetailPrice());
			ps.setInt(3, orderDetailVO.getDetailGoodPrice());
			ps.setInt(4, orderDetailVO.getOrdid());
			ps.setInt(5, orderDetailVO.getPdid());
			
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<OrderDetailVO> selectAll() {
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		OrderDetailVO orderDetailVO = null;
		
		try (
			Connection con = DriverManager.getConnection(SELECT_ALL);
			PreparedStatement ps = con.prepareStatement(SELECT_ALL);
		){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrdid(rs.getInt(1));
				orderDetailVO.setPdid(rs.getInt(2));
				orderDetailVO.setDetailNumber(rs.getInt(3));
				orderDetailVO.setDetailPrice(rs.getInt(4));
				orderDetailVO.setDetailGoodPrice(rs.getInt(5));
				
				list.add(orderDetailVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public OrderDetailVO findByPrimaryKey(Integer ordid, Integer pdid) {
		OrderDetailVO orderDetailVO = null;
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = con.prepareStatement(SELECT_ONE);	
		){
			ps.setInt(1, ordid);
			ps.setInt(2, pdid);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				orderDetailVO = new OrderDetailVO();
				orderDetailVO.setOrdid(rs.getInt(1));
				orderDetailVO.setPdid(rs.getInt(2));
				orderDetailVO.setDetailNumber(rs.getInt(3));
				orderDetailVO.setDetailPrice(rs.getInt(4));
				orderDetailVO.setDetailGoodPrice(rs.getInt(5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return orderDetailVO;
	}

	@Override
	public List<OrderDetailVO> selectAllByOrdid(Integer ordid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OrderDetailVO getOneOrderDetailByOrdid(Integer ordid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updatePdNumber(OrderDetailVO orderDetailVO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insert2(OrderDetailVO orderDetailVO, Connection con) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<OrderDetailVO> findByPrimaryKey(Integer ordid) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
