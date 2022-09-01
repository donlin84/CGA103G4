package com.chefskillstype.model;


import static com.util.common_hung.PASSWORD;
import static com.util.common_hung.URL;
import static com.util.common_hung.USER;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChefSkillsTypeJDBCDAO implements ChefSkillsTypeDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";

	private static final String INSERT_STMT = "INSERT INTO ChefSkillsType (skill) VALUES (?)";
	private static final String GET_ALL_STMT = "SELECT skillid,skill FROM ChefSkillsType order by skillid";
	private static final String GET_ONE_STMT = "SELECT skillid,skill FROM ChefSkillsType where skillid = ?";
	private static final String UPDATE = "UPDATE ChefSkillsType set skill=? where skillid = ?";

	@Override
	public void insert(ChefSkillsTypeVO chefskillstypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
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
			Class.forName(driver);
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
			Class.forName(driver);
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
			Class.forName(driver);
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

	public static void main(String[] args) {
		ChefSkillsTypeJDBCDAO dao = new ChefSkillsTypeJDBCDAO();

		// 新增
//		ChefSkillsTypeVO chefskillstypeVO1 = new ChefSkillsTypeVO();
//		chefskillstypeVO1.setSkill("泰式料理");
//		dao.insert(chefskillstypeVO1);

		// 修改
		ChefSkillsTypeVO chefskillstypeVO2 = new ChefSkillsTypeVO();
		chefskillstypeVO2.setSkillid(1);
		chefskillstypeVO2.setSkill("泰式料理");
		dao.update(chefskillstypeVO2);

		// 查詢
//		ChefSkillsTypeVO chefskillstypeVO3 = dao.findByPrimaryKey(1);
//		System.out.print(chefskillstypeVO3.getSkillid() + ",");
//		System.out.print(chefskillstypeVO3.getSkill() + ",");

		// 查詢
		List<ChefSkillsTypeVO> list = dao.getAll();
		for (ChefSkillsTypeVO aEmp : list) {
			System.out.print(aEmp.getSkillid() + ",");
			System.out.print(aEmp.getSkill() + ",");
			System.out.println();
		}
	}
}
