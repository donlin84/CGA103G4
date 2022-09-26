package com.membercoupon.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.member.model.MemberVO;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_MemberCoupon;

public class MemberCouponDAO implements MemberCouponDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Cga103G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO cga103g4.MemberCoupon (memid,cpTpid,memCpDate,memCpStatus,memCpRecord) VALUES (?, ?, ?, ?, ?)";

	private static final String UPDATE = "UPDATE cga103g4.MemberCoupon set memid=?, cpTpid=?, memCpDate=?, memCpStatus=?, memCpRecord=? where memCpid = ?";

	private static final String DELETE = "DELETE FROM cga103g4.MemberCoupon where memCpid = ?";

	private static final String GET_ALL_STMT = "SELECT memCpid,memid,cpTpid,memCpDate,memCpStatus,memCpRecord FROM cga103g4.MemberCoupon order by memCpid";

	private static final String GET_ONE_STMT = "SELECT memCpid,memid,cpTpid,memCpDate,memCpStatus,memCpRecord FROM cga103g4.MemberCoupon where memCpid = ?";
//======================================亦翔新增=======================================	
	private static final String TRANS_UPDATE = "UPDATE cga103g4.MemberCoupon set memCpStatus = ? where memCpid = ?";
	@Override
	public void insert(MemberCouponVO memberCouponVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, memberCouponVO.getMemid());
			pstmt.setInt(2, memberCouponVO.getCpTpid());

			pstmt.setDate(3, memberCouponVO.getMemCpDate());
//			pstmt.setObject(3, memberCouponVO.getMemCpDate());

			pstmt.setInt(4, memberCouponVO.getMemCpStatus());

			pstmt.setDate(5, memberCouponVO.getMemCpRecord());
//			pstmt.setObject(5, memberCouponVO.getMemCpRecord());

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

			pstmt.setDate(3, memberCouponVO.getMemCpDate());
//			pstmt.setObject(3, memberCouponVO.getMemCpDate());

			pstmt.setInt(4, memberCouponVO.getMemCpStatus());

			pstmt.setDate(5, memberCouponVO.getMemCpRecord());
//			pstmt.setObject(5, memberCouponVO.getMemCpRecord());

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

				memberCouponVO.setMemCpDate(rs.getDate("memCpDate"));
//				memberCouponVO.setMemCpDate(rs.getObject("memCpDate",LocalDateTime.class));

				memberCouponVO.setMemCpStatus(rs.getInt("memCpStatus"));

				memberCouponVO.setMemCpRecord(rs.getDate("memCpRecord"));
//				memberCouponVO.setMemCpRecord(rs.getObject("memCpRecord",LocalDateTime.class));

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

				memberCouponVO.setMemCpDate(rs.getDate("memCpDate"));
//				memberCouponVO.setMemCpDate(rs.getObject("memCpDate",LocalDateTime.class));

				memberCouponVO.setMemCpStatus(rs.getInt("memCpStatus"));

				memberCouponVO.setMemCpRecord(rs.getDate("memCpRecord"));
//				memberCouponVO.setMemCpRecord(rs.getObject("memCpRecord",LocalDateTime.class));

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

	@Override
	public List<MemberCouponVO> getAll(Map<String, String[]> map) {
		List<MemberCouponVO> list = new ArrayList<MemberCouponVO>();
		MemberCouponVO memberCouponVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			String finalSQL = "select * from memberCoupon "
		          + jdbcUtil_CompositeQuery_MemberCoupon.get_WhereCondition(map)
		          + "order by memcpid";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				memberCouponVO = new MemberCouponVO();
				memberCouponVO.setMemCpid(rs.getInt("memCpid"));
				memberCouponVO.setMemid(rs.getInt("memid"));
				memberCouponVO.setCpTpid(rs.getInt("cpTpid"));

				memberCouponVO.setMemCpDate(rs.getDate("memCpDate"));
//				memberCouponVO.setMemCpDate(rs.getObject("memCpDate",LocalDateTime.class));

				memberCouponVO.setMemCpStatus(rs.getInt("memCpStatus"));

				memberCouponVO.setMemCpRecord(rs.getDate("memCpRecord"));
//				memberCouponVO.setMemCpRecord(rs.getObject("memCpRecord",LocalDateTime.class));

				list.add(memberCouponVO);
			}
	
			// Handle any SQL errors
		} catch (SQLException se) {
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
	
//	=========================================亦翔新增==============================================
	@Override
	public void transUpdate(Integer memCpStatus, Integer memCpid, Connection con) {
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(TRANS_UPDATE);
			
			ps.setInt(1, memCpStatus);
			ps.setInt(2, memCpid);
			
			ps.executeUpdate();
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-會員優惠券");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. " + excep.getMessage());
				}
			}
			throw new RuntimeException("A database error occured. " + se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (ps != null) {
				try {
					ps.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
		}
	}

}
