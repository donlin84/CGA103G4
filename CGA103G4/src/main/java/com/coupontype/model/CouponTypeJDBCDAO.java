package com.coupontype.model;

import java.util.*;

import com.membercoupon.model.MemberCouponVO;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

public class CouponTypeJDBCDAO implements CouponTypeDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga103g4?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "cga103g4";

	private static final String INSERT_STMT = "insert into cga103g4.CouponType(cpname,cpdiscount,cpstart,cpend,cpstatus,cppic)"
			+ " VALUES (?, ?, ?, ?, ?, ?)";

	private static final String UPDATE = "UPDATE cga103g4.CouponType set cpname=?,cpdiscount=?,cpstart=?,cpend=?,cpstatus=?,cppic=?"
			+ " where cpTpid = ?";
	
	private static final String DELETE = "DELETE FROM cga103g4.CouponType where cpTpid = ?";

	private static final String GET_ALL_STMT = "SELECT cpTpid,cpname,cpdiscount,cpstart,cpend,cpstatus,cppic "
			+ " FROM cga103g4.coupontype order by cpTpid";

	private static final String GET_ONE_STMT = "SELECT cpTpid,cpname,cpdiscount,cpstart,cpend,cpstatus,cppic "
			+ " FROM cga103g4.coupontype where cpTpid = ?";

	@Override
	public void insert(CouponTypeVO coupontypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, coupontypeVO.getCpName());
			pstmt.setInt(2, coupontypeVO.getCpDiscount());
			
			pstmt.setDate(3, coupontypeVO.getCpStart());
			pstmt.setDate(4, coupontypeVO.getCpEnd());
//			pstmt.setObject(3, coupontypeVO.getCpStart());
//			pstmt.setObject(4, coupontypeVO.getCpEnd());
			
			
			pstmt.setInt(5, coupontypeVO.getCpStatus());
			pstmt.setBytes(6, coupontypeVO.getCpPic());

			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void update(CouponTypeVO coupontypeVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, coupontypeVO.getCpName());
			pstmt.setInt(2, coupontypeVO.getCpDiscount());
			
			pstmt.setDate(3, coupontypeVO.getCpStart());
			pstmt.setDate(4, coupontypeVO.getCpEnd());
//			pstmt.setObject(3, coupontypeVO.getCpStart());
//			pstmt.setObject(4, coupontypeVO.getCpEnd());
			
			pstmt.setInt(5, coupontypeVO.getCpStatus());
			pstmt.setBytes(6, coupontypeVO.getCpPic());
			pstmt.setInt(7, coupontypeVO.getCpTpid());
			
			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public void delete(Integer cpTpid) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, cpTpid);

			pstmt.executeUpdate();
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
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
	public List<CouponTypeVO> getAll() {
		List<CouponTypeVO> list = new ArrayList<CouponTypeVO>();
		CouponTypeVO couponTypeVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			
			
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				couponTypeVO = new CouponTypeVO();
				couponTypeVO.setCpTpid(rs.getInt("cpTpid"));
				couponTypeVO.setCpName(rs.getString("cpName"));
				couponTypeVO.setCpDiscount(rs.getInt("cpDiscount"));
				
				couponTypeVO.setCpStart(rs.getDate("cpStart"));
				couponTypeVO.setCpEnd(rs.getDate("cpEnd"));
//				couponTypeVO.setCpStart(rs.getObject("cpStart",LocalDateTime.class));
//				couponTypeVO.setCpEnd(rs.getObject("cpEnd",LocalDateTime.class));
				
				couponTypeVO.setCpStatus(rs.getInt("cpStatus"));
				couponTypeVO.setCpPic(rs.getBytes("cpPic"));
				
				list.add(couponTypeVO); // Store the row in the list
			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any driver errors
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
	public CouponTypeVO findByPrimaryKey(Integer cpTpid) {
		CouponTypeVO couponTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, cpTpid);
			
			rs = pstmt.executeQuery();

			while (rs.next()) {

				couponTypeVO = new CouponTypeVO();
				couponTypeVO.setCpTpid(rs.getInt("cpTpid"));
				couponTypeVO.setCpName(rs.getString("cpName"));
				couponTypeVO.setCpDiscount(rs.getInt("cpDiscount"));
				
				couponTypeVO.setCpStart(rs.getDate("cpStart"));
				couponTypeVO.setCpEnd(rs.getDate("cpEnd"));
//				couponTypeVO.setCpStart(rs.getObject("cpStart",LocalDateTime.class));
//				couponTypeVO.setCpEnd(rs.getObject("cpEnd",LocalDateTime.class));
				
				couponTypeVO.setCpStatus(rs.getInt("cpStatus"));
				couponTypeVO.setCpPic(rs.getBytes("cpPic"));
				

			}
			
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any driver errors
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
		return couponTypeVO;
	}
	@Override
	public Set<MemberCouponVO> getMemberCouponByCpTpid(Integer memCpid) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	
	
	public static void main(String[] args) {
		
		CouponTypeJDBCDAO dao = new CouponTypeJDBCDAO();
		
		// 新增
		CouponTypeVO couponTypeVO1 = new CouponTypeVO();

		couponTypeVO1.setCpName("吳永志");
		couponTypeVO1.setCpDiscount(50);
		
		couponTypeVO1.setCpStart(java.sql.Date.valueOf("2022-08-01"));
		couponTypeVO1.setCpEnd(java.sql.Date.valueOf("2022-08-10"));
//		couponTypeVO1.setCpStart(LocalDateTime.parse("2022-08-01T00:00:00"));
//		couponTypeVO1.setCpEnd(LocalDateTime.parse("2022-08-10T00:00:00"));
		
		
		couponTypeVO1.setCpStatus(1);
		
		byte[] pic;
		try {
			pic = getPictureByteArray("C:\\01.jpg");
			couponTypeVO1.setCpPic(pic);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		dao.insert(couponTypeVO1);

		// 修改
		CouponTypeVO couponTypeVO2 = new CouponTypeVO();
		couponTypeVO2.setCpTpid(4);
		couponTypeVO2.setCpName("FuFu");
		couponTypeVO2.setCpDiscount(80);
		
		couponTypeVO2.setCpStart(java.sql.Date.valueOf("2022-08-15"));
		couponTypeVO2.setCpEnd(java.sql.Date.valueOf("2022-08-16"));
//		couponTypeVO2.setCpStart(LocalDateTime.parse("2022-08-01T00:00:00"));
//		couponTypeVO2.setCpEnd(LocalDateTime.parse("2022-08-10T00:00:00"));
		
		
		couponTypeVO2.setCpStatus(1);
		
		byte[] pic2;
		
		try {
			pic2 = getPictureByteArray("C:\\02.jpg");
			couponTypeVO2.setCpPic(pic2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		dao.update(couponTypeVO2);

		// 刪除
		dao.delete(3);

		// 查詢
		CouponTypeVO couponTypeVO3 = dao.findByPrimaryKey(2);
		System.out.print(couponTypeVO3.getCpTpid() + ",");
		System.out.print(couponTypeVO3.getCpName() + ",");
		System.out.print(couponTypeVO3.getCpDiscount() + ",");
		System.out.print(couponTypeVO3.getCpStart() + ",");
		System.out.print(couponTypeVO3.getCpEnd() + ",");
		System.out.print(couponTypeVO3.getCpStatus() + ",");
		System.out.println(couponTypeVO3.getCpPic());
		System.out.println("---------------------");
		

		// 查詢
		List<CouponTypeVO> list = dao.getAll();
		for (CouponTypeVO aCouponType : list) {
			System.out.print(aCouponType.getCpTpid() + ",");
			System.out.print(aCouponType.getCpName() + ",");
			System.out.print(aCouponType.getCpDiscount() + ",");
			System.out.print(aCouponType.getCpStart() + ",");
			System.out.print(aCouponType.getCpEnd() + ",");
			System.out.print(aCouponType.getCpStatus() + ",");
			System.out.print(aCouponType.getCpPic());
			System.out.println();
		}
		
	}

	@Override
	public List<CouponTypeVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}



}
