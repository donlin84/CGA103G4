package com.orderdetail.model;

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

public class OrderDetailDAO implements OrderDetailDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/cga103g4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT = "insert into orderdetail (ordid, pdid, detailNumber, detailPrice, detailGoodPrice) "
			+ "values (?, ?, ?, ?, ?)";
	private static final String UPDATE = "update orderdetail set detailNumber = ?, detailPrice = ?, detailGoodPrice = ? "
			+ "where ordid = ? && pdid = ?";
	private static final String SELECT_ALL = "select ordid, pdid, detailNumber, detailPrice, detailGoodPrice from orderdetail "
			+ "order by ordid";
	private static final String SELECT_ONE = "select ordid, pdid, detailNumber, detailPrice, detailGoodPrice from orderdetail "
			+ "where ordid = ? && pdid = ?";
	
//	========================冠銓新增=====================
	private static final String SELECT_All_By_ORDID = "select ordid, pdid, detailNumber, detailPrice, detailGoodPrice from orderdetail "
			+ "where ordid = ?";

	private static final String GET_ONE_ORDERDETAIL_BY_ORDID = "select ordid, pdid, detailNumber, detailPrice, detailGoodPrice from orderdetail where ordid = ?";
	
	private static final String UPDATE_PD_NUMBER = "update orderdetail set detailNumber = ? where ordid = ? && pdid = ?";
//	========================亦翔新增=====================
	
	private static final String SELECT_ONES = "select ordid, pdid, detailNumber, detailPrice, detailGoodPrice from orderdetail "
			+ "where ordid = ?";

	@Override
	public void insert(OrderDetailVO orderDetailVO) {
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(INSERT);) {
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
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(UPDATE);){
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
		
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(SELECT_ALL);){
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
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(SELECT_ONE);){
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

//	========================冠銓新增=====================
	
	@Override
	public List<OrderDetailVO> selectAllByOrdid(Integer ordid) {
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		OrderDetailVO orderDetailVO = null;
		
		try (Connection con = ds.getConnection(); 
				PreparedStatement ps = con.prepareStatement(SELECT_All_By_ORDID)){
				ps.setInt(1, ordid);
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
	public void updatePdNumber(OrderDetailVO orderDetailVO) {
		
	
		try (
			Connection con = ds.getConnection(); 
			PreparedStatement ps = con.prepareStatement(UPDATE_PD_NUMBER);
				) {
			
			ps.setInt(1, orderDetailVO.getDetailNumber());
			ps.setInt(2, orderDetailVO.getOrdid());
			ps.setInt(3, orderDetailVO.getPdid());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	@Override
	public OrderDetailVO getOneOrderDetailByOrdid(Integer ordid) {
		OrderDetailVO orderDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_ORDERDETAIL_BY_ORDID);

			pstmt.setInt(1, ordid);

			rs = pstmt.executeQuery();
			while(rs.next()) {
				orderDetailVO = new OrderDetailVO();
//				
				orderDetailVO.setOrdid(rs.getInt("ordid"));
				orderDetailVO.setPdid(rs.getInt("pdid"));
				orderDetailVO.setDetailNumber(rs.getInt("detailNumber"));
				orderDetailVO.setDetailPrice(rs.getInt("detailPrice"));
				orderDetailVO.setDetailGoodPrice(rs.getInt("detailGoodPrice"));

			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return orderDetailVO;
	}
	
//	========================亦翔新增=====================
	
	@Override
	public void insert2(OrderDetailVO orderDetailVO, Connection con) {
		PreparedStatement ps = null;
		try {

			ps = con.prepareStatement(INSERT);

			ps.setInt(1, orderDetailVO.getOrdid());
			ps.setInt(2, orderDetailVO.getPdid());
			ps.setInt(3, orderDetailVO.getDetailNumber());
			ps.setInt(4, orderDetailVO.getDetailPrice());
			ps.setInt(5, orderDetailVO.getDetailGoodPrice());

			ps.executeUpdate();

		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-訂單明細");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}
	
	@Override
	public List<OrderDetailVO> findByPrimaryKey(Integer ordid) {
		OrderDetailVO orderDetailVO = null;
		List<OrderDetailVO> list = new ArrayList<OrderDetailVO>();
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(SELECT_ONES);) {
			ps.setInt(1, ordid);
//			ps.setInt(2, pdid);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
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
}
