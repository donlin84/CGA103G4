package com.articlepicture.model;

import static com.util.common.PASSWORD;
import static com.util.common.URL;
import static com.util.common.USER;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ArticlePictureJDBC implements ArticlePictureDAO_inteface {

	private static final String INSERT_SQL = "INSERT INTO articlePicture(atcid,atcPic) VALUES (?,?);";
	private static final String UPDATE_SQL = "UPDATE articlePicture set atcpic where picid = ?;";
	private static final String DELETE_SQL = "DELETE FROM articlePicture where picid = ?";
	private static final String GET_ONE_SQL = "SELECT * FROM articlePicture where picid = ?;";

	@Override
	public void insert(ArticlePictureVO articlePictureVO) {
		String img = "C:\\CGA103_WebApp\\elicpse_WTP_workspace1\\CGA103G4\\src\\main\\webapp\\images\\tom.jpg";
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
			try (PreparedStatement ps = conn.prepareStatement(INSERT_SQL);
					FileInputStream in = new FileInputStream(img)) {
				ps.setInt(1, articlePictureVO.getAtcid());
				ps.setBinaryStream(2, in, in.available());
			} catch (Exception e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(ArticlePictureVO articlePictureVO) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer atcPicid) {
		try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = conn.prepareStatement(DELETE_SQL)) {
			ps.setInt(1, atcPicid);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ArticlePictureVO findByPrimaryKey(Integer atcPicid) {
		ArticlePictureVO articlePictureVO = null;
		ResultSet rs = null;
		try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement ps = connection.prepareStatement(GET_ONE_SQL)) {
			FileOutputStream out = new FileOutputStream("");
			ps.setInt(1, atcPicid);
			rs = ps.executeQuery();
			while (rs.next()) {
				byte[] img = rs.getBytes(3);
				articlePictureVO = new ArticlePictureVO();
				articlePictureVO.setAtcid(rs.getInt("atcid"));
				articlePictureVO.setPicid(rs.getInt("picid"));
				out.write(img);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return articlePictureVO;
	}

}
