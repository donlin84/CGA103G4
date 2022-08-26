package com.frreport.model;

public class ArticleReportVO {
	private Integer atcReportid;
	private Integer atcid;
	private Integer memid;
	private String reportContent;
	private Integer reportStatus;

	public Integer getAtcReportid() {
		return atcReportid;
	}

	public void setAtcReportid(Integer atcReportid) {
		this.atcReportid = atcReportid;
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

	public Integer getReportStatus() {
		return reportStatus;
	}

	public void setReportStatus(Integer reportStatus) {
		this.reportStatus = reportStatus;
	}

	public String getReportContent() {
		return reportContent;
	}

	public void setReportContent(String reportContent) {
		this.reportContent = reportContent;
	}

}
