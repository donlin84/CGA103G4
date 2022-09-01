package com.fraritclegood.model;

import static com.util.Common.PASSWORD;
import static com.util.Common.URL;
import static com.util.Common.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.fr.model.ForumVO;
import com.frarticle.model.ArticleVO;

public class ArticleGoodJDBCDAO implements ArticleGoodDAO_interface {
	private static final String INSERT_SQL = "INSERT INTO articlegood(memid,atcid) VALUES (?,?);";
	private static final String DELETE_SQL = "DELETE FROM articlegood where memid = ?";
	private static final String GET_ONE_SQL = "SELECT memid,atcid FROM articlegood where atcid = ?;";

	@Override
	public void insert(ArticleGoodVO articleGoodVO) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {
			ps.setInt(1, articleGoodVO.getMemid());
			ps.setInt(2, articleGoodVO.getAtcid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	

	@Override
	public void delete(Integer memid) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
			ps.setInt(1, memid);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArticleGoodVO findByPrimaryKey(Integer atcid) {
		ArticleGoodVO articleGoodVO = null;
		ResultSet rs = null;
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(GET_ONE_SQL)) {
			ps.setInt(1, atcid);
			rs = ps.executeQuery();
			while (rs.next()) {
				articleGoodVO = new ArticleGoodVO();
				articleGoodVO.setMemid(rs.getInt("memid"));
				articleGoodVO.setAtcid(rs.getInt("atcid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleGoodVO;
	}

}
