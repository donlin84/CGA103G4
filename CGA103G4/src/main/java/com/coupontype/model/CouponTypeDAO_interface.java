package com.coupontype.model;

import java.util.List;

public interface CouponTypeDAO_interface {
    public void insert(CouponTypeVO coupontypeVO);
    public void update(CouponTypeVO coupontypeVO);
    public void delete(Integer cpTpid);
    public CouponTypeVO findByPrimaryKey(Integer cpTpid);
    public List<CouponTypeVO> getAll();
}
