package com.teacher.model;
import java.util.*;



import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.*;


public class TeacherJDBCDAO implements TeacherDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga103g4?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "Alan0622";
	
	private static final String INSERT_STMT = 
		"INSERT INTO Teacher (thrName,thrGender,thrPhone,thrEmail,thrStatus,thrIntroduction,thrComment,thrCmnumber,thrPic) VALUES (?, ?, ?, ?, ?, ?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT thrid,thrName,thrGender,thrPhone,thrEmail,thrStatus,thrIntroduction,thrComment,thrCmnumber,thrPic FROM Teacher order by thrid";
	private static final String GET_ONE_STMT = 
		"SELECT thrid,thrName,thrGender,thrPhone,thrEmail,thrStatus,thrIntroduction,thrComment,thrCmnumber,thrPic FROM Teacher where thrid = ?";
	private static final String DELETE = 
		"DELETE FROM Teacher where thrid = ?";
	private static final String UPDATE = 
		"UPDATE Teacher set thrName=?, thrGender=?, thrPhone=?, thrEmail=?, thrStatus=? ,thrIntroduction=?,thrComment=?,thrCmnumber=?,thrPic=? where thrid = ?";

	
	@Override
	public void insert(TeacherVO teacherVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid,passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, teacherVO.getThrName());
			pstmt.setString(2, teacherVO.getThrGender());
			pstmt.setString(3, teacherVO.getThrPhone());
			pstmt.setString(4, teacherVO.getThrEmail());
			pstmt.setInt(5, teacherVO.getThrStatus());
//          NULL相關
//			pstmt.setString(6, teacherVO.getThrIntroduction());
			String ThrIntroduction = teacherVO.getThrIntroduction();
			if(ThrIntroduction == null) {
				pstmt.setNull(6, Types.VARCHAR);
			} else {
				pstmt.setString(6, ThrIntroduction);
			}	
			
//          NULL相關
			Integer Comment = teacherVO.getThrComment();
			if(Comment == null) {
				pstmt.setNull(7, Types.INTEGER);
			} else {
				pstmt.setInt(7, Comment);
			}		
			
			Integer cmnumber = teacherVO.getThrCmnumber();
			if(cmnumber == null) {
				pstmt.setNull(8, Types.INTEGER);
			} else {
				pstmt.setInt(8, cmnumber);
			}		
			
//			pstmt.setInt(7, teacherVO.getThrComment());
//			pstmt.setInt(8, teacherVO.getThrCmnumber());
			
//			圖片
			pstmt.setBytes(9,teacherVO.getThrPic());
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
	public void update(TeacherVO teacherVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid,passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setString(1, teacherVO.getThrName());
			pstmt.setString(2, teacherVO.getThrGender());
			pstmt.setString(3, teacherVO.getThrPhone());
			pstmt.setString(4, teacherVO.getThrEmail());
			pstmt.setInt(5, teacherVO.getThrStatus());
//          NULL相關
//			pstmt.setString(6, teacherVO.getThrIntroduction());
			String ThrIntroduction = teacherVO.getThrIntroduction();
			if(ThrIntroduction == null) {
				pstmt.setNull(6, Types.VARCHAR);
			} else {
				pstmt.setString(6, ThrIntroduction);
			}				
//          NULL相關
			Integer Comment = teacherVO.getThrComment();
			if(Comment == null) {
				pstmt.setNull(7, Types.INTEGER);
			} else {
				pstmt.setInt(7, Comment);
			}		
			
			Integer cmnumber = teacherVO.getThrCmnumber();
			if(cmnumber == null) {
				pstmt.setNull(8, Types.INTEGER);
			} else {
				pstmt.setInt(8, cmnumber);
			}			
						
//			pstmt.setInt(7, teacherVO.getThrComment());
//			pstmt.setInt(8, teacherVO.getThrCmnumber());
//			圖片
			pstmt.setBytes(9,teacherVO.getThrPic());
			pstmt.setInt(10,teacherVO.getThrid());
			
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
	public TeacherVO findByPrimaryKey(Integer thrid) {
		
		TeacherVO teacherVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

	
		
		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid,passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, thrid);

			rs = pstmt.executeQuery();

//			FileInputStream fis = new FileInputStream("picture/nopicture.jpg");
//			byte[] nopicture = new byte[fis.available()];
//			fis.read(nopicture);
//			fis.close();				
			
			while (rs.next()) {
				// empVo 也稱為 Domain objects
				teacherVO = new TeacherVO();
				teacherVO.setThrid(rs.getInt("thrid"));
				teacherVO.setThrName(rs.getString("thrname"));
				teacherVO.setThrGender(rs.getString("thrgender"));
				teacherVO.setThrPhone(rs.getString("thrphone"));
				teacherVO.setThrEmail(rs.getString("thremail"));
				teacherVO.setThrStatus(rs.getInt("thrstatus"));
				teacherVO.setThrIntroduction(rs.getString("thrintroduction"));
//				teacherVO.setThrComment(rs.getInt("thrComment"));
//				teacherVO.setThrCmnumber(rs.getInt("thrcmnumber"));
//				null相關
				teacherVO.setThrComment(rs.getInt("thrcomment"));
				if (rs.wasNull()) {
					teacherVO.setThrComment(null);
				}						
				teacherVO.setThrCmnumber(rs.getInt("thrcmnumber"));
				if (rs.wasNull()) {
					teacherVO.setThrCmnumber(null);
				}					
//				if (!(rs.getBytes("thrPic")==null)) {
//					teacherVO.setThrPic(rs.getBytes("thrPic"));
//				}else {
//					teacherVO.setThrPic(nopicture);
//				}	
				teacherVO.setThrPic(rs.getBytes("thrPic"));
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
			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
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
		return teacherVO;
	}
//	@Override
	public List<TeacherVO> getAll() {
	List<TeacherVO> list = new ArrayList<TeacherVO>();
	TeacherVO teacherVO = null;

	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	try {

		Class.forName(driver);
		con = DriverManager.getConnection(url, userid,passwd);
		pstmt = con.prepareStatement(GET_ALL_STMT);
		rs = pstmt.executeQuery();


//		FileInputStream fis = new FileInputStream("picture/nopicture.jpg");
//		byte[] nopicture = new byte[fis.available()];
//		fis.read(nopicture);
//		fis.close();

		while (rs.next()) {
			// empVO 也稱為 Domain objects
			teacherVO = new TeacherVO();
			teacherVO.setThrid(rs.getInt("thrid"));
			teacherVO.setThrName(rs.getString("thrname"));
			teacherVO.setThrGender(rs.getString("thrgender"));
			teacherVO.setThrPhone(rs.getString("thrphone"));
			teacherVO.setThrEmail(rs.getString("thremail"));
			teacherVO.setThrStatus(rs.getInt("thrstatus"));
			teacherVO.setThrIntroduction(rs.getString("thrintroduction"));
			teacherVO.setThrComment(rs.getInt("thrcomment"));
//			null相關
			if (rs.wasNull()) {
				teacherVO.setThrComment(null);
			}						
			teacherVO.setThrCmnumber(rs.getInt("thrcmnumber"));
			if (rs.wasNull()) {
				teacherVO.setThrCmnumber(null);
			}	
//			if (!(rs.getBytes("thrPic")==null)) {
//				teacherVO.setThrPic(rs.getBytes("thrPic"));
//			}else {
//				teacherVO.setThrPic(nopicture);
//			}
			teacherVO.setThrPic(rs.getBytes("thrPic"));
			list.add(teacherVO); // Store the row in the list

		}

		// Handle any driver errors
	} catch (ClassNotFoundException e) {
		throw new RuntimeException("Couldn't load database driver. "
				+ e.getMessage());
		// Handle any SQL errors
	} catch (SQLException se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
//		 Clean up JDBC resources
//	} catch (IOException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
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
//	@Override
//	public List<TeacherVO> getAll(Map<String, String[]> map) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//	
	
/*---------------------------------------------------------------------------------------*/
	
//	測試
	public static void main(String[] args) {
		TeacherJDBCDAO dao = new TeacherJDBCDAO();
		

//		新增
		TeacherVO vo = new TeacherVO();
		vo.setThrName("無名");
		vo.setThrGender("男");
		vo.setThrPhone("0912345678");
		vo.setThrEmail("1823u9@gmail.com");
		vo.setThrStatus(0);
		vo.setThrIntroduction(null);
		vo.setThrComment(null);
		vo.setThrCmnumber(null);
		vo.setThrPic(new byte[] { (byte)0xe0, 0x4f, (byte)0xd0,
		    0x20, (byte)0xea, 0x3a, 0x69, 0x10, (byte)0xa2, (byte)0xd8, 0x08, 0x00, 0x2b,
		    0x30, 0x30, (byte)0x9d });
		dao.insert(vo);
		
		
		
//		修改
//		TeacherVO vo2 = new TeacherVO();
//		vo2.setThrName("修改");
//		vo2.setThrGender("女");
//		vo2.setThrPhone("1912345678");
//		vo2.setThrEmail("adf@gmail.com");
//		vo2.setThrStatus(1);
//		vo2.setThrIntroduction(null);
//		vo2.setThrComment(null);
//		vo2.setThrCmnumber(null);	
//		vo2.setThrPic(new byte[] { (byte)0xe0, 0x4f, (byte)0xd0,
//			    0x20, (byte)0xea, 0x3a, 0x69, 0x10, (byte)0xa2, (byte)0xd8, 0x08, 0x00, 0x2b,
//			    0x30, 0x30, (byte)0x9d });
//		vo2.setThrid(3);
//		dao.update(vo2);
		
//		查一筆資料
//		TeacherVO vo3 = dao.findByPrimaryKey(1);
//		System.out.print(vo3.getThrid() + ",");
//		System.out.print(vo3.getThrName() + ",");
//		System.out.print(vo3.getThrGender() + ",");
//		System.out.print(vo3.getThrPhone() + ",");
//		System.out.print(vo3.getThrEmail() + ",");
//		System.out.print(vo3.getThrStatus() + ",");
//		System.out.print(vo3.getThrIntroduction() + ",");
//		System.out.print(vo3.getThrComment()+ ",");
//		System.out.print(vo3.getThrCmnumber()+ ",");
//		System.out.print(vo3.getThrPic() +",");
//		System.out.println();
		
		// 查詢
//		List<TeacherVO> list = dao.getAll();
//		for (TeacherVO aEmp : list) {
//			System.out.print(aEmp.getThrid() + ",");
//			System.out.print(aEmp.getThrName() + ",");
//			System.out.print(aEmp.getThrGender() + ",");
//			System.out.print(aEmp.getThrPhone() + ",");
//			System.out.print(aEmp.getThrEmail() + ",");
//			System.out.print(aEmp.getThrStatus() + ",");
//			System.out.print(aEmp.getThrIntroduction() + ",");
//			System.out.print(aEmp.getThrComment()+ ",");
//			System.out.print(aEmp.getThrCmnumber()+ ",");
//			System.out.print(aEmp.getThrPic() +",");
//			System.out.println();
//		}	
		
//		TeacherVO vo3 = dao.findByPrimaryKey(4);
//		String base64str = Base64.getEncoder().encodeToString(vo3.getThrPic());
//		System.out.println(base64str);
		
		
		
		
	}
	
	
	
}