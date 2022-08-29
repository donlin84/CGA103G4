package com.registtrationform.model;

import java.time.LocalDateTime;

public class RegisttrationFormVO {
	private Integer claid;
	private Integer memid;
	private Integer regPayment;
	private LocalDateTime regTime;
	private Integer regStatus;
	private Integer regReview;
	private String regReviewContent;
	
	public Integer getClaid() {
		return claid;
	}
	public void setClaid(Integer claid) {
		this.claid = claid;
	}
	public Integer getMemid() {
		return memid;
	}
	public void setMemid(Integer memid) {
		this.memid = memid;
	}
	public Integer getRegPayment() {
		return regPayment;
	}
	public void setRegPayment(Integer regPayment) {
		this.regPayment = regPayment;
	}
	public LocalDateTime getRegTime() {
		return regTime;
	}
	public void setRegTime(LocalDateTime regTime) {
		this.regTime = regTime;
	}
	public Integer getRegStatus() {
		return regStatus;
	}
	public void setRegStatus(Integer regStatus) {
		this.regStatus = regStatus;
	}
	public Integer getRegReview() {
		return regReview;
	}
	public void setRegReview(Integer regReview) {
		this.regReview = regReview;
	}
	public String getRegReviewContent() {
		return regReviewContent;
	}
	public void setRegReviewContent(String regReviewContent) {
		this.regReviewContent = regReviewContent;
	}
	
}
