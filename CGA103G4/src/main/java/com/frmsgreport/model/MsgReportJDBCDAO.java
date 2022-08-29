package com.frmsgreport.model;

import static com.util.common.PASSWORD;
import static com.util.common.URL;
import static com.util.common.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.frarticlemsg.model.ArticleMsgVO;

public class MsgReportJDBCDAO implements MsgReportDAO_interface {
	private static final String INSERT_SQL = "INSERT INTO msgreport(atcMsgid,memid,msgReportContent) VALUES (?,?,?);";
	private static final String UPDATE_SQL = "UPDATE msgreport set msgReportStatus=? where msgReportid = ?;";
	private static final String GET_ONE_SQL = "SELECT * FROM msgreport where msgReportid=?;";

	@Override
	public void insert(MsgReportVO msgReportVO) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {
			ps.setInt(1,msgReportVO.getAtcMsgid());
			ps.setInt(2, msgReportVO.getMemid());
			ps.setString(3,msgReportVO.getMsgReportContent());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(MsgReportVO msgReportVO) {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(UPDATE_SQL)) {
			ps.setInt(1, msgReportVO.getMsgReportStatus());
			ps.setInt(2, msgReportVO.getMsgReportid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public MsgReportVO findByPrimaryKey(Integer msgReportid) {
		MsgReportVO msgReportVO = null;
		ResultSet rs = null;
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(GET_ONE_SQL)) {
			ps.setInt(1, msgReportid);
			rs = ps.executeQuery();
			while (rs.next()) {
				msgReportVO = new MsgReportVO();
				msgReportVO.setMsgReportid(rs.getInt("msgReportid"));
				msgReportVO.setAtcMsgid(rs.getInt("atcMsgid"));
				msgReportVO.setMemid(rs.getInt("memid"));
				msgReportVO.setMsgReportStatus(rs.getInt("msgReportStatus"));
				msgReportVO.setMsgReportTime(rs.getTimestamp("msgReportTime"));
				msgReportVO.setMsgReportContent(rs.getString("msgReportContent"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return msgReportVO;
	}

}
