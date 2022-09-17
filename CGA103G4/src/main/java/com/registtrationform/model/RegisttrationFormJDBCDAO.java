package com.registtrationform.model;
import static com.util.Common_12.*;
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
			"INSERT INTO RegisttrationForm (claid,memid,regPayment,regStatus,regPeople,regReview,regReviewContent) VALUES (?,?,?, ?, ?, ?, ?)";
		private static final String GET_ALL_STMT = 
			"SELECT claid,memid,regPayment,regTime,regStatus,regPeople,regReview,regReviewContent FROM RegisttrationForm order by claid,memid";
		private static final String GET_ONE_STMT = 
			"SELECT claid,memid,regPayment,regTime,regStatus,regPeople,regReview,regReviewContent FROM RegisttrationForm where claid = ? and memid = ? ";

		private static final String UPDATE = 
			"UPDATE RegisttrationForm set regPayment=?,regStatus=?,regPeople=? where claid = ? and memid= ? ";
		
		private static final String GET_SUM_REGPEOPLE = " select sum(regPeople)regPeople from registtrationform where claid = ? and regStatus = 0  ";
		//給timer抓memid用的
		private static final String TIMER_GETMEMID="select memid from registtrationform where claid = ? and regStatus=0";
		//單純修改訂單狀態
		private static final String UPDATE_SATATUS="UPDATE registtrationform SET regStatus = 1 WHERE claid = ? and memid = ?";
		//用來更新評價及評分用的
		private static final String UPDATE_REVIEW ="UPDATE RegisttrationForm set regReview=?,regReviewContent=? where claid = ? and memid= ? ";
		//給click_people用的 按照狀態排
		private static final String CLICL_PEOPLE ="select * from registtrationform where claid = ? order by regStatus";
		
		
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
				pstmt.setInt(5, registtrationFormVO.getRegPeople());
				//null相關
//				pstmt.setInt(5, registtrationFormVO.getRegReview());
				Integer RegReview = registtrationFormVO.getRegReview();
				if(RegReview == null) {
					pstmt.setNull(6, Types.INTEGER);
				} else {
					pstmt.setInt(6, RegReview);
				}	
				//null相關
				pstmt.setString(7, registtrationFormVO.getRegReviewContent());
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
				pstmt.setInt(3, registtrationFormVO.getRegPeople());
								
				pstmt.setInt(4, registtrationFormVO.getClaid());
				pstmt.setInt(5, registtrationFormVO.getMemid());

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
					registtrationFormVO.setRegPeople(rs.getInt("regPeople"));
					
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
					registtrationFormVO.setRegPeople(rs.getInt("regPeople"));
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
		@Override
		public Integer getConutPeopleByClaid(Integer claid) {
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			Integer ConutPeople = 0;
			
			try {
				
				Class.forName(driver);
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = con.prepareStatement(GET_SUM_REGPEOPLE);
				pstmt.setInt(1, claid);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					ConutPeople = rs.getInt("regPeople");
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
			return ConutPeople;
		}
		//給TIMER用的抓MEMID
		@Override
		public List<RegisttrationFormVO> timer_getmemid(Integer claid) {
			List<RegisttrationFormVO> list = new ArrayList<RegisttrationFormVO>();
			RegisttrationFormVO registtrationFormVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = con.prepareStatement(TIMER_GETMEMID);
				pstmt.setInt(1, claid);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					// empVO 也稱為 Domain objects
					registtrationFormVO = new RegisttrationFormVO();
					registtrationFormVO.setMemid(rs.getInt("memid"));

					
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
		
		//單純些改訂單狀態便取消
		@Override
		public void update_status(Integer claid,Integer memid) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = con.prepareStatement(UPDATE_SATATUS);
				pstmt.setInt(1, claid);
				pstmt.setInt(2, memid);

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
		public void update_review(RegisttrationFormVO registtrationFormVO) {
			Connection con = null;
			PreparedStatement pstmt = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = con.prepareStatement(UPDATE_REVIEW);
				pstmt.setInt(1, registtrationFormVO.getRegReview());
				pstmt.setString(2, registtrationFormVO.getRegReviewContent());
								
				pstmt.setInt(3, registtrationFormVO.getClaid());
				pstmt.setInt(4, registtrationFormVO.getMemid());

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
		//給click_people用
		@Override
		public List<RegisttrationFormVO> click_people(Integer claid) {
			List<RegisttrationFormVO> list = new ArrayList<RegisttrationFormVO>();
			RegisttrationFormVO registtrationFormVO = null;

			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			try {

				Class.forName(driver);
				con = DriverManager.getConnection(URL, USER, PASSWORD);
				pstmt = con.prepareStatement(CLICL_PEOPLE);
				pstmt.setInt(1, claid);
				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					// empVO 也稱為 Domain objects
					registtrationFormVO = new RegisttrationFormVO();
					registtrationFormVO.setMemid(rs.getInt("memid"));

					
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
//			RegisttrationFormVO vo = new RegisttrationFormVO();
//			vo.setClaid(3);
//			vo.setMemid(203);
//			vo.setRegPayment(1);
//			vo.setRegStatus(1);
//			vo.setRegPeople(3);
//			vo.setRegReview(null);
//			vo.setRegReviewContent(null);
//			dao.insert(vo);
			
//			修改
//			RegisttrationFormVO vo2 = new RegisttrationFormVO();
//			vo2.setClaid(3);
//			vo2.setMemid(203);
//			vo2.setRegPayment(1);
//			vo2.setRegStatus(1);
//			vo2.setRegPeople(2);
//			vo2.setRegReview(null);
//			vo2.setRegReviewContent(null);
//			dao.update(vo2);
			
//			查一筆資料
//			RegisttrationFormVO vo3 = dao.findByPrimaryKey(1,201);
//			System.out.print(vo3.getClaid() + ",");
//			System.out.print(vo3.getMemid() + ",");
//			System.out.print(vo3.getRegPayment() + ",");
//			System.out.print(vo3.getRegTime() + ",");
//			System.out.print(vo3.getRegStatus() + ",");
//			System.out.print(vo3.getRegPeople() + ",");
//			System.out.print(vo3.getRegReview() + ",");
//			System.out.print(vo3.getRegReviewContent()+ ",");
//			System.out.println();		
			
//			查詢全部
//			List<RegisttrationFormVO> list = dao.getAll();
//			for (RegisttrationFormVO aEmp : list) {
//				System.out.print(aEmp.getClaid() + ",");
//				System.out.print(aEmp.getMemid() + ",");
//				System.out.print(aEmp.getRegPayment() + ",");
//				System.out.print(aEmp.getRegTime() + ",");
//				System.out.print(aEmp.getRegStatus() + ",");
//				System.out.print(aEmp.getRegPeople() + ",");
//				System.out.print(aEmp.getRegReview() + ",");
//				System.out.print(aEmp.getRegReviewContent() + ",");
//				
//				System.out.println();
//				}		
			
//          查單一課程報名人數
//			System.out.println(dao.getConutPeopleByClaid(1));
			
//			List<RegisttrationFormVO> list= dao.timer_getmemid(1);
//			for (RegisttrationFormVO aEmp : list) {
//				System.out.print(aEmp.getMemid() + ",");
//				System.out.println();
//				}	
			//單純修改訂單狀態
			dao.update_status(1, 201);
		}
		
		
}
