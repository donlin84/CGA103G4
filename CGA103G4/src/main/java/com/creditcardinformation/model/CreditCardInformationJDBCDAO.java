package com.creditcardinformation.model;

import static com.util.Common.*;

import java.sql.*;
import java.util.*;

import com.chef.model.ChefVO;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Chef;
import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_CreditCardInformation;

public class CreditCardInformationJDBCDAO implements CreditCardInformationDAO_interface{

	private static final String INSERT_STMT = "INSERT INTO CreditCardInformation (memid, "
			+ "creditCardNumber, creditCardName, creditCardTime, cvcCode) VALUES (?,?,?,?,?)";
	private static final String GET_ALL_STMT = "SELECT creditCardid, memid, "
			+ "creditCardNumber, creditCardName, creditCardTime, cvcCode "
			+ "FROM CreditCardInformation order by creditCardid";
	private static final String GET_ONE_STMT = "SELECT creditCardid, memid, "
			+ "creditCardNumber, creditCardName, creditCardTime, cvcCode "
			+ "FROM CreditCardInformation where creditCardid = ?";
	private static final String DELETE = "DELETE FROM CreditCardInformation where creditCardid = ?";
	
	@Override
	public void insert(CreditCardInformationVO creditCardInformationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(INSERT_STMT);
			pstmt.setInt(1, creditCardInformationVO.getMemid());
			pstmt.setString(2, creditCardInformationVO.getCreditCardNumber());
			pstmt.setString(3, creditCardInformationVO.getCreditCardName());
			pstmt.setString(4, creditCardInformationVO.getCreditCardTime());
			pstmt.setString(5, creditCardInformationVO.getCvcCode());
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
	public void delete(Integer creditCardid) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(DELETE);
			pstmt.setInt(1, creditCardid);
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
	public CreditCardInformationVO findByPrimaryKey(Integer creditCardid) {
		CreditCardInformationVO creditCardInformationVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, creditCardid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				creditCardInformationVO = new CreditCardInformationVO();
				creditCardInformationVO.setCreditCardid(rs.getInt("creditCardid"));
				creditCardInformationVO.setMemid(rs.getInt("memid"));
				creditCardInformationVO.setCreditCardNumber(rs.getString("creditCardNumber"));
				creditCardInformationVO.setCreditCardName(rs.getString("creditCardName"));
				creditCardInformationVO.setCreditCardTime(rs.getString("creditCardTime"));
				creditCardInformationVO.setCvcCode(rs.getString("cvcCode"));
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
		return creditCardInformationVO;
	}

	@Override
	public List<CreditCardInformationVO> getAll() {
		List<CreditCardInformationVO> list = new ArrayList<CreditCardInformationVO>();
		CreditCardInformationVO creditCardInformationVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				creditCardInformationVO = new CreditCardInformationVO();
				creditCardInformationVO.setCreditCardid(rs.getInt("creditCardid"));
				creditCardInformationVO.setMemid(rs.getInt("memid"));
				creditCardInformationVO.setCreditCardNumber(rs.getString("creditCardNumber"));
				creditCardInformationVO.setCreditCardName(rs.getString("creditCardName"));
				creditCardInformationVO.setCreditCardTime(rs.getString("creditCardTime"));
				creditCardInformationVO.setCvcCode(rs.getString("cvcCode"));
				list.add(creditCardInformationVO); // Store the row in the list
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
	
	@Override
	public List<CreditCardInformationVO> getAll(Map<String, String[]> map) {
		List<CreditCardInformationVO> list = new ArrayList<CreditCardInformationVO>();
		CreditCardInformationVO creditCardInformationVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

			String finalSQL = "select * from creditcardinformation "
		          + jdbcUtil_CompositeQuery_CreditCardInformation.get_WhereCondition(map)
		          + "order by creditCardid";
			pstmt = conn.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				creditCardInformationVO = new CreditCardInformationVO();
				creditCardInformationVO.setCreditCardid(rs.getInt("creditCardid"));
				creditCardInformationVO.setMemid(rs.getInt("memid"));
				creditCardInformationVO.setCreditCardNumber(rs.getString("creditCardNumber"));
				creditCardInformationVO.setCreditCardName(rs.getString("creditCardName"));
				creditCardInformationVO.setCreditCardTime(rs.getString("creditCardTime"));
				creditCardInformationVO.setCvcCode(rs.getString("cvcCode"));
				list.add(creditCardInformationVO); // Store the row in the List
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
		CreditCardInformationJDBCDAO dao = new CreditCardInformationJDBCDAO();
		
		// 新增
//		CreditCardInformationVO creditCardInformationVO1 = new CreditCardInformationVO();
//		creditCardInformationVO1.setMemid(203);
//		creditCardInformationVO1.setCreditCardNumber("5555666677778888");
//		creditCardInformationVO1.setCreditCardName("張正黃");
//		creditCardInformationVO1.setCreditCardTime("0127");
//		creditCardInformationVO1.setCvcCode("333");
//		dao.insert(creditCardInformationVO1);

		// 刪除
		dao.delete(5);

		// 查詢
		CreditCardInformationVO creditCardInformationVO3 = dao.findByPrimaryKey(1);
		System.out.print(creditCardInformationVO3.getCreditCardid() + ",");
		System.out.print(creditCardInformationVO3.getMemid() + ",");
		System.out.print(creditCardInformationVO3.getCreditCardNumber() + ",");
		System.out.print(creditCardInformationVO3.getCreditCardName() + ",");
		System.out.print(creditCardInformationVO3.getCreditCardTime() + ",");
		System.out.println(creditCardInformationVO3.getCvcCode() + ",");
		System.out.println("---------------------");
		
		// 查詢
		List<CreditCardInformationVO> list = dao.getAll();
		for (CreditCardInformationVO aCreditCardInformation : list) {
			System.out.print(aCreditCardInformation.getCreditCardid() + ",");
			System.out.print(aCreditCardInformation.getMemid() + ",");
			System.out.print(aCreditCardInformation.getCreditCardNumber() + ",");
			System.out.print(aCreditCardInformation.getCreditCardName() + ",");
			System.out.print(aCreditCardInformation.getCreditCardTime() + ",");
			System.out.print(aCreditCardInformation.getCvcCode() + ",");
			System.out.println();
		}
	}

}
