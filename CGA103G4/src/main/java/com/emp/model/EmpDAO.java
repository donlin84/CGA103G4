package com.emp.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.announcement.model.AnnouncementVO;

public class EmpDAO implements EmpDAO_interface {

	private static final String INSERT_STMT = "INSERT INTO EMPLOYEE(empName, empPhone, empPicture, empAccount, empPassword, empLevel, empHiredate) VALUES (?, ?, ?, ?, ?, ?, ?);";
	private static final String GET_ALL_STMT = "SELECT * FROM EMPLOYEE order by empid;";
	private static final String GET_ONE_STMT = "SELECT * FROM EMPLOYEE where empid = ?;";
	private static final String UPDATE = "UPDATE EMPLOYEE set empName=?, empPhone=?, empPicture=?, empPassword=?, empLevel=?, empStatus=?, empHiredate=? where empid = ?;";
	private static final String GET_LATEST = "SELECT empid FROM EMPLOYEE ORDER BY empid DESC LIMIT 0,1";

	// 連線池
	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Cga103G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	// 新增
	@Override
	public void insert(EmpVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);

			pstmt.setString(1, empVO.getEmpName());
			pstmt.setString(2, empVO.getEmpPhone());
			pstmt.setBytes(3, empVO.getEmpPicture());
			pstmt.setString(4, empVO.getEmpAccount());
			pstmt.setString(5, empVO.getEmpPassword());
			pstmt.setInt(6, empVO.getEmpLevel());
			pstmt.setDate(7, empVO.getEmpHiredate());

			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("Couldn't load database driver. " + se.getMessage());
			// Handle any SQL errors
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

	// 修改
	@Override
	public void update(EmpVO empVO) {

		Connection con = null;
		PreparedStatement pstmt = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);

			pstmt.setString(1, empVO.getEmpName());
			pstmt.setString(2, empVO.getEmpPhone());
			pstmt.setBytes(3, empVO.getEmpPicture());
			pstmt.setString(4, empVO.getEmpPassword());
			pstmt.setInt(5, empVO.getEmpLevel());
			pstmt.setInt(6, empVO.getEmpStatus());
			pstmt.setDate(7, empVO.getEmpHiredate());
			pstmt.setInt(8, empVO.getEmpid());
			
			pstmt.executeUpdate();

			// Handle any driver errors
		} catch (SQLException se) {
			throw new RuntimeException("Couldn't load database driver. " + se.getMessage());
			// Handle any SQL errors
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

	// 查詢單一筆
	@Override
	public EmpVO findByPrimaryKey(Integer empid) {

		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);

			pstmt.setInt(1, empid);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo Domain objects
				empVO = new EmpVO();
				empVO.setEmpid(rs.getInt("empid"));
				empVO.setEmpName(rs.getString("empName"));
				empVO.setEmpPhone(rs.getString("empPhone"));
				empVO.setEmpPicture(rs.getBytes("empPicture"));
				empVO.setEmpAccount(rs.getString("empAccount"));
				empVO.setEmpPassword(rs.getString("empPassword"));
				empVO.setEmpLevel(rs.getInt("empLevel"));
				empVO.setEmpStatus(rs.getInt("empStatus"));
				empVO.setEmpHiredate(rs.getDate("empHiredate"));
			}

			// Handle any driver errors
		} catch (SQLException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

	// 查詢全部
	@Override
	public List<EmpVO> getAll() {
		List<EmpVO> list = new ArrayList<EmpVO>();
		EmpVO empVO = null;

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_STMT);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVO 也稱為 Domain objects
				empVO = new EmpVO();
				empVO.setEmpid(rs.getInt("empid"));
				empVO.setEmpName(rs.getString("empName"));
				empVO.setEmpPhone(rs.getString("empPhone"));
				empVO.setEmpPicture(rs.getBytes("empPicture"));
				empVO.setEmpAccount(rs.getString("empAccount"));
				empVO.setEmpPassword(rs.getString("empPassword"));
				empVO.setEmpLevel(rs.getInt("empLevel"));
				empVO.setEmpStatus(rs.getInt("EmpStatus"));
				empVO.setEmpHiredate(rs.getDate("empHiredate"));
				list.add(empVO); // Store the row in the list
			}

			// Handle any driver errors
		} catch (SQLException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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

	// 取得最新一筆員工編號
	public EmpVO findLatestId() {
		EmpVO empVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_LATEST);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// empVo Domain objects
				empVO = new EmpVO();
				empVO.setEmpid(rs.getInt("empid"));
			}

			// Handle any driver errors
		} catch (SQLException e) {
			throw new RuntimeException("Couldn't load database driver. " + e.getMessage());
			// Handle any SQL errors
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
	public Set<AnnouncementVO> getAnnouncementByEmpid(Integer empid) {
		Set<AnnouncementVO> set = findByPrimaryKey(empid).getAnnoucements();
		return set;
	}
}
