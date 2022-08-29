package com.recipeingredients.model;

import java.io.Serializable;

public class RecipeIngredientsVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer reid;
	private Integer pdid;
	
	public RecipeIngredientsVO() {
		
	}
	
	public Integer getReid() {
		return reid;
	}
	public void setReid(Integer reid) {
		this.reid = reid;
	}
	public Integer getPdid() {
		return pdid;
	}
	public void setPdid(Integer productid) {
		this.pdid = productid;
	}
	
	
}
