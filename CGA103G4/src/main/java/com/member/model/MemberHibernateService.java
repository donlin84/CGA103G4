package com.member.model;

import java.sql.Date;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.announcement.model.AnnouncementDAO_interface;

public class MemberHibernateService {
	
	private static MemberDAO_interface dao;

	static {
		//dao = new EmpHibernateDAO();
		//註1: 雖然model-config1-DriverManagerDataSource.xml也可以用
		//註2: 但為了使用Apache DBCP連線池,以提高效能,所以底下的model-config2-JndiObjectFactoryBean.xml內部dataSource設定是採用org.springframework.jndi.JndiObjectFactoryBean
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("membercoupon-config2-JndiObjectFactoryBean.xml");
		dao =(MemberDAO_interface) context.getBean("MemberHibernateDAO");
	}

	public MemberVO addMember(String memName, String memAccount, String memPassword, String memGender,
			String memPhone, String memEmail, String memAddres, Date memBirthday,
			String memNation) {

		MemberVO memberVO = new MemberVO();

		memberVO.setMemName(memName);
		memberVO.setMemAccount(memAccount);
		memberVO.setMemPassword(memPassword);
		memberVO.setMemGender(memGender);
		memberVO.setMemPhone(memPhone);
		memberVO.setMemEmail(memEmail);
		memberVO.setMemAddres(memAddres);
		memberVO.setMemBirthday(memBirthday);
		memberVO.setMemNation(memNation);
		dao.insert(memberVO);

		return memberVO;
	}

	public MemberVO updateMember(Integer memid, String memName, String memAccount, String memPassword, String memGender,
			String memPhone, String memEmail, String memAddres, Date memBirthday, Integer memStatus,
			String memNation) {

		MemberVO memberVO = new MemberVO();
		memberVO.setMemid(memid);
		memberVO.setMemName(memName);
		memberVO.setMemAccount(memAccount);
		memberVO.setMemPassword(memPassword);
		memberVO.setMemGender(memGender);
		memberVO.setMemPhone(memPhone);
		memberVO.setMemEmail(memEmail);
		memberVO.setMemAddres(memAddres);
		memberVO.setMemBirthday(memBirthday);
		memberVO.setMemStatus(memStatus);
		memberVO.setMemNation(memNation);
		dao.update(memberVO);

		return memberVO;
	}

	public MemberVO getOneMember(Integer memid) {
//		System.out.println("testOne");
		return dao.findByPrimaryKey(memid);
	}

	public List<MemberVO> getAll() {
//		System.out.println("testAll");
		return dao.getAll();
	}

	public List<Integer> getAllMemid() {
//		System.out.println("testAll");
		return dao.getAllMemid();
	}	


}
