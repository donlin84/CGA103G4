package com.recipe.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class RecipeVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer reid;
	private Integer memid;
	private String reContext;
	private LocalDateTime reSTime;
	private LocalDateTime reLTime;

	public RecipeVO() {
		
	}
	public LocalDateTime getReSTime() {
		return reSTime;
	}
	public void setReSTime(LocalDateTime reSTime) {
		this.reSTime = reSTime;
	}
	public LocalDateTime getReLTime() {
		return reLTime;
	}
	public void setReLTime(LocalDateTime reLTime) {
		this.reLTime = reLTime;
	}
	public Integer getReid() {
		return reid;
	}
	public void setReid(Integer reid) {
		this.reid = reid;
	}
	public Integer getMemid() {
		return memid;
	}
	public void setMemid(Integer memid) {
		this.memid = memid;
	}
	public String getReContext() {
		return reContext;
	}
	public void setReContext(String reContext) {
		this.reContext = reContext;
	}
	
	
}
