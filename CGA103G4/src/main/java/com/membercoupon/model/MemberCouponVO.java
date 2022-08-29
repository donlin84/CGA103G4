package com.membercoupon.model;

import java.time.LocalDateTime;

public class MemberCouponVO implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer memCpid;
	private Integer memid;
	private Integer cpTpid;
	private LocalDateTime memCpDate;
	private Integer memCpStatus;
	private LocalDateTime memCpRecord;
	
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

	public LocalDateTime getMemCpDate() {
		return memCpDate;
	}

	public void setMemCpDate(LocalDateTime memCpDate) {
		this.memCpDate = memCpDate;
	}

	public Integer getMemCpStatus() {
		return memCpStatus;
	}

	public void setMemCpStatus(Integer memCpStatus) {
		this.memCpStatus = memCpStatus;
	}

	public LocalDateTime getMemCpRecord() {
		return memCpRecord;
	}

	public void setMemCpRecord(LocalDateTime memCpRecord) {
		this.memCpRecord = memCpRecord;
	}
	
	
}
