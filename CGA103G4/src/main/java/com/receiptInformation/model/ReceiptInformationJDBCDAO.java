package com.receiptInformation.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ReceiptInformationJDBCDAO implements ReceiptInformationDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/seefooddata?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "tn00349903";

	private static final String INSERT_STMT = 
		"INSERT INTO receiptinformation (memid,rcpName,rcpCvs,rcpAddress,rcpPhone) VALUES (?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT rcpid,memid,rcpName,rcpCvs,rcpAddress,rcpPhone FROM Receiptinformation order by rcpid";
	private static final String GET_ONE_STMT = 
		"SELECT rcpid,memid,rcpName,rcpCvs,rcpAddress,rcpPhone FROM Receiptinformation where rcpid = ?";
	private static final String DELETE = 
		"DELETE FROM Receiptinformation where rcpid = ?";
	private static final String UPDATE = 
		"UPDATE Receiptinformation set memid=?, rcpName=?, rcpCvs=?, rcpAddress=?, rcpPhone=?  where rcpid = ?";
	
	
	
	@Override
	public void insert(ReceiptInformationVO receiptInformationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, receiptInformationVO.getMemid());
			pstmt.setString(2, receiptInformationVO.getRcpName());
			pstmt.setString(3, receiptInformationVO.getRcpCvs());
			pstmt.setString(4, receiptInformationVO.getRcpAddress());
			pstmt.setString(5, receiptInformationVO.getRcpPhone());


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
	public void update(ReceiptInformationVO receiptInformationVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, receiptInformationVO.getMemid());
			pstmt.setString(2, receiptInformationVO.getRcpName());
			pstmt.setString(3, receiptInformationVO.getRcpCvs());
			pstmt.setString(4, receiptInformationVO.getRcpAddress());
			pstmt.setString(5, receiptInformationVO.getRcpPhone());
			pstmt.setInt(6, receiptInformationVO.getRcpid());
			

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
	public void delete(Integer rcpid) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, rcpid);

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
	public ReceiptInformationVO findByPrimaryKey(Integer rcpid) {
		ReceiptInformationVO receiptInformationVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, rcpid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 嚙稽嚙誶穿蕭 Domain objects
				receiptInformationVO = new ReceiptInformationVO();
				receiptInformationVO.setRcpid(rs.getInt("rcpid"));
				receiptInformationVO.setMemid(rs.getInt("memid"));
				receiptInformationVO.setRcpName(rs.getString("rcpName"));
				receiptInformationVO.setRcpCvs(rs.getString("rcpCvs"));
				receiptInformationVO.setRcpAddress(rs.getString("rcpAddress"));
				receiptInformationVO.setRcpPhone(rs.getString("rcpPhone"));
				
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
		return receiptInformationVO;
	}

	@Override
	public List<ReceiptInformationVO> getAll() {
		List<ReceiptInformationVO> list = new ArrayList<ReceiptInformationVO>();
		ReceiptInformationVO receiptInformationVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 嚙稽嚙誶穿蕭 Domain objects
				receiptInformationVO = new ReceiptInformationVO();
				receiptInformationVO.setRcpid(rs.getInt("rcpid"));
				receiptInformationVO.setMemid(rs.getInt("memid"));
				receiptInformationVO.setRcpName(rs.getString("rcpname"));
				receiptInformationVO.setRcpCvs(rs.getString("rcpcvs"));
				receiptInformationVO.setRcpAddress(rs.getString("rcpaddress"));
				receiptInformationVO.setRcpPhone(rs.getString("rcpphone"));
				list.add(receiptInformationVO); // Store the row in the list
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

		ReceiptInformationJDBCDAO dao = new ReceiptInformationJDBCDAO();

		// 新增
		ReceiptInformationVO receiptinformationVO1 = new ReceiptInformationVO();
		receiptinformationVO1.setMemid(1);
		receiptinformationVO1.setRcpName("Michael");
		receiptinformationVO1.setRcpCvs("台東市市政路中山街一段中山門市");
		receiptinformationVO1.setRcpAddress("花蓮市和平鄉");
		receiptinformationVO1.setRcpPhone("0987878877");
		
		dao.insert(receiptinformationVO1);

		// 修改
		ReceiptInformationVO receiptinformationVO2 = new ReceiptInformationVO();
		receiptinformationVO2.setRcpid(1);
		receiptinformationVO2.setMemid(4);
		receiptinformationVO2.setRcpName("城武");
		receiptinformationVO2.setRcpCvs("台北市士林區忠誠路忠誠門市");
		receiptinformationVO2.setRcpAddress("新北市新莊區新泰路一段");
		receiptinformationVO2.setRcpPhone("0987878877");
		
		dao.update(receiptinformationVO2);

		// 刪除
//		dao.delete(1);

		// 查詢
		ReceiptInformationVO receiptinformationVO3 = dao.findByPrimaryKey(2);
		System.out.print(receiptinformationVO3.getRcpid() + ",");
		System.out.print(receiptinformationVO3.getMemid() + ",");
		System.out.print(receiptinformationVO3.getRcpName() + ",");
		System.out.print(receiptinformationVO3.getRcpCvs() + ",");
		System.out.print(receiptinformationVO3.getRcpAddress() + ",");
		System.out.print(receiptinformationVO3.getRcpPhone() + ",");
		
		System.out.println("---------------------");

		// 查詢所有
		List<ReceiptInformationVO> list = dao.getAll();
		for (ReceiptInformationVO aReceiptinformation : list) {
			System.out.print(aReceiptinformation.getRcpid() + ",");
			System.out.print(aReceiptinformation.getMemid() + ",");
			System.out.print(aReceiptinformation.getRcpName() + ",");
			System.out.print(aReceiptinformation.getRcpCvs() + ",");
			System.out.print(aReceiptinformation.getRcpAddress() + ",");
			System.out.print(aReceiptinformation.getRcpPhone() + ",");
			System.out.println();
		}
	}
}