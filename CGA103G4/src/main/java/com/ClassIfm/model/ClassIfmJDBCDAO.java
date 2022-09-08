package com.ClassIfm.model;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import com.ClassPicture.model.ClassPictureJDBCDAO;
import com.ClassPicture.model.ClassPictureVO;


public class ClassIfmJDBCDAO implements ClassIfmDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";
	String url = "jdbc:mysql://localhost:3306/cga103g4?serverTimezone=Asia/Taipei";
	String userid = "root";
	String passwd = "cga103g4";

	private static final String INSERT_STMT = 
		"INSERT INTO ClassIfm (thrid,claTagid,claTitle,claIntroduction,claTime,claPrice,claPeopleMax,claPeopleMin,claPeople,claStatus,claStrTime,claFinTime) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = 
		"SELECT claid,thrid,claTagid,claTitle,claIntroduction,claTime,claPrice,claPeopleMax,claPeopleMin,claPeople,claStatus,claStrTime,claFinTime FROM ClassIfm order by claid";
	private static final String GET_ONE_STMT = 
		"SELECT claid,thrid,claTagid,claTitle,claIntroduction,claTime,claPrice,claPeopleMax,claPeopleMin,claPeople,claStatus,claStrTime,claFinTime FROM ClassIfm where claid = ?";
//	private static final String DELETE = 
//		"DELETE FROM Teacher where thrid = ?";
	private static final String UPDATE = 
		"UPDATE ClassIfm set thrid=?, claTagid=?, claTitle=?, claIntroduction=?, claTime=? ,claPrice=?,claPeopleMax=?,claPeopleMin=?,claPeople=?,claStatus=?,claStrTime=?,claFinTime=? where claid = ?";
	private static final String JOINTEACHER =
		"select thrName from classifm c join teacher t on c.thrid = t.thrid where t.thrid=? limit 1";
	//只更改報名人數
	private static final String UPDATE_CLAPEOPLE="UPDATE classIfm set claPeople = ? where claid = ?";
	//給前台getall上架客程
	private static final String FRONT_GETALL = 
	"SELECT claid,thrid,claTagid,claTitle,claIntroduction,claTime,claPrice,claPeopleMax,claPeopleMin,claPeople,claStatus,claStrTime,claFinTime FROM ClassIfm where claStatus = 1 order by claid";		
	private static final String TIMER_GETCANCEL = 
			"SELECT claid,thrid,claTagid,claTitle,claIntroduction,claTime,claPrice,claPeopleMax,claPeopleMin,claPeople,claStatus,claStrTime,claFinTime FROM ClassIfm where claStatus = 3 order by claid";		
	//單純修改課程狀態
	private static final String UPDATE_CLASTATUS="UPDATE classifm SET claStatus = 4 WHERE claid = ?";
	@Override
	public void insert(ClassIfmVO classIfmVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid,passwd);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setInt(1, classIfmVO.getThrid());
			pstmt.setInt(2, classIfmVO.getClaTagid());
			pstmt.setString(3, classIfmVO.getClaTitle());
			pstmt.setString(4, classIfmVO.getClaIntroduction());
			pstmt.setObject(5, classIfmVO.getClaTime());
			pstmt.setInt(6, classIfmVO.getClaPrice());
			pstmt.setInt(7, classIfmVO.getClaPeopleMax());
			pstmt.setInt(8, classIfmVO.getClaPeopleMin());
			pstmt.setInt(9, classIfmVO.getClaPeople());
			pstmt.setInt(10, classIfmVO.getClaStatus());
			pstmt.setObject(11,classIfmVO.getClaStrTime());
			pstmt.setObject(12,classIfmVO.getClaFinTime());
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
	public void update(ClassIfmVO classIfmVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid,passwd);
			pstmt = con.prepareStatement(UPDATE);
			pstmt.setInt(1, classIfmVO.getThrid());
			pstmt.setInt(2, classIfmVO.getClaTagid());
			pstmt.setString(3, classIfmVO.getClaTitle());
			pstmt.setString(4, classIfmVO.getClaIntroduction());
			pstmt.setObject(5, classIfmVO.getClaTime());
			pstmt.setInt(6, classIfmVO.getClaPrice());
			pstmt.setInt(7, classIfmVO.getClaPeopleMax());
			pstmt.setInt(8, classIfmVO.getClaPeopleMin());
			pstmt.setInt(9, classIfmVO.getClaPeople());
			pstmt.setInt(10, classIfmVO.getClaStatus());
			pstmt.setObject(11,classIfmVO.getClaStrTime());
			pstmt.setObject(12,classIfmVO.getClaFinTime());
			pstmt.setInt(13, classIfmVO.getClaid());
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
	public ClassIfmVO findByPrimaryKey(Integer claid) {
		
		ClassIfmVO classIfmVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid,passwd);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, claid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo 也稱為 Domain objects
				classIfmVO = new ClassIfmVO();
				classIfmVO.setClaid(rs.getInt("claid"));
				classIfmVO.setThrid(rs.getInt("thrid"));
				classIfmVO.setClaTagid(rs.getInt("clatagid"));
				classIfmVO.setClaTitle(rs.getString("clatitle"));
				classIfmVO.setClaIntroduction(rs.getString("claintroduction"));
				classIfmVO.setClaTime(rs.getObject("clatime",LocalDateTime.class));
				classIfmVO.setClaPrice(rs.getInt("claprice"));
				classIfmVO.setClaPeopleMax(rs.getInt("clapeoplemax"));
				classIfmVO.setClaPeopleMin(rs.getInt("clapeoplemin"));
				classIfmVO.setClaPeople(rs.getInt("clapeople"));
				classIfmVO.setClaStatus(rs.getInt("clastatus"));
				classIfmVO.setClaStrTime(rs.getObject("clastrtime",LocalDateTime.class));
				classIfmVO.setClaFinTime(rs.getObject("clafintime",LocalDateTime.class));
				
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
		return classIfmVO;
	} 
	@Override
	public List<ClassIfmVO> getAll() {
		List<ClassIfmVO> list = new ArrayList<ClassIfmVO>();
		ClassIfmVO classIfmVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid,passwd);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				classIfmVO = new ClassIfmVO();
				classIfmVO.setClaid(rs.getInt("claid"));
				classIfmVO.setThrid(rs.getInt("thrid"));
				classIfmVO.setClaTagid(rs.getInt("clatagid"));
				classIfmVO.setClaTitle(rs.getString("clatitle"));
				classIfmVO.setClaIntroduction(rs.getString("claintroduction"));
				classIfmVO.setClaTime(rs.getObject("clatime",LocalDateTime.class));
				classIfmVO.setClaPrice(rs.getInt("claprice"));
				classIfmVO.setClaPeopleMax(rs.getInt("clapeoplemax"));
				classIfmVO.setClaPeopleMin(rs.getInt("clapeoplemin"));
				classIfmVO.setClaPeople(rs.getInt("clapeople"));
				classIfmVO.setClaStatus(rs.getInt("clastatus"));
				classIfmVO.setClaStrTime(rs.getObject("clastrtime",LocalDateTime.class));
				classIfmVO.setClaFinTime(rs.getObject("clafintime",LocalDateTime.class));

				
				list.add(classIfmVO); // Store the row in the list
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
	
	
	//一對多新增資訊和圖片
	@Override
	public void insertwithclapic(ClassIfmVO classIfmVO,List<ClassPictureVO> list) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid, passwd);
			
			// 1●設定於 pstm.executeUpdate()之前
    		con.setAutoCommit(false);
			
    		// 先新增課程資訊
			String cols[] = {"claid"};
			pstmt = con.prepareStatement(INSERT_STMT , cols);			
			pstmt.setInt(1, classIfmVO.getThrid());
			pstmt.setInt(2, classIfmVO.getClaTagid());
			pstmt.setString(3, classIfmVO.getClaTitle());
			pstmt.setString(4, classIfmVO.getClaIntroduction());
			pstmt.setObject(5, classIfmVO.getClaTime());
			pstmt.setInt(6, classIfmVO.getClaPrice());
			pstmt.setInt(7, classIfmVO.getClaPeopleMax());
			pstmt.setInt(8, classIfmVO.getClaPeopleMin());
			pstmt.setInt(9, classIfmVO.getClaPeople());
			pstmt.setInt(10, classIfmVO.getClaStatus());
			pstmt.setObject(11,classIfmVO.getClaStrTime());
			pstmt.setObject(12,classIfmVO.getClaFinTime());
Statement stmt=	con.createStatement();
//stmt.executeUpdate("set auto_increment_offset=10;");    //自增主鍵-初始值
//stmt.executeUpdate("set auto_increment_increment=10;"); //自增主鍵-遞增
			pstmt.executeUpdate();
			//掘取對應的自增主鍵值
			String next_claid = null;
			ResultSet rs = pstmt.getGeneratedKeys();
			if (rs.next()) {
				next_claid = rs.getString(1);
				System.out.println("自增主鍵值= " + next_claid +"(剛新增成功的部門編號)");
			} else {
				System.out.println("未取得自增主鍵值");
			}
			rs.close();
			// 再同時新增員工
			ClassPictureJDBCDAO dao = new ClassPictureJDBCDAO();
			System.out.println("list.size()-A="+list.size());
			for (ClassPictureVO aclapic : list) {
				aclapic.setClaid(new Integer(next_claid)) ;
				dao.insert2(aclapic,con);
			}

			// 2●設定於 pstm.executeUpdate()之後
			con.commit();
			con.setAutoCommit(true);
			System.out.println("list.size()-B="+list.size());
			System.out.println("新增部門編號" + next_claid + "時,共有員工" + list.size()
					+ "人同時被新增"); 
			
			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			if (con != null) {
				try {
					// 3●設定於當有exception發生時之catch區塊內
					System.err.print("Transaction is being ");
					System.err.println("rolled back-由-dept");
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
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}
	//單一更新報名人數
	@Override
	public void update_clapeople(ClassIfmVO classIfmVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid,passwd);
			pstmt = con.prepareStatement(UPDATE_CLAPEOPLE);
			
			pstmt.setInt(1, classIfmVO.getClaPeople());
			pstmt.setInt(2, classIfmVO.getClaid());
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
	//萬用查詢
	@Override
	public List<ClassIfmVO> cangetall(String xxx) {
		List<ClassIfmVO> list = new ArrayList<ClassIfmVO>();
		ClassIfmVO classIfmVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid,passwd);
			pstmt = con.prepareStatement(xxx);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				classIfmVO = new ClassIfmVO();
				classIfmVO.setClaid(rs.getInt("claid"));
				classIfmVO.setThrid(rs.getInt("thrid"));
				classIfmVO.setClaTagid(rs.getInt("clatagid"));
				classIfmVO.setClaTitle(rs.getString("clatitle"));
				classIfmVO.setClaIntroduction(rs.getString("claintroduction"));
				classIfmVO.setClaTime(rs.getObject("clatime",LocalDateTime.class));
				classIfmVO.setClaPrice(rs.getInt("claprice"));
				classIfmVO.setClaPeopleMax(rs.getInt("clapeoplemax"));
				classIfmVO.setClaPeopleMin(rs.getInt("clapeoplemin"));
				classIfmVO.setClaPeople(rs.getInt("clapeople"));
				classIfmVO.setClaStatus(rs.getInt("clastatus"));
				classIfmVO.setClaStrTime(rs.getObject("clastrtime",LocalDateTime.class));
				classIfmVO.setClaFinTime(rs.getObject("clafintime",LocalDateTime.class));

				
				list.add(classIfmVO); // Store the row in the list
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
	public List<ClassIfmVO> front_getall() {
		List<ClassIfmVO> list = new ArrayList<ClassIfmVO>();
		ClassIfmVO classIfmVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid,passwd);
			pstmt = con.prepareStatement(FRONT_GETALL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				classIfmVO = new ClassIfmVO();
				classIfmVO.setClaid(rs.getInt("claid"));
				classIfmVO.setThrid(rs.getInt("thrid"));
				classIfmVO.setClaTagid(rs.getInt("clatagid"));
				classIfmVO.setClaTitle(rs.getString("clatitle"));
				classIfmVO.setClaIntroduction(rs.getString("claintroduction"));
				classIfmVO.setClaTime(rs.getObject("clatime",LocalDateTime.class));
				classIfmVO.setClaPrice(rs.getInt("claprice"));
				classIfmVO.setClaPeopleMax(rs.getInt("clapeoplemax"));
				classIfmVO.setClaPeopleMin(rs.getInt("clapeoplemin"));
				classIfmVO.setClaPeople(rs.getInt("clapeople"));
				classIfmVO.setClaStatus(rs.getInt("clastatus"));
				classIfmVO.setClaStrTime(rs.getObject("clastrtime",LocalDateTime.class));
				classIfmVO.setClaFinTime(rs.getObject("clafintime",LocalDateTime.class));

				
				list.add(classIfmVO); // Store the row in the list
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
	//給TIMER用的
	@Override
	public List<ClassIfmVO> timer_getcancel() {
		List<ClassIfmVO> list = new ArrayList<ClassIfmVO>();
		ClassIfmVO classIfmVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid,passwd);
			pstmt = con.prepareStatement(TIMER_GETCANCEL);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				// empVO 也稱為 Domain objects
				classIfmVO = new ClassIfmVO();
				classIfmVO.setClaid(rs.getInt("claid"));
				classIfmVO.setThrid(rs.getInt("thrid"));
				classIfmVO.setClaTagid(rs.getInt("clatagid"));
				classIfmVO.setClaTitle(rs.getString("clatitle"));
				classIfmVO.setClaIntroduction(rs.getString("claintroduction"));
				classIfmVO.setClaTime(rs.getObject("clatime",LocalDateTime.class));
				classIfmVO.setClaPrice(rs.getInt("claprice"));
				classIfmVO.setClaPeopleMax(rs.getInt("clapeoplemax"));
				classIfmVO.setClaPeopleMin(rs.getInt("clapeoplemin"));
				classIfmVO.setClaPeople(rs.getInt("clapeople"));
				classIfmVO.setClaStatus(rs.getInt("clastatus"));
				classIfmVO.setClaStrTime(rs.getObject("clastrtime",LocalDateTime.class));
				classIfmVO.setClaFinTime(rs.getObject("clafintime",LocalDateTime.class));

				
				list.add(classIfmVO); // Store the row in the list
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
	
	//單一修改課程狀態
	@Override
	public void update_clastatus(ClassIfmVO classIfmVO) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(driver);
			con = DriverManager.getConnection(url, userid,passwd);
			pstmt = con.prepareStatement(UPDATE_CLASTATUS);
			
			pstmt.setInt(1,classIfmVO.getClaid());
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

	
	


/*---------------------------------------------------------------------------------------*/
	
//	測試	
	
	public static void main(String[] args) {
		ClassIfmJDBCDAO dao = new ClassIfmJDBCDAO();
		
//		新增
//		ClassIfmVO vo = new ClassIfmVO();
//		vo.setThrid(2);
//		vo.setClaTagid(2);
//		vo.setClaTitle("課程1");
//		vo.setClaIntroduction("歡迎參加");
//		vo.setClaTime(LocalDateTime.parse("2001-01-01T10:00"));
//		vo.setClaPrice(1000);
//		vo.setClaPeopleMax(30);
//		vo.setClaPeopleMin(15);
//		vo.setClaPeople(10);
//		vo.setClaStatus(0);
//		vo.setClaStrTime(LocalDateTime.parse("2001-02-03T10:00"));
//		vo.setClaFinTime(LocalDateTime.parse("2001-02-10T10:00"));
//		dao.insert(vo);
////		修改
//		ClassIfmVO vo2 = new ClassIfmVO();
//		vo2.setThrid(2);
//		vo2.setClaTagid(1);
//		vo2.setClaTitle("課程123");
//		vo2.setClaIntroduction("修改");
//		vo2.setClaTime(LocalDateTime.parse("2010-10-01T10:00:00"));
//		vo2.setClaPrice(1000);
//		vo2.setClaPeopleMax(30);
//		vo2.setClaPeopleMin(15);
//		vo2.setClaPeople(10);
//		vo2.setClaStatus(0);
//		vo2.setClaStrTime(LocalDateTime.parse("2010-02-03T10:00:00"));
//		vo2.setClaFinTime(LocalDateTime.parse("2010-02-10T10:00:00"));
//		vo2.setClaid(2);
//		dao.update(vo2);	
//		
////		查一筆資料
//		ClassIfmVO vo3 = dao.findByPrimaryKey(2);
//		System.out.print(vo3.getClaid() + ",");
//		System.out.print(vo3.getThrid() + ",");
//		System.out.print(vo3.getClaTagid() + ",");
//		System.out.print(vo3.getClaTitle() + ",");
//		System.out.print(vo3.getClaIntroduction() + ",");
//		System.out.print(vo3.getClaTime() + ",");
//		System.out.print(vo3.getClaPrice()+ ",");
//		System.out.print(vo3.getClaPeopleMax()+ ",");
//		System.out.print(vo3.getClaPeopleMin()+ ",");
//		System.out.print(vo3.getClaPeople()+ ",");
//		System.out.print(vo3.getClaStatus()+ ",");
//		System.out.print(vo3.getClaStrTime()+ ",");
//		System.out.print(vo3.getClaFinTime()+ ",");
//		System.out.println();
//	查詢全部
//	List<ClassIfmVO> list = dao.getAll();
//	for (ClassIfmVO aEmp : list) {
//		System.out.print(aEmp.getClaid() + ",");
//		System.out.print(aEmp.getThrid() + ",");
//		System.out.print(aEmp.getClaTagid() + ",");
//		System.out.print(aEmp.getClaTitle() + ",");
//		System.out.print(aEmp.getClaIntroduction() + ",");
//		System.out.print(aEmp.getClaTime() + ",");
//		System.out.print(aEmp.getClaPrice()+ ",");
//		System.out.print(aEmp.getClaPeopleMax()+ ",");
//		System.out.print(aEmp.getClaPeopleMin()+ ",");
//		System.out.print(aEmp.getClaPeople()+ ",");
//		System.out.print(aEmp.getClaStatus()+ ",");
//		System.out.print(aEmp.getClaStrTime()+ ",");
//		System.out.print(aEmp.getClaFinTime()+ ",");
//		System.out.println();
//		
//		}
		//單一更新報名人數
//		ClassIfmVO vo2 = new ClassIfmVO();
//		
//		vo2.setClaPeople(10);
//		vo2.setClaid(2);
//		
//		dao.update_clapeople(vo2);	
		
		//單一修改課程狀態
		ClassIfmVO vo2 = new ClassIfmVO();
//		
		vo2.setClaid(1);
		
		dao.update_clastatus(vo2);	

	}

	
}
