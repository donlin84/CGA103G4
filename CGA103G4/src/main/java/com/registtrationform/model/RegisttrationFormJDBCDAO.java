package com.registtrationform.model;
import static com.util.Common.*;
import java.lang.reflect.Member;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.*;


public class RegisttrationFormJDBCDAO implements RegisttrationFormDAO_interface {
	String driver = "com.mysql.cj.jdbc.Driver";
	private static final String INSERT_STMT = 
			"INSERT INTO RegisttrationForm (claid,memid,regPayment,regStatus,regReview,regReviewContent) VALUES (?, ?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT claid,memid,regPayment,regTime,regStatus,regReview,regReviewContent FROM RegisttrationForm order by claid,memid";
		private static final String GET_ONE_STMT = 
			"SELECT claid,memid,regPayment,regTime,regStatus,regReview,regReviewContent FROM RegisttrationForm where claid = ? and memid = ? ";
//		private static final String DELETE = 
//			"DELETE FROM Teacher where thrid = ?";
		private static final String UPDATE = 
			"UPDATE RegisttrationForm set regPayment=?,regStatus=?,regReview=?,regReviewContent=? where claid = ? and memid= ? ";
		@Override
		public void insert(RegisttrationFormVO registtrationFormVO) {
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {

				Class.forName(driver);
				con = DriverManager.getConnection(URL, USER,PASSWORD);
				pstmt = con.prepareStatement(INSERT_STMT);

				pstmt.setInt(1, registtrationFormVO.getClaid());
				pstmt.setInt(2, registtrationFormVO.getMemid());
				pstmt.setInt(3, registtrationFormVO.getRegPayment());
				pstmt.setInt(4, registtrationFormVO.getRegStatus());
				//null相關
//				pstmt.setInt(5, registtrationFormVO.getRegReview());
				Integer RegReview = registtrationFormVO.getRegReview();
				if(RegReview == null) {
					pstmt.setNull(5, Types.INTEGER);
				} else {
					pstmt.setInt(5, RegReview);
				}	
				//null相關
				pstmt.setString(6, registtrationFormVO.getRegReviewContent());
//				String RegReviewContent = registtrationFormVO.getRegReviewContent();
//				if(RegReviewContent == null) {
//					pstmt.setNull(6, Types.VARCHAR);
//				} else {
//					pstmt.setString(6, RegReviewContent);
//				}	
				
				

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
		public void update(RegisttrationFormVO registtrationFormVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = con.prepareStatement(UPDATE);
				pstmt.setInt(1, registtrationFormVO.getRegPayment());
				pstmt.setInt(2, registtrationFormVO.getRegStatus());
				//NULL相關				
//				pstmt.setInt(3, registtrationFormVO.getRegReview());
				Integer RegReview = registtrationFormVO.getRegReview();
				if(RegReview == null) {
					pstmt.setNull(3, Types.INTEGER);
				} else {
					pstmt.setInt(3, RegReview);
				}	
//				NULL相關
				pstmt.setString(4, registtrationFormVO.getRegReviewContent());
//				String RegReviewContent = registtrationFormVO.getRegReviewContent();
//				if(RegReviewContent.isEmpty()) {
//					pstmt.setNull(4, Types.VARCHAR);
//				} else {
//					pstmt.setString(4, RegReviewContent);
//				}					
				pstmt.setInt(5, registtrationFormVO.getClaid());
				pstmt.setInt(6, registtrationFormVO.getMemid());

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
		public RegisttrationFormVO findByPrimaryKey(Integer claid, Integer memid) {
			
			RegisttrationFormVO registtrationFormVO = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = con.prepareStatement(GET_ONE_STMT);

				pstmt.setInt(1, claid);
				pstmt.setInt(2, memid);

				rs = pstmt.executeQuery();

				while (rs.next()) {
					// empVo 也稱為 Domain object
					registtrationFormVO = new RegisttrationFormVO();
					registtrationFormVO.setClaid(rs.getInt("claid"));
					registtrationFormVO.setMemid(rs.getInt("memid"));
					registtrationFormVO.setRegPayment(rs.getInt("regpayment"));
					registtrationFormVO.setRegTime(rs.getObject("regtime",LocalDateTime.class));			
					registtrationFormVO.setRegStatus(rs.getInt("regstatus"));
					
					//null相關
//					registtrationFormVO.setRegReview(rs.getInt("regreview"));
					registtrationFormVO.setRegReview(rs.getInt("regreview"));
					if (rs.wasNull()) {
						registtrationFormVO.setRegReview(null);
					}							
					
					registtrationFormVO.setRegReviewContent(rs.getString("regreviewcontent"));
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
			return registtrationFormVO;
		}
		@Override
		public List<RegisttrationFormVO> getAll() {
			List<RegisttrationFormVO> list = new ArrayList<RegisttrationFormVO>();
			RegisttrationFormVO registtrationFormVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = con.prepareStatement(GET_ALL_STMT);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					// empVO 也稱為 Domain objects
					registtrationFormVO = new RegisttrationFormVO();
					registtrationFormVO.setClaid(rs.getInt("claid"));
					registtrationFormVO.setMemid(rs.getInt("memid"));
					registtrationFormVO.setRegPayment(rs.getInt("regpayment"));
					registtrationFormVO.setRegTime(rs.getObject("regtime",LocalDateTime.class));
					registtrationFormVO.setRegStatus(rs.getInt("regstatus"));
					//NULL相關
//					registtrationFormVO.setRegReview(rs.getInt("regreview"));
					registtrationFormVO.setRegReview(rs.getInt("regreview"));
					if (rs.wasNull()) {
						registtrationFormVO.setRegReview(null);
					}
					registtrationFormVO.setRegReviewContent(rs.getString("regreviewcontent"));

					
					list.add(registtrationFormVO); // Store the row in the list
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
			RegisttrationFormJDBCDAO dao = new RegisttrationFormJDBCDAO();

//			新增
			RegisttrationFormVO vo = new RegisttrationFormVO();
//			vo.setClaid(3);
//			vo.setMemid(201);
//			vo.setRegPayment(1);
//			vo.setRegStatus(1);
//			vo.setRegReview(null);
//			vo.setRegReviewContent(null);
//			dao.insert(vo);
			
//			修改
			RegisttrationFormVO vo2 = new RegisttrationFormVO();
			vo2.setClaid(2);
			vo2.setMemid(201);
			vo2.setRegPayment(1);
			vo2.setRegStatus(1);
			vo2.setRegReview(null);
			vo2.setRegReviewContent(null);
			dao.update(vo2);
			
//			查一筆資料
			RegisttrationFormVO vo3 = dao.findByPrimaryKey(1,201);
			System.out.print(vo3.getClaid() + ",");
			System.out.print(vo3.getMemid() + ",");
			System.out.print(vo3.getRegPayment() + ",");
			System.out.print(vo3.getRegTime() + ",");
			System.out.print(vo3.getRegStatus() + ",");
			System.out.print(vo3.getRegReview() + ",");
			System.out.print(vo3.getRegReviewContent()+ ",");
			System.out.println();			
//			查詢全部
			List<RegisttrationFormVO> list = dao.getAll();
			for (RegisttrationFormVO aEmp : list) {
				System.out.print(aEmp.getClaid() + ",");
				System.out.print(aEmp.getMemid() + ",");
				System.out.print(aEmp.getRegPayment() + ",");
				System.out.print(aEmp.getRegTime() + ",");
				System.out.print(aEmp.getRegStatus() + ",");
				System.out.print(aEmp.getRegReview() + ",");
				System.out.print(aEmp.getRegReviewContent() + ",");
				
				System.out.println();
				}			
		}
}
