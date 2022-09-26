package com.ClassPicture.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.teacher.model.TeacherVO;


public class ClassPictureJDBCDAO implements ClassPictureDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga103g4?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "cga103g4";
	
	private static final String INSERT_ClassPicture = 
			"INSERT INTO ClassPicture (claid,claPic) VALUES (?, ?)";
	private static final String GET_ALL_ClassPicture = 
			"SELECT claPicid,claid,claPic FROM ClassPicture order by claPicid";
	private static final String GET_ONE_ClassPicture = 
			"SELECT claPic FROM ClassPicture where claid = ?";
	private static final String DELETE_ClassPicture = 
			"DELETE FROM ClassPicture where claPicid = ?";
	private static final String UPDATE_ClassPicture = 
			"UPDATE ClassPicture set claid=?, claPic=? where claPicid = ?" ;
	private static final String get_clapicid = 
			"select clapicid from ClassPicture where claid=?";
	
	
	@Override
	public void insert(ClassPictureVO classPictureVO) {
		
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_ClassPicture);
			
			pstmt.setInt(1, classPictureVO.getClaid());
			pstmt.setBytes(2, classPictureVO.getClaPic());

			pstmt.executeUpdate();
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public void update(ClassPictureVO classPictureVO) {
		
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(UPDATE_ClassPicture);
			
			pstmt.setInt(1, classPictureVO.getClaid());
			pstmt.setBytes(2, classPictureVO.getClaPic());
			pstmt.setInt(3, classPictureVO.getClaPicid());

			pstmt.executeUpdate();
			
		}catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
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
	public void delete(Integer claPicid) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_ClassPicture);

			pstmt.setInt(1, claPicid);

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

	@Override
	public List<ClassPictureVO> findByPrimaryKey(Integer claid) {
		List<ClassPictureVO> list = new ArrayList<ClassPictureVO>();
		ClassPictureVO classPictureVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_ClassPicture);

			pstmt.setInt(1, claid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// classPictureVO �]�٬� Domain objects
				classPictureVO = new ClassPictureVO();
//				classPictureVO.setClaPicid(rs.getInt("claPicid"));
//				classPictureVO.setClaid(rs.getInt("claid"));
				classPictureVO.setClaPic(rs.getBytes("claPic"));
				list.add(classPictureVO);
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
	public List<ClassPictureVO> get_clapicid(Integer claid) {
		List<ClassPictureVO> list = new ArrayList();
		ClassPictureVO classPictureVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(get_clapicid);

			pstmt.setInt(1, claid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// classPictureVO �]�٬� Domain objects
				classPictureVO = new ClassPictureVO();
				classPictureVO.setClaPicid(rs.getInt("claPicid"));
//				classPictureVO.setClaid(rs.getInt("claid"));
//				classPictureVO.setClaPic(rs.getBytes("claPic"));
				list.add(classPictureVO);
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
	public List<ClassPictureVO> getAll() {
		List<ClassPictureVO> list = new ArrayList<ClassPictureVO>();
		ClassPictureVO classPictureVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_ClassPicture);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				classPictureVO = new ClassPictureVO();
				classPictureVO.setClaPicid(rs.getInt("claPicid"));
				classPictureVO.setClaid(rs.getInt("claid"));
				classPictureVO.setClaPic(rs.getBytes("claPic"));
				list.add(classPictureVO); // Store the row in the list
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
	public void insert2 (ClassPictureVO classPictureVO , Connection con) {

		PreparedStatement pstmt = null;

		try {

     		pstmt = con.prepareStatement(INSERT_ClassPicture);

     		pstmt.setInt(1, classPictureVO.getClaid());
			pstmt.setBytes(2, classPictureVO.getClaPic());

Statement stmt=	con.createStatement();

			pstmt.executeUpdate();

			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-emp");
					con.rollback();
				} catch (SQLException excep) {
					throw new RuntimeException("rollback error occured. "
							+ excep.getMessage());
				}
			}
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
		}

	}
	
	
	
	public static void main(String[] args) throws IOException {
		ClassPictureJDBCDAO dao = new ClassPictureJDBCDAO();
		
		//新增
//		ClassPictureVO classPictureVO1 = new ClassPictureVO();
//		classPictureVO1.setClaid(0);
//		FileInputStream fis = new FileInputStream("C:/Users/Tibame/Desktop/apple.png");	//拿路徑放進sql
//		byte[] buffer = new byte[fis.available()];
//		classPictureVO1.setClaPic(buffer);
//		dao.insert(classPictureVO1);
//-------------------------------------------------------
		//修改
//		ClassPictureVO classPictureVO2 = new ClassPictureVO();
//		classPictureVO2.setClaPicid(0);
//		classPictureVO2.setClaid(0);
//		FileInputStream fis = new FileInputStream("C:/Users/Tibame/Desktop/apple.png");	//拿路徑放進sql
//		byte[] buffer = new byte[fis.available()];
//		classPictureVO2.setClaPic(buffer);
//		dao.update(classPictureVO2);
//-------------------------------------------------------
		// 刪除
//		dao.delete(�ҵ{��뫽s��);
//-------------------------------------------------------
//		單一查詢
//		List<ClassPictureVO> list = dao.findByPrimaryKey(41);
//		for(ClassPictureVO a : list) {
//			System.out.print(a.getClaPic()+",");
//			System.out.println();
//		}
//-------------------------------------------------------
		//用課程編號抓到圖片編號
		List<ClassPictureVO> list = dao.get_clapicid(43);
		for(ClassPictureVO a : list) {
			System.out.print(a.getClaPicid());
			System.out.println();
		}
//-------------------------------------------------------
		//全部查詢
//		List<ClassPictureVO> list = dao.getAll();
//		for(ClassPictureVO a : list) {
//			System.out.print(a.getClaPicid()+",");
//			System.out.print(a.getClaid()+",");
//			System.out.print(a.getClaPic()+",");
//			System.out.println("有茶道");
//		}		
//		
	}

}
