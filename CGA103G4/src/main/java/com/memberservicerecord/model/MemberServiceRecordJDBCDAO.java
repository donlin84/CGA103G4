package com.memberservicerecord.model;

import static com.util.Common.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.keyword.model.KeyWordVO;


public class MemberServiceRecordJDBCDAO implements MemberServiceRecordDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";

	private static final String INSERT_STMT = 
		"INSERT INTO MemberServiceRecord (empid,memid,msrText,direction) VALUES (?,?,?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT msrid,empid,memid,msrText,msrTime,direction FROM MemberServiceRecord order by msrid";
	private static final String GET_ONE_STMT = 
		"SELECT msrid,empid,memid,msrText,msrTime,direction FROM MemberServiceRecord  where msrid = ?";
	private static final String DELETE = 
		"DELETE FROM MemberServiceRecord where msrid = ?";
	private static final String UPDATE = 
		"UPDATE MemberServiceRecord set empid=?,memid=?,msrText=?,direction=? where msrid = ?";
	
	
	@Override
	public void insert(MemberServiceRecordVO memberServiceRecordVO) {
		try (
				Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(INSERT_STMT);
			){
				pstmt.setInt(1, memberServiceRecordVO.getEmpid());
				pstmt.setInt(2, memberServiceRecordVO.getMemid());
				pstmt.setString(3, memberServiceRecordVO.getMsrText());
				pstmt.setInt(4, memberServiceRecordVO.getDirection());

				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	@Override
	public void update(MemberServiceRecordVO memberServiceRecordVO) {
		try (
				Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(UPDATE);
			){
				pstmt.setInt(1, memberServiceRecordVO.getEmpid());
				pstmt.setInt(2, memberServiceRecordVO.getMemid());
				pstmt.setString(3, memberServiceRecordVO.getMsrText());
				pstmt.setInt(4, memberServiceRecordVO.getDirection());
				pstmt.setInt(5,memberServiceRecordVO.getMsrid());
				
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	@Override
	public void delete(Integer msrid) {
		try (
				Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(DELETE);
			){
				pstmt.setInt(1, msrid);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

	@Override
	public MemberServiceRecordVO findByPrimaryKey(Integer msrid) {
		MemberServiceRecordVO memberServiceRecordVO = null;
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(GET_ONE_STMT);	
		){
			pstmt.setInt(1, msrid);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memberServiceRecordVO = new MemberServiceRecordVO();
				memberServiceRecordVO.setMsrid(rs.getInt("msrid"));
				memberServiceRecordVO.setEmpid(rs.getInt("empid"));
				memberServiceRecordVO.setMemid(rs.getInt("empid"));
				memberServiceRecordVO.setMsrText(rs.getString("msrText"));
				memberServiceRecordVO.setMsrTime(rs.getObject("msrTime",LocalDateTime.class));
				memberServiceRecordVO.setDirection(rs.getInt("direction"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memberServiceRecordVO;
	}

	@Override
	public List<MemberServiceRecordVO> getAll() {
		List<MemberServiceRecordVO> list = new ArrayList<MemberServiceRecordVO>();
		MemberServiceRecordVO memberServiceRecordVO = null;
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT);
		){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				memberServiceRecordVO = new MemberServiceRecordVO();
				memberServiceRecordVO.setMsrid(rs.getInt("msrid"));
				memberServiceRecordVO.setEmpid(rs.getInt("empid"));
				memberServiceRecordVO.setMemid(rs.getInt("empid"));
				memberServiceRecordVO.setMsrText(rs.getString("msrText"));
				memberServiceRecordVO.setMsrTime(rs.getObject("msrTime",LocalDateTime.class));
				memberServiceRecordVO.setDirection(rs.getInt("direction"));
				list.add(memberServiceRecordVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public static void main(String[] args) {
		MemberServiceRecordJDBCDAO dao = new MemberServiceRecordJDBCDAO();
//		新增
		MemberServiceRecordVO vo = new MemberServiceRecordVO();
		vo.setEmpid(101);
		vo.setMemid(201);
		vo.setMsrText("");
		vo.setDirection(1);
		dao.insert(vo);			
//		修改
		MemberServiceRecordVO vo2 = new MemberServiceRecordVO();
		vo2.setEmpid(101);
		vo2.setMemid(201);
		vo2.setMsrText(null);
		vo2.setDirection(1);
		vo2.setMsrid(3);
		dao.update(vo2);
//		查一筆
		MemberServiceRecordVO vo3 = dao.findByPrimaryKey(4);
		System.out.print(vo3.getMsrid() + ",");
		System.out.print(vo3.getEmpid() + ",");
		System.out.print(vo3.getMemid() + ",");
		System.out.print(vo3.getMsrText()+ ",");
		System.out.print(vo3.getMsrTime()+ ",");
		System.out.print(vo3.getDirection()+ ",");
		System.out.println();		
				
		// 查詢全部
		List<MemberServiceRecordVO> list = dao.getAll();
		for (MemberServiceRecordVO aEmp : list) {
			System.out.print(aEmp.getMsrid() + ",");
			System.out.print(aEmp.getEmpid() + ",");
			System.out.print(aEmp.getMemid() + ",");
			System.out.print(aEmp.getMsrText()+ ",");
			System.out.print(aEmp.getMsrTime()+ ",");
			System.out.print(aEmp.getDirection()+ ",");
			System.out.println();
		}		
			
				
		
		
//		刪除
//		dao.delete(16);
		
	}
}
