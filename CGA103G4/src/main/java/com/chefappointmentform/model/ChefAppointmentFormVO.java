package com.chefappointmentform.model;

import java.sql.Date;

public class ChefAppointmentFormVO implements java.io.Serializable{
	private Integer apmid;
	private Integer memid;
	private Integer chefid;
	private Date apmDate;
	private Integer apmTime;
	private Integer apmPrice;
	private Integer apmStatus;
	private Integer star;
	private String comments;
	
	public ChefAppointmentFormVO() {
		
	}
	
	public ChefAppointmentFormVO(Integer apmid, Integer memid, Integer chefid, Date apmDate, Integer apmTime,
			Integer apmPrice, Integer apmStatus, Integer star, String comments) {
		super();
		this.apmid = apmid;
		this.memid = memid;
		this.chefid = chefid;
		this.apmDate = apmDate;
		this.apmTime = apmTime;
		this.apmPrice = apmPrice;
		this.apmStatus = apmStatus;
		this.star = star;
		this.comments = comments;
	}
	
	public Integer getApmid() {
		return apmid;
	}
	public void setApmid(Integer apmid) {
		this.apmid = apmid;
	}
	public Integer getMemid() {
		return memid;
	}
	public void setMemid(Integer memid) {
		this.memid = memid;
	}
	public Integer getChefid() {
		return chefid;
	}
	public void setChefid(Integer chefid) {
		this.chefid = chefid;
	}
	public Date getApmDate() {
		return apmDate;
	}
	public void setApmDate(Date apmDate) {
		this.apmDate = apmDate;
	}
	public Integer getApmTime() {
		return apmTime;
	}
	public void setApmTime(Integer apmTime) {
		this.apmTime = apmTime;
	}
	public Integer getApmPrice() {
		return apmPrice;
	}
	public void setApmPrice(Integer apmPrice) {
		this.apmPrice = apmPrice;
	}
	public Integer getApmStatus() {
		return apmStatus;
	}
	public void setApmStatus(Integer apmStatus) {
		this.apmStatus = apmStatus;
	}
	public Integer getStar() {
		return star;
	}
	public void setStar(Integer star) {
		this.star = star;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	//取得會員VO
	public com.member.model.MemberVO getMemberVO(){
		com.member.model.MemberService memsvc = new com.member.model.MemberService();
		com.member.model.MemberVO memVO = memsvc.getOneMember(memid);
		return memVO;
	}
	//取得私廚VO
	public com.chef.model.ChefVO getChefVO(){
		com.chef.model.ChefService chefsvc = new com.chef.model.ChefService();
		com.chef.model.ChefVO chefVO = chefsvc.getOneChef(chefid);
		return chefVO;
	}
}
