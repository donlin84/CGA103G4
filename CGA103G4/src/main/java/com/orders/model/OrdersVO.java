package com.orders.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.membercoupon.model.MemberCouponService;
import com.membercoupon.model.MemberCouponVO;
import com.orderdetail.model.OrderDetailDAO;
import com.orderdetail.model.OrderDetailDAO_interface;
import com.orderdetail.model.OrderDetailVO;

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
	
//	========================冠銓新增=====================
	//根據ordid找order detail
	
	public List<OrderDetailVO> getAllDetailByOrdid(Integer ordid){
		OrderDetailDAO_interface dao = new OrderDetailDAO();	
		return dao.selectAllByOrdid(ordid);
	}
	//查詢一筆order 根據 ordid找order detail
	public com.orderdetail.model.OrderDetailVO getOrderDetailVO(){
		com.orderdetail.model.OrderDetailService orderDeSvc = new com.orderdetail.model.OrderDetailService();
		com.orderdetail.model.OrderDetailVO orderDetailVO = orderDeSvc.getOneUserAllDetailJoin(ordid);
		return orderDetailVO;
	}
//	========================亦翔新增=====================
	
	public com.member.model.MemberVO getMemberVO() {
		com.member.model.MemberService memSvc = new com.member.model.MemberService();
		com.member.model.MemberVO memberVO = memSvc.getOneMember(memid);
		return memberVO;
	}
	
	public MemberCouponVO getMemberCouponVO() {
		MemberCouponService memCpSvc = new MemberCouponService();
		MemberCouponVO memCpVO = memCpSvc.getOneMemberCoupon(memCpid);
		return memCpVO;
	}
}
