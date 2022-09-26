package com.orders.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.cartdetail.model.CartDetailDAO;
import com.membercoupon.model.MemberCouponDAO;
import com.orderdetail.model.OrderDetailDAO;
import com.orderdetail.model.OrderDetailVO;



public class OrdersDAO implements OrdersDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/cga103g4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT = "insert into orders (memid, memcpid, ordsubtotal, ordtotal, ordrecipient, "
			+ "recphone, ordpayment, orddelivery, ordaddress) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = "update orders set memid = ?, memcpid = ?, ordsubtotal = ?, ordtotal = ?, ordstatus = ?, ordrecipient = ?, "
			+ "recphone = ?, ordpayment = ?, orddelivery = ?, ordaddress = ? where ordid = ?";
	private static final String SELECT_ALL = "select ordid, memid, memcpid, ordsubtotal, ordtotal, ordstatus, ordcreate, ordrecipient, recphone, "
			+ "ordpayment, orddelivery, ordaddress from orders order by ordid";
	private static final String SELECT_ONE = "select ordid, memid, memcpid, ordsubtotal, ordtotal, ordstatus, ordcreate, ordrecipient, recphone, "
			+ "ordpayment, orddelivery, ordaddress from orders where ordid = ?";
	
//	====================================================冠銓新增================================================================
	private static final String GET_ALL_ORDER_OF_ONE_MEM = "select ordid, memid, memcpid, ordsubtotal, ordtotal, ordstatus, ordcreate, ordrecipient, recphone, "
			+ "ordpayment, orddelivery, ordaddress from orders where memid = ?";
	
	private static final String UPDATE_ORDERINFO = "update orders set ordrecipient = ?, recphone = ?, orddelivery = ?, ordaddress = ? where ordid = ?";
	
	private static final String UPDATE_ORDERSTATUS = "update orders set ordstatus = ? where ordid = ?";
	
	
	@Override
	public void insert(OrdersVO ordersVO) {
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(INSERT);) {
			ps.setInt(1, ordersVO.getMemid());
			Integer memCpid = ordersVO.getMemCpid();
			if (memCpid == null) {
				ps.setNull(2, Types.INTEGER);
			} else {
				ps.setInt(2, memCpid);
			}
//			ps.setInt(2, ordersVO.getMemCpid());
			ps.setInt(3, ordersVO.getOrdSubTotal());
			ps.setInt(4, ordersVO.getOrdTotal());
//			ps.setInt(5, ordersVO.getOrdStatus());
			ps.setString(5, ordersVO.getOrdRecipient());
			ps.setString(6, ordersVO.getRecPhone());
			ps.setInt(7, ordersVO.getOrdPayment());
			ps.setInt(8, ordersVO.getOrdDelivery());
			ps.setString(9, ordersVO.getOrdAddress());
//			ps.setObject(10, ordersVO.getOrdHopetime());
//			Object hopeTime = ordersVO.getOrdHopetime();
//			if (hopeTime == null) {
//				ps.setNull(11, Types.JAVA_OBJECT);
//			} else {
//				ps.setObject(11, hopeTime);
//			}

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(OrdersVO ordersVO) {
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(UPDATE);) {
			
			ps.setInt(1, ordersVO.getMemid());
			Integer memCpid = ordersVO.getMemCpid();
			if (memCpid == null) {
				ps.setNull(2, Types.INTEGER);
			} else {
				ps.setInt(2, memCpid);
			}
			ps.setInt(2, ordersVO.getMemCpid());
			ps.setInt(3, ordersVO.getOrdSubTotal());
			ps.setInt(4, ordersVO.getOrdTotal());
			ps.setInt(5, ordersVO.getOrdStatus());
			ps.setString(6, ordersVO.getOrdRecipient());
			ps.setString(7, ordersVO.getRecPhone());
			ps.setInt(8, ordersVO.getOrdPayment());
			ps.setInt(9, ordersVO.getOrdDelivery());
			ps.setString(10, ordersVO.getOrdAddress());
//			Object hopeTime = ordersVO.getOrdHopetime();
//			if (hopeTime == null) {
//				ps.setNull(11, Types.JAVA_OBJECT);
//			} else {
//				ps.setObject(11, hopeTime);
//			}
			ps.setInt(11, ordersVO.getOrdid());

			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<OrdersVO> selectAll() {
		List<OrdersVO> list = new ArrayList<OrdersVO>();
		OrdersVO ordersVO = null;
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(SELECT_ALL);) {
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ordersVO = new OrdersVO();
				ordersVO.setOrdid(rs.getInt(1));
				ordersVO.setMemid(rs.getInt(2));
				ordersVO.setMemCpid(rs.getInt(3));
				ordersVO.setOrdSubTotal(rs.getInt(4));
				ordersVO.setOrdTotal(rs.getInt(5));
				ordersVO.setOrdStatus(rs.getInt(6));
				ordersVO.setOrdCreate(rs.getObject("ordcreate", LocalDateTime.class));
				ordersVO.setOrdRecipient(rs.getString(8));
				ordersVO.setRecPhone(rs.getString(9));
				ordersVO.setOrdPayment(rs.getInt(10));
				ordersVO.setOrdDelivery(rs.getInt(11));
				ordersVO.setOrdAddress(rs.getString(12));


				list.add(ordersVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	

	@Override
	public OrdersVO findByPrimaryKey(Integer ordid) {
		OrdersVO ordersVO = null;
		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(SELECT_ONE);) {
			ps.setInt(1, ordid);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				ordersVO = new OrdersVO();
				ordersVO.setOrdid(rs.getInt(1));
				ordersVO.setMemid(rs.getInt(2));
				ordersVO.setMemCpid(rs.getInt(3));
//				if (rs.wasNull()) {
//					ordersVO.setMemCpid(null);
//				}
				ordersVO.setOrdSubTotal(rs.getInt(4));
				ordersVO.setOrdTotal(rs.getInt(5));
				ordersVO.setOrdStatus(rs.getInt(6));
				ordersVO.setOrdCreate(rs.getObject("ordcreate", LocalDateTime.class));
				ordersVO.setOrdRecipient(rs.getString(8));
				ordersVO.setRecPhone(rs.getString(9));
				ordersVO.setOrdPayment(rs.getInt(10));
				ordersVO.setOrdDelivery(rs.getInt(11));
				ordersVO.setOrdAddress(rs.getString(12));
//				ordersVO.setOrdHopetime(rs.getObject("ordhopetime", LocalDateTime.class));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ordersVO;
	}
//===========================================冠銓新增=============================================
	@Override
	public List<OrdersVO> selectOneMemAll(Integer memid) {
//		List<FavoriteProductVO> list = new ArrayList<FavoriteProductVO>();
		
		List<OrdersVO> list = new ArrayList<OrdersVO>();
		OrdersVO ordersVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
		con = ds.getConnection();
		pstmt = con.prepareStatement(GET_ALL_ORDER_OF_ONE_MEM);

		pstmt.setInt(1, memid);
		
		rs = pstmt.executeQuery();
		
	
			while(rs.next()) {
			ordersVO = new OrdersVO();
			
//			ordid, memid, memcpid, ordsubtotal, ordtotal, ordstatus, ordcreate, ordrecipient, recphone, "
//					+ "ordpayment, orddelivery, ordaddress
			
			ordersVO.setOrdid(rs.getInt("ordid"));
			ordersVO.setMemid(rs.getInt("memid"));
			
			ordersVO.setMemCpid(rs.getInt("memCpid"));
			ordersVO.setOrdSubTotal(rs.getInt("ordsubtotal"));
			ordersVO.setOrdTotal(rs.getInt("ordtotal"));
			ordersVO.setOrdStatus(rs.getInt("ordstatus"));
			ordersVO.setOrdCreate(rs.getObject("ordcreate",LocalDateTime.class));
			ordersVO.setOrdRecipient(rs.getString("ordrecipient"));
			ordersVO.setRecPhone(rs.getString("recphone"));
			ordersVO.setOrdPayment(rs.getInt("ordpayment"));
			ordersVO.setOrdDelivery(rs.getInt("orddelivery"));
			ordersVO.setOrdAddress(rs.getString("ordaddress"));

			list.add(ordersVO);
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
	return list;
}


	@Override

	public void modifyOrderInfo(OrdersVO ordersVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_ORDERINFO);
//			ordrecipient = ?, recphone = ?, orddelivery = ?, ordaddress = ?
			pstmt.setString(1, ordersVO.getOrdRecipient());
			pstmt.setString(2, ordersVO.getRecPhone());
			pstmt.setInt(3, ordersVO.getOrdDelivery());
			pstmt.setString(4, ordersVO.getOrdAddress());
			pstmt.setInt(5, ordersVO.getOrdid());
			


			pstmt.executeUpdate();
		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
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
	}


	@Override
	public void cancelOrder(OrdersVO ordersVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_ORDERSTATUS);
				
			pstmt.setInt(1, ordersVO.getOrdStatus());
			pstmt.setInt(2, ordersVO.getOrdid());
				
			pstmt.executeUpdate();
			
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. "
						+ se.getMessage());
				// Clean up JDBC resources
			} finally {
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

	}

	//=====================================亦翔==============================================
	
	@Override
	public void insert2(OrdersVO ordersVO, List<OrderDetailVO> list) {
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = ds.getConnection();
			con.setAutoCommit(false);
			
			String[] cols = {"ordid"};
			
//			System.out.println(cols);

			ps = con.prepareStatement(INSERT, cols);
			
			ps.setInt(1, ordersVO.getMemid());
			Integer memCpid = ordersVO.getMemCpid();
			if (memCpid == null) {
				ps.setNull(2, Types.INTEGER);
			} else {
				ps.setInt(2, memCpid);
			}
			
			System.out.println(memCpid);
			ps.setInt(3, ordersVO.getOrdSubTotal());
			ps.setInt(4, ordersVO.getOrdTotal());
			ps.setString(5, ordersVO.getOrdRecipient());
			ps.setString(6, ordersVO.getRecPhone());
			ps.setInt(7, ordersVO.getOrdPayment());
			ps.setInt(8, ordersVO.getOrdDelivery());
			ps.setString(9, ordersVO.getOrdAddress());
			
			ps.executeUpdate();
			
			Integer memid = ordersVO.getMemid();
			
			String nextOrdid = null;
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()) {
				nextOrdid = rs.getString(1);
			}
			rs.close();
			
			OrderDetailDAO dao = new OrderDetailDAO();
			for(OrderDetailVO oneDetail : list) {
				oneDetail.setOrdid(Integer.valueOf(nextOrdid));
				dao.insert2(oneDetail, con);
			}
			
			CartDetailDAO cdao = new CartDetailDAO();
			cdao.delete2(memid, con);
			
			if(memCpid != null) {
				Integer memCpStatus = 0;
				MemberCouponDAO memCpdao = new MemberCouponDAO();
				memCpdao.transUpdate(memCpStatus, memCpid, con);
			}
			
			
			
			con.commit();
			con.setAutoCommit(true);
		}catch(SQLException e) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-訂單主檔");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. "
					+ e.getMessage());
		}
		finally {
			if (ps != null) {
				try {
					ps.close();
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
	}
}

