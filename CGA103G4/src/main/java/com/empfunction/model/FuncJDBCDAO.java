package com.empfunction.model;

import static com.util.common.PASSWORD;
import static com.util.common.URL;
import static com.util.common.USER;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;;

public class FuncJDBCDAO implements FuncDaoImpl {
	private static final String INSERT_SQL = "INSERT INTO EmpFunction(funcName) VALUES (?);";
	private static final String GET_ALL_SQL = "SELECT funcid,funcName FROM EmpFunction order by funcid;";
	private static final String GET_ONE_SQL = "SELECT funcid,funcName FROM EmpFunction where funcid = ?;";
	private static final String UPDATE_SQL = "UPDATE EmpFunction set funcName=? where funcid = ?;";

	public void insert(FuncVO FuncVO) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {
			ps.setString(1, FuncVO.getFuncName());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void update(FuncVO FuncVO) {
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(UPDATE_SQL)) {
			ps.setString(1, FuncVO.getFuncName());
			ps.setInt(2, FuncVO.getFuncid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public FuncVO findByPrimaryKey(Integer funcid) {
		FuncVO funcVO = null;
		ResultSet rs = null;
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(GET_ONE_SQL)) {
			ps.setInt(1, funcid);
			rs = ps.executeQuery();
			while (rs.next()) {
				funcVO = new FuncVO();
				funcVO.setFuncid(rs.getInt("funcid"));
				funcVO.setFuncName(rs.getString("funcName"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return funcVO;
	}

	public List<FuncVO> getAll() {
		List<FuncVO> list = new ArrayList<FuncVO>();
		FuncVO funcVO = null;
		ResultSet rs = null;
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(GET_ALL_SQL)) {

			rs = ps.executeQuery();
			while (rs.next()) {
				funcVO = new FuncVO();
				funcVO.setFuncid(rs.getInt("funcid"));
				funcVO.setFuncName(rs.getString("funcName"));
				list.add(funcVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
