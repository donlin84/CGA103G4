package com.keyword.model;

import static com.util.Common_15.PASSWORD;
import static com.util.Common_15.URL;
import static com.util.Common_15.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.time.LocalDateTime;
import java.util.*;

import com.orders.model.OrdersVO;
import com.teacher.model.TeacherVO;



public class KeyWordJDBCDAO implements KeyWordDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";

	private static final String INSERT_STMT = 
		"INSERT INTO KeyWord (kwName,kwContext) VALUES (?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT kwid,kwName,kwContext FROM KeyWord order by kwid";
	private static final String GET_ONE_STMT = 
		"SELECT kwid,kwName,kwContext FROM KeyWord  where kwid = ?";
	private static final String DELETE = 
		"DELETE FROM KeyWord where kwid = ?";
	private static final String UPDATE = 
		"UPDATE KeyWord set kwName=? ,kwContext=? where kwid = ?";
	
	
	
	@Override
	public void insert(KeyWordVO keyWordVO) {
		try (
				Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(INSERT_STMT);
			){
				pstmt.setString(1, keyWordVO.getKwName());
				pstmt.setString(2, keyWordVO.getKwContext());

				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}


	@Override
	public void update(KeyWordVO keyWordVO) {
		try (
				Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(UPDATE);
			){
				pstmt.setString(1, keyWordVO.getKwName());
				pstmt.setString(2, keyWordVO.getKwContext());
				pstmt.setInt(3, keyWordVO.getKwid());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	@Override
	public void delete(Integer kwid) {
		
		try (
				Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(DELETE);
			){
				pstmt.setInt(1, kwid);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	@Override
	public KeyWordVO findByPrimaryKey(Integer kwid) {
		KeyWordVO keyWordVO = null;
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(GET_ONE_STMT);	
		){
			pstmt.setInt(1, kwid);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				keyWordVO = new KeyWordVO();
				keyWordVO.setKwid(rs.getInt("kwid"));
				keyWordVO.setKwName(rs.getString("kwName"));
				keyWordVO.setKwContext(rs.getString("kwContext"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return keyWordVO;
	}

	@Override
	public List<KeyWordVO> getAll() {
		List<KeyWordVO> list = new ArrayList<KeyWordVO>();
		KeyWordVO keyWordVO = null;
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT);
		){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				keyWordVO = new KeyWordVO();
				keyWordVO.setKwid(rs.getInt("kwid"));
				keyWordVO.setKwName(rs.getString("kwName"));
				keyWordVO.setKwContext(rs.getString("kwContext"));
				list.add(keyWordVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static void main(String[] args) {
		KeyWordJDBCDAO dao = new KeyWordJDBCDAO();
		
//		新增
		KeyWordVO vo = new KeyWordVO();
		vo.setKwName("HI");
		vo.setKwContext("asdasdasd");
		dao.insert(vo);		
		
//		修改
		KeyWordVO vo2 = new KeyWordVO();
		vo2.setKwName("HI");
		vo2.setKwContext("asdasdasd");
		vo2.setKwid(3);
		dao.update(vo2);		
		
//		查一筆資料
		KeyWordVO vo3 = dao.findByPrimaryKey(3);
		System.out.print(vo3.getKwid() + ",");
		System.out.print(vo3.getKwName() + ",");
		System.out.print(vo3.getKwContext() + ",");
		System.out.println();		
		
		// 查詢全部
		List<KeyWordVO> list = dao.getAll();
		for (KeyWordVO aEmp : list) {
			System.out.print(aEmp.getKwid() + ",");
			System.out.print(aEmp.getKwName() + ",");
			System.out.print(aEmp.getKwContext() + ",");
			System.out.println();
		}		
		
//		刪除 
//		dao.delete(9);
		
		
		
		
		
		
		
		
		
		
	}
}
