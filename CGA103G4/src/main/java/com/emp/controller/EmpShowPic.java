package com.emp.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.mysql.cj.xdevapi.Result;

@WebServlet("/EmpShowPic")
public class EmpShowPic extends HttpServlet {
	Connection con;
	public void init() {
		try {
			DataSource ds;
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/Cga103G4");
			con=ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		res.setContentType("image/gif");
		ServletOutputStream out = res.getOutputStream();
		
		try {
			String empid = req.getParameter("empid");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select empPicture FROM EMPLOYEE where empid = " + empid);
			
		if(rs.next()) {
			BufferedInputStream in = new BufferedInputStream(rs.getBinaryStream("empPicture"));
			byte[] buf = in.readAllBytes();
			out.write(buf);
			in.close();
		}else {
			InputStream in = getServletContext().getResourceAsStream("/back-end/emp/images/noImage.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
		rs.close();
		st.close();
		} catch (Exception e) {
			InputStream in = getServletContext().getResourceAsStream("/back-end/emp/images/noImage.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	}
		
		public void destroy() {
			try {
				if(con !=null) {
					con.close();
				}
			} catch ( SQLException e) {
				e.printStackTrace();
			}
		}
		

		
	

}
