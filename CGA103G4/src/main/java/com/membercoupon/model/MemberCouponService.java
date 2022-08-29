package com.membercoupon.model;

import java.time.LocalDateTime;
import java.util.List;

public class MemberCouponService {
	
	private MemberCouponDAO_interface dao;
	
	
	public MemberCouponVO addMemberCoupon(Integer memid, Integer cpTpid, LocalDateTime memCpDate, Integer memCpStatus,
			LocalDateTime memCpRecord) {
		
		MemberCouponVO memberCouponVO = new MemberCouponVO();
		
		memberCouponVO.setMemid(memid);
		memberCouponVO.setCpTpid(cpTpid);
		memberCouponVO.setMemCpDate(memCpDate);
		memberCouponVO.setMemCpStatus(memCpStatus);
		memberCouponVO.setMemCpRecord(memCpRecord);
		dao.insert(memberCouponVO);
		
		return memberCouponVO;
	}
	
	public MemberCouponVO updateMemberCoupon(Integer memCpid, Integer memid, Integer cpTpid, LocalDateTime memCpDate,
			Integer memCpStatus, LocalDateTime memCpRecord) {
		
		MemberCouponVO memberCouponVO = new MemberCouponVO();
		
		memberCouponVO.setCpTpid(memCpid);
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
	
}
