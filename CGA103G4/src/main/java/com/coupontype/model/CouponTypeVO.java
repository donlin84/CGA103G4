package com.coupontype.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.membercoupon.model.MemberCouponVO;

public class CouponTypeVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer cpTpid;
	private String cpName;
	private Integer cpDiscount;
	private Date cpStart;
	private Date cpEnd;
	private Integer cpStatus;
	private byte[] cpPic;
	private Set<MemberCouponVO> memberCoupons = new HashSet<MemberCouponVO>();
	
	public Integer getCpTpid() {
		return cpTpid;
	}

	public void setCpTpid(Integer cpTpid) {
		this.cpTpid = cpTpid;
	}

	public String getCpName() {
		return cpName;
	}

	public void setCpName(String cpName) {
		this.cpName = cpName;
	}

	public Integer getCpDiscount() {
		return cpDiscount;
	}

	public void setCpDiscount(Integer cpDiscount) {
		this.cpDiscount = cpDiscount;
	}

	public Date getCpStart() {
		return cpStart;
	}

	public void setCpStart(Date cpStart) {
		this.cpStart = cpStart;
	}

	public Date getCpEnd() {
		return cpEnd;
	}

	public void setCpEnd(Date cpEnd) {
		this.cpEnd = cpEnd;
	}

	public Integer getCpStatus() {
		return cpStatus;
	}

	public void setCpStatus(Integer cpStatus) {
		this.cpStatus = cpStatus;
	}

	public byte[] getCpPic() {
		return cpPic;
	}

	public void setCpPic(byte[] cpPic) {
		this.cpPic = cpPic;
	}

	public Set<MemberCouponVO> getMemberCoupons() {
		return memberCoupons;
	}

	public void setMemberCoupons(Set<MemberCouponVO> memberCoupons) {
		this.memberCoupons = memberCoupons;
	}
	

}
