package com.promotionsdetail.model;

public class JoinAllVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer pmid;
	private String pmName;
	private Double pmDiscount;
	private Integer pdid;
	private String pdName;
	private Integer pdPrice;
	private Integer pdDiscountPrice;
	private Integer pmPdDiscountPrice;
	
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
	public Double getPmdiscount() {
		return pmDiscount;
	}
	public void setPmDiscount(Double pmDiscount) {
		this.pmDiscount = pmDiscount;
	}
	public Integer getPdid() {
		return pdid;
	}
	public void setPdid(Integer pdid) {
		this.pdid = pdid;
	}
	public String getPdName() {
		return pdName;
	}
	public void setPdName(String pdName) {
		this.pdName = pdName;
	}
	public Integer getPdPrice() {
		return pdPrice;
	}
	public void setPdPrice(Integer pdPrice) {
		this.pdPrice = pdPrice;
	}
	public Integer getPdDiscountPrice() {
		return pdDiscountPrice;
	}
	public void setPdDiscountPrice(Integer pdDiscountPrice) {
		this.pdDiscountPrice = pdDiscountPrice;
	}
	public Integer getPmPdDiscountPrice() {
		return pmPdDiscountPrice;
	}
	public void setPmPdDiscountPrice(Integer pmPdDiscountPrice) {
		this.pmPdDiscountPrice = pmPdDiscountPrice;
	}
	
}
