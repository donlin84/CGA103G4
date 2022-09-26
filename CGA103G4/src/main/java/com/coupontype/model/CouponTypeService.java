package com.coupontype.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.membercoupon.model.MemberCouponVO;
import com.promotions.model.PromotionsVO;

public class CouponTypeService {
	
	private CouponTypeDAO_interface dao;
	
	public CouponTypeService() {
		dao = new CouponTypeDAO();
	}
	
	public CouponTypeVO addCouponType(String cpName, Integer cpDiscount, Date cpStart, Date cpEnd,
			Integer cpStatus, byte[] cpPic) {
		
		CouponTypeVO couponTypeVO = new CouponTypeVO();
		
		couponTypeVO.setCpName(cpName);
		couponTypeVO.setCpDiscount(cpDiscount);
		couponTypeVO.setCpStart(cpStart);
		couponTypeVO.setCpEnd(cpEnd);
		couponTypeVO.setCpStatus(cpStatus);
		couponTypeVO.setCpPic(cpPic);
		dao.insert(couponTypeVO);
		
		return couponTypeVO;
	}
	
	public CouponTypeVO updateCouponType(Integer cpTpid,String cpName, Integer cpDiscount, Date cpStart, Date cpEnd,
			Integer cpStatus, byte[] cpPic) {
		
		CouponTypeVO couponTypeVO = new CouponTypeVO();
		
		couponTypeVO.setCpTpid(cpTpid);
		couponTypeVO.setCpName(cpName);
		couponTypeVO.setCpDiscount(cpDiscount);
		couponTypeVO.setCpStart(cpStart);
		couponTypeVO.setCpEnd(cpEnd);
		couponTypeVO.setCpStatus(cpStatus);
		couponTypeVO.setCpPic(cpPic);
		dao.update(couponTypeVO);
		
		return couponTypeVO;
	}
	
	public void deleteCouponType(Integer cpTpid) {
		dao.delete(cpTpid);
	}

	public CouponTypeVO getOneCouponType(Integer cpTpid) {
		return dao.findByPrimaryKey(cpTpid);
	}

	public List<CouponTypeVO> getAll() {
		return dao.getAll();
	}
	public List<CouponTypeVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
	
}

