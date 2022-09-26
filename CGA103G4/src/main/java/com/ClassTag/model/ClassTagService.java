package com.ClassTag.model;

import java.util.List;

public class ClassTagService {

	private ClassTagDAO_interface dao;

	public ClassTagService() {
		dao = new ClassTagJDBCDAO();
	}

	public ClassTagVO addClassTag(String claTagName, Integer claTagStatus) {

		ClassTagVO classTagVO = new ClassTagVO();
		classTagVO.setClaTagName(claTagName);
		classTagVO.setClaTagStatus(claTagStatus);
		dao.insert(classTagVO);

		return classTagVO;
	}

	public ClassTagVO updateClassTag(String claTagName, Integer claTagStatus,Integer claTagid) {

		ClassTagVO classTagVO = new ClassTagVO();
		classTagVO.setClaTagName(claTagName);
		classTagVO.setClaTagStatus(claTagStatus);
		classTagVO.setClaTagid(claTagid);
		dao.update(classTagVO);

		return classTagVO;
	}
	
	public void deleteClassTag(Integer claTagid) {
		dao.delete(claTagid);
	}
	
	public ClassTagVO getOneClassTag(Integer claTagid) {
		return dao.findByPrimaryKey(claTagid);
	}
	
	public List<ClassTagVO> getAll() {
		return dao.getAll();
	}

}
