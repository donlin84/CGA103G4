package com.fr.model;

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

import com.chefschedule.model.ChefScheduleJDBCDAO;
import com.chefschedule.model.ChefScheduleVO;

public class ForumDAO implements ForumDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";

	private static final String INSERT_SQL = "INSERT INTO Forum(frName) VALUES (?);";
	private static final String UPDATE_SQL = "UPDATE Forum set frName=? where frid = ?;";
	private static final String DELETE_SQL = "DELETE FROM Forum where frid = ?";
	private static final String GET_ONE_SQL = "SELECT frid,frName FROM Forum where frid = ?;";
	private static final String GET_ALL_SQL = "SELECT frid,frName FROM Forum order by frid;";
	@Override
	public void insert(ForumVO forumVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(INSERT_SQL);
			pstmt.setString(1, forumVO.getFrName());
			pstmt.executeUpdate();
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
	public void update(ForumVO forumVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(UPDATE_SQL);
			pstmt.setString(1, forumVO.getFrName());
			pstmt.setInt(2, forumVO.getFrid());
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

	public void delete(Integer frid) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(DELETE_SQL);

			pstmt.setInt(1, frid);

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
	public ForumVO findByPrimaryKey(Integer frid) {
		ForumVO forumVO = null;
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_SQL);
			pstmt.setInt(1, frid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				forumVO = new ForumVO();
				forumVO.setFrid(rs.getInt("frid"));
				forumVO.setFrName(rs.getString("frName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forumVO;
	}
	
	
	 public List<ForumVO> getAll(){
		 List<ForumVO> list = new ArrayList<ForumVO>();
		 ForumVO forumVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				Class.forName(driver);
				Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = conn.prepareStatement(GET_ALL_SQL);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					forumVO = new ForumVO();
					forumVO.setFrid(rs.getInt("frid"));
					forumVO.setFrName(rs.getString("frName"));
					list.add(forumVO); // Store the row in the list
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
	

}
