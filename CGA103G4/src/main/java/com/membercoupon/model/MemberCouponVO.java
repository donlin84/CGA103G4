package com.membercoupon.model;

import java.sql.Date;

import com.coupontype.model.CouponTypeVO;
import com.member.model.MemberVO;

public class MemberCouponVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer memCpid;
	private Integer memid;
	private MemberVO memberVO;
	private Integer cpTpid;
	private CouponTypeVO couponTypeVO;
	private Date memCpDate;
	private Integer memCpStatus;
	private Date memCpRecord;
	
	public Integer getMemCpid() {
		return memCpid;
	}

	public void setMemCpid(Integer memCpid) {
		this.memCpid = memCpid;
	}

	public Integer getMemid() {
		return memid;
	}

	public void setMemid(Integer memid) {
		this.memid = memid;
	}

	public Integer getCpTpid() {
		return cpTpid;
	}

	public void setCpTpid(Integer cpTpid) {
		this.cpTpid = cpTpid;
	}

	public Date getMemCpDate() {
		return memCpDate;
	}

	public void setMemCpDate(Date memCpDate) {
		this.memCpDate = memCpDate;
	}

	public Integer getMemCpStatus() {
		return memCpStatus;
	}

	public void setMemCpStatus(Integer memCpStatus) {
		this.memCpStatus = memCpStatus;
	}

	public Date getMemCpRecord() {
		return memCpRecord;
	}

	public void setMemCpRecord(Date memCpRecord) {
		this.memCpRecord = memCpRecord;
	}
	
    public com.member.model.MemberVO getMemVO() {
    	com.member.model.MemberService memSvc = new com.member.model.MemberService();
    	com.member.model.MemberVO memberVO = memSvc.getOneMember(memid);
	    return memberVO;
    }
    
    public com.coupontype.model.CouponTypeVO getCpTpVO() {
    	com.coupontype.model.CouponTypeService cpTpSvc = new com.coupontype.model.CouponTypeService();
    	com.coupontype.model.CouponTypeVO couponTypeVO = cpTpSvc.getOneCouponType(cpTpid);
	    return couponTypeVO;
    }

	public MemberVO getMemberVO() {
		return memberVO;
	}

	public void setMemberVO(MemberVO memberVO) {
		this.memberVO = memberVO;
	}
	
	public CouponTypeVO getCouponTypeVO() {
		return couponTypeVO;
	}
	
	public void setCouponTypeVO(CouponTypeVO couponTypeVO) {
		this.couponTypeVO = couponTypeVO;
	}
}
