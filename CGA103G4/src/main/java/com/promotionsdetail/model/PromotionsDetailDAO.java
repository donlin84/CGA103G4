package com.promotionsdetail.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class PromotionsDetailDAO implements PromotionsDetailDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/cga103g4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "INSERT INTO cga103g4.PromotionsDetail (pmid,pdid,pmPdDiscountPrice) VALUES (?, ?, ?)";

	private static final String UPDATE = "UPDATE cga103g4.PromotionsDetail set pdid=?, pmPdDiscountPrice=? where pmid = ?";

	private static final String GET_ONE_STMT = "SELECT pmid,pdid,pmPdDiscountPrice FROM cga103g4.PromotionsDetail where pmid = ? && pdid = ?";

	private static final String GET_ALL_STMT = "SELECT pmid,pdid,pmPdDiscountPrice FROM cga103g4.PromotionsDetail order by pmid";

	private static final String GET_ONE_BY_PMID = "SELECT pmid,pdid,pmPdDiscountPrice FROM cga103g4.PromotionsDetail where pmid = ?";

	private static final String GET_ONE_BY_PDID = "SELECT pmid,pdid,pmPdDiscountPrice FROM cga103g4.PromotionsDetail where pdid = ?";

	@Override
	public void insert(PromotionsDetailVO promotionsDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, promotionsDetailVO.getPmid());
			pstmt.setInt(2, promotionsDetailVO.getPdid());

//			pstmt.setInt(3, promotionsDetailVO.getPmPdDiscountPrice());
			Integer pmPdDiscountPrice = promotionsDetailVO.getPmPdDiscountPrice();
			if (pmPdDiscountPrice == null) {
				pstmt.setNull(3, Types.INTEGER);
			} else {
				pstmt.setInt(3, pmPdDiscountPrice);
			}

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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

	@Override
	public void update(PromotionsDetailVO promotionsDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setInt(1, promotionsDetailVO.getPdid());

//			pstmt.setInt(2, promotionsDetailVO.getPmPdDiscountPrice());
			Integer pmPdDiscountPrice = promotionsDetailVO.getPmPdDiscountPrice();
			if (pmPdDiscountPrice == null) {
				pstmt.setNull(2, Types.INTEGER);
			} else {
				pstmt.setInt(2, pmPdDiscountPrice);
			}

			pstmt.setInt(3, promotionsDetailVO.getPmid());

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
				} catch (SQLException e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public PromotionsDetailVO findByPrimaryKey(Integer pmid, Integer pdid) {

		PromotionsDetailVO promotionsDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, pmid);
			pstmt.setInt(2, pdid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain object
				promotionsDetailVO = new PromotionsDetailVO();
				promotionsDetailVO.setPmid(rs.getInt("pmid"));
				promotionsDetailVO.setPdid(rs.getInt("pdid"));
				promotionsDetailVO.setPmPdDiscountPrice(rs.getInt("pmPdDiscountPrice"));
				// null相關
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
		return promotionsDetailVO;
	}

	@Override
	public List<PromotionsDetailVO> getAll() {

		List<PromotionsDetailVO> list = new ArrayList<PromotionsDetailVO>();
		PromotionsDetailVO promotionsDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				promotionsDetailVO = new PromotionsDetailVO();
				promotionsDetailVO.setPmid(rs.getInt("pmid"));
				promotionsDetailVO.setPdid(rs.getInt("pdid"));

//				promotionsDetailVO.setPmPdDiscountPrice(rs.getInt("pmPdDiscountPrice"));
				if (rs.wasNull()) {
					promotionsDetailVO.setPmPdDiscountPrice(null);
				}

				list.add(promotionsDetailVO);
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
	public List<PromotionsDetailVO> findByPmid(Integer pmid) {

		List<PromotionsDetailVO> list = new ArrayList<PromotionsDetailVO>();
		PromotionsDetailVO promotionsDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_PMID);
			pstmt.setInt(1, pmid);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				promotionsDetailVO = new PromotionsDetailVO();
				promotionsDetailVO.setPmid(rs.getInt("pmid"));
				promotionsDetailVO.setPdid(rs.getInt("pdid"));

//				promotionsDetailVO.setPmPdDiscountPrice(rs.getInt("pmPdDiscountPrice"));
				if (rs.wasNull()) {
					promotionsDetailVO.setPmPdDiscountPrice(null);
				}

				list.add(promotionsDetailVO);
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
	public List<PromotionsDetailVO> findByPdid(Integer pdid) {

		List<PromotionsDetailVO> list = new ArrayList<PromotionsDetailVO>();
		PromotionsDetailVO promotionsDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_BY_PDID);
			pstmt.setInt(1, pdid);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				promotionsDetailVO = new PromotionsDetailVO();
				promotionsDetailVO.setPmid(rs.getInt("pmid"));
				promotionsDetailVO.setPdid(rs.getInt("pdid"));

//				promotionsDetailVO.setPmPdDiscountPrice(rs.getInt("pmPdDiscountPrice"));
				if (rs.wasNull()) {
					promotionsDetailVO.setPmPdDiscountPrice(null);
				}

				list.add(promotionsDetailVO);
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
}