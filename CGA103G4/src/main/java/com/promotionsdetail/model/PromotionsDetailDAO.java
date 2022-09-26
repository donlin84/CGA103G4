package com.promotionsdetail.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_PromotionsDetail;

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

	private static final String UPDATE = "UPDATE cga103g4.PromotionsDetail set pmPdDiscountPrice=? where pmid = ? && pdid = ?";

	private static final String GET_ONE_STMT = "SELECT pmid,pdid,pmPdDiscountPrice FROM cga103g4.PromotionsDetail where pmid = ? && pdid = ?";

	private static final String GET_ALL_STMT = "SELECT pmid,pdid,pmPdDiscountPrice FROM cga103g4.PromotionsDetail order by pmid";

	private static final String GET_ONE_BY_PMID = "SELECT pmid,pdid,pmPdDiscountPrice FROM cga103g4.PromotionsDetail where pmid = ?";

	private static final String GET_ONE_BY_PDID = "SELECT pmid,pdid,pmPdDiscountPrice FROM cga103g4.PromotionsDetail where pdid = ?";
	
	private static final String GET_NOT_PROMATIONS_PRODUCTS ="select t.pmid , pmName , pmdiscount , t.pdid , pdName , pdPrice , pdDiscountPrice , pmpdDiscountPrice "
			+ "from "
			+ "	(select * from promotions cross join product) "
			+ "    left join promotionsdetail pd "
			+ "		on t.pmid = pd.pmid and t.pdid = pd.pdid "
			+ "where "
			+ "	pd.pmid is null";
//	===============冠銓新增
private static final String GET_ONE_PROMO_BY_PDID = "SELECT pmid,pdid,pmPdDiscountPrice FROM Cga103G4.PromotionsDetail where pdid = ?";
	
	private static final String DELETE_ONE_DETAIL_BY_PDID = "DELETE FROM cga103g4.PromotionsDetail where pdid = ?";
		
		
		
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

//			pstmt.setInt(1, promotionsDetailVO.getPmPdDiscountPrice());
			Integer pmPdDiscountPrice = promotionsDetailVO.getPmPdDiscountPrice();
			if (pmPdDiscountPrice == null) {
				pstmt.setNull(2, Types.INTEGER);
			} else {
				pstmt.setInt(1, pmPdDiscountPrice);
			}
			pstmt.setInt(2, promotionsDetailVO.getPmid());
			pstmt.setInt(3, promotionsDetailVO.getPdid());
			
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
				promotionsDetailVO.setPmPdDiscountPrice(rs.getInt("pmPdDiscountPrice"));
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
				promotionsDetailVO.setPmPdDiscountPrice(rs.getInt("pmPdDiscountPrice"));
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
				promotionsDetailVO.setPmPdDiscountPrice(rs.getInt("pmPdDiscountPrice"));
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
	public List<PromotionsDetailVO> getAll(Map<String, String[]> map) {
		List<PromotionsDetailVO> list = new ArrayList<PromotionsDetailVO>();
		PromotionsDetailVO promotionsDetailVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from promotionsdetail "
		          + jdbcUtil_CompositeQuery_PromotionsDetail.get_WhereCondition(map)
		          + "order by pmPdDiscountPrice";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				promotionsDetailVO = new PromotionsDetailVO();
				promotionsDetailVO.setPmid(rs.getInt("pmid"));
				promotionsDetailVO.setPdid(rs.getInt("pdid"));
				promotionsDetailVO.setPmPdDiscountPrice(rs.getInt("pmPdDiscountPrice"));
				list.add(promotionsDetailVO); // Store the row in the List
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
	
	
	@Override
	public List<JoinAllVO> getAllGetNotPromationsProducts() {

		List<JoinAllVO> list = new ArrayList<JoinAllVO>();
		JoinAllVO joinAllVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_NOT_PROMATIONS_PRODUCTS);
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
	//====================================================================冠銓新增
		@Override
		public PromotionsDetailVO getOnePmidByPdid(Integer pdid) {
			 

				PromotionsDetailVO promotionsDetailVO = null;
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;

				try {

					con = ds.getConnection();
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
		public PromotionsDetailVO deleteOnePromoDetailVO(Integer pdid) {
			
				Connection con = null;
				PreparedStatement pstmt = null;

				try {
					con = ds.getConnection();
					pstmt = con.prepareStatement(DELETE_ONE_DETAIL_BY_PDID);

					pstmt.setInt(1, pdid);

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
			
			return null;
		}
	}