package com.memberservicerecord.model;

import java.time.LocalDateTime;
import java.util.List;

import com.keyword.model.KeyWordDAO_interface;
import com.keyword.model.KeyWordJDBCDAO;
import com.keyword.model.KeyWordVO;

public class MemberServiceRecordService {
	private MemberServiceRecordDAO_interface dao;

	public MemberServiceRecordService() {
		dao = new MemberServiceRecordJDBCDAO();
	}

	public MemberServiceRecordVO addMemberServiceRecord(Integer empid, Integer memid, String msrText,
			Integer direction) {

		MemberServiceRecordVO memberServiceRecordVO = new MemberServiceRecordVO();

		memberServiceRecordVO.setEmpid(empid);
		memberServiceRecordVO.setMemid(memid);
		memberServiceRecordVO.setMsrText(msrText);
		memberServiceRecordVO.setDirection(direction);

		dao.insert(memberServiceRecordVO);
		return memberServiceRecordVO;
	}

	public MemberServiceRecordVO updateMemberServiceRecord(Integer empid, Integer memid, String msrText,
			Integer direction, Integer msrid) {

		MemberServiceRecordVO memberServiceRecordVO = new MemberServiceRecordVO();

		memberServiceRecordVO.setEmpid(empid);
		memberServiceRecordVO.setMemid(memid);
		memberServiceRecordVO.setMsrText(msrText);
		memberServiceRecordVO.setDirection(direction);
		memberServiceRecordVO.setMsrid(msrid);
		dao.update(memberServiceRecordVO);

		return memberServiceRecordVO;
	}

	public void deleteMemberServiceRecord(Integer msrid) {
		dao.delete(msrid);
	}

	public MemberServiceRecordVO getOneKeyWord(Integer msrid) {
		return dao.findByPrimaryKey(msrid);
	}

	public List<MemberServiceRecordVO> getAll() {
		return dao.getAll();
	}

}
