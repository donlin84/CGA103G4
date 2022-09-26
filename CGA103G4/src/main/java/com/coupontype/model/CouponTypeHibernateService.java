package com.coupontype.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CouponTypeHibernateService {
	
	private static CouponTypeDAO_interface dao;
	
	static {
		//dao = new EmpHibernateDAO();
		//註1: 雖然model-config1-DriverManagerDataSource.xml也可以用
		//註2: 但為了使用Apache DBCP連線池,以提高效能,所以底下的model-config2-JndiObjectFactoryBean.xml內部dataSource設定是採用org.springframework.jndi.JndiObjectFactoryBean
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("membercoupon-config2-JndiObjectFactoryBean.xml");
		dao =(CouponTypeDAO_interface) context.getBean("CouponTypeHibernateDAO");
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

