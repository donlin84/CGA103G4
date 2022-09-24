package com.chef.model;


import static com.util.Common.*;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Chef;

public class ChefJDBCDAO implements ChefDAO_interface {
//	String driver = "com.mysql.cj.jdbc.Driver";

	private static final String INSERT_STMT = "INSERT INTO Chef(chefName, chefNickname, "
			+ "chefAccount, chefPassword, chefPrice, license, idCard, idCardBack, chefPhoto, chefIntroduction ) "
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT chefid, chefAccount, chefPassword, chefStatus, chefName, chefNickname, chefPrice, "
			+ "schDate, reserve, com, gomg, license, idCard, idCardBack, chefPhoto, chefIntroduction "
			+ "FROM Chef order by chefid";
	private static final String GET_ONE_STMT = "SELECT chefid, chefAccount, chefPassword, chefStatus, chefName, chefNickname, chefPrice, "
			+ "schDate, reserve, com, gomg, license, idCard, idCardBack, chefPhoto, chefIntroduction "
			+ "FROM Chef where chefid = ?";
	private static final String UPDATE = "UPDATE Chef set chefName=?, chefNickname=?, chefAccount=?, chefPassword=?,  chefStatus=?, "
			+ "chefPrice=?, license=?, idCard=?, idCardBack=?, chefPhoto=?,  chefIntroduction=? " 
			+ "where chefid = ?";
	//後台登入
	private static final String GET_ONE_ACCOUNT ="select * from chef where BINARY chefAccount = ?";

	@Override
	public void insert(ChefVO chefVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(INSERT_STMT);
			pstmt.setString(1, chefVO.getChefName());
			pstmt.setString(2, chefVO.getChefNickname());
			pstmt.setString(3, chefVO.getChefAccount());
			pstmt.setString(4, chefVO.getChefPassword());
			pstmt.setInt(5, chefVO.getChefPrice());
			pstmt.setBytes(6, chefVO.getLicense());
			pstmt.setBytes(7, chefVO.getIdCard());
			pstmt.setBytes(8, chefVO.getIdCardBack());
			pstmt.setBytes(9, chefVO.getChefPhoto());
			pstmt.setString(10, chefVO.getChefIntroduction());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public void update(ChefVO chefVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(UPDATE);
			pstmt.setString(1, chefVO.getChefName());
			pstmt.setString(2, chefVO.getChefNickname());
			pstmt.setString(3, chefVO.getChefAccount());
			pstmt.setString(4, chefVO.getChefPassword());
			pstmt.setInt(5, chefVO.getChefStatus());
			pstmt.setInt(6, chefVO.getChefPrice());
			pstmt.setBytes(7, chefVO.getLicense());
			pstmt.setBytes(8, chefVO.getIdCard());
			pstmt.setBytes(9, chefVO.getIdCardBack());
			pstmt.setBytes(10, chefVO.getChefPhoto());
			pstmt.setString(11, chefVO.getChefIntroduction());
			pstmt.setInt(12, chefVO.getChefid());
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public ChefVO findByPrimaryKey(Integer chefid) {
		ChefVO chefVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, chefid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				chefVO = new ChefVO();
				chefVO.setChefid(rs.getInt("chefid"));
				chefVO.setChefName(rs.getString("chefName"));
				chefVO.setChefNickname(rs.getString("chefNickname"));
				chefVO.setChefAccount(rs.getString("chefAccount"));
				chefVO.setChefPassword(rs.getString("chefPassword"));
				chefVO.setChefStatus(rs.getInt("chefStatus"));
				chefVO.setChefPrice(rs.getInt("chefPrice"));
				chefVO.setSchDate(rs.getString("schDate"));
				chefVO.setReserve(rs.getInt("reserve"));
				chefVO.setCom(rs.getInt("com"));
				chefVO.setGomg(rs.getInt("gomg"));
				chefVO.setLicense(rs.getBytes("license"));
				chefVO.setIdCard(rs.getBytes("idCard"));
				chefVO.setIdCardBack(rs.getBytes("idCardBack"));
				chefVO.setChefPhoto(rs.getBytes("chefPhoto"));
				chefVO.setChefIntroduction(rs.getString("chefIntroduction"));
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
		return chefVO;
	}

	@Override
	public List<ChefVO> getAll() {
		List<ChefVO> list = new ArrayList<ChefVO>();
		ChefVO chefVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				chefVO = new ChefVO();
				chefVO.setChefid(rs.getInt("chefid"));
				chefVO.setChefName(rs.getString("chefName"));
				chefVO.setChefNickname(rs.getString("chefNickname"));
				chefVO.setChefAccount(rs.getString("chefAccount"));
				chefVO.setChefPassword(rs.getString("chefPassword"));
				chefVO.setChefStatus(rs.getInt("chefStatus"));
				chefVO.setChefPrice(rs.getInt("chefPrice"));
				chefVO.setSchDate(rs.getString("schDate"));
				chefVO.setReserve(rs.getInt("reserve"));
				chefVO.setCom(rs.getInt("com"));
				chefVO.setGomg(rs.getInt("gomg"));
				chefVO.setLicense(rs.getBytes("license"));
				chefVO.setIdCard(rs.getBytes("idCard"));
				chefVO.setIdCardBack(rs.getBytes("idCardBack"));
				chefVO.setChefPhoto(rs.getBytes("chefPhoto"));
				chefVO.setChefIntroduction(rs.getString("chefIntroduction"));
				list.add(chefVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
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
	public List<ChefVO> getAll(Map<String, String[]> map) {
		List<ChefVO> list = new ArrayList<ChefVO>();
		ChefVO chefVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

			String finalSQL = "select * from chef "
		          + jdbcUtil_CompositeQuery_Chef.get_WhereCondition(map)
		          + "order by chefid";
			pstmt = conn.prepareStatement(finalSQL);
			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				chefVO = new ChefVO();
				chefVO.setChefid(rs.getInt("chefid"));
				chefVO.setChefAccount(rs.getString("chefAccount"));
				chefVO.setChefPassword(rs.getString("chefPassword"));
				chefVO.setChefStatus(rs.getInt("chefStatus"));
				chefVO.setChefName(rs.getString("chefName"));
				chefVO.setChefNickname(rs.getString("chefNickname"));
				chefVO.setChefPrice(rs.getInt("chefPrice"));
				chefVO.setChefIntroduction(rs.getString("chefIntroduction"));
				list.add(chefVO); // Store the row in the List
			}
	
			// Handle any SQL errors
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't load database driver. "
					+ e.getMessage());
			// Handle any SQL errors
		}catch (SQLException se) {
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
	
	//後台登入
		@Override
		public ChefVO get_one_account(String chefAccount) {
			ChefVO chefVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
				Class.forName(DRIVER);
				Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = conn.prepareStatement(GET_ONE_ACCOUNT);
				pstmt.setString(1, chefAccount);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					chefVO = new ChefVO();
					chefVO.setChefid(rs.getInt("chefid"));
					chefVO.setChefName(rs.getString("chefName"));
					chefVO.setChefNickname(rs.getString("chefNickname"));
					chefVO.setChefAccount(rs.getString("chefAccount"));
					chefVO.setChefPassword(rs.getString("chefPassword"));
					chefVO.setChefStatus(rs.getInt("chefStatus"));
					chefVO.setChefPrice(rs.getInt("chefPrice"));
					chefVO.setSchDate(rs.getString("schDate"));
					chefVO.setReserve(rs.getInt("reserve"));
					chefVO.setCom(rs.getInt("com"));
					chefVO.setGomg(rs.getInt("gomg"));
					chefVO.setLicense(rs.getBytes("license"));
					chefVO.setIdCard(rs.getBytes("idCard"));
					chefVO.setIdCardBack(rs.getBytes("idCardBack"));
					chefVO.setChefPhoto(rs.getBytes("chefPhoto"));
					chefVO.setChefIntroduction(rs.getString("chefIntroduction"));
				}

				// Handle any driver errors
			} catch (ClassNotFoundException e) {
				throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
				// Handle any SQL errors
			} catch (SQLException se) {
				throw new RuntimeException("A database error occured. " + se.getMessage());
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
			return chefVO;
		}

	public static byte[] getPictureByteArray(String path) throws IOException {
		FileInputStream fis = new FileInputStream(path);
		byte[] buffer = new byte[fis.available()];
		fis.read(buffer);
		fis.close();
		return buffer;
	}

	public static void main(String[] args) throws IOException {
		ChefJDBCDAO dao = new ChefJDBCDAO();

		// 新增
//		ChefVO chefVO1 = new ChefVO();
//		chefVO1.setChefName("吳永志2");
//		chefVO1.setChefNickname("大吳2");
//		chefVO1.setChefAccount("Account6");
//		chefVO1.setChefPassword("Pass333");
//		chefVO1.setChefPrice(99999);
//		byte[] pic1 = getPictureByteArray(	"images/Tibame.png");
////		byte[] pic1 = getPictureByteArray(
////				"C:\\CGA103_WebApp\\eclipse_WTP_workspace1\\CGA103G4HUNG\\src\\main\\java\\com\\chef\\model\\images\\Tibame.png");
//		chefVO1.setLicense(pic1);
//
//		byte[] pic2 = getPictureByteArray("images/none3.jpg");
//		chefVO1.setIdCard(pic2);
//		byte[] pic3 = getPictureByteArray(	"images/none.jpg");
//		chefVO1.setIdCardBack(pic3);
//		byte[] pic4 = getPictureByteArray(	"images/none2.jpg");
//		chefVO1.setChefPhoto(pic4);
//		chefVO1.setChefIntroduction("我很厲害2");
//		dao.insert(chefVO1);

		// 修改
//		ChefVO chefVO2 = new ChefVO();
//		chefVO2.setChefid(304);
//		chefVO2.setChefName("吳永志1");
//		chefVO2.setChefNickname("大吳1");
//		chefVO2.setChefAccount("Account4");
//		chefVO2.setChefPassword("Pass333");
//		chefVO2.setChefStatus(1);
//		chefVO2.setChefPrice(99999);
//		chefVO2.setSchDate("1111111");
//		chefVO2.setReserve(100);
//		chefVO2.setCom(100);
//		chefVO2.setGomg(1000);
//		chefVO2.setLicense(null);
//		chefVO2.setIdCard(null);
//		chefVO2.setIdCardBack(null);
//		chefVO2.setChefPhoto(null);
//		chefVO2.setChefIntroduction("我很厲害1");
//		dao.update(chefVO2);

		// 查詢
		ChefVO chefVO3 = dao.findByPrimaryKey(301);
		System.out.print(chefVO3.getChefid() + ",");
		System.out.print(chefVO3.getChefName() + ",");
		System.out.print(chefVO3.getChefNickname() + ",");
		System.out.print(chefVO3.getChefAccount() + ",");
		System.out.print(chefVO3.getChefPassword() + ",");
		System.out.print(chefVO3.getChefStatus() + ",");
		System.out.print(chefVO3.getChefPrice() + ",");
		System.out.print(chefVO3.getSchDate() + ",");
		System.out.print(chefVO3.getReserve() + ",");
		System.out.print(chefVO3.getCom() + ",");
		System.out.print(chefVO3.getGomg() + ",");
		System.out.print(chefVO3.getLicense() + ",");
		System.out.print(chefVO3.getIdCard() + ",");
		System.out.print(chefVO3.getIdCardBack() + ",");
		System.out.print(chefVO3.getChefPhoto() + ",");
		System.out.println(chefVO3.getChefIntroduction());
		System.out.println("---------------------");

		// 查詢
		List<ChefVO> list = dao.getAll();
		for (ChefVO aChef : list) {
			System.out.print(aChef.getChefid() + ",");
			System.out.print(aChef.getChefName() + ",");
			System.out.print(aChef.getChefNickname() + ",");
			System.out.print(aChef.getChefAccount() + ",");
			System.out.print(aChef.getChefPassword() + ",");
			System.out.print(aChef.getChefStatus() + ",");
			System.out.print(aChef.getChefPrice() + ",");
			System.out.print(aChef.getSchDate() + ",");
			System.out.print(aChef.getReserve() + ",");
			System.out.print(aChef.getCom() + ",");
			System.out.print(aChef.getGomg() + ",");
			System.out.print(aChef.getLicense() + ",");
			System.out.print(aChef.getIdCard() + ",");
			System.out.print(aChef.getIdCardBack() + ",");
			System.out.print(aChef.getChefPhoto() + ",");
			System.out.print(aChef.getChefIntroduction());
			System.out.println();
		}
	}
}
