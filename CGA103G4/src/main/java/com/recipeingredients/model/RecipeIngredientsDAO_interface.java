package com.recipeingredients.model;

import java.util.List;

public interface RecipeIngredientsDAO_interface {
	public void insert(RecipeIngredientsVO recipeIngredientsVO);
	public void update(RecipeIngredientsVO recipeIngredientsVO);
	public List<RecipeIngredientsVO> selectAll();
	public void delete(Integer reid, Integer pdid);
	public RecipeIngredientsVO findByPrimaryKey(Integer reid, Integer pdid);
}
