package com.frarticlemsg.model;

import java.sql.Timestamp;

public class ArticleMsgVO {
	private Integer atcMsgid;
	private Integer atcid;
	private Integer memid;
	private String msgContent;
	private Timestamp msgTime;
	private Integer msgStatus;

	public Integer getAtcMsgid() {
		return atcMsgid;
	}

	public void setAtcMsgid(Integer atcMsgid) {
		this.atcMsgid = atcMsgid;
	}

	public Integer getAtcid() {
		return atcid;
	}

	public void setAtcid(Integer atcid) {
		this.atcid = atcid;
	}

	public Integer getMemid() {
		return memid;
	}

	public void setMemid(Integer memid) {
		this.memid = memid;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public Timestamp getMsgTime() {
		return msgTime;
	}

	public void setMsgTime(Timestamp msgTime) {
		this.msgTime = msgTime;
	}

	public Integer getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(Integer msgStatus) {
		this.msgStatus = msgStatus;
	}

}
