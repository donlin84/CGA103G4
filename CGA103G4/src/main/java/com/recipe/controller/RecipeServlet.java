package com.recipe.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.recipe.model.RecipeService;
import com.recipe.model.RecipeVO;
@WebServlet("/back-end/recipe/Recipe.do")
public class RecipeServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("reid");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入食譜編號");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/recipe/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷 
			}

			Integer reid = null;
			try {
				reid = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("訂單編號格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/recipe/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			RecipeService resv = new RecipeService();
			RecipeVO recipeVO = resv.getOne(reid);
			if (recipeVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/recipe/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("recipeVO", recipeVO); // 資料庫取出的empVO物件,存入req
			String url = "/back-end/recipe/listOneRecipe.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer reid = Integer.valueOf(req.getParameter("reid"));

			/*************************** 2.開始查詢資料 ****************************************/
			RecipeService resv = new RecipeService();
			RecipeVO recipeVO = resv.getOne(reid);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("recipeVO", recipeVO); 
			String url = "/back-end/recipe/update_recipe_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_emp_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer reid = Integer.valueOf(req.getParameter("reid").trim());

			Integer memid = Integer.valueOf(req.getParameter("memid").trim());
			
			String reTitle = req.getParameter("reTitle");
			if(reTitle.trim().length() > 20) {
				errorMsgs.add("食譜標題請勿超過20個字");
			}
			
			String reContext = req.getParameter("reContext");
			if(reContext.trim().length() > 3000) {
				errorMsgs.add("食譜內請勿超過3000個字");
			}
			
			LocalDateTime reSTime = LocalDateTime.parse(req.getParameter("reSTime"));
			
			LocalDateTime reLTime = LocalDateTime.parse(req.getParameter("reLTime"));

			RecipeVO recipeVO = new RecipeVO();
			recipeVO.setReid(reid);
			recipeVO.setMemid(memid);
			recipeVO.setReTitle(reTitle);
			recipeVO.setReContext(reContext);
			recipeVO.setReSTime(reSTime);
			recipeVO.setReLTime(reLTime);
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("recipeVO", recipeVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/recipe/update_recipe_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			RecipeService ordSvc = new RecipeService();
			recipeVO = ordSvc.updateRecipe(memid, reTitle, reContext, reid);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("recipeVO", recipeVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url ="Recipe.do?action=getOne_For_Display";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
//			Integer ordid = Integer.valueOf(req.getParameter("ordid").trim());

//			String memid = req.getParameter("memid");
			Integer memid = 0;
			String memidStr = req.getParameter("memid");
			String memidReg = "^[(0-9)]{3}$";
			
			if(!memidStr.matches(memidReg)) {
				errorMsgs.add("會員編號: 只能是數字 , 且長度必需為3");
			} else if(memidStr == null || memidStr.trim().length() == 0) {
				errorMsgs.add("會員編號: 請勿空白");
				memidStr = "";
			} else {
				memid = Integer.valueOf(memidStr);
			}
			
			String reTitle = req.getParameter("reTitle");
			if(reTitle.trim().length() > 20) {
				errorMsgs.add("食譜標題請勿超過20個字");
			}
			
			String reContext = req.getParameter("reContext");
			if(reContext.trim().length() > 3000) {
				errorMsgs.add("食譜內請勿超過3000個字");
			}

			RecipeVO recipeVO = new RecipeVO();
			
			recipeVO.setMemid(memid);
			recipeVO.setReTitle(reTitle);
			recipeVO.setReContext(reContext);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("recipeVO", recipeVO); // 含有輸入格式錯誤的empVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/recipe/addOrders.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始新增資料 ***************************************/
			RecipeService ordSvc = new RecipeService();
			recipeVO = ordSvc.addRecipe(memid, reTitle, reContext);
			
			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/recipe/listAllRecipe.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // �s�W���\�����listAllEmp.jsp
			successView.forward(req, res);
		}

	}
}
