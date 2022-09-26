package com.membercoupon.model;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.coupontype.model.CouponTypeVO;
import com.member.model.MemberVO;
import com.promotionsdetail.model.PromotionsDetailVO;

public interface MemberCouponDAO_interface {
    public void insert(MemberCouponVO memberCouponVO);
    public void update(MemberCouponVO memberCouponVO);
    public void delete(Integer memCpid);
    public MemberCouponVO findByPrimaryKey(Integer memCpid);
    public List<MemberCouponVO> getAll();
  //萬用複合查詢(傳入參數型態Map)(回傳 List)
    public List<MemberCouponVO> getAll(Map<String, String[]> map);
//    ===============================亦翔新增===================================
    public void transUpdate (Integer memCpStatus, Integer memCpid, Connection con);
}
