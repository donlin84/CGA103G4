package com.member.model;

import java.sql.Date;
import java.util.List;


public class MemberService {
	private MemberDAO_interface dao;

	public MemberService() {
		dao = new MemberJDBCDAO();
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

	

}
