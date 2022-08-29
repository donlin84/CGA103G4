package com.memberservicepicture.model;

import static com.util.Common_15.PASSWORD;
import static com.util.Common_15.URL;
import static com.util.Common_15.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.memberservicerecord.model.MemberServiceRecordJDBCDAO;
import com.memberservicerecord.model.MemberServiceRecordVO;

public class MemberServicePictureJDBCDAO implements MemberServicePictureDAO_interface{
	String driver = "com.mysql.cj.jdbc.Driver";

	private static final String INSERT_STMT = 
		"INSERT INTO MemberServicePicture (msrid,mspPic) VALUES (?,?)";
	private static final String GET_ALL_STMT = 
		"SELECT mspPicid,msrid,mspPic FROM MemberServicePicture order by mspPicid";
	private static final String GET_ONE_STMT = 
		"SELECT mspPicid,msrid,mspPic FROM MemberServicePicture  where mspPicid = ?";
	private static final String DELETE = 
		"DELETE FROM MemberServicePicture where mspPicid = ?";
	private static final String UPDATE = 
		"UPDATE MemberServicePicture set msrid=?,mspPic=? where mspPicid = ?";
	@Override
	public void insert(MemberServicePictureVO memberServicePictureVO) {
		try (
				Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(INSERT_STMT);
			){
				pstmt.setInt(1, memberServicePictureVO.getMsrid());
				pstmt.setBytes(2, memberServicePictureVO.getMspPic());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}		
	}
	@Override
	public void update(MemberServicePictureVO memberServicePictureVO) {
		try (
				Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(UPDATE);
			){
				pstmt.setInt(1,memberServicePictureVO.getMsrid());
				pstmt.setBytes(2, memberServicePictureVO.getMspPic());
				pstmt.setInt(3, memberServicePictureVO.getMspPicid());
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	@Override
	public void delete(Integer mspPicid) {
		try (
				Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(DELETE);
			){
				pstmt.setInt(1, mspPicid);
				pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	@Override
	public MemberServicePictureVO findByPrimaryKey(Integer mspPicid) {
		MemberServicePictureVO memberServicePictureVO = null;
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(GET_ONE_STMT);	
		){
			pstmt.setInt(1, mspPicid);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				memberServicePictureVO = new MemberServicePictureVO();
				memberServicePictureVO.setMspPicid(rs.getInt("mspPicid"));
				memberServicePictureVO.setMsrid(rs.getInt("msrid"));
				memberServicePictureVO.setMspPic(rs.getBytes("mspPic"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memberServicePictureVO;		
	}
	@Override
	public List<MemberServicePictureVO> getAll() {
		List<MemberServicePictureVO> list = new ArrayList<MemberServicePictureVO>();
		MemberServicePictureVO memberServicePictureVO = null;
		try (
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(GET_ALL_STMT);
		){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				memberServicePictureVO = new MemberServicePictureVO();
				memberServicePictureVO.setMspPicid(rs.getInt("mspPicid"));
				memberServicePictureVO.setMsrid(rs.getInt("msrid"));
				memberServicePictureVO.setMspPic(rs.getBytes("mspPic"));
				list.add(memberServicePictureVO);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	public static void main(String[] args) {
		MemberServicePictureJDBCDAO dao = new MemberServicePictureJDBCDAO();
//		新增
		MemberServicePictureVO vo = new MemberServicePictureVO();
		vo.setMsrid(1);
		vo.setMspPic(null);
		dao.insert(vo);	
		
//		修改
		MemberServicePictureVO vo2 = new MemberServicePictureVO();
		vo2.setMsrid(1);
		vo2.setMspPic(new byte[2]);
		vo2.setMspPicid(2);
		dao.update(vo2);
//		查一筆
		MemberServicePictureVO vo3 = dao.findByPrimaryKey(1);
		System.out.print(vo3.getMspPicid() + ",");
		System.out.print(vo3.getMsrid() + ",");
		System.out.print(vo3.getMspPic() + ",");
		System.out.println();		
				
		// 查詢全部
		List<MemberServicePictureVO> list = dao.getAll();
		for (MemberServicePictureVO aEmp : list) {
			System.out.print(aEmp.getMspPicid() + ",");
			System.out.print(aEmp.getMsrid() + ",");
			System.out.print(aEmp.getMspPic() + ",");
			System.out.println();
		}		
				
//		刪除
//		dao.delete(3);		
	}
}
