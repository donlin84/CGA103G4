package com.coupontype.model;

import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CouponTypeDAO implements CouponTypeDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/cga103g4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "insert into cga103g4.CouponType(cpname,cpdiscount,cpstart,cpend,cpstatus,cppic)"
			+ " VALUES (?, ?, ?, ?, ?, ?)";

	private static final String UPDATE = "UPDATE cga103g4.CouponType set cpname=?,cpdiscount=?,cpstart=?,cpend=?,cpstatus=?,cppic=? "
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

			con = ds.getConnection();
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

			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, cpTpid);

			pstmt.executeUpdate();

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

			con = ds.getConnection();
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
	public CouponTypeVO findByPrimaryKey(Integer cpTpid) {
		CouponTypeVO couponTypeVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
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
		return couponTypeVO;
	}
<<<<<<< HEAD

=======
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	
>>>>>>> refs/remotes/origin/sosohung
}
