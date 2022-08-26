package com.frarticle.model;

import java.sql.Timestamp;

public class ArticleVO {
	private Integer atcid;
	private Integer frid;
	private Integer memid;
	private String atcTitle;
	private String atcContent;
	private Timestamp atcStime;
	private Timestamp atcLtime;
	private Integer atcStatus;

	public Integer getAtcid() {
		return atcid;
	}

	public void setAtcid(Integer atcid) {
		this.atcid = atcid;
	}

	public Integer getFrid() {
		return frid;
	}

	public void setFrid(Integer frid) {
		this.frid = frid;
	}

	public Integer getMemid() {
		return memid;
	}

	public void setMemid(Integer memid) {
		this.memid = memid;
	}

	public String getAtcTitle() {
		return atcTitle;
	}

	public void setAtcTitle(String atcTitle) {
		this.atcTitle = atcTitle;
	}

	public String getAtcContent() {
		return atcContent;
	}

	public void setAtcContent(String atcContent) {
		this.atcContent = atcContent;
	}

	public Timestamp getAtcStime() {
		return atcStime;
	}

	public void setAtcStime(Timestamp atcStime) {
		this.atcStime = atcStime;
	}

	public Timestamp getAtcLtime() {
		return atcLtime;
	}

	public void setAtcLtime(Timestamp atcLtime) {
		this.atcLtime = atcLtime;
	}

	public Integer getAtcStatus() {
		return atcStatus;
	}

	public void setAtcStatus(Integer atcStatus) {
		this.atcStatus = atcStatus;
	}
}
