package com.member.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.announcement.model.AnnouncementVO;
import com.membercoupon.model.MemberCouponVO;

@Transactional(readOnly = true)
public class MemberHibernateDAO implements MemberDAO_interface{
	
	
	private HibernateTemplate hibernateTemplate;    
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) { 
        this.hibernateTemplate = hibernateTemplate;
    }

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void insert(MemberVO memberVO) {
			hibernateTemplate.saveOrUpdate(memberVO);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(MemberVO memberVO) {
			hibernateTemplate.update(memberVO);
	}

	@Override
	public MemberVO findByPrimaryKey(Integer empid) {
		MemberVO memberVO = (MemberVO)hibernateTemplate.get(MemberVO.class, empid);
		return memberVO;
	}

	@Override
	public List<MemberVO> getAll() {
		List<MemberVO> list = (List<MemberVO>)hibernateTemplate.loadAll(MemberVO.class);
		return list;
	}

//	@Override
//	public Set<MemberCouponVO> getMemberCouponByCpTpid(Integer memCpid) {
//		Set<MemberCouponVO> set = findByPrimaryKey(memCpid).getMemberCoupons();
//		return set;
//	}

	@Override
	public List<Integer> getAllMemid() {
		List<Integer> list = (List<Integer>)hibernateTemplate.loadAll(Integer.class);
		return list;
	}

	@Override
	public List<MemberVO> getAll(Map<String, String[]> map) {
		List<MemberVO> list = (List<MemberVO>)hibernateTemplate.loadAll(MemberVO.class);
		return list;
	}

	@Override
	public MemberVO findByAccount(String memAccount) {
		MemberVO memberVO = (MemberVO)hibernateTemplate.get(MemberVO.class, memAccount);
		return memberVO;
	}

}
