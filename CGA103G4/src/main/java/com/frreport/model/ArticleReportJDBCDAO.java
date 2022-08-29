package com.frreport.model;

import static com.util.Common_12.PASSWORD;
import static com.util.Common_12.URL;
import static com.util.Common_12.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ArticleReportJDBCDAO implements ArticleReportDAO_interface {
	private static final String INSERT_SQL = "INSERT INTO Report(atcid,memid,reportContent) VALUES (?,?,?);";
	private static final String UPDATE_SQL = "UPDATE Report set reportStatus=? where atcReportid = ?;";
	private static final String GET_ONE_SQL = "SELECT * FROM Report where atcid=?;";

	@Override
	public void insert(ArticleReportVO articleReportVO) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {
			ps.setInt(1, articleReportVO.getAtcid());
			ps.setInt(2, articleReportVO.getMemid());
			ps.setString(3, articleReportVO.getReportContent());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override // 修改檢舉狀態(0:待審核 1:已移除 2:取消檢舉)
	public void update(ArticleReportVO articleReportVO) {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(UPDATE_SQL)) {
			ps.setInt(1, articleReportVO.getReportStatus());
			ps.setInt(2, articleReportVO.getAtcReportid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArticleReportVO findByPrimaryKey(Integer atcReportid) {
		ArticleReportVO articleReportVO = null;
		ResultSet rs = null;
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(GET_ONE_SQL)) {
			ps.setInt(1, atcReportid);
			rs = ps.executeQuery();
			while (rs.next()) {
				articleReportVO = new ArticleReportVO();
				articleReportVO.setAtcReportid(rs.getInt("atcReportid"));
				articleReportVO.setAtcid(rs.getInt("atcid"));
				articleReportVO.setMemid(rs.getInt("memid"));
				articleReportVO.setReportContent("reportContent");
				articleReportVO.setReportStatus(rs.getInt("reportStatus"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articleReportVO;
	}

}
