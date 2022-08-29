package com.util;

import static com.util.common_hung.*;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.DataSource;

//@WebServlet("/DBGifReader")
public class showIdCardBackPicture extends HttpServlet {

//	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String driver = "com.mysql.cj.jdbc.Driver";
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
			Statement stmt = conn.createStatement();
			String chefid = req.getParameter("chefid").trim();
			ResultSet rs = stmt.executeQuery("SELECT idCardBack FROM chef WHERE chefid = " + chefid);

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("idCardBack"));
				byte[] buf = in.readAllBytes();
				out.write(buf);
//				byte[] buf = new byte[4 * 1024]; // 4K buffer
//				int len;
//				while ((len = in.read(buf)) != -1) {
//					out.write(buf, 0, len);
//				}
				in.close();
			} else {
//				res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = getServletContext().getResourceAsStream("/back-end/chef/images/no.png");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/back-end/chef/images/no.png");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	}

//	public void init() throws ServletException {
//		try {
//			Context ctx = new javax.naming.InitialContext();
//			DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/David");
//			con = ds.getConnection();
//		} catch (NamingException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void destroy() {
//		try {
//			if (con != null)
//				con.close();
//		} catch (SQLException e) {
//			System.out.println(e);
//		}
//	}

}