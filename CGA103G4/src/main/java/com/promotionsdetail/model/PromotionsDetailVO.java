package com.promotionsdetail.model;

public class PromotionsDetailVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer pmid;
	private Integer pdid;
	private Integer pmPdDiscountPrice;
	
	public Integer getPmid() {
		return pmid;
	}
	public void setPmid(Integer pmid) {
		this.pmid = pmid;
	}
	public Integer getPdid() {
		return pdid;
	}
	public void setPdid(Integer pdid) {
		this.pdid = pdid;
	}
	public Integer getPmPdDiscountPrice() {
		return pmPdDiscountPrice;
	}
	public void setPmPdDiscountPrice(Integer pmPdDiscountPrice) {
		this.pmPdDiscountPrice = pmPdDiscountPrice;
	}
}
