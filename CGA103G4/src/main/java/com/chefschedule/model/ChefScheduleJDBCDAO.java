package com.chefschedule.model;

import static com.util.Common.PASSWORD;
import static com.util.Common.URL;
import static com.util.Common.USER;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChefScheduleJDBCDAO implements ChefScheduleDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";

	private static final String INSERT_STMT = "INSERT INTO ChefSchedule(chefid, schDate, schTime) "
			+ "VALUES (?, ?, ?)";
	private static final String UPDATE = "UPDATE ChefSchedule set schTime=? where chefid = ? AND schDate=? ";
	private static final String Get_ALL_BY_ID = "SELECT chefSchid, chefid, schDate, schTime from ChefSchedule where chefid=?";
	private static final String DELETE = "DELETE FROM ChefSchedule where chefid = ? AND schDate= ?";
	@Override
	public void insert(ChefScheduleVO chefScheduleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, chefScheduleVO.getChefid());
			pstmt.setDate(2, chefScheduleVO.getSchDate());
			pstmt.setInt(3, chefScheduleVO.getSchTime());
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
	public void update(ChefScheduleVO chefScheduleVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(UPDATE);
			pstmt.setInt(1, chefScheduleVO.getSchTime());
			pstmt.setInt(2, chefScheduleVO.getChefid());
			pstmt.setDate(3, chefScheduleVO.getSchDate());
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

	public void delete(Integer chefid, Date schDate) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, chefid);
			pstmt.setDate(2, schDate);

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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


	 public List<ChefScheduleVO> getAllById(Integer chefid){
		 List<ChefScheduleVO> list = new ArrayList<ChefScheduleVO>();
			ChefScheduleVO chefScheduleVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				Class.forName(driver);
				Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = conn.prepareStatement(Get_ALL_BY_ID);
				pstmt.setInt(1, chefid);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					chefScheduleVO = new ChefScheduleVO();
					chefScheduleVO.setChefSchid(rs.getInt("chefSchid"));
					chefScheduleVO.setChefid(rs.getInt("chefid"));
					chefScheduleVO.setSchDate(rs.getDate("schDate"));
					chefScheduleVO.setSchTime(rs.getInt("schTime"));
					list.add(chefScheduleVO); // Store the row in the list
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
		ChefScheduleJDBCDAO dao = new ChefScheduleJDBCDAO();

//		// 新增
//		ChefScheduleVO chefScheduleVO1 = new ChefScheduleVO();
//		chefScheduleVO1.setChefid(301);
//		chefScheduleVO1.setSchDate(java.sql.Date.valueOf("2012-11-11"));
//		chefScheduleVO1.setSchTime(2);
//		dao.insert(chefScheduleVO1);

		// 修改
//		ChefScheduleVO chefScheduleVO2 = new ChefScheduleVO();
//		chefScheduleVO2.setChefid(302);
//		chefScheduleVO2.setSchDate(java.sql.Date.valueOf("2012-12-12"));
//		chefScheduleVO2.setSchTime(1);
//		chefScheduleVO2.setChefSchid(6);
//		dao.update(chefScheduleVO2);
//
//		// 查詢
//		ChefScheduleVO chefScheduleVO3 = dao.findByPrimaryKey(3);
//		System.out.print(chefScheduleVO3.getChefSchid() + ",");
//		System.out.print(chefScheduleVO3.getChefid() + ",");
//		System.out.print(chefScheduleVO3.getSchDate() + ",");
//		System.out.println(chefScheduleVO3.getSchTime() + ",");
//		System.out.println("---------------------");

		// 查詢
//		List<ChefScheduleVO> list = dao.getAll();
//		for (ChefScheduleVO aChefSchedule : list) {
//			System.out.print(aChefSchedule.getChefSchid() + ",");
//			System.out.print(aChefSchedule.getChefid() + ",");
//			System.out.print(aChefSchedule.getSchDate() + ",");
//			System.out.print(aChefSchedule.getSchTime() + ",");
//			System.out.println();
//		}
		
		//查詢單一私廚所有班表
//		List<ChefScheduleVO> list = dao.getAllById(302);
//		for (ChefScheduleVO aChefSchedule : list) {
//		System.out.print(aChefSchedule.getChefSchid() + ",");
//		System.out.print(aChefSchedule.getChefid() + ",");
//		System.out.print(aChefSchedule.getSchDate() + ",");
//		System.out.print(aChefSchedule.getSchTime() + ",");
//		System.out.println();
//	}
	}

}
