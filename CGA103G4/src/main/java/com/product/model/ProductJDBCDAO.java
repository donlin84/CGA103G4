package com.product.model;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;


import com.promotions.model.PromotionsVO;
import com.promotionsdetail.model.PromotionsDetailVO;

public class ProductJDBCDAO implements ProductDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga103g4?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "cga103g4";
	
	private static final String INSERT_STMT = 
		"INSERT INTO Product (pdsid,pdName,pdPrice,pdDiscountPrice,pdDescription,pdStatus,pdUpdate) VALUES (?,?,?,?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT pdid,pdsid,pdName,pdPrice,pdDiscountPrice,pdDescription,pdStatus,pdUpdate FROM Product order by pdid";
	private static final String GET_ONE_STMT =
		"SELECT pdid,pdsid,pdName,pdPrice,pdDiscountPrice,pdDescription,pdStatus,pdUpdate FROM Product where pdid = ?";
	private static final String UPDATE =
		"UPDATE Product set pdsid=?, pdName=?, pdPrice=?, pdDiscountPrice=?, pdDescription=?, pdStatus=?, pdUpdate=? where pdid = ?";
	private static final String ALL_PD_NAME = 
		"SELECT pdname from product";
	private static final String listByPdSort=
		"SELECT pdid,pdsid,pdName,pdPrice,pdDiscountPrice,pdDescription,pdStatus,pdUpdate FROM Product where pdsid = ?";
	private static final String ListByPdStatus=
		"SELECT pdid,pdsid,pdName,pdPrice,pdDiscountPrice,pdDescription,pdStatus,pdUpdate FROM Product where pdStatus = ?";
	
	private static final String GET_ALL_JOIN_PMID = 
			"SELECT b.pmid from product a left join promotionsdetail b on a.pdid = b.pdid order by a.pdid"; 
	@Override
	public void insert(ProductVO productVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
	try {
		
		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd); 
		pstmt = con.prepareStatement(INSERT_STMT);
		
		pstmt.setInt(1, productVO.getPdsid());
		pstmt.setString(2, productVO.getPdName());
		pstmt.setInt(3, productVO.getPdPrice());
		pstmt.setInt(4, productVO.getPdDiscountPrice());
		pstmt.setString(5, productVO.getPdDescription());
		pstmt.setInt(6, productVO.getPdStatus());
		pstmt.setObject(7, productVO.getPdUpdate());
		
		pstmt.executeUpdate();
		
	} catch(ClassNotFoundException e) {
		throw new RuntimeException("Couldn't load database driver. " 
				+ e.getMessage());
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
		
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
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

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd); 
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
		
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
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
					Class.forName(driver);
					con = DriverManager.getConnection(url, userid, passwd);
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
					
				} catch (ClassNotFoundException e) {
					
					e.printStackTrace();
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
						Class.forName(driver);
						con = DriverManager.getConnection(url, userid, passwd); 
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
					} catch (ClassNotFoundException e) {
						
						e.printStackTrace();
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
	public List<ProductVO> getAll(Map<String, String[]> map) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Object> getTop3pd() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ProductVO getNewestPdid() {
		// TODO Auto-generated method stub
		return null;
	
	}
	@Override
	public PromotionsVO getPromoDiscount(Integer pmid) {
		// TODO Auto-generated method stub
		return null;
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
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd); 
			pstmt = con.prepareStatement(GET_ALL_JOIN_PMID);
			rs = pstmt.executeQuery();
			

			while (rs.next()) {
				
				if(rs.getObject("pmid") == null) {
					 promotionsDetailVO.setPmid(rs.getInt(0));
				}
				else promotionsDetailVO.setPmid(rs.getInt("pmid"));
				list2.add(promotionsDetailVO); 
				System.out.println(promotionsDetailVO);
			}
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
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
	public List<ProductVO> listAllOderByPdid() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<ProductVO> listAllOdByPdPriceDesc() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<ProductVO> listAllOdByPdPriceAsc() {
		// TODO Auto-generated method stub
		return null;
	}


public static void main(String[] args) {

	ProductJDBCDAO dao = new ProductJDBCDAO();
	
	List<PromotionsDetailVO> list2 = dao.getAllJoinPmid();
	
	for (PromotionsDetailVO apromotionDetailVO : list2) {
		
	System.out.print(apromotionDetailVO.getPmid() + ",");	


	}

}
@Override
public void updatePdStatus(ProductVO productVO) {
	// TODO Auto-generated method stub
	
}
@Override
public List<ProductVO> getPdOnShelfOrderByPrice() {
	// TODO Auto-generated method stub
	return null;
}
@Override
public List<ProductVO> getPdOnShelfOrderByPriceDesc() {
	// TODO Auto-generated method stub
	return null;
}
@Override
public List<ProductVO> getPdOnShelfOrderPdUpdate() {
	// TODO Auto-generated method stub
	return null;
}

@Override
public List<ProductVO> NameSearchGetAll(String pdName) {
	// TODO Auto-generated method stub
	return null;
}


	}





	
//		List<ProductVO> list2 = dao.getAll();
//		for (ProductVO aProduct : list2) {
//			System.out.print(aProduct.getPdid() + ",");
//			System.out.print(aProduct.getPdsid() + ",");
//			System.out.print(aProduct.getPdName() + ",");
//			System.out.print(aProduct.getPdPrice() + ",");
//			System.out.print(aProduct.getPdDiscountPrice() + ",");
//			System.out.print(aProduct.getPdDescription() + ",");
//			System.out.print(aProduct.getPdStatus());
//			System.out.print(aProduct.getPdUpdate());
//			System.out.println();
//		}
	


//		// ??????
//		ProductVO productVO1 = new ProductVO();
//		productVO1.setPdsid(2);
//		productVO1.setPdName("5678");
//		productVO1.setPdPrice(900);
//		productVO1.setPdDiscountPrice(900);
//		productVO1.setPdDescription("?????????");
//		productVO1.setPdStatus(1);
//		productVO1.setPdUpdate(LocalDateTime.now());
//		dao.insert(productVO1);

		// ??????
//		ProductVO productVO2 = new ProductVO();
//		
//		productVO2.setPdsid(1);
//		productVO2.setPdName("????????????");
//		productVO2.setPdPrice(700);
//		productVO2.setPdDiscountPrice(700);
//		productVO2.setPdDescription("????????????");
//		productVO2.setPdStatus(1);
//		productVO2.setPdUpdate(LocalDateTime.parse("2001-01-01T10:00:00"));
//		productVO2.setPdid(4009);
//		dao.update(productVO2);
//		System.out.println("updateSucceed");

		// ??????


//		 ??????

	

	
	
