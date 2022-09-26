package com.productPicture.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;



public class ProductpicDAO implements ProductpicDAO_interface {
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/cga103g4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	private static final String INSERT_PICTURE = 
			"Insert into productpic (pdpic,pdid) value (?,(SELECT pdid from product order by pdid desc limit 1))";
		private static final String INSERT_PIC_TO＿EXISTED_PRODUCT =
			"Insert into productpic (pdid, pdpic)value(?,?)";
		private static final String GET_ALL_STMT = 
			"SELECT pdPicid, pdid, pdPic from Productpic order by pdPicid";
		private static final String GET_ONE_STMT = 
			"SELECT pdPicid, pdid, pdPic from Productpic where pdPicid = ?";
		private static final String UPDATE = 
			"UPDATE Productpic SET pdPic = ?, pdid = ? WHERE pdPicid = ?";
		private static final String DELETE = 
			"DELETE FROM Productpic where pdPicid = ?";
	    private static final String GET_PICTURES_BY_PDID =
	    	"SELECT pdPicid from productpic where pdid = ?";
	    private static final String GetoneInByte = " select pdPic from Productpic where pdPicid=?";
	
	@Override
	public void insert(ProductpicVO productpicVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_PICTURE);
			
			pstmt.setBytes(1, productpicVO.getPdPic());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			
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
	public void existedInsert(ProductpicVO productpicVO) {
		PreparedStatement pstmt = null;
		Connection con = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_PIC_TO＿EXISTED_PRODUCT);
			pstmt.setInt(1, productpicVO.getPdid());
			pstmt.setBytes(2, productpicVO.getPdPic());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
	public void update(ProductpicVO productpicVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			 con = ds.getConnection();
			 pstmt = con.prepareStatement(UPDATE);	
			
		    pstmt.setBytes(1, productpicVO.getPdPic());
		    pstmt.setInt(2, productpicVO.getPdid());
		    pstmt.setInt(3, productpicVO.getPdPicid());
			pstmt.executeUpdate();
			int rowCount = pstmt.executeUpdate();
			// Handle any driver errors
			System.out.println(rowCount + " row(s) updated!!");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			
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
	public void delete(Integer pdPicid) {
		Connection con = null;
		PreparedStatement pstmt = null;
 
		try {
			 con = ds.getConnection();
			 pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, pdPicid);

			pstmt.executeUpdate();
			// Handle any driver errors
		}catch (SQLException e) {
			e.printStackTrace();
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
	public ProductpicVO findByPrimaryKey(Integer pdPicid) {
		
		ProductpicVO productpicVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			 con = ds.getConnection();
			 pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setInt(1, pdPicid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
			}
				// empVo �]�٬� Domain objects
			productpicVO = new ProductpicVO();
			productpicVO.setPdPicid(rs.getInt("pdPicid"));
			productpicVO.setPdid(rs.getInt("pdid"));
			productpicVO.setPdPic(rs.getBytes("pdPic"));
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
		return productpicVO;
	}

	@Override
	public List<ProductpicVO> getAll() {
		List<ProductpicVO> list = new ArrayList<ProductpicVO>();
		
		ProductpicVO productpicVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			 con = ds.getConnection();
			 pstmt = con.prepareStatement(GET_ALL_STMT);
			 rs = pstmt.executeQuery();

			while (rs.next()) {
				
				productpicVO = new ProductpicVO();
				productpicVO.setPdPicid(rs.getInt("pdpicid"));
				productpicVO.setPdid(rs.getInt("pdid"));
				productpicVO.setPdPic(rs.getBytes("pdPic"));
				list.add(productpicVO); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
	
	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}
	

	
	@Override
	public List<ProductpicVO> getOneProductPics(Integer pdid) {
	
		List<ProductpicVO> list = new ArrayList<>();
		ProductpicVO productpicVO = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_PICTURES_BY_PDID, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			pstmt.setInt(1, pdid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				productpicVO = new ProductpicVO();
				productpicVO.setPdPicid(rs.getInt("pdPicid"));
				list.add(productpicVO);
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "+ se.getMessage());
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
	public ProductpicVO GetOnePicBypdPicid(Integer pdPicid) {
			
			ProductpicVO productpicVO = new ProductpicVO();
			Connection con = null;
			ResultSet rs = null;
			PreparedStatement ps = null;
			
			try {
				con = ds.getConnection();
				ps = con.prepareStatement(GetoneInByte);
				ps.setInt(1, pdPicid);
				rs = ps.executeQuery();
				while (rs.next()) {			
					productpicVO.setPdPic(rs.getBytes("pdPic"));
					return productpicVO;
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
				if (ps != null) {
					try {
						ps.close();
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
	}

