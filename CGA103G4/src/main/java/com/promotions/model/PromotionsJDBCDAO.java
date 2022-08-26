package com.promotions.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PromotionsJDBCDAO implements PromotionsDAO_interface {

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga103g4?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "sa4850869";

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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
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
	public void update(PromotionsVO promotionsVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public void delete(Integer pmid) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, pmid);

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
	public List<PromotionsVO> getAll() {
		List<PromotionsVO> list = new ArrayList<PromotionsVO>();
		PromotionsVO promotionsVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
	public PromotionsVO findByPrimaryKey(Integer pmid) {
		PromotionsVO promotionsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		return promotionsVO;
	}

	public static void main(String[] args) {
		PromotionsJDBCDAO dao = new PromotionsJDBCDAO();

		// 新增
		PromotionsVO promotionsVO1 = new PromotionsVO();

		promotionsVO1.setPmName("春節特惠");
		promotionsVO1.setPmDescription("所有年貨相關商品打折");
		promotionsVO1.setPmDiscount(0.85);
		promotionsVO1.setPmStart(java.sql.Date.valueOf("2022-08-10"));
		promotionsVO1.setPmEnd(java.sql.Date.valueOf("2022-08-16"));
//		promotionsVO1.setPmStart(LocalDateTime.parse("2022-08-10T00:00:00"));
//		promotionsVO1.setPmEnd(LocalDateTime.parse("2022-08-16T00:00:00"));
		promotionsVO1.setPmStatus(1);
		
		dao.insert(promotionsVO1);

		// 修改
		PromotionsVO promotionsVO2 = new PromotionsVO();
		promotionsVO2.setPmid(3);
		promotionsVO2.setPmName("端午節特惠");
		promotionsVO2.setPmDescription("所有端午節相關商品打折");
		promotionsVO2.setPmDiscount(0.88);
		promotionsVO2.setPmStart(java.sql.Date.valueOf("2022-08-16"));
		promotionsVO2.setPmEnd(java.sql.Date.valueOf("2022-08-17"));
//		promotionsVO2.setPmStart(LocalDateTime.parse("2022-08-16T00:00:00"));
//		promotionsVO2.setPmEnd(LocalDateTime.parse("2022-08-17T00:00:00"));
		promotionsVO2.setPmStatus(1);

		dao.update(promotionsVO2);

		// 刪除
		dao.delete(3);

		// 查詢
		PromotionsVO promotionsVO3 = dao.findByPrimaryKey(2);
		System.out.print(promotionsVO3.getPmid() + ",");
		System.out.print(promotionsVO3.getPmName() + ",");
		System.out.print(promotionsVO3.getPmDescription() + ",");
		System.out.print(promotionsVO3.getPmDiscount() + ",");
		System.out.print(promotionsVO3.getPmStart() + ",");
		System.out.print(promotionsVO3.getPmEnd() + ",");
		System.out.println(promotionsVO3.getPmStatus());
		System.out.println("---------------------");

		// 查詢
		List<PromotionsVO> list = dao.getAll();
		for (PromotionsVO aPromotion : list) {
			System.out.print(aPromotion.getPmid() + ",");
			System.out.print(aPromotion.getPmName() + ",");
			System.out.print(aPromotion.getPmDescription() + ",");
			System.out.print(aPromotion.getPmDiscount() + ",");
			System.out.print(aPromotion.getPmStart() + ",");
			System.out.print(aPromotion.getPmEnd() + ",");
			System.out.print(aPromotion.getPmStatus());
			System.out.println();
		}

	}

}
