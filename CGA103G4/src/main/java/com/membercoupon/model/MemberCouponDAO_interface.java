package com.membercoupon.model;

import java.util.List;

public interface MemberCouponDAO_interface {
    public void insert(MemberCouponVO memberCouponVO);
    public void update(MemberCouponVO memberCouponVO);
    public void delete(Integer memCpid);
    public MemberCouponVO findByPrimaryKey(Integer memCpid);
    public List<MemberCouponVO> getAll();
}
