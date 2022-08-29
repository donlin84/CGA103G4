package com.frarticlesave.model;

import static com.util.Common_12.PASSWORD;
import static com.util.Common_12.URL;
import static com.util.Common_12.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.frarticle.model.ArticleVO;

public class ArticleSaveJDBCDAO implements ArticleSaveDAO_interface {

	private static final String INSERT_SQL = "INSERT INTO articlesave(memid,atcid) VALUES (?,?);";
	private static final String DELETE_SQL = "DELETE FROM articlesave where atcid = ?";
	private static final String GET_ONE_SQL = "SELECT * FROM Article where memid=?;";

	@Override
	public void insert(ArticleSaveVO articleSaveVO) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {
			ps.setInt(1, articleSaveVO.getMemid());
			ps.setInt(2, articleSaveVO.getAtcid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer atcid) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
			ps.setInt(1, atcid);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArticleSaveVO findByPrimaryKey(Integer atcid) {
		ArticleSaveVO articleSaveVO = null;
		ResultSet rs = null;
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(GET_ONE_SQL)) {
			ps.setInt(1, atcid);
			rs = ps.executeQuery();
			while (rs.next()) {
				articleSaveVO = new ArticleSaveVO();
				articleSaveVO.setMemid(rs.getInt("memid"));
				articleSaveVO.setAtcid(rs.getInt("atcid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return articleSaveVO;
	}
}
