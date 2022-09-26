package com.coupontype.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.announcement.model.AnnouncementVO;
import com.membercoupon.model.MemberCouponVO;

@Transactional(readOnly = true)
public class CouponTypeHibernateDAO implements CouponTypeDAO_interface{
	
	
	private HibernateTemplate hibernateTemplate;    
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) { 
        this.hibernateTemplate = hibernateTemplate;
    }

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void insert(CouponTypeVO couponTypeVO) {
			hibernateTemplate.saveOrUpdate(couponTypeVO);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public void update(CouponTypeVO couponTypeVO) {
			hibernateTemplate.update(couponTypeVO);
	}

	@Override
	public CouponTypeVO findByPrimaryKey(Integer empid) {
		CouponTypeVO couponTypeVO = (CouponTypeVO)hibernateTemplate.get(CouponTypeVO.class, empid);
		return couponTypeVO;
	}

	@Override
	public List<CouponTypeVO> getAll() {
		List<CouponTypeVO> list = (List<CouponTypeVO>)hibernateTemplate.loadAll(CouponTypeVO.class);
		return list;
	}

	@Override
	public void delete(Integer cpTpid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Set<MemberCouponVO> getMemberCouponByCpTpid(Integer memCpid) {
		Set<MemberCouponVO> set = findByPrimaryKey(memCpid).getMemberCoupons();
		return set;
	}

	@Override
	public List<CouponTypeVO> getAll(Map<String, String[]> map) {
		List<CouponTypeVO> list = (List<CouponTypeVO>)hibernateTemplate.loadAll(CouponTypeVO.class);
		return list;
	}

}
