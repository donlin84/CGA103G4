package com.productPicture.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.productPicture.model.ProductpicService;
import com.productPicture.model.ProductpicVO;


@WebServlet("/back-end/product/showPicsByPicNo.do")
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 5 * 1024 * 1024, maxRequestSize = 5 * 5 * 1024 * 1024)

public class ShowPicByPicNo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("image/gif");
		ServletOutputStream out = null;
		
		try
	    {
		out = response.getOutputStream();
		
		ProductpicService productpicService = new ProductpicService();
		Integer pdPicid = Integer.valueOf(request.getParameter("pdPicid"));

		ProductpicVO productpicVO =productpicService.onePicByPicNo(pdPicid);
		
		out.write(productpicVO.getPdPic());

	    } catch(Exception e)
			{
        e.printStackTrace();
			}
		out.close();
		
        }
    
}
		
	


