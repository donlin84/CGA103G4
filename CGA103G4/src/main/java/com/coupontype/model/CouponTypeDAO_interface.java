package com.coupontype.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.membercoupon.model.MemberCouponVO;

public interface CouponTypeDAO_interface {
    public void insert(CouponTypeVO coupontypeVO);
    public void update(CouponTypeVO coupontypeVO);
    public void delete(Integer cpTpid);
    public CouponTypeVO findByPrimaryKey(Integer cpTpid);
    public Set<MemberCouponVO> getMemberCouponByCpTpid(Integer memCpid);
    public List<CouponTypeVO> getAll();
	public List<CouponTypeVO> getAll(Map<String, String[]> map);
}
