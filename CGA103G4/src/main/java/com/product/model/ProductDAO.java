package com.product.model;

import java.sql.*;
import java.sql.Connection;

import java.time.LocalDateTime;
import java.util.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.promotions.model.PromotionsVO;
import com.promotionsdetail.model.PromotionsDetailVO;
import com.util.jdbcUtil_CompositeQuery_product;

public class ProductDAO implements ProductDAO_interface {
	
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/cga103g4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_STMT = 
		"INSERT INTO Product (pdsid,pdName,pdPrice,pdDiscountPrice,pdDescription,pdStatus,pdUpdate) VALUES (?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT pdid,pdsid,pdName,pdPrice,pdDiscountPrice,pdDescription,pdStatus,pdUpdate FROM Product order by pdid desc";
	private static final String GET_ONE_STMT =
		"SELECT pdid,pdsid,pdName,pdPrice,pdDiscountPrice,pdDescription,pdStatus,pdUpdate FROM Product where pdid = ?";
	private static final String UPDATE =
		"UPDATE Product set pdsid=?, pdName=?, pdPrice=?, pdDiscountPrice=?, pdDescription=?, pdStatus=?, pdUpdate=? where pdid = ?";
	
	
	private static final String UPDATE_PDSTATUS = //商品管理改上架狀態
			"UPDATE product set pdStatus = ? where pdid = ?";

	
	
	private static final String ALL_PD_NAME = 
		"SELECT pdname from product";
//	========================================================排序+查詢================================================================
	private static final String LIST_ALL_ORDERBY_PDID =
		"SELECT pdid,pdsid,pdName,pdPrice,pdDiscountPrice,pdDescription,pdStatus,pdUpdate FROM Product order by pdid";	
	private static final String LIST_ALL_ORDERBY_PDPRICE_DESC =
			"SELECT pdid,pdsid,pdName,pdPrice,pdDiscountPrice,pdDescription,pdStatus,pdUpdate FROM Product order by pdprice desc";	
	private static final String LIST_ALL_ORDERBY_PDPRICE_ASC =
			"SELECT pdid,pdsid,pdName,pdPrice,pdDiscountPrice,pdDescription,pdStatus,pdUpdate FROM Product order by pdprice";
	private static final String listByPdSort=
		"SELECT pdid,pdsid,pdName,pdPrice,pdDiscountPrice,pdDescription,pdStatus,pdUpdate FROM Product where pdsid = ?";
	private static final String ListByPdStatus=
			"SELECT pdid,pdsid,pdName,pdPrice,pdDiscountPrice,pdDescription,pdStatus,pdUpdate FROM Product where pdStatus = ?";

	
	private static final String LIST_ALL_PD_BY_NAME=
			"SELECT pdid,pdsid,pdName,pdPrice,pdDiscountPrice,pdDescription,pdStatus,pdUpdate FROM Product where pdName like ?";
	
	//	=======================================================新增修改相關=================================================================

	private static final String GET_NEWESTPDID = 
			"SELECT pdid FROM product order by 1 desc limit 1";
	private static final String GET_PROMODISCOUNT = 
			"SELECT pmdiscount FROM promotions where pmid =?";
	
//	=======================================================================================================================
	private static final String GET_ALL_JOIN_PMID = 
			"SELECT b.pmid from product a left join promotionsdetail b on a.pdid = b.pdid order by a.pdid"; 
	
	private static final String GET_NEWESTONE = 
			"SELECT * FROM product order by 1 desc limit 1";
//	===============================================前台查詢=======================================================
	private static final String GET_PD_ON_SHELF_ORDER_BY_PRICE = "SELECT pdid,pdsid,pdName,pdPrice,pdDiscountPrice,pdDescription,pdStatus,pdUpdate FROM Product where pdStatus = 1 order by pdPrice";
	private static final String GET_PD_ON_SHELF_ORDER_BY_PRICE_DESC = "SELECT pdid,pdsid,pdName,pdPrice,pdDiscountPrice,pdDescription,pdStatus,pdUpdate FROM Product where pdStatus = 1 order by pdPrice desc";
	private static final String GET_PD_ON_SHELF_ORDER_BY_PD_UPDATE = "SELECT pdid,pdsid,pdName,pdPrice,pdDiscountPrice,pdDescription,pdStatus,pdUpdate FROM Product where pdStatus = 1 order by pdUpdate";
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
	

	@Override
	public List<ProductVO> getAllPdName() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(ALL_PD_NAME);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				productVO = new ProductVO();
				productVO.setPdName(rs.getString("pdName"));
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
	@Override
	public List<ProductVO> listByPdSort(Integer pdsid) {
		
		List<ProductVO> list = new ArrayList<ProductVO>();
			ProductVO productVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(listByPdSort);

				pstmt.setInt(1, pdsid);

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
	@Override
    public List<ProductVO> listByPdStatus(Integer pdStatus) {
		
		List<ProductVO> list = new ArrayList<ProductVO>();
			ProductVO productVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(ListByPdStatus);
				pstmt.setInt(1, pdStatus);
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
	
	@Override // 複合查詢
	public List<ProductVO> getAll(Map<String, String[]> map) { //複合查詢
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			
			con = ds.getConnection();
			String finalSQL = "select * from product "
		          + jdbcUtil_CompositeQuery_product.get_WhereCondition(map)
		          + "order by pdid";
			pstmt = con.prepareStatement(finalSQL);
			
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
	public ProductVO getNewestPdid() {
		
		
		ProductVO productVO = null;
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_NEWESTPDID);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				productVO = new ProductVO();
				
				productVO.setPdid(rs.getInt("pdid"));
				
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
	public PromotionsVO getPromoDiscount(Integer pmid) {
		PromotionsVO promotionsVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PROMODISCOUNT);

			pstmt.setInt(1, pmid);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				promotionsVO = new PromotionsVO();
				
				promotionsVO.setPmDiscount(rs.getDouble("pmDiscount"));
				
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
		return promotionsVO;
	}

	@Override
	public List<ProductVO> listAllOderByPdid() {
		
//		LIST_ALL_ORDERBY_PDID_
		
			List<ProductVO> list = new ArrayList<ProductVO>();
			ProductVO productVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(LIST_ALL_ORDERBY_PDID);
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
	
	@Override
	public List<ProductVO> listAllOdByPdPriceDesc() {
		// LIST_ALL_ORDERBY_PDPRICE_DESC
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(LIST_ALL_ORDERBY_PDPRICE_DESC);
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
	@Override
	public List<ProductVO> listAllOdByPdPriceAsc() {
		// LIST_ALL_ORDERBY_PDPRICE_DESC
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(LIST_ALL_ORDERBY_PDPRICE_ASC);
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



//	=======================================================================
	
	@Override
	public List<Object> getTop3pd() {
		
		List<Object> list = new ArrayList<>();
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_NEWESTONE);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				Map<String, Object> map =new HashMap<String, Object>();
		
				map.put("pdid", rs.getInt("Pdid"));		
				map.put("pdsid", rs.getInt("Pdsid"));
				map.put("pdName", rs.getString("PdName"));
				map.put("pdPrice", rs.getInt("PdPrice"));
				map.put("pdDiscountPrice", rs.getInt("PdDiscountPrice"));
				map.put("pdDescription", rs.getInt("PdDescription"));
				map.put("pdStatus", rs.getInt("PdStatus"));
				map.put("pdUpdate", rs.getString("PdUpdate"));
				

				list.add(map);
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
	@SuppressWarnings("null")
	@Override
	
	public List<PromotionsDetailVO> getAllJoinPmid() {
		
			List<PromotionsDetailVO> list2 = new ArrayList<PromotionsDetailVO>();
			
			PromotionsDetailVO promotionsDetailVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		
			try {

				con = ds.getConnection();
				pstmt = con.prepareStatement(GET_ALL_JOIN_PMID);
				rs = pstmt.executeQuery();
				

				while (rs.next()) {
					promotionsDetailVO.setPmid(rs.getInt("pmid"));
					list2.add(promotionsDetailVO); 
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
			return list2;
		}
	@Override
	public void updatePdStatus(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_PDSTATUS);
		

			
			pstmt.setInt(1, productVO.getPdStatus());
			pstmt.setInt(2, productVO.getPdid());
			
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
	public List<ProductVO> getPdOnShelfOrderByPrice() {
	
			List<ProductVO> list = new ArrayList<ProductVO>();
				ProductVO productVO = null;
				
				Connection con = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
			
				try {
					
					con = ds.getConnection();
					
					
					pstmt = con.prepareStatement(GET_PD_ON_SHELF_ORDER_BY_PRICE);
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
	@Override
	public List<ProductVO> getPdOnShelfOrderByPriceDesc() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PD_ON_SHELF_ORDER_BY_PRICE_DESC);
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
	
	@Override
	public List<ProductVO> getPdOnShelfOrderPdUpdate() {
		List<ProductVO> list = new ArrayList<ProductVO>();
		ProductVO productVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PD_ON_SHELF_ORDER_BY_PD_UPDATE);
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
		return null;
	}
	@Override

    public List<ProductVO> NameSearchGetAll(String pdName) {
		
		List<ProductVO> list = new ArrayList<ProductVO>();
			ProductVO productVO = null;
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
		
			try {
				con = ds.getConnection();
				pstmt = con.prepareStatement(LIST_ALL_PD_BY_NAME);
				pstmt.setString(1, pdName);
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
	}

	
//	public static void main(String[] args) {
//
//		ProductDAO dao = new ProductDAO();
//
//		List<ProductVO> list = dao.getAll();
//		for (ProductVO aProduct : list) {
//
//			System.out.print(aProduct.getPdName() + ",");
//
//			System.out.println();
//		
//		
		//新增
//		ProductVO productVO1 = new ProductVO();
//		productVO1.setPdsid(2);
//		productVO1.setPdName("utf188");
//		productVO1.setPdPrice(900);
//		productVO1.setPdDiscountPrice(900);
//		productVO1.setPdDescription("憭剖ˊ霈�");
//		productVO1.setPdStatus(1);
//		productVO1.setPdUpdate(LocalDateTime.now());
//		dao.insert(productVO1);

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

	
	

