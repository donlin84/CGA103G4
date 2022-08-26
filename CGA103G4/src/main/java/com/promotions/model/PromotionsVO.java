package com.promotions.model;

import java.sql.Date;

public class PromotionsVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer pmid;
	private String pmName;
	private String pmDescription;
	private Double pmDiscount;
	private Date pmStart;
	private Date pmEnd;
	private Integer pmStatus;
	
	
	public Integer getPmid() {
		return pmid;
	}
	public void setPmid(Integer pmid) {
		this.pmid = pmid;
	}
	public String getPmName() {
		return pmName;
	}
	public void setPmName(String pmName) {
		this.pmName = pmName;
	}
	public String getPmDescription() {
		return pmDescription;
	}
	public void setPmDescription(String pmDescription) {
		this.pmDescription = pmDescription;
	}
	public Double getPmDiscount() {
		return pmDiscount;
	}
	public void setPmDiscount(Double pmDiscount) {
		this.pmDiscount = pmDiscount;
	}
	public Date getPmStart() {
		return pmStart;
	}
	public void setPmStart(Date pmStart) {
		this.pmStart = pmStart;
	}
	public Date getPmEnd() {
		return pmEnd;
	}
	public void setPmEnd(Date pmEnd) {
		this.pmEnd = pmEnd;
	}
	public Integer getPmStatus() {
		return pmStatus;
	}
	public void setPmStatus(Integer pmStatus) {
		this.pmStatus = pmStatus;
	}
	
	
}
