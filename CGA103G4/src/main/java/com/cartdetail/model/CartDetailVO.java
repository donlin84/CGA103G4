package com.cartdetail.model;

import java.io.Serializable;

public class CartDetailVO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Integer memid;
	private Integer pdid;
	private Integer pdNumber;
	
	public Integer getMemid() {
		return memid;
	}
	public void setMemid(Integer memid) {
		this.memid = memid;
	}
	public Integer getPdid() {
		return pdid;
	}
	public void setPdid(Integer pdid) {
		this.pdid = pdid;
	}
	public Integer getPdNumber() {
		return pdNumber;
	}
	public void setPdNumber(Integer pdNumber) {
		this.pdNumber = pdNumber;
	}
//	======================================亦翔新增=================================================
	public com.member.model.MemberVO getMemberVO() {
		com.member.model.MemberService memSvc = new com.member.model.MemberService();
		com.member.model.MemberVO memberVO = memSvc.getOneMember(memid);
		return memberVO;
	}
	public com.product.model.ProductVO getProductVO() {
		com.product.model.ProductService proSvc = new com.product.model.ProductService();
		com.product.model.ProductVO productVO = proSvc.getOneproduct(pdid);
		return productVO;
	}
	
}
