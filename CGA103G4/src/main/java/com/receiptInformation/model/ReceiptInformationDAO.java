package com.receiptInformation.model;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ReceiptInformationDAO implements ReceiptInformationDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/TestDB2");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
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


			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, receiptInformationVO.getMemid());
			pstmt.setString(2, receiptInformationVO.getRcpName());
			pstmt.setString(3, receiptInformationVO.getRcpCvs());
			pstmt.setString(4, receiptInformationVO.getRcpAddress());
			pstmt.setString(5, receiptInformationVO.getRcpPhone());


			pstmt.executeUpdate();

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


			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, receiptInformationVO.getMemid());
			pstmt.setString(2, receiptInformationVO.getRcpName());
			pstmt.setString(3, receiptInformationVO.getRcpCvs());
			pstmt.setString(4, receiptInformationVO.getRcpAddress());
			pstmt.setString(5, receiptInformationVO.getRcpPhone());
			pstmt.setInt(6, receiptInformationVO.getRcpid());
			

			pstmt.executeUpdate();

			// Handle any driver errors
		
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, rcpid);

			pstmt.executeUpdate();

		
		
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, rcpid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo �]�٬� Domain objects
				receiptInformationVO = new ReceiptInformationVO();
				receiptInformationVO.setRcpid(rs.getInt("rcpid"));
				receiptInformationVO.setMemid(rs.getInt("memid"));
				receiptInformationVO.setRcpName(rs.getString("rcpName"));
				receiptInformationVO.setRcpCvs(rs.getString("rcpCvs"));
				receiptInformationVO.setRcpAddress(rs.getString("rcpAddress"));
				receiptInformationVO.setRcpPhone(rs.getString("rcpPhone"));
				
			}
		
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO �]�٬� Domain objects
				receiptInformationVO = new ReceiptInformationVO();
				receiptInformationVO.setRcpid(rs.getInt("rcpid"));
				receiptInformationVO.setMemid(rs.getInt("memid"));
				receiptInformationVO.setRcpName(rs.getString("rcpname"));
				receiptInformationVO.setRcpCvs(rs.getString("rcpcvs"));
				receiptInformationVO.setRcpAddress(rs.getString("rcpaddress"));
				receiptInformationVO.setRcpPhone(rs.getString("rcpphone"));
				list.add(receiptInformationVO); // Store the row in the list
			}

	
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

		ReceiptInformationDAO dao = new ReceiptInformationDAO();

		// �s�W
		ReceiptInformationVO receiptinformationVO1 = new ReceiptInformationVO();
		receiptinformationVO1.setMemid(1);
		receiptinformationVO1.setRcpName("Michael");
		receiptinformationVO1.setRcpCvs("�x�F�����F�����s��@�q���s����");
		receiptinformationVO1.setRcpAddress("�Ὤ���M���m");
		receiptinformationVO1.setRcpPhone("0987878877");
		
		dao.insert(receiptinformationVO1);

		// �ק�
		ReceiptInformationVO receiptinformationVO2 = new ReceiptInformationVO();
		receiptinformationVO2.setRcpid(1);
		receiptinformationVO2.setMemid(4);
		receiptinformationVO2.setRcpName("���Z");
		receiptinformationVO2.setRcpCvs("�x�_���h�L�ϩ��۸����۪���");
		receiptinformationVO2.setRcpAddress("�s�_���s���Ϸs�����@�q");
		receiptinformationVO2.setRcpPhone("0987878877");
		
		dao.update(receiptinformationVO2);

		// �R��
//		dao.delete(1);

		// �d��
		ReceiptInformationVO receiptinformationVO3 = dao.findByPrimaryKey(2);
		System.out.print(receiptinformationVO3.getRcpid() + ",");
		System.out.print(receiptinformationVO3.getMemid() + ",");
		System.out.print(receiptinformationVO3.getRcpName() + ",");
		System.out.print(receiptinformationVO3.getRcpCvs() + ",");
		System.out.print(receiptinformationVO3.getRcpAddress() + ",");
		System.out.print(receiptinformationVO3.getRcpPhone() + ",");
		
		System.out.println("---------------------");

		// �d�ߩҦ�
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