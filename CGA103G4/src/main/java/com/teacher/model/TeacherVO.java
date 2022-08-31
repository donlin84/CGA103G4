package com.teacher.model;

import java.io.Serializable;

public class TeacherVO implements Serializable {
	private Integer thrid;
	private String thrName;
	private String thrGender;
	private String thrPhone;
	private String thrEmail;
	private Integer thrStatus;
	private String thrIntroduction;
	private Integer thrComment;
	private Integer thrCmnumber;
	private byte[] thrPic;
	
	
	public Integer getThrid() {
		return thrid;
	}
	public void setThrid(Integer thrid) {
		this.thrid = thrid;
	}
	public String getThrName() {
		return thrName;
	}
	public void setThrName(String thrName) {
		this.thrName = thrName;
	}
	public String getThrGender() {
		return thrGender;
	}
	public void setThrGender(String thrGender) {
		this.thrGender = thrGender;
	}
	public String getThrPhone() {
		return thrPhone;
	}
	public void setThrPhone(String thrPhone) {
		this.thrPhone = thrPhone;
	}
	public String getThrEmail() {
		return thrEmail;
	}
	public void setThrEmail(String thrEmail) {
		this.thrEmail = thrEmail;
	}
	public Integer getThrStatus() {
		return thrStatus;
	}
	public void setThrStatus(Integer thrStatus) {
		this.thrStatus = thrStatus;
	}
	public String getThrIntroduction() {
		return thrIntroduction;
	}
	public void setThrIntroduction(String thrIntroduction) {
		this.thrIntroduction = thrIntroduction;
	}
	public Integer getThrComment() {
		return thrComment;
	}
	public void setThrComment(Integer thrComment) {
		this.thrComment = thrComment;
	}
	public Integer getThrCmnumber() {
		return thrCmnumber;
	}
	public void setThrCmnumber(Integer thrCmnumber) {
		this.thrCmnumber = thrCmnumber;
	}
	public byte[] getThrPic() {
		return thrPic;
	}
	public void setThrPic(byte[] thrPic) {
		this.thrPic = thrPic;
	}
	
	
}
