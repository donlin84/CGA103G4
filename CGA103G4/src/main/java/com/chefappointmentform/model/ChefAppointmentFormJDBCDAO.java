package com.chefappointmentform.model;

import static com.util.Common.PASSWORD;
import static com.util.Common.URL;
import static com.util.Common.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChefAppointmentFormJDBCDAO implements ChefAppointmentFormDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";

	private static final String INSERT_STMT = "INSERT INTO ChefAppointmentForm(memid, "
			+ "chefid, apmDate, apmTime, apmPrice, apmStatus, star, comments) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT apmid, memid, chefid, apmDate, "
			+ "apmTime, apmPrice, apmStatus, star, comments " + "FROM ChefAppointmentForm order by apmid";
	private static final String GET_ONE_STMT = "SELECT apmid, memid, chefid, apmDate, "
			+ "apmTime, apmPrice, apmStatus, star, comments " + "FROM ChefAppointmentForm where apmid = ?";
	private static final String UPDATE = "UPDATE ChefAppointmentForm set memid=?, "
			+ "chefid=?, apmDate=?, apmTime=?, apmPrice=?, apmStatus=?, star=?, comments=? " + "where apmid = ?";

	@Override
	public void insert(ChefAppointmentFormVO chefAppointmentFormVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, chefAppointmentFormVO.getMemid());
			pstmt.setInt(2, chefAppointmentFormVO.getChefid());
			pstmt.setDate(3, chefAppointmentFormVO.getApmDate());
			pstmt.setInt(4, chefAppointmentFormVO.getApmTime());
			pstmt.setInt(5, chefAppointmentFormVO.getApmPrice());
			pstmt.setInt(6, chefAppointmentFormVO.getApmStatus());
			pstmt.setInt(7, chefAppointmentFormVO.getStar());
			pstmt.setString(8, chefAppointmentFormVO.getComments());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(ChefAppointmentFormVO chefAppointmentFormVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(UPDATE);
			pstmt.setInt(1, chefAppointmentFormVO.getMemid());
			pstmt.setInt(2, chefAppointmentFormVO.getChefid());
			pstmt.setDate(3, chefAppointmentFormVO.getApmDate());
			pstmt.setInt(4, chefAppointmentFormVO.getApmTime());
			pstmt.setInt(5, chefAppointmentFormVO.getApmPrice());
			pstmt.setInt(6, chefAppointmentFormVO.getApmStatus());
			pstmt.setInt(7, chefAppointmentFormVO.getStar());
			pstmt.setString(8, chefAppointmentFormVO.getComments());
			pstmt.setInt(9, chefAppointmentFormVO.getApmid());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public ChefAppointmentFormVO findByPrimaryKey(Integer apmid) {
		ChefAppointmentFormVO chefAppointmentFormVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, apmid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				chefAppointmentFormVO = new ChefAppointmentFormVO();
				chefAppointmentFormVO.setApmid(rs.getInt("apmid"));
				chefAppointmentFormVO.setMemid(rs.getInt("memid"));
				chefAppointmentFormVO.setChefid(rs.getInt("chefid"));
				chefAppointmentFormVO.setApmDate(rs.getDate("apmDate"));
				chefAppointmentFormVO.setApmTime(rs.getInt("apmTime"));
				chefAppointmentFormVO.setApmPrice(rs.getInt("apmPrice"));
				chefAppointmentFormVO.setApmStatus(rs.getInt("apmStatus"));
				chefAppointmentFormVO.setStar(rs.getInt("star"));
				chefAppointmentFormVO.setComments(rs.getString("comments"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return chefAppointmentFormVO;
	}

	@Override
	public List<ChefAppointmentFormVO> getAll() {
		List<ChefAppointmentFormVO> list = new ArrayList<ChefAppointmentFormVO>();
		ChefAppointmentFormVO chefAppointmentFormVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				chefAppointmentFormVO = new ChefAppointmentFormVO();
				chefAppointmentFormVO.setApmid(rs.getInt("apmid"));
				chefAppointmentFormVO.setMemid(rs.getInt("memid"));
				chefAppointmentFormVO.setChefid(rs.getInt("chefid"));
				chefAppointmentFormVO.setApmDate(rs.getDate("apmDate"));
				chefAppointmentFormVO.setApmTime(rs.getInt("apmTime"));
				chefAppointmentFormVO.setApmPrice(rs.getInt("apmPrice"));
				chefAppointmentFormVO.setApmStatus(rs.getInt("apmStatus"));
				chefAppointmentFormVO.setStar(rs.getInt("star"));
				chefAppointmentFormVO.setComments(rs.getString("comments"));
				list.add(chefAppointmentFormVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	public static void main(String[] args) {
		ChefAppointmentFormJDBCDAO dao = new ChefAppointmentFormJDBCDAO();

		// 新增
//		ChefAppointmentFormVO chefAppointmentFormVO1 = new ChefAppointmentFormVO();
//		chefAppointmentFormVO1.setMemid(202);
//		chefAppointmentFormVO1.setChefid(303);
//		chefAppointmentFormVO1.setApmDate(java.sql.Date.valueOf("2005-01-01"));
//		chefAppointmentFormVO1.setApmTime(0);
//		chefAppointmentFormVO1.setApmPrice(7000);
//		chefAppointmentFormVO1.setApmStatus(2);
//		chefAppointmentFormVO1.setStar(4);
//		chefAppointmentFormVO1.setComments("很棒");
//		dao.insert(chefAppointmentFormVO1);

		// 修改
		ChefAppointmentFormVO chefAppointmentFormVO2 = new ChefAppointmentFormVO();
		chefAppointmentFormVO2.setApmid(4);
		chefAppointmentFormVO2.setMemid(202);
		chefAppointmentFormVO2.setChefid(302);
		chefAppointmentFormVO2.setApmDate(java.sql.Date.valueOf("2022-12-01"));
		chefAppointmentFormVO2.setApmTime(1);
		chefAppointmentFormVO2.setApmPrice(7000);
		chefAppointmentFormVO2.setApmStatus(2);
		chefAppointmentFormVO2.setStar(4);
		chefAppointmentFormVO2.setComments("棒");
		dao.update(chefAppointmentFormVO2);

		// 查詢
		ChefAppointmentFormVO chefAppointmentFormVO3 = dao.findByPrimaryKey(2);
		System.out.print(chefAppointmentFormVO3.getApmid() + ",");
		System.out.print(chefAppointmentFormVO3.getMemid() + ",");
		System.out.print(chefAppointmentFormVO3.getChefid() + ",");
		System.out.print(chefAppointmentFormVO3.getApmDate() + ",");
		System.out.print(chefAppointmentFormVO3.getApmTime() + ",");
		System.out.print(chefAppointmentFormVO3.getApmPrice() + ",");
		System.out.print(chefAppointmentFormVO3.getApmStatus() + ",");
		System.out.print(chefAppointmentFormVO3.getStar() + ",");
		System.out.println(chefAppointmentFormVO3.getComments() + ",");
		System.out.println("---------------------");

		// 查詢
		List<ChefAppointmentFormVO> list = dao.getAll();
		for (ChefAppointmentFormVO aChefAppointmentForm : list) {
			System.out.print(aChefAppointmentForm.getApmid() + ",");
			System.out.print(aChefAppointmentForm.getMemid() + ",");
			System.out.print(aChefAppointmentForm.getChefid() + ",");
			System.out.print(aChefAppointmentForm.getApmDate() + ",");
			System.out.print(aChefAppointmentForm.getApmTime() + ",");
			System.out.print(aChefAppointmentForm.getApmPrice() + ",");
			System.out.print(aChefAppointmentForm.getApmStatus() + ",");
			System.out.print(aChefAppointmentForm.getStar() + ",");
			System.out.print(aChefAppointmentForm.getComments() + ",");
			System.out.println();
		}
	}
}
