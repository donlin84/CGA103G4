package com.membercoupon.model;

import java.util.List;
import java.util.Map;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public class MemberCouponHibernateDAO implements MemberCouponDAO_interface{
	
	
	private HibernateTemplate hibernateTemplate;    
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) { 
        this.hibernateTemplate = hibernateTemplate;
    }

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void insert(MemberCouponVO memberCouponVO) {
			hibernateTemplate.saveOrUpdate(memberCouponVO);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(MemberCouponVO memberCouponVO) {
			hibernateTemplate.update(memberCouponVO);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void delete(Integer memCpid) {
		MemberCouponVO memberCouponVO = new MemberCouponVO();
		memberCouponVO.setMemCpid(memCpid);
		hibernateTemplate.delete(memberCouponVO);
	}

	@Override
	public MemberCouponVO findByPrimaryKey(Integer memCpid) {
		MemberCouponVO memberCouponVO = (MemberCouponVO)hibernateTemplate.get(MemberCouponVO.class, memCpid);
		return memberCouponVO;
	}

	@Override
	public List<MemberCouponVO> getAll() {
		List<MemberCouponVO> list = (List<MemberCouponVO>)hibernateTemplate.loadAll(MemberCouponVO.class);
		return list;
	}
	
	@Override
	public List<MemberCouponVO> getAll(Map<String, String[]> map) {
		List<MemberCouponVO> list = (List<MemberCouponVO>)hibernateTemplate.loadAll(MemberCouponVO.class);
		return list;
	}

}
