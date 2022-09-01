package com.announcement.model;

import java.sql.Date;

import com.emp.model.EmpVO;

public class AnnouncementVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer annid;
	private EmpVO empVO;
	private String annContent;
	private byte[] annPic;
	private Integer annStatus;
	private Date annUpdate;
	private Date annTime;
	
	public Integer getAnnid() {
		return annid;
	}
	public void setAnnid(Integer annid) {
		this.annid = annid;
	}
	public EmpVO getEmpVO() {
		return empVO;
	}
	public void setEmpVO(EmpVO empVO) {
		this.empVO = empVO;
	}
	public String getAnnContent() {
		return annContent;
	}
	public void setAnnContent(String annContent) {
		this.annContent = annContent;
	}
	public byte[] getAnnPic() {
		return annPic;
	}
	public void setAnnPic(byte[] annPic) {
		this.annPic = annPic;
	}
	public Integer getAnnStatus() {
		return annStatus;
	}
	public void setAnnStatus(Integer annStatus) {
		this.annStatus = annStatus;
	}
	public Date getAnnUpdate() {
		return annUpdate;
	}
	public void setAnnUpdate(Date annUpdate) {
		this.annUpdate = annUpdate;
	}
	public Date getAnnTime() {
		return annTime;
	}
	public void setAnnTime(Date annTime) {
		this.annTime = annTime;
	}

	
}
