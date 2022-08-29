package com.ClassIfm.controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ClassPicture.model.ClassPictureJDBCDAO;
import com.ClassPicture.model.ClassPictureVO;

/**
 * Servlet implementation class ClassIfmPic
 */
@WebServlet("/ClassIfmPic")
public class ClassIfmPic extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req,resp);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		resp.setContentType("image/gif");
		ServletOutputStream out = resp.getOutputStream();
		try {
			Integer id = Integer.valueOf(req.getParameter("id").trim());
			Integer page = Integer.valueOf(req.getParameter("page").trim());
			ClassPictureJDBCDAO classPictureJDBCDAO = new ClassPictureJDBCDAO();
			List<ClassPictureVO> list = classPictureJDBCDAO.findByPrimaryKey(id);
			System.out.println(list.size());
			System.out.println(list.get(1));
				if (list.get(page).getClaPic().length!=0) {
					BufferedInputStream in = new BufferedInputStream(new ByteArrayInputStream(list.get(page).getClaPic()));
					
					byte[] buf = in.readAllBytes();
					out.write(buf); 

					in.close();
				} else {
//					res.sendError(HttpServletResponse.SC_NOT_FOUND);
					InputStream in = getServletContext().getResourceAsStream("/backend/classifm/images/1062-5092x3395.jpg");
					byte[] b = new byte[in.available()];
					in.read(b);
					out.write(b);
					in.close();
				}
		} catch (Exception e) {
//			System.out.println(e);
			InputStream in = getServletContext().getResourceAsStream("/backend/classifm/images/ggg.jpg");
			byte[] b = new byte[in.available()];
			in.read(b);
			out.write(b);
			in.close();
		}
	}

}
