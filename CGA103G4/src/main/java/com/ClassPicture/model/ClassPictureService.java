package com.ClassPicture.model;

import java.util.List;

public class ClassPictureService {
	private ClassPictureDAO_interface dao;

	ClassPictureService() {
		dao = new ClassPictureJDBCDAO();
	}

	public ClassPictureVO addClassPicture(Integer claPicid, Integer claid, byte[] claPic) {

		ClassPictureVO classPictureVO = new ClassPictureVO();

		classPictureVO.setClaid(claid);
		classPictureVO.setClaPic(claPic);
		dao.insert(classPictureVO);

		return classPictureVO;
	}

	public ClassPictureVO updateClassPicture(Integer claPicid, Integer claid, byte[] claPic) {

		ClassPictureVO classPictureVO = new ClassPictureVO();

		classPictureVO.setClaid(claid);
		classPictureVO.setClaPic(claPic);
		dao.update(classPictureVO);

		return classPictureVO;
	}
	
	public void deleteClassPicture(Integer claPicid) {
		dao.delete(claPicid);
	}
	
	public List<ClassPictureVO> getOneClassPicture(Integer claPicid) {
		return dao.findByPrimaryKey(claPicid);
	}
	
	public List<ClassPictureVO> getAll(){
		return dao.getAll();
	}

}
