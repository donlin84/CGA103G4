package com.emp.model;

import static com.util.common.DRIVER;
import static com.util.common.PASSWORD;
import static com.util.common.URL;
import static com.util.common.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpJDBCDAO implements EmpDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO EMPLOYEE(empName, empPhone, empAccount, empPassword) VALUES (?, ?, ?, ?);";
	private static final String GET_ALL_STMT = "SELECT empid,empName, empPhone, empAccount, empPassword, empStatus, empHiredate FROM EMPLOYEE order by empid;";
	private static final String GET_ONE_STMT = "SELECT empid,empName, empPhone, empAccount, empPassword, empStatus, empHiredate FROM EMPLOYEE where empid = ?;";
	private static final String UPDATE = "UPDATE EMPLOYEE set empName=?, empPhone=?, empPassword=?, empStatus=? where empid = ?;";
	private static final String GET_LATEST = "SELECT empid FROM EMPLOYEE ORDER BY empid DESC LIMIT 0,1";

	@Override
	public void insert(EmpVO EmpVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, EmpVO.getEmpName());
			pstmt.setString(2, EmpVO.getEmpPhone());
			pstmt.setString(3, EmpVO.getEmpAccount());
			pstmt.setString(4, EmpVO.getEmpPassword());

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
	public void update(EmpVO EmpVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, EmpVO.getEmpName());
			pstmt.setString(2, EmpVO.getEmpPhone());
			pstmt.setString(3, EmpVO.getEmpPassword());
			pstmt.setInt(4, EmpVO.getEmpStatus());
			pstmt.setInt(5, EmpVO.getEmpid());

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
	public EmpVO findByPrimaryKey(Integer empid) {

		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, empid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo Domain objects
				empVO = new EmpVO();
				empVO.setEmpid(rs.getInt("empid"));
				empVO.setEmpName(rs.getString("empName"));
				empVO.setEmpPhone(rs.getString("empPhone"));
				empVO.setEmpAccount(rs.getString("empAccount"));
				empVO.setEmpPassword(rs.getString("empPassword"));
				empVO.setEmpStatus(rs.getInt("EmpStatus"));
				empVO.setEmpHiredate(rs.getDate("empHiredate"));
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
		return empVO;
	}

	@Override
	public List<EmpVO> getAll() {
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				empVO = new EmpVO();
				empVO.setEmpid(rs.getInt("empid"));
				empVO.setEmpName(rs.getString("empName"));
				empVO.setEmpPhone(rs.getString("empPhone"));
				empVO.setEmpAccount(rs.getString("empAccount"));
				empVO.setEmpPassword(rs.getString("empPassword"));
				empVO.setEmpStatus(rs.getInt("EmpStatus"));
				empVO.setEmpHiredate(rs.getDate("empHiredate"));
				list.add(empVO); // Store the row in the list
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

	public EmpVO findLatestId() {

		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			Class.forName(DRIVER);
			con = DriverManager.getConnection(URL, USER, PASSWORD);
			pstmt = con.prepareStatement(GET_LATEST);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo Domain objects
				empVO = new EmpVO();
				empVO.setEmpid(rs.getInt("empid"));
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
		return empVO;
	}

	public static void main(String[] args) {

		EmpJDBCDAO dao = new EmpJDBCDAO();

		// 新增
//		EmpVO empVO1 = new EmpVO();
//		empVO1.setEmpName("帥哥");
//		empVO1.setEmpPhone("0987111222");
//		empVO1.setEmpAccount("ericdon");
//		empVO1.setEmpPassword("123456");
//		dao.insert(empVO1);

//		// 修改
//		EmpVO empVO2 = new EmpVO();
//		empVO2.setEmpid(106);
//		empVO2.setEmpName("醜男");
//		empVO2.setEmpPhone("0987222333");
//		empVO2.setEmpPassword("654321");
//		empVO2.setEmpStatus(1);
//		dao.update(empVO2);

		// ID查詢
//		EmpVO empVO3 = dao.findByPrimaryKey(101);
//		System.out.print(empVO3.getEmpid() + ",");
//		System.out.print(empVO3.getEmpName() + ",");
//		System.out.print(empVO3.getEmpPhone() + ",");
//		System.out.print(empVO3.getEmpAccount() + ",");
//		System.out.print(empVO3.getEmpPassword() + ",");
//		System.out.println(empVO3.getEmpStatus());
//		System.out.println(empVO3.getEmpHiredate() + ",");
//		System.out.println("---------------------");

//		// 查詢全部
//		List<EmpVO> list = dao.getAll();
//		for (EmpVO aEmp : list) {
//			System.out.print(aEmp.getEmpid() + ",");
//			System.out.print(aEmp.getEmpName() + ",");
//			System.out.print(aEmp.getEmpPhone() + ",");
//			System.out.print(aEmp.getEmpAccount() + ",");
//			System.out.print(aEmp.getEmpPassword() + ",");
//			System.out.print(aEmp.getEmpStatus() + ",");
//			System.out.print(aEmp.getEmpHiredate() + ",");
//			System.out.println();
//		}

		// 取得最新員工編號
//		EmpVO empVO = dao.findLast();
//		System.out.println(empVO.getEmpid());
	}
}
