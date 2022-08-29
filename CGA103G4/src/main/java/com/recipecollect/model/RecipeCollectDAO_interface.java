package com.recipecollect.model;

import java.util.List;

public interface RecipeCollectDAO_interface {
	public void insert(RecipeCollectVO recipeCollectVO);
	public void update(RecipeCollectVO recipeCollectVO);
	public void delete(Integer reid, Integer memid);
	public List<RecipeCollectVO> selectAll();
	public RecipeCollectVO findByPrimaryKey(Integer reid, Integer memid);
}
