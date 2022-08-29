package com.membercoupon.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MemberCouponJDBCDAO implements MemberCouponDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga103g4?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "sa4850869";

	private static final String INSERT_STMT = "INSERT INTO cga103g4.MemberCoupon (memid,CpTpid,memCpDate,memCpStatus,memCpRecord) VALUES (?, ?, ?, ?, ?)";
	
	private static final String UPDATE = "UPDATE cga103g4.MemberCoupon set memid=?, CpTpid=?, memCpDate=?, memCpStatus=?, memCpRecord=? where memCpid = ?";
	
	private static final String DELETE = "DELETE FROM cga103g4.MemberCoupon where memCpid = ?";
	
	private static final String GET_ALL_STMT = "SELECT memCpid,memid,CpTpid,memCpDate,memCpStatus,memCpRecord FROM cga103g4.MemberCoupon order by memCpid";
	
	private static final String GET_ONE_STMT = "SELECT memCpid,memid,CpTpid,memCpDate,memCpStatus,memCpRecord FROM cga103g4.MemberCoupon where memCpid = ?";

	@Override
	public void insert(MemberCouponVO memberCouponVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, memberCouponVO.getMemid());
			pstmt.setInt(2, memberCouponVO.getCpTpid());
			
//			pstmt.setDate(3, memberCouponVO.getMemCpDate());
			pstmt.setObject(3, memberCouponVO.getMemCpDate());
			
			pstmt.setInt(4, memberCouponVO.getMemCpStatus());
			
//			pstmt.setDate(5, memberCouponVO.getMemCpRecord());
			pstmt.setObject(5, memberCouponVO.getMemCpRecord());

			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e){
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
	public void update(MemberCouponVO memberCouponVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, memberCouponVO.getMemid());
			pstmt.setInt(2, memberCouponVO.getCpTpid());
			
//			pstmt.setDate(3, memberCouponVO.getMemCpDate());
			pstmt.setObject(3, memberCouponVO.getMemCpDate());
			
			pstmt.setInt(4, memberCouponVO.getMemCpStatus());
			
//			pstmt.setDate(5, memberCouponVO.getMemCpRecord());
			pstmt.setObject(5, memberCouponVO.getMemCpRecord());
			
			pstmt.setInt(6, memberCouponVO.getMemCpid());

			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void delete(Integer memCpid) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memCpid);

			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<MemberCouponVO> getAll() {	
		List<MemberCouponVO> list = new ArrayList<MemberCouponVO>();
		MemberCouponVO memberCouponVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memberCouponVO = new MemberCouponVO();
				memberCouponVO.setMemCpid(rs.getInt("memCpid"));
				memberCouponVO.setMemid(rs.getInt("memid"));
				memberCouponVO.setCpTpid(rs.getInt("cpTpid"));
				
//				memberCouponVO.setMemCpDate(rs.getDate("memCpDate"));
				memberCouponVO.setMemCpDate(rs.getObject("memCpDate",LocalDateTime.class));
				
				memberCouponVO.setMemCpStatus(rs.getInt("memCpStatus"));
				
//				memberCouponVO.setMemCpRecord(rs.getDate("memCpRecord"));
				memberCouponVO.setMemCpRecord(rs.getObject("memCpRecord",LocalDateTime.class));
				
				list.add(memberCouponVO);

			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());

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
	public MemberCouponVO findByPrimaryKey(Integer memCpid) {

		MemberCouponVO memberCouponVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, memCpid);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				memberCouponVO = new MemberCouponVO();
				memberCouponVO.setMemCpid(rs.getInt("memCpid"));
				memberCouponVO.setMemid(rs.getInt("memid"));
				memberCouponVO.setCpTpid(rs.getInt("cpTpid"));
				
//				memberCouponVO.setMemCpDate(rs.getDate("memCpDate"));
				memberCouponVO.setMemCpDate(rs.getObject("memCpDate",LocalDateTime.class));
				
				memberCouponVO.setMemCpStatus(rs.getInt("memCpStatus"));
				
//				memberCouponVO.setMemCpRecord(rs.getDate("memCpRecord"));
				memberCouponVO.setMemCpRecord(rs.getObject("memCpRecord",LocalDateTime.class));

			}
			
		} catch(ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return memberCouponVO;
	}
	
	public static void main(String[] args) {
		
		MemberCouponJDBCDAO dao = new MemberCouponJDBCDAO();
		
		// 新增
		MemberCouponVO memberCouponVO1 = new MemberCouponVO();
		
		memberCouponVO1.setMemid(204);
		memberCouponVO1.setCpTpid(2);
		memberCouponVO1.setMemCpDate(LocalDateTime.parse("2022-08-01T00:00:00"));
		memberCouponVO1.setMemCpStatus(1);
		memberCouponVO1.setMemCpRecord(LocalDateTime.parse("2022-08-10T00:00:00"));
		
		dao.insert(memberCouponVO1);

		// 修改
		MemberCouponVO memberCouponVO2 = new MemberCouponVO();
		memberCouponVO2.setMemCpid(2);
		memberCouponVO2.setMemid(204);
		memberCouponVO2.setCpTpid(2);
		memberCouponVO2.setMemCpDate(LocalDateTime.parse("2022-08-15T00:00:00"));
		memberCouponVO2.setMemCpStatus(1);
		memberCouponVO2.setMemCpRecord(LocalDateTime.parse("2022-08-16T00:00:00"));
		
		dao.update(memberCouponVO2);

		// 刪除
		dao.delete(4);

		// 查詢
		MemberCouponVO memberCouponVO3 = dao.findByPrimaryKey(2);
		System.out.print(memberCouponVO3.getMemCpid() + ",");
		System.out.print(memberCouponVO3.getMemid() + ",");
		System.out.print(memberCouponVO3.getCpTpid() + ",");
		System.out.print(memberCouponVO3.getMemCpDate() + ",");
		System.out.print(memberCouponVO3.getMemCpStatus() + ",");
		System.out.print(memberCouponVO3.getMemCpRecord() + ",");
		System.out.println("---------------------");
		

		// 查詢
		List<MemberCouponVO> list = dao.getAll();
		for (MemberCouponVO aMemberCoupon : list) {
			System.out.print(aMemberCoupon.getMemCpid() + ",");
			System.out.print(aMemberCoupon.getMemid() + ",");
			System.out.print(aMemberCoupon.getCpTpid() + ",");
			System.out.print(aMemberCoupon.getMemCpDate() + ",");
			System.out.print(aMemberCoupon.getMemCpStatus() + ",");
			System.out.print(aMemberCoupon.getMemCpRecord() + ",");
			System.out.println();
		
		
	}
		
	}

}
