package com.membercoupon.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public class MemberCouponService {
	
	private MemberCouponDAO_interface dao;
	
	public MemberCouponService() {
		dao = new MemberCouponDAO();
	}
	
	public MemberCouponVO addMemberCoupon(Integer memid, Integer cpTpid, Date memCpDate, Integer memCpStatus, Date memCpRecord) {
		
		MemberCouponVO memberCouponVO = new MemberCouponVO();
		
		memberCouponVO.setMemid(memid);
		memberCouponVO.setCpTpid(cpTpid);	
		memberCouponVO.setMemCpDate(memCpDate);
		memberCouponVO.setMemCpStatus(memCpStatus);
		memberCouponVO.setMemCpRecord(memCpRecord);
		dao.insert(memberCouponVO);
		
		return memberCouponVO;
	}
	
	public MemberCouponVO updateMemberCoupon(Integer memCpid, Integer memid, Integer cpTpid, Date memCpDate,
			Integer memCpStatus, Date memCpRecord) {
		
		MemberCouponVO memberCouponVO = new MemberCouponVO();
		
		memberCouponVO.setMemCpid(memCpid);
		memberCouponVO.setMemid(memid);
		memberCouponVO.setCpTpid(cpTpid);	
		memberCouponVO.setMemCpDate(memCpDate);
		memberCouponVO.setMemCpStatus(memCpStatus);
		memberCouponVO.setMemCpRecord(memCpRecord);
		dao.insert(memberCouponVO);
		
		return memberCouponVO;
	}
	
	public void deleteMemberCoupon(Integer memCpid) {
		dao.delete(memCpid);
	}

	public MemberCouponVO getOneMemberCoupon(Integer memCpid) {
		return dao.findByPrimaryKey(memCpid);
	}

	public List<MemberCouponVO> getAll() {
		return dao.getAll();
	}
	public List<MemberCouponVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
}
