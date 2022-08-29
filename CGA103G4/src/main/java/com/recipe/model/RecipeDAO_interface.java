package com.recipe.model;

import java.util.List;

public interface RecipeDAO_interface {
	public void insert(RecipeVO recipeVO);
	public void update(RecipeVO recipeVO);
	public List<RecipeVO> selectAll();
	public RecipeVO findByPrimaryKey(Integer reid);
}
