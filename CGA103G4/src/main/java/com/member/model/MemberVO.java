package com.member.model;

import java.sql.Date;

public class MemberVO {
	private Integer memid;
	private String memName;
	private String memAccount;
	private String memPassword;
	private String memGender;
	private String memPhone;
	private String memEmail;
	private String memAddres;
	private Date memBirthday;
	private Integer memStatus;
	private String memNation;
	
//	public MemberVO() {
//		
//	}

//	public MemberVO(Integer memid, String memName, String memAccount, String memPassword, String memGender,
//			String memPhone, String memEmail, String memAddres, Date memBirthday, Integer memStatus,
//			String memNation) {
//		super();
//		this.memid = memid;
//		this.memName = memName;
//		this.memAccount = memAccount;
//		this.memPassword = memPassword;
//		this.memGender = memGender;
//		this.memPhone = memPhone;
//		this.memEmail = memEmail;
//		this.memAddres = memAddres;
//		this.memBirthday = memBirthday;
//		this.memStatus = memStatus;
//		this.memNation = memNation;
//	}
	public Integer getMemid() {
		return memid;
	}
	public void setMemid(Integer memid) {
		this.memid = memid;
	}
	public String getMemName() {
		return memName;
	}
	public void setMemName(String memName) {
		this.memName = memName;
	}
	public String getMemAccount() {
		return memAccount;
	}
	public void setMemAccount(String memAccount) {
		this.memAccount = memAccount;
	}
	public String getMemPassword() {
		return memPassword;
	}
	public void setMemPassword(String memPassword) {
		this.memPassword = memPassword;
	}
	public String getMemGender() {
		return memGender;
	}
	public void setMemGender(String memGender) {
		this.memGender = memGender;
	}
	public String getMemPhone() {
		return memPhone;
	}
	public void setMemPhone(String memPhone) {
		this.memPhone = memPhone;
	}
	public String getMemEmail() {
		return memEmail;
	}
	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}
	public String getMemAddres() {
		return memAddres;
	}
	public void setMemAddres(String memAddres) {
		this.memAddres = memAddres;
	}
	public Date getMemBirthday() {
		return memBirthday;
	}
	public void setMemBirthday(Date memBirthday) {
		this.memBirthday = memBirthday;
	}
	public Integer getMemStatus() {
		return memStatus;
	}
	public void setMemStatus(Integer memStatus) {
		this.memStatus = memStatus;
	}
	public String getMemNation() {
		return memNation;
	}
	public void setMemNation(String memNation) {
		this.memNation = memNation;
	}
}
