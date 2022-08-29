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
//				ordersVO.setOrdHopetime(rs.getObject("ordhopetime", LocalDateTime.class));
//				if (rs.wasNull()) {
//					ordersVO.setMemCpid(null);
//				}

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
				if (rs.wasNull()) {
					ordersVO.setMemCpid(null);
				}
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

}
