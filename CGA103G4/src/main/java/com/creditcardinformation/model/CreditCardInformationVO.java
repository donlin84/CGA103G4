package com.creditcardinformation.model;

public class CreditCardInformationVO {
	private Integer creditCardid;
	private Integer memid;
	private String creditCardNumber;
	private String creditCardName;
	private String creditCardTime;
	private String cvcCode;
	
	public CreditCardInformationVO() {
		
	}
	
	public CreditCardInformationVO(Integer creditCardid, Integer memid, String creditCardNumber, String creditCardName,
			String creditCardTime, String cvcCode) {
		super();
		this.creditCardid = creditCardid;
		this.memid = memid;
		this.creditCardNumber = creditCardNumber;
		this.creditCardName = creditCardName;
		this.creditCardTime = creditCardTime;
		this.cvcCode = cvcCode;
	}
	
	public Integer getCreditCardid() {
		return creditCardid;
	}
	public void setCreditCardid(Integer creditCardid) {
		this.creditCardid = creditCardid;
	}
	public Integer getMemid() {
		return memid;
	}
	public void setMemid(Integer memid) {
		this.memid = memid;
	}
	public String getCreditCardNumber() {
		return creditCardNumber;
	}
	public void setCreditCardNumber(String creditCardNumber) {
		this.creditCardNumber = creditCardNumber;
	}
	public String getCreditCardName() {
		return creditCardName;
	}
	public void setCreditCardName(String creditCardName) {
		this.creditCardName = creditCardName;
	}
	public String getCreditCardTime() {
		return creditCardTime;
	}
	public void setCreditCardTime(String creditCardTime) {
		this.creditCardTime = creditCardTime;
	}
	public String getCvcCode() {
		return cvcCode;
	}
	public void setCvcCode(String cvcCode) {
		this.cvcCode = cvcCode;
	}	
}
