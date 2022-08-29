package com.frmsgreport.model;

import java.sql.Timestamp;

public class MsgReportVO {
	private Integer msgReportid;
	private Integer atcMsgid;
	private Integer memid;
	private Integer msgReportStatus;
	private Timestamp msgReportTime;
	private String msgReportContent;
	
	public Integer getMsgReportid() {
		return msgReportid;
	}
	public void setMsgReportid(Integer msgReportid) {
		this.msgReportid = msgReportid;
	}
	public Integer getAtcMsgid() {
		return atcMsgid;
	}
	public void setAtcMsgid(Integer atcMsgid) {
		this.atcMsgid = atcMsgid;
	}
	public Integer getMemid() {
		return memid;
	}
	public void setMemid(Integer memid) {
		this.memid = memid;
	}
	public Integer getMsgReportStatus() {
		return msgReportStatus;
	}
	public void setMsgReportStatus(Integer msgReportStatus) {
		this.msgReportStatus = msgReportStatus;
	}
	public Timestamp getMsgReportTime() {
		return msgReportTime;
	}
	public void setMsgReportTime(Timestamp msgReportTime) {
		this.msgReportTime = msgReportTime;
	}
	public String getMsgReportContent() {
		return msgReportContent;
	}
	public void setMsgReportContent(String msgReportContent) {
		this.msgReportContent = msgReportContent;
	}
}
