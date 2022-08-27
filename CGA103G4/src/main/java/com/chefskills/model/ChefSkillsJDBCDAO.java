package com.chefskills.model;

import static com.util.common.*;

import java.sql.*;
import java.util.*;

public class ChefSkillsJDBCDAO implements ChefSkillsDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";

	private static final String INSERT_STMT = "INSERT INTO ChefSkills (chefid, skillid) VALUES (?,?)";
	private static final String GET_ALL_STMT = "SELECT chefid, skillid FROM ChefSkills order by chefid, skillid";
	private static final String GET_ONE_STMT = "SELECT chefid, skillid FROM ChefSkills where chefid = ? and skillid = ?";
//	private static final String UPDATE = "UPDATE ChefSkills set chefid=? where skillid = ?";
	private static final String DELETE = "DELETE FROM ChefSkills where chefid = ? and skillid = ?";
	
	@Override
	public void insert(ChefSkillsVO chefSkillsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, chefSkillsVO.getChefid());
			pstmt.setInt(2, chefSkillsVO.getSkillid());
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

//	@Override
//	public void update(ChefSkillsVO chefSkillsVO) {
//		Connection con = null;
//		PreparedStatement pstmt = null;
//		try {
//			Class.forName(driver);
//			Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//			pstmt = conn.prepareStatement(UPDATE);
//			pstmt.setInt(1, chefSkillsVO.getChefid());
//			pstmt.setInt(2, chefSkillsVO.getSkillid());
//			pstmt.executeUpdate();
//
//			// Handle any driver errors
//		} catch (ClassNotFoundException e) {
//			throw new RuntimeException("Couldn't load database driver. "
//					+ e.getMessage());
//			// Handle any SQL errors
//		} catch (SQLException se) {
//			throw new RuntimeException("A database error occured. "
//					+ se.getMessage());
//			// Clean up JDBC resources
//		} finally {
//			if (pstmt != null) {
//				try {
//					pstmt.close();
//				} catch (SQLException se) {
//					se.printStackTrace(System.err);
//				}
//			}
//			if (con != null) {
//				try {
//					con.close();
//				} catch (Exception e) {
//					e.printStackTrace(System.err);
//				}
//			}
//		}
//	}
	
	@Override
	public void delete(Integer chefid, Integer skillid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, chefid);
			pstmt.setInt(2, skillid);
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

	@Override
	public ChefSkillsVO findByPrimaryKey(Integer chefid, Integer skillid) {
		ChefSkillsVO chefSkillsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, chefid);
			pstmt.setInt(2, skillid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				chefSkillsVO = new ChefSkillsVO();
				chefSkillsVO.setChefid(rs.getInt("chefid"));
				chefSkillsVO.setSkillid(rs.getInt("skillid"));
			}

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
		return chefSkillsVO;
	}

	@Override
	public List<ChefSkillsVO> getAll() {
		List<ChefSkillsVO> list = new ArrayList<ChefSkillsVO>();
		ChefSkillsVO chefSkillsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				chefSkillsVO = new ChefSkillsVO();
				chefSkillsVO.setChefid(rs.getInt("chefid"));
				chefSkillsVO.setSkillid(rs.getInt("skillid"));
				list.add(chefSkillsVO); // Store the row in the list
			}

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
		ChefSkillsJDBCDAO dao = new ChefSkillsJDBCDAO();
		// 新增
//		ChefSkillsVO chefSkillsVO1 = new ChefSkillsVO();
//		chefSkillsVO1.setChefid(2);
//		chefSkillsVO1.setSkillid(3);
//		dao.insert(chefSkillsVO1);
		
		// 修改
//		ChefSkillsVO chefSkillsVO2 = new ChefSkillsVO();
//		chefSkillsVO2.setChefid(301);
//		chefSkillsVO2.setSkillid(1);
//		dao.update(chefSkillsVO2);
		
		// 刪除
//		dao.delete(301, 6);

		// 查詢
//		ChefSkillsVO chefSkillsVO3 = dao.findByPrimaryKey(302, 5);
//		System.out.print(chefSkillsVO3.getChefid() + ",");
//		System.out.println(chefSkillsVO3.getSkillid() + ",");
//		System.out.println("---------------------");
//		
		// 查詢
		List<ChefSkillsVO> list = dao.getAll();
		for (ChefSkillsVO aChefSkills : list) {
			System.out.print(aChefSkills.getChefid() + ",");
			System.out.print(aChefSkills.getSkillid() + ",");
			System.out.println();
		}
	}
}
