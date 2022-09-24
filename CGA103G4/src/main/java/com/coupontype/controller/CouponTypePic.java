package com.coupontype.controller;

import java.io.*;
import java.sql.*;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.sql.DataSource;
@WebServlet("/CouponTypePic")
public class CouponTypePic extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();

		try {
			Statement stmt = con.createStatement();
			String id = req.getParameter("cpTpid").trim();
			ResultSet rs = stmt.executeQuery("SELECT cpPic FROM coupontype WHERE cpTpid =" + id);
//				 + req.getParameter("PID")

			if (rs.next()) {
				BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("cpPic"));

//					新方法(檔案小時,很方便)
				byte[] buf = in.readAllBytes();
				out.write(buf);

//					舊方法(但檔案大時,建議還是水桶一桶一桶裝)
//					byte[] buf = new byte[4 * 1024]; // 4K buffer
//					int len;
//					while ((len = in.read(buf)) != -1) {
//						out.write(buf, 0, len);
//					}

				in.close();

			} else {
//					res.sendError(HttpServletResponse.SC_NOT_FOUND);
				InputStream in = getServletContext().getResourceAsStream("/images/NoData_22/none2.jpg");
				byte[] b = new byte[in.available()];
				in.read(b);
				out.write(b);
				in.close();
			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
//				System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/images/NoData_22/null.jpg");
			byte[] b = new byte[in.available()];
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

//			try {
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db01?serverTimezone=Asia/Taipei", "root", "sa4850869");
//			} catch (ClassNotFoundException e) {
//				throw new UnavailableException("Couldn't load JdbcOdbcDriver");
//			} catch (SQLException e) {
//				throw new UnavailableException("Couldn't get db connection");
//			}

	}

	public void destroy() {
		try {
			if (con != null)
				con.close();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
