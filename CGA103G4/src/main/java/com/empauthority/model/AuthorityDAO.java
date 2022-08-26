package com.empauthority.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class AuthorityDAO {
	private static final String INSERT_SQL = "INSERT INTO Authority(empid,funcid) VALUES (?,?);";
	private static final String GET_ALL_SQL = "SELECT empid,funcid FROM Authority order by empid;";
	private static final String GET_ONE_SQL = "SELECT empid,funcid FROM Authority where empid = ?;";
	private static final String DELETE_SQL = "DELETE FROM Authority where (empid = ?) AND (funcid = ?)";

	private static DataSource ds = null;
	static {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Cga103G4");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public void insert(AuthorityVO authorityVO) {
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(INSERT_SQL)) {
			ps.setInt(1, authorityVO.getEmpid());
			ps.setInt(2, authorityVO.getFuncid());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete(Integer empid, Integer funcid) {
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
			ps.setInt(1, empid);
			ps.setInt(2, funcid);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public AuthorityVO findByPrimaryKey(Integer empid) {
		AuthorityVO authorityVO = null;
		ResultSet rs = null;
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(GET_ONE_SQL)) {
			ps.setInt(1, empid);
			rs = ps.executeQuery();
			while (rs.next()) {
				authorityVO = new AuthorityVO();
				authorityVO.setEmpid(rs.getInt("empid"));
				authorityVO.setFuncid(rs.getInt("funcid"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return authorityVO;
	}

	public List<AuthorityVO> getAll() {
		List<AuthorityVO> list = new ArrayList<AuthorityVO>();
		AuthorityVO authorityVO = null;
		ResultSet rs = null;
		try (Connection conn = ds.getConnection(); PreparedStatement ps = conn.prepareStatement(GET_ALL_SQL)) {

			rs = ps.executeQuery();
			while (rs.next()) {
				authorityVO = new AuthorityVO();
				authorityVO.setEmpid(rs.getInt("empid"));
				authorityVO.setFuncid(rs.getInt("funcid"));
				list.add(authorityVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
