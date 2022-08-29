package com.recipeingredients.model;

import java.util.List;

public class RecipeIngredientsService {
	private RecipeIngredientsDAO_interface dao;
	
	public RecipeIngredientsService() {
		dao = new RecipeIngredientsDAO();
	}
	
	public RecipeIngredientsVO addRecipeIngredients(Integer reid, Integer pdid) {
		RecipeIngredientsVO recipeIngredientsVO = new RecipeIngredientsVO();
		
		recipeIngredientsVO.setReid(reid);
		recipeIngredientsVO.setPdid(pdid);
		
		dao.insert(recipeIngredientsVO);
		
		return recipeIngredientsVO;
	}
	
	public RecipeIngredientsVO updateRecipeIngredients(Integer pdid, Integer reid) {
		RecipeIngredientsVO recipeIngredientsVO = new RecipeIngredientsVO();
		
		recipeIngredientsVO.setPdid(pdid);
		recipeIngredientsVO.setReid(reid);
		
		dao.update(recipeIngredientsVO);
		
		return recipeIngredientsVO;
	}
	
	public RecipeIngredientsVO getOne(Integer reid, Integer pdid) {
		return dao.findByPrimaryKey(reid, pdid);
	}
	
	public List<RecipeIngredientsVO> getAll() {
		return dao.selectAll();
	}
	
	public void delete(Integer reid, Integer pdid) {
		dao.delete(reid, pdid);
	}
}
