package com.productSort.model;

public class ProductsortVO implements java.io.Serializable {
	
	private static final long serialVersionUID = 6863344816528844382L;
	private Integer pdsid;
	private String pdsName;
	
	public Integer getPdsid() {
		
		return pdsid;
	}
	public void setPdsid(Integer pdsid) {
		this.pdsid = pdsid;
	}
	public String getPdsName() {
		return pdsName;
	}
	public void setPdsName(String pdsName) {
		this.pdsName = pdsName;
	}

}
