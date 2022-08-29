package com.frarticle.model;

import static com.util.Common_12.PASSWORD;
import static com.util.Common_12.URL;
import static com.util.Common_12.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ArticleJDBCDAO implements ArticleDAO_interface {
	private static final String INSERT_SQL = "INSERT INTO Article(frid,memid,atcTitle,atcContent) VALUES (?,?,?,?);";
	private static final String UPDATE_SQL = "UPDATE Article set atcContent=? where atcid = ?;";
	private static final String GET_ONE_SQL = "SELECT * FROM Article where atcid=?;";
	private static final String GET_ALL_SQL = "SELECT * FROM Article order by atcid;";

	@Override
	public void insert(ArticleVO articleVO) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {
			ps.setInt(1, articleVO.getFrid());
			ps.setInt(2, articleVO.getMemid());
			ps.setString(3, articleVO.getAtcTitle());
			ps.setString(4, articleVO.getAtcContent());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(ArticleVO articleVO) {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(UPDATE_SQL)) {
			ps.setString(1, articleVO.getAtcContent());
			ps.setInt(2, articleVO.getAtcid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArticleVO findByPrimaryKey(Integer atcid) {
		ArticleVO articleVO = null;
		ResultSet rs = null;
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(GET_ONE_SQL)) {
			ps.setInt(1, atcid);
			rs = ps.executeQuery();
			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setAtcid(rs.getInt("atcid"));
				articleVO.setFrid(rs.getInt("frid"));
				articleVO.setMemid(rs.getInt("memid"));
				articleVO.setAtcTitle(rs.getString("atcTitle"));
				articleVO.setAtcContent(rs.getString("atcContent"));
				articleVO.setAtcStime(rs.getTimestamp("atcStime"));
				articleVO.setAtcLtime(rs.getTimestamp("atcLtime"));
				articleVO.setAtcStatus(rs.getInt("atcStatus"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleVO;
	}

	@Override
	public List<ArticleVO> getAll() {
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		ArticleVO articleVO = null;
		ResultSet rs = null;
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(GET_ALL_SQL)) {

			rs = ps.executeQuery();
			while (rs.next()) {
				articleVO = new ArticleVO();
				articleVO.setAtcid(rs.getInt("atcid"));
				articleVO.setFrid(rs.getInt("frid"));
				articleVO.setMemid(rs.getInt("memid"));
				articleVO.setAtcTitle(rs.getString("atcTitle"));
				articleVO.setAtcContent(rs.getString("atcContent"));
				articleVO.setAtcStime(rs.getTimestamp("atcStime"));
				articleVO.setAtcLtime(rs.getTimestamp("atcLtime"));
				articleVO.setAtcStatus(rs.getInt("atcStatus"));
				list.add(articleVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
