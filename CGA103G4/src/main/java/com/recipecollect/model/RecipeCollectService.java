package com.recipecollect.model;

import java.util.List;

public class RecipeCollectService {
	private RecipeCollectDAO_interface dao;
	
	public RecipeCollectService() {
		dao = new RecipeCollectDAO();
	}
	
	public RecipeCollectVO addRecipeCollect(Integer reid, Integer memid) {
		RecipeCollectVO recipeCollectVO = new RecipeCollectVO();
		
		recipeCollectVO.setReid(reid);
		recipeCollectVO.setMemid(memid);
		
		dao.insert(recipeCollectVO);
		
		return recipeCollectVO;
	}
	
	public RecipeCollectVO updateRecipeCollect(Integer reid, Integer memid) {
		RecipeCollectVO recipeCollectVO = new RecipeCollectVO();
		
		recipeCollectVO.setReid(reid);
		recipeCollectVO.setMemid(memid);
		
		dao.update(recipeCollectVO);
		
		return recipeCollectVO;
	}
	
	public RecipeCollectVO getOne(Integer reid, Integer memid) {
		return dao.findByPrimaryKey(reid, memid);
	}
	
	public List<RecipeCollectVO> getAll () {
		return dao.selectAll();
	}
	
	public void deleteRecipeCollect (Integer reid, Integer memid) {
		dao.delete(reid, memid);
	}
}
