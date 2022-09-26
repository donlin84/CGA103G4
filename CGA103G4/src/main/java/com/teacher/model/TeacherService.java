package com.teacher.model;

import java.util.List;
import java.util.Map;

public class TeacherService {
	private TeacherDAO_interface dao;

	public TeacherService() {
		dao = new TeacherJDBCDAO();
	}

	public TeacherVO addTeacher(String thrName, String thrGender, String thrPhone, String thrEmail, Integer thrStatus,
			String thrIntroduction, Integer thrComment, Integer thrCmnumber, byte[] thrPic) {

		TeacherVO teacherVO = new TeacherVO();

		teacherVO.setThrName(thrName);
		teacherVO.setThrGender(thrGender);
		teacherVO.setThrPhone(thrPhone);
		teacherVO.setThrEmail(thrEmail);
		teacherVO.setThrStatus(thrStatus);
		teacherVO.setThrIntroduction(thrIntroduction);
		teacherVO.setThrComment(thrComment);
		teacherVO.setThrCmnumber(thrCmnumber);
		teacherVO.setThrPic(thrPic);
		dao.insert(teacherVO);

		return teacherVO;
	}

	public TeacherVO updateTeacher(String thrName, String thrGender, String thrPhone, String thrEmail,
			Integer thrStatus, String thrIntroduction, Integer thrComment, Integer thrCmnumber, byte[] thrPic,
			Integer thrid) {

		TeacherVO teacherVO = new TeacherVO();

		teacherVO.setThrid(thrid);
		teacherVO.setThrName(thrName);
		teacherVO.setThrGender(thrGender);
		teacherVO.setThrPhone(thrPhone);
		teacherVO.setThrEmail(thrEmail);
		teacherVO.setThrStatus(thrStatus);
		teacherVO.setThrIntroduction(thrIntroduction);
		teacherVO.setThrComment(thrComment);
		teacherVO.setThrCmnumber(thrCmnumber);
		teacherVO.setThrPic(thrPic);
		dao.update(teacherVO);

		return teacherVO;
	}

//	public void deleteTeacher(Integer thrid) {
//		dao.delete(thrid);
//	}

	public TeacherVO getOneTeacher(Integer thrid) {
		return dao.findByPrimaryKey(thrid);
	}

	public List<TeacherVO> getAll() {
		return dao.getAll();
	}
	public List<TeacherVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}

}
