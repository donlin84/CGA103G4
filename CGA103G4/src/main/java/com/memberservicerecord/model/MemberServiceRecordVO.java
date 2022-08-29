package com.memberservicerecord.model;

import java.time.LocalDateTime;

public class MemberServiceRecordVO {
	private Integer msrid;
	private Integer empid;
	private Integer memid;
	private String msrText;
	private LocalDateTime msrTime;
	private Integer direction;
	public Integer getMsrid() {
		return msrid;
	}
	public void setMsrid(Integer msrid) {
		this.msrid = msrid;
	}
	public Integer getEmpid() {
		return empid;
	}
	public void setEmpid(Integer empid) {
		this.empid = empid;
	}
	public Integer getMemid() {
		return memid;
	}
	public void setMemid(Integer memid) {
		this.memid = memid;
	}
	public String getMsrText() {
		return msrText;
	}
	public void setMsrText(String msrText) {
		this.msrText = msrText;
	}
	public LocalDateTime getMsrTime() {
		return msrTime;
	}
	public void setMsrTime(LocalDateTime msrTime) {
		this.msrTime = msrTime;
	}
	public Integer getDirection() {
		return direction;
	}
	public void setDirection(Integer direction) {
		this.direction = direction;
	}
	
	
}
