package com.membercoupon.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.coupontype.model.CouponTypeVO;
import com.member.model.MemberVO;

public class MemberCouponHibernateService {
	
	private static MemberCouponDAO_interface dao;
	
	static {
		//dao = new EmpHibernateDAO();
		//註1: 雖然model-config1-DriverManagerDataSource.xml也可以用
		//註2: 但為了使用Apache DBCP連線池,以提高效能,所以底下的model-config2-JndiObjectFactoryBean.xml內部dataSource設定是採用org.springframework.jndi.JndiObjectFactoryBean
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("membercoupon-config2-JndiObjectFactoryBean.xml");
		dao =(MemberCouponDAO_interface) context.getBean("MemberCouponHibernateDAO");
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
