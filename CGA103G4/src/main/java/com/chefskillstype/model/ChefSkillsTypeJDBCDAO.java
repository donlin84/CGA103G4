package com.chefskillstype.model;


import static com.util.Common.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_ChefSkillsType;

public class ChefSkillsTypeJDBCDAO implements ChefSkillsTypeDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO ChefSkillsType (skill) VALUES (?)";
	private static final String GET_ALL_STMT = "SELECT skillid,skill FROM ChefSkillsType order by skillid";
	private static final String GET_ONE_STMT = "SELECT skillid,skill FROM ChefSkillsType where skillid = ?";
	private static final String UPDATE = "UPDATE ChefSkillsType set skill=? where skillid = ?";

	@Override
	public void insert(ChefSkillsTypeVO chefskillstypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(INSERT_STMT);
			pstmt.setString(1, chefskillstypeVO.getSkill());
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
	public void update(ChefSkillsTypeVO chefskillstypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(UPDATE);
			pstmt.setString(1, chefskillstypeVO.getSkill());
			pstmt.setInt(2, chefskillstypeVO.getSkillid());
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
	public ChefSkillsTypeVO findByPrimaryKey(Integer skillid) {
		ChefSkillsTypeVO chefskillstypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, skillid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				chefskillstypeVO = new ChefSkillsTypeVO();
				chefskillstypeVO.setSkillid(rs.getInt("skillid"));
				chefskillstypeVO.setSkill(rs.getString("skill"));
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
		return chefskillstypeVO;
	}

	@Override
	public List<ChefSkillsTypeVO> getAll() {
		List<ChefSkillsTypeVO> list = new ArrayList<ChefSkillsTypeVO>();
		ChefSkillsTypeVO chefskillstypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				chefskillstypeVO = new ChefSkillsTypeVO();
				chefskillstypeVO.setSkillid(rs.getInt("skillid"));
				chefskillstypeVO.setSkill(rs.getString("skill"));
				list.add(chefskillstypeVO); // Store the row in the list
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

	@Override
	public List<ChefSkillsTypeVO> getAll(Map<String, String[]> map) {
		List<ChefSkillsTypeVO> list = new ArrayList<ChefSkillsTypeVO>();
		ChefSkillsTypeVO chefSkillsTypeVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

			String finalSQL = "select * from chefSkillsType "
		          + jdbcUtil_CompositeQuery_ChefSkillsType.get_WhereCondition(map)
		          + "order by skillid";
			pstmt = conn.prepareStatement(finalSQL);
			System.out.println("??????finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				chefSkillsTypeVO = new ChefSkillsTypeVO();
				chefSkillsTypeVO.setSkillid(rs.getInt("skillid"));
				chefSkillsTypeVO.setSkill(rs.getString("skill"));
				list.add(chefSkillsTypeVO); // Store the row in the List
			}
	
			// Handle any SQL errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
		ChefSkillsTypeJDBCDAO dao = new ChefSkillsTypeJDBCDAO();

		// ??????
//		ChefSkillsTypeVO chefskillstypeVO1 = new ChefSkillsTypeVO();
//		chefskillstypeVO1.setSkill("????????????");
//		dao.insert(chefskillstypeVO1);

		// ??????
		ChefSkillsTypeVO chefskillstypeVO2 = new ChefSkillsTypeVO();
		chefskillstypeVO2.setSkillid(1);
		chefskillstypeVO2.setSkill("????????????");
		dao.update(chefskillstypeVO2);

		// ??????
//		ChefSkillsTypeVO chefskillstypeVO3 = dao.findByPrimaryKey(1);
//		System.out.print(chefskillstypeVO3.getSkillid() + ",");
//		System.out.print(chefskillstypeVO3.getSkill() + ",");

		// ??????
		List<ChefSkillsTypeVO> list = dao.getAll();
		for (ChefSkillsTypeVO aEmp : list) {
			System.out.print(aEmp.getSkillid() + ",");
			System.out.print(aEmp.getSkill() + ",");
			System.out.println();
		}
	}
}
