package com.productSort.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ProductsortDAO implements ProductsortDAO_interface {

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc//Cga103G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT = 
	 "INSERT INTO Productsort (pdsName) VALUES (?)";
	private static final String GET_ALL_STMT = 
	"SELECT pdsid,pdsName FROM Productsort order by pdsid";
	private static final String GET_ONE_STMT =
	"SELECT pdsid,pdsName FROM Productsort where pdsid = ?";
	private static final String UPDATE =
	"UPDATE Productsort set pdsName =? where pdsid = ?";
	
	@Override
	public void insert(ProductsortVO productsortVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
	try {
		con = ds.getConnection();
		pstmt = con.prepareStatement(INSERT_STMT);
		
		pstmt.setString(1, productsortVO.getPdsName());
		
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
	public void update(ProductsortVO productsortVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
		
			pstmt.setString(1, productsortVO.getPdsName());
			pstmt.setInt(2, productsortVO.getPdsid());


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
	public ProductsortVO findByPrimaryKey(Integer pdsid) {
		
		ProductsortVO productsortVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
			
				
				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, pdsid);

				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					productsortVO = new ProductsortVO();
					
					productsortVO.setPdsid(rs.getInt("pdsid"));
					productsortVO.setPdsName(rs.getString("pdsName"));
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
			return productsortVO;
		}
	@Override
	public List<ProductsortVO> getAll() {
		List<ProductsortVO> list = new ArrayList<ProductsortVO>();
		ProductsortVO productsortVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				productsortVO = new ProductsortVO();
				productsortVO.setPdsid(rs.getInt("pdsid"));
				productsortVO.setPdsName(rs.getString("pdsName"));
				
				list.add(productsortVO); 
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

		ProductsortDAO dao = new ProductsortDAO();

		// 新增
//		ProductsortVO productsortVO1 = new ProductsortVO();
//		productsortVO1.setPdsName("魚翅");
//		dao.insert(productsortVO1);
//		System.out.println ("Succeed to insert");
	
	// 修改
		ProductsortVO productsortVO2 = new ProductsortVO();
		
		productsortVO2.setPdsName("三星筆電");
		productsortVO2.setPdsid(4);
		
			dao.update(productsortVO2);
			System.out.println ("Succeed to update");
	// 查詢	
		ProductsortVO productsortVO3 = dao.findByPrimaryKey(1);
		System.out.print(productsortVO3.getPdsid() + ",");
		System.out.print(productsortVO3.getPdsName() + ",");
		System.out.println();
	// 查詢
		List<ProductsortVO> list = dao.getAll();
		for (ProductsortVO aProductsort : list) {
		System.out.print(aProductsort.getPdsid() + ",");	
		System.out.print(aProductsort.getPdsName() + ",");
	
	}
	}
}
