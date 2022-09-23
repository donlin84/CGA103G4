package com.fr.model;

import java.util.List;


public class ForumService {

	private ForumDAO_interface dao;

	public ForumService() {
		dao = new ForumDAO();
	}

	public ForumVO addfr(String frName) {
		ForumVO forumVO=new ForumVO();
		forumVO.setFrName(frName);
		dao.insert(forumVO);
		return forumVO;
	}

	public ForumVO updatefr(Integer frid, String frName) {
		ForumVO forumVO = new ForumVO();
		forumVO.setFrid(frid);
		forumVO.setFrName(frName);
		dao.update(forumVO);
		return forumVO;
	}

	public ForumVO getOnefr(Integer frid) {
		return dao.findByPrimaryKey(frid);
	}

	public List<ForumVO> getAll() {
		return dao.getAll();
	}

	public void deleteFr(Integer frid) {
		dao.delete(frid);
	}
}
