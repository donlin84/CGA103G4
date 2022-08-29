package com.favoriteProduct.model;


public class FavoriteProductVO implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer pdid;
	private Integer memid;
	
	public Integer getPdid() {
		return pdid;
	}
	public void setPdid(Integer pdid) {
		this.pdid = pdid;
	}
	public Integer getMemid() {
		return memid;
	}
	public void setMemid(Integer memid) {
		this.memid = memid;
	}


}
