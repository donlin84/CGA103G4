package com.product.model;

import java.sql.*;
import java.sql.Connection;

import java.time.LocalDateTime;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductDAO implements ProductDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/seefooddata");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
		"INSERT INTO Product (pdsid,pdName,pdPrice,pdDiscountPrice,pdDescription,pdStatus,pdUpdate) VALUES (?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT pdid,pdsid,pdName,pdPrice,pdDiscountPrice,pdDescription,pdStatus,pdUpdate FROM Product order by pdid";
	private static final String GET_ONE_STMT =
		"SELECT pdid,pdsid,pdName,pdPrice,pdDiscountPrice,pdDescription,pdStatus,pdUpdate FROM Product where pdid = ?";
	private static final String UPDATE =
		"UPDATE Product set pdsid=?, pdName=?, pdPrice=?, pdDiscountPrice=?, pdDescription=?, pdStatus=?, pdUpdate=? where pdid = ?";
	@Override
	public void insert(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
	try {
		con = ds.getConnection();
		pstmt = con.prepareStatement(INSERT_STMT);
		
		pstmt.setInt(1, productVO.getPdsid());
		pstmt.setString(2, productVO.getPdName());
		pstmt.setInt(3, productVO.getPdPrice());
		pstmt.setInt(4, productVO.getPdDiscountPrice());
		pstmt.setString(5, productVO.getPdDescription());
		pstmt.setInt(6, productVO.getPdStatus());
		pstmt.setObject(7, productVO.getPdUpdate());
		
		pstmt.executeUpdate();
		
	} catch (SQLException se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
	} finally {
		if(pstmt != null) {
			try {
				pstmt.close();
			}catch(SQLException se) {
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
	public void update(ProductVO productVO) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
		
			pstmt.setInt(1, productVO.getPdsid());
			pstmt.setString(2, productVO.getPdName());
			pstmt.setInt(3, productVO.getPdPrice());
			pstmt.setInt(4, productVO.getPdDiscountPrice());
			pstmt.setString(5, productVO.getPdDescription());
			pstmt.setInt(6, productVO.getPdStatus());
			pstmt.setObject(7, productVO.getPdUpdate());
			pstmt.setInt(8, productVO.getPdid());

			pstmt.executeUpdate();
		
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
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
	public ProductVO findByPrimaryKey(Integer pdid) {
		ProductVO productVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, pdid);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productVO = new ProductVO();
				
				productVO.setPdid(rs.getInt("pdid"));
				productVO.setPdsid(rs.getInt("pdsid"));
				productVO.setPdName(rs.getString("pdName"));
				productVO.setPdPrice(rs.getInt("pdPrice"));
				productVO.setPdDiscountPrice(rs.getInt("pdDiscountPrice"));
				productVO.setPdDescription(rs.getString("pdDescription"));
				productVO.setPdStatus(rs.getInt("pdStatus"));
				productVO.setPdUpdate(rs.getObject("pdUpdate",LocalDateTime.class));
				
			}

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
		return productVO;
	}
			
	@Override
	public List<ProductVO> getAll() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				
				productVO = new ProductVO();
				productVO.setPdid(rs.getInt("pdid"));
				productVO.setPdsid(rs.getInt("pdsid"));
				productVO.setPdName(rs.getString("pdName"));
				productVO.setPdPrice(rs.getInt("pdPrice"));
				productVO.setPdDiscountPrice(rs.getInt("pdDiscountPrice"));
				productVO.setPdDescription(rs.getString("pdDescription"));
				productVO.setPdStatus(rs.getInt("pdStatus"));
				productVO.setPdUpdate(rs.getObject("pdUpdate",LocalDateTime.class));
				list.add(productVO); 
			}
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
	public static void main(String[] args) {

		ProductDAO dao = new ProductDAO();

		//新增
		ProductVO productVO1 = new ProductVO();
		productVO1.setPdsid(2);
		productVO1.setPdName("utf188");
		productVO1.setPdPrice(900);
		productVO1.setPdDiscountPrice(900);
		productVO1.setPdDescription("憭剖ˊ霈�");
		productVO1.setPdStatus(1);
		productVO1.setPdUpdate(LocalDateTime.now());
		dao.insert(productVO1);

//		// 修改
//		ProductVO productVO2 = new ProductVO();
//		
//		productVO2.setPdsid(1);
//		productVO2.setPdName("摰��");
//		productVO2.setPdPrice(700);
//		productVO2.setPdDiscountPrice(700);
//		productVO2.setPdDescription("��虜霈��");
//		productVO2.setPdStatus(1);
//		productVO2.setPdUpdate(LocalDateTime.parse("2001-01-01T10:00:00"));
//		productVO2.setPdid(4009);
//		dao.update(productVO2);
//		System.out.println("updateSucceed");

		// 根據pk查詢一個
//		ProductVO productVO3 = dao.findByPrimaryKey(1);
//		System.out.print(productVO3.getPdid() + ",");
//		System.out.print(productVO3.getPdsid() + ",");
//		System.out.print(productVO3.getPdName() + ",");
//		System.out.print(productVO3.getPdPrice() + ",");
//		System.out.print(productVO3.getPdDiscountPrice() + ",");
//		System.out.print(productVO3.getPdDescription() + ",");
//		System.out.println(productVO3.getPdStatus());
//		System.out.println(productVO3.getPdUpdate());
//		System.out.println("---------------------");

		// 查詢全部
//		List<ProductVO> list = dao.getAll();
//		for (ProductVO aProduct : list) {
//			System.out.print(aProduct.getPdid() + ",");
//			System.out.print(aProduct.getPdsid() + ",");
//			System.out.print(aProduct.getPdName() + ",");
//			System.out.print(aProduct.getPdPrice() + ",");
//			System.out.print(aProduct.getPdDiscountPrice() + ",");
//			System.out.print(aProduct.getPdDescription() + ",");
//			System.out.print(aProduct.getPdStatus());
//			System.out.print(aProduct.getPdUpdate());
//			System.out.println();

	}
}
