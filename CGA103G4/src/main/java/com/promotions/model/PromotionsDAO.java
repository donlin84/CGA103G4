package com.promotions.model;

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


import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_PromotionsDetail;

public class PromotionsDAO implements PromotionsDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/cga103g4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private static final String INSERT_STMT = "insert into cga103g4.Promotions(pmName,pmDescription,pmDiscount,pmStart,pmEnd,pmStatus)"
			+ " VALUES (?, ?, ?, ?, ?, ?)";

	private static final String UPDATE = "UPDATE cga103g4.Promotions set pmName=?,pmDescription=?,pmDiscount=?,pmStart=?,pmEnd=?,pmStatus=?"
			+ " where pmid = ?";

	private static final String DELETE = "DELETE FROM cga103g4.Promotions where pmid = ?";

	private static final String GET_ALL_STMT = "SELECT pmid,pmName,pmDescription,pmDiscount,pmStart,pmEnd,pmStatus"
			+ " FROM cga103g4.Promotions order by pmid";

	private static final String GET_ONE_STMT = "SELECT pmid,pmName,pmDescription,pmDiscount,pmStart,pmEnd,pmStatus"
			+ " FROM cga103g4.Promotions where pmid = ?";

	@Override
	public void insert(PromotionsVO promotionsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, promotionsVO.getPmName());
			pstmt.setString(2, promotionsVO.getPmDescription());
			pstmt.setDouble(3, promotionsVO.getPmDiscount());
					
			pstmt.setDate(4, promotionsVO.getPmStart());
			pstmt.setDate(5, promotionsVO.getPmEnd());
//			pstmt.setObject(4, promotionsVO.getPmStart());
//			pstmt.setObject(5, promotionsVO.getPmEnd());
			
			pstmt.setInt(6, promotionsVO.getPmStatus());

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
	public void update(PromotionsVO promotionsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, promotionsVO.getPmName());
			pstmt.setString(2, promotionsVO.getPmDescription());
			pstmt.setDouble(3, promotionsVO.getPmDiscount());
						
			pstmt.setDate(4, promotionsVO.getPmStart());
			pstmt.setDate(5, promotionsVO.getPmEnd());
//			pstmt.setObject(4, promotionsVO.getPmStart());
//			pstmt.setObject(5, promotionsVO.getPmEnd());
			
			pstmt.setInt(6, promotionsVO.getPmStatus());
			pstmt.setInt(7, promotionsVO.getPmid());

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
	public void delete(Integer pmid) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, pmid);

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
	public List<PromotionsVO> getAll() {
		List<PromotionsVO> list = new ArrayList<PromotionsVO>();
		PromotionsVO promotionsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {

				promotionsVO = new PromotionsVO();
				promotionsVO.setPmid(rs.getInt("pmid"));
				promotionsVO.setPmName(rs.getString("pmName"));
				promotionsVO.setPmDescription(rs.getString("pmDescription"));
				promotionsVO.setPmDiscount(rs.getDouble("pmDiscount"));
				
				promotionsVO.setPmStart(rs.getDate("pmStart"));
				promotionsVO.setPmEnd(rs.getDate("pmEnd"));
//				promotionsVO.setPmStart(rs.getObject("pmStart",LocalDateTime.class));
//				promotionsVO.setPmEnd(rs.getObject("pmEnd",LocalDateTime.class));
				
				promotionsVO.setPmStatus(rs.getInt("pmStatus"));

				list.add(promotionsVO);

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
	public PromotionsVO findByPrimaryKey(Integer pmid) {
		PromotionsVO promotionsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, pmid);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				promotionsVO = new PromotionsVO();
				promotionsVO.setPmid(rs.getInt("pmid"));
				promotionsVO.setPmName(rs.getString("pmName"));
				promotionsVO.setPmDescription(rs.getString("pmDescription"));
				promotionsVO.setPmDiscount(rs.getDouble("pmDiscount"));
				
				promotionsVO.setPmStart(rs.getDate("pmStart"));
				promotionsVO.setPmEnd(rs.getDate("pmEnd"));
//				promotionsVO.setPmStart(rs.getObject("pmStart",LocalDateTime.class));
//				promotionsVO.setPmEnd(rs.getObject("pmEnd",LocalDateTime.class));
				
				promotionsVO.setPmStatus(rs.getInt("pmStatus"));
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
		return promotionsVO;
	}

	@Override
	public List<PromotionsVO> getAll(Map<String, String[]> map) {
		List<PromotionsVO> list = new ArrayList<PromotionsVO>();
		PromotionsVO promotionsVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from Promotions "
		          + jdbcUtil_CompositeQuery_PromotionsDetail.get_WhereCondition(map)
		          + "order by pmDiscount";
			pstmt = con.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				promotionsVO = new PromotionsVO();
				promotionsVO.setPmid(rs.getInt("pmid"));
				promotionsVO.setPmName(rs.getString("pmName"));
				promotionsVO.setPmDescription(rs.getString("pmDescription"));
				promotionsVO.setPmDiscount(rs.getDouble("pmDiscount"));
				promotionsVO.setPmStart(rs.getDate("pmStart"));
				promotionsVO.setPmEnd(rs.getDate("pmEnd"));
				promotionsVO.setPmStatus(rs.getInt("pmStatus"));
				list.add(promotionsVO); // Store the row in the List
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

}
