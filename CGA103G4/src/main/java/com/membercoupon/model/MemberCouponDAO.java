package com.membercoupon.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberCouponDAO implements MemberCouponDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/cga103g4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

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

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, memberCouponVO.getMemid());
			pstmt.setInt(2, memberCouponVO.getCpTpid());
			
//			pstmt.setDate(3, memberCouponVO.getMemCpDate());
			pstmt.setObject(3, memberCouponVO.getMemCpDate());
			
			pstmt.setInt(4, memberCouponVO.getMemCpStatus());
			
//			pstmt.setDate(5, memberCouponVO.getMemCpRecord());
			pstmt.setObject(5, memberCouponVO.getMemCpRecord());

			pstmt.executeUpdate();

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
			con = ds.getConnection();
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

			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, memCpid);

			pstmt.executeUpdate();

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
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
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
				
				list.add(memberCouponVO);

			}

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
			con = ds.getConnection();
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

}
