package com.receiptInformation.model;

public class ReceiptInformationVO implements java.io.Serializable {

	private Integer rcpid;
	private Integer memid;
	private String rcpName;
	private String rcpCvs;
	private String rcpAddress;
	private String rcpPhone;
	
	public Integer getRcpid() {
		return rcpid;
	}
	public void setRcpid(Integer rcpid) {
		this.rcpid = rcpid;
	}
	public Integer getMemid() {
		return memid;
	}
	public void setMemid(Integer memid) {
		this.memid = memid;
	}
	public String getRcpName() {
		return rcpName;
	}
	public void setRcpName(String rcpName) {
		this.rcpName = rcpName;
	}
	public String getRcpCvs() {
		return rcpCvs;
	}
	public void setRcpCvs(String rcpCvs) {
		this.rcpCvs = rcpCvs;
	}
	public String getRcpAddress() {
		return rcpAddress;
	}
	public void setRcpAddress(String rcpAddress) {
		this.rcpAddress = rcpAddress;
	}
	public String getRcpPhone() {
		return rcpPhone;
	}
	public void setRcpPhone(String rcpPhone) {
		this.rcpPhone = rcpPhone;
	}

}
