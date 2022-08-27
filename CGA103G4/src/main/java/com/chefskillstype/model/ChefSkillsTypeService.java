package com.chefskillstype.model;

import java.util.List;

public class ChefSkillsTypeService {
	private ChefSkillsTypeDAO_interface dao;

	public ChefSkillsTypeService() {
		dao = new ChefSkillsTypeJDBCDAO();
	}

	public ChefSkillsTypeVO addChefSkillsType(String skill) {

		ChefSkillsTypeVO chefSkillsTypeVO = new ChefSkillsTypeVO();

		chefSkillsTypeVO.setSkill(skill);
		dao.insert(chefSkillsTypeVO);

		return chefSkillsTypeVO;
	}

	public ChefSkillsTypeVO updateChefSkillsType(Integer skillid, String skill) {

		ChefSkillsTypeVO chefSkillsTypeVO = new ChefSkillsTypeVO();
		chefSkillsTypeVO.setSkillid(skillid);
		chefSkillsTypeVO.setSkill(skill);
	dao.update(chefSkillsTypeVO);

		return chefSkillsTypeVO;
	}

	public ChefSkillsTypeVO getOneChefSkillsType(Integer skillid) {
		return dao.findByPrimaryKey(skillid);
	}

	public List<ChefSkillsTypeVO> getAll() {
		return dao.getAll();
	}
}
