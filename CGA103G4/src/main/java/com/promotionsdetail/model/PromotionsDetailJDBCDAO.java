package com.promotionsdetail.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_PromotionsDetail;

public class PromotionsDetailJDBCDAO implements PromotionsDetailDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga103g4?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "cga103g4";

	private static final String INSERT_STMT = "INSERT INTO cga103g4.PromotionsDetail (pmid,pdid,pmPdDiscountPrice) VALUES (?, ?, ?)";

	private static final String UPDATE = "UPDATE cga103g4.PromotionsDetail set pdid=?, pmPdDiscountPrice=? where pmid = ?";

	private static final String GET_ONE_STMT = "SELECT pmid,pdid,pmPdDiscountPrice FROM cga103g4.PromotionsDetail where pmid = ? && pdid = ?";

	private static final String GET_ALL_STMT = "SELECT pmid,pdid,pmPdDiscountPrice FROM cga103g4.PromotionsDetail order by pmid";

	private static final String GET_ONE_BY_PMID = "SELECT pmid,pdid,pmPdDiscountPrice FROM cga103g4.PromotionsDetail where pmid = ?";

	private static final String GET_ONE_BY_PDID = "SELECT pmid,pdid,pmPdDiscountPrice FROM cga103g4.PromotionsDetail where pdid = ?";

	// =============冠銓新增
	private static final String GET_ONE_PROMO_BY_PDID = "SELECT pmid,pdid,pmPdDiscountPrice FROM Cga103G4.PromotionsDetail where pdid = ?";

	@Override
	public void insert(PromotionsDetailVO promotionsDetailVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public List<JoinAllVO> getAllGetNotPromationsProducts() {

		List<JoinAllVO> list = new ArrayList<JoinAllVO>();
		JoinAllVO joinAllVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				joinAllVO = new JoinAllVO();
				joinAllVO.setPmid(rs.getInt("t.pmid"));
				joinAllVO.setPmName(rs.getString("pmName"));
				joinAllVO.setPmDiscount(rs.getDouble("pmdiscount"));
				joinAllVO.setPdid(rs.getInt("t.pdid"));
				joinAllVO.setPdName(rs.getString("pdName"));
				joinAllVO.setPdPrice(rs.getInt("pdPrice"));
				joinAllVO.setPdDiscountPrice(rs.getInt("pdDiscountPrice"));
				joinAllVO.setPmPdDiscountPrice(rs.getInt("pmPdDiscountPrice"));
				if (rs.wasNull()) {
					joinAllVO.setPmPdDiscountPrice(null);
				}
				list.add(joinAllVO);
			}
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

	@Override
	public List<PromotionsDetailVO> getAll(Map<String, String[]> map) {
		List<PromotionsDetailVO> list = new ArrayList<PromotionsDetailVO>();
		PromotionsDetailVO promotionsDetailVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			String finalSQL = "select * from promotionsdetail "
					+ jdbcUtil_CompositeQuery_PromotionsDetail.get_WhereCondition(map) + "order by pmPdDiscountPrice";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = " + finalSQL);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				promotionsDetailVO = new PromotionsDetailVO();
				promotionsDetailVO.setPmid(rs.getInt("pmid"));
				promotionsDetailVO.setPdid(rs.getInt("pdid"));
				promotionsDetailVO.setPmPdDiscountPrice(rs.getInt("pmPdDiscountPrice"));
				list.add(promotionsDetailVO); // Store the row in the List
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

	// ====冠銓新增
	@Override
	public PromotionsDetailVO getOnePmidByPdid(Integer pdid) {

		PromotionsDetailVO promotionsDetailVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_PROMO_BY_PDID);

			pstmt.setInt(1, pdid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain object
				promotionsDetailVO = new PromotionsDetailVO();
				promotionsDetailVO.setPmid(rs.getInt("pmid"));
				promotionsDetailVO.setPdid(rs.getInt("pdid"));
				promotionsDetailVO.setPmPdDiscountPrice(rs.getInt("pmPdDiscountPrice"));
				// null相關
			}

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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

	public static void main(String[] args) {
		PromotionsDetailJDBCDAO dao = new PromotionsDetailJDBCDAO();

		// 新增
		PromotionsDetailVO promotionsDetailVO1 = new PromotionsDetailVO();

		promotionsDetailVO1.setPmid(1);
		promotionsDetailVO1.setPdid(4002);
		promotionsDetailVO1.setPmPdDiscountPrice(250);

		dao.insert(promotionsDetailVO1);

		// 修改
		PromotionsDetailVO promotionsDetailVO2 = new PromotionsDetailVO();
		promotionsDetailVO2.setPmid(4);
		promotionsDetailVO2.setPdid(4002);
		promotionsDetailVO2.setPmPdDiscountPrice(200);

		dao.update(promotionsDetailVO2);

		// 刪除
		dao.findByPrimaryKey(4, 4002);

		// Pmid查詢
		List<PromotionsDetailVO> listPmid = dao.findByPmid(1);
		for (PromotionsDetailVO Pmid : listPmid) {
			System.out.print(Pmid.getPmid() + ",");
			System.out.print(Pmid.getPdid() + ",");
			System.out.print(Pmid.getPmPdDiscountPrice() + ",");
			System.out.println();
		}
		System.out.println("---------------------");
		// Pdid查詢
		List<PromotionsDetailVO> listPdid = dao.findByPdid(4002);
		for (PromotionsDetailVO Pdid : listPdid) {
			System.out.print(Pdid.getPmid() + ",");
			System.out.print(Pdid.getPdid() + ",");
			System.out.print(Pdid.getPmPdDiscountPrice() + ",");
			System.out.println();
		}
		System.out.println("---------------------");
		// 查詢
		List<PromotionsDetailVO> list = dao.getAll();
		for (PromotionsDetailVO apromotionsDetail : list) {
			System.out.print(apromotionsDetail.getPmid() + ",");
			System.out.print(apromotionsDetail.getPdid() + ",");
			System.out.print(apromotionsDetail.getPmPdDiscountPrice() + ",");
			System.out.println();
		}
	}

}
