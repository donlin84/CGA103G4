package com.orders.model;

import static com.util.Common.*;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrdersJDBCDAO implements OrdersDAO_interface{
	private static final String INSERT = 
			"insert into orders (memid, memcpid, ordsubtotal, ordtotal, ordrecipient, "
			+ "recphone, ordpayment, orddelivery, ordaddress) values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE = 
			"update orders set memid = ?, memcpid = ?, ordsubtotal = ?, ordtotal = ?, ordstatus = ?, ordrecipient = ?, "
			+ "recphone = ?, ordpayment = ?, orddelivery = ?, ordaddress = ? where ordid = ?";
	private static final String SELECT_ALL = 
			"select ordid, memid, memcpid, ordsubtotal, ordtotal, ordstatus, ordcreate, ordrecipient, recphone, "
			+ "ordpayment, orddelivery, ordaddress from orders order by ordid";
	private static final String SELECT_ONE = 
			"select ordid, memid, memcpid, ordsubtotal, ordtotal, ordstatus, ordcreate, ordrecipient, recphone, "
			+ "ordpayment, orddelivery, ordaddress from orders where ordid = ?";
	@Override
	public void insert(OrdersVO ordersVO) {
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = con.prepareStatement(INSERT);
		){
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
//			ps.setObject(11, ordersVO.getOrdHopetime());
//			Object hopeTime = ordersVO.getOrdHopetime();
//			if(hopeTime == null) {
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
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = con.prepareStatement(UPDATE);
		){
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
//			if(hopeTime == null) {
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
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = con.prepareStatement(SELECT_ALL);
		){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
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
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement ps = con.prepareStatement(SELECT_ONE);	
		){
			ps.setInt(1, ordid);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
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
	
	public static void main(String[] args) {
		OrdersJDBCDAO dao = new OrdersJDBCDAO();
		
		
//		OrdersVO vo = new OrdersVO();
//		vo.setMemid(201);
//		vo.setMemCpid(1);
//		vo.setOrdSubTotal(500);
//		vo.setOrdTotal(500);
//		vo.setOrdStatus(1);
//		vo.setOrdRecipient("八德林俊傑");
//		vo.setRecPhone("0987654321");
//		vo.setOrdPayment(1);
//		vo.setOrdDelivery(2);
//		vo.setOrdAddress("桃園八德林俊傑家");
//		vo.setOrdHopetime(null);
//		
//		dao.insert(vo);
//		
		OrdersVO vo3 = dao.findByPrimaryKey(4);
		System.out.print(vo3.getOrdid() + ", ");
		System.out.print(vo3.getMemid() + ", ");
		System.out.print(vo3.getMemCpid() + ", ");
		System.out.print(vo3.getOrdSubTotal() + ", ");
		System.out.print(vo3.getOrdTotal() + ", ");
		System.out.print(vo3.getOrdStatus() + ", ");
		System.out.print(vo3.getOrdCreate() + ", ");
		System.out.print(vo3.getOrdRecipient() + ", ");
		System.out.print(vo3.getRecPhone() + ", ");
		System.out.print(vo3.getOrdPayment() + ", ");
		System.out.print(vo3.getOrdDelivery() + ", ");
		System.out.print(vo3.getOrdAddress() + ", ");
//		System.out.print(vo3.getOrdHopetime() + ", ");
		
//		List<OrdersVO> voList = dao.selectAll();
//		for(OrdersVO order : voList) {
//			System.out.print(order.getOrdid() + ", ");
//			System.out.print(order.getMemid() + ", ");
//			System.out.print(order.getMemCpid() + ", ");
//			System.out.print(order.getOrdSubTotal() + ", ");
//			System.out.print(order.getOrdTotal() + ", ");
//			System.out.print(order.getOrdStatus() + ", ");
//			System.out.print(order.getOrdCreate() + ", ");
//			System.out.print(order.getOrdRecipient() + ", ");
//			System.out.print(order.getRecPhone() + ", ");
//			System.out.print(order.getOrdPayment() + ", ");
//			System.out.print(order.getOrdDelivery() + ", ");
//			System.out.print(order.getOrdAddress() + ", ");
//			System.out.print(order.getOrdHopetime() + ", ");
//			System.out.println();
//		}
		
//		OrdersVO vo4 = new OrdersVO();
		
//		vo4.setMemid(202);
//		vo4.setMemCpid(null);
//		vo4.setOrdSubTotal(300);
//		vo4.setOrdTotal(300);
//		vo4.setOrdStatus(2);
//		vo4.setOrdRecipient("aaa");
//		vo4.setRecPhone("01234");
//		vo4.setOrdPayment(2);
//		vo4.setOrdDelivery(1);
//		vo4.setOrdAddress("aaaaa");
//		vo4.setOrdHopetime(LocalDateTime.parse("2022-08-17T20:35:32"));
//		vo4.setOrdid(4);
//		
//		dao.update(vo4);
	}
	
	
	
	
	
	
	
	
	
	
	
}
