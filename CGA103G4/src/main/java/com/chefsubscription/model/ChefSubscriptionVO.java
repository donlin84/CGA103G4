package com.chefsubscription.model;

public class ChefSubscriptionVO implements java.io.Serializable{
	private Integer Chefid;
	private Integer Memid;
	
	public ChefSubscriptionVO() {
		
	}
	
	public ChefSubscriptionVO(Integer Chefid, Integer Memid) {
		super();
		this.Chefid = Chefid;
		this.Memid = Memid;
	}
	
	public Integer getChefid() {
		return Chefid;
	}
	public void setChefid(Integer Chefid) {
		this.Chefid = Chefid;
	}
	public Integer getMemid() {
		return Memid;
	}
	public void setMemid(Integer Memid) {
		this.Memid = Memid;
	}
	
}
