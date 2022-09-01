package com.product.model;

import java.time.LocalDateTime;

import java.time.temporal.ChronoUnit;


public class ProductVO implements java.io.Serializable {
	
	private static final long serialVersionUID = -7792255716423459227L;
	private Integer pdid;
	private	Integer pdsid;
	private	String pdName;
	private	Integer pdPrice;
	private	Integer	pdDiscountPrice;
	private String pdDescription;
	private Integer pdStatus;
	private LocalDateTime pdUpdate;
	
	
	
	public Integer getPdid() {
		return pdid;
	}
	public void setPdid(Integer pdid) {
		this.pdid = pdid;
	}
	public Integer getPdsid() {
		return pdsid;
	}
	public void setPdsid(Integer pdsid) {
		this.pdsid = pdsid;
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
	public String getPdDescription() {
		return pdDescription;
	}
	public void setPdDescription(String pdDescription) {
		this.pdDescription = pdDescription;
	}
	public Integer getPdStatus() {
		return pdStatus;
	}
	public void setPdStatus(Integer pdStatus) {
		this.pdStatus = pdStatus;
	}
	public LocalDateTime getPdUpdate() {
		
		return pdUpdate.truncatedTo(ChronoUnit.SECONDS);
	}
	public void setPdUpdate(LocalDateTime pdUpdate) {
		this.pdUpdate = pdUpdate;
	}
	public com.productSort.model.ProductsortVO getProductSortVO(){
		com.productSort.model.ProductsortService pdsSvc = new com.productSort.model.ProductsortService();
		com.productSort.model.ProductsortVO productsortVO = pdsSvc.getOneproductsort(pdsid);
	return productsortVO;
	}

	
	

}
