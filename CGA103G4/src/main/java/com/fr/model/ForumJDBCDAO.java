package com.fr.model;

import static com.util.common.PASSWORD;
import static com.util.common.URL;
import static com.util.common.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ForumJDBCDAO implements ForumDaoImpl {
	private static final String INSERT_SQL = "INSERT INTO Forum(frName) VALUES (?);";
	private static final String UPDATE_SQL = "UPDATE Forum set frName=? where frid = ?;";
	private static final String DELETE_SQL = "DELETE FROM Forum where frid = ?";
	private static final String GET_ONE_SQL = "SELECT frid,frName FROM Forum where frid = ?;";
	private static final String GET_ALL_SQL = "SELECT frid,frName FROM Forum order by frid;";

	@Override
	public void insert(ForumVO forumVO) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {
			ps.setString(1, forumVO.getFrName());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(ForumVO forumVO) {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(UPDATE_SQL)) {
			ps.setString(1, forumVO.getFrName());
			ps.setInt(2, forumVO.getFrid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Integer frid) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
			ps.setInt(1, frid);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ForumVO findByPrimaryKey(Integer frid) {
		ForumVO forumVO = null;
		ResultSet rs = null;
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(GET_ONE_SQL)) {
			ps.setInt(1, frid);
			rs = ps.executeQuery();
			while (rs.next()) {
				forumVO = new ForumVO();
				forumVO.setFrid(rs.getInt("frid"));
				forumVO.setFrName(rs.getString("frName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return forumVO;
	}

	@Override
	public List<ForumVO> getAll() {
		List<ForumVO> list = new ArrayList<ForumVO>();
		ForumVO forumVO = null;
		ResultSet rs = null;
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(GET_ALL_SQL)) {

			rs = ps.executeQuery();
			while (rs.next()) {
				forumVO = new ForumVO();
				forumVO.setFrid(rs.getInt("funcid"));
				forumVO.setFrName(rs.getString("funcName"));
				list.add(forumVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
