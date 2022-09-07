package com.chefskills.model;

import java.util.List;
import java.util.Map;



public class ChefSkillsService {
	private ChefSkillsDAO_interface dao;

	public ChefSkillsService() {
		dao = new ChefSkillsJDBCDAO();
	}

	public ChefSkillsVO addChefSkills(Integer chefid, Integer skillid) {

		ChefSkillsVO chefSkillsVO = new ChefSkillsVO();

		chefSkillsVO.setChefid(chefid);
		chefSkillsVO.setSkillid(skillid);
		dao.insert(chefSkillsVO);

		return chefSkillsVO;
	}

//	public ChefSkillsVO updateChefSkills(Integer chefid, Integer skillid) {
//
//		ChefSkillsVO chefSkillsVO = new ChefSkillsVO();
//		chefSkillsVO.setChefid(chefid);
//		chefSkillsVO.setSkillsid(skillid);
//		dao.update(chefSkillsVO);
//
//		return chefSkillsVO;
//	}
	
	public void deleteChefSkills(Integer chefid, Integer skillid) {
		dao.delete(chefid, skillid);
	}

	public ChefSkillsVO getOneChefSkills(Integer chefid, Integer skillid) {
		return dao.findByPrimaryKey(chefid, skillid);
	}

	public List<ChefSkillsVO> getAll() {
		return dao.getAll();
	}
	public List<ChefSkillsVO> getAll(Map<String, String[]> map) {
		return dao.getAll(map);
	}
}
