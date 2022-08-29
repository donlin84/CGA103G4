package com.orders.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class OrdersVO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer ordid;
	private Integer memid;
	private Integer memCpid;
	private Integer ordSubTotal;
	private Integer ordTotal;
	private Integer ordStatus;
	private LocalDateTime ordCreate;
	private String ordRecipient;
	private String recPhone;
	private Integer ordPayment;
	private Integer ordDelivery;
	private String ordAddress;
//	private LocalDateTime ordHopetime;
	
	public Integer getOrdid() {
		return ordid;
	}
	public void setOrdid(Integer ordid) {
		this.ordid = ordid;
	}
	public Integer getMemid() {
		return memid;
	}
	public void setMemid(Integer memid) {
		this.memid = memid;
	}
	public Integer getMemCpid() {
		return memCpid;
	}
	public void setMemCpid(Integer memCpid) {
		this.memCpid = memCpid;
	}
	public Integer getOrdSubTotal() {
		return ordSubTotal;
	}
	public void setOrdSubTotal(Integer ordSubTotal) {
		this.ordSubTotal = ordSubTotal;
	}
	public Integer getOrdTotal() {
		return ordTotal;
	}
	public void setOrdTotal(Integer ordTotal) {
		this.ordTotal = ordTotal;
	}
	public Integer getOrdStatus() {
		return ordStatus;
	}
	public void setOrdStatus(Integer ordStatus) {
		this.ordStatus = ordStatus;
	}
	public LocalDateTime getOrdCreate() {
		return ordCreate;
	}
	public void setOrdCreate(LocalDateTime ordCreate) {
		this.ordCreate = ordCreate;
	}
	public String getOrdRecipient() {
		return ordRecipient;
	}
	public void setOrdRecipient(String ordRecipient) {
		this.ordRecipient = ordRecipient;
	}
	public String getRecPhone() {
		return recPhone;
	}
	public void setRecPhone(String recPhone) {
		this.recPhone = recPhone;
	}
	public Integer getOrdPayment() {
		return ordPayment;
	}
	public void setOrdPayment(Integer ordPayment) {
		this.ordPayment = ordPayment;
	}
	public Integer getOrdDelivery() {
		return ordDelivery;
	}
	public void setOrdDelivery(Integer ordDelivery) {
		this.ordDelivery = ordDelivery;
	}
	public String getOrdAddress() {
		return ordAddress;
	}
	public void setOrdAddress(String ordAddress) {
		this.ordAddress = ordAddress;
	}
//	public LocalDateTime getOrdHopetime() {
//		return ordHopetime;
//	}
//	public void setOrdHopetime(LocalDateTime ordHopetime) {
//		this.ordHopetime = ordHopetime;
//	}
	
}
