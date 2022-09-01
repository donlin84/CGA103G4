package com.chefsubscription.model;


import static com.util.Common.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChefSubscriptionJDBCDAO implements ChefSubscriptionDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";

	private static final String INSERT_STMT = "INSERT INTO ChefSubscription (chefid, memid) VALUES (?,?)";
	private static final String GET_ALL_STMT = "SELECT chefid, memid FROM ChefSubscription order by chefid, memid";
	private static final String GET_ONE_STMT = "SELECT chefid, memid FROM ChefSubscription where chefid = ? and memid = ?";
	private static final String DELETE = "DELETE FROM ChefSubscription where chefid = ? and memid = ?";

	@Override
	public void insert(ChefSubscriptionVO chefSubscriptionVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, chefSubscriptionVO.getChefid());
			pstmt.setInt(2, chefSubscriptionVO.getMemid());
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
	public void delete(Integer chefid, Integer memid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, chefid);
			pstmt.setInt(2, memid);
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
	public ChefSubscriptionVO findByPrimaryKey(Integer chefid, Integer memid) {
		ChefSubscriptionVO chefSubscriptionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, chefid);
			pstmt.setInt(2, memid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				chefSubscriptionVO = new ChefSubscriptionVO();
				chefSubscriptionVO.setChefid(rs.getInt("chefid"));
				chefSubscriptionVO.setMemid(rs.getInt("memid"));
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
		return chefSubscriptionVO;
	}

	@Override
	public List<ChefSubscriptionVO> getAll() {
		List<ChefSubscriptionVO> list = new ArrayList<ChefSubscriptionVO>();
		ChefSubscriptionVO chefSubscriptionVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				chefSubscriptionVO = new ChefSubscriptionVO();
				chefSubscriptionVO.setChefid(rs.getInt("chefid"));
				chefSubscriptionVO.setMemid(rs.getInt("memid"));
				list.add(chefSubscriptionVO); // Store the row in the list
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
		ChefSubscriptionJDBCDAO dao = new ChefSubscriptionJDBCDAO();

		// 新增
//		ChefSubscriptionVO chefSubscriptionVO1 = new ChefSubscriptionVO();
//		chefSubscriptionVO1.setChefid(302);
//		chefSubscriptionVO1.setMemid(203);
//		dao.insert(chefSubscriptionVO1);

		// 刪除
//		dao.delete(301, 205);

		// 查詢
//		ChefSubscriptionVO chefSubscriptionVO3 = dao.findByPrimaryKey(1, 5);
//		System.out.print(chefSubscriptionVO3.getChefid() + ",");
//		System.out.println(chefSubscriptionVO3.getMemid() + ",");
//		System.out.println("---------------------");

		// 查詢
		List<ChefSubscriptionVO> list = dao.getAll();
		for (ChefSubscriptionVO aChefSubscription : list) {
			System.out.print(aChefSubscription.getChefid() + ",");
			System.out.print(aChefSubscription.getMemid() + ",");
			System.out.println();
		}
	}
}
