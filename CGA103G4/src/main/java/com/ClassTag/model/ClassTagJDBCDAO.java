package com.ClassTag.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jdt.internal.compiler.batch.Main;

import com.ClassPicture.model.ClassPictureJDBCDAO;
import com.ClassPicture.model.ClassPictureVO;

public class ClassTagJDBCDAO implements ClassTagDAO_interface{

	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga103g4?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "Alan0622";
	
	private static final String INSERT_ClassTag = 
			"INSERT INTO ClassTag (claTagName,claTagStatus) VALUES (?, ?)";
	private static final String GET_ALL_ClassTag = 
			"SELECT claTagid,claTagName,claTagStatus FROM ClassTag order by claTagid";
	private static final String GET_ONE_ClassTag = 
			"SELECT claTagid,claTagName,claTagStatus FROM ClassTag where claTagid = ?";
	private static final String DELETE_ClassTag = 
			"DELETE FROM ClassPicture where claTagid = ?";
	private static final String UPDATE_ClassTag = 
			"UPDATE ClassTag set claTagName=?, claTagStatus=? where claTagid = ?" ;
	
	@Override
	public void insert(ClassTagVO classTagVO) {
		
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(INSERT_ClassTag);
			
			pstmt.setString(1, classTagVO.getClaTagName());
			pstmt.setInt(2, classTagVO.getClaTagStatus());

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
	public void update(ClassTagVO classTagVO) {
		
		Connection con =null;
		PreparedStatement pstmt = null;
		
		try {
			
			Class.forName(driver);
			con = DriverManager.getConnection(url,userid,passwd);
			pstmt = con.prepareStatement(UPDATE_ClassTag);
			
			pstmt.setString(1, classTagVO.getClaTagName());
			pstmt.setInt(2, classTagVO.getClaTagStatus());

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
	public void delete(Integer claTagid) {
		
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(DELETE_ClassTag);

			pstmt.setInt(1, claTagid);

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
	public ClassTagVO findByPrimaryKey(Integer claTagid) {
		ClassTagVO classTagVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ONE_ClassTag);

			pstmt.setInt(1, claTagid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// classPictureVO �]�٬� Domain objects
				classTagVO = new ClassTagVO();
				classTagVO.setClaTagid(rs.getInt("claTagid"));
				classTagVO.setClaTagName(rs.getString("claTagName"));
				classTagVO.setClaTagStatus(rs.getInt("claTagStatus"));
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
		return classTagVO;
	}

	@Override
	public List<ClassTagVO> getAll() {
		List<ClassTagVO> list = new ArrayList<ClassTagVO>();
		ClassTagVO classTagVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			pstmt = con.prepareStatement(GET_ALL_ClassTag);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				classTagVO = new ClassTagVO();
				classTagVO.setClaTagid(rs.getInt("claTagid"));
				classTagVO.setClaTagName(rs.getString("claTagName"));
				classTagVO.setClaTagStatus(rs.getInt("claTagStatus"));
				list.add(classTagVO); // Store the row in the list
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
	
	
	
	
	
	public static void main(String[] args) {
		ClassTagJDBCDAO dao = new ClassTagJDBCDAO();
		
		//�s�W
//		ClassTagVO ClassTagVO1 = new ClassTagVO();
//		ClassTagVO1.setClaTagName("");
//		ClassTagVO1.setClaTagStatus(0);
//		dao.insert(ClassTagVO1);
//-------------------------------------------------------
		//�ק�
//		ClassTagVO ClassTagVO2 = new ClassTagVO();
//		ClassTagVO2.setClaTagid(0);
//		ClassTagVO2.setClaTagName("");
//		ClassTagVO2.setClaTagStatus(0);
//		dao.update(ClassTagVO2);
//-------------------------------------------------------
		// �R��
//		dao.delete(�ҵ{���O뫽s��);
//-------------------------------------------------------
//		��@�d��
		ClassTagVO ClassTagVO3 = dao.findByPrimaryKey(2);
		System.out.print(ClassTagVO3.getClaTagid()+",");
		System.out.print(ClassTagVO3.getClaTagName()+",");
		System.out.print(ClassTagVO3.getClaTagStatus()+",");
//-------------------------------------------------------
		//�d�ߩҦ�
//		List<ClassTagVO> list = dao.getAll();
//		for(ClassTagVO a : list) {
//			System.out.print(a.getClaTagid()+",");
//			System.out.print(a.getClaTagName()+",");
//			System.out.print(a.getClaTagStatus()+",");
//			System.out.println();
//		}		
	}

}
