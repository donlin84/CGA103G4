package com.cartdetail.model;

import java.io.Serializable;

public class CartDetailVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer memid;
	private Integer pdid;
	private Integer pdNumber;
	
	public Integer getMemid() {
		return memid;
	}
	public void setMemid(Integer memid) {
		this.memid = memid;
	}
	public Integer getPdid() {
		return pdid;
	}
	public void setPdid(Integer pdid) {
		this.pdid = pdid;
	}
	public Integer getPdNumber() {
		return pdNumber;
	}
	public void setPdNumber(Integer pdNumber) {
		this.pdNumber = pdNumber;
	}
	
}
