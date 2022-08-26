package com.chefschedule.model;

import java.sql.Date;

public class ChefScheduleVO implements java.io.Serializable{
	private Integer chefSchid;
	private Integer chefid;
	private Date schDate;
	private Integer schTime;
	
	public ChefScheduleVO() {
		
	}
	
	public ChefScheduleVO(Integer chefSchid, Integer chefid, Date schDate, Integer schTime) {
		super();
		this.chefSchid = chefSchid;
		this.chefid = chefid;
		this.schDate = schDate;
		this.schTime = schTime;
	}
	
	public Integer getChefSchid() {
		return chefSchid;
	}
	public void setChefSchid(Integer chefSchid) {
		this.chefSchid = chefSchid;
	}
	public Integer getChefid() {
		return chefid;
	}
	public void setChefid(Integer chefid) {
		this.chefid = chefid;
	}
	public Date getSchDate() {
		return schDate;
	}
	public void setSchDate(Date schDate) {
		this.schDate = schDate;
	}
	public Integer getSchTime() {
		return schTime;
	}
	public void setSchTime(Integer schTime) {
		this.schTime = schTime;
	}
	
}
