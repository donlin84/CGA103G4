package com.recipe.model;

import java.util.List;

public class RecipeService {
		private RecipeDAO_interface dao;
		
		public RecipeService() {
			dao = new RecipeDAO();
		}
		
		public RecipeVO addRecipe(Integer memid, String reContext) {
			RecipeVO recipeVO = new RecipeVO();
			
			recipeVO.setMemid(memid);
			recipeVO.setReContext(reContext);
			
			dao.insert(recipeVO);
			
			return recipeVO;
		}
		
		public RecipeVO updateRecipe(Integer memid, String reContext, Integer reid) {
			RecipeVO recipeVO = new RecipeVO();
			
			recipeVO.setMemid(memid);
			recipeVO.setReContext(reContext);
			recipeVO.setReid(reid);
			
			dao.update(recipeVO);
			
			return recipeVO;
		}
		
		public RecipeVO getOne(Integer reid) {
			return dao.findByPrimaryKey(reid);
		}
		
		public List<RecipeVO> getAll() {
			return dao.selectAll();
		}
}
