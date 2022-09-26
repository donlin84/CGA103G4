package com.registtrationform.model;

import java.time.LocalDateTime;
import java.util.List;
import com.member.model.*;

import net.bytebuddy.asm.Advice.Return;

public class RegisttrationFormService {
	private RegisttrationFormDAO_interface dao;
	private MemberService membersrc;

	public RegisttrationFormService() {
		dao = new RegisttrationFormJDBCDAO();
		MemberService membersrc = new MemberService();
	}
	
	public RegisttrationFormVO addRegisttrationForm(Integer claid, Integer memid, Integer regPayment, Integer regStatus,Integer regPeople,
			Integer regReview, String regReviewContent

	) {

		RegisttrationFormVO registtrationFormVO = new RegisttrationFormVO();

		registtrationFormVO.setClaid(claid);
		registtrationFormVO.setMemid(memid);
		registtrationFormVO.setRegPayment(regPayment);
		registtrationFormVO.setRegStatus(regStatus);
		registtrationFormVO.setRegPeople(regPeople);
		registtrationFormVO.setRegReview(regReview);
		registtrationFormVO.setRegReviewContent(regReviewContent);
		dao.insert(registtrationFormVO);
		return registtrationFormVO;
	}

	public RegisttrationFormVO updateRegisttrationForm(Integer regPayment, Integer regStatus,Integer regPeople, Integer claid, Integer memid) {

		RegisttrationFormVO registtrationFormVO = new RegisttrationFormVO();

		registtrationFormVO.setRegPayment(regPayment);
		registtrationFormVO.setRegStatus(regStatus);
		registtrationFormVO.setRegPeople(regPeople);
		registtrationFormVO.setClaid(claid);
		registtrationFormVO.setMemid(memid);
		dao.update(registtrationFormVO);

		return registtrationFormVO;
	}

//	public void deleteTeacher(Integer thrid) {
//		dao.delete(thrid);
//	}

	public RegisttrationFormVO getOneRegisttrationForm(Integer claid, Integer memid) {
		return dao.findByPrimaryKey(claid, memid);
	}

	public List<RegisttrationFormVO> getAll() {
		return dao.getAll();
	}

	public List<Integer> getAllmemid(){
		return membersrc.getAllMemid();
	}
	
	public Integer getConutPeople(Integer claid) {
		return  dao.getConutPeopleByClaid(claid);
	}
	
	//給TIMER用抓MEMID
	public List<RegisttrationFormVO> timer_getmemid(Integer claid) {
		return dao.timer_getmemid(claid);
	}
	
	//單純修改訂單狀態
	public void update_status(Integer claid, Integer memid) {
		dao.update_status(claid, memid);
		return ;
	}
	//更新review和reviewcontent
	public RegisttrationFormVO update_review(Integer regReview,String regReviewContent, Integer claid, Integer memid) {

		RegisttrationFormVO registtrationFormVO = new RegisttrationFormVO();

		registtrationFormVO.setRegReview(regReview);
		registtrationFormVO.setRegReviewContent(regReviewContent);
		registtrationFormVO.setClaid(claid);
		registtrationFormVO.setMemid(memid);
		dao.update_review(registtrationFormVO);

		return registtrationFormVO;
	}
	//給click_people用的
		public List<RegisttrationFormVO> click_people(Integer claid) {
			return dao.click_people(claid);
		}
	//給評價統計用
		public List<RegisttrationFormVO> review_sum(Integer regreview,Integer claid) {
			return dao.review_sum(regreview, claid);
		}
}
