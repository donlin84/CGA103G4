package com.cartdetail.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CartDetailDAO implements CartDetailDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/cga103g4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT = "insert into cartdetail (memid, pdid, pdnumber) values (?, ?, ?)";
	private static final String UPDATE = "update cartdetail set pdnumber = ? where memid = ? && pdid = ?";
	private static final String SELECT_ALL = "select memid, pdid, pdnumber from cartdetail order by memid";
	private static final String SELECT_ONE = "select memid, pdid, pdnumber from cartdetail where memid = ? && pdid = ?";
	private static final String DELETE = "delete from cartdetail where memid = ? && pdid = ?";

	@Override
	public void insert(CartDetailVO cartDetailVO) {
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(INSERT);) {
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
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(UPDATE);) {
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

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(SELECT_ALL);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
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
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(SELECT_ONE);) {
			ps.setInt(1, memid);
			ps.setInt(2, pdid);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				cartDetailVO = new CartDetailVO();
				cartDetailVO.setMemid(rs.getInt(1));
				cartDetailVO.setPdid(rs.getInt(2));
				cartDetailVO.setPdNumber(rs.getInt(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void delete(Integer memid, Integer pdid) {
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(DELETE);) {
			ps.setInt(1, memid);
			ps.setInt(2, pdid);

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
