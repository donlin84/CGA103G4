package com.memberservicepicture.model;

import java.util.List;

public class MemberServicePictureService {
	private MemberServicePictureDAO_interface dao;

	public MemberServicePictureService() {
		dao = new MemberServicePictureJDBCDAO();
	}

	public MemberServicePictureVO addMemberServicePicture(Integer msrid, byte[] mspic) {

		MemberServicePictureVO memberServicePictureVO = new MemberServicePictureVO();

		memberServicePictureVO.setMsrid(msrid);
		memberServicePictureVO.setMspPic(mspic);
		dao.insert(memberServicePictureVO);
		return memberServicePictureVO;
	}

	public MemberServicePictureVO updateMemberServicePicture(Integer msrid, byte[] mspPic, Integer mspPicid) {

		MemberServicePictureVO memberServicePictureVO = new MemberServicePictureVO();
		memberServicePictureVO.setMsrid(msrid);
		memberServicePictureVO.setMspPic(mspPic);
		memberServicePictureVO.setMspPicid(mspPicid);
		dao.update(memberServicePictureVO);
		return memberServicePictureVO;
	}

	public void deleteMemberServicePicture(Integer mspPicid) {
		dao.delete(mspPicid);
	}

	public MemberServicePictureVO getOneMemberServicePicture(Integer mspPicid) {
		return dao.findByPrimaryKey(mspPicid);
	}

	public List<MemberServicePictureVO> getAll() {
		return dao.getAll();
	}
}
