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
			+ "chefid, apmDate, apmTime, apmPrice) " + "VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_CHEFID = "SELECT apmid, memid, chefid, apmDate, "
			+ "apmTime, apmPrice, apmStatus, star, comments " + "FROM ChefAppointmentForm where chefid=? order by apmid";
	private static final String GET_ALL_MEMID = "SELECT apmid, memid, chefid, apmDate, "
			+ "apmTime, apmPrice, apmStatus, star, comments " + "FROM ChefAppointmentForm where memid=? order by apmid";
	private static final String GET_ONE_STMT = "SELECT apmid, memid, chefid, apmDate, "
			+ "apmTime, apmPrice, apmStatus, star, comments " + "FROM ChefAppointmentForm where apmid=?";
	private static final String CHEF_UPDATE = "UPDATE ChefAppointmentForm set"
			+ " apmDate=?, apmTime=?, apmPrice=?, apmStatus=? " + "where apmid = ?";
	private static final String MEM_UPDATE = "UPDATE ChefAppointmentForm set"
			+ " star=?, comments=? " + "where apmid = ?";

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
	public void updateByChef(ChefAppointmentFormVO chefAppointmentFormVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(CHEF_UPDATE);
			pstmt.setDate(1, chefAppointmentFormVO.getApmDate());
			pstmt.setInt(2, chefAppointmentFormVO.getApmTime());
			pstmt.setInt(3, chefAppointmentFormVO.getApmPrice());
			pstmt.setInt(4, chefAppointmentFormVO.getApmStatus());
			pstmt.setInt(5, chefAppointmentFormVO.getApmid());
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
	public void updateByMem(ChefAppointmentFormVO chefAppointmentFormVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(MEM_UPDATE);
			pstmt.setInt(1, chefAppointmentFormVO.getStar());
			pstmt.setString(2, chefAppointmentFormVO.getComments());
			pstmt.setInt(3, chefAppointmentFormVO.getApmid());
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


	public ChefAppointmentFormVO findByapmid(Integer apmid) {
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
	public List<ChefAppointmentFormVO> getAllByChef(Integer chefid) {
		List<ChefAppointmentFormVO> list = new ArrayList<ChefAppointmentFormVO>();
		ChefAppointmentFormVO chefAppointmentFormVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(GET_ALL_CHEFID);
			pstmt.setInt(1, chefid);
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
	public List<ChefAppointmentFormVO> getAllByMem(Integer memid) {
		List<ChefAppointmentFormVO> list = new ArrayList<ChefAppointmentFormVO>();
		ChefAppointmentFormVO chefAppointmentFormVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(GET_ALL_MEMID);
			pstmt.setInt(1, memid);
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


}
