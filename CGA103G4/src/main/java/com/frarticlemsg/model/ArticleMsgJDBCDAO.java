package com.frarticlemsg.model;

import static com.util.Common.PASSWORD;
import static com.util.Common.URL;
import static com.util.Common.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ArticleMsgJDBCDAO implements ArticleMsgDAO_interface {
	private static final String INSERT_SQL = "INSERT INTO ArticleMsg(atcid,memid,msgContent) VALUES (?,?,?);";
	private static final String UPDATE_SQL = "UPDATE ArticleMsg set msgStatus=? where atcMsgid = ?;";
	private static final String GET_ONE_SQL = "SELECT * FROM ArticleMsg where atcMsgid=?;";
	private static final String GET_ALL_SQL = "SELECT * FROM ArticleMsg order by atcid;";

	@Override
	public void insert(ArticleMsgVO articleMsgVO) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {
			ps.setInt(1, articleMsgVO.getAtcid());
			ps.setInt(2, articleMsgVO.getMemid());
			ps.setString(3, articleMsgVO.getMsgContent());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 留言顯示、隱藏，(1:顯示 , 0:隱藏)
	public void update(ArticleMsgVO articleMsgVO) {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(UPDATE_SQL)) {
			ps.setInt(1, articleMsgVO.getMsgStatus());
			ps.setInt(2, articleMsgVO.getAtcMsgid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArticleMsgVO findByPrimaryKey(Integer atcMsgid) {
		ArticleMsgVO articleMsgVO = null;
		ResultSet rs = null;
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(GET_ONE_SQL)) {
			ps.setInt(1, atcMsgid);
			rs = ps.executeQuery();
			while (rs.next()) {
				articleMsgVO = new ArticleMsgVO();
				articleMsgVO.setAtcMsgid(rs.getInt("atcMsgid"));
				articleMsgVO.setAtcid(rs.getInt("atcid"));
				articleMsgVO.setMemid(rs.getInt("memid"));
				articleMsgVO.setMsgContent(rs.getString("msgContent"));
				articleMsgVO.setMsgTime(rs.getTimestamp("msgTime"));
				articleMsgVO.setMsgStatus(rs.getInt("msgStatus"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleMsgVO;
	}

	public List<ArticleMsgVO> getAll() {
		List<ArticleMsgVO> list = new ArrayList<ArticleMsgVO>();
		ArticleMsgVO articleMsgVO = null;
		ResultSet rs = null;
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(GET_ALL_SQL)) {

			rs = ps.executeQuery();
			while (rs.next()) {
				articleMsgVO = new ArticleMsgVO();
				articleMsgVO.setAtcMsgid(rs.getInt("atcMsgid"));
				articleMsgVO.setAtcid(rs.getInt("atcid"));
				articleMsgVO.setMemid(rs.getInt("memid"));
				articleMsgVO.setMsgContent(rs.getString("msgContent"));
				articleMsgVO.setMsgTime(rs.getTimestamp("msgTime"));
				articleMsgVO.setMsgStatus(rs.getInt("msgStatus"));
				list.add(articleMsgVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
