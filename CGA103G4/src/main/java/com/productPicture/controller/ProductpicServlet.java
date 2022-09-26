package com.productPicture.controller;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;



@WebServlet("/front-end/shop/Productpic.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)
public class ProductpicServlet extends HttpServlet {
	
	
	
	private static final long serialVersionUID = 1L;
	Connection con;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		
		
try {
			Statement stmt = con.createStatement();
			String pdid = req.getParameter("pdid").trim();
			ResultSet rs = stmt.executeQuery(
				"SELECT pdpic FROM productpic WHERE pdid = "+pdid);

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("pdpic"));//buffered 增加效能
				//可寫rs.getBinaryStream("image") 24行為緩衝區用多型接資料;
				
				//java9新方法, 不適合存取大檔案(GB+)
				byte[] buf = in.readAllBytes();
				out.write(buf);
				
//				byte[] buf = new byte[4 * 1024]; // 4K buffer
//				int len;
//				while ((len = in.read(buf)) != -1) {
//					out.write(buf, 0, len);
//				}
				in.close();
				
			} else {
				//res.sendError(HttpServletResponse.SC_NOT_FOUND);
				//NoData/none2.jpg
				//NoData/null.jpg
				InputStream in = getServletContext().getResourceAsStream("/NoData/none2.jpg");
				byte[] b = new byte [in.available()];
				in.read(b);
				out.write(b);
				in.close();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			//System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/NoData/null.jpg");
			byte[] b = new byte [in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	}
	
	public void init() throws ServletException {
		try {
			Context ctx = new javax.naming.InitialContext();
			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Cga103G4");
			con = ds.getConnection();
		} catch (NamingException e) {
		
			e.printStackTrace();
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
	}

	public void destroy() {
		try {
			if (con != null) con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
	
