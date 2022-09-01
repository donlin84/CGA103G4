package com.favoriteProduct.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FavoriteProductJDBCDAO implements FavoriteProductDAO_interface {
	
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga103g4?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "cga103g4";
	
	private static final String INSERT_STMT =
			"INSERT INTO productfavorite (pdid, memid) VALUES (?,?)";
	private static final String UPDATE =		
			"UPDATE favoriteproduct set pdid = ?, where memid = ?";
	private static final String GET_ALL_STMT = 
			"SELECT pdid, memid from favoriteproduct order by pdid";
	private static final String GET_ONE_STMT =
			"SELECT pdid, mdmid from favoriteproduct where pdid = ?";
	private static final String DELETE = 
			"DELETE FROM favoriteproduct where pdid = ?";
	
	@Override
	public void insert(FavoriteProductVO favoriteProductVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
	try {
		Class.forName(driver);
		con = DriverManager.getConnection(url, userid, passwd); 
		pstmt = con.prepareStatement(INSERT_STMT);
		
		pstmt.setInt(1, favoriteProductVO.getPdid());
		pstmt.setInt(2, favoriteProductVO.getMemid());
		
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
	public void update(FavoriteProductVO favoriteProductVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(UPDATE);
		
			pstmt.setInt(1, favoriteProductVO.getPdid());
			pstmt.setInt(2, favoriteProductVO.getMemid());

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
public FavoriteProductVO findbyPrimaryKey(Integer pdid) {
		
		FavoriteProductVO favoriteProductVO = null;
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
					favoriteProductVO = new FavoriteProductVO();
					
					favoriteProductVO.setPdid(rs.getInt("pdid"));
					favoriteProductVO.setMemid(rs.getInt("memid"));
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
			return favoriteProductVO;
		}
	@Override
	public List<FavoriteProductVO> getAll() {
		List<FavoriteProductVO> list = new ArrayList<FavoriteProductVO>();
		FavoriteProductVO favoriteProductVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				
				favoriteProductVO = new FavoriteProductVO();
				favoriteProductVO.setPdid(rs.getInt("pdsid"));
				favoriteProductVO.setMemid(rs.getInt("memid"));
				
				list.add(favoriteProductVO); 
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
	public void delete(Integer pdid) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE);

			pstmt.setInt(1, pdid);

			pstmt.executeUpdate();

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
}

	

		


	
