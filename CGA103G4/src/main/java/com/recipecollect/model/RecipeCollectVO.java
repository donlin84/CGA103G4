package com.recipecollect.model;

import java.io.Serializable;

public class RecipeCollectVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer reid;
	private Integer memid;
	
	public RecipeCollectVO() {
		
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
	
	
}
