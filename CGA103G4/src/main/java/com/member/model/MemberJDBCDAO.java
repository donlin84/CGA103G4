package com.member.model;
import static com.util.Common.*;

import java.io.Console;
import java.sql.*;
import java.util.*;


import jdbc.util.CompositeQuery.jdbcUtil_CompositeQuery_Member;


public class MemberJDBCDAO implements MemberDAO_interface{


	private static final String INSERT_STMT = "INSERT INTO Member (memName, "
			+ "memAccount, memPassword, memGender, memPhone, memEmail, memAddres, "
			+ "memBirthday, memNation) " 
			+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String GET_ALL_STMT = "SELECT memid, memName, memAccount, "
			+ "memPassword, memGender, memPhone, memEmail, memAddres, memBirthday, "
			+ "memStatus, memNation " 
			+ "FROM Member order by memid";
	private static final String GET_ONE_STMT = "SELECT memid, memName, memAccount, "
			+ "memPassword, memGender, memPhone, memEmail, memAddres, memBirthday, "
			+ "memStatus, memNation " 
			+ "FROM Member where memid = ?";
	private static final String UPDATE = "UPDATE Member set memName=?, memAccount=?,  "
			+ "memPassword=?, memGender=?, memPhone=?, memEmail=?, memAddres=?, "
			+ "memBirthday=?, memStatus=?, memNation=? " 
			+ "where memid = ?";
	private static final String GET_ALL_MEMID = "select memid from CGA103G4.Member order by memid" ;

	@Override
	public void insert(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(INSERT_STMT);
			pstmt.setString(1, memberVO.getMemName());
			pstmt.setString(2, memberVO.getMemAccount());
			pstmt.setString(3, memberVO.getMemPassword());
			pstmt.setString(4, memberVO.getMemGender());
			pstmt.setString(5, memberVO.getMemPhone());
			pstmt.setString(6, memberVO.getMemEmail());
			pstmt.setString(7, memberVO.getMemAddres());
			pstmt.setDate(8, memberVO.getMemBirthday());
			pstmt.setString(9, memberVO.getMemNation());
//			System.out.println("action");
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
	public void update(MemberVO memberVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(UPDATE);
			pstmt.setString(1, memberVO.getMemName());
			pstmt.setString(2, memberVO.getMemAccount());
			pstmt.setString(3, memberVO.getMemPassword());
			pstmt.setString(4, memberVO.getMemGender());
			pstmt.setString(5, memberVO.getMemPhone());
			pstmt.setString(6, memberVO.getMemEmail());
			pstmt.setString(7, memberVO.getMemAddres());
			pstmt.setDate(8, memberVO.getMemBirthday());
			pstmt.setInt(9, memberVO.getMemStatus());
			pstmt.setString(10, memberVO.getMemNation());
			pstmt.setInt(11, memberVO.getMemid());
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
	public MemberVO findByPrimaryKey(Integer memid) {
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(GET_ONE_STMT);
			pstmt.setInt(1, memid);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemid(rs.getInt("memid"));
				memberVO.setMemName(rs.getString("memName"));
				memberVO.setMemAccount(rs.getString("memAccount"));
				memberVO.setMemPassword(rs.getString("memPassword"));
				memberVO.setMemGender(rs.getString("memGender"));
				memberVO.setMemPhone(rs.getString("memPhone"));
				memberVO.setMemEmail(rs.getString("memEmail"));
				memberVO.setMemAddres(rs.getString("memAddres"));
				memberVO.setMemBirthday(rs.getDate("memBirthday"));
				memberVO.setMemStatus(rs.getInt("memStatus"));
				memberVO.setMemNation(rs.getString("memNation"));
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
		return memberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemid(rs.getInt("memid"));
				memberVO.setMemName(rs.getString("memName"));
				memberVO.setMemAccount(rs.getString("memAccount"));
				memberVO.setMemPassword(rs.getString("memPassword"));
				memberVO.setMemGender(rs.getString("memGender"));
				memberVO.setMemPhone(rs.getString("memPhone"));
				memberVO.setMemEmail(rs.getString("memEmail"));
				memberVO.setMemAddres(rs.getString("memAddres"));
				memberVO.setMemBirthday(rs.getDate("memBirthday"));
				memberVO.setMemStatus(rs.getInt("memStatus"));
				memberVO.setMemNation(rs.getString("memNation"));
				list.add(memberVO); // Store the row in the list
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
	
	public List<Integer> getAllMemid() {
		List<Integer> list = new ArrayList<Integer>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = conn.prepareStatement(GET_ALL_MEMID);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getInt("memid")); // Store the row in the list
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
	public List<MemberVO> getAll(Map<String, String[]> map) {
		List<MemberVO> list = new ArrayList<MemberVO>();
		MemberVO memberVO = null;
	
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		try {
			Class.forName(DRIVER);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);

			String finalSQL = "select * from member "
		          + jdbcUtil_CompositeQuery_Member.get_WhereCondition(map)
		          + "order by memid";
			pstmt = conn.prepareStatement(finalSQL);
//			System.out.println("●●finalSQL(by DAO) = "+finalSQL);
			rs = pstmt.executeQuery();
	
			while (rs.next()) {
				memberVO = new MemberVO();
				memberVO.setMemid(rs.getInt("memid"));
				memberVO.setMemName(rs.getString("memName"));
				memberVO.setMemAccount(rs.getString("memAccount"));
				memberVO.setMemPassword(rs.getString("memPassword"));
				memberVO.setMemGender(rs.getString("memGender"));
				memberVO.setMemPhone(rs.getString("memPhone"));
				memberVO.setMemEmail(rs.getString("memEmail"));
				memberVO.setMemAddres(rs.getString("memAddres"));
				memberVO.setMemBirthday(rs.getDate("memBirthday"));
				memberVO.setMemStatus(rs.getInt("memStatus"));
				memberVO.setMemNation(rs.getString("memNation"));
				list.add(memberVO); // Store the row in the List
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


	public static void main(String[] args) {
		MemberJDBCDAO dao = new MemberJDBCDAO();

		// 新增
//		MemberVO memberVO1 = new MemberVO();
//		memberVO1.setMemName("郭富乘");
//		memberVO1.setMemAccount("Account6");
//		memberVO1.setMemPassword("Password6");
//		memberVO1.setMemGender("f");
//		memberVO1.setMemPhone("0988000666");
//		memberVO1.setMemEmail("soso6@gmail.com");
//		memberVO1.setMemAddres("");
//		memberVO1.setMemBirthday(java.sql.Date.valueOf("2005-01-01"));
//		memberVO1.setMemNation("台灣");
//		dao.insert(memberVO1);

		// 修改
//		MemberVO memberVO2 = new MemberVO();
//		memberVO2.setMemid(201);
//		memberVO2.setMemName("郭富乘2");
//		memberVO2.setMemAccount("Account7");
//		memberVO2.setMemPassword("Password7");
//		memberVO2.setMemGender("m");
//		memberVO2.setMemPhone("0988000777");
//		memberVO2.setMemEmail("soso7@gmail.com");
////		memberVO2.setMemAddres("");
//		memberVO2.setMemBirthday(java.sql.Date.valueOf("2000-11-11"));
//		memberVO2.setMemStatus(0);
//		memberVO2.setMemNation("台灣");
//		dao.update(memberVO2);

		// 查詢
		MemberVO memberVO3 = dao.findByPrimaryKey(201);
		System.out.print(memberVO3.getMemid() + ",");
		System.out.print(memberVO3.getMemName() + ",");
		System.out.print(memberVO3.getMemAccount() + ",");
		System.out.print(memberVO3.getMemPassword() + ",");
		System.out.print(memberVO3.getMemGender() + ",");
		System.out.print(memberVO3.getMemPhone() + ",");
		System.out.print(memberVO3.getMemEmail() + ",");
		System.out.print(memberVO3.getMemAddres() + ",");
		System.out.print(memberVO3.getMemBirthday() + ",");
		System.out.print(memberVO3.getMemStatus() + ",");
		System.out.println(memberVO3.getMemNation());
		System.out.println("---------------------");
		
		// 查詢
		List<MemberVO> list = dao.getAll();
		for (MemberVO aMember : list) {
			System.out.print(aMember.getMemid() + ",");
			System.out.print(aMember.getMemName() + ",");
			System.out.print(aMember.getMemAccount() + ",");
			System.out.print(aMember.getMemPassword() + ",");
			System.out.print(aMember.getMemGender() + ",");
			System.out.print(aMember.getMemPhone() + ",");
			System.out.print(aMember.getMemEmail() + ",");
			System.out.print(aMember.getMemAddres() + ",");
			System.out.print(aMember.getMemBirthday() + ",");
			System.out.print(aMember.getMemStatus() + ",");
			System.out.print(aMember.getMemNation() + ",");
			System.out.println();
		}
//		查詢全部memid
		List list1 = dao.getAllMemid();
		System.out.println(list1);}}
	
