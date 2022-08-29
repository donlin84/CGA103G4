package com.registtrationform.model;

import java.time.LocalDateTime;
import java.util.List;
import com.member.model.*;

public class RegisttrationFormService {
	private RegisttrationFormDAO_interface dao;
	private MemberService membersrc;

	public RegisttrationFormService() {
		dao = new RegisttrationFormJDBCDAO();
		MemberService membersrc = new MemberService();
	}
	
	public RegisttrationFormVO addRegisttrationForm(Integer claid, Integer memid, Integer regPayment, Integer regStatus,
			Integer regReview, String regReviewContent

	) {

		RegisttrationFormVO registtrationFormVO = new RegisttrationFormVO();

		registtrationFormVO.setClaid(claid);
		registtrationFormVO.setMemid(memid);
		registtrationFormVO.setRegPayment(regPayment);
		registtrationFormVO.setRegStatus(regStatus);
		registtrationFormVO.setRegReview(regReview);
		registtrationFormVO.setRegReviewContent(regReviewContent);
		dao.insert(registtrationFormVO);
		return registtrationFormVO;
	}

	public RegisttrationFormVO updateRegisttrationForm(Integer regPayment, Integer regStatus, Integer regReview,
			String regReviewContent, Integer claid, Integer memid) {

		RegisttrationFormVO registtrationFormVO = new RegisttrationFormVO();

		registtrationFormVO.setRegPayment(regPayment);
		registtrationFormVO.setRegStatus(regStatus);
		registtrationFormVO.setRegReview(regReview);
		registtrationFormVO.setRegReviewContent(regReviewContent);
		registtrationFormVO.setClaid(claid);
		registtrationFormVO.setMemid(memid);
		dao.update(registtrationFormVO);

		return registtrationFormVO;
	}

//	public void deleteTeacher(Integer thrid) {
//		dao.delete(thrid);
//	}

	public RegisttrationFormVO getOneRegisttrationForm(Integer thrid, Integer memid) {
		return dao.findByPrimaryKey(thrid, memid);
	}

	public List<RegisttrationFormVO> getAll() {
		return dao.getAll();
	}
	
	public List<Integer> getAllmemid(){
		return membersrc.getAllMemid();
	}
}
